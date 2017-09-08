package players;

import bases.inputs.MouseManager;
import bases.renderers.FixtureRenderer;
import org.dyn4j.geometry.Convex;
import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.MassType;
import bases.GameObject;
import org.dyn4j.geometry.Vector2;

import java.awt.*;

public class Player extends GameObject{
    private static final double SPEED = 50;
    private Color color;
    private Vector2 linearVelocity;

    public Player(Convex convex, double rotation, MassType massType, double angularVelocity, Vector2 position) {
        super();
        convex.rotate(rotation);
        this.addFixture(convex);
        this.setMass(massType);
        this.setAngularVelocity(angularVelocity);
        linearVelocity = new Vector2(SPEED, 0);
        this.setLinearVelocity(linearVelocity);
        this.translate(position);

        this.renderer = new FixtureRenderer();
    }

    //TODO: Fix to drag
    @Override
    public void update() {
        super.update();
        this.setLinearVelocity(linearVelocity);
        this.setAngularVelocity(0);
        if (MouseManager.instance.oldPoint != null && MouseManager.instance.movedPoint != null){
            this.translate(0, MouseManager.instance.oldPoint.y - MouseManager.instance.movedPoint.y);
            MouseManager.instance.oldPoint = MouseManager.instance.movedPoint;
            MouseManager.instance.movedPoint = null;
        }
    }
}
