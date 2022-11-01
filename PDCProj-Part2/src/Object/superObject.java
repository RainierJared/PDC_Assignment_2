/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Object;

import Main.RPGPanel;
import Main.Tools;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author botor
 */
//Abstract class for objects/items
//**I will only comment objCoffee.java 
//**as every subclasses are the same, with the only difference with the image, name, and coordinates
public abstract class superObject {

    //Public objects for this class and subclasses
    public BufferedImage image;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public Tools t = new Tools();

    //Variables for the coordinates of each object
    public int worldX, worldY;
    public int defaultSolidAreaX = 0;
    public int defaultSolidAreaY = 0;

    //Public variabels for the name and collision of each object
    public String name;
    public boolean collision = false;

    //Draw function that draws objects according to their coordinates
    public void draw(Graphics2D g2, RPGPanel rp) {
        int screenX = worldX - rp.playerObj.worldX + rp.playerObj.screenX;
        int screenY = worldY - rp.playerObj.worldY + rp.playerObj.screenY;

        if (worldX + rp.TILESIZE > rp.playerObj.worldX - rp.playerObj.screenX
                && worldX - rp.TILESIZE < rp.playerObj.worldX + rp.playerObj.screenX
                && worldY + rp.TILESIZE > rp.playerObj.worldY - rp.playerObj.screenY
                && worldY - rp.TILESIZE < rp.playerObj.worldY + rp.playerObj.screenY) {
            g2.drawImage(image, screenX, screenY, rp.TILESIZE, rp.TILESIZE, null);

        }
    }

    //Abstract function for each object subclass
    public abstract void setCollision();
}
