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
    BufferedImage itemImage;
    public boolean messageOn = false;
    public boolean gameFinished = false;
    public String message = "";

    int msgCounter = 0;

    public UI(RPGPanel rp) {
        this.rp = rp;
        objMugWithCoffee item = new objMugWithCoffee();
        itemImage = item.image;

    }

    public void showMsg(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {
        if (rp.gameState == rp.titleState) {
            drawTitleScreen(g2);
        }
        if (gameFinished == true) {
            if (rp.player.hasCoffee == true) {
                coffeeEnd(g2);
            } else if (rp.player.hasTea == true) {
                teaEnd(g2);
            }
            rp.gameThread = null;

        } else {
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
    }

    public void drawTitleScreen(Graphics2D g2) {
        
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        String text = "Coffee Adventure";
        int textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = rp.screenWidth / 2 - textLength / 2;
        int y = rp.tileSize*3;
        
        g2.setColor(Color.white);
        g2.drawString(text, x, y);
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
