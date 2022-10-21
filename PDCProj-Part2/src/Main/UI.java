/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import Object.objMugWithCoffee;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author botor
 */
public class UI {

    RPGPanel rp;
    Graphics2D g2;
    Tools t;
    BufferedImage itemImage;
    public boolean messageOn = false;
    public boolean gameFinished = false;
    public String message = "";

    int msgCounter = 0;

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

        if (rp.gameState == rp.playState) {
            drawPlayScreen();
        }
        if (rp.gameState == rp.pauseState) {
            drawPauseScreen();
        }
        if (rp.gameState == rp.titleState) {
            drawTitleScreen();
        }
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

    private void drawPauseScreen() {
        String text = "PAUSED";
        g2.setFont(new Font("Arial", Font.PLAIN, 70));
        g2.setColor(Color.white);
        int x = t.findCenter(g2, rp, text);
        int y = rp.screenHeight / 2;

        g2.drawString(text, x, y);
    }

    public void drawTitleScreen() {

        //Title Name
        g2.setFont(new Font("Arial", Font.PLAIN, 70));
        g2.setColor(Color.white);

        String text = "Coffee Adventure";
        int x = t.findCenter(g2, rp, text);
        int y = rp.tileSize * 3;

        g2.drawString(text, x, y);
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

    public void teaEnd(Graphics2D g2) {
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

    public void coffeeEnd(Graphics2D g2) {
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
}
