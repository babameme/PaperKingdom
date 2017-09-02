package bases.physics;

import bases.GameObject;
import bases.Vector2D;

import javax.swing.*;

/**
 * Created by huynq on 8/12/17.
 */
public class BoxCollider extends GameObject {
    private float width;
    private float height;

    public BoxCollider(float x, float y, float width, float height) {
        super();
        this.position.set(x, y);
        this.width = width;
        this.height = height;
    }

    public BoxCollider(float width, float height) {
        this(0, 0, width, height);
    }

    public float left() {
        return this.screenPosition.x - this.width / 2;
    }

    public float right() {
        return this.screenPosition.x + this.width / 2;
    }

    public float top() {
        return this.screenPosition.y - this.height / 2;
    }

    public float bottom() {
        return this.screenPosition.y + this.height / 2;
    }

    public boolean intersects(BoxCollider other) {
        return this.bottom() >= other.top() &&
                this.top() <= other.bottom() &&
                this.right() >= other.left() &&
                this.left() <= other.right();
    }

    public boolean intersects(Vector2D center, float width, float height){
        float top = center.y - height / 2;
        float bottom = center.y + height / 2;
        float left = center.x - width / 2;
        float right = center.x + width / 2;
        return this.bottom() >= top &&
                this.top() <= bottom &&
                this.right() >= left &&
                this.left() <= right;
    }

    @Override
    public String toString() {
        return "BoxCollider{" +
                "width=" + width +
                ", height=" + height +
                ", screenPosition=" + screenPosition +
                '}';
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
}
