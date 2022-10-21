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
public class superObject {

    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int defaultSolidAreaX = 0;
    public int defaultSolidAreaY = 0;
    Tools t = new Tools();

    public void draw(Graphics2D g2, RPGPanel rp) {
        int screenX = worldX - rp.player.worldX + rp.player.screenX;
        int screenY = worldY - rp.player.worldY + rp.player.screenY;

        if (worldX + rp.tileSize > rp.player.worldX - rp.player.screenX
                && worldX - rp.tileSize < rp.player.worldX + rp.player.screenX
                && worldY + rp.tileSize > rp.player.worldY - rp.player.screenY
                && worldY - rp.tileSize < rp.player.worldY + rp.player.screenY) {
            g2.drawImage(image, screenX, screenY, rp.tileSize, rp.tileSize, null);

        }
    }
}
