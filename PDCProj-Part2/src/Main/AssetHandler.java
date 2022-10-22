/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import Object.objCoffee;
import Object.objDoor;
import Object.objMilk;
import Object.objMug;
import Object.objMugWithCoffee;
import Object.objPhone;
import Object.objSugar;
import Object.objTea;

/**
 *
 * @author botor
 */
public class AssetHandler {

    RPGPanel rp;

    public AssetHandler(RPGPanel rp) {
        this.rp = rp;
    }

    public void setObject() {
        rp.sObj[0] = new objPhone(rp);
        rp.sObj[0].worldX = 10 * rp.tileSize;
        rp.sObj[0].worldY = 7 * rp.tileSize;

        rp.sObj[1] = new objDoor(rp);
        rp.sObj[1].worldX = 13 * rp.tileSize;
        rp.sObj[1].worldY = 5 * rp.tileSize;

        rp.sObj[2] = new objDoor(rp);
        rp.sObj[2].worldX = 1 * rp.tileSize;
        rp.sObj[2].worldY = 0 * rp.tileSize;

        rp.sObj[3] = new objTea(rp);
        rp.sObj[3].worldX = 8 * rp.tileSize;
        rp.sObj[3].worldY = 7 * rp.tileSize;

        rp.sObj[4] = new objMilk(rp);
        rp.sObj[4].worldX = 5 * rp.tileSize;
        rp.sObj[4].worldY = 1 * rp.tileSize;

        rp.sObj[5] = new objCoffee(rp);
        rp.sObj[5].worldX = 7 * rp.tileSize;
        rp.sObj[5].worldY = 1 * rp.tileSize;

        rp.sObj[6] = new objSugar(rp);
        rp.sObj[6].worldX = 9 * rp.tileSize;
        rp.sObj[6].worldY = 1 * rp.tileSize;

        rp.sObj[7] = new objMug(rp);
        rp.sObj[7].worldX = 11 * rp.tileSize;
        rp.sObj[7].worldY = 1 * rp.tileSize;

    }
    
    public void drawCoffee() {
        rp.sObj[8] = new objMugWithCoffee(rp);
        rp.sObj[8].worldX = 8 * rp.tileSize;
        rp.sObj[8].worldY = 3 * rp.tileSize;
    }
}
