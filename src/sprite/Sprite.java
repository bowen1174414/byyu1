package sprite;

import bagel.util.Point;

public abstract class Sprite {
    protected Point position;
    private int damagePoint = 25;

    public int getDamagePoint() {
        return damagePoint;
    }

    public abstract void draw();

    public Point getPosition() {
        return position;
    }

    public Sprite(Point position) {
        this.position = position;
    }

    public Sprite() {
        this.position = new Point(0, 0);
    }
}
