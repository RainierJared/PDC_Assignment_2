/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tile;

import GUIVersion.RPGPanel;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;

/**
 *
 * @author botor
 */
public class TileManager {

    RPGPanel rp;
    Tile[] tile;
    int mapTileNo[][];

    public TileManager(RPGPanel rp) {
        this.rp = rp;
        tile = new Tile[10];
        mapTileNo = new int[rp.maxScreenCol][rp.maxScreenRow];
        getTileImage();
        loadMap();
    }

    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/res/tile/floor.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/res/tile/wall.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap() {
        try {
            InputStream is = getClass().getResourceAsStream("/res/map/level0.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < rp.maxScreenCol && row < rp.maxScreenRow) {
                String line = br.readLine();

                while (col < rp.maxScreenCol) {
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNo[col][row] = num;
                    col++;
                }
                if (col == rp.maxScreenCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
        }
    }

    public void draw(Graphics2D g2) {
//        g2.drawImage(tile[0].image, 0, 0, rp.tileSize, rp.tileSize, null);
//        g2.drawImage(tile[1].image, 48, 0, rp.tileSize, rp.tileSize, null);
        int cols = 0;
        int rows = 0;
        int x = 0;
        int y = 0;

        while (cols < rp.maxScreenCol && rows < rp.maxScreenRow) {
            int tileNo = mapTileNo[cols][rows];
            g2.drawImage(tile[tileNo].image, x, y, rp.tileSize, rp.tileSize, null);
            cols++;
            x += rp.tileSize;

            if (cols == rp.maxScreenCol) {
                cols = 0;
                x = 0;
                rows++;
                y += rp.tileSize;
            }
        }
    }
}
