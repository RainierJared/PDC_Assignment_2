/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author botor
 */

//Tools used for scaling images and finding the center of text
public class Tools {

    //Function for scaling/pre-rendering images to improve performance
    public BufferedImage scaleImg(BufferedImage originalImg, int width, int height) {
        
        BufferedImage scaledImg = new BufferedImage(width, height, originalImg.getType());
        Graphics2D g2 = scaledImg.createGraphics();
        g2.drawImage(originalImg, 0, 0, width, height, null);
        g2.dispose();
        
        return scaledImg;
    }
    
    //Function to find the center of the screen relative to a String's length
    public int findCenter(Graphics2D g2, RPGPanel rp, String text) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return rp.SCREENWIDTH/2 - length/2;
    }
}
