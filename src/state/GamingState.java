package state;

import bagel.*;
import bagel.util.Colour;
import bagel.util.Point;
import bagel.util.Rectangle;
import sprite.Block;
import sprite.Player;
import util.Direction;
import util.FontUtil;

import java.util.ArrayList;
import java.util.List;

public class GamingState extends State {
    private List<Block> blocks = new ArrayList<>();
    private Player player;
    private Point lastPosition;


    public GamingState(StateHolder stateHolder) {
        super(stateHolder);
    }

    @Override
    public void draw() {

        player.draw();
        for (Block block : blocks) {
            block.draw();
        }

        if (player.getHealthPoint() < 35) {
            FontUtil.drawString("" + player.getHealthPoint() + "%", 10, 25, new Colour(1, 0, 0));
        } else if (player.getHealthPoint() < 65) {
            FontUtil.drawString("" + player.getHealthPoint() + "%", 10, 25, new Colour(0.9, 0.6, 0));
        } else {
            FontUtil.drawString("" + player.getHealthPoint() + "%", 10, 25, new Colour(0, 0.8, 0.2));
        }
    }

    @Override
    public void update() {
        if (collideWithBlock()) {
            player.getDamaged(10);
            System.out.println("Block inflicts 10 damage points on Sailor. Sailor’s current health: " + player.getHealthPoint() + "/100");
            player.move(lastPosition);
        }
        if (player.getHealthPoint() <= 0) {
            stateHolder.changeState("over");
        }
    }

    @Override
    public void handleKeyPress(Keys key) {
        lastPosition = player.getPosition();
        Point nextPosition = nextPlayerPosition(key);
        player.move(nextPosition);

        player.updateFacingDirection(key);

        if (hasWon()) {
            stateHolder.changeState("win");
        } else if (outOfBounds()) {
            stateHolder.changeState("over");
        }
    }

    public void initBlocks(List<Point> positions) {
        for (Point position : positions) {
            blocks.add(new Block(position));
        }
    }

    public void initPlayer(Point position) {
        player = new Player(position, Direction.East);
    }

    private Point nextPlayerPosition(Keys key) {
        Point position = player.getPosition();
        if (key == Keys.LEFT) {
            return new Point(position.x - 20, position.y);
        } else if (key == Keys.RIGHT) {
            return new Point(position.x + 20, position.y);
        } else if (key == Keys.UP) {
            return new Point(position.x, position.y - 20);
        } else if (key == Keys.DOWN) {
            return new Point(position.x, position.y + 20);
        }
        return null;
    }

    private boolean collideWithBlock() {
        Point position = player.getPosition();
        Rectangle playerRectangle = new Rectangle(position, 40, 61);
        for (Block block : blocks) {
            if (playerRectangle.intersects(new Rectangle(block.getPosition(), 40, 40))) {
                return true;
            }
        }
        return false;
    }

    private boolean outOfBounds() {
        Point position = player.getPosition();
        if (position.x < 0 || position.x > 1024) {
            return true;
        }
        return position.y < 60 || position.y > 670;
    }

    private boolean hasWon() {
        Point position = player.getPosition();
        return position.x >= 990 && position.y >= 630;
    }
}
