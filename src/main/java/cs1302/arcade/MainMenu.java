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

public class MainMenu extends VBox{

    ImageView viewFrogger;
    ImageView view2048;
    Button button2048;
    Button buttonFrogger;
    ImageView background;
    FlowPane imagePane;
    StackPane stack;

    public MainMenu(){
        imagePane=new FlowPane(Orientation.HORIZONTAL);
        stack=new StackPane();
        setFrogAnd2048();
        //Frogger Button
        buttonFrogger.setOnAction(e-> {
                AppFrogger appFrog=new AppFrogger();
                Scene scene = new Scene(appFrog);
                Thread t = new Thread(()->controlFrogger(scene,appFrog));
                t.setDaemon(true);
                t.start();
                Stage stage = new Stage();
                makeStage(stage,scene,"Frogger!");
                stage.setOnCloseRequest(y->appFrog.stopTL());
                stage.show();
            });//setOnAction
        
        //2048 Button
        button2048.setOnAction(e-> {
                App2048 app2048 = new App2048();
                Scene scene = new Scene(app2048);
                control2048(scene, app2048);
                Stage stage = new Stage();
                makeStage(stage,scene,"2048!");
            });
        
        //Background
        background=new ImageView(new Image("MenuImages/Background.jpg"));
        setUpLayout();
        this.getChildren().addAll(new UtilityBar(), stack);
    }//MainMenu

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

    private void setFrogAnd2048(){
        viewFrogger=new ImageView(new Image("MenuImages/FroggerCover.jpg"));
        viewFrogger.setFitWidth(220);
        viewFrogger.setFitHeight(220);
        buttonFrogger=new Button("",viewFrogger);
        view2048=new ImageView(new Image("MenuImages/2048Cover.png"));
        button2048 = new Button("",view2048);
        
    }//setFrogAnd2048
    
    private void setUpLayout(){
        imagePane.setHgap(100);
        imagePane.setAlignment(Pos.BOTTOM_CENTER);
        imagePane.getChildren().addAll(buttonFrogger, button2048);
        stack.getChildren().addAll(background, imagePane);
        
    }//setUpLayout
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
