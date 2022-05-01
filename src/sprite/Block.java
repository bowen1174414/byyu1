package sprite;

import bagel.util.Point;
import util.Images;

public class Block extends Sprite {
    public Block(Point position) {
        super(position);
    }

    @Override
    public void draw() {
        Images.getInstance().getBlockImage().draw(position.x, position.y);
    }
}
