import bases.inputs.MouseManager;
import bases.renderers.ImageRenderer;
import obstacles.Obstacle;
import org.dyn4j.collision.broadphase.BroadphasePair;
import org.dyn4j.collision.narrowphase.Gjk;
import org.dyn4j.collision.narrowphase.NarrowphaseDetector;
import org.dyn4j.collision.narrowphase.Penetration;
import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.geometry.*;
import org.dyn4j.geometry.Rectangle;
import players.Player;
import bases.GameObject;
import simulations.SimulationFrame;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class GameWindow extends SimulationFrame{
    private Player player;
    NarrowphaseDetector np = new Gjk();

    private final class CustomMouseAdapter extends MouseAdapter {
        @Override
        public void mouseMoved(MouseEvent e) {
            //MouseManager.instance.mouseMoved(e);
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            super.mouseDragged(e);
            MouseManager.instance.mouseDragged(e);
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
        addObstacle();
    }

    private void addPlayer() {
        player = new Player(new Circle(10), 0, MassType.NORMAL, 0, new Vector2(15,0), new Vector2(-300,0));
        player.getFixture(0).setSensor(true);

        this.world.addBody(player);
//        GameObject gameObject = new GameObject(ImageRenderer.create("assets/images/green_square.png"));
//        gameObject.getPosition().set(100, 100);
//        this.world.addBody(gameObject);
    }

    @Override
    protected void update(Graphics2D g2d, double elapsedTime) {
        super.update(g2d, elapsedTime);
        List<BroadphasePair<Body, BodyFixture>> pairs = world.getBroadphaseDetector().detect();
        for (BroadphasePair<Body, BodyFixture> pair : pairs){
            Body body1 = pair.getCollidable1();
            Body body2 = pair.getCollidable2();
            BodyFixture fixture1 = pair.getFixture1();
            BodyFixture fixture2 = pair.getFixture2();
            Transform transform1 = body1.getTransform();
            Transform transform2 = body2.getTransform();
            Convex convex2 = fixture2.getShape();
            Convex convex1 = fixture1.getShape();
            Penetration p = new Penetration();
            if (np.detect(convex1,transform1,convex2,transform2,p)){
                if (player == body1 || player == body2){
                    System.out.println("Player collide");
                }
            }
        }
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
    private void addObstacle() {
        Obstacle obstacle1 = new Obstacle(new Circle(20), 0, MassType.INFINITE, 0, new Vector2(0, 0), new Vector2(-200, 0), 0);
        this.world.addBody(obstacle1);
    }
}