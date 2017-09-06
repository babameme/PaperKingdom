import bases.inputs.MouseManager;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.dynamics.joint.MotorJoint;
import org.dyn4j.geometry.*;
import org.dyn4j.geometry.Rectangle;
import players.Player;
import simulations.GameObject;
import simulations.SimulationFrame;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameWindow extends SimulationFrame{
    private Player player;

    private final class CustomMouseAdapter extends MouseAdapter {
        @Override
        public void mouseMoved(MouseEvent e) {
            MouseManager.instance.mouseMoved(e);
        }
    }

    public GameWindow(){
        super("Paper Kingdom", 1.0);
        MouseAdapter mouseAdapter = new CustomMouseAdapter();
        this.canvas.addMouseMotionListener(mouseAdapter);
        this.canvas.addMouseWheelListener(mouseAdapter);
        this.canvas.addMouseListener(mouseAdapter);
    }


    @Override
    protected void initializeWorld() {
        this.world.setGravity(world.ZERO_GRAVITY);
        addPlayer();
        //addFloor();
    }

    private void addPlayer() {
        player = new Player();
        player.translate(-20, 0);

        this.world.addBody(player);
    }

    @Override
    protected void update(Graphics2D g2d, double elapsedTime) {
        super.update(g2d, elapsedTime);
    }

    private Vector2 toWorldCoordinates(Point point) {
        double x =  (point.getX() - this.canvas.getWidth() / 2.0) / this.scale;
        double y = -(point.getY() - this.canvas.getHeight() / 2.0) / this.scale;
        return new Vector2(x, y);
    }

    private Vector2 toWorldCoordinates(double x, double y) {
        double xx =  (x - this.canvas.getWidth() / 2.0) / this.scale;
        double yy = -(y - this.canvas.getHeight() / 2.0) / this.scale;
        return new Vector2(xx, yy);
    }

    private void addFloor() {
        Rectangle rectShape = new Rectangle(100, 20);
        rectShape.rotate(Math.PI / 6);
        GameObject rectangle = new GameObject();
        rectangle.addFixture(rectShape);
        rectangle.setMass(MassType.INFINITE);
        rectangle.setColor(Color.red);
        rectangle.setAngularVelocity(5.4);
        rectangle.setLinearVelocity(10, 0);
        this.world.addBody(rectangle);


        Circle circleShape = new Circle(10);
        GameObject circle = new GameObject();
        circle.addFixture(circleShape);
        circle.setMass(MassType.NORMAL);
        circle.setColor(Color.RED);
        circle.translate(5, 10);
        this.world.addBody(circle);

    }
}