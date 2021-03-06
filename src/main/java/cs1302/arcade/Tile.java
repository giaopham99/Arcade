package cs1302.arcade;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Tile class to hold the image and value of each {@code Tile} on the 2048 board. 
 */
public class Tile{
    //Constants
    public static final String TILE_0 = "Tiles2048/BlankTile.png";
    public static final String TILE_2 = "Tiles2048/Tile2.png";
    public static final String TILE_4 = "Tiles2048/Tile4.png";
    public static final String TILE_8 = "Tiles2048/Tile8.png";
    public static final String TILE_16 = "Tiles2048/Tile16.png";
    public static final String TILE_32 = "Tiles2048/Tile32.png";
    public static final String TILE_64 = "Tiles2048/Tile64.png";
    public static final String TILE_128 = "Tiles2048/Tile128.png";
    public static final String TILE_256 = "Tiles2048/Tile256.png";
    public static final String TILE_512 = "Tiles2048/Tile512.png";
    public static final String TILE_1024 = "Tiles2048/Tile1024.png";
    public static final String TILE_2048 = "Tiles2048/Tile2048.png";
    public static final String TILE_4096 = "Tiles2048/Tile4096.png";
    public static final String TILE_8192 = "Tiles2048/Tile8192.png";
    
    //Properties
    private int value;
    private ImageView imgView;
    
    /**
     * Constructs a new Tile based on the value given.
     * @param value the numeric value of the Tile piece.
     */
    public Tile(int value){
        imgView = new ImageView();
        imgView.setFitWidth(100);
        imgView.setFitHeight(100);
        this.setValue(value);
    }//Tile
    
    /**
     * Method to get the ImageView containing the image in the {@code Tile}.
     * @return the {@code ImageView} containing the image.
     */
    public ImageView getImgView(){
        ImageView copy = imgView;
        return copy;
    }// getImgView
    
    /**
     * Method to get the numeric value of the {@code Tile}.
     * @return the numeric value.
     */
    public int getValue(){
        int temp = value;
        return temp;
    }// getValue
    
    /**
     * Method to set the Tile's value to another number.
     * @param value the new numeric value of the {@code Tile}.
     */
    public void setValue(int value){
        this.value=value;
        imgView.setImage(new Image(getURL(this.value)));
    }//setValue
    
    /**
     * Private method that returns the pathname for the image that represents the
     * specified value.
     * @param value the specified value.
     * @return the pathname of the image.
     */
    private String getURL(int value){
        String path = ""; 
        switch(value) {
        case 0: path = TILE_0;
            break;
        case 2: path = TILE_2;
            break;
        case 4: path = TILE_4;
            break;
        case 8: path = TILE_8;
            break;
        case 16: path = TILE_16;
            break;
        case 32: path = TILE_32;
            break;
        case 64: path = TILE_64;
            break;
        case 128: path = TILE_128;
            break;
        case 256: path = TILE_256;
            break;
        case 512: path = TILE_512;
            break;
        case 1024: path = TILE_1024;
            break;
        case 2048: path = TILE_2048;
            break;
        case 4096: path = TILE_4096;
            break;
        case 8192: path = TILE_8192;
            break;
        }//switch
        return path;
    }//setImage
} // Tile
