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

/**
 *This class represents a game of Frogger. It uses several other classes
 *and their functionalities to set up and play a game of frogger.
 */
public class AppFrogger extends VBox{
     
    Player p1;
    int level=1;
    
    FroggerItems frog = new FroggerItems("frog",-40,360,60,60);
    FroggerItems log1 = new FroggerItems("log", -280,-120,120,60);
    FroggerItems log2 = new FroggerItems("log",100,-120,120,60);
    FroggerItems truck1 = new FroggerItems("truck", -200,200,150,71);
    FroggerItems truck2 = new FroggerItems("truck", -200,280,150,71);
    FroggerItems carBlue = new FroggerItems("cb", -100,40,120,71);
    FroggerItems carYellow = new FroggerItems("cy", 200,40,120,71);
    FroggerItems carGreen = new FroggerItems("cg",200,280,120,71);
    
    StackPane gameArea;
    FroggerLevels levelGen;
    Timeline slowTL;
    Timeline fastTL;
    Thread slowThread;
    Thread fastThread;
    HBox scoreBoard;
    Text score;
    Text scoreNum;
    Text levelTxt;
    Text levelNum;

    /**
     *Default constructor for {@code AppFrogger}. It sets up
     *a game of frogger starting with level 1. 
     */
    public AppFrogger(){
        super();
        p1 = new Player();
        gameArea = new StackPane();
        levelGen=new FroggerLevels();
        frog.rotateImg(180);
        gameArea.getChildren().add(levelGen.getLevel());
        setUpScoreBoard();
        slowThread = new Thread(()->{
                slowTL = setUpSlowItems1(log1,log2);
                
                });
        
        fastThread = new Thread(()->{
                fastTL = setUpFastItems1(truck1);
                      });
        slowThread.setDaemon(true);
        slowThread.start();
        
        fastThread.setDaemon(true);
        fastThread.start();
        
        Platform.runLater(()-> gameArea.getChildren().add(frog.getImg()));
    }//AppFrogger

    /**
     * Private method that sets up the ScoreBoard with the score, level, and game area.
     */
    private void setUpScoreBoard(){
        scoreBoard = new HBox(10);
        score = new Text("Score: ");
        scoreNum = new Text(Integer.toString(p1.getScore()));
        levelTxt = new Text("Level: ");
        levelNum = new Text(Integer.toString(level));
        scoreBoard.getChildren().addAll(levelTxt,levelNum,score,scoreNum);
        this.getChildren().addAll(scoreBoard,gameArea);
    }//setUpScoreBoard
    
    /**
     *Sets up a window to display when the player has won
     */
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

    /**
     *Sets up a window to display when the player has lost
     */
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

    /**
     *Helper method to restart the game if a player wishes to play again.
     *Resets differently depending on what level it was when a restart was needed.  
     */
    private void resetGame(){
        levelGen.genLevel1();
        frog.setXY(-40,360);
        frog.rotateImg(180);
        if(level==2){
            Platform.runLater(()-> {
                    gameArea.getChildren().remove(carBlue.getImg());
                    gameArea.getChildren().remove(carYellow.getImg());
                });
        }//if
        else if(level==3){
            Platform.runLater(()-> {
                    log1.setY(-120);
                    log2.setY(-120);
                    gameArea.getChildren().remove(carBlue.getImg());
                    gameArea.getChildren().remove(carYellow.getImg());
                    gameArea.getChildren().remove(carGreen.getImg());
                    gameArea.getChildren().remove(truck2.getImg());
                });
        }//else if
        else{
            slowTL.play();
            fastTL.play();
        }//else
        level = 1;
        p1.setScore(0);
        scoreNum.setText(Integer.toString(p1.getScore()));
        levelNum.setText(Integer.toString(level));
    }//resetGame

    /**
     *Helper method to snap the frog to the middle of a tile when 
     *coming off of a log  
     */
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

    /**
     *Method that controls the movement of the frog
     *when moving up
     */
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
        p1.addScore(10);
        scoreNum.setText(Integer.toString(p1.getScore()));
    }//moveUp

    /**
     *Method that controls the movement of the frog
     *when moving down
     */
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
        snapFrog();
        p1.addScore(10);
        scoreNum.setText(Integer.toString(p1.getScore()));
    }//moveDown

    /**
     *Method that controls the movement of the frog
     *when moving left
     */
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
        p1.addScore(10);
        scoreNum.setText(Integer.toString(p1.getScore()));
    }//moveLeft

    /**
     *Method that controls the movement of the frog
     *when moving right
     */
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
        p1.addScore(10);
        scoreNum.setText(Integer.toString(p1.getScore()));
    }//moveRight

    /**
     *Method to check whether or not the player has won or lost level 1
     *Checks if the frog is on a lily pad, or if it has jumped into
     *the water without being on a log 
     */
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
                levelNum.setText(Integer.toString(level));
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

    /**
     *Method to check whether or not the player has won or lost level 2
     *Checks if the frog is on a lily pad, or if it has jumped into
     *the water without being on a log
     */
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
                levelNum.setText(Integer.toString(level));
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

    /**
     *Method to check whether or not the player has won or lost level 3
     *Checks if the frog is on a lily pad, or if it has jumped into
     *the water without being on a log
     */
    private void checkLevel3(){
        if(frog.getY()== -360){
            if(frog.getX()== -40){
                displayWin();
            }//if
            else{
                displayLoss();
            }//else
        }//if
        else if ((frog.getY()==-200 || frog.getY()==-40) && !collisionCheckLogs(log1,log2) ){
            stopTL();
            displayLoss();
        }//else if
    }//checkLevel3

    /**
     *Sets up the animations for slow items on level 1
     *
     *@param log1, a log from level 1
     *@param log2, a log from level 1
     *@return timeline, the {@code Timeline} for the slow item animation  
     */
    private Timeline setUpSlowItems1(FroggerItems log1, FroggerItems log2){
        gameArea.getChildren().addAll(log1.getImg(), log2.getImg());
        
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

    /**
     *Sets up the animations for fast items on level 1
     *
     *@param truck, a truck from level 1
     *@return timeline, the {@code Timeline} for the fast item animation
     */
    private Timeline setUpFastItems1(FroggerItems truck){
        gameArea.getChildren().add(truck.getImg());
        
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

    /**
     *Sets up the animations for slow items on level 2
     *
     *@param log1, a log from level 2
     *@param log2, a log from level 2
     *@param truck, a truck from level 2
     *@return timeline, the {@code Timeline} for the slow item animation
     */
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

    /**
     *Sets up the animations for fast items on level 2
     *
     *@param cb, the blue car from level 2
     *@param cy, the yellow car from level 2
     *@return timeline, the {@code Timeline} for the fast item animation
     */
    private Timeline setUpFastItems2(FroggerItems cb, FroggerItems cy){
        gameArea.getChildren().addAll(cb.getImg(), cy.getImg());
        
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

    /**
     *Sets up the animations for slow items on level 3
     *
     *@param cb, the blue car from level 3
     *@param cy, the yellow car from level 3
     *@param cg, the green car from level 3
     *@param truck, a truck from level 3
     *@return timeline, the {@code Timeline} for the slow item animation
     */
    private Timeline setUpSlowItems3(FroggerItems cb, FroggerItems cy,
                                     FroggerItems cg, FroggerItems truck){
        gameArea.getChildren().addAll(cg.getImg(), truck.getImg());

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

    /**
     *Sets up the animations for fast items on level 3
     *
     *@param log1, a log from level 3
     *@param log2, a log from level 3
     *@param truck, a truck from level 3
     *@return timeline, the {@code Timeline} for the fast item animation
     */
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

    /**
     *Helper method to create a handler that controls 
     *the movement of hazards in the game of frogger
     */
    private void makeHandler(FroggerItems item){
            if (item.getX()==320){
                item.setX(-280);
            }//if
            else {
                item.addX(5);
            }//else
    }//makeHandler

    /**
     *Helper method used to stop both the slow and fast
     *animation timelines
     */
    protected void stopTL(){
        fastTL.stop();
        slowTL.stop();
    }//stopTL

    /**
     *Helper method used to check if the frog is on top of a log
     *
     *@param logs, the logs used to check for collisions
     *@return true if the frog is on top of a log, false otherwise  
     */
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

    /**
     *Helper method to check if the frog was hit by a vehicle
     *
     *@param vehicles, the vehicles used to check for collisions
     *@return true if the frog was hit by a vehicle, false otherwise
     */
    private boolean collisionCheckVeh(FroggerItems...vehicles){
        for(FroggerItems v:vehicles){
            if(frog.getRect().intersects(v.getRect())){
                stopTL();
                displayLoss();
                return true;
            }//if
        }//for
        return false;
    }//collisionCheckVeh
    
}//AppFrogger
