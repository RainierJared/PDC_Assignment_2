/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import Object.objDoor;
import Object.objPhone;

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
        rp.sObj[0] = new objPhone();
        rp.sObj[0].worldX = 3 * rp.tileSize;
        rp.sObj[0].worldY = 3 * rp.tileSize;

        rp.sObj[1] = new objDoor();
        rp.sObj[1].worldX = 1 * rp.tileSize;
        rp.sObj[1].worldY = 0 * rp.tileSize;
        
    }
}
