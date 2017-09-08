package paper.obstacles;
import bases.GameObject;
import bases.renderers.FixtureRenderer;
import org.dyn4j.geometry.Convex;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Vector2;

import java.awt.*;
import java.util.Vector;

public class Obstacle extends GameObject {

    public Obstacle(Convex convex, double rotation, MassType massType, double angularVelocity, Vector2 linearVelocity, Vector2 position) {
        super();
        convex.rotate(rotation);
        this.addFixture(convex);
        this.setMass(massType);
        this.setAngularVelocity(angularVelocity);
        this.setLinearVelocity(linearVelocity);
        this.translate(position);
        this.color = Color.RED;
        this.renderer = new FixtureRenderer();
    }

}
