package util;

import bagel.DrawOptions;
import bagel.Font;
import bagel.util.Colour;

public class FontUtil {
    static Font font;

    static {
        font = new Font("res/wheaton.otf", 30);
    }

    public static void drawString(String str, int x, int y, Colour colour) {
        DrawOptions options = new DrawOptions();
        options.setBlendColour(colour);
        font.drawString(str, x, y, options);
    }
}
