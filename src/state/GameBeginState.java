package state;

import bagel.Keys;
import bagel.Window;
import bagel.util.Colour;
import bagel.util.Rectangle;
import util.FontUtil;

public class GameBeginState extends State {
    public GameBeginState(StateHolder stateHolder) {
        super(stateHolder);
    }

    @Override
    public void draw() {
        FontUtil.drawString("PRESS SPACE TO START", Window.getWidth() / 2 - 150, Window.getHeight() / 2, Colour.BLACK);
        FontUtil.drawString("USE ARROW KEYS TO FIND LADDER", Window.getWidth() / 2 - 150, Window.getHeight() / 2 + 70, Colour.BLACK);
    }

    @Override
    public void handleKeyPress(Keys key) {
        if (key == Keys.SPACE) {
            stateHolder.changeState("gaming");
        }
    }
}
