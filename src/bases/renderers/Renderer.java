package bases.renderers;

import bases.Vector2D;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.geometry.Convex;
import org.dyn4j.geometry.Vector2;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;

/**
 * Created by huynq on 8/16/17.
 */
public interface Renderer {
    void render(Graphics2D g2d, List<BodyFixture> fixtures, double scale, Color color, Vector2 position);
}
