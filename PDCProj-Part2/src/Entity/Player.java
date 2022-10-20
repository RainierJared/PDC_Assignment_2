/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import GUIVersion.KeyHandler;
import GUIVersion.RPGPanel;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author jaredbotor
 */
public class Player extends Entity {

    RPGPanel rp;
    KeyHandler key;
    BufferedImage image = null;
    
    public final int screenX;
    public final int screenY;
    
    public Player(RPGPanel rp, KeyHandler key) {
        this.rp = rp;
        this.key = key;
        
        screenX = rp.screenWidth/2 - (rp.tileSize/2);
        screenY = rp.screenHeight/2 - (rp.tileSize/2);
        
        setDefaultSettings();
        getPlayerImage();
    }

    public void setDefaultSettings() {
        worldX = rp.tileSize * 2;       //Player's pos on the world map
        worldY = rp.tileSize * 8;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/res/player/walk-up-1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/player/walk-up-2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/player/walk-down-1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/player/walk-down-2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/player/walk-left-1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/player/walk-left-2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/player/walk-right-1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/player/walk-right-2.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {

        if (key.upPress || key.downPress || key.leftPress || key.rightPress) {
            if (key.upPress) {
                direction = "up";
                worldY -= speed;
            } else if (key.downPress) {
                direction = "down";
                worldY += speed;
            } else if (key.leftPress) {
                direction = "left";
                worldX -= speed;

            } else if (key.rightPress) {
                direction = "right";
                worldX += speed;
            }
            spriteCounter++;
            if (spriteCounter > 10) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2) {
//        g2.setColor(Color.white);
//        g2.fillRect(x, y, rp.tileSize, rp.tileSize);

        image = null;

        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, rp.tileSize, rp.tileSize, null);
    }
}
