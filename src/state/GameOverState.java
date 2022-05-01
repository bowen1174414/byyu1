package state;

import bagel.Keys;
import bagel.Window;
import bagel.util.Colour;
import util.FontUtil;

public class GameOverState extends State {
    public GameOverState(StateHolder stateHolder) {
        super(stateHolder);
    }

    @Override
    public void draw() {
        FontUtil.drawString("GAME OVER", Window.getWidth() / 2 - 150, Window.getHeight() / 2, Colour.BLACK);
    }

    @Override
    public void handleKeyPress(Keys key) {

    }
}
