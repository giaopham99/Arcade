package cs1302.arcade;

import javafx.scene.layout.VBox;
import javafx.scene.layout.TilePane;


public class AppFrogger extends VBox{
    
    TilePane level1;
    TilePane level2;
    TilePane level3;
    Player p1;
    
    public AppFrogger(){
        super();
        FroggerLevels levelGen=new FroggerLevels();
        //level1=levelGen.genLevel1();
        //level2=levelGen.genLevel2();
        level3=levelGen.genLevel3();

        this.getChildren().add(level3);
    }//AppFrogger
    
    
}//AppFrogger
