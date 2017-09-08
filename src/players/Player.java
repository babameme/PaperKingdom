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
    private static final double SPEED = 10;
    private Color color;
    private Convex convex;
    private double rotation;
    private MassType massType;
    private double angularVelocity;
    private Vector2 linearVelocity;
    private Vector2 position;

    public Player(Convex convex, double rotation, MassType massType, double angularVelocity, Vector2 linearVelocity, Vector2 position) {
        super();
        this.convex = convex;
        this.rotation = rotation;
        this.massType = massType;
        this.angularVelocity = angularVelocity;
        this.linearVelocity = linearVelocity;
        this.position = position;
        this.color = Color.MAGENTA;
        this.setLinearVelocity(SPEED,0);

        convex.rotate(rotation);
        this.addFixture(convex);
        this.setMass(massType);
        this.setColor(color);
        this.setAngularVelocity(angularVelocity);
        this.setLinearVelocity(linearVelocity);
        this.translate(position);

        this.renderer = new FixtureRenderer();
    }

    //TODO: Fix to drag
    @Override
    public void update() {
        super.update();
        this.setLinearVelocity(linearVelocity);
        this.setAngularVelocity(angularVelocity);
        if (MouseManager.instance.oldPoint != null && MouseManager.instance.movedPoint != null){
            this.translate(0, MouseManager.instance.oldPoint.y - MouseManager.instance.movedPoint.y);
            MouseManager.instance.movedPoint = null;
        }
    }
}
