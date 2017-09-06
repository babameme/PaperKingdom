package bases.inputs;

import java.awt.*;
import java.awt.event.MouseEvent;

public class MouseManager {
    public Point oldPoint, movedPoint;
    public static final MouseManager instance = new MouseManager();

    private MouseManager() {

    }

    public void mouseMoved(MouseEvent e) {
        oldPoint = movedPoint;
        movedPoint = e.getLocationOnScreen();
    }
}
