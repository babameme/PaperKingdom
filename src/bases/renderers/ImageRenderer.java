package bases.renderers;

import bases.Vector2D;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.geometry.Vector2;
import tklibs.SpriteUtils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;
import java.awt.image.BufferedImage;

/**
 * Created by huynq on 8/5/17.
 */
public class ImageRenderer implements Renderer {
    public BufferedImage image;
    private Vector2D anchor;


    public ImageRenderer(BufferedImage image) {
        this.image = image;
        this.anchor = new Vector2D(0.5f, 0.5f);
    }

    public Vector2D getAnchor() {
        return anchor;
    }

    // Factory
    public static ImageRenderer create(String url) {
        return new ImageRenderer(SpriteUtils.loadImage(url));
    }

    @Override
    public void render(Graphics2D g2d, List<BodyFixture> fixtures, double scale, Color color, Vector2 position) {
        g2d.drawImage(image,
                (int)(position.x - (image.getWidth() * anchor.x)),
                (int)(position.y - (image.getHeight() * anchor.y)),
                null);
    }
}
