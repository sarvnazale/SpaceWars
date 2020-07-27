package com.SPACEWARS.SpritesClasses;

import javax.swing.ImageIcon;

public class Alien extends Sprite {
    
    private Bomb bomb;
    /**
     * constructor
     * pre: x and y value of alien
     * post: alien method has been initialized
     */
    public Alien(int x, int y) {

        initAlien(x, y);
    }
    /**
     * initializes object and sets basic values
     * pre:  x and y value of alien
     * post: image has been set, bomb has been created
     */

    private void initAlien(int x, int y) {

        this.x = x;
        this.y = y;
        //create new bomb object
        bomb = new Bomb(x, y);
        //set image
        var alienImg = "src/images/alien.png";
        var ii = new ImageIcon(alienImg);

        setImage(ii.getImage());
    }

    /**
     * moves each alien
     * pre: value of direction
     * post: direction has been added to x value
     */
    public void move(int direction) {

        this.x += direction;
    }
    /**
     * returns bomb 
     * pre: none
     * post: bomb has been returned
     */

    public Bomb getBomb() {

        return bomb;
    }
    //new class for bomb object
    public class Bomb extends Sprite {

        private boolean destroyed;
        /**
        * constructor
        * pre: x and y value of bomb
        * post: bomb method has been initialized
         */

        public Bomb(int x, int y) {

            initBomb(x, y);
        }
        
        /**
        * initializes object and sets basic values
        * pre:  x and y value of alien
        * post: image has been set, bomb has been created
        */
        private void initBomb(int x, int y) {

            setDestroyed(true);

            this.x = x;
            this.y = y;

            var bombImg = "src/images/bomb.png";
            var ii = new ImageIcon(bombImg);
            setImage(ii.getImage());
        }

        /**
         * sets whether bomb is destoryed
         * pre: destroyed status
         * post: boolean destoryed has been se4t
         */
        public void setDestroyed(boolean destroyed) {

            this.destroyed = destroyed;
        }

        /**
         * returns whether bomb is destoryed
         * pre: none
         * post: destroyed has been returned
         */
        public boolean isDestroyed() {

            return destroyed;
        }
    }
}
