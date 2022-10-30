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
//Player class for the game
public final class Player extends Entity {

    //Private Variables and Objects
    private RPGPanel rp;
    private KeyHandler key;
    private BufferedImage image = null;

    //Final values for the game's X and Y
    public final int screenX;
    public final int screenY;

    //Variable that counts how many item the user currently holds
    public int itemCount = 0;

    //Different boolean variables for when the user reloads their save
    //The program will decide to draw or not draw certain items depending on the state of each booleans
    public boolean hasPhone = false;
    public boolean hasTea = false;
    public boolean hasCoffee = false;
    public boolean hasSugar = false;
    public boolean hasMilk = false;
    public boolean hasMug = false;
    public boolean updateCheck = false;

    
    //Constructor that takes in an RPGPanel and KeyHandler object
    public Player(RPGPanel rp, KeyHandler key) {
        
        //Initializes the objects
        this.rp = rp;
        this.key = key;

        //Initializing the size of the screen's x and y values
        screenX = rp.SCREENWIDTH / 2 - (rp.TILESIZE / 2);
        screenY = rp.SCREENHEIGHT / 2 - (rp.TILESIZE / 2);

        //Initializing values for user collision
        solidArea = new Rectangle(8, 16, 32, 32);
        defaultSolidAreaX = solidArea.x;
        defaultSolidAreaY = solidArea.y;

        //Calling functions to set default settings and player images
        setDefaultSettings();
        getPlayerImage();
    }

    public void setDefaultSettings() {
        worldX = rp.TILESIZE * 11;       //Player's pos on the world map
        worldY = rp.TILESIZE * 7;
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
            scaledImg = t.scaleImg(scaledImg, rp.TILESIZE, rp.TILESIZE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scaledImg;
    }

    public void setDefaultStats() {
        worldX = rp.TILESIZE * 11;       //Player's pos on the world map
        worldY = rp.TILESIZE * 7;
        direction = "down";
        hasTea = false;
        hasCoffee = false;
        itemCount = 0;
    }

    public void update() {
        if (updateCheck == true) {
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
                rp.collisionHandlerObj.checkTile(this);

                //For object collisions
                int objIndex = rp.collisionHandlerObj.checkObject(this, true);
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

    }

    public void checkSave() {
        if (hasPhone == true) {
            rp.objArray[0] = null;
            rp.objArray[1] = null;
        }
        if (hasCoffee == true) {
            rp.objArray[5] = null;
        }
        if (hasSugar == true) {
            rp.objArray[6] = null;
        }
        if (hasMilk == true) {
            rp.objArray[4] = null;
        }
        if (hasMug == true) {
            rp.objArray[7] = null;
        }
    }

    public void pickUpObject(int i) {
        if (itemCount == 5) {
            rp.assetHandler.drawCoffee();
        }

        if (i != 999) {
            String objName = rp.objArray[i].name;

            switch (objName) {
                case "Phone":
                    itemCount++;
                    rp.objArray[i] = null;
                    rp.uiObj.showMsg("Phone Acquired!");
                    hasPhone = true;
                    break;
                case "Tea":
                    itemCount = 5;
                    rp.objArray[i] = null;
                    rp.uiObj.showMsg("Tea Drank!");
                    hasTea = true;
                    rp.uiObj.gameFinished = true;
                    break;
                case "Coffee":
                    itemCount++;
                    rp.objArray[i] = null;
                    rp.uiObj.showMsg("Coffee Powder Acquired!");
                    hasCoffee = true;
                    break;
                case "Sugar":
                    itemCount++;
                    rp.objArray[i] = null;
                    rp.uiObj.showMsg("Sugar Acquired!");
                    hasSugar = true;
                    break;
                case "Milk":
                    itemCount++;
                    rp.objArray[i] = null;
                    rp.uiObj.showMsg("Milk Acquired!");
                    hasMilk = true;
                    break;
                case "Mug":
                    itemCount++;
                    rp.objArray[i] = null;
                    rp.uiObj.showMsg("Empty Mug Acquired!");
                    hasMug = true;
                    break;
                case "Door":
                    if (hasPhone == true) {
                        rp.objArray[i] = null;
                        rp.uiObj.showMsg("Door Opened!");
                    } else {
                        rp.uiObj.showMsg("Key item has not been acquired...");
                    }
                    break;
                case "MugWithCoffee":
                    if (itemCount == 5) {
                        rp.objArray[i] = null;
                        rp.uiObj.showMsg("Coffee Acquired!");
                        itemCount = 10;
                        hasCoffee = true;
                        rp.uiObj.gameFinished = true;
                    } else {
                        rp.uiObj.showMsg("Need Coffee level of at least 5 to acquire...");
                    }
                    break;
            }
        }
    }

    public void draw(Graphics2D g2) {

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
        g2.drawImage(image, screenX, screenY, rp.TILESIZE, rp.TILESIZE, null);
    }
}
