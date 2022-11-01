/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import Main.RPGPanel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author botor
 */
public class SaveLoad {

    //RPGPanel object for this class
    private final RPGPanel rp;

    //Class cosntructor
    public SaveLoad(RPGPanel rp) {
        this.rp = rp;
    }

    //Save function
    public void save() {
        try {
            //Creating ObjectOutputStream objcet to save player data to a file
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("src/saveData/"+rp.logForm.user+".dat")));

            //Creating DataStorage object to store data in
            DataStorage ds = new DataStorage();
            ds.energyLevel = rp.playerObj.itemCount;
            ds.hasPhone = rp.playerObj.hasPhone;
            ds.hasCoffee = rp.playerObj.hasCoffee;
            ds.hasMilk = rp.playerObj.hasMilk;
            ds.hasSugar = rp.playerObj.hasSugar;
            ds.hasMug = rp.playerObj.hasMug;

            //Calling writeObject function to write data of DataStorage object to save.dat
            oos.writeObject(ds);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(SaveLoad.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SaveLoad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Load function
    public void load() {
        try {
            //Creating ObejctInputStream to read from save.dat
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("src/saveData/"+rp.logForm.user+".dat")));

            //Creating DataStorage object with the data of save.dat
            DataStorage ds = (DataStorage) ois.readObject();

            //Initializing Player class objects with the values of each variables
            rp.playerObj.itemCount = ds.energyLevel;
            rp.playerObj.hasPhone = ds.hasPhone;
            rp.playerObj.hasCoffee = ds.hasCoffee;
            rp.playerObj.hasMilk = ds.hasMilk;
            rp.playerObj.hasSugar = ds.hasSugar;
            rp.playerObj.hasMug = ds.hasMug;

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Load Exception!");
        }
    }
}
