package state;

import bagel.Keys;

public abstract class State {
    public abstract void draw();
    public abstract void handleKeyPress(Keys key);
    public void update() {}

    protected StateHolder stateHolder;

    public State(StateHolder stateHolder) {
        this.stateHolder = stateHolder;
    }
}
