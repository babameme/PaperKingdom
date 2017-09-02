import org.dyn4j.Graphics2DRenderer;
import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.geometry.Convex;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class GameObject extends Body{
    protected Color color;
    private AffineTransform ot, lt;

    public GameObject(){
        //super();
        this.color = new Color(
                (float)Math.random() * 0.5f + 0.5f,
                (float)Math.random() * 0.5f + 0.5f,
                (float)Math.random() * 0.5f + 0.5f);
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void run(){

    }

    public void render(Graphics2D g2d){
        ot = g2d.getTransform();
        lt = new AffineTransform();
        lt.translate(this.transform.getTranslationX() * GameWindow.SCALE,
                this.transform.getTranslationY() * GameWindow.SCALE);
        lt.rotate(this.transform.getRotation());

        g2d.transform(lt);

        for (BodyFixture fixture : this.fixtures){
            Convex convex = fixture.getShape();
            Graphics2DRenderer.render(g2d,convex,GameWindow.SCALE,color);
        }
        g2d.setTransform(ot);
    }
}
