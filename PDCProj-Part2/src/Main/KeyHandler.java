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
    public boolean upPress, downPress, leftPress, rightPress, interactPressed;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public KeyHandler(RPGPanel rp) {
        this.rp = rp;
        this.interactPressed = false;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        //Instruction State
        switch (rp.gameState) {
            case States.INSTRUCTIONSTATE:
                instructionInput(code);
                break;
            case States.TITLESTATE:
                titleInput(code);
                break;
            case States.PLAYSTATE:
                playInput(code);
                break;
            case States.OPTIONSTATE:
                optionState(code);
                break;
            default:
                break;
        }
    }

    private void instructionInput(int code) {
        if (code == KeyEvent.VK_SPACE) {
            rp.gameState = States.TITLESTATE;
        }
    }

    private void playInput(int code) {
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
        if (code == KeyEvent.VK_ESCAPE) {
            rp.gameState = States.OPTIONSTATE;
        }
    }

    private void titleInput(int code) {
        if (code == KeyEvent.VK_UP) {
            rp.uiObj.commandNumber--;
            if (rp.uiObj.commandNumber < 0) {
                rp.uiObj.commandNumber = 2;
            }
        }
        if (code == KeyEvent.VK_DOWN) {
            rp.uiObj.commandNumber++;
            if (rp.uiObj.commandNumber > 2) {
                rp.uiObj.commandNumber = 0;
            }
        }
        if (code == KeyEvent.VK_Z) {
            switch (rp.uiObj.commandNumber) {
                case 0:
                    //For New Game
                    rp.restart();
                    rp.gameState = States.PLAYSTATE;
                    break;
                case 1:
                    //For Loading Save
                    rp.saveLoad.load();
                    rp.playerObj.checkSave();
                    rp.gameState = States.PLAYSTATE;
                    break;
                case 2:
                    //For Exiting
                    System.exit(0);
                    break;
            }
        }
    }

    private void optionState(int code) {
        if (code == KeyEvent.VK_ESCAPE) {
            rp.gameState = States.PLAYSTATE;
        }
        if (code == KeyEvent.VK_Z) {
            interactPressed = true;
            //System.out.println("Interact Key is pressed");
        }

        int maxCmdNum = 0;
        switch (rp.uiObj.subState) {
            case 0:
                maxCmdNum = 3;
                break;
            case 2:
                maxCmdNum = 1;
                break;
        }
        if (code == KeyEvent.VK_UP) {
            rp.uiObj.commandNumber--;
            if (rp.uiObj.commandNumber < 0) {
                rp.uiObj.commandNumber = maxCmdNum;
            }
        }
        if (code == KeyEvent.VK_DOWN) {
            rp.uiObj.commandNumber++;
            if (rp.uiObj.commandNumber > maxCmdNum) {
                rp.uiObj.commandNumber = 0;
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
        if (code == KeyEvent.VK_Z) {
            interactPressed = false;
        }
    }

}
