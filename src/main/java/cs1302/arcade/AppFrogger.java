package cs1302.arcade;

import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.util.Duration;

public class AppFrogger extends StackPane{
     
    Player p1;
    int level=1;

    FroggerItems frog = new FroggerItems("frog",-40,360,true);
    FroggerItems log1 = new FroggerItems("log", -280, -120, true);
    FroggerItems truck1 = new FroggerItems("truck", -200, 200, true);
    FroggerItems carBlue = new FroggerItems("cb", -100, 100, false);
    FroggerItems carYellow = new FroggerItems("cy", 200, 200, false);
    FroggerItems fly = new FroggerItems("fly", 0, 0, true);
    
    public AppFrogger(){
        super();
        FroggerLevels levelGen=new FroggerLevels();
        frog.rotateImg(180);
        this.getChildren().add(levelGen.getLevel());
        
        Timeline logTL = setUpSlowItems(log1);
        logTL.stop();
        //setUpSlowItems(truck1);


        this.getChildren().add(frog.getImg());
    }//AppFrogger

    public void moveUp(){
        if (frog.getY()!=-360){
            frog.addY(-80);
            frog.rotateImg(180);
            System.out.println(frog.getY());
        }//if
        else {
            System.out.println("Can't go that way");
        }//else
    }//moveUp

    public void moveDown(){
        if (frog.getY()!=360){
            frog.addY(80);
            frog.rotateImg(0);
            System.out.println(frog.getY());
        }//if
        else {
            System.out.println("Can't go that way");
        }//else
    }//moveDown

    public void moveLeft(){
        if (frog.getX()!=-200){
            frog.addX(-80);
            frog.rotateImg(90);
            System.out.println(frog.getX());
        }//if
        else {
            System.out.println("CAn't go that way");
        }//else
    }//moveLeft

    public void moveRight(){
        if (frog.getX()!=200){
            frog.addX(80);
            frog.rotateImg(270);
            System.out.println(frog.getX());
        }//if
        else {
            System.out.println("Can't go that way");
        }//else
    }//moveRight

    private Timeline setUpSlowItems(FroggerItems item){
        this.getChildren().add(item.getImg());
        
        EventHandler<ActionEvent> handler = event -> {
            if (item.getX()==320){
                item.setX(-280);
            }//if
            else {
                item.addX(5);
                System.out.println(item.getX());
            }//else
        };
        
        KeyFrame kf = new KeyFrame(Duration.seconds(.1), handler);
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(kf);
        timeline.play();

        return timeline;
    }//setUpSlowItems
    
}//AppFrogger
