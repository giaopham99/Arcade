package cs1302.arcade;

import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.util.Duration;
import javafx.application.Platform;
import java.awt.Rectangle;

public class AppFrogger extends StackPane{
     
    Player p1;
    int level=1;
    VBox container;
    Text levelName;
    Text lvlNum;
    Text score;
    Text scoreNum;
    
    FroggerItems frog = new FroggerItems("frog",-40,360,60,60,true);
    FroggerItems log1 = new FroggerItems("log", -280,-120,120,60,true);
    FroggerItems log2 = new FroggerItems("log",100,-120,120,60,true);
    FroggerItems truck1 = new FroggerItems("truck", -200,200,150,71,true);
    FroggerItems truck2 = new FroggerItems("truck", -200,280,150,71,true);
    FroggerItems carBlue = new FroggerItems("cb", -100,40,120,71,false);
    FroggerItems carYellow = new FroggerItems("cy", 200,40,120,71,false);
    FroggerItems carGreen = new FroggerItems("cg",200,280,120,71,true);
    FroggerItems fly = new FroggerItems("fly",0,0,63,60,true);

    FroggerLevels levelGen;
    Timeline slowTL;
    Timeline fastTL;
    
    public AppFrogger(){
        super();
        container = new VBox();
        levelName = new Text("Level: ");
        lvlNum = new Text("1");
        score = new Text("Score: ");
        //scoreNum = new Text(Integer.toString(p1.getScore()));
        levelGen=new FroggerLevels();
        frog.rotateImg(180);
        this.getChildren().add(levelGen.getLevel());

        Thread slowThread = new Thread(()->{
                slowTL = setUpSlowItems1(log1,log2);
        });
        
        Thread fastThread = new Thread(()->{
                fastTL = setUpFastItems1(truck1);
        });
        slowThread.setDaemon(true);
        slowThread.start();
        
        fastThread.setDaemon(true);
        fastThread.start();
        
        Platform.runLater(()-> this.getChildren().add(frog.getImg()));
        //container.getChildren().addAll(levelName, this);
    }//AppFrogger

    private void displayWin(){
        VBox root=new VBox();
        HBox buttons=new HBox();
        Text winMessage=new Text("Congratulations! You won!");
        Button playAgain=new Button("Play Again");

        buttons.getChildren().addAll(playAgain);
        root.getChildren().addAll(winMessage, buttons);
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Winner!");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.sizeToScene();
        stage.setResizable(false);
        stage.show();

        playAgain.setOnAction(e->{
                resetGame();
                stage.close();
            });
    }//displayWin

    private void displayLoss(){
        VBox root=new VBox();
        HBox buttons=new HBox();
        Text lossMessage=new Text("You died! RIP :(");
        Button playAgain=new Button("Play Again?");

        buttons.getChildren().addAll(playAgain);
        root.getChildren().addAll(lossMessage, buttons);
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Game Over!");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.sizeToScene();
        stage.setResizable(false);
        stage.show();

        playAgain.setOnAction(e->{
                resetGame();
                stage.close();
            });
    }//displayLoss

    public void resetGame(){
        levelGen.genLevel1();
        frog.setXY(-40,360);
        frog.rotateImg(180);
        if(level==2){
            Platform.runLater(()-> {
                    this.getChildren().remove(carBlue.getImg());
                    this.getChildren().remove(carYellow.getImg());
                });
        }//if
        else if(level==3){
            Platform.runLater(()-> {
                    log1.setY(-120);
                    log2.setY(-120);
                    this.getChildren().remove(carBlue.getImg());
                    this.getChildren().remove(carYellow.getImg());
                    this.getChildren().remove(carGreen.getImg());
                    this.getChildren().remove(truck2.getImg());
                });
        }//else if
        else{
            slowTL.play();
            fastTL.play();
        }//else
        level = 1;
    }//resetGame

    private void snapFrog(){
        if(frog.getX()>-200 && frog.getX()<-120){
            frog.setX(-200);
        }//if
        else if(frog.getX()<-40){
            frog.setX(-120);
        }//else if
        else if(frog.getX()<40){
            frog.setX(-40);
        }//else if
        else if(frog.getX()<120){
            frog.setX(40);
        }//else if
        else if(frog.getX()<200){
            frog.setX(120);
        }//else if
        else{
            frog.setX(200);
        }//else
    }//snapFrog
    
    public void moveUp(){
        if (frog.getY()!=-360){
            frog.addY(-80);
            frog.rotateImg(180);
            frog.getRect().setLocation(frog.getX(),frog.getY());
            if(level==1){
                checkLevel1();
            }//if
            else if(level==2){
                checkLevel2();
            }//else if
            else if(level==3){
                checkLevel3();
            }//else
            System.out.println(frog.getY());
        }//if
        else {
            System.out.println("Can't go that way");
        }//else
        snapFrog();
    }//moveUp

    public void moveDown(){
        if (frog.getY()!=360){
            frog.addY(80);
            frog.rotateImg(0);
            frog.getRect().setLocation(frog.getX(),frog.getY());
            if(level==1){
                checkLevel1();
            }//if
            else if(level==2){
                checkLevel2();
            }//else if
            else if(level==3){
                checkLevel3();
            }//else
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
            frog.getRect().setLocation(frog.getX(),frog.getY());
            if(level==1){
                checkLevel1();
            }//if
            else if(level==2){
                checkLevel2();
            }//else if
            else if(level==3){
                checkLevel3();
            }//else
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
            frog.getRect().setLocation(frog.getX(),frog.getY());
            if(level==1){
                checkLevel1();
            }//if
            else if(level==2){
                checkLevel2();
            }//else if
            else if(level==3){
                checkLevel3();
            }//else
            System.out.println(frog.getX());
        }//if
        else {
            System.out.println("Can't go that way");
        }//else
    }//moveRight

    private void checkLevel1(){
        if(frog.getY()== -360){
            if(frog.getX()== -120
               || frog.getX()== 40
               || frog.getX()== 200){
                levelGen.genLevel2();
                slowTL = setUpSlowItems2(log1,log2,truck1);
                fastTL = setUpFastItems2(carBlue,carYellow);
                frog.setX(-40);
                frog.setY(360);
                level++;
            }//if
            else{
                displayLoss();
            }//else
        }//if
        else if(frog.getY()==-120 && !collisionCheckLogs(log1,log2)){
            stopTL();
            displayLoss();
        }//else if
    }//checkLevel1

    private void checkLevel2(){
        if(frog.getY()== -360){
            if(frog.getX()== -120
               || frog.getX()== 120){
                levelGen.genLevel3();
                slowTL = setUpSlowItems3(carBlue,carYellow,carGreen,truck2);
                fastTL = setUpFastItems3(log1,log2,truck1);
                frog.setX(-40);
                frog.setY(360);
                level++;
            }//if
            else{
                displayLoss();
            }//else
        }//if
        else if(frog.getY()==-120 && !collisionCheckLogs(log1,log2)){
            stopTL();
            displayLoss();
        }//else if
    }//checkLevel2

    private void checkLevel3(){
        if(frog.getY()== -360){
            if(frog.getX()== -40){
                displayWin();
            }//if
            else{
                displayLoss();
            }//else
        }//if
        else if (frog.getY()==-200 || frog.getY()==-40){
            stopTL();
            displayLoss();
        }//else if
    }//checkLevel3
    
    private Timeline setUpSlowItems1(FroggerItems log1, FroggerItems log2){
        this.getChildren().addAll(log1.getImg(), log2.getImg());
        
        EventHandler<ActionEvent> handler = event -> {
            makeHandler(log1);
            makeHandler(log2);
            collisionCheckLogs(log1,log2);
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
            collisionCheckVeh(truck);
        };
        
        KeyFrame kf = new KeyFrame(Duration.seconds(.05), handler);
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(kf);
        timeline.play();

        return timeline;
    }//setUpFastItems1

    private Timeline setUpSlowItems2(FroggerItems log1, FroggerItems log2, FroggerItems truck){
        EventHandler<ActionEvent> handler = event -> {
            makeHandler(log1);
            makeHandler(log2);
            makeHandler(truck);
            collisionCheckLogs(log1,log2);
            collisionCheckVeh(truck);
        };
        
        KeyFrame kf = new KeyFrame(Duration.seconds(.1), handler);
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(kf);
        timeline.play();

        return timeline;
    }//setUpSlowItems2

    private Timeline setUpFastItems2(FroggerItems cb, FroggerItems cy){
        this.getChildren().addAll(cb.getImg(), cy.getImg());
        
        EventHandler<ActionEvent> handler = event -> {
            makeHandler(cb);
            makeHandler(cy);
            collisionCheckVeh(cb,cy);
        };
        
        KeyFrame kf = new KeyFrame(Duration.seconds(.05), handler);
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(kf);
        timeline.play();

        return timeline;
    }//setUpFastItems2

    private Timeline setUpSlowItems3(FroggerItems cb, FroggerItems cy,
                                     FroggerItems cg, FroggerItems truck){
        this.getChildren().addAll(cg.getImg(), truck.getImg());

        EventHandler<ActionEvent> handler = event -> {
            makeHandler(cb);
            makeHandler(cy);
            makeHandler(cg);
            makeHandler(truck);
            collisionCheckVeh(cb,cy,cg,truck);
        };
        
        KeyFrame kf = new KeyFrame(Duration.seconds(.1), handler);
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(kf);
        timeline.play();

        return timeline;
    }//setUpSlowItems3

    private Timeline setUpFastItems3(FroggerItems log1, FroggerItems log2, FroggerItems truck){
        log1.setY(-200);
        log2.setY(-40);
        EventHandler<ActionEvent> handler = event -> {
            makeHandler(log1);
            makeHandler(log2);
            makeHandler(truck);
            collisionCheckLogs(log1,log2);
            collisionCheckVeh(truck);
        };
        
        KeyFrame kf = new KeyFrame(Duration.seconds(.05), handler);
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(kf);
        timeline.play();

        return timeline;
    }//setUpFastItems3
    
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

    private boolean collisionCheckLogs(FroggerItems...logs){
        for(FroggerItems l:logs){
            if(frog.getRect().intersects(l.getRect())){
                frog.addX(5);
                if (frog.getX()==225){
                    stopTL();
                    displayLoss();
                }//if
                return true;
            }//if
        }//for
        return false;
    }//collisionCheckLogs

    private boolean collisionCheckVeh(FroggerItems...vehicles){
        for(FroggerItems v:vehicles){
            if(frog.getRect().intersects(v.getRect())){
                stopTL();
                displayLoss();
            }//if
        }//for
        return true;
    }//collisionCheckVeh
}//AppFrogger
