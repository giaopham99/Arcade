package cs1302.arcade;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import java.awt.Rectangle;

/**
 *Class representing an object in {@code AppFrogger}. The items have 
 *properties that allow for movement around the game board as well as
 *a corresponding {@code ImageView}. 
 */
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

    /**
     *Constructor for a {@code FroggerItems} object.
     *
     *@param item, the name of the item that is used to get its {@code ImageView} 
     *@param x, the starting x position of the item
     *@param y, the starting y position of the item
     *@param width, the width of the item's hitbox
     *@param height, the height of the item's hitbox
     */
    public FroggerItems(String item, int x, int y,int width,int height){
        this.item = new ImageView(itemChoice(item));
        this.x =x;
        this.y =y;
        this.item.setTranslateX(x);
        this.item.setTranslateY(y);
        hitBox = new Rectangle(this.x,this.y,width,height);
    }//FroggerItems

    /**
     *Helper method used to get the image corresponding to a {@code FroggerItems} object
     *
     *@param item, the name of the item 
     *@return temp, the image corresponding to the item's name  
     */
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
    
    /**
     *Setter method to set the x position of the {@code FroggerItems} object
     *
     *@param x, the new x position of the item
     */
    public void setX(int x){
        this.x = x;
        item.setTranslateX(x);
    }//setX

    /**
     *Method to add to the item's x position
     *
     *@param move, the amount to add to the item's x position
     */
    public void addX(int move){
        x += move;
        item.setTranslateX(x);
        hitBox.setLocation(this.x,this.y);
    }//addX

    /**
     *Getter method for the x position of the {@code FroggerItems} object
     *
     *@return temp, the current x position of the item
     */
    public int getX(){
        int temp = x;
        return temp;
    }//getX

    /**
     *Setter method to set the y position of the {@code FroggerItems} object
     *
     *@param y, the new y position of the item
     */
    public void setY(int y){
        this.y = y;
        item.setTranslateY(y);
    }//setY

    /**
     *Helper method to add to the item's x position
     *
     *@param move, the amount to add to the item's y position
     */
    public void addY(int move){
        y += move;
        item.setTranslateY(y);
        hitBox.setLocation(this.x-27,this.y);
    }//addY

    /**
     *Getter method for the y position of the {@code FroggerItems} object
     *
     *@return temp, the current y position of the item
     */
    public int getY(){
        int temp = y;
        return temp;
    }//getY

    /**
     *Setter method to set the x and y position of the {@code FroggerItems} object
     *
     *@param x, the new x position of the item
     *@param y, the new y position of the item
     */
    public void setXY(int x, int y){
        this.x = x;
        this.y = y;
        hitBox.setLocation(this.x,this.y);
        item.setTranslateX(this.x);
        item.setTranslateY(this.y);
    }//setXY

    /**
     *Getter method for the image of the {@code FroggerItems} object
     *
     *@return item, the image of the item
     */
    public ImageView getImg(){
        return item;
    }//getImg

    /**
     *Getter method for the {@code hitBox} of the {@code FroggerItems} object
     *
     *@return hitBox, the rectangle for the item used in collision detection
     */
    public Rectangle getRect(){
        return hitBox;
    }//getRect(lol) 

    /**
     *Roatates the item by a certain amount of degrees
     *
     *@param degrees, the number of degrees to rotate the item by
     */
    public void rotateImg(double degrees){
        item.setRotate(degrees);
    }//rotateImg

    /**
     *Sets the {@code ImageView} of the item to a new {@code ImageView}
     *
     *@param image, the name of the item 
     */
    public void setImg(String image){
       this.item.setImage(itemChoice(image));
    }//setImg

}//FroggerItems
