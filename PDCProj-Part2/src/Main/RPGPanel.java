/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import Entity.Player;
import Object.superObject;
import Tile.TileManager;
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

    final int origTileSize = 16;    //16x16 tile
    final int scale = 3;

    public final int tileSize = origTileSize * scale; // 48x48 tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;

    public final int screenWidth = tileSize * maxScreenCol; //768 pixels
    public final int screenHeight = tileSize * maxScreenRow; //576 pixel

    //World Settings
    public final int maxWorldCols = 15;
    public final int maxWorldRows = 10;
    public final int worldWidth = tileSize * maxWorldCols;
    public final int worldHeight = tileSize * maxWorldRows;

    //FPS
    int FPS = 60;

    //SYSTEM
    TileManager tm = new TileManager(this);
    KeyHandler key = new KeyHandler();
    Thread gameThread;
    public CollisionHandler ch = new CollisionHandler(this);
    public Player player = new Player(this, key);
    public AssetHandler ah = new AssetHandler(this);
    public UI ui = new UI(this);

    //Objects
    public superObject sObj[] = new superObject[10];

    //States
    int gameState;
    public final int titleState = 0;

    public RPGPanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(key);
        this.setFocusable(true);
    }

    public void setupMap() {
        ah.setObject();
        gameState = titleState;
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;
        while (gameThread != null) {
            //System.out.println("Game is running");

            update();
            repaint();

            try {
                double remainTime = nextDrawTime - System.nanoTime();
                remainTime = remainTime / 1000000;

                if (remainTime < 0) {
                    remainTime = 0;
                }

                nextDrawTime += drawInterval;

                Thread.sleep((long) remainTime);
            } catch (InterruptedException ex) {
                Logger.getLogger(RPGPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void update() {
        player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        //Title Screen
        if (gameState == titleState) {

        } else {
            //Tiles
            tm.draw(g2);
            //Objects
            for (int i = 0; i < sObj.length; i++) {
                if (sObj[i] != null) {
                    sObj[i].draw(g2, this);
                }
            }
            //Player
            player.draw(g2);
            //UI
            ui.draw(g2);
        }
        g2.dispose();
    }
}
