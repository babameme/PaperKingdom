package simulations;
import bases.renderers.FixtureRenderer;
import bases.renderers.Graphics2DRenderer;
import bases.renderers.ImageRenderer;
import bases.renderers.Renderer;
import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.geometry.Convex;
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
        this.color = new Color(
                (float)Math.random() * 0.5f + 0.5f,
                (float)Math.random() * 0.5f + 0.5f,
                (float)Math.random() * 0.5f + 0.5f);
        position = new Vector2(0.0, 0.0);
    }


    public GameObject(ImageRenderer imageRenderer){
        this.renderer = imageRenderer;
        position = new Vector2(0.0, 0.0);
    }

    public GameObject(FixtureRenderer fixtureRenderer){
        this.renderer = fixtureRenderer;
    }

    public GameObject(Color color){
        this.color = color;
    }

    public void setColor(Color color) {
        this.color = color;
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
