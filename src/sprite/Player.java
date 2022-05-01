package sprite;

import bagel.Keys;
import bagel.util.Point;
import util.Direction;
import util.Images;

public class Player extends Sprite {
    private int healthPoint = 100;

    public int getHealthPoint() {
        return healthPoint;
    }

    public Player(Point position, Direction direction) {
        super(position);
        this.facingDirection = Direction.East;
    }

    private Direction direction;

    private Direction facingDirection;

    public void move(Point point) {
        this.position = point;
    }

    @Override
    public void draw() {
        Images.getInstance().getPlayerImage(facingDirection).draw(position.x, position.y);
    }

    public void updateFacingDirection(Keys key) {
        if (key == Keys.LEFT) {
            facingDirection = Direction.West;
        } else if (key == Keys.RIGHT) {
            facingDirection = Direction.East;
        }
    }

    public void getDamaged(int point) {
        healthPoint -= point;
    }
}
