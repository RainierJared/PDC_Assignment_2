/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import Object.objBag;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author botor
 */
public class UI {
    RPGPanel rp;
    BufferedImage itemImage; 
    public UI(RPGPanel rp) {
        this.rp = rp;
        objBag item = new objBag();
        itemImage = item.image;
        
    }
    
    public void draw(Graphics2D g2) {
        g2.setFont(new Font("Arial",Font.PLAIN,40));
        g2.setColor(Color.white);
        g2.drawImage(itemImage, rp.tileSize/2, rp.tileSize/2,rp.tileSize,rp.tileSize,null);
        g2.drawString("x " + rp.player.hasItems, 74, 65);
    }
}
