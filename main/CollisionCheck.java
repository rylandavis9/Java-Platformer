package main;

import entity.KillTile;
import entity.CollisionTile;
import entity.Entity;

import java.awt.*;

public class CollisionCheck {

    GamePanel gp;

    public CollisionCheck(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity, String axis) {
        Rectangle futureBounds = new Rectangle(
                entity.worldX + entity.solidArea.x,
                entity.worldY + entity.solidArea.y,
                entity.solidArea.width,
                entity.solidArea.height
        );

        if (axis.equals("horizontal")) {
            futureBounds.x += entity.xVel;
            entity.colDir = entity.xVel > 0 ? "right" : "left";
        } else if (axis.equals("vertical")) {
            futureBounds.y += entity.yVel;
            entity.colDir = entity.yVel > 0 ? "down" : "up";
        }

        for (CollisionTile tile : gp.tileM.collisionTiles) {
            if (futureBounds.intersects(tile.getBounds()) && !entity.colDir.equals("up")) {
                entity.collisionOn = true;
                return;
            }
        }

        for (KillTile killTile : gp.tileM.killTiles) {
            if (futureBounds.intersects(killTile.getBounds())) {
                entity.reset();
            }
        }

        entity.collisionOn = false;
    }
}
