package cs1302.arcade;

import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.util.Duration;
import javafx.application.Platform;
public class AppFrogger extends StackPane{
     
    Player p1;
    int level=1;

    FroggerItems frog = new FroggerItems("frog",-40,360,true);
    FroggerItems log1 = new FroggerItems("log", -280, -120, true);
    FroggerItems log2 = new FroggerItems("log",100, -120, true);
    FroggerItems truck1 = new FroggerItems("truck", -200, 200, true);
    FroggerItems truck2 = new FroggerItems("truck", -200, 200, true);
    FroggerItems carBlue = new FroggerItems("cb", -100, 100, false);
    FroggerItems carYellow = new FroggerItems("cy", 200, 200, false);
    FroggerItems carGreen = new FroggerItems("cg",200, 200, true);
    FroggerItems fly = new FroggerItems("fly", 0, 0, true);

    Timeline slowTL;
    Timeline fastTL;
    
    public AppFrogger(){
        super();
        FroggerLevels levelGen=new FroggerLevels();
        frog.rotateImg(180);
        this.getChildren().add(levelGen.getLevel());

        Thread t = new Thread(()->{
                slowTL = setUpSlowItems1(log1,log2);
                fastTL = setUpFastItems1(truck1);
                
        });
        t.setDaemon(true);
        t.start();
        //logTL.stop();
        //setUpSlowItems(truck1);
        
        Platform.runLater(()-> this.getChildren().add(frog.getImg()));
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

    private Timeline setUpSlowItems1(FroggerItems log1, FroggerItems log2){
        this.getChildren().addAll(log1.getImg(), log2.getImg());
        
        EventHandler<ActionEvent> handler = event -> {
            makeHandler(log1);
            makeHandler(log2);
        };
        
        KeyFrame kf = new KeyFrame(Duration.seconds(.1), handler);
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(kf);
        timeline.play();

        return timeline;
    }//setUpSlowItems1

    
    private Timeline setUpFastItems1(FroggerItems truck){
        this.getChildren().add(truck.getImg());
        
        EventHandler<ActionEvent> handler = event -> {
            makeHandler(truck);
        };
        
        KeyFrame kf = new KeyFrame(Duration.seconds(.05), handler);
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(kf);
        timeline.play();

        return timeline;
    }//setUpFastItems1
    
    private void makeHandler(FroggerItems item){
            if (item.getX()==320){
                item.setX(-280);
            }//if
            else {
                item.addX(5);
            }//else
    }//makeHandler

    public void stopTL(){
        fastTL.stop();
        slowTL.stop();
    }//stopTL
    
}//AppFrogger
