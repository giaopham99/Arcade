package cs1302.arcade;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;//
import javafx.scene.layout.StackPane;
import javafx.scene.Scene;
import javafx.geometry.Pos;

public class MainMenu extends VBox{

    //Image previewTetris;
    //Image preview2048;
    ImageView viewTetris;
    ImageView view2048;
    ImageView background;
    HBox imageBox;
    
    StackPane stack;
    //special toolbar

    public MainMenu(){
   
        imageBox=new HBox();
        stack=new StackPane();
        viewTetris=new ImageView(new Image("MenuImages/TetrisCover.jpeg"));
        view2048=new ImageView(new Image("MenuImages/2048Cover.png"));
        background=new ImageView(new Image("MenuImages/Background.jpg"));
        imageBox.getChildren().addAll(viewTetris, view2048);
        stack.setAlignment(imageBox, Pos.BOTTOM_CENTER);
        stack.getChildren().addAll(background, imageBox);
        this.getChildren().addAll(new UtilityBar(), imageBox);
    }//MainMenu
    
}//MainMenu
