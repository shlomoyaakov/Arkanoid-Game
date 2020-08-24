package sprite;

import biuoop.DrawSurface;
import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;

/**
 * The type Final four back ground.
 */
public class FinalFourBackGround implements Sprite {
    private Color color;
    private Rectangle rectangle;
    private Point location;

    /**
     * Instantiates a new Final four back ground.
     *
     * @param color     the color of the background
     * @param rectangle the shape of the background
     * @param location  the location of a paint on the background
     */
    public FinalFourBackGround(Color color, Rectangle rectangle, Point location) {
        this.color = color;
        this.rectangle = rectangle;
        this.location = location;
    }

    /**
     * drawOn draw the background that fits to final four level.
     *
     * draw clouds using circles.
     *
     * @param surface the surface that we draw on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.gray);
        surface.fillRectangle(0, 0, surface.getWidth(), surface.getHeight());
        surface.setColor(this.color);
        surface.fillRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        int r = 40, x = (int) location.getX(), y = (int) location.getY();
        java.awt.Color[] c = {new Color(210, 212, 200), new Color(213, 213, 219),
                new Color(216, 216, 216)};
        int x1 = x - 100, y1 = y, x2 = x - 130, y2 = 600;
        surface.setColor(Color.WHITE);
        for (int i = 0; i < 10; i++) {
            surface.drawLine(x1, y1, x2, y2);
            surface.drawLine(x1 - 400, y1 - 70, x2 - 400, y2);
            x1 = x1 + 10;
            x2 = x2 + 10;
        }
        for (int i = 0; i < 3; i++) {
            surface.setColor(c[i]);
            surface.fillCircle(x - 70, y, r);
            surface.fillCircle(x - 470, y - 70, r);
            surface.fillCircle(x - 90, y + 30, r - 5);
            surface.fillCircle(x - 490, y - 50, r - 5);
            r = 28;
            x = x + 30;
        }

    }

    /**
     * timePassed is responsible to make the changes in the environment of each Sprite object.
     * <p>
     * in background it does nothing.
     */
    public void timePassed() {
        return;
    }
}

