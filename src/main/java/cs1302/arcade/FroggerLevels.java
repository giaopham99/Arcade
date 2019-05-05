package cs1302.arcade;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

/**
 *This class is used for creating the levels for {@code AppFrogger}.
 *It uses an array to store the {@code ImageView} objects that are 
 *then used to fill in a {@code TilePane} to represent each level.
 */
public class FroggerLevels{

    static final Image WATER=new Image("FroggerDesign/Water.png");
    static final Image ROAD=new Image("FroggerDesign/Road.png");
    static final Image GRASS=new Image("FroggerDesign/Grass.png");
    static final Image BUSH=new Image("FroggerDesign/Bush.png");
    static final Image LILY=new Image("FroggerDesign/Lily.png");

    TilePane level;
    ImageView[][] boardView;

    /**
     *The default constructor for {@code FroggerLevels}.
     *It creates an {@code TilePane} with no images,
     *then calls {@code genLevel1} to fill out the 
     *board with the correct images. 
     */
    public FroggerLevels(){
        lvl =1;
        boardView = new ImageView[10][6];
        level = new TilePane();
        level.setPrefColumns(6);
        level.setPrefRows(10);
        for(int row =0; row<10; row++){
            for(int col =0; col<6; col++){
                boardView[row][col] = new ImageView();
                level.getChildren().add(boardView[row][col]);
            }//for
        }//for
        genLevel1();
    }//FroggerLevels

    /**
     *Getter method for the board
     *@return level, a {@code TilePane} representing the level
     */
    public TilePane getLevel(){
        return level;
    }//getLevel

    /**
     *Changes each {@code ImageView} in {@code boardView}
     *to the corresponding {@code ImageView} for level 1
     */
    protected void genLevel1(){
        for (int i=0;i<10;i++){
            if (i==0){
                for (int j=0;j<6;j++){
                    if(j==0 || j==2 || j ==4){
                        boardView[i][j].setImage(BUSH);
                    }//if
                    else{
                        boardView[i][j].setImage(LILY);
                    }//else
                }//for
            }//if
            else if (i==3){
                waterRow(i);
            }//else if
            else if (i==7){
                roadRow(i);
            }//else If
            else {
                grassRow(i);
            }//else
        }//for
    }//genLevel1

    /**
     *Changes each {@code ImageView} in {@code boardView}
     *to the corresponding {@code ImageView} for level 2
     */
    protected void genLevel2(){
        for (int i=0;i<10;i++){
            if (i==0){
                for (int j=0;j<6;j++){
                    if (j==1 || j==4){
                        boardView[i][j].setImage(LILY);
                    }//if
                    else{
                        boardView[i][j].setImage(BUSH);
                    }//else
                }//for
            }//if
            else if (i==3){
                waterRow(i);
            }//else if
            else if (i==7 || i==5){
                roadRow(i);
            }//else If
            else {
                grassRow(i);
            }//else
        }//for
    }//genLevel2

    /**
     *Changes each {@code ImageView} in {@code boardView}
     *to the corresponding {@code ImageView} for level 3
     */
    protected void genLevel3(){
        for (int i=0;i<10;i++){
            if (i==0){
                for (int j=0;j<6;j++){
                    if (j==2){
                        boardView[i][j].setImage(LILY);
                    }//if
                    else{
                        boardView[i][j].setImage(BUSH);
                    }//else
                }//for
            }//if
            else if (i==2 || i==4){
                waterRow(i);
            }//else if
            else if (i==8 || i==7 || i==5){
                roadRow(i);
            }//else If
            else {
                grassRow(i);
            }//else
        }//for
    }//genLevel3

    /**
     *Changes an entire row in {@code boardView}
     *to a grass {@code ImageView}
     *
     *@param row, the row in the {@code boardView} to set to grass
     */
    private void grassRow(int row){
        for (int i=0;i<6;i++){
            boardView[row][i].setImage(GRASS);
        }//for
    }//grassRow

    /**
     *Changes an entire row in {@code boardView}
     *to a water {@code ImageView}
     *
     *@param row, the row in the {@code boardView} to set to water
     */
    private void waterRow(int row){
        for (int i=0;i<6;i++){
            boardView[row][i].setImage(WATER);
        }//for
    }//waterRow

    /**
     *Changes an entire row in {@code boardView}
     *to a road {@code ImageView}
     *
     @param row, the row in the {@code boardView} to set to road
     */
    private void roadRow(int row){
        for (int i=0;i<6;i++){
            boardView[row][i].setImage(ROAD);
        }//for
    }//roadRow
    
}//FroggerLevels 
