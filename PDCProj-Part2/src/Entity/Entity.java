/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author jaredbotor
 */
public class Entity {

    public int worldX, worldY;
    public int speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2, idle1, idle2, idle3;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;

    //Player's hitbox for collision
    public Rectangle solidArea;
    public int defaultSolidAreaX, defaultSolidAreaY;
    public boolean entCollision = false;

}
