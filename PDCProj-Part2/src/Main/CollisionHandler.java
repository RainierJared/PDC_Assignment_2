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

    RPGPanel rp;

    public CollisionHandler(RPGPanel rp) {
        this.rp = rp;
    }

    public void checkTile(Entity ent) {
        int entLeftWorldX = ent.worldX + ent.solidArea.x;
        int entRightWorldX = ent.worldX + ent.solidArea.x + ent.solidArea.width;
        int entTopWorldY = ent.worldY + ent.solidArea.y;
        int entBottomWorldY = ent.worldY + ent.solidArea.y + ent.solidArea.height;

        int entLeftCol = entLeftWorldX / rp.TILESIZE;
        int entRightCol = entRightWorldX / rp.TILESIZE;
        int entTopRow = entTopWorldY / rp.TILESIZE;
        int entBottomRow = entBottomWorldY / rp.TILESIZE;

        int tileNo1, tileNo2;

        switch (ent.direction) {
            case "up":
                entTopRow = (entTopWorldY - ent.speed) / rp.TILESIZE;
                tileNo1 = rp.tileManagerObj.mapTileNo[entLeftCol][entTopRow];
                tileNo2 = rp.tileManagerObj.mapTileNo[entRightCol][entTopRow];
                if (rp.tileManagerObj.tile[tileNo1].collision == true || rp.tileManagerObj.tile[tileNo2].collision == true) {
                    ent.entCollision = true;
                }
                break;
            case "down":
                entBottomRow = (entBottomWorldY + ent.speed) / rp.TILESIZE;
                tileNo1 = rp.tileManagerObj.mapTileNo[entLeftCol][entBottomRow];
                tileNo2 = rp.tileManagerObj.mapTileNo[entRightCol][entBottomRow];
                if (rp.tileManagerObj.tile[tileNo1].collision == true || rp.tileManagerObj.tile[tileNo2].collision == true) {
                    ent.entCollision = true;
                }
                break;
            case "left":
                entLeftCol = (entLeftWorldX - ent.speed) / rp.TILESIZE;
                tileNo1 = rp.tileManagerObj.mapTileNo[entLeftCol][entTopRow];
                tileNo2 = rp.tileManagerObj.mapTileNo[entLeftCol][entBottomRow];
                if (rp.tileManagerObj.tile[tileNo1].collision == true || rp.tileManagerObj.tile[tileNo2].collision == true) {
                    ent.entCollision = true;
                }
                break;
            case "right":
                entRightCol = (entRightWorldX + ent.speed) / rp.TILESIZE;
                tileNo1 = rp.tileManagerObj.mapTileNo[entRightCol][entTopRow];
                tileNo2 = rp.tileManagerObj.mapTileNo[entRightCol][entBottomRow];
                if (rp.tileManagerObj.tile[tileNo1].collision == true || rp.tileManagerObj.tile[tileNo2].collision == true) {
                    ent.entCollision = true;
                }
                break;
        }
    }

    //For Object
    public int checkObject(Entity ent, boolean player) {
        int index = 999;

        for (int i = 0; i < rp.objArray.length; i++) {
            if (rp.objArray[i] != null) {
                //Get ent's solid area position
                ent.solidArea.x = ent.worldX + ent.solidArea.x;
                ent.solidArea.y = ent.worldY + ent.solidArea.y;

                //Get the object's solid area position
                rp.objArray[i].solidArea.x = rp.objArray[i].worldX + rp.objArray[i].solidArea.x;
                rp.objArray[i].solidArea.y = rp.objArray[i].worldY + rp.objArray[i].solidArea.y;

                switch (ent.direction) {
                    case "up":
                        ent.solidArea.y -= ent.speed;
                        if (ent.solidArea.intersects(rp.objArray[i].solidArea)) {
                            if (rp.objArray[i].collision == true) {
                                ent.entCollision = true;
                            }
                            if (player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        ent.solidArea.y += ent.speed;
                        if (ent.solidArea.intersects(rp.objArray[i].solidArea)) {
                            if (rp.objArray[i].collision == true) {
                                ent.entCollision = true;
                            }
                            if (player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        ent.solidArea.x -= ent.speed;
                        if (ent.solidArea.intersects(rp.objArray[i].solidArea)) {
                            if (rp.objArray[i].collision == true) {
                                ent.entCollision = true;
                            }
                            if (player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        ent.solidArea.x += ent.speed;
                        if (ent.solidArea.intersects(rp.objArray[i].solidArea)) {
                            if (rp.objArray[i].collision == true) {
                                ent.entCollision = true;
                            }
                            if (player == true) {
                                index = i;
                            }
                        }
                        break;
                }
                ent.solidArea.x = ent.defaultSolidAreaX;
                ent.solidArea.y = ent.defaultSolidAreaY;
                rp.objArray[i].solidArea.x = rp.objArray[i].defaultSolidAreaX;
                rp.objArray[i].solidArea.y = rp.objArray[i].defaultSolidAreaY;

            }
        }
        return index;
    }
}
