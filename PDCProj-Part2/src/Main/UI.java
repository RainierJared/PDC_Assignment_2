/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import Object.objMugWithCoffee;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author botor
 */
public class UI {

    //Private
    //--------------
    //Objects
    private final RPGPanel rp;
    private Graphics2D g2;
    private final Tools t;
    private final BufferedImage itemImage;

    //Booleans
    private boolean messageOn = false;

    //String
    private String message = "";

    //Integers
    private int msgCounter = 0;

    //Public
    //--------------
    //Integer
    public int commandNumber = 0;
    public int subState = 0;

    //Boolean
    public boolean gameFinished = false;

    //Default constructor that takes in an RPGPanel object
    public UI(RPGPanel rp) {
        this.rp = rp;
        this.t = new Tools();
        objMugWithCoffee item = new objMugWithCoffee(rp);
        itemImage = item.image;

    }

    //Function for showing the congratulation message
    public void showMsg(String text) {
        message = text;
        messageOn = true;
    }

    //The draw function of UI
    public void draw(Graphics2D g2) {
        this.g2 = g2;   //Initializing the Graphics2D variable
        //Play State

        switch (rp.gameState) {
            case States.PLAYSTATE:
                drawPlayScreen();
                break;
            case States.TITLESTATE:
                drawTitleScreen();
                break;
            case States.INSTRUCTIONSTATE:
                drawInstructionScreen();
                break;
            case States.OPTIONSTATE:
                drawOptionScreen();
                break;
            default:
                drawTitleScreen();
                break;
        }
    }

    //Private function for drawing the option screen
    private void drawOptionScreen() {
        //Setting the font's color, face, and size
        g2.setColor(Color.white);
        g2.setFont(new Font("Arial", Font.PLAIN, 20));

        //Sub Window
        //Creating the size of the window
        int frameX = rp.TILESIZE * 4;
        int frameY = rp.TILESIZE;
        int frameWidth = rp.TILESIZE * 8;
        int frameHeight = rp.TILESIZE * 10;

        //Drawing the options window
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        //switch case for substate which dictates what page the user is on in options
        switch (subState) {
            case 0:
                optionsTop(frameX, frameY); //Draws the base option screen
                break;
            case 1:
                optionsControl(frameX, frameY); //Draws the control screen
                break;
            case 2:
                optionsQuit(frameX, frameY);    //Draws the quit screen
                break;
            case 3:
                optionSave(frameX, frameY); //Draws the save screen
                break;
        }
        rp.keyHandlerObj.interactPressed = false;   //Setting the boolean for interactPressed to false
    }

    //Function to print the cursor beside each option relative to the value of commandNumber
    private void cursor(int inCommandNo, int subStateValue, int outCommandNo, int textX, int textY) {
        if (commandNumber == inCommandNo) {
            g2.drawString(">", textX - 25, textY);
            //
            if (rp.keyHandlerObj.interactPressed == true) {
                subState = subStateValue;
                commandNumber = outCommandNo;
            }
        }
    }

    //Another version of the function that prints the cursor beside each option for the titlescreen
    //This version only takes in the commandNumber and x and y position of the cursor
    private void cursor(int inCommandNumber, int x, int y) {
        if (commandNumber == inCommandNumber) {
            g2.drawString(">", x - rp.TILESIZE, y);
        }
    }

    //Function for the base option screen
    //All the optionsX functions are all identical; the only difference is the message that it outputs, and the amount of options the user can choose
    private void optionsTop(int frameX, int frameY) {

        //Creating text coordinate variables
        int textX;
        int textY;

        //Title
        String text = "OPTIONS";
        textX = t.findCenter(g2, rp, text);
        textY = frameY + rp.TILESIZE;
        g2.drawString(text, textX, textY);

        //Save Game
        textX = frameX + rp.TILESIZE;
        textY += rp.TILESIZE * 2;
        g2.drawString("SAVE GAME", textX, textY);
        cursor(0, 3, 0, textX, textY);      //Calling the function to place the position according to the the value of commandNumber

        //Controls
        textY += rp.TILESIZE;
        g2.drawString("CONTROLS", textX, textY);
        cursor(1, 1, 0, textX, textY);

        //Exit Game
        textY += rp.TILESIZE;
        g2.drawString("EXIT GAME", textX, textY);
        cursor(2, 2, 0, textX, textY);

        //Back
        textY += rp.TILESIZE * 4;
        g2.drawString("BACK", textX, textY);
        //This is the only unique option in the option screen as it uses rp.gameState rather than subState
        if (commandNumber == 3) {
            g2.drawString(">", textX - 25, textY);
            //
            if (rp.keyHandlerObj.interactPressed == true) {
                rp.gameState = States.PLAYSTATE;
                commandNumber = 0;
            }
        }
    }

    //Function for the save option in the options menu
    private void optionSave(int frameX, int frameY) {
        int textX;
        int textY;

        //Calling Save function
        rp.saveLoad.save();

        //Title
        String text = "SAVE SUCCESSFUL";
        textX = t.findCenter(g2, rp, text);
        textY = frameY + rp.TILESIZE;
        g2.drawString(text, textX, textY);

        //Subtext
        String line = "Save was successful!";
        textX = frameX + rp.TILESIZE;
        textY = frameY + rp.TILESIZE * 3;
        g2.drawString(line, textX, textY);

        //Back
        textX = frameX + rp.TILESIZE;
        textY = frameY + rp.TILESIZE * 9;
        g2.drawString("BACK", textX, textY);
        cursor(0, 0, 0, textX, textY);
    }

    //Function the controls option in the options menu
    private void optionsControl(int frameX, int frameY) {
        int textX;
        int textY;

        //Title
        String text = "CONTROLS";
        textX = t.findCenter(g2, rp, text);
        textY = frameY + rp.TILESIZE;
        g2.drawString(text, textX, textY);

        //Subtext
        textX = frameX + rp.TILESIZE;
        textY += rp.TILESIZE;
        g2.drawString("MOVEMENT", textX, textY);
        textY += rp.TILESIZE;
        g2.drawString("OPTIONS", textX, textY);
        textY += rp.TILESIZE;
        g2.drawString("INTERACT", textX, textY);

        //Keys
        textX = frameX + rp.TILESIZE * 4;
        textY = frameY + rp.TILESIZE * 2;
        g2.drawString("ARROW KEYS", textX, textY);
        textY += rp.TILESIZE;
        g2.drawString("ESC", textX, textY);
        textY += rp.TILESIZE;
        g2.drawString("Z", textX, textY);

        //Back
        textX = frameX + rp.TILESIZE;
        textY = frameY + rp.TILESIZE * 9;
        g2.drawString("BACK", textX, textY);
        cursor(0, 0, 0, textX, textY);
    }

    //Function for the quit option of the options screen
    private void optionsQuit(int frameX, int frameY) {

        //Title
        String text = "WARNING";
        int textX = t.findCenter(g2, rp, text);
        int textY = frameY + rp.TILESIZE;
        g2.drawString(text, textX, textY);

        //Subtext
        String dialogue = "Are you sure you want to /nquit to the title scren?";
        textX = frameX + rp.TILESIZE;
        textY = frameY + rp.TILESIZE * 3;
        for (String line : dialogue.split("/n")) {
            g2.drawString(line, textX, textY);
            textY += 40;
        }

        //Yes option
        text = "YES";
        textX = t.findCenter(g2, rp, text);
        textY += rp.TILESIZE * 3;
        g2.drawString(text, textX, textY);
        //Another unique version of the cursor function as it deals with gameState rather than subState
        if (commandNumber == 0) {
            g2.drawString(">", textX - 25, textY);
            if (rp.keyHandlerObj.interactPressed == true) {
                subState = 0;
                rp.gameState = States.TITLESTATE;
            }
        }

        //No option
        text = "NO";
        textX = t.findCenter(g2, rp, text);
        textY += rp.TILESIZE;
        g2.drawString(text, textX, textY);
        cursor(1, 0, 2, textX, textY);
    }

    //Function that draws the instruction screen
    //This is the first screen that the user is met upon loading the game
    private void drawInstructionScreen() {
        //Instructions
        g2.setFont(new Font("Arial", Font.PLAIN, 70));
        g2.setColor(Color.white);

        String text = "INSTRUCTIONS";
        int x = t.findCenter(g2, rp, text);
        int y = rp.TILESIZE * 3;
        g2.drawString(text, x, y);

        g2.setFont(new Font("Arial", Font.PLAIN, 20));
        g2.setColor(Color.white);

        text = "Use Arrow Keys to move";
        x = t.findCenter(g2, rp, text);
        y += rp.TILESIZE * 2;
        g2.drawString(text, x, y);

        text = "Z to interact";
        x = t.findCenter(g2, rp, text);
        y += rp.TILESIZE;
        g2.drawString(text, x, y);

        text = "ESCAPE to go to options menu";
        x = t.findCenter(g2, rp, text);
        y += rp.TILESIZE;
        g2.drawString(text, x, y);

        text = "Press Space to continue";
        x = t.findCenter(g2, rp, text);
        y += rp.TILESIZE;
        g2.drawString(text, x, y);

    }

    //Function that draws the play screen of the game
    //This function also features a check to see if the user has finished the game
    private void drawPlayScreen() {
        if (gameFinished == true) {
            //An if statement that checks if the user went for the coffee or tea route
            if (rp.playerObj.hasCoffee == true) {
                endingScreen(States.COFFEE_ENDING, g2);
            } else if (rp.playerObj.hasTea == true) {
                endingScreen(States.TEA_ENDING, g2);
            }
            rp.gameThread = null;

        } else {
            //Otherwise the function simply draws the UI
            drawUI(g2);
        }
    }

    //Function that draws the titlescreen
    public void drawTitleScreen() {

        //Font face and color
        g2.setFont(new Font("Arial", Font.PLAIN, 70));
        g2.setColor(Color.white);

        //Title Name
        String text = "Coffee Adventure";
        int x = t.findCenter(g2, rp, text);
        int y = rp.TILESIZE * 3;
        g2.drawString(text, x, y);

        //Character that appears on the titlescreen
        x = rp.SCREENWIDTH / 2 - (rp.TILESIZE * 2) / 2;
        y += rp.TILESIZE * 2;
        g2.drawImage(rp.playerObj.idle, x, y, rp.TILESIZE * 2, rp.TILESIZE * 2, null);

        //Menu
        g2.setFont(new Font("Arial", Font.PLAIN, 30));
        g2.setColor(Color.white);

        text = "NEW GAME";
        x = t.findCenter(g2, rp, text);
        y += rp.TILESIZE * 4;
        g2.drawString(text, x, y);
        //Calling an alternative version of the cursor function for the option screen
        cursor(0, x, y);

        text = "LOAD GAME";
        x = t.findCenter(g2, rp, text);
        y += rp.TILESIZE;
        g2.drawString(text, x, y);
        cursor(1, x, y);

        text = "QUIT";
        x = t.findCenter(g2, rp, text);
        y += rp.TILESIZE;
        g2.drawString(text, x, y);
        cursor(2, x, y);
    }

    //Function that draws the UI of the game
    private void drawUI(Graphics2D g2) {

        //Setting the Font face, color, and size
        g2.setFont(new Font("Arial", Font.PLAIN, 40));
        g2.setColor(Color.white);

        //Drawing the image for the counter on the top left (acts as the point system of the game)
        g2.drawImage(itemImage, rp.TILESIZE / 2, rp.TILESIZE / 2, rp.TILESIZE, rp.TILESIZE, null);
        g2.drawString("= " + rp.playerObj.itemCount, 74, 65);

        //Message that draws for every item that the user gets
        //This message prints below the point system on the top left corner
        if (messageOn == true) {
            g2.setFont(g2.getFont().deriveFont(30F));
            g2.drawString(message, rp.TILESIZE / 2, rp.TILESIZE * 2);

            msgCounter++;

            //If statement that removes the message and 90 units of time
            if (msgCounter > 90) {
                msgCounter = 0;
                messageOn = false;
            }
        }
    }

    //Function that draws the ending screen of the game depending on which route the user took
    private void endingScreen(int ending, Graphics2D g2) {
        //Creating a String variable and setting it's 
        //Font face, color, and size
        g2.setFont(new Font("Arial", Font.BOLD, 40));
        g2.setColor(Color.white);
        String text = "";
        
        //Coffee Ending
        if (ending == States.COFFEE_ENDING) {
            g2.setFont(new Font("Arial", Font.BOLD, 40));
            g2.setColor(Color.white);

            text = "YOU DRANK COFFEE!";
            int textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            g2.drawString(text, rp.SCREENWIDTH / 2 - textLength / 2, rp.SCREENHEIGHT / 2 - (rp.TILESIZE * 3));

            g2.setFont(new Font("Arial", Font.BOLD, 50));
            g2.setColor(Color.yellow);

            text = "CONGRATULATIONS!";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();

            g2.drawString(text, rp.SCREENWIDTH / 2 - textLength / 2, rp.SCREENHEIGHT / 2 + (rp.TILESIZE * 2));

            g2.setFont(new Font("Arial", Font.BOLD, 30));
            g2.setColor(Color.white);

            text = "Final Score: " + rp.playerObj.itemCount;
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            g2.drawString(text, rp.SCREENWIDTH / 2 - textLength / 2, rp.SCREENHEIGHT / 2 + (rp.TILESIZE * 4));
        } 
        //Tea Ending
        else if (ending == States.TEA_ENDING) {
            //Printing the Header's message
            text = "YOU DRANK TEA!";
            int textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            g2.drawString(text, rp.SCREENWIDTH / 2 - textLength / 2, rp.SCREENHEIGHT / 2 - (rp.TILESIZE * 3));

            //Sub-header font's face, color, and size
            g2.setFont(new Font("Arial", Font.BOLD, 50));
            g2.setColor(Color.yellow);

            //Printing the sub-header's message
            text = "COULD BE BETTER";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            g2.drawString(text, rp.SCREENWIDTH / 2 - textLength / 2, rp.SCREENHEIGHT / 2 + (rp.TILESIZE * 2));

            //Setting the font face, color, and size of the total points
            g2.setFont(new Font("Arial", Font.BOLD, 30));
            g2.setColor(Color.white);

            //Prints the user's final score
            text = "Final Score: " + rp.playerObj.itemCount;
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            g2.drawString(text, rp.SCREENWIDTH / 2 - textLength / 2, rp.SCREENHEIGHT / 2 + (rp.TILESIZE * 4));
        }
    }

    //Drawing the window for the options menu
    private void drawSubWindow(int x, int y, int width, int height) {
        Color c = new Color(0, 0, 0);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 5);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);

    }
}
