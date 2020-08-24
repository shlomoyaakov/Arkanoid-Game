package sprite;

import biuoop.DrawSurface;
import geometry.Rectangle;
import game.Counter;

import java.awt.Color;

/**
 * The type Lives indicator.
 */
public class LivesIndicator implements Sprite {
    private Rectangle rectangle;
    private Counter lives;
    private double location;

    /**
     * Instantiates a new Lives indicator.
     *
     * @param rec   the rectangle that the level's name is being printed on.
     * @param lives the counter of the lives.
     * @param place the place the location on the rectangle that the level's name is going to be print.
     */
    public LivesIndicator(Rectangle rec, Counter lives, double place) {
        this.rectangle = rec;
        this.lives = lives;
        this.location = place;
    }

    /**
     * drawOn draw the the lives of the player on the top of the screen, according to counter lives.
     *
     * @param d the surface that we draw on.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        //draw the number in the middle of the rectangle.
        int y = (int) (this.rectangle.getUpperLeft().getY() + 0.7 * this.rectangle.getHeight());
        int x = (int) (this.rectangle.getUpperLeft().getX() + this.location * this.rectangle.getWidth());
        String value = String.valueOf(this.lives.getValue());
        String txt = "Lives: " + value;
        d.drawText(x, y, txt, 20);
    }

    /**
     * Time passed responsible to the changes in the environment of the game.
     */
    public void timePassed() {
        return;
    }
}
