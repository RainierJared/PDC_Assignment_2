/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import Entity.Player;
import Object.superObject;
import Tile.TileManager;
import data.SaveLoad;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author jaredbotor
 */
public class RPGPanel extends JPanel implements Runnable {

    //Final Variables
    //----------------------------
    //Scaling variables
    final int ORIGINALTILESIZE = 16;    //16x16 tile
    final int SCALE = 3;

    //Variables for tileSize and max screen columns and rows
    public final int TILESIZE = ORIGINALTILESIZE * SCALE; // 48x48 tile
    public final int MAXSCREENCOL = 16;
    public final int MAXSCREENROW = 12;

    //Calculating the screen width and height
    public final int SCREENWIDTH = TILESIZE * MAXSCREENCOL; //768 pixels
    public final int SCREENHEIGHT = TILESIZE * MAXSCREENROW; //576 pixel

    //World Settings
    public final int MAXWORLDCOL = 15;
    public final int MAXWORLDROW = 10;
    public final int WORLDWIDTH = TILESIZE * MAXWORLDCOL;
    public final int WORLDHEIGHT = TILESIZE * MAXWORLDROW;

    //FPS
    public final int FPS = 60;

    //SYSTEM OBJECTS
    public TileManager tileManagerObj = new TileManager(this);
    public KeyHandler keyHandlerObj = new KeyHandler(this);
    public SaveLoad saveLoad = new SaveLoad(this);
    public Thread gameThread;
    public CollisionHandler collisionHandlerObj = new CollisionHandler(this);
    public Player playerObj = new Player(this, keyHandlerObj);
    public AssetHandler assetHandler = new AssetHandler(this);
    public UI uiObj = new UI(this);

    //Objects
    public superObject[] objArray = new superObject[10];

    //States
    int gameState;
    
    //Variable for test case
    double drawInterval;

    //Constructor
    public RPGPanel() {
        this.setPreferredSize(new Dimension(SCREENWIDTH, SCREENHEIGHT));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandlerObj);
        this.setFocusable(true);
    }

    //Function that sets up the map, objects, and ensures that the game starts in the correct
    //game state
    public void setupMap() {
        assetHandler.setObject();
        gameState = States.INSTRUCTIONSTATE;
    }

    //Function that starts the game threads
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    //Function that resets the positions of objects and the player
    public void restart() {
        playerObj.setDefaultStats();
        assetHandler.setObject();
    }

    //Run function for thread
    @Override
    public void run() {
        drawInterval = 1000000000 / FPS;     //The interval to draw new frames
        double nextDrawTime = System.nanoTime() + drawInterval;     //The draw time for the next frame

        //While the gameThread is running
        while (gameThread != null) {
            //Calling functions to update and repaint the sprites
            update();
            repaint();

            try {
                //Calculating the value for remainTime
                double remainTime = nextDrawTime - System.nanoTime();
                remainTime = remainTime / 1000000;
                //Checking if remainTime becomes negative
                if (remainTime < 0) {
                    remainTime = 0;
                }
                nextDrawTime += drawInterval;   //Initializing the nextDrawTime
                Thread.sleep((long) remainTime);        //Putting the thread to sleep
            } catch (InterruptedException ex) {
                Logger.getLogger(RPGPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //The update function
    public void update() {
        playerObj.updateCheck = true;   //Setting boolean to true
        
        //Checks if the gameState is correct
        if (gameState == States.PLAYSTATE) {
            playerObj.update();     //Calls the update() function from Player class to update sprites
        }
    }

    //The painComponent function that paints either the instruction, title screen, and draws tiles and objects
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        //Switch state for the game's state
        //Different screens will the drawn depending on the game's current state
        switch (gameState) {
            case States.INSTRUCTIONSTATE:
                uiObj.draw(g2);
                break;
            case States.TITLESTATE:
                uiObj.draw(g2);
                break;
            default:
                //Tiles
                tileManagerObj.draw(g2);
                //Objects
                for (superObject sObj1 : objArray) {
                    if (sObj1 != null) {
                        sObj1.draw(g2, this);
                    }
                } //Player
                playerObj.draw(g2);
                //UI
                uiObj.draw(g2);
                break;

        }
        g2.dispose();       //Disposing Graphics2D variable
    }
}
