import bases.inputs.MouseManager;
import paper.friends.Friend;
import paper.obstacles.Obstacle;
import org.dyn4j.geometry.*;
import org.dyn4j.geometry.Rectangle;
import players.Player;
import bases.GameObject;
import paper.simulations.SimulationFrame;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class GameWindow extends SimulationFrame{
    private Player player;

    private final class CustomMouseAdapter extends MouseAdapter {
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
//        addFloor();
        addObstacle();
        addFriend();
    }

    private void addFriend() {
        Friend friend = new Friend(new Rectangle(20, 20), 0.4, MassType.FIXED_ANGULAR_VELOCITY,3.5,  new Vector2(0,0), new Vector2(0, 30));
        friend.setColor(Color.BLUE);
        this.world.addBody(friend);

        friend = new Friend(new Circle(5), 0, MassType.NORMAL, 0, new Vector2(0.0), new Vector2(-120,-50));
        this.world.addBody(friend);

        friend = new Friend(new Circle(5), 0, MassType.NORMAL, 0, new Vector2(0.0), new Vector2(-120,-60));
        this.world.addBody(friend);

        friend = new Friend(new Circle(5), 0, MassType.NORMAL, 0, new Vector2(0.0), new Vector2(-120,-70));
        this.world.addBody(friend);

        friend = new Friend(new Circle(5), 0, MassType.NORMAL, 0, new Vector2(0.0), new Vector2(-120,-80));
        this.world.addBody(friend);

        friend = new Friend(new Circle(5), 0, MassType.NORMAL, 0, new Vector2(0.0), new Vector2(-120,-90));
        this.world.addBody(friend);

        friend = new Friend(new Circle(5), 0, MassType.NORMAL, 0, new Vector2(0.0), new Vector2(-120,-100));
        this.world.addBody(friend);
    }

    private void addPlayer() {
        player = new Player(new Circle(10), 0, MassType.FIXED_LINEAR_VELOCITY, 0, new Vector2(-300,0));
        //player.getFixture(0).setSensor(true);

        this.world.addBody(player);
    }

    @Override
    protected void update(Graphics2D g2d, double elapsedTime) {
        //MouseManager.instance.oldPoint = MouseManager.instance.movedPoint;
        int x = MouseInfo.getPointerInfo().getLocation().x - this.getLocationOnScreen().x;
        int y = MouseInfo.getPointerInfo().getLocation().y - this.getLocationOnScreen().y;
        MouseManager.instance.postition = new Vector2(x, y);
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

    private void addObstacle() {
        Obstacle obstacle = new Obstacle(new Rectangle(100, 250), 0, MassType.INFINITE, 0, new Vector2(0, 0), new Vector2(-350, 175));
        this.world.addBody(obstacle);

        obstacle = new Obstacle(new Rectangle(100, 250), 0, MassType.INFINITE, 0, new Vector2(0, 0), new Vector2(-350, -150));
        this.world.addBody(obstacle);

        obstacle = new Obstacle(new Rectangle(100, 250), 0, MassType.INFINITE, 0, new Vector2(0, 0), new Vector2(350, 175));
        this.world.addBody(obstacle);

        obstacle = new Obstacle(new Rectangle(100, 250), 0, MassType.INFINITE, 0, new Vector2(0, 0), new Vector2(350, -150));
        this.world.addBody(obstacle);

        obstacle = new Obstacle(new Rectangle(80, 280), 0, MassType.INFINITE, 0, new Vector2(0, 0), new Vector2(-120, 100));
        this.world.addBody(obstacle);

        obstacle = new Obstacle(new Rectangle(80, 280), 0, MassType.INFINITE, 0, new Vector2(0, 0), new Vector2(-120, -250));
        this.world.addBody(obstacle);

        obstacle = new Obstacle(new Rectangle(80, 280), 0, MassType.INFINITE, 0, new Vector2(0, 0), new Vector2(150, 150));
        this.world.addBody(obstacle);

        obstacle = new Obstacle(new Rectangle(80, 280), 0, MassType.INFINITE, 0, new Vector2(0, 0), new Vector2(150, -200));
        this.world.addBody(obstacle);

        obstacle = new Obstacle(new Rectangle(100, 10), 0, MassType.INFINITE, 5.0, new Vector2(0,0), new Vector2(0,-50));
        this.world.addBody(obstacle);
    }
}