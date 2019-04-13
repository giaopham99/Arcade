package cs1302.arcade;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;//
import javafx.scene.layout.StackPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.Scene;
import javafx.geometry.Pos;
import javafx.geometry.Orientation;

public class MainMenu extends VBox{

    //Image previewTetris;
    //Image preview2048;
    ImageView viewTetris;
    ImageView view2048;
    ImageView background;
    FlowPane imagePane;
    
    StackPane stack;
    //special toolbar

    public MainMenu(){
   
        imagePane=new FlowPane(Orientation.HORIZONTAL);
        stack=new StackPane();
        viewTetris=new ImageView(new Image("MenuImages/TetrisCover.jpeg"));
        viewTetris.setFitWidth(220);
        viewTetris.setFitHeight(220);
        view2048=new ImageView(new Image("MenuImages/2048Cover.png"));
        background=new ImageView(new Image("MenuImages/Background.jpg"));
        imagePane.setHgap(100);
        imagePane.setAlignment(Pos.BOTTOM_CENTER);
        imagePane.getChildren().addAll(viewTetris, view2048);
        
        stack.getChildren().addAll(background, imagePane);

        this.getChildren().addAll(new UtilityBar(), stack);
    }//MainMenu
    
}//MainMenu
