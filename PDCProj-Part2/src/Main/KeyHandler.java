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

    //Private RPGPanle object for this class
    private final RPGPanel rp;

    //Booleans that indicate which button has been pressed
    public boolean upPress, downPress, leftPress, rightPress, interactPressed;

    //Class Constructor
    public KeyHandler(RPGPanel rp) {
        this.rp = rp;
        this.interactPressed = false;
    }

    //Implemented KeyListener functions
    //keyTyped() function is not neededs
    @Override
    public void keyTyped(KeyEvent e) {
    }

    //keyPressed function for keyboard input
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        //Instruction State
        
        //Switch case for RPGPanel gameState
        //Each case calls the function appropriate to their states
        switch (rp.gameState) {
            
            //For Instruction state
            case States.INSTRUCTIONSTATE:
                instructionInput(code);
                break;
                
            //For Title state
            case States.TITLESTATE:
                titleInput(code);
                break;
                
            //For Play state    
            case States.PLAYSTATE:
                playInput(code);
                break;
                
            //For Option state
            case States.OPTIONSTATE:
                optionState(code);
                break;
            default:
                break;
        }
    }

    //Private function for instruction state
    private void instructionInput(int code) {
        if (code == KeyEvent.VK_SPACE) {
            rp.gameState = States.TITLESTATE;       //Changing gamestate to titlestate
        }
    }

    //Private function for play state
    private void playInput(int code) {
        
        //For directional keys
        //This program uses the arrow keys for movement
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
        
        //For option menu
        //This program uses escape key for the options menu
        if (code == KeyEvent.VK_ESCAPE) {
            rp.gameState = States.OPTIONSTATE;
        }
    }

    //Private function for title state
    private void titleInput(int code) {
        
        //Directional input for scrolling through the titlescreen menu
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
        
        //For the interaction key
        if (code == KeyEvent.VK_Z) {
            //Switch case for the commandNumber variable
            //Dictates where the cursor should be printed and what functions should be run
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

    //Private function for option menu state
    private void optionState(int code) {
        //Button to bring up the options menu
        if (code == KeyEvent.VK_ESCAPE) {
            rp.gameState = States.PLAYSTATE;
        }
        
        //Button for interaction button
        if (code == KeyEvent.VK_Z) {
            interactPressed = true;
        }

        //Switch case that ensures that users can't scroll 
        //past the limit of the options menu
        int maxCommandNo = 0;
        switch (rp.uiObj.subState) {
            //For the options menu
            case 0:
                maxCommandNo = 3;
                break;
            //For the quit menu in the options menu
            case 2:
                maxCommandNo = 1;
                break;
        }
        
        //Inputs for scrolling up and down the options menu
        if (code == KeyEvent.VK_UP) {
            rp.uiObj.commandNumber--;
            if (rp.uiObj.commandNumber < 0) {
                rp.uiObj.commandNumber = maxCommandNo;
            }
        }
        if (code == KeyEvent.VK_DOWN) {
            rp.uiObj.commandNumber++;
            if (rp.uiObj.commandNumber > maxCommandNo) {
                rp.uiObj.commandNumber = 0;
            }
        }
    }

    //Private function implemented from KeyListener
    //This function sets the booleans as false when the button is no longer pressed
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
