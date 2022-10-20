/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tile;

import Main.RPGPanel;
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
    public Tile[] tile;
    public int mapTileNo[][];

    public TileManager(RPGPanel rp) {
        this.rp = rp;
        tile = new Tile[10];
        mapTileNo = new int[rp.maxWorldCols][rp.maxWorldRows];
        getTileImage();
        loadMap();
    }

    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/res/tile/floor.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/res/tile/wall.png"));
            tile[1].collision = true;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/res/tile/drawer.png"));
            tile[2].collision = true;

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/res/tile/chair.png"));
            tile[3].collision = true;

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/res/tile/table.png"));
            tile[4].collision = true;

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/res/tile/bed.png"));
            tile[5].collision = true;

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

            while (col < rp.maxWorldCols && row < rp.maxWorldRows) {
                String line = br.readLine();

                while (col < rp.maxWorldCols) {
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNo[col][row] = num;
                    col++;
                }
                if (col == rp.maxWorldCols) {
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

        while (worldCols < rp.maxWorldCols && worldRows < rp.maxWorldRows) {
            int tileNo = mapTileNo[worldCols][worldRows];

            int worldX = worldCols * rp.tileSize;
            int worldY = worldRows * rp.tileSize;
            int screenX = worldX - rp.player.worldX + rp.player.screenX;
            int screenY = worldY - rp.player.worldY + rp.player.screenY;

            //Improving tile drawing efficiency
            //Checks the players position from the centre of the screen in all four quadrants
            //If a tile is within the four quadrants (-screenX, +screenX, -screenY, +screenY)
            //The tile will be rendered
            if (worldX + rp.tileSize > rp.player.worldX - rp.player.screenX
                    && worldX - rp.tileSize < rp.player.worldX + rp.player.screenX
                    && worldY + rp.tileSize > rp.player.worldY - rp.player.screenY
                    && worldY - rp.tileSize < rp.player.worldY + rp.player.screenY) {
                g2.drawImage(tile[tileNo].image, screenX, screenY, rp.tileSize, rp.tileSize, null);

            }
            worldCols++;

            if (worldCols == rp.maxWorldCols) {
                worldCols = 0;
                worldRows++;
            }
        }
    }
}
