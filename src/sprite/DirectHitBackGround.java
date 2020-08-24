package sprite;

import biuoop.DrawSurface;
import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;

/**
 * The type Direct hit back ground.
 */
public class DirectHitBackGround implements Sprite {
    private Color color;
    private Rectangle rectangle;
    private Point location;

    /**
     * Instantiates a new Direct hit back ground.
     *
     * @param color     the color of the background
     * @param rectangle the shape of the background
     * @param location  the location of a paint on the background
     */
    public DirectHitBackGround(Color color, Rectangle rectangle, Point location) {
        this.color = color;
        this.rectangle = rectangle;
        this.location = location;
    }
    /**
     * drawOn draw the background that fits to directHit level.
     *
     * @param surface the surface that we draw on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.gray);
        surface.fillRectangle(0, 0, surface.getWidth(), surface.getHeight());
        surface.setColor(this.color);
        surface.fillRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        surface.setColor(Color.BLUE);
        int r = 60, x = (int) location.getX(), y = (int) location.getY();
        for (int i = 0; i < 3; i++) {
            surface.drawCircle(x, y, r);
            r = +30;
        }
        //draw the paint by the drawcircle and drawline methods
        surface.drawLine(x, y + 30, x, y + 150);
        surface.drawLine(x, y - 30, x, y - 150);
        surface.drawLine(x + 30, y, x + 150, y);
        surface.drawLine(x - 30, y, x - 150, y);
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
