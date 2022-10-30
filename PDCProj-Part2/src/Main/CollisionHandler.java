/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import Entity.Entity;

/**
 *
 * @author botor
 */
public class CollisionHandler {

    //Private RPGPanel for this class
    private final RPGPanel rp;

    //Class Constructor
    public CollisionHandler(RPGPanel rp) {
        this.rp = rp;
    }

    //Function for the collision between the user and tiles
    public void checkTile(Entity ent) {
        
        //Variables for the x and y values for entityWorld variables
        int entLeftWorldX = ent.worldX + ent.solidPlayerArea.x;
        int entRightWorldX = ent.worldX + ent.solidPlayerArea.x + ent.solidPlayerArea.width;
        int entTopWorldY = ent.worldY + ent.solidPlayerArea.y;
        int entBottomWorldY = ent.worldY + ent.solidPlayerArea.y + ent.solidPlayerArea.height;

        //Size of the tiles
        int entLeftCol = entLeftWorldX / rp.TILESIZE;
        int entRightCol = entRightWorldX / rp.TILESIZE;
        int entTopRow = entTopWorldY / rp.TILESIZE;
        int entBottomRow = entBottomWorldY / rp.TILESIZE;

        int tileNo1, tileNo2;
        
        //Switch case statements for the direction of the player
        //This switch case determines the collision between the player and tiles
        switch (ent.direction) {
            case "up":
                entTopRow = (entTopWorldY - ent.speed) / rp.TILESIZE;
                tileNo1 = rp.tileManagerObj.mapTileNo[entLeftCol][entTopRow];
                tileNo2 = rp.tileManagerObj.mapTileNo[entRightCol][entTopRow];
                if (rp.tileManagerObj.tileArray[tileNo1].collision == true || rp.tileManagerObj.tileArray[tileNo2].collision == true) {
                    ent.setCollision = true;
                }
                break;
            case "down":
                entBottomRow = (entBottomWorldY + ent.speed) / rp.TILESIZE;
                tileNo1 = rp.tileManagerObj.mapTileNo[entLeftCol][entBottomRow];
                tileNo2 = rp.tileManagerObj.mapTileNo[entRightCol][entBottomRow];
                if (rp.tileManagerObj.tileArray[tileNo1].collision == true || rp.tileManagerObj.tileArray[tileNo2].collision == true) {
                    ent.setCollision = true;
                }
                break;
            case "left":
                entLeftCol = (entLeftWorldX - ent.speed) / rp.TILESIZE;
                tileNo1 = rp.tileManagerObj.mapTileNo[entLeftCol][entTopRow];
                tileNo2 = rp.tileManagerObj.mapTileNo[entLeftCol][entBottomRow];
                if (rp.tileManagerObj.tileArray[tileNo1].collision == true || rp.tileManagerObj.tileArray[tileNo2].collision == true) {
                    ent.setCollision = true;
                }
                break;
            case "right":
                entRightCol = (entRightWorldX + ent.speed) / rp.TILESIZE;
                tileNo1 = rp.tileManagerObj.mapTileNo[entRightCol][entTopRow];
                tileNo2 = rp.tileManagerObj.mapTileNo[entRightCol][entBottomRow];
                if (rp.tileManagerObj.tileArray[tileNo1].collision == true || rp.tileManagerObj.tileArray[tileNo2].collision == true) {
                    ent.setCollision = true;
                }
                break;
        }
    }

    //This function is the same as the function above with the only difference is that
    //it deals with the objects instead of tiles
    //Objects in this program are usually furnitures and items that the user can pick up
    public int checkObject(Entity ent, boolean player) {
        int index = 999;

        for (int i = 0; i < rp.itemArray.length; i++) {
            if (rp.itemArray[i] != null) {
                //Get ent's solid area position
                ent.solidPlayerArea.x = ent.worldX + ent.solidPlayerArea.x;
                ent.solidPlayerArea.y = ent.worldY + ent.solidPlayerArea.y;

                //Get the object's solid area position
                rp.itemArray[i].solidArea.x = rp.itemArray[i].worldX + rp.itemArray[i].solidArea.x;
                rp.itemArray[i].solidArea.y = rp.itemArray[i].worldY + rp.itemArray[i].solidArea.y;

                //Switch case with various statements for the direction the user is facing
                //Each switch case decreases the user's speed if they have setCollision as true
                //and if the player is colliding with it's solid area
                switch (ent.direction) {
                    case "up":
                        ent.solidPlayerArea.y -= ent.speed;
                        if (ent.solidPlayerArea.intersects(rp.itemArray[i].solidArea)) {
                            if (rp.itemArray[i].collision == true) {
                                ent.setCollision = true;
                            }
                            if (player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        ent.solidPlayerArea.y += ent.speed;
                        if (ent.solidPlayerArea.intersects(rp.itemArray[i].solidArea)) {
                            if (rp.itemArray[i].collision == true) {
                                ent.setCollision = true;
                            }
                            if (player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        ent.solidPlayerArea.x -= ent.speed;
                        if (ent.solidPlayerArea.intersects(rp.itemArray[i].solidArea)) {
                            if (rp.itemArray[i].collision == true) {
                                ent.setCollision = true;
                            }
                            if (player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        ent.solidPlayerArea.x += ent.speed;
                        if (ent.solidPlayerArea.intersects(rp.itemArray[i].solidArea)) {
                            if (rp.itemArray[i].collision == true) {
                                ent.setCollision = true;
                            }
                            if (player == true) {
                                index = i;
                            }
                        }
                        break;
                }
                
                //Resetting the object's solid area back to normal
                ent.solidPlayerArea.x = ent.defaultSolidAreaX;
                ent.solidPlayerArea.y = ent.defaultSolidAreaY;
                rp.itemArray[i].solidArea.x = rp.itemArray[i].defaultSolidAreaX;
                rp.itemArray[i].solidArea.y = rp.itemArray[i].defaultSolidAreaY;

            }
        }
        return index;
    }
}
