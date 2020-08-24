package sprite;

import biuoop.DrawSurface;
import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;

/**
 * The type Wide easy back ground.
 */
public class WideEasyBackGround implements Sprite {
    private Color color;
    private Rectangle rectangle;
    private Point location;

    /**
     * Instantiates a new Wide easy back ground.
     *
     * @param color     the color the background
     * @param rectangle the shape of the background.
     * @param location  the of the "sun" in the background;
     */
    public WideEasyBackGround(Color color, Rectangle rectangle, Point location) {
        this.color = color;
        this.rectangle = rectangle;
        this.location = location;
    }
    /**
     * drawOn draw the background that fits to Wide Easy level.
     *
     * draw circles in several lines that look like a sun.
     *
     *
     * @param surface the surface that we draw on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.gray);
        surface.fillRectangle(0, 0, surface.getWidth(), surface.getHeight());
        surface.setColor(this.color);
        surface.fillRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        int r = 80, x = (int) location.getX(), y = (int) location.getY();
        int x2 = 30, y2 = 250;
        Color c = new Color(239, 231, 176);
        surface.setColor(c);
        for (int i = 0; i < 100; i++) {
            surface.drawLine(x, y, x2, y2);
            x2 = x2 + 7;
        }
        int loc = 176;
        for (int i = 0; i < 3; i++) {
            c = new Color(250, 250, loc);
            surface.fillCircle(x, y, r);
            surface.setColor(c);
            loc = loc - 85;
            r = r - 10;
        }
    }
    /**
     * timePassed is responsible to make the changes in the environment of each Sprite object.
     *
     * in background it does nothing.
     */
    public void timePassed() {
        return;
    }
}
