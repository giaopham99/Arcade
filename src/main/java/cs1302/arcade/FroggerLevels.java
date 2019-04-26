package cs1302.arcade;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

public class FroggerLevels{

    static final Image WATER=new Image("FroggerDesign/Water.png");
    static final Image ROAD=new Image("FroggerDesign/Road.png");
    static final Image GRASS=new Image("FroggerDesign/Grass.png");
    static final Image BUSH=new Image("FroggerDesign/Bush.png");
    static final Image LILY=new Image("FroggerDesign/BushLilly.png");
    
    protected TilePane genLevel1(){
        TilePane level1=new TilePane();

        level1.setPrefColumns(6);
        level1.setPrefRows(10);

        for (int i=0;i<10;i++){
            if (i==0){
                for (int j=0;j<3;j++){
                    level1.getChildren().addAll(new ImageView(BUSH),new ImageView(LILY));
                }//for
            }//if
            else if (i==3){
                waterRow(level1);
            }//else if
            else if (i==7){
                roadRow(level1);
            }//else If
            else {
                grassRow(level1);
            }//else
        }//for

        return level1;
    }//genLevel1

    protected TilePane genLevel2(){
        TilePane level2=new TilePane();
        level2.setPrefColumns(6);
        level2.setPrefRows(10);

        for (int i=0;i<10;i++){
            if (i==0){
                for (int j=0;j<6;j++){
                    if (j==1 || j==4){
                        level2.getChildren().add(new ImageView(LILY));
                    }//if
                    else{
                        level2.getChildren().add(new ImageView(BUSH));
                    }//else
                }//for
            }//if
            else if (i==3){
                waterRow(level2);
            }//else if
            else if (i==7 || i==5){
                roadRow(level2);
            }//else If
            else {
                grassRow(level2);
            }//else
        }//for
        return level2;
    }//genLevel2

    protected TilePane genLevel3(){
        TilePane level3=new TilePane();
        level3.setPrefColumns(6);
        level3.setPrefRows(10);

        for (int i=0;i<10;i++){
            if (i==0){
                for (int j=0;j<6;j++){
                    if (j==2){
                        level3.getChildren().add(new ImageView(LILY));
                    }//if
                    else{
                        level3.getChildren().add(new ImageView(BUSH));
                    }//else
                }//for
            }//if
            else if (i==2 || i==4){
                waterRow(level3);
            }//else if
            else if (i==8 || i==7 || i==5){
                roadRow(level3);
            }//else If
            else {
                grassRow(level3);
            }//else
        }//for
        return level3;
    }//genLevel3

    protected void grassRow(TilePane tp){
        for (int i=0;i<6;i++){
            tp.getChildren().add(new ImageView(GRASS));
        }//for
    }//grassRow

    protected void waterRow(TilePane tp){
        for (int i=0;i<6;i++){
            tp.getChildren().add(new ImageView(WATER));
        }//for
    }//waterRow

    protected void roadRow(TilePane tp){
        for (int i=0;i<6;i++){
            tp.getChildren().add(new ImageView(ROAD));
        }//for
    }//roadRow
    
}//FroggerLevels 
