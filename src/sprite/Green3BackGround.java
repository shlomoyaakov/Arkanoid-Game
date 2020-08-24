package sprite;

import biuoop.DrawSurface;
import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;

/**
 * The type Green 3 back ground.
 */
public class Green3BackGround implements Sprite {
    private Color color;
    private Rectangle rectangle;
    private Point location;

    /**
     * Instantiates a new Green 3 back ground.
     *
     * @param color     the color of the background.
     * @param rectangle the shape of the background.
     * @param location  the location of a certain draw on the background.
     */
    public Green3BackGround(Color color, Rectangle rectangle, Point location) {
        this.color = color;
        this.rectangle = rectangle;
        this.location = location;
    }
    /**
     * drawOn draw the background that fits to Green3 level.
     *
     * draw a building using rectangles.
     *
     * @param surface the surface that we draw on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.gray);
        surface.fillRectangle(0, 0, surface.getWidth(), surface.getHeight());
        surface.setColor(this.color);
        surface.fillRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        surface.setColor(Color.gray.darker());
        int r = 16, x = (int) location.getX(), y = (int) location.getY(), reduce = 5;
        surface.fillRectangle(x - 8, y, 16, 150);
        java.awt.Color[] c = {new Color(205, 185, 80), new Color(255, 135, 80), Color.WHITE};
        for (int i = 0; i < 3; i++) {
            surface.setColor(c[i]);
            surface.fillCircle(x, y, r);
            r = r - reduce;
        }
        y = y + 150;
        surface.setColor(Color.gray.darker().darker());
        surface.fillRectangle(x - 25, y, 50, 75);
        y = y + 70;
        surface.setColor(Color.gray.darker().darker().darker().darker());
        surface.fillRectangle(x - 80, y, 160, 180);
        y = y + 10;
        surface.setColor(Color.WHITE);
        surface.fillRectangle(x - 70, y, 140, 170);
        x = x - 70 + 18;
        int x2 = x - 18, y2 = y + 29;
        surface.setColor(Color.gray.darker().darker().darker().darker());
        for (int i = 0; i < 4; i++) {
            surface.fillRectangle(x, y, 10, 600 - y);
            surface.fillRectangle(x2, y2, 140, 10);
            y2 = y2 + 39;
            x = x + 30;
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
