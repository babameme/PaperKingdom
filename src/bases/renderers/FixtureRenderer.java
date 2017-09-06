package bases.renderers;

import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.geometry.Convex;
import org.dyn4j.geometry.Vector2;

import java.awt.*;
import java.util.List;

public class FixtureRenderer implements Renderer {
    public FixtureRenderer(){

    }
    @Override
    public void render(Graphics2D g2d, List<BodyFixture> fixtures, double scale, Color color, Vector2 position) {
        for (BodyFixture fixture : fixtures){
            Convex convex = fixture.getShape();
            Graphics2DRenderer.render(g2d,convex,scale,color);
        }
    }
}
