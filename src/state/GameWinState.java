package state;

import bagel.Keys;
import bagel.Window;
import bagel.util.Colour;
import util.FontUtil;

public class GameWinState extends State {
    public GameWinState(StateHolder stateHolder) {
        super(stateHolder);
    }

    @Override
    public void draw() {
        FontUtil.drawString("CONGRATULATIONS!", Window.getWidth() / 2 - 150, Window.getHeight() / 2, Colour.BLACK);
    }

    @Override
    public void handleKeyPress(Keys key) {

    }
}
