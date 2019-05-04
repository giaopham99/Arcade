package cs1302.arcade;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.geometry.Pos;
import javafx.stage.Modality;

/**
 * Main class that creates the functionalities of 2048.
 */
public class App2048 extends VBox{

    //Instance variables
    private Tile[][] board;
    private Player p1;
    private TilePane game;
    private VBox grid;
    private HBox scoreBoard;
    private Text score;
    private Text actualScore;
    private boolean won;
    private Button restartGame;

    /**
     * Constructs a new 2048 game.
     */
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
    
    /**
     * Private method that creates a "Win" screen for when the player has reached the 2048 tile.
     */
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
    
    /**
     * Private method that creates a "Game Over" screen with options for when player loses the game.
     */
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

    /**
     * Private method to set up the 2048 game area properties.
     */
    private void setGrid(){
        game.setHgap(10);
        game.setVgap(10);
        game.setPrefColumns(4);
        game.setPrefRows(4);
    }//setGrid

    /**
     * Private method to reset the game board for a new game.
     */
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

    /**
     * Private method to check if the game board is full.
     * @return true if the board is full; false otherwise.
     */
    private boolean isFull(){
        boolean isFull = true;
        for(int row = 0; row < 4 && isFull; row++){
            for(int col = 0; col < 4 && isFull; col++){
                if(board[row][col].getValue() == 0){
                    isFull = false;
                }//if
            }//for
        }//for
        return isFull;
    }//isFull 

    /**
     * Private method to check when the game is over and displays the lost message.
     */
    private void gameOver(){
        if(!canSlideH() && !canSlideV() && isFull()){
            displayLoss();
        }//if
    }//gameOver

    /**
     * Private method to generate a random 2 or 4 Tile onto the board.
     */ 
    private void genRandPos(){
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
    }//getRandPos

    /**
     * Private method to swap a Tile with another horizontally.
     * @param row the row where the swap will happen.
     * @param first the first Tile's index.
     * @param sec the second Tile's index.
     */
    private void swapH(int row,int first, int sec){
        Tile copy = new Tile(board[row][first].getValue());
        board[row][first].setValue(board[row][sec].getValue());
        board[row][sec].setValue(copy.getValue());
    }//swapH

    /**
     * Private method to swap a Tile with another vertically.
     * @param col the column where the swap will happen.
     * @param first the first Tile's index.
     * @param sec the second Tile's index.
     */
    private void swapV(int col, int first, int sec){
        Tile copy = new Tile(board[first][col].getValue());
        board[first][col].setValue(board[sec][col].getValue());
        board[sec][col].setValue(copy.getValue());
    }//swapV
   
    /**
     * Private method that checks to see if any Tile can still slide Horizontally.
     * @return true if Tiles can still slide; false otherwise.
     */
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

    /**
     * Private method that checks to see if any Tile can still slide Vertically.
     * @return true if Tiles can still slide; false otherwise.
     */
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

    /**
     * Method to slide all the tiles up and generates a new Tile after each slide.
     * Checks to see if the game can continue after each move.
     */
    public void slideUp(){
        Tile[][] copy = board;
        
        //Slide Up
        for (int col=0;col<4;col++){
            this.slideColUp(col);
            this.combineUp(col);
            this.slideColUp(col);
        }//for
        genRandPos();
        gameOver();
    }//slideUp

    /**
     * Private method to slide a specific column up.
     * @param col the specified column to move up.
     */
    private void slideColUp(int col){
        for (int i=0;i<3;i++){
            for (int row=0;row<3;row++){
                if (board[row][col].getValue()==0 && board[row+1][col].getValue()>0){
                    swapV(col,row,row+1);
                }//if
            }//for
        }//for
    }//slideColUp

    /**
     * Private method to combine elements in a specified column upwards.
     * @param col the specified column to combine upwards.
     */
    private void combineUp(int col){
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

    /**
     * Method to slide all the tiles down and generate a new Tile after each slide.
     * Checks to see if the game can continue after each move.
     */
    public void slideDown(){
        Tile[][] copy = board;
        
        for (int col=0;col<4;col++){
            this.slideColDown(col);
            this.combineDown(col);
            this.slideColDown(col);
        }//for
        genRandPos();
        gameOver();
    }//slideDown

    /**
     * Private method to slide a specific column down.
     * @param col the specified column to move down.
     */
    private void slideColDown(int col){
        for (int i=0;i<3;i++){
            for (int row=3;row>0;row--){
                if (board[row][col].getValue()==0 && board[row-1][col].getValue()>0){
                    swapV(col,row,row-1);
                }//if
            }//for
        }//for
    }//slideColDown

    /**
     * Private method to combine elements in a specified column downwards.
     * @param col the specified column to combine downwards.
     */     
    private void combineDown(int col){
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
    
    /**
     * Method to slide all the tiles left and generates a new Tile after each slide.
     * Checks to see if the game can continue after each move.
     */
    public void slideLeft(){
        Tile[][] copy = board;
        
        for (int row=0;row<4;row++){
            this.slideRowLeft(row);
            this.combineLeft(row);
            this.slideRowLeft(row);
        }//for
        genRandPos();
        gameOver();
    }//slideLeft

    /**
     * Private method to slide a specific row left.
     * @param row the specified row to move left.
     */
    private void slideRowLeft(int row){
        for (int i=0;i<3;i++){
            for (int col=0;col<3;col++){
                if (board[row][col].getValue()==0 && board[row][col+1].getValue()>0){
                    swapH(row,col,col+1);
                }//if
            }//for
        }//for
    }//slideRowLeft

    /**
     * Private method to combine elements in a specified row left.
     * @param row the specified row to combine left.
     */     
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
    
    /**
     * Method to slide all the tiles right and generates a new Tile after each slide.
     * Checks to see if the game can continue after each move.
     */
    public void slideRight(){
        Tile[][] copy = board;
        
        for (int row=0;row<4;row++){
            this.slideRowRight(row);
            this.combineRight(row);
            this.slideRowRight(row);
        }//for
        genRandPos();
        gameOver();
    }//slideRight

    /**
     * Private method to slide a specific row right.
     * @param row the specified row to move right.
     */
    private void slideRowRight(int row){
        for (int i=0;i<3;i++){
            for (int col=3;col>0;col--){
                if (board[row][col].getValue()==0 && board[row][col-1].getValue()>0){
                    swapH(row,col,col-1);
                }//if
            }//for
        }//for
    }//slideRowRight

    /**
     * Private method to combine elements in a specified row right.
     * @param row the specified row to combine right.
     */     
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
