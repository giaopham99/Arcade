package cs1302.arcade;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;//
import javafx.scene.layout.StackPane;

public class MainMenu extends Scene{

    //Image previewTetris;
    //Image preview2048;
    ImageView viewTetris;
    ImageView view2048;
    ImageView background;
    HBox imageBox;
    VBox mainLayout;
    StackPane stack;
    //special toolbar

    public MainMenu(){
        mainLayout=new VBox();
        imageBox=new HBox();
        stack=new StackPane();
        viewTetris=new ImageView(new Image());
        view2048=new ImageView(new Image());
        background=new ImageView(new Image());
        imageBox.getChildren().addAll(viewTetris, view2048);
        stack.setAlignment(imageBox, Pos.BOTTOM);
        stack.getChildren().addAll(background, imageBox);
        mainLayout.getChildren().addAll();
    }//MainMenu
    
}//MainMenu
