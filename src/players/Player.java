package players;

import bases.inputs.MouseManager;
import bases.renderers.FixtureRenderer;
import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.MassType;
import simulations.GameObject;

import java.awt.*;

public class Player extends GameObject{
    private static final double SPEED = 4;

    public Player() {
        super();
        this.renderer = new FixtureRenderer();
        this.addFixture(Geometry.createCircle(1));
        this.setMass(MassType.NORMAL);
        this.setAutoSleepingEnabled(false);

        this.setLinearVelocity(SPEED,0);
    }

    public Player(Color color) {
        super(color);
        this.renderer = new FixtureRenderer();
        this.addFixture(Geometry.createCircle(1));
        this.setMass(MassType.NORMAL);
        this.setAutoSleepingEnabled(false);
    }

    @Override
    public void update() {
        super.update();
        this.setLinearVelocity(SPEED,0);
        this.setAngularVelocity(0);
        if (MouseManager.instance.oldPoint != null && MouseManager.instance.movedPoint != null){
            this.translate(0, MouseManager.instance.oldPoint.y - MouseManager.instance.movedPoint.y);
            MouseManager.instance.movedPoint = null;
        }
    }
}
