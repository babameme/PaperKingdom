
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
    public static final double SCALE = 45.0;
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

        Rectangle floorRect = new Rectangle(15.0, 1.0);
        GameObject floor = new GameObject();
        floor.addFixture(new BodyFixture(floorRect));
        floor.setMass(MassType.INFINITE);

        // move the floor down a bit
        //floor.translate(0.0, -4.0);
        floor.translate(0.0, -4.0);
        this.world.addBody(floor);

        // create a triangle object
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
        this.world.addBody(triangle);
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

        /*this.setSize(Settings.instance.getWindowWidth(), Settings.instance.getWindowHeight());

        this.setVisible(true);

        this.backbufferImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        this.backbufferGraphics = (Graphics2D) this.backbufferImage.getGraphics();

        this.blackBackground = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D backgroundGraphics = (Graphics2D) this.blackBackground.getGraphics();
        backgroundGraphics.setColor(Color.PINK);
        backgroundGraphics.fillRect(0, 0, this.getWidth(), this.getHeight());
        backgroundGraphics.dispose();*/

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
       g2d.setColor(Color.PINK);
       g2d.fillRect(-400,-300,800,600);
       g2d.translate(0.0, -1.0 * SCALE);
        for (int i = 0; i < this.world.getBodyCount(); i++) {
            GameObject gameObject = (GameObject) this.world.getBody(i);
            gameObject.render(g2d);
        }
    }
}
