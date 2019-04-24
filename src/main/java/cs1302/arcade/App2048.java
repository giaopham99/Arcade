package cs1302.arcade;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.geometry.Pos;
import javafx.scene.input.KeyCode;
import java.awt.event.KeyEvent;
import javafx.stage.Modality;

public class App2048 extends VBox{
    
    private Tile[][] board;
    private Player p1;
    private TilePane game;
    private VBox grid;
    private HBox scoreBoard;
    private Text score;
    private Text actualScore;
    private boolean won;
    private Button restartGame;
    
    public App2048(){
        super();
        won = false;
        board = new Tile[4][4];
        game = new TilePane();
        setGrid();
        grid = new VBox();
        scoreBoard = new HBox(10);
        restartGame = new Button("Restart");
        grid.setMaxWidth(430);
        grid.setMinWidth(430);
        grid.setMaxHeight(430);
        grid.setMinHeight(430);
        p1 = new Player();        
        score = new Text("Score: ");
        actualScore = new Text(Integer.toString(p1.getScore()));
        scoreBoard.getChildren().addAll(restartGame,score, actualScore);
        grid.getChildren().addAll(game);

        //Setting up a blank board
        for(int row = 0; row < 4; row++) {
            for(int col = 0; col < 4; col++) {
                board[row][col] = new Tile(0);
                game.getChildren().add(board[row][col].getImgView());
            }//for
        }//for

        restartGame.setOnAction(e->restart());
        
        //Start with two random tiles
        genRandPos();
        genRandPos();
        this.getChildren().addAll(scoreBoard,grid);
    }//App2048

    private void displayWin(){
        VBox root=new VBox();
        HBox buttons=new HBox();
        Text winMessage=new Text("Congratulations! You won!");
        Button keepGoing=new Button("Continue");
        Button restart=new Button("Restart");

        buttons.getChildren().addAll(keepGoing, restart);
        root.getChildren().addAll(winMessage, buttons);
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Winner!");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.sizeToScene();
        stage.setResizable(false);
        stage.show();

        keepGoing.setOnAction(e-> stage.close());
        restart.setOnAction(e-> {
                restart();
                stage.close();
                    });
    }//displayWin

    private void displayLoss(){
        VBox root=new VBox();
        HBox buttons=new HBox();
        Text lossMessage=new Text("No possible moves! You Lose :(");
        Button playAgain=new Button("Play Again?");

        buttons.getChildren().addAll(playAgain);
        root.getChildren().addAll(lossMessage, buttons);
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Game Over!");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.sizeToScene();
        stage.setResizable(false);
        stage.show();

        playAgain.setOnAction(e->{
                restart();
                stage.close();
            });
    }//displayLoss
    
    private void setGrid(){
        game.setHgap(10);
        game.setVgap(10);
        // game.setMaxWidth(450);
        // game.setMaxHeight(450);
        game.setPrefColumns(4);
        game.setPrefRows(4);
        /*
        for(int row=0;row <4; row++){
            for(int col=0; col<4; col++){
                game.setConstraints(board[row][col].getImgView(),row,col);
            }//for
        }//for
        */
    }//setGrid

    private void restart(){
        for (int row=0;row<4;row++){
            for (int col=0;col<4;col++){
                board[row][col].setValue(0);
            }//for
        }//for
        won = false;
        genRandPos();
        genRandPos();
        p1.setScore(0);
        actualScore.setText(Integer.toString(p1.getScore()));
    }//restart
    
    public boolean isFull(){
        boolean isFull = true;
        for(int row = 0; row < 4 && isFull; row++){
            for(int col = 0; col < 4 && isFull; col++){
                if(board[row][col].getValue() == 0){
                    isFull = false;
                }//if
            }//for
        }//for
        return isFull;
    }// isFull 

    private void gameOver(){
        if(!canSlideH() && !canSlideV() && isFull()){
            displayLoss();
        }//if
    }//gameOver
    
    public void genRandPos(){
        boolean generated=false;

        while(!generated && !isFull()){
            int genX = (int)(Math.random() * 4);
            int genY = (int)(Math.random() * 4);
            int whichTile = (int)(Math.random() * 10);
            
            if(board[genX][genY].getValue()==0) {
                if(whichTile == 9){
                    board[genX][genY].setValue(4);
                    generated=true;
                }//if
                else{
                    board[genX][genY].setValue(2);
                    generated=true;
                }//else
            }//if
        }//while
    }// getRandPos
    
    private void swapH(int row,int first, int sec){
        Tile copy = new Tile(board[row][first].getValue());
        board[row][first].setValue(board[row][sec].getValue());
        board[row][sec].setValue(copy.getValue());
    }//swapH

    private void swapV(int col, int first, int sec){
        Tile copy = new Tile(board[first][col].getValue());
        board[first][col].setValue(board[sec][col].getValue());
        board[sec][col].setValue(copy.getValue());
    }//swapV

    private boolean canSlideH(){
        boolean canSlide = false;
        for(int row=0; row<3 && !canSlide; row++){
            for(int col=0;col<4 && !canSlide; col++){
                if(board[row][col].getValue()== board[row+1][col].getValue()){
                    canSlide = true;
                }//if
            }//for
        }//for
        return canSlide;   
    }//canSlideH
    
    private boolean canSlideV(){
        boolean canSlide = false;
        for(int row=0; row<4 && !canSlide; row++){
            for(int col=0;col<3 && !canSlide; col++){
                if(board[row][col].getValue()== board[row][col+1].getValue()){
                    canSlide = true;
                }//if
            }//for
        }//for
        return canSlide;
    }//canSlideV
    
    public void slideUp(){
        Tile[][] copy = board;
        boolean canSlide = false;
        
        //Slide Up
        for (int col=0;col<4;col++){
            this.slideColUp(col);
            this.combineUp(col);
            this.slideColUp(col);
        }//for
        genRandPos();
        gameOver();
    }//slideUp
    
    public void slideColUp(int col){
        for (int i=0;i<3;i++){
            for (int row=0;row<3;row++){
                if (board[row][col].getValue()==0 && board[row+1][col].getValue()>0){
                    swapV(col,row,row+1);
                }//if
            }//for
        }//for
    }//slideColUp

    public void combineUp(int col){
        for (int row=0;row<3;row++){
            if (board[row][col].getValue() == board[row+1][col].getValue()){
                board[row][col].setValue(2*board[row+1][col].getValue());
                board[row+1][col].setValue(0);
                p1.addScore(board[row][col].getValue());
                actualScore.setText(Integer.toString(p1.getScore()));
                if(!won && board[row][col].getValue()==2048){
                    displayWin();
                    won = true;
                }//if
            }//if
        }//for
    }//combineUp

    public void slideDown(){
        Tile[][] copy = board;
        boolean canSlide = false;
        
        for (int col=0;col<4;col++){
            this.slideColDown(col);
            this.combineDown(col);
            this.slideColDown(col);
        }//for
        genRandPos();
        gameOver();
    }//slideDown
    
    public void slideColDown(int col){
        for (int i=0;i<3;i++){
            for (int row=3;row>0;row--){
                if (board[row][col].getValue()==0 && board[row-1][col].getValue()>0){
                    swapV(col,row,row-1);
                }//if
            }//for
        }//for
    }//slideColDown

    public void combineDown(int col){
        for (int row=3;row>0;row--){
            if (board[row][col].getValue() == board[row-1][col].getValue()){
                board[row][col].setValue(2*board[row-1][col].getValue());
                board[row-1][col].setValue(0);
                p1.addScore(board[row][col].getValue());
                actualScore.setText(Integer.toString(p1.getScore()));
                if(!won && board[row][col].getValue()==2048){
                    displayWin();
                }//if
                
            }//if
        }//for
    }//combineDown
    
    public void slideLeft(){
        Tile[][] copy = board;
        boolean canSlide = false;
        
        for (int row=0;row<4;row++){
            this.slideRowLeft(row);
            this.combineLeft(row);
            this.slideRowLeft(row);
        }//for
        genRandPos();
        gameOver();
    }//slideLeft
    
    public void slideRowLeft(int row){
        for (int i=0;i<3;i++){
            for (int col=0;col<3;col++){
                if (board[row][col].getValue()==0 && board[row][col+1].getValue()>0){
                    swapH(row,col,col+1);
                }//if
            }//for
        }//for
    }//slideRowLeft
    
    private void combineLeft(int row){
        for (int col=0;col<3;col++){
            if (board[row][col].getValue() == board[row][col+1].getValue()){
                board[row][col].setValue(2*board[row][col+1].getValue());
                board[row][col+1].setValue(0);
                p1.addScore(board[row][col].getValue());
                actualScore.setText(Integer.toString(p1.getScore()));
                if(!won && board[row][col].getValue()==2048){
                    displayWin();
                }//if
            }//if
        }//for
    }//combineLeft
    
    public void slideRight(){
        Tile[][] copy = board;
        boolean canSlide = false;
        
        for (int row=0;row<4;row++){
            this.slideRowRight(row);
            this.combineRight(row);
            this.slideRowRight(row);
        }//for
        genRandPos();
        gameOver();
    }//slideRight
    
    private void slideRowRight(int row){
        for (int i=0;i<3;i++){
            for (int col=3;col>0;col--){
                if (board[row][col].getValue()==0 && board[row][col-1].getValue()>0){
                    swapH(row,col,col-1);
                }//if
            }//for
        }//for
    }//slideRowRight
    
    private void combineRight(int row){
        for (int col=3;col>0;col--){
            if (board[row][col].getValue() == board[row][col-1].getValue()){
                board[row][col].setValue(2*board[row][col-1].getValue());
                board[row][col-1].setValue(0);
                p1.addScore(board[row][col].getValue());
                actualScore.setText(Integer.toString(p1.getScore()));
                if(!won && board[row][col].getValue()==2048){
                    displayWin();
                }//if
            }//if
        }//for
    }//combineRight
}//App2048
