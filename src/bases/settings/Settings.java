package bases.settings;

import java.awt.*;

/**
 * Created by huynq on 8/19/17.
 */
public class Settings {
    private int windowWidth;
    private int windowHeight;

    private int gamePlayWidth;
    private int gamePlayHeight;

    public static final Settings instance = new Settings();

    private Settings(int gamePlayWidth, int gamePlayHeight) {
        this.gamePlayWidth = gamePlayWidth;
        this.gamePlayHeight = gamePlayHeight;
    }

    public int getGamePlayWidth() {
        return gamePlayWidth;
    }

    public int getGamePlayHeight() {
        return gamePlayHeight;
    }

    private Settings() {
        this(736, 414);
    }
}
