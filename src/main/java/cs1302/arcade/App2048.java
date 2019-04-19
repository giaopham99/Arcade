package cs1302.arcade;

import javafx.scene.Group;
import javafx.scene.layout.GridPane;

public class App2048 extends Group{

    //some checker class to check the way you can move

    
    private Tile[][] board = new Tile[4][4];
    private Controller control;
    private Player p1;
   
    public App2048(){
        super();

        control = new Controller(this, true);
        p1 = new Player();
        for(int row = 0; row < 4; row++) {
            for(int col = 0; col < 4; col++) {
                board[row][col] = new Tile(0);
            }
        }

        genRandPos();
        genRandPos();
        //win menu (continue or new game)
        //game over menu (retrun to arcade, close, or new game)
    }//App2048

    public void genRandPos(){
        int genX = (int)(Math.random() * 4);
        int genY = (int)(Math.random() * 4);
        
        if(board[genX][genY].getValue()==0) {
            board[genX][genY] = new Tile(2);
        }
    } // getRandPos

    private void swap(int row,int first, int sec){
        Tile copy=board[row][first];
        board[row][first]=board[row][sec];
        board[row][sec]=copy;
    }//swap

    public void slideUp(){
        for (int col=0;col<4;col++){
            this.slideColUp(col);
            this.combineUp(col);
            this.slideColUp(col);
        }//for
    }//slideUp

    public void slideColUp(int col){
        for (int row=0;row<3;row++){
            if (board[row][col].getValue()==0 && board[row+1][col].getValue()>0){
                swap(col,row,row+1);
            }//if
        }//for
    }//slideColUp

    public void combineUp(int col){
        for (int row=0;row<3;row++){
            if (board[row][col].getValue() == board[row+1][col].getValue()){
                board[row][col].setValue(2*board[row+1][col].getValue());
                board[row+1][col].setValue(0);
                p1.addScore(board[row][col]);
            }//if
        }//for
    }//combineUp

    public void slideDown(){
        for (int col=0;col<4;col++){
            this.slideColDown(col);
            this.combineDown(col);
            this.slideColDown(col);
        }//for
    }//slideDown

    public void slideColDown(int col){
        for (int row=3;row>0;row--){
            if (board[row][col].getValue()==0 && board[row-1][col].getValue()>0){
                swap(col,row,row-1);
            }//if
        }//for
    }//slideColDown

    public void combineDown(int col){
        for (int row=3;row>0;row--){
            if (board[row][col].getValue() == board[row-1][col].getValue()){
                board[row][col].setValue(2*board[row-1][col].getValue());
                board[row-1][col].setValue(0);
                p1.addScore(board[row][col]);
            }//if
        }//for
    }//combineDown

    public void slideLeft(){
        for (int row=0;row<4;row++){
            this.slideRowLeft(row);
            this.combineLeft(row);
            this.slideRowLeft(row);
        }//for
    }//slideLeft

    public void slideRowLeft(int row){
        for (int col=0;col<3;col++){
            if (board[row][col].getValue()==0 && board[row][col+1].getValue()>0){
                swap(row,col,col+1);
            }//if
        }//for
    }//slideRowLeft

    private void combineLeft(int row){
        for (int col=0;col<3;col++){
            if (board[row][col].getValue() == board[row][col+1].getValue()){
                board[row][col].setValue(2*board[row][col+1].getValue());
                board[row][col+1].setValue(0);
                p1.addScore(board[row][col]);
            }//if
        }//for
    }//combineLeft

    public void slideRight(){
        for (int row=0;row<4;row++){
            this.slideRowRight(row);
            this.combineRight(row);
            this.slideRowRight(row);
        }//for
    }//slideRight

    private void slideRowRight(int row){
        for (int col=3;col>0;col--){
            if (board[row][col].getValue()==0 && board[row][col-1].getValue()>0){
                swap(row,col,col-1);
            }//if
        }//for
    }//slideRowRight

    private void combineRight(int row){
        for (int col=3;col>0;col--){
            if (board[row][col].getValue() == board[row][col-1].getValue()){
                board[row][col].setValue(2*board[row][col-1].getValue());
                board[row][col-1].setValue(0);
                p1.addScore(board[row][col]);
            }//if
        }//for
    }//combineRight
}//App2048
