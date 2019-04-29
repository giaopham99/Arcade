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
    FroggerItems frog = new FroggerItems("frog",-40,360,true);
    FroggerItems log1 = new FroggerItems("log", -280, -120, true);
    
    public AppFrogger(){
        super();
        FroggerLevels levelGen=new FroggerLevels();
        frog.rotateImg(180);
        this.getChildren().add(levelGen.getLevel());
        this.getChildren().add(frog.getImg());
        setUpSlowLog(log1);

        /*
        EventHandler<ActionEvent> handler = event -> log1.addX(5);
        KeyFrame kf = new KeyFrame(Duration.seconds(1/100), handler);
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(kf);
        timeline.play();
        */
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

    private void setUpSlowLog(FroggerItems log){
        this.getChildren().add(log.getImg());

        EventHandler<ActionEvent> handler = event -> {
            if (log.getX()==320){
                log.setX(-280);
            }//if
            else {
                log.addX(5);
                System.out.println(log.getX());
            }//else
        };
        KeyFrame kf = new KeyFrame(Duration.seconds(.1), handler);
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(kf);
        timeline.play();

    }//setUpHazards
    
}//AppFrogger
