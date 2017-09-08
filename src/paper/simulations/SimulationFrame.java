package paper.simulations;

import bases.GameObject;
import org.dyn4j.dynamics.World;
import org.dyn4j.geometry.Vector2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;

public abstract class SimulationFrame extends JFrame{
    public static final double NANO_TO_BASE = 1.0e9;
    protected final Canvas canvas;
    protected final World world;
    protected final double scale;
    protected final Dimension size;
    private long lastTimeUpdate, currentTime, diff;
    private double elapsedTime;
    private BufferStrategy strategy;
    private int w, h;

    private Camera camera;
    private Vector2 position;

    private final AffineTransform yFlip, move;

    public SimulationFrame(String name, double scale) {
        super(name);
        this.scale = scale;
        this.world = new World();
        size = new Dimension(736, 414);
        this.canvas = new Canvas();
        this.canvas.setPreferredSize(size);
        this.canvas.setMinimumSize(size);
        this.canvas.setMaximumSize(size);
        this.add(this.canvas);
        this.setResizable(false);

        this.pack();

        w = this.canvas.getWidth();
        h = this.canvas.getHeight();
        position = new Vector2(w / 2, -h / 2);
        //System.out.println(Integer.toString(w) + " " + Integer.toString(h));
        yFlip = AffineTransform.getScaleInstance(1,-1);
        move = AffineTransform.getTranslateInstance(position.x, position.y);
        camera = new Camera();
        this.initializeWorld();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
            }
        });
    }

    protected abstract void initializeWorld();

    public void loop(){
        this.setVisible(true);
        this.canvas.setIgnoreRepaint(true);
        this.canvas.createBufferStrategy(2);
        lastTimeUpdate = System.nanoTime();
        while(true) {
            currentTime = System.nanoTime();
            diff = currentTime - lastTimeUpdate;
            //if (diff > 17000000) {
                gameLoop();
                elapsedTime = diff / NANO_TO_BASE;
                lastTimeUpdate = currentTime;
            //}
        }
    }

    private void gameLoop(){
        Graphics2D g2d = (Graphics2D) this.canvas.getBufferStrategy().getDrawGraphics();
        this.transform(g2d);
        this.clear(g2d);
        this.update(g2d, elapsedTime);
        this.render(g2d,elapsedTime);
        g2d.dispose();
        strategy = this.canvas.getBufferStrategy();
        if (!strategy.contentsLost()){
            strategy.show();
        }
        Toolkit.getDefaultToolkit().sync();
    }

    protected void update(Graphics2D g2d, double elapsedTime) {
        for (int i = 0; i < this.world.getBodyCount(); i++) {
            GameObject gameObject = (GameObject) this.world.getBody(i);
            gameObject.update();
//            if(i == 1)
//            camera.follow(gameObject);
        }
        this.world.update(elapsedTime);
    }

    private void render(Graphics2D g2d, double elapsedTime) {
        camera.render(this, g2d, elapsedTime);
    }

    protected void render(Graphics2D g2d, GameObject gameObject){
        gameObject.render(g2d, this.scale);
    }

    protected void transform(Graphics2D g2d) {
        g2d.transform(yFlip);
        g2d.transform(move);
    }

    protected void clear(Graphics2D g2d){
        g2d.setColor(Color.YELLOW);
        g2d.fillRect(-w/2, -h/2, w, h);
    }

    public AffineTransform getMove() {
        return move;
    }

    public Vector2 getPosition() {
        return position;
    }
}
