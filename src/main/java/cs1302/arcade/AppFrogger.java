package cs1302.arcade;

import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;


public class AppFrogger extends StackPane{
     
    Player p1;
    FroggerItems frog = new FroggerItems("frog",90,0,true);
    
    public AppFrogger(){
        super();
        FroggerLevels levelGen=new FroggerLevels();
        frog.rotateImg(180);
        this.getChildren().add(levelGen.getLevel());
        this.getChildren().add(frog.getImg());
    }//AppFrogger
    
    public void moveUp(){
        frog.addY(-10);
        
    }//moveUp

    public void moveDown(){
        frog.addY(10);
    }//moveDown

    public void moveLeft(){
        frog.addX(-10);
    }//moveLeft

    public void moveRight(){
        frog.addX(10);
    }//moveRight
    
}//AppFrogger
