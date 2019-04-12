package cs1302.arcade;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Modality;

//might have to import event handler

public class UtilityBar extends MenuBar{

    MenuItem exitArcade;
    MenuItem exitGame;
    MenuItem controls;
    Menu file;
    Menu help;
    private final String RULES_TETRIS = "Controls- \n" +
            "LEFT ARROW KEY: Moves the block left one space. \n" +
            "RIGHT ARROW KEY: Moves the block right one space. \n" +
            "DOWN ARROW KEY: Slams the block down." +
            "SPACE BAR: Rotates block. \n \n" +
            "How To Play- \n" +
            "Fill an entire row with blocks to increase \n score and clear the row. " +
            "If the blocks exceed the maximum height of the play area,\n the game is over.";

    private final String RULES_2048 = "Controls- \n" +
            "LEFT ARROW KEY: Slides all Tiles to the left. \n" +
            "RIGHT ARROW KEY: Slides all Tiles to the right. \n" +
            "DOWN ARROW KEY: Slides all Tiles down. \n"+
            "UP ARROW KEY: Slides all Tiles up. \n"+
            "How To Play- \n"+
            "Combine tiles with the same numeric values to reach 2048. \n"+
            "After reaching the Tile 2048, you may choose to continue to reach 8192.";
    
    public UtilityBar(){
        super();
        exitArcade=new MenuItem("Exit Arcade");
        exitGame=new MenuItem("Exit Game");
        controls=new MenuItem("Controls");

        //set actions for menuItems
        exitArcade.setOnAction(e -> System.exit(0));
        exitGame.setOnAction(e -> System.exit(0)); //Leaves game but not arcade
        controls.setOnAction(e -> controlScreen());

        
        file.getItems().addAll(exitArcade, exitGame);
        help.getItems().addAll(controls);
        
        this.getMenus().addAll(file, help);
    }//UtilityBar

    private void controlScreen(){
        //Create vboxes to contain rules and buttons
        //Add vboxes to tab
        //add tabs to pane
        //add pane to scene

        VBox instruct = new VBox();
        TabPane pane = new TabPane();
        Tab tabTetris = new Tab("Tetris Rules",new Text(RULES_TETRIS));
        Tab tab2048 = new Tab("2048 Rules",new Text(RULES_2048));

        pane.getTabs.addAll(tabTetris, tab2048);
        instruct.getChildren().addAll(pane);
        //Create Stage and Scene
        Scene scene = new Scene(instruct);
        Stage helpWindow = new Stage(scene);
        helpWindow.initModality(Modality.APPLICATION_MODAL);
        helpWindow.setTitle("How To Play");
        helpWindow.setMinWidth(300);
        helpWindow.setMinHeight(400);
        
        Button close = new Button("Close");
        close.setOnAction(e -> helpWindow.close());

        helpWindow.show();
    } // controlScreen()
    
}//UtilityBar
