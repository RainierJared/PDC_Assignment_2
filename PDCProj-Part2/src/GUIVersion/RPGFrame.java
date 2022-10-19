/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUIVersion;

import javax.swing.JFrame;

/**
 *
 * @author jaredbotor
 */
public class RPGFrame extends JFrame {

    public RPGFrame() {
        this.setTitle("Coffee Adventure");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        RPGPanel panel = new RPGPanel();
        this.add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        
        panel.startGameThread();
    }
}
