package com.SPACEWARS;
/**created by 
 * Sarvnaz Alemohammad
 */

//import required libraries for this program
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.SPACEWARS.SpritesClasses.Alien;
import com.SPACEWARS.SpritesClasses.Player;
import com.SPACEWARS.SpritesClasses.Laser;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class GamePlay extends JPanel {
    //declare variables
    //the addition of "2" at the end of the variable means that it is for the second player
    private Dimension d;
    private List<Alien> aliens1;
    private List<Alien> aliens2;
    private Player player;
    private Player player2;

    private Laser laser1;
    private Laser laser2;

    //direction in which aliens will be traveling 
    private int direction1 = 3;
    private int direction2 = -3;
    //keep track of scores
    private int score1 = 0;
    private int score2 = 0;
    

    private boolean inGame = true;

    private String explImg = "src/images/explosion.png";
    private String display = "Game Over";
    private String score1display = "Player 1: " + score1 + " points.";
    private String score2display = "Player 2: " + score2 + " points.";

    private Timer timer;

    /**
     * 
     * 
     * 
     */
    public GamePlay(Graphics g) {
        //open page
        initScreen();
    }

    /**
     * Initalizies the game board.
     * pre: none
     * post: page is opened and background is set to black, gameInit() is called.
     */
        
    private void initScreen() {

        addKeyListener(new KeyActions());
        setFocusable(true);
        d = new Dimension(Constants.BOARD_WIDTH, Constants.BOARD_HEIGHT);
        setBackground(Color.black);

        timer = new Timer(Constants.DELAY, new GameCycle());
        timer.start();

        initGame();
    }
    /**
     * Initializes the game
     * pre: none
     * post: all sprites have been created
     */

    private void initGame() {

        //enemy aliens are constructed in arrays of 3 x 2 and move as a unit
        aliens1 = new ArrayList<>(); //aliens for player1
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {

                var alien = new Alien(Constants.ALIEN_INIT_X + 18 * j,
                        Constants.ALIEN_INIT_Y + 18 * i);
                aliens1.add(alien);
            }
        }


        aliens2 = new ArrayList<>(); //aliens for player1
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {

                var alien = new Alien(Constants.ALIEN_INIT_X2 + 18 * j,
                        Constants.ALIEN_INIT_Y2 + 18 * i);
                aliens2.add(alien);
            }
        }



        player = new Player(1);
        player2 = new Player(2);
        laser1 = new Laser();
        laser2 = new Laser();
    }

    /**
     * Draws the enemy aliens on the screen 
     * pre: g 
     * post: enemy aliens have been drawn if they are still alive.
     */
    private void drawAliens(Graphics g) {
        //aliens for player 1
        for (Alien alien : aliens1) {

            if (alien.getVisible()) {

                g.drawImage(alien.getImage(), alien.getX(), alien.getY(), 30, 30, this);
            }

            if (alien.isDying()) {

                alien.die();
            }
        }
        //aliens for player 2
        for (Alien alien : aliens2) {

            if (alien.getVisible()) {

                g.drawImage(alien.getImage(), alien.getX(), alien.getY(), 30, 30, this);
            }

            if (alien.isDying()) {

                alien.die();
            }
        }
    }

     /**
     * Draws the first player on the screen 
     * pre: g 
     * post: the first player has been drawn if still alive
     */
    private void drawPlayer(Graphics g) {

        if (player.getVisible()) {

            g.drawImage(player.getImage(), player.getX(), player.getY(), 40, 60, this);
        }

        if (player.isDying()) {

            player.die();
            inGame = false;
            display = "Game over - Player 2 wins!";
        }
    }
    /**
     * Draws the second player on the screen 
     * pre: g 
     * post: the second player has been drawn if still alive
     */
    private void drawPlayer2(Graphics g){
        if (player2.getVisible()) {
            g.drawImage(player2.getImage(), player2.getX(), player2.getY(), 40, 60, this);
        }

        if (player2.isDying()) {

        player2.die();
        inGame = false;
        display = "Game over - Player 1 wins!";
        }
    }
    /**
     * Draws the players' lasers on the screen 
     * pre: g 
     * post: the laser has been drawn 
     */ 
    private void drawLaser(Graphics g) {

        if (laser1.getVisible()) {

            g.drawImage(laser1.getImage(), laser1.getX(), laser1.getY(), 20, 20 ,this);
        }
        if (laser2.getVisible()) {

            g.drawImage(laser2.getImage(), laser2.getX(), laser2.getY(), this);
        }
    }
    /**
     * Draws the alien bombs on the screen 
     * pre: g 
     * post: the alien bombs have been drawn 
     */ 
    private void drawBombing(Graphics g) {

        for (Alien a : aliens1) {

            Alien.Bomb b = a.getBomb();

            if (!b.isDestroyed()) {

                g.drawImage(b.getImage(), b.getX(), b.getY(), 15, 10,  this);
            }
        }
        for (Alien a : aliens2) {

            Alien.Bomb b = a.getBomb();

            if (!b.isDestroyed()) {

                g.drawImage(b.getImage(), b.getX(), b.getY(), 15, 15, this);
            }
        }
    }
    /**
     * Draws each player's scores on the screen 
     * pre: g 
     * post: the user scores have been displayed
     */
    public void printScore(Graphics g){
        score1display = "Player 2: " + score1 + " points.";
        score2display = "Player 1: " + score2 + " points.";
        g.drawString(score1display, 30, 750);
        g.drawString(score2display, 850, 750);
    }
    /**
     * draws all of the components on the screen
     * pre: g parameter
     * post: board has been fully drawn
     */
    private void doDrawing(Graphics g) {
        //draw background color
        g.setColor(Color.black);
        g.fillRect(0, 0, d.width, d.height);
        g.setColor(Color.green);
        if (inGame) {
            //draws all components
            g.drawLine(500, 0, 500, 1000);
            g.drawLine(0, 790 , 1000, 790);
            drawAliens(g);
            drawPlayer(g);
            drawPlayer2(g);
            drawLaser(g);
            drawBombing(g);
            printScore(g);
            //g.drawString(score1display, 30, 750);
            //g.drawString(score2display, 850, 750);

        } 
        //stops the game and draws game over screen
        else if (inGame == false){
            if (timer.isRunning()) {
                timer.stop();
            }
            gameOver(g);
        }
        Toolkit.getDefaultToolkit().sync();
    }
    
    /**
     * Implemented from Jpanel
     * pre: g
     * post: updates the board
     */ 
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
        printScore(g);
    }
    /**
     * Game over screen after one user has won
     * pre: g parameter
     * post: displays message and winner
     */

    private void gameOver(Graphics g) {

        g.setColor(Color.black);
        g.fillRect(0, 0, Constants.BOARD_WIDTH, Constants.BOARD_HEIGHT);

        g.setColor(new Color(0, 32, 48));
        g.fillRect(50, Constants.BOARD_WIDTH / 2 - 30, Constants.BOARD_WIDTH - 100, 50);
        g.setColor(Color.white);
        g.drawRect(50, Constants.BOARD_WIDTH / 2 - 30, Constants.BOARD_WIDTH - 100, 50);

        var small = new Font("Helvetica", Font.BOLD, 14);
        var fontMetrics = this.getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(display, (Constants.BOARD_WIDTH - fontMetrics.stringWidth(display)) / 2,
                Constants.BOARD_WIDTH / 2);
     
    }

    /**
     * updates all necessary game methods to keep track of the component
     * pre: none
     * post: all components have been updated
     */
    private void update() {
        //happens if user one has destoryed all of their enemies
        if (score1 == Constants.NUMBER_OF_ALIENS_TO_DESTROY) {
            inGame = false;
            timer.stop();
            display = "Game won by player 2!";
        }
        //happens if user two has destoryed all of their enemies
        if (score2 == Constants.NUMBER_OF_ALIENS_TO_DESTROY) {
            inGame = false;
            timer.stop();
            display = "Game won by player 1!";
        }

        //updates players
        player.move();
        player2.move();

        //sees if player 1's shot has hit an alien

        if (laser1.getVisible()) {

            int laser2X = laser1.getX();
            int laser2Y = laser1.getY();

            for (Alien alien : aliens1) {

                int alienX = alien.getX();
                int alienY = alien.getY();

                if (alien.getVisible() && laser1.getVisible()) {
                    if (laser2X >= (alienX - 100)
                            && laser2X <= (alienX + 100)
                            && laser2Y >= (alienY - 100)
                            && laser2Y <= (alienY + 100)
                            ) {

                        var ii = new ImageIcon(explImg);
                        alien.setImage(ii.getImage());
                        alien.setDying(true);
                        score1 += 1;
                        laser1.die();
                    }
                }
            }
            int y = laser1.getY();
            y -= 12;

            if (y < 0) {
                laser1.die();
            } else {
                laser1.setY(y);
            }
        }


        //sees if player 2's laser has hit an alien
        if (laser2.getVisible()) {

                int laser2X = laser2.getX();
                int laser2Y = laser2.getY();
    
                for (Alien alien : aliens2) {
    
                    int alienX = alien.getX();
                    int alienY = alien.getY();
    
                    if (alien.getVisible() && laser2.getVisible()) {
                        if (laser2X >= (alienX - 100)
                                && laser2X <= (alienX + 100)
                                && laser2Y >= (alienY - 100)
                                && laser2Y <= (alienY + 100)) {
    
                            var ii = new ImageIcon(explImg);
                            alien.setImage(ii.getImage());
                            alien.setDying(true);
                            score2 += 1;
                            laser2.die();
                        }
                   
                    }

            }
            //moves player 2's shot 
            int y = laser2.getY();
            y -= 12;
            
            if (y < 0) {
                laser2.die();
            } 
            else {
                laser2.setY(y);
        }

    }
    
        //moves the alien array through the screen and downwards
        for (Alien alien : aliens1) {

            int x = alien.getX();

            //moves left if it reaches the divider
            if (x >= 500 && direction1 != -3) {

                direction1 = -3;

                Iterator<Alien> i1 = aliens1.iterator();

                while (i1.hasNext()) {

                    Alien a2 = i1.next();
                    a2.setY(a2.getY() + Constants.DOWN);
                }
            }
            //moves right if it reaches the border
            if (x <= 0 && direction1 != 3) {

                direction1 = 3;

                Iterator<Alien> i2 = aliens1.iterator();

                while (i2.hasNext()) {

                    Alien a = i2.next();
                    a.setY(a.getY() + Constants.DOWN);
                }
            }
        }
        //iterates through every element
        Iterator<Alien> it = aliens1.iterator();

        while (it.hasNext()) {

            Alien alien = it.next();
            //moves aliens
            if (alien.getVisible()) {
                alien.move(direction1);
            }
        }
        
        //controls movement of second set of aliens
        for (Alien enemy : aliens2) {

            int enemyx = enemy.getX();

            if (enemyx <= 500  && direction2 != 3) {

                direction2 = 3;

                Iterator<Alien> i1 = aliens2.iterator();

                while (i1.hasNext()) {

                    Alien a2 = i1.next();
                    a2.setY(a2.getY() + Constants.DOWN);
                }
            }

            if (enemyx >= 990 && direction2 != -3) {

                direction2 = -3;

                Iterator<Alien> i2 = aliens2.iterator();

                while (i2.hasNext()) {

                    Alien a = i2.next();
                    a.setY(a.getY() + Constants.DOWN);
                }
            }
        }
        //iterates through every element
        Iterator<Alien> it2 = aliens2.iterator();

        while (it2.hasNext()) {

            Alien enemy = it2.next();

            if (enemy.getVisible()) {

                enemy.move(direction2);
            }
        }
           
        // generates bombs for the first alien array
        var generator1 = new Random();

        for (Alien enemy2 : aliens1) {

            int laser1 = generator1.nextInt(15);
            Alien.Bomb bomb = enemy2.getBomb();

            if (laser1 == Constants.CHANCE && enemy2.getVisible() && bomb.isDestroyed()) {

                bomb.setDestroyed(false);
                bomb.setX(enemy2.getX());
                bomb.setY(enemy2.getY());
            }

        int bombX = bomb.getX();
        int bombY = bomb.getY();
        int playerX = player.getX();
        int playerY = player.getY();
        int player2X = player2.getX();
        int player2Y = player2.getY();
            //if player 1 has been hit by a bomb
            if (player.getVisible() && !bomb.isDestroyed()) {

                if (bombX >= (playerX)
                        && bombX <= (playerX + Constants.PLAYER_WIDTH)
                        && bombY >= (playerY)
                        && bombY <= (playerY + Constants.PLAYER_HEIGHT)) {

                    var ii = new ImageIcon(explImg);
                    player.setImage(ii.getImage());
                    player.setDying(true);
                    bomb.setDestroyed(true);
                }
            }
            //if player2 has been hit by a bomb
            if (player2.getVisible() && !bomb.isDestroyed()) {

                if (bombX >= (player2X)
                        && bombX <= (player2X + Constants.PLAYER_WIDTH)
                        && bombY >= (player2Y)
                        && bombY <= (player2Y + Constants.PLAYER_HEIGHT)) {

                    var ii = new ImageIcon(explImg);
                    player2.setImage(ii.getImage());
                    player2.setDying(true);
                    bomb.setDestroyed(true);
                }
            }
            if (!bomb.isDestroyed()) {

                bomb.setY(bomb.getY() + 4);

                if (bomb.getY() >= Constants.GROUND - Constants.BHEIGHT) {

                    bomb.setDestroyed(true);
                }
            }
        }
    
    
    //generates bombs for the second alien array
    for (Alien enemy : aliens2) {
        var generator2 = new Random();
        int shot = generator2.nextInt(15);
            Alien.Bomb bomb = enemy.getBomb();

            if (shot == Constants.CHANCE && enemy.getVisible() && bomb.isDestroyed()) {

                bomb.setDestroyed(false);
                bomb.setX(enemy.getX());
                bomb.setY(enemy.getY());
            }

        int bombX = bomb.getX();
        int bombY = bomb.getY();
        int playerX = player.getX();
        int playerY = player.getY();
        int player2X = player2.getX();
        int player2Y = player2.getY();

            if (player.getVisible() && !bomb.isDestroyed()) {

                if (bombX >= (playerX)
                        && bombX <= (playerX + Constants.PLAYER_WIDTH)
                        && bombY >= (playerY)
                        && bombY <= (playerY + Constants.PLAYER_HEIGHT)) {

                    var ii = new ImageIcon(explImg);
                    player.setImage(ii.getImage());
                    player.setDying(true);
                    bomb.setDestroyed(true);
                }
            }
            //kills player 2 if hit by a bomb 
            if (player2.getVisible() && !bomb.isDestroyed()) {

                if (bombX >= (player2X)
                        && bombX <= (player2X + Constants.PLAYER_WIDTH)
                        && bombY >= (player2Y)
                        && bombY <= (player2Y + Constants.PLAYER_HEIGHT)) {

                    var ii = new ImageIcon(explImg);
                    player2.setImage(ii.getImage());
                    player2.setDying(true);
                    bomb.setDestroyed(true);
                }
            }
            //moves bomb downwards
            if (!bomb.isDestroyed()) {

                bomb.setY(bomb.getY() + 4);

                if (bomb.getY() >= Constants.GROUND - Constants.BHEIGHT) {

                    bomb.setDestroyed(true);
                }
            }
        }  
    }


    /**
     * Updates the two update methods
     * pre: none
     * post: game has been updates
     */
    private void doGameCycle() {
        update();
        repaint();
    }
    /**
     * Updates the two update methods
     * pre: none
     * post: game has been updates
     */
    private class GameCycle implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            doGameCycle();
        }
    }
    /**
     * Implements actions based off of the key pressed
     * pre: none
     * post: game has been updates
     */
    private class KeyActions extends KeyAdapter {
        @Override     
        public void keyReleased(KeyEvent e) {
            player.keyReleased(e);
            player2.keyReleased2(e);
            
        }
        @Override
        public void keyPressed(KeyEvent e) {
   
            //moves player1 right
            if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
                if(player.getX() >= 800) {
                    player.moveRight(false);
                }
                else {
                    player.moveRight(true);
                }
            }
            //moves player1 left
            if(e.getKeyCode() == KeyEvent.VK_LEFT) {
                if(player.getX() <10) {
                    player.moveLeft(false);
                }
                else {
                    player.moveLeft(true);
                }
            }
              
            //moves player2 right
            if(e.getKeyCode() == KeyEvent.VK_D) {
                if(player2.getX() >= 800) {
                    player2.moveRight(false);
                }
                else {
                    player2.moveRight(true);
                }
            }
            //moves player2 left
            if(e.getKeyCode() == KeyEvent.VK_A) {
                if(player2.getX() <10) {
                    player2.moveLeft(false);
                }
                else {
                    player2.moveLeft(true);
                }
            }  
            

            int key = e.getKeyCode();
            int x = player.getX();
            int y = player.getY();
            int x2 = player2.getX();
            int y2 = player2.getY();
            //shoots a laser for player one
            if (key == KeyEvent.VK_UP) {

                if (inGame) {

                    if (!laser2.getVisible()) {

                        laser2 = new Laser(x, y);
                    }
                }
            }
             //shoots a laser for player two
            if (key == KeyEvent.VK_W) {

                if (inGame) {

                    if (!laser1.getVisible()) {

                        laser1 = new Laser(x2, y2);
                    }
                }
            }   
        }
     

    }
}


