/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import javax.swing.JFrame;

/**
 *
 * @author jaredbotor
 */

//Class that extends JFrame for the RPGPanel
public class RPGFrame extends JFrame {

    //Constructor
    public RPGFrame() {
        this.setTitle("Coffee Adventure");  //Setting title of the JFrame
        this.setResizable(false);   //Making it non-resizeable
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //Making default close operating exit on close
        RPGPanel panel = new RPGPanel();        //Creating new RPGPanel object
        this.add(panel);    //Adding RPGPanel to RPGFrame
        this.pack();        //Packing the JFrame
        this.setLocationRelativeTo(null);       //Making program open on the centre of the screen
        this.setVisible(true);      //Making the JFrame visible
        
        //Calling functions to setup the map and start running the game thread
        panel.setupMap();
        panel.startGameThread();
    }
}
