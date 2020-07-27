package com.SPACEWARS.SpritesClasses;

import com.SPACEWARS.Constants;


import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;

public class Player extends Sprite {

    private int num;
    /**
     * constructor
     * pre: number of player
     * post: player method has been initialized
     */
    public Player(int num) {
        this.num = num;
        initPlayer(num);
    }
   /**
     * initializes object and sets basic values
     * pre: player number
     * post: image has been set, position has been determined
     */

    private void initPlayer(int num) {

        var playerImg = "src/images/player.png"; //image set
        var ii = new ImageIcon(playerImg);

        //postion determined based off player number
        setImage(ii.getImage());
        if (num == 1) {
            int START_X1 = 800;
            setX(START_X1);

            int START_Y = 600;
            setY(START_Y);
        }
        if (num ==2) {
            int START_X1 = 200;
            setX(START_X1);

            int START_Y = 600;
            setY(START_Y);
        }


    }
    /**
     * moves the player's position on the board if it nears the border
     * pre: none
     * post: player has been moved
     */

    public void move() {

        x += sprite_direction;

        if (x <= 2) {

            x = 2;
        }
        if (num ==1 & x <= 510){
            x = 520;
        }
        if (num ==2 & x >= 495){
            x = 485;
        }
        if (x >= Constants.BOARD_WIDTH - 2) {

            x = Constants.BOARD_WIDTH - 2;
        }

    }
        /**
        * moves the player's position to the right
        * pre: direction of movement
        * post: player has been moved right
        */

        public void moveRight(boolean Right){
            if (Right == true) {
                sprite_direction = 2;

            }
            else {
                sprite_direction = 0;
            }
        }
        /**
        * moves the player's position to the left
        * pre: direction of movement
        * post: player has been moved left
        */
        public void moveLeft(boolean Left){
            if (Left == true) {
                sprite_direction = -2;

            }
            else {
                sprite_direction = 0;
            }
        }
    /**
     * keeps track of keys pressed and relates them to movements
     * pre: key value pressed by user
     * post: direction is determined
     */
    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {

            sprite_direction = 0; 
        }

        if (key == KeyEvent.VK_RIGHT) {

            sprite_direction = 0;
        }

    }
     /**
     * keeps track of keys released and relates them to movements
     * pre: key value pressed by user
     * post: movement is stopped
     */
    public void keyReleased2(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_A) {
            sprite_direction = 0;
        }
        if (key == KeyEvent.VK_D) {
            sprite_direction = 0;
        }
    }
}
