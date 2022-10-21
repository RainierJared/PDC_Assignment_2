/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Object;

import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author botor
 */
public class objMilk extends superObject{

    public objMilk() {
        name = "Milk";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/milk.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
