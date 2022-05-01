package util;

import bagel.Image;

import java.util.HashMap;
import java.util.Map;

public class Images {
    private final Map<Direction, Image> playerImages;
    private final Image blockImage;

    private Images() {
        playerImages = new HashMap<>();
        playerImages.put(Direction.East, new Image("res/sailorRight.png"));
        playerImages.put(Direction.West, new Image("res/sailorLeft.png"));

        blockImage = new Image("res/block.png");
    }

    private static Images instance;
    public static Images getInstance() {
        if (instance == null) {
            instance = new Images();
        }
        return instance;
    }

    public Image getBlockImage() {
        return blockImage;
    }

    public Image getPlayerImage(Direction direction) {
        return playerImages.get(direction);
    }
}
