package com.SPACEWARS;
/**created by 
 * Sarvnaz Alemohammad
 */

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Graphics;

public class SpaceWars extends JFrame  {

    private static final long serialVersionUID = 1L;
    public Graphics g;
    /**
     * constructor
     * pre: none
     * post: initializes user interface
     */
    public SpaceWars() {

        initUI();
    }
    /**
     * creates new Board and starts the game
     * pre: none
     * post: basics of game have been created
     */

    private void initUI() {
        
        add(new GamePlay(g));

        setTitle("Space Wars"); //title of page
        setSize(Constants.BOARD_WIDTH, Constants.BOARD_HEIGHT); //size of page
        setLocationRelativeTo(null);

        setDefaultCloseOperation(EXIT_ON_CLOSE); //exists the game when tab is closed
        setResizable(false); //page is not resizable
    }

    public static void main(String[] args) {
        
        EventQueue.invokeLater(() -> {
            var ex = new SpaceWars(); //new spaceinvaders game
            ex.setVisible(true); 
        });
    }
}
