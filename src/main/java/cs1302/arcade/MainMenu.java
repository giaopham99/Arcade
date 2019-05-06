package cs1302.arcade;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.stage.Modality;
import javafx.geometry.Orientation;
import javafx.application.Platform;

/**
 *This class represents the main menu of the arcade. This is where
 *the game selection screen is displayed and the first thing that 
 *is seen by the user when the {@code ArcadeApp} is started 
 */
public class MainMenu extends VBox{

    ImageView viewFrogger;
    ImageView view2048;
    Button button2048;
    Button buttonFrogger;
    ImageView background;
    FlowPane imagePane;
    StackPane stack;

    /**
     *The default constructor for the main menu. It displays a
     *2 images buttons that launch the games when pressed and
     *a background
     */
    public MainMenu(){
        imagePane=new FlowPane(Orientation.HORIZONTAL);
        stack=new StackPane();
        setFrogAnd2048();
        //Frogger Button
        buttonFrogger.setOnAction(e->buildRulesFrog());
        
        //2048 Button
        button2048.setOnAction(e->buildRules2048());
        
        //Background
        background=new ImageView(new Image("MenuImages/Background.jpg"));
        setUpLayout();
        this.getChildren().addAll(new UtilityBar(), stack);
    }//MainMenu

    /**
     *Helper method to display the rules for 2048 when the game
     *is selected. When the user closes this window, the game 
     *will begin 
     */
    private void buildRules2048(){
        VBox rules = new VBox();
        rules.getChildren().add(new Text(UtilityBar.RULES_2048));
        Scene rulesScene = new Scene(rules);
        Stage rulesStage = new Stage();
        rulesStage.setScene(rulesScene);
        rulesStage.setOnCloseRequest(e->{
                App2048 app2048 = new App2048();
                Scene scene = new Scene(app2048);
                control2048(scene, app2048);
                Stage stage = new Stage();
                makeStage(stage,scene,"2048!");
            });
        rulesStage.sizeToScene();
        rulesStage.setResizable(false);
        rulesStage.initModality(Modality.APPLICATION_MODAL);
        rulesStage.show();
    }//buildRules2048

    /**
     *Helper method to display the rules for Frogger when the game
     *is selected. When the user closes this window, the game 
     *will begin
     */
    private void buildRulesFrog(){
        VBox rules = new VBox();
        rules.getChildren().add(new Text(UtilityBar.RULES_FROGGER));
        Scene rulesScene = new Scene(rules);
        Stage rulesStage = new Stage();
        rulesStage.setScene(rulesScene);
        rulesStage.setOnCloseRequest(e->{
                AppFrogger appFrog=new AppFrogger();
                Scene scene = new Scene(appFrog);
                Thread t = new Thread(()->controlFrogger(scene,appFrog));
                t.setDaemon(true);
                t.start();
                Stage stage = new Stage();
                makeStage(stage,scene,"Frogger!");
                stage.setOnCloseRequest(y->appFrog.stopTL());
                stage.show();
            });
        rulesStage.sizeToScene();
        rulesStage.setResizable(false);
        rulesStage.initModality(Modality.APPLICATION_MODAL);
        rulesStage.show();
    }//buildRulesFrog

    /**
     *Helper method to set up the controls for 2048. Each
     *movement calls a method from the {@code App2048} class
     *
     *@param scene, the scene for 2048
     *@param app2048, a game of 2048
     */
    private void control2048(Scene scene, App2048 app2048){
        scene.setOnKeyPressed(x-> {
                switch(x.getCode()) {
                case UP:
                    app2048.slideUp();
                    break;
                case DOWN:
                    app2048.slideDown();
                    break;
                case LEFT:
                    app2048.slideLeft();
                    break;
                case RIGHT:
                    app2048.slideRight();
                    break;
                }
            });
    }//control2048

    /**
     *Helper method to set up the controls for Frogger. Each
     *movement calls a method from the {@code AppFrogger} class
     *
     *@param scene, the scene for Frogger
     *@param app2048, a game of Frogger
     */
    private void controlFrogger(Scene scene, AppFrogger appFrog){
        scene.setOnKeyPressed(x->{
                switch(x.getCode()){
                case UP: appFrog.moveUp();
                    break;
                case DOWN: appFrog.moveDown();
                    break;
                case LEFT: appFrog.moveLeft();
                    break;
                case RIGHT: appFrog.moveRight();
                    break;
                };
            });
    }//controlFrogger

    /**
     *Helper method to add the game pictures to the buttons on the main menu
     */
    private void setFrogAnd2048(){
        viewFrogger=new ImageView(new Image("MenuImages/FroggerCover.jpg"));
        viewFrogger.setFitWidth(220);
        viewFrogger.setFitHeight(220);
        buttonFrogger=new Button("",viewFrogger);
        view2048=new ImageView(new Image("MenuImages/2048Cover.png"));
        button2048 = new Button("",view2048);
        
    }//setFrogAnd2048

    /**
     *Helper method to adjust the positioning of children so that
     *the layout of the main menu is correct 
     */
    private void setUpLayout(){
        imagePane.setHgap(100);
        imagePane.setAlignment(Pos.BOTTOM_CENTER);
        imagePane.getChildren().addAll(buttonFrogger, button2048);
        stack.getChildren().addAll(background, imagePane);
        
    }//setUpLayout

    /**
     *Helper method to make the stage for the main menu and  display it
     */
    private void makeStage(Stage stage, Scene scene, String title){
        stage.setTitle(title);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.sizeToScene();
        stage.setMaxHeight(720);
        stage.setMaxWidth(1000);
        stage.show();
    }//makeStage
    
}//MainMenu
