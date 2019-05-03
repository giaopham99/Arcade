package cs1302.arcade;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import java.awt.Rectangle;

public class FroggerItems{

    //Constant Images
    private static final Image FROG = new Image("FroggerItems/Frog.png");
    private static final Image FLY = new Image("FroggerItems/Fly.png");
    private static final Image LOG = new Image("FroggerItems/Log.png");
    private static final Image DEATH = new Image("FroggerItems/Death.png");
    private static final Image TRUCK = new Image("FroggerItems/TruckSmall.png");
    private static final Image C_BLUE = new Image("FroggerItems/CarBlue.png");
    private static final Image C_GREEN = new Image("FroggerItems/CarGreen.png");
    private static final Image C_YELLOW = new Image("FroggerItems/CarYellow.png");

    //Item Properties
    private ImageView item;
    private int x;
    private int y;
    private Rectangle hitBox;
    private boolean visible;

    public FroggerItems(String item, int x, int y,int width,int height, boolean visible){
        this.item = new ImageView(itemChoice(item));
        this.x =x;
        this.y =y;
        this.item.setTranslateX(x);
        this.item.setTranslateY(y);
        this.visible = visible;
        hitBox = new Rectangle(this.x,this.y,width,height);
    }//FroggerItems

    private Image itemChoice(String item){
        Image temp = FROG;
        switch(item){
        case "frog": temp = FROG;
            break;
        case "fly": temp = FLY;
            break;
        case "log": temp = LOG;
            break;
        case "death": temp = DEATH;
            break;
        case "truck": temp = TRUCK;
            break;
        case "cb": temp =  C_BLUE;
            break;
        case "cg": temp = C_GREEN;
            break;
        case "cy": temp =  C_YELLOW;
            break;
        }
        return temp;
    }//itemChoice
    
    public void setX(int x){
        this.x = x;
    }//setX

    public void addX(int move){
        x += move;
        item.setTranslateX(x);
        hitBox.setLocation(this.x-27,this.y);
    }//addX

    public int getX(){
        int temp = x;
        return temp;
    }//getX

    public void setY(int y){
        this.y = y;
    }//setY

    public void addY(int move){
        y += move;
        item.setTranslateY(y);
        hitBox.setLocation(this.x-27,this.y);
    }//addY

    public int getY(){
        int temp = y;
        return temp;
    }//getY
    
    public void setXY(int x, int y){
        this.x = x;
        this.y = y;
    }//setXY

    public ImageView getImg(){
        return item;
    }//getImg

    public Rectangle getRect(){
        return hitBox;
    }//getRect(lol) 

    public void rotateImg(double degrees){
        item.setRotate(degrees);
    }

    public void setImg(String image){
       this.item.setImage(itemChoice(image));
    }//setImg

    public void setVisibility(boolean visible){
        this.visible = visible;
    }//setVisibility

    public boolean getVisibility(){
        boolean temp = visible;
        return temp;
    }//getVisibility
}//FroggerItems
