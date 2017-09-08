package paper.friends;

import bases.GameObject;
import bases.renderers.FixtureRenderer;
import org.dyn4j.geometry.Convex;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Vector2;

public class Friend extends GameObject{
    public Friend(Convex convex, double rotation, MassType massType, double angularVelocity, Vector2 linearVelocity, Vector2 position) {
        super();
        convex.rotate(rotation);
        this.addFixture(convex);
        this.setMass(massType);
        this.setAngularVelocity(angularVelocity);
        this.setLinearVelocity(linearVelocity);
        this.translate(position);
        this.renderer = new FixtureRenderer();
    }
}
