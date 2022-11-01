/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import java.io.Serializable;

/**
 *
 * @author botor
 */

//Class for DataStorage
public class DataStorage implements Serializable {

    //Player Stats
    int energyLevel;
    
    //Boolean variables that checks if player has certain items
    boolean hasPhone;
    boolean hasCoffee;
    boolean hasSugar;
    boolean hasMilk;
    boolean hasMug;
}
