package cs1302.arcade;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.geometry.Pos;
import javafx.scene.input.KeyCode;
import java.awt.event.KeyEvent;
import javafx.stage.Modality;

public class App2048 extends Group{

    //some checker class to check the way you can move

    
    private Tile[][] board;

    private Player p1;
    private TilePane game;
    private VBox grid;
    private Text score;
    
    public App2048(){
        super();
        board = new Tile[4][4];
        game = new TilePane();
        setGrid();
        grid = new VBox();
        grid.setMaxWidth(500);
        grid.setMinWidth(500);
        
        score = new Text("Score");
        grid.getChildren().addAll(game);

        p1 = new Player();

        //Setting up a blank board
        for(int row = 0; row < 4; row++) {
            for(int col = 0; col < 4; col++) {
                board[row][col] = new Tile(0);
                game.getChildren().add(board[row][col].getImgView());
            }//for
        }//for
        
        //Start with two random tiles
        genRandPos();
        genRandPos();


        this.getChildren().addAll(grid);
        //win menu (continue or new game)
        //game over menu (retrun to arcade, close, or new game)
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
        stage.show();

        keepGoing.setOnAction(e-> stage.close());
        restart.setOnAction(e-> restart());
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
        stage.show();

        playAgain.setOnAction(e-> restart());
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

        p1.setScore(0);
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

    public void genRandPos(){
        boolean generated=false;

        while(!generated){
            int genX = (int)(Math.random() * 4);
            int genY = (int)(Math.random() * 4);
            
            if(board[genX][genY].getValue()==0) {
                board[genX][genY].setValue(2);
                generated=true;
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

    public boolean slideUp(){
        Tile[][] copy = board;
        boolean canSlide = false;

        //Slide Up
        for (int col=0;col<4;col++){
            this.slideColUp(col);
            this.combineUp(col);
            this.slideColUp(col);
        }//for

        for(int row = 0; row < 4 && !canSlide; row++){
            for(int col = 0; col < 4 && !canSlide; col++){
                if(copy[row][col].getValue() != board[row][col].getValue()){
                    canSlide = true;
                }//if
            }// for
        }//for

        genRandPos();

        return canSlide;
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
            }//if
        }//for
    }//combineUp

    public boolean slideDown(){
        Tile[][] copy = board;
        boolean canSlide = false;
        
        for (int col=0;col<4;col++){
            this.slideColDown(col);
            this.combineDown(col);
            this.slideColDown(col);
        }//for
        
        for(int row = 0; row < 4 && !canSlide; row++){
            for(int col = 0; col < 4 && !canSlide; col++){
                if(copy[row][col].getValue() != board[row][col].getValue()){
                    canSlide = true;
                }//if
            }// for
        }//for

        genRandPos();

        return canSlide;
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
            }//if
        }//for
    }//combineDown

    public boolean slideLeft(){
        Tile[][] copy = board;
        boolean canSlide = false;
        
        for (int row=0;row<4;row++){
            this.slideRowLeft(row);
            this.combineLeft(row);
            this.slideRowLeft(row);
        }//for
        
        for(int row = 0; row < 4 && !canSlide; row++){
            for(int col = 0; col < 4 && !canSlide; col++){
                if(copy[row][col].getValue() != board[row][col].getValue()){
                    canSlide = true;
                }//if
            }// for
        }//for

        genRandPos();

        return canSlide;
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
            }//if
        }//for
    }//combineLeft

    public boolean slideRight(){
        Tile[][] copy = board;
        boolean canSlide = false;
        
        for (int row=0;row<4;row++){
            this.slideRowRight(row);
            this.combineRight(row);
            this.slideRowRight(row);
        }//for

        for(int row = 0; row < 4 && !canSlide; row++){
            for(int col = 0; col < 4 && !canSlide; col++){
                if(copy[row][col].getValue() != board[row][col].getValue()){
                    canSlide = true;
                }//if
            }// for
        }//for

        genRandPos();
        
        return canSlide;
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
            }//if
        }//for
    }//combineRight
}//App2048
