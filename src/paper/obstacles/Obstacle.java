package paper.obstacles;
import bases.GameObject;
import bases.renderers.FixtureRenderer;
import org.dyn4j.geometry.Convex;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Vector2;

import java.awt.*;

public class Obstacle extends GameObject {
    private final Color type0 = Color.RED;
    private final Color type1 = Color.blue;
    private Convex convex;
    private double rotation;
    private MassType massType;
    private double angularVelocity;
    private Vector2 linearVelocity;
    private Vector2 position;
    private Color color;

    public Obstacle(Convex convex, double rotation, MassType massType, double angularVelocity, Vector2 linearVelocity, Vector2 position) {
        super();
        this.convex = convex;
        this.rotation = rotation;
        this.massType = massType;
        this.angularVelocity = angularVelocity;
        this.linearVelocity = linearVelocity;
        this.position = position;

        convex.rotate(rotation);
        this.addFixture(convex);
        this.setMass(massType);
        this.setColor(color);
        this.setAngularVelocity(angularVelocity);
        this.setLinearVelocity(linearVelocity);
        this.translate(position);
        this.renderer = new FixtureRenderer();
    }

}
