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
public final class objTea extends superObject {

    RPGPanel rp;

    public objTea(RPGPanel rp) {
        this.rp = rp;
        name = "Tea";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/mug-tea.png"));
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
