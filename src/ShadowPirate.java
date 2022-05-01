import bagel.*;
import bagel.util.Point;
import state.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Skeleton Code for SWEN20003 Project 1, Semester 1, 2022
 *
 * Please filling your name below
 * @author
 */
public class ShadowPirate extends AbstractGame implements StateHolder {
    public final static int WINDOW_WIDTH = 1024;
    public final static int WINDOW_HEIGHT = 768;
    private final static String GAME_TITLE = "ShadowPirate";
    private final Image BACKGROUND_IMAGE = new Image("res/background0.png");


    private Point playerPosition;
    private List<Point> blockPositions = new ArrayList<>();

    private Map<String, State> states = new HashMap<>();
    private State currentState;

    private Keys[] handledKeys = new Keys[]{Keys.LEFT, Keys.RIGHT, Keys.UP, Keys.DOWN, Keys.SPACE};

    public ShadowPirate() {
        super(WINDOW_WIDTH, WINDOW_HEIGHT, GAME_TITLE);

        try {
            readCSV("res/level0.csv");
            initState();
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        }
    }

    private void initState() {
        states.put("begin", new GameBeginState(this));

        GamingState gamingState = new GamingState(this);
        gamingState.initBlocks(blockPositions);
        gamingState.initPlayer(playerPosition);
        states.put("gaming", gamingState);
        states.put("win", new GameWinState(this));
        states.put("over", new GameOverState(this));

        currentState = states.get("begin");
    }

    /**
     * The entry point for the program.
     */
    public static void main(String[] args) {
        ShadowPirate game = new ShadowPirate();
        game.run();
    }

    /**
     * Method used to read file and create objects
     */
    private void readCSV(String fileName) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(fileName));
        if (scanner.hasNextLine()) {
            String[] parts = scanner.nextLine().split(",");
            playerPosition = new Point(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
        }
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(",");
            blockPositions.add(new Point(Integer.parseInt(parts[1]), Integer.parseInt(parts[2])));
        }
    }

    /**
     * Performs a state update.
     * allows the game to exit when the escape key is pressed.
     */
    @Override
    public void update(Input input) {
        BACKGROUND_IMAGE.draw(Window.getWidth()/2.0, Window.getHeight()/2.0);
        currentState.draw();
        currentState.update();

        if (input.wasPressed(Keys.ESCAPE)){
            Window.close();
        }

        for (Keys key : handledKeys) {
            if (input.wasPressed(key)) {
                currentState.handleKeyPress(key);
            }
        }
    }

    @Override
    public void changeState(String stateKey) {
        currentState = states.get(stateKey);
    }
}
