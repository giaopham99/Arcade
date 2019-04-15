package cs1302.arcade;

import javafx.scene.Group;

public class App2048 extends Group{

    //some checker class to check the way you can move

    
    private Tile[][] board = new Tile[4][4];

    
    public App2048(){
        super();
        scoreBoard = 0;

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
}//App2048
