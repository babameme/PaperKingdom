package bases;

import org.dyn4j.geometry.Vector2;

/**
 * Created by huynq on 8/2/17.
 */
public class Constraints {
    public double top;
    public double bottom;
    public double left;
    public double right;

    public Constraints(double top, double bottom, double left, double right) {
        this.top = top;
        this.bottom = bottom;
        this.left = left;
        this.right = right;
    }

    public void make(Vector2 position) {
        if (position.y < top) position.y = top;
        if (position.y > bottom) position.y = bottom;
        if (position.x < left) position.x = left;
        if (position.x > right) position.x = right;
    }
}
