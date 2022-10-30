/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Object;

import Main.RPGPanel;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author botor
 */

//Coffee subclass of superObject
public final class objCoffee extends superObject {

    //RPGPanel object for this subclass
    RPGPanel rp;

    //Subclass constructor
    public objCoffee(RPGPanel rp) {
        this.rp = rp;       //This initializes the RPGPanel object with the arguement's RPGPanel object
        name = "Coffee";        //Initializing the name of the object subclass
        try {
            //Initializing image of the object with the correct sprite
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/instant-coffee.png"));
            
            //Pre-scaling the image for performance
            t.scaleImg(image, rp.TILESIZE, rp.TILESIZE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        //Calls the function to set the collision of this subclass
        this.setCollision();
    }

    @Override
    public void setCollision() {
        this.collision = true;      //Sets the collision of this subclass as true
    }
}
