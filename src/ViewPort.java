import org.dyn4j.geometry.Vector2;
import bases.GameObject;

public class ViewPort {
    private Vector2 position;
    private Vector2 followOffset;
    public ViewPort(){
        position = Vector2.ZERO.clone();
        followOffset = Vector2.ZERO.clone();
    }

    public void follow(GameObject gameObject){
        position = gameObject.getLocalCenter().add(followOffset);
    }

    public Vector2 translate(Vector2 screenPosition){
        return screenPosition.subtract(this.position);
    }

    public Vector2 getFollowOffset() {
        return followOffset;
    }
}
