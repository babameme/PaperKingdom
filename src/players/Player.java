package players;

import bases.inputs.MouseManager;
import bases.renderers.FixtureRenderer;
import bases.settings.Settings;
import org.dyn4j.collision.broadphase.BroadphasePair;
import org.dyn4j.collision.narrowphase.Gjk;
import org.dyn4j.collision.narrowphase.NarrowphaseDetector;
import org.dyn4j.collision.narrowphase.Penetration;
import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.geometry.*;
import bases.GameObject;
import paper.friends.Friend;
import paper.obstacles.Obstacle;
import paper.simulations.SimulationFrame;

import java.awt.*;
import java.util.List;

public class Player extends GameObject{
    private static final double SPEED = 110;
    private Color color;
    private Vector2 linearVelocity;
    NarrowphaseDetector np = new Gjk();

    public Player(Convex convex, double rotation, MassType massType, double angularVelocity, Vector2 position) {
        super();
        convex.rotate(rotation);
        this.addFixture(convex);
        this.setMass(massType);
        this.setAngularVelocity(angularVelocity);
        this.setLinearVelocity(SPEED, 0);
        //linearVelocity = new Vector2(SPEED, 0);
        this.setColor(Color.magenta);
        this.translate(position);

        this.renderer = new FixtureRenderer();
    }

    //TODO: Fix to drag
    @Override
    public void update(double elapsedTime) {
        super.update(elapsedTime);
        this.setAngularVelocity(0);

        double delta = ((-this.transform.getTranslationY() + 212) - MouseManager.instance.postition.y);
        this.getTransform().setTranslationY(Settings.instance.getGamePlayHeight()/2 - MouseManager.instance.postition.y);
        //this.setLinearVelocity(SPEED, delta * SPEED);
//        if (Math.abs(delta) > 1) {
//            this.setLinearVelocity(SPEED, Math.signum(delta) * 200);
//        } else {
//            this.setLinearVelocity(SPEED, 0);
//        }
//        if (MouseManager.instance.oldPoint != null && MouseManager.instance.movedPoint != null){
//            int maxMove = MouseManager.instance.oldPoint.y - MouseManager.instance.movedPoint.y;
//            int t = 0;
//            //TODO: Velocity when collide
//            while ((!CollideWith(Friend.class)) && (!CollideWith(Obstacle.class) && t < Math.abs(maxMove))){
//                t ++;
//                this.translate(0, Math.signum(maxMove));
//            }
//            if (CollideWith(Obstacle.class)){
//                this.getLinearVelocity().set(SPEED, SPEED);
//                SimulationFrame.world.update(elapsedTime);
//            }
//            //this.translate(0, MouseManager.instance.oldPoint.y - MouseManager.instance.movedPoint.y);
//            MouseManager.instance.oldPoint = MouseManager.instance.movedPoint;
//            MouseManager.instance.movedPoint = null;
//        }
    }

    public <T extends GameObject> boolean CollideWith(Class<T> classz){
        List<BroadphasePair<Body, BodyFixture>> pairs = SimulationFrame.world.getBroadphaseDetector().detect();
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
                if ((this == body1 && body2.getClass() == classz) ||
                        (body1.getClass() == classz && this == body2)){
                    return true;
                }
            }
        }
        return false;
    }
}
