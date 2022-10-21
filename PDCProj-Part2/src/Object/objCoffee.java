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
public class objCoffee extends superObject{

    public objCoffee() {
        name = "Coffee";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/instant-coffee.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
