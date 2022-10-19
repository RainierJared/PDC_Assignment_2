/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import GUIVersion.KeyHandler;
import GUIVersion.RPGPanel;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author jaredbotor
 */
public class Player extends Entity {

    RPGPanel rp;
    KeyHandler key;

    public Player(RPGPanel rp, KeyHandler key) {
        this.rp = rp;
        this.key = key;
        setDefaultSettings();
    }

    public void setDefaultSettings() {
        x = 100;
        y = 100;
        speed = 4;
    }

    public void update() {
        if (key.upPress) {
            y -= speed;

        } else if (key.downPress) {
            y += speed;

        } else if (key.leftPress) {
            x -= speed;

        } else if (key.rightPress) {
            x += speed;

        }
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.white);
        g2.fillRect(x, y, rp.tileSize, rp.tileSize);
    }
}
