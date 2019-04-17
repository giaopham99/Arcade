package cs1302.arcade;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Controller implements KeyListener{

    Group group;
    boolean is2048;
    
    public Controller(Group g, boolean is2048){
        this.is2048 = is2048;
        group=g;
    }//controller

    public void keyPressed(KeyEvent e){
        int keyCode = e.getKeyCode();
        
        if (is2048){
            switch (keyCode){
                case KeyEvent.VK_UP: group.slideUp();
                    break;
                case KeyEvent.VK_DOWN: group.slideDown();
                    break;
                case KeyEvent.VK_LEFT: group.slideLeft();
                    break;
                case KeyEvent.VK_RIGHT: group.slideRight();
                    break;
            }//switch
        }//if
        else{
            switch (keyCode){
                case KeyEvent.VK_DOWN: group.slam();
                    break;
                case KeyEvent.VK_LEFT: group.moveLeft();
                    break;
                case KeyEvent.VK_RIGHT: group.moveRight();
                    break;
                case KeyEvent.VK_SPACE: group.rotatePiece();
                    break;
            }//switch
        }//else
    }//keyPressed

    public void keyReleased(KeyEvent e){
        return;
    }//keyReleased

    public void keyTyped(KeyEvent e){
        return;
    }//keyTyped
}//Controller
