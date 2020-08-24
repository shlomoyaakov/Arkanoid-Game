package sprite;

import biuoop.DrawSurface;
import geometry.Rectangle;

import java.awt.Color;

/**
 * The type Level indicator.
 */
public class LevelIndicator implements Sprite {
    private String name;
    private Rectangle rectangle;
    private double location;

    /**
     * Instantiates a new Level indicator.
     *
     * @param rec   the rectangle that the level's name is being printed on.
     * @param place the place the location on the rectangle that the level's name is going to be print.
     * @param name  the name of the level.
     */
    public LevelIndicator(Rectangle rec, double place, String name) {
        this.name = name;
        this.rectangle = rec;
        this.location = place;
    }
    /**
     * drawOn draw draw the level name on the rectangle.
     *
     * @param d the surface that we draw on.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        //draw the number in the middle of the rectangle.
        int y = (int) (this.rectangle.getUpperLeft().getY() + 0.7 * this.rectangle.getHeight());
        int x = (int) (this.rectangle.getUpperLeft().getX() + this.location * this.rectangle.getWidth());
        String txt = "Level Name : " + this.name;
        d.drawText(x, y, txt, 20);
    }

    /**
     * Time passed responsible to the changes in the environment of the game.
     */
    public void timePassed() {
        return;
    }
}
