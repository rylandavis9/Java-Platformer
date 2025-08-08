package entity;

import main.GamePanel;
import main.InputHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    GamePanel gp;
    InputHandler keyH;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, InputHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle();
        solidArea.x = 16;
        solidArea.y = 32;
        solidArea.width = 16;
        solidArea.height = 16;

        setDefaults();
        getPlayerImage();


    }

    public void setDefaults() {
        startX = gp.tileSize * 2;
        startY = gp.tileSize * 2;
        worldX = startX;
        worldY = startY;
        xVel = 0;
        yVel = 0;
        direction = "down";
        colDir = "";
    }

    public void getPlayerImage() {
        try {
            playerImg = ImageIO.read(getClass().getResourceAsStream("/res/player/player.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {

        if (keyH.rPressed) {
            this.reset();
        }

        if (keyH.leftPressed) {
            xVel = -6;
        } else if (keyH.rightPressed) {
            xVel = 6;
        }
        // Horizontal movement collision
        collisionOn = false;
        gp.collisionCheck.checkTile(this, "horizontal");
        if (!collisionOn) {
            worldX += xVel;
        } else {
            xVel = 0;
        }

        // Vertical movement collision
        collisionOn = false;
        gp.collisionCheck.checkTile(this, "vertical");
        if (!collisionOn) {
            worldY += yVel;
            grounded = false;
        } else {
            if (yVel > 0) {
                grounded = true; // only grounded if falling downward onto a surface
            }
            yVel = 0;

        }

        // Apply friction and gravity
        xVel *= 0.9;
        if (Math.abs(xVel) < 0.1) xVel = 0;

        yVel += 0.5; // gravity
        if (yVel > 10) yVel = 10;

        // Animation logic
        spriteCounter++;
        if (spriteCounter > 12) {
            spriteNum = (spriteNum % 4) + 1;
            spriteCounter = 0;
        }

        if (keyH.upPressed) {
            if (grounded) yVel = - 15;
        }



    }

    public void draw(Graphics g2) {
        BufferedImage image = null;

        // Just use right1 as placeholder (you can re-enable animation later)
        image = playerImg;

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }

    public Rectangle getBounds() {
        return new Rectangle(worldX + solidArea.x, worldY + solidArea.y, solidArea.width, solidArea.height);
    }
}
