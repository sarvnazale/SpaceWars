package com.SPACEWARS.SpritesClasses;

import javax.swing.ImageIcon;

public class Laser extends Sprite {
    /**
     * constructor
     * pre: none
     * post: none
     */
    public Laser() {
    }
    /**
     * constructor
     * pre: x and y value of shot
     * post: initializes shot method
     */
    public Laser(int x, int y) {

        initLaser(x, y);
    }
    /**
     * initalizes shot method
     * pre: x and y value of shot
     * post: image has been set, 
     */
    private void initLaser(int x, int y) {
        //sets image
        var shotImg = "src/images/laser.png";
        var ii = new ImageIcon(shotImg);
        setImage(ii.getImage());
        
        int H_SPACE = 4;
        setX(x + H_SPACE);

        int V_SPACE = 6;
        setY(y - V_SPACE);
    }
}
