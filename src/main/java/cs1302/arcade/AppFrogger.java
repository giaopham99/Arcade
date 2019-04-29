package cs1302.arcade;

import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;


public class AppFrogger extends StackPane{
     
    Player p1;
    FroggerItems frog = new FroggerItems("frog",200,360,true);
    
    public AppFrogger(){
        super();
        FroggerLevels levelGen=new FroggerLevels();
        frog.rotateImg(180);
        this.getChildren().add(levelGen.getLevel());
        this.getChildren().add(frog.getImg());
    }//AppFrogger
    
    public void moveUp(){
        frog.addY(-80);
        frog.rotateImg(180);
    }//moveUp

    public void moveDown(){
        frog.addY(80);
        frog.rotateImg(0);
    }//moveDown

    public void moveLeft(){
        frog.addX(-80);
        frog.rotateImg(90);
    }//moveLeft

    public void moveRight(){
        frog.addX(80);
        frog.rotateImg(270);
    }//moveRight
    
}//AppFrogger
