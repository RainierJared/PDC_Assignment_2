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
    private final RPGPanel rp;
    private final KeyHandler key;
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
        solidPlayerArea = new Rectangle(8, 16, 32, 32);
        defaultSolidAreaX = solidPlayerArea.x;
        defaultSolidAreaY = solidPlayerArea.y;

        //Calling functions to set default settings and player images
        setDefaultSettings();
        setPlayerImage();
    }

    //Function that sets the player's position in the world, their speed, and what position they initally start in
    private void setDefaultSettings() {
        worldX = rp.TILESIZE * 11;       //Player's pos on the world map
        worldY = rp.TILESIZE * 7;
        speed = 4;
        direction = "down";
    }

    //Function that assigns the BufferedImage objects from Entity.java with the correct image according to the frame that
    //the player is in
    private void setPlayerImage() {

        //This function also uses the same setup() function as the objects with the only difference being the images used
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

    //This function deals with pre-scaling the images to improve the performance of the game
    private BufferedImage setup(String imgName) {
        //Creating Tools and BufferedImage objects
        Tools t = new Tools();
        BufferedImage scaledImg = null;
        try {
            //Initializing the BufferedImage's image
            scaledImg = ImageIO.read(getClass().getResourceAsStream("/res/player/" + imgName + ".png"));
            scaledImg = t.scaleImg(scaledImg, rp.TILESIZE, rp.TILESIZE);        //Scaling the BufferedImages
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scaledImg;
    }

    //Function for setting the player's position sand stats when they choose 'New Game' on the title screen
    public void setNewGameStats() {
        worldX = rp.TILESIZE * 11;       //Player's pos on the world map
        worldY = rp.TILESIZE * 7;
        direction = "down";
        hasTea = false;
        hasCoffee = false;
        itemCount = 0;
    }

    //Function that updates the player's sprites based on his direction
    public void update() {
        //Checks if update() is being called (created for test calls)
        if (updateCheck == true) {
            
            //If statement only works while a key is being pressed
            if (key.upPress || key.downPress || key.leftPress || key.rightPress) {
                
                //Checks if a direction key is being pressed
                //If it's being pressed then set the direction depending on the key pressed
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
                setCollision = false;
                rp.collisionHandlerObj.checkTile(this);

                //For object collisions
                int objIndex = rp.collisionHandlerObj.checkObject(this, true);
                pickUpObject(objIndex);

                //If Player doesn't collide with anything, they can move
                if (setCollision == false) {
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
                
                //Sprite coutner for sprite animation
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

    //Function that checks if any one of the boolean variables above are true
    //If any of them are true, then they're set as null and are not drawn on the next load
    public void checkSave() {
        if (hasPhone == true) {
            rp.itemArray[0] = null;
            rp.itemArray[1] = null;
        }
        if (hasCoffee == true) {
            rp.itemArray[4] = null;
        }
        if (hasSugar == true) {
            rp.itemArray[5] = null;
        }
        if (hasMilk == true) {
            rp.itemArray[3] = null;
        }
        if (hasMug == true) {
            rp.itemArray[6] = null;
        }
    }

    //Function that occurs whenever the player collides with any items that can be picked up
    public void pickUpObject(int i) {
        
        //Checks if itemCount == 5
        if (itemCount == 5) {
            //If it is, then call the function to draw the mug with coffee
            rp.itemHandler.drawCoffee();
        }

        if (i != 999) {
            String objName = rp.itemArray[i].name;

            //Switch case that checks the collided item's name
            //This function firstly increments the itemCount variable by one --
            //sets it as 5 or 10 depending on the ending that the user chose -- and sets the
            //element in the itemArray as null (removing the item) prints the message.
            //Afterwards, it sets one of the few variables as true.
            //If the player doesn't meet a certain criteria, then a message will print stating the problem
            switch (objName) {
                case "Phone":
                    itemCount++;
                    rp.itemArray[i] = null;
                    rp.uiObj.showMsg("Phone Acquired!");
                    hasPhone = true;
                    break;
                case "Tea":
                    itemCount = 5;
                    rp.itemArray[i] = null;
                    rp.uiObj.showMsg("Tea Drank!");
                    hasTea = true;
                    rp.uiObj.gameFinished = true;
                    break;
                case "Coffee":
                    itemCount++;
                    rp.itemArray[i] = null;
                    rp.uiObj.showMsg("Coffee Powder Acquired!");
                    hasCoffee = true;
                    break;
                case "Sugar":
                    itemCount++;
                    rp.itemArray[i] = null;
                    rp.uiObj.showMsg("Sugar Acquired!");
                    hasSugar = true;
                    break;
                case "Milk":
                    itemCount++;
                    rp.itemArray[i] = null;
                    rp.uiObj.showMsg("Milk Acquired!");
                    hasMilk = true;
                    break;
                case "Mug":
                    itemCount++;
                    rp.itemArray[i] = null;
                    rp.uiObj.showMsg("Empty Mug Acquired!");
                    hasMug = true;
                    break;
                case "Door":
                    if (hasPhone == true) {
                        rp.itemArray[i] = null;
                        rp.uiObj.showMsg("Door Opened!");
                    } else {
                        rp.uiObj.showMsg("Key item has not been acquired...");
                    }
                    break;
                case "MugWithCoffee":
                    if (itemCount == 5) {
                        rp.itemArray[i] = null;
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

    //Draw function for the sprite animation when the player moves
    public void draw(Graphics2D g2) {
        image = null;

        //Switch case statement that checks the player's direction
        //Each direction has two if statements that checks the value of spriteNum
        //If spriteNum == 1, then first frame of the walking animation is drawn
        //If spriteNum == 2, then the second frame of the walking animation is drawn
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
        //Draws the image
        g2.drawImage(image, screenX, screenY, rp.TILESIZE, rp.TILESIZE, null);
    }
}
