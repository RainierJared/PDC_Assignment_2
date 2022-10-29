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
    public int cmdNo = 0;
    public int subState = 0;

    //Boolean
    public boolean gameFinished = false;

    public UI(RPGPanel rp) {
        this.rp = rp;
        this.t = new Tools();
        objMugWithCoffee item = new objMugWithCoffee(rp);
        itemImage = item.image;

    }

    public void showMsg(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;
        //Play State
        if (rp.gameState == States.PLAYSTATE) {
            drawPlayScreen();
        }
        //Title State
        if (rp.gameState == States.TITLESTATE) {
            drawTitleScreen();
        }
        //Instruction State
        if (rp.gameState == States.INSTRUCTIONSTATE) {
            drawInstructionScreen();
        }
        //Option State
        if (rp.gameState == States.OPTIONSTATE) {
            drawOptionScreen();
        }
    }

    private void drawOptionScreen() {
        g2.setColor(Color.white);
        g2.setFont(new Font("Arial", Font.PLAIN, 20));

        //Sub Window
        int frameX = rp.tileSize * 4;
        int frameY = rp.tileSize;
        int frameWidth = rp.tileSize * 8;
        int frameHeight = rp.tileSize * 10;

        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        switch (subState) {
            case 0:
                optionsTop(frameX, frameY);
                break;
            case 1:
                optionsControl(frameX, frameY);
                break;
            case 2:
                optionsQuit(frameX, frameY);
                break;
        }
        rp.key.interactPressed = false;
    }

    private void optionsTop(int frameX, int frameY) {

        int textX;
        int textY;

        //Title
        String text = "OPTIONS";
        textX = t.findCenter(g2, rp, text);
        textY = frameY + rp.tileSize;
        g2.drawString(text, textX, textY);

        //Save Game
        textX = frameX + rp.tileSize;
        textY += rp.tileSize * 2;
        g2.drawString("SAVE GAME", textX, textY);
        if (cmdNo == 0) {
            g2.drawString(">", textX - 25, textY);
        }

        //Controls
        textY += rp.tileSize;
        g2.drawString("CONTROLS", textX, textY);
        if (cmdNo == 1) {
            g2.drawString(">", textX - 25, textY);
            if (rp.key.interactPressed == true) {
                subState = 1;
                cmdNo = 0;
            }
        }
        //Exit Game
        textY += rp.tileSize;
        g2.drawString("EXIT GAME", textX, textY);
        if (cmdNo == 2) {
            g2.drawString(">", textX - 25, textY);
            if (rp.key.interactPressed == true) {
                subState = 2;
                cmdNo = 0;
            }
        }

        //Back
        textY += rp.tileSize * 4;
        g2.drawString("BACK", textX, textY);
        if (cmdNo == 3) {
            g2.drawString(">", textX - 25, textY);
            if (rp.key.interactPressed == true) {
                rp.gameState = States.PLAYSTATE;
                cmdNo = 0;
            }
        }
    }

    private void optionsControl(int frameX, int frameY) {
        int textX;
        int textY;

        //Title
        String text = "CONTROLS";
        textX = t.findCenter(g2, rp, text);
        textY = frameY + rp.tileSize;
        g2.drawString(text, textX, textY);

        //Subtext
        textX = frameX + rp.tileSize;
        textY += rp.tileSize;
        g2.drawString("MOVEMENT", textX, textY);
        textY += rp.tileSize;
        g2.drawString("OPTIONS", textX, textY);
        textY += rp.tileSize;
        g2.drawString("INTERACT", textX, textY);

        //Keys
        textX = frameX + rp.tileSize * 4;
        textY = frameY + rp.tileSize * 2;
        g2.drawString("ARROW KEYS", textX, textY);
        textY += rp.tileSize;
        g2.drawString("ESC", textX, textY);
        textY += rp.tileSize;
        g2.drawString("Z", textX, textY);

        //Back
        textX = frameX + rp.tileSize;
        textY = frameY + rp.tileSize * 9;
        g2.drawString("BACK", textX, textY);
        if (cmdNo == 0) {
            g2.drawString(">", textX - 25, textY);
            if (rp.key.interactPressed == true) {
                subState = 0;
            }
        }
    }

    private void optionsQuit(int frameX, int frameY) {

        //Title
        String text = "WARNING";
        int textX = t.findCenter(g2, rp, text);
        int textY = frameY + rp.tileSize;
        g2.drawString(text, textX, textY);

        //Subtext
        String dialogue = "Are you sure you want to /nquit to the title scren?";
        textX = frameX + rp.tileSize;
        textY = frameY + rp.tileSize * 3;
        for (String line : dialogue.split("/n")) {
            g2.drawString(line, textX, textY);
            textY += 40;
        }

        //Yes
        text = "YES";
        textX = t.findCenter(g2, rp, text);
        textY += rp.tileSize * 3;
        g2.drawString(text, textX, textY);
        if (cmdNo == 0) {
            g2.drawString(">", textX - 25, textY);
            if (rp.key.interactPressed == true) {
                subState = 0;
                rp.gameState = States.TITLESTATE;
            }
        }

        //No
        text = "NO";
        textX = t.findCenter(g2, rp, text);
        textY += rp.tileSize;
        g2.drawString(text, textX, textY);
        if (cmdNo == 1) {
            g2.drawString(">", textX - 25, textY);
            if (rp.key.interactPressed == true) {
                subState = 0;
                cmdNo=2;
            }
        }
    }

    private void drawInstructionScreen() {
        //Instructions
        g2.setFont(new Font("Arial", Font.PLAIN, 70));
        g2.setColor(Color.white);

        String text = "INSTRUCTIONS";
        int x = t.findCenter(g2, rp, text);
        int y = rp.tileSize * 3;
        g2.drawString(text, x, y);

        g2.setFont(new Font("Arial", Font.PLAIN, 20));
        g2.setColor(Color.white);

        text = "Use Arrow Keys to move";
        x = t.findCenter(g2, rp, text);
        y += rp.tileSize * 2;
        g2.drawString(text, x, y);

        text = "Z to interact";
        x = t.findCenter(g2, rp, text);
        y += rp.tileSize;
        g2.drawString(text, x, y);

        text = "ESCAPE to go to options menu";
        x = t.findCenter(g2, rp, text);
        y += rp.tileSize;
        g2.drawString(text, x, y);

        text = "Press Space to continue";
        x = t.findCenter(g2, rp, text);
        y += rp.tileSize;
        g2.drawString(text, x, y);

    }

    private void drawPlayScreen() {
        if (gameFinished == true) {
            if (rp.player.hasCoffee == true) {
                coffeeEnd(g2);
            } else if (rp.player.hasTea == true) {
                teaEnd(g2);
            }
            rp.gameThread = null;

        } else {
            drawUI(g2);
        }
    }

    public void drawTitleScreen() {

        //Title Name
        g2.setFont(new Font("Arial", Font.PLAIN, 70));
        g2.setColor(Color.white);

        String text = "Coffee Adventure";
        int x = t.findCenter(g2, rp, text);
        int y = rp.tileSize * 3;
        g2.drawString(text, x, y);

        //Character
        x = rp.screenWidth / 2 - (rp.tileSize * 2) / 2;
        y += rp.tileSize * 2;
        g2.drawImage(rp.player.idle, x, y, rp.tileSize * 2, rp.tileSize * 2, null);

        //Menu
        g2.setFont(new Font("Arial", Font.PLAIN, 30));
        g2.setColor(Color.white);

        text = "NEW GAME";
        x = t.findCenter(g2, rp, text);
        y += rp.tileSize * 4;
        g2.drawString(text, x, y);
        if (cmdNo == 0) {
            g2.drawString(">", x - rp.tileSize, y);
        }

        text = "LOAD GAME";
        x = t.findCenter(g2, rp, text);
        y += rp.tileSize;
        g2.drawString(text, x, y);
        if (cmdNo == 1) {
            g2.drawString(">", x - rp.tileSize, y);
        }
        text = "QUIT";
        x = t.findCenter(g2, rp, text);
        y += rp.tileSize;
        g2.drawString(text, x, y);
        if (cmdNo == 2) {
            g2.drawString(">", x - rp.tileSize, y);
        }
    }

    private void drawUI(Graphics2D g2) {
        g2.setFont(new Font("Arial", Font.PLAIN, 40));
        g2.setColor(Color.white);
        g2.drawImage(itemImage, rp.tileSize / 2, rp.tileSize / 2, rp.tileSize, rp.tileSize, null);
        g2.drawString("= " + rp.player.hasItems, 74, 65);

        //Message
        if (messageOn == true) {

            g2.setFont(g2.getFont().deriveFont(30F));
            g2.drawString(message, rp.tileSize / 2, rp.tileSize * 2);

            msgCounter++;

            if (msgCounter > 90) {
                msgCounter = 0;
                messageOn = false;
            }
        }
    }

    private void teaEnd(Graphics2D g2) {
        g2.setFont(new Font("Arial", Font.BOLD, 40));
        g2.setColor(Color.white);

        String text = "YOU DRANK TEA!";
        int textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        g2.drawString(text, rp.screenWidth / 2 - textLength / 2, rp.screenHeight / 2 - (rp.tileSize * 3));

        g2.setFont(new Font("Arial", Font.BOLD, 50));
        g2.setColor(Color.yellow);

        text = "COULD BE BETTER";
        textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();

        g2.drawString(text, rp.screenWidth / 2 - textLength / 2, rp.screenHeight / 2 + (rp.tileSize * 2));

        g2.setFont(new Font("Arial", Font.BOLD, 30));
        g2.setColor(Color.white);

        text = "Final Score: " + rp.player.hasItems;
        textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        g2.drawString(text, rp.screenWidth / 2 - textLength / 2, rp.screenHeight / 2 + (rp.tileSize * 4));
    }

    private void coffeeEnd(Graphics2D g2) {
        g2.setFont(new Font("Arial", Font.BOLD, 40));
        g2.setColor(Color.white);

        String text = "YOU DRANK COFFEE!";
        int textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        g2.drawString(text, rp.screenWidth / 2 - textLength / 2, rp.screenHeight / 2 - (rp.tileSize * 3));

        g2.setFont(new Font("Arial", Font.BOLD, 50));
        g2.setColor(Color.yellow);

        text = "CONGRATULATIONS!";
        textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();

        g2.drawString(text, rp.screenWidth / 2 - textLength / 2, rp.screenHeight / 2 + (rp.tileSize * 2));

        g2.setFont(new Font("Arial", Font.BOLD, 30));
        g2.setColor(Color.white);

        text = "Final Score: " + rp.player.hasItems;
        textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        g2.drawString(text, rp.screenWidth / 2 - textLength / 2, rp.screenHeight / 2 + (rp.tileSize * 4));
    }

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
