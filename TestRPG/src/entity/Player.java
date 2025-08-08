package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {
    GamePanel gamePanel;
    KeyHandler keyH;

    public Player(GamePanel gamePanel, KeyHandler keyH) {
        this.gamePanel = gamePanel;
        this.keyH = keyH;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
        Direction = "down"; // Default direction
    }

    public void getPlayerImage() {
    
        try{
            P_Right = ImageIO.read(getClass().getResourceAsStream("/Res/Player/P_Right.png"));
            P_Left = ImageIO.read(getClass().getResourceAsStream("/Res/Player/P_Left.png"));
            P_Up = ImageIO.read(getClass().getResourceAsStream("/Res/Player/P_Up.png"));
            P_Down = ImageIO.read(getClass().getResourceAsStream("/Res/Player/P_Down.png"));     
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (keyH.upPressed) {
            Direction = "up";
            y -= speed;
        }  else if (keyH.downPressed) {
            Direction = "down";
            y += speed;
        }   else if (keyH.leftPressed) {
            Direction = "left";
            x -= speed;
        }   else if (keyH.rightPressed) {
            Direction = "right";
            x += speed;
        }   
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        switch (Direction) {
            case "up":
                image = P_Up;
                break;
            case "down":
                image = P_Down;
                break;
            case "left":
                image = P_Left;
                break;
            case "right":
                image = P_Right;
                break;  
        }
        g2.drawImage(image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}