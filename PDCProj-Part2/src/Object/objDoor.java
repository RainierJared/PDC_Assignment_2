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
public final class objDoor extends superObject {

    RPGPanel rp;

    public objDoor(RPGPanel rp) {
        this.rp = rp;
        name = "Door";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/door.png"));
            t.scaleImg(image, rp.TILESIZE, rp.TILESIZE);

        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setCollision();
    }

    @Override
    public void setCollision() {
        this.collision = true;
    }
}
