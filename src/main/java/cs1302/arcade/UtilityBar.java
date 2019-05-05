package cs1302.arcade;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Modality;

/**
 *Class used to create a utility bar for our arcade app. Has functions to display controls 
 *and exit the arcade
 */ 
public class UtilityBar extends MenuBar{

    MenuItem exitArcade;
    MenuItem exitGame;
    MenuItem controls;
    Menu file;
    Menu help;
    
    private final String RULES_FROGGER = "Controls- \n\n" +
            "LEFT ARROW KEY: Moves the frog left one space. \n" +
            "RIGHT ARROW KEY: Moves the frog right one space. \n" +
            "DOWN ARROW KEY: Moves the frog down one space." +
            "UP ARROW: Moves the frog up one space. \n \n \n" +
            "How To Play- \n" +
            "Get the frog from the starting position to a lily pad" +
            "without getting hit by a vehicle or jumping into the water";

    private final String RULES_2048 = "Controls- \n \n" +
            "LEFT ARROW KEY: Slides all Tiles to the left. \n" +
            "RIGHT ARROW KEY: Slides all Tiles to the right. \n" +
            "DOWN ARROW KEY: Slides all Tiles down. \n"+
            "UP ARROW KEY: Slides all Tiles up. \n \n"+
            "How To Play- \n \n"+
            "Combine tiles with the same numeric values to reach 2048. \n"+
            "After reaching the Tile 2048, you may choose to continue to reach 8192.";

    /**
     *The default constructor for the utility bar used in the application
     *Provides a file option and a help option
     */
    public UtilityBar(){
        super();
        file = new Menu("File");
        help = new Menu("Help");
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

    /**
     *Method that creates a tab pane to display the controls when selected
     *from the help menu
     */
    private void controlScreen(){
        Button close1 = new Button("Close");
        Button close2 = new Button("Close");
        VBox pageFrogger = new VBox();
        pageFrogger.getChildren().addAll(new Text(RULES_FROGGER), close1);
        VBox page2048 = new VBox();
        page2048.getChildren().addAll(new Text(RULES_2048), close2);
        
        TabPane pane = new TabPane();
        Tab tabFrogger = new Tab("Frogger Rules",pageFrogger);
        Tab tab2048 = new Tab("2048 Rules", page2048);

        pane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
        pane.getTabs().addAll(tabFrogger, tab2048);
   
        //Create Stage and Scene
        Scene scene = new Scene(pane);
        Stage helpWindow = new Stage();
        helpWindow.setScene(scene);

        close1.setOnAction(e -> helpWindow.close());
        close2.setOnAction(e -> helpWindow.close());
        
        helpWindow.initModality(Modality.APPLICATION_MODAL);
        helpWindow.setTitle("How To Play");
        helpWindow.setMinWidth(300);
        helpWindow.setMinHeight(400);
        helpWindow.show();
    } // controlScreen()
    
}//UtilityBar
