/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tile;

import Main.RPGPanel;
import Main.Tools;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
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
    public Tile[] tile;
    public int mapTileNo[][];

    public TileManager(RPGPanel rp) {
        this.rp = rp;
        tile = new Tile[10];
        mapTileNo = new int[rp.MAXWORLDCOL][rp.MAXWORLDROW];
        getTileImage();
        loadMap();
    }

    public void getTileImage() {
        setup(0,"floor",false);
        setup(1, "wall", true);
        setup(2, "drawer", true);
        setup(3, "chair", false);
        setup(4, "table", true);
        setup(5, "bed", false);
    }

    public void setup(int index, String imgName, boolean collision) {
        Tools t = new Tools();
        try {

            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/res/tile/" + imgName + ".png"));
            tile[index].image = t.scaleImg(tile[index].image, rp.TILESIZE, rp.TILESIZE);
            tile[index].collision = collision;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap() {
        try {
            InputStream is = getClass().getResourceAsStream("/res/map/world1.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < rp.MAXWORLDCOL && row < rp.MAXWORLDROW) {
                String line = br.readLine();

                while (col < rp.MAXWORLDCOL) {
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNo[col][row] = num;
                    col++;
                }
                if (col == rp.MAXWORLDCOL) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
        }
    }

    public void draw(Graphics2D g2) {
        int worldCols = 0;
        int worldRows = 0;

        while (worldCols < rp.MAXWORLDCOL && worldRows < rp.MAXWORLDROW) {
            int tileNo = mapTileNo[worldCols][worldRows];

            int worldX = worldCols * rp.TILESIZE;
            int worldY = worldRows * rp.TILESIZE;
            int screenX = worldX - rp.playerObj.worldX + rp.playerObj.screenX;
            int screenY = worldY - rp.playerObj.worldY + rp.playerObj.screenY;

            //Improving tile drawing efficiency
            //Checks the players position from the centre of the screen in all four quadrants
            //If a tile is within the four quadrants (-screenX, +screenX, -screenY, +screenY)
            //The tile will be rendered
            if (worldX + rp.TILESIZE > rp.playerObj.worldX - rp.playerObj.screenX
                    && worldX - rp.TILESIZE < rp.playerObj.worldX + rp.playerObj.screenX
                    && worldY + rp.TILESIZE > rp.playerObj.worldY - rp.playerObj.screenY
                    && worldY - rp.TILESIZE < rp.playerObj.worldY + rp.playerObj.screenY) {

                g2.drawImage(tile[tileNo].image, screenX, screenY, rp.TILESIZE, rp.TILESIZE, null);

            }
            worldCols++;

            if (worldCols == rp.MAXWORLDCOL) {
                worldCols = 0;
                worldRows++;
            }
        }
    }
}
