package simulations;

import org.dyn4j.geometry.Vector2;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Camera {
    private Vector2 position;
    public Camera() {
        this(0, 0);
    }

    public Camera(double x, double y) {
        this.position = new Vector2(x, y);
    }

    public void render(SimulationFrame frame, Graphics2D g2d, double elapsedTime) {
        matchToCameraPosition(frame);

        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (int i = 0; i < frame.world.getBodyCount(); i++) {
            GameObject gameObject = (GameObject) frame.world.getBody(i);
            gameObject.render(g2d, frame.scale);
        }

    }

    private void matchToCameraPosition(SimulationFrame frame) {
        AffineTransform move = frame.getMove();
        move.setToTranslation(frame.getPosition().x - this.position.x, frame.getPosition().y - this.position.y);
    }

    public void follow(GameObject gameObject) {
        this.setPosition(gameObject.getPosition());
    }

    /**
     * this function return the postion of camera
     * @return
     */
    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }
}
