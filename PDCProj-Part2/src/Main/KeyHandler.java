/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author jaredbotor
 */
public class KeyHandler implements KeyListener {

    RPGPanel rp;
    public boolean upPress, downPress, leftPress, rightPress;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public KeyHandler(RPGPanel rp) {
        this.rp = rp;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_UP) {
            upPress = true;
        }
        if (code == KeyEvent.VK_DOWN) {
            downPress = true;
        }
        if (code == KeyEvent.VK_LEFT) {
            leftPress = true;
        }
        if (code == KeyEvent.VK_RIGHT) {
            rightPress = true;
        }
        if (code == KeyEvent.VK_P) {
            if (rp.gameState == rp.playState) {
                rp.gameState = rp.pauseState;
            } else if(rp.gameState == rp.pauseState) {
                rp.gameState = rp.playState;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        if (code == KeyEvent.VK_UP) {
            upPress = false;
        }
        if (code == KeyEvent.VK_DOWN) {
            downPress = false;
        }
        if (code == KeyEvent.VK_LEFT) {
            leftPress = false;
        }
        if (code == KeyEvent.VK_RIGHT) {
            rightPress = false;
        }
    }

}
