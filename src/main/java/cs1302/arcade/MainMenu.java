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

public class MainMenu extends VBox{

    //Image previewTetris;
    //Image preview2048;
    ImageView viewTetris;
    ImageView view2048;
    Button button2048;
    ImageView background;
    FlowPane imagePane;
    
    StackPane stack;

    public MainMenu(){
        imagePane=new FlowPane(Orientation.HORIZONTAL);
        stack=new StackPane();
        
        //Tetris Image
        viewTetris=new ImageView(new Image("MenuImages/TetrisCover.jpeg"));
        viewTetris.setFitWidth(220);
        viewTetris.setFitHeight(220);
        
        //2048 Image
        view2048=new ImageView(new Image("MenuImages/2048Cover.png"));
        button2048 = new Button("",view2048);
        button2048.setOnAction(e-> {
                App2048 app2048 = new App2048();
                Scene scene = new Scene(app2048);
                  scene.setOnKeyPressed(
                      x-> {
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
                      }
                      );
                Stage stage = new Stage();
                stage.setTitle("2048!");
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.sizeToScene();
                stage.show();
            });
        
        //Background
        background=new ImageView(new Image("MenuImages/Background.jpg"));

        //Layout
        imagePane.setHgap(100);
        imagePane.setAlignment(Pos.BOTTOM_CENTER);
        imagePane.getChildren().addAll(viewTetris, button2048);
        stack.getChildren().addAll(background, imagePane);

        this.getChildren().addAll(new UtilityBar(), stack);
    }//MainMenu

}//MainMenu
