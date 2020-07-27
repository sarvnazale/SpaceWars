package com.SPACEWARS.SpritesClasses;

import java.awt.Image;
//super class for all objects in the game
public class Sprite {

    private boolean visible;
    private Image image;
    private boolean dying;

    int x;
    int y;
    public int sprite_direction; //direction of movement

    /**
     * constructor
     * pre: none
     * post: defaults sprite to visible
     */
    public Sprite() {

        visible = true;
    }
    /**
     * erases sprite from screen if dead
     * pre: none
     * post: visibililty is set to false
     */
    public void die() {

        visible = false;
    }
 
    public boolean isDying() {

        return this.dying;
    }

    /**
     * returns visbility status of object
     * pre: none
     * post: visbility status of object is returned
     */
    public boolean getVisible() {

        return visible;
    }
    /**
     * changes visbility status of object
     * pre: visibility status
     * post: visbility status of object is changed
     */
    protected void setVisible(boolean visible) {

        this.visible = visible;
    }

    /**
     * sets image of object
     * pre: image graphic
     * post: image of object is set
     */
    public void setImage(Image image) {

        this.image = image;
    }
    /**
     * changes image  of object
     * pre: none
     * post: image of object is changed
     */
    public Image getImage() {

        return image;
    }

    /**
     * sets x-coordinate of object
     * pre: x-coordinate 
     * post: x-coordinate  has been set
     */
    public void setX(int x) {

        this.x = x;
    }

    /**
     * sets y-coordinate of object
     * pre: y coordinate
     * post: y-coordinate has been set
     */
    public void setY(int y) {

        this.y = y;
    }
    /**
     * returns y-coordinate of object
     * pre: y coordinate
     * post: y-coordinate has been returned
     */

    public int getY() {

        return y;
    }

    /**
     * returns x-coordinate of object
     * pre: x coordinate
     * post: x-coordinate has been returned
     */
    public int getX() {

        return x;
    }

    /**
     * sets death status
     * pre: dying status 
     * post: death status has been set
     */
    public void setDying(boolean dying) {

        this.dying = dying;
    }
    /**
     * returns death status
     * pre: none
     * post: death status has been returned
     */

}
