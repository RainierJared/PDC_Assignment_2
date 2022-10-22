/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import Main.KeyHandler;
import Main.RPGPanel;
import Main.Tools;
import java.awt.Graphics2D;
import java.awt.Rectangle;
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

    public int hasItems = 0;

    boolean hasPhone = false;
    public boolean hasTea = false;
    public boolean hasCoffee = false;

    public Player(RPGPanel rp, KeyHandler key) {
        this.rp = rp;
        this.key = key;

        screenX = rp.screenWidth / 2 - (rp.tileSize / 2);
        screenY = rp.screenHeight / 2 - (rp.tileSize / 2);

        solidArea = new Rectangle(8, 16, 32, 32);
        defaultSolidAreaX = solidArea.x;
        defaultSolidAreaY = solidArea.y;

        setDefaultSettings();
        getPlayerImage();
    }

    public void setDefaultSettings() {
        worldX = rp.tileSize * 11;       //Player's pos on the world map
        worldY = rp.tileSize * 7;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {

        up1 = setup("walk-up-1");
        up2 = setup("walk-up-2");
        down1 = setup("walk-down-1");
        down2 = setup("walk-down-2");
        left1 = setup("walk-left-1");
        left2 = setup("walk-left-2");
        right1 = setup("walk-right-1");
        right2 = setup("walk-right-2");
        idle = setup("idle-1");

    }

    public BufferedImage setup(String imgName) {
        Tools t = new Tools();
        BufferedImage scaledImg = null;
        try {
            scaledImg = ImageIO.read(getClass().getResourceAsStream("/res/player/" + imgName + ".png"));
            scaledImg = t.scaleImg(scaledImg, rp.tileSize, rp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scaledImg;
    }

    public void update() {

        if (key.upPress || key.downPress || key.leftPress || key.rightPress) {
            if (key.upPress) {
                direction = "up";
            } else if (key.downPress) {
                direction = "down";
            } else if (key.leftPress) {
                direction = "left";
            } else if (key.rightPress) {
                direction = "right";
            }

            //For tile collisions
            entCollision = false;
            rp.ch.checkTile(this);

            //For object collisions
            int objIndex = rp.ch.checkObject(this, true);
            pickUpObject(objIndex);

            //If Player doesn't collide with anything, they can move
            if (entCollision == false) {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
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

    public void pickUpObject(int i) {
        if(hasItems == 3) {
            rp.ah.drawCoffee();
        }
        
        if (i != 999) {
            String objName = rp.sObj[i].name;

            switch (objName) {
                case "Phone":
                    hasItems--;
                    rp.sObj[i] = null;
                    rp.ui.showMsg("Phone Acquired!");
                    hasPhone = true;
                    break;
                case "Tea":
                    hasItems += 5;
                    rp.sObj[i] = null;
                    rp.ui.showMsg("Tea Drank!");
                    hasTea = true;
                    rp.ui.gameFinished = true;
                    break;
                case "Coffee":
                    hasItems++;
                    rp.sObj[i] = null;
                    rp.ui.showMsg("Coffee Powder Acquired!");
                    break;
                case "Sugar":
                    hasItems++;
                    rp.sObj[i] = null;
                    rp.ui.showMsg("Sugar Acquired!");
                    break;
                case "Milk":
                    hasItems++;
                    rp.sObj[i] = null;
                    rp.ui.showMsg("Milk Acquired!");
                    break;
                case "Mug":
                    hasItems++;
                    rp.sObj[i] = null;
                    rp.ui.showMsg("Empty Mug Acquired!");
                    break;
                case "Door":
                    if (hasPhone == true) {
                        rp.sObj[i] = null;
                        rp.ui.showMsg("Door Opened!");
                        hasPhone = false;
                    } else {
                        rp.ui.showMsg("Key item has not been acquired...");
                    }
                    break;
                case "MugWithCoffee":
                    if (hasItems == 3) {
                        rp.sObj[i] = null;
                        rp.ui.showMsg("Coffee Acquired!");
                        hasItems = 10;
                        hasCoffee = true;
                        rp.ui.gameFinished = true;
                    } else {
                        rp.ui.showMsg("Need Coffee level of at least 3 to acquire...");
                    }
                    break;
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
