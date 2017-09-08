package bases.inputs;

import org.dyn4j.geometry.Vector2;

import java.awt.*;
import java.awt.event.MouseEvent;

public class MouseManager {
    public Vector2 postition;
    public static final MouseManager instance = new MouseManager();

    private MouseManager() {

    }
}
