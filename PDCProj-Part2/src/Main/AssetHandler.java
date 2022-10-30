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
            rp.objArray[0] = new objPhone(rp);
            rp.objArray[0].worldX = 10 * rp.TILESIZE;
            rp.objArray[0].worldY = 7 * rp.TILESIZE;

            rp.objArray[1] = new objDoor(rp);
            rp.objArray[1].worldX = 13 * rp.TILESIZE;
            rp.objArray[1].worldY = 5 * rp.TILESIZE;

            rp.objArray[2] = new objDoor(rp);
            rp.objArray[2].worldX = 1 * rp.TILESIZE;
            rp.objArray[2].worldY = 0 * rp.TILESIZE;

            rp.objArray[3] = new objTea(rp);
            rp.objArray[3].worldX = 8 * rp.TILESIZE;
            rp.objArray[3].worldY = 7 * rp.TILESIZE;

            rp.objArray[4] = new objMilk(rp);
            rp.objArray[4].worldX = 5 * rp.TILESIZE;
            rp.objArray[4].worldY = 1 * rp.TILESIZE;

            rp.objArray[5] = new objCoffee(rp);
            rp.objArray[5].worldX = 7 * rp.TILESIZE;
            rp.objArray[5].worldY = 1 * rp.TILESIZE;

            rp.objArray[6] = new objSugar(rp);
            rp.objArray[6].worldX = 9 * rp.TILESIZE;
            rp.objArray[6].worldY = 1 * rp.TILESIZE;

            rp.objArray[7] = new objMug(rp);
            rp.objArray[7].worldX = 11 * rp.TILESIZE;
            rp.objArray[7].worldY = 1 * rp.TILESIZE;
    }

    public void drawCoffee() {
        rp.objArray[8] = new objMugWithCoffee(rp);
        rp.objArray[8].worldX = 8 * rp.TILESIZE;
        rp.objArray[8].worldY = 3 * rp.TILESIZE;
    }
}
