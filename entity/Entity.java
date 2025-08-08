package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    public int startX, startY;
    public int worldX, worldY;
    public double xVel;
    public double yVel;

    public BufferedImage playerImg;
    public String direction;
    public String colDir;

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public  Rectangle solidArea;
    public Boolean collisionOn = false;
    public Boolean grounded = false;

    public void reset(){
        worldX = startX;
        worldY = startY;
    }
}
