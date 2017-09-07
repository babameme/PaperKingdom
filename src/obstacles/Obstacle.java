package obstacles;
import bases.GameObject;
import org.dyn4j.geometry.Convex;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Vector2;

import java.awt.*;

public class Obstacle extends GameObject {
    private final Color type0 = Color.RED;
    private final Color type1 = Color.blue;
    private Convex convex;
    private double rotation;
    private MassType massType;
    private double angularVelocity;
    private Vector2 linearVelocity;
    private Vector2 position;
    private Color color;
    private int type;
    /**
     * Type = 0 : Ke dich
     * Type = 1 : Ban cua minh
     */
    public Obstacle(Convex convex, double rotation, MassType massType, double angularVelocity, Vector2 linearVelocity, Vector2 position, int type) {
        this.convex = convex;
        this.rotation = rotation;
        this.massType = massType;
        this.angularVelocity = angularVelocity;
        this.linearVelocity = linearVelocity;
        this.position = position;
        this.type = type;
        switch (this.type){
            case 0:
                color = type0;
                break;
            case 1:
                color = type1;
                break;
        }
        convex.rotate(rotation);
        this.addFixture(convex);
        this.setMass(massType);
        this.setColor(color);
        this.setAngularVelocity(angularVelocity);
        this.setLinearVelocity(linearVelocity);
        this.translate(position);
    }
}
