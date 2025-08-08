package entity;

import java.awt.*;


public class KillTile extends Entity{


    public Rectangle kBounds;

    public KillTile(int x, int y) {
        kBounds = new Rectangle(x, y, 48, 48);
    }

    public Rectangle getBounds() {
        return kBounds;
    }
}
