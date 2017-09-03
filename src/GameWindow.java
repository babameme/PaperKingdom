
import bases.inputs.InputManager;
import bases.scenes.SceneManager;
import bases.settings.Settings;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.dynamics.World;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Rectangle;
import org.dyn4j.geometry.Triangle;
import org.dyn4j.geometry.Vector2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;
//import java.awt.image.BufferedImage;

/**
 * Created by huynq on 7/29/17.
 */
public class GameWindow extends JFrame {
    public static final double SCALE = 10.0;
    public static final double NANO_TO_BASE = 1.0e9;

    private long lastTimeUpdate, currentTime, diff;
    private double elapsedTime;

    protected Canvas canvas;
    Dimension size;

    private AffineTransform yFlip = AffineTransform.getScaleInstance(1,-1);
    private AffineTransform move = AffineTransform.getTranslateInstance(400, -300);

    InputManager inputManager = InputManager.instance;

    private World world;

    public GameWindow() {
        setupGameLoop();
        setupWindow();
        initializeWorld();
    }

    private void initializeWorld() {
        world = new World();

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

        /*// create a triangle object
        Triangle triShape = new Triangle(
                new Vector2(0.0, 0.5),
                new Vector2(-0.5, -0.5),
                new Vector2(0.5, -0.5));
        GameObject triangle = new GameObject();
        triangle.addFixture(triShape);
        triangle.setMass(MassType.NORMAL);
        triangle.translate(-1.0, 4.0);
        // test having a velocity
        triangle.getLinearVelocity().set(0.5, 0.0);
        triangle.setAngularVelocity(2.5);
        this.world.addBody(triangle);*/
    }

    private void setupGameLoop() {
        lastTimeUpdate = -1;
    }

    private void setupWindow() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("<Enter title here>");
        size = new Dimension(800,600);
        this.canvas = new Canvas();
        this.canvas.setPreferredSize(size);
        this.canvas.setMinimumSize(size);
        this.canvas.setMaximumSize(size);
        this.add(this.canvas);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);


        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                inputManager.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                inputManager.keyReleased(e);
            }
        });

        //Settings.instance.setWindowInsets(this.getInsets());
    }

    public void loop() {
        this.canvas.setIgnoreRepaint(true);
        this.canvas.createBufferStrategy(2);
        while(true) {
            if (lastTimeUpdate == -1) {
                lastTimeUpdate = System.nanoTime();
            }

            currentTime = System.nanoTime();
            diff = currentTime - lastTimeUpdate;
            if (diff > 17000000) {
                gameLoop();
                elapsedTime = diff / NANO_TO_BASE;
                lastTimeUpdate = currentTime;
                this.world.update(elapsedTime);
                //System.out.println("Update world");
            }
        }
    }

    private void gameLoop() {
        Graphics2D g2d = (Graphics2D) this.canvas.getBufferStrategy().getDrawGraphics();
        g2d.transform(yFlip);
        g2d.transform(move);
        run();
        render(g2d);
        g2d.dispose();
        BufferStrategy strategy = this.canvas.getBufferStrategy();
        if (!strategy.contentsLost()){
            strategy.show();
        }
        Toolkit.getDefaultToolkit().sync();
        SceneManager.changeSceneIfNeeded();
    }

    private void run() {
        //GameObject.runAll();
        //GameObject.runAllActions();
    }

    private void render(Graphics2D g2d) {
       g2d.setColor(Color.WHITE);
       g2d.fillRect(-400,-300,800,600);
       g2d.translate(0.0, -1.0 * SCALE);
        for (int i = 0; i < this.world.getBodyCount(); i++) {
            GameObject gameObject = (GameObject) this.world.getBody(i);
            gameObject.render(g2d);
        }
    }
}
