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
    //Timeline timelineFast;
    //Timeline timelineSlow;
    //KeyFrame kfSlow;
    //KeyFrame kfFast;
    
    StackPane stack;

    public MainMenu(){
        imagePane=new FlowPane(Orientation.HORIZONTAL);
        stack=new StackPane();
        
        //Frogger Image
        viewFrogger=new ImageView(new Image("MenuImages/FroggerCover.jpg"));
        viewFrogger.setFitWidth(220);
        viewFrogger.setFitHeight(220);
        buttonFrogger=new Button("",viewFrogger);
        buttonFrogger.setOnAction(e-> {
                AppFrogger appFrog=new AppFrogger();
                Scene scene = new Scene(appFrog);
                Thread t = new Thread(()->{
                        scene.setOnKeyPressed(a->{
                                switch(a.getCode()){
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
                });
                t.setDaemon(true);
                t.start();
                Stage stage = new Stage();
                stage.setTitle("Frogger!");
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setResizable(false);
                stage.sizeToScene();
                stage.setMaxHeight(720);
                stage.setMaxWidth(1280);
                stage.setOnCloseRequest(y-> {
                        appFrog.stopTL();

                    });
                stage.show();
            });//setOnAction
        
        
        //2048 Image
        view2048=new ImageView(new Image("MenuImages/2048Cover.png"));
        button2048 = new Button("",view2048);
        button2048.setOnAction(e-> {
                App2048 app2048 = new App2048();
                Scene scene = new Scene(app2048);
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
                Stage stage = new Stage();
                stage.setTitle("2048!");
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setResizable(false);
                stage.sizeToScene();
                stage.show();
            });
        
        //Background
        background=new ImageView(new Image("MenuImages/Background.jpg"));

        //Layout
        imagePane.setHgap(100);
        imagePane.setAlignment(Pos.BOTTOM_CENTER);
        imagePane.getChildren().addAll(buttonFrogger, button2048);
        stack.getChildren().addAll(background, imagePane);

        this.getChildren().addAll(new UtilityBar(), stack);
    }//MainMenu

}//MainMenu
