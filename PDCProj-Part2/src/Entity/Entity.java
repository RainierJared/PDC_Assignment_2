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
//Class for the Entity (Can be player or objects)
public class Entity {

    //Variables for the player's position in the world and the player's speed
    public int worldX, worldY;
    public int speed;

    //String variable that didctates the direction the user is facing
    public String direction;

    //Buffered image objects for each frame of animation
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2, idle;

    //Integer variables that deals with the small sprite animation that occurs as the player is moving
    public int spriteCounter = 0;
    public int spriteNum = 1;

    //Player's hitbox for collision
    public Rectangle solidPlayerArea;
    public int defaultSolidAreaX, defaultSolidAreaY;
    public boolean setCollision = false;

}
