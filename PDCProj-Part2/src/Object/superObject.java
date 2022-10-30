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
public abstract class superObject {

    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int defaultSolidAreaX = 0;
    public int defaultSolidAreaY = 0;
    Tools t = new Tools();

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
    
    public abstract void setCollision();
}
