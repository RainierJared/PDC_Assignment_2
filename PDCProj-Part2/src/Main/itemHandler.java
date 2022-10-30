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

//Class that deals with the items/objects in the program
public class ItemHandler {

    //Private RPGPanel for this class
    private final RPGPanel rp;

    //Class Constructor
    public ItemHandler(RPGPanel rp) {
        this.rp = rp;
    }

    //Function that sets the object once the game is opened
    public void setObject() {
        
        //This function works by assigning elements of the itemArray with various objects in the Object package
        //This function also sets the world coordinates/position of the objects in the world
            rp.itemArray[0] = new objPhone(rp);
            rp.itemArray[0].worldX = 10 * rp.TILESIZE;
            rp.itemArray[0].worldY = 7 * rp.TILESIZE;

            rp.itemArray[1] = new objDoor(rp);
            rp.itemArray[1].worldX = 13 * rp.TILESIZE;
            rp.itemArray[1].worldY = 5 * rp.TILESIZE;
            
            rp.itemArray[2] = new objTea(rp);
            rp.itemArray[2].worldX = 8 * rp.TILESIZE;
            rp.itemArray[2].worldY = 7 * rp.TILESIZE;

            rp.itemArray[3] = new objMilk(rp);
            rp.itemArray[3].worldX = 5 * rp.TILESIZE;
            rp.itemArray[3].worldY = 1 * rp.TILESIZE;

            rp.itemArray[4] = new objCoffee(rp);
            rp.itemArray[4].worldX = 7 * rp.TILESIZE;
            rp.itemArray[4].worldY = 1 * rp.TILESIZE;

            rp.itemArray[5] = new objSugar(rp);
            rp.itemArray[5].worldX = 9 * rp.TILESIZE;
            rp.itemArray[5].worldY = 1 * rp.TILESIZE;

            rp.itemArray[6] = new objMug(rp);
            rp.itemArray[6].worldX = 11 * rp.TILESIZE;
            rp.itemArray[6].worldY = 1 * rp.TILESIZE;
    }

    //This function draws a mug with coffee object only once a certain criteria has been met
    public void drawCoffee() {
        rp.itemArray[7] = new objMugWithCoffee(rp);
        rp.itemArray[7].worldX = 8 * rp.TILESIZE;
        rp.itemArray[7].worldY = 3 * rp.TILESIZE;
    }
}
