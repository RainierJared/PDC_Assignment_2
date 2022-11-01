/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tile;

import Main.RPGPanel;
import Main.Tools;
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
public final class TileManager {

    //RPGPanel object for this class
    private final RPGPanel rp;
    
    public Tile[] tileArray;     //Tile array to fill its elements with the tiles sprites and info
    public int mapTileNo[][];       //2D array for the world map

    //Class Constructor
    public TileManager(RPGPanel rp) {
        this.rp = rp;
        tileArray = new Tile[10];
        mapTileNo = new int[rp.MAXWORLDCOL][rp.MAXWORLDROW];
        setTileImage();
        loadMap();
    }

    //Function that assigns the various tiles into the array's elements
    //The setup() function arguements are as follows:
    //Array index, image name, and collision (true if its collidable, false if its not)
    private void setTileImage() {
        setup(0,"floor",false);
        setup(1, "wall", true);
        setup(2, "drawer", true);
        setup(3, "chair", false);
        setup(4, "table", true);
        setup(5, "bed", false);
    }

    //Function that sets the tiles' images and locations in the tileArray
    //This function also utilizes Tools' scaleImg() function to pre-scale the image to improve performance
    private void setup(int index, String imgName, boolean collision) {
        Tools t = new Tools();
        try {

            tileArray[index] = new Tile();
            tileArray[index].image = ImageIO.read(getClass().getResourceAsStream("/res/tile/" + imgName + ".png"));
            tileArray[index].image = t.scaleImg(tileArray[index].image, rp.TILESIZE, rp.TILESIZE);
            tileArray[index].collision = collision;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Function that loads the map created in /res/map/world.txt file
    //That text file contains a 15x10 grid that contains values according to the indices
    //of the tileArray
    private void loadMap() {
        try {
            //Reading the world1.txt file
            InputStream is = getClass().getResourceAsStream("/res/map/world1.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            
            //This section of code initializes the 2D mapTileNo array with the indices of the tileArray
            while (col < rp.MAXWORLDCOL && row < rp.MAXWORLDROW) {
                String line = br.readLine();
                
                while (col < rp.MAXWORLDCOL) {
                    String numbers[] = line.split(" ");     //Splitting the spaces between the numbers
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNo[col][row] = num;      //Initializes the 2D array with the values in txt file
                    col++;
                }
                if (col == rp.MAXWORLDCOL) {
                    col = 0;
                    row++;
                }
            }
            //Closing the BufferedReader object
            br.close();
        } catch (Exception e) {
        }
    }

    //Draw function of this class
    public void draw(Graphics2D g2) {
        int worldCols = 0;
        int worldRows = 0;

        //This section of code deals with drawing where the tile is in the world
        while (worldCols < rp.MAXWORLDCOL && worldRows < rp.MAXWORLDROW) {
            int tileNo = mapTileNo[worldCols][worldRows];

            int worldX = worldCols * rp.TILESIZE;
            int worldY = worldRows * rp.TILESIZE;
            int screenX = worldX - rp.playerObj.worldX + rp.playerObj.screenX;
            int screenY = worldY - rp.playerObj.worldY + rp.playerObj.screenY;

            //Improving tileArray drawing efficiency
            //Checks the players position from the centre of the screen in all four quadrants
            //If a tileArray is within the four quadrants (-screenX, +screenX, -screenY, +screenY)
            //The tileArray will be rendered
            if (worldX + rp.TILESIZE > rp.playerObj.worldX - rp.playerObj.screenX
                    && worldX - rp.TILESIZE < rp.playerObj.worldX + rp.playerObj.screenX
                    && worldY + rp.TILESIZE > rp.playerObj.worldY - rp.playerObj.screenY
                    && worldY - rp.TILESIZE < rp.playerObj.worldY + rp.playerObj.screenY) {

                g2.drawImage(tileArray[tileNo].image, screenX, screenY, rp.TILESIZE, rp.TILESIZE, null);

            }
            worldCols++;

            if (worldCols == rp.MAXWORLDCOL) {
                worldCols = 0;
                worldRows++;
            }
        }
    }
}
