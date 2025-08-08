package tiles;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import main.GamePanel;


public class TileManager {
    GamePanel gp;
    Tiles[] tile;
    int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tiles[10];
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
    }

    public void getTileImage() {
        try{
            //floor
            tile[0] = new Tiles();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/Res/World/house_floor_tile.png"));
            //wall
            tile[1] = new Tiles();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/Res/World/house_wall.png"));
            tile[1].collision = true;
            //door
            tile[2] = new Tiles();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/Res/World/door.png"));

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String mapPath) {
        try{
            InputStream is = getClass().getResourceAsStream(mapPath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
                String line = br.readLine();
                while (col < gp.maxScreenCol) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxScreenCol){
                    col = 0;
                    row++;
                }
            }
        br.close();

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
        int tileNum = mapTileNum[col][row];
        
        if (tileNum < 0 || tileNum >= tile.length) {
            System.out.println("Invalid tile number: " + tileNum);
            return;
        }

        g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
        col++;
        x += gp.tileSize;
        if (col == gp.maxScreenCol) {
            col = 0;
            x = 0;
            row++;
            y += gp.tileSize;
        }
    }
    }
}   
