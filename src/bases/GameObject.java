package bases;
import bases.renderers.Renderer;
import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.Vector2;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class GameObject extends Body{
    protected Color color;
    private AffineTransform ot, lt;
    protected Renderer renderer;
    private Vector2 position;

    public GameObject(){
        //super();
        //this.renderer = new BasicRenrer(convex, color);
        //this.renderer = new ImageRenderer(image);
        super();
        position = new Vector2(0.0, 0.0);
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setRenderer(Renderer renderer) {
        this.renderer = renderer;
    }

    public void render(Graphics2D g2d, double scale){
        ot = g2d.getTransform();
        lt = new AffineTransform();
        lt.translate(this.transform.getTranslationX() * scale,
                this.transform.getTranslationY() * scale);
        lt.rotate(this.transform.getRotation());

        g2d.transform(lt);
        renderer.render(g2d, this.fixtures, scale, color, position);

        g2d.setTransform(ot);
    }

    public void update(){

    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Vector2 getPosition() {
        return this.getTransform().getTranslation();
    }
}
