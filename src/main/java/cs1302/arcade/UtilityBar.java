package cs1302.arcade;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.MenuBar;
//might have to import event handler

public class UtilityBar extends MenuBar{

    MenuItem exitArcade;
    MenuItem exitGame;
    MenuItem controls;
    Menu file;
    Menu help;

    public UtilityBar(){
        super();
        exitArcade=new MenuItem("Exit Arcade");
        exitGame=new MenuItem("Exit Game");
        controls=new MenuItem("Controls");

        //set actions for menuItems
        exitArcade.setOnAction(e --> System.exit(0));

        file.getItems().addAll(exitArcade, exitGame);
        help.getItems().addAll(controls);
        
        this.getMenus().addAll(file, help);
    }//UtilityBar
    
}//UtilityBar
