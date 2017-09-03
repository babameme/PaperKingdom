import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.dynamics.joint.MotorJoint;
import org.dyn4j.geometry.*;
import org.dyn4j.geometry.Rectangle;
import simulations.GameObject;
import simulations.SimulationFrame;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameWindow extends SimulationFrame{
    private Point movedPoint;
    private GameObject player;

    private final class CustomMouseAdapter extends MouseAdapter {
        @Override
        public void mouseMoved(MouseEvent e) {
            // get the panel-space point
            movedPoint = e.getLocationOnScreen();
        }
    }

    public GameWindow(){
        super("Paper Kingdom", 10.0);
        MouseAdapter mouseAdapter = new CustomMouseAdapter();
        this.canvas.addMouseMotionListener(mouseAdapter);
        this.canvas.addMouseWheelListener(mouseAdapter);
        this.canvas.addMouseListener(mouseAdapter);
    }


    @Override
    protected void initializeWorld() {
        this.world.setGravity(world.ZERO_GRAVITY);
        addPlayer();
        addFloor();
    }

    private void addPlayer() {
        player = new GameObject(Color.green);
        player.addFixture(Geometry.createCircle(1));
        player.setMass(MassType.NORMAL);
        player.setAutoSleepingEnabled(false);
        this.world.addBody(player);

    }

    @Override
    protected void update(Graphics2D g2d, double elapsedTime) {
        if (this.movedPoint != null){
            Vector2 p = this.toWorldCoordinates(this.movedPoint);
            // Set the desired position
            Transform tx = new Transform();
            tx.translate(p);
            this.player.setTransform(tx);
            this.movedPoint = null;
        }
        super.update(g2d, elapsedTime);
    }

    private Vector2 toWorldCoordinates(Point point) {
        double x =  (point.getX() - this.canvas.getWidth() / 2.0) / this.scale;
        double y = -(point.getY() - this.canvas.getHeight() / 2.0) / this.scale;
        return new Vector2(x, y);
    }

    private void addFloor() {
        Rectangle rectShape = new Rectangle(10.0, 25.0);
        GameObject rectangle = new GameObject();
        rectangle.addFixture(rectShape);
        rectangle.setMass(MassType.INFINITE);
        rectangle.translate(-35.0, 17.5);
        rectangle.setColor(Color.red);
        this.world.addBody(rectangle);

        rectShape = new Rectangle(40.0, 15.0);
        rectangle = new GameObject();
        rectangle.addFixture(rectShape);
        rectangle.setMass(MassType.INFINITE);
        rectangle.translate(-10,22.5);
        rectangle.setColor(Color.red);
        this.world.addBody(rectangle);

        rectShape = new Rectangle(30, 25);
        rectangle = new GameObject();
        rectangle.addFixture(rectShape);
        rectangle.setMass(MassType.INFINITE);
        rectangle.translate(25, 17.5);
        rectangle.setColor(Color.red);
        this.world.addBody(rectangle);

        rectShape = new Rectangle(10, 10);
        rectangle = new GameObject();
        rectangle.addFixture(rectShape);
        rectangle.setMass(MassType.INFINITE);
        rectangle.translate(15, 0);
        rectangle.setColor(Color.red);
        this.world.addBody(rectangle);

        rectShape = new Rectangle(40, 25);
        rectangle = new GameObject();
        rectangle.addFixture(rectShape);
        rectangle.setMass(MassType.INFINITE);
        rectangle.translate(-20, -17.5);
        rectangle.setColor(Color.red);
        this.world.addBody(rectangle);

        rectShape = new Rectangle(20, 10);
        rectangle = new GameObject();
        rectangle.addFixture(rectShape);
        rectangle.setMass(MassType.INFINITE);
        rectangle.translate(-10, 0);
        rectangle.setColor(Color.red);
        this.world.addBody(rectangle);

        rectShape = new Rectangle(40, 15);
        rectangle = new GameObject();
        rectangle.addFixture(rectShape);
        rectangle.setMass(MassType.INFINITE);
        rectangle.translate(20, -22.5);
        rectangle.setColor(Color.red);
        this.world.addBody(rectangle);

        rectShape = new Rectangle(10, 10);
        rectangle = new GameObject();
        rectangle.addFixture(rectShape);
        rectangle.setMass(MassType.INFINITE);
        rectangle.translate(35, -10);
        rectangle.setColor(Color.red);
        this.world.addBody(rectangle);
    }
}