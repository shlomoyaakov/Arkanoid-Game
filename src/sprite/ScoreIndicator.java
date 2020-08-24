package sprite;

import biuoop.DrawSurface;
import geometry.Rectangle;
import game.Counter;

import java.awt.Color;


/**
 * The type Score indicator.
 */
public class ScoreIndicator implements Sprite {
    private Rectangle rectangle;
    private Counter score;
    private double location;

    /**
     * Instantiates a new Score indicator.
     *
     * @param rec   the rectangle that the level's name is being printed on.
     * @param score the counter of the score.
     * @param place the place the location on the rectangle that the level's name is going to be print.
     */
    public ScoreIndicator(Rectangle rec, Counter score, double place) {
        this.rectangle = rec;
        this.score = score;
        this.location = place;
    }

    /**
     * drawOn draw the the score of the player on the top of the screen, according to counter score.
     * <p>
     * the method print the score right in the middle of the rectangle of the field.
     * also the method draw the rectangle.
     *
     * @param d the surface that we draw on.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.lightGray);
        int width = (int) this.rectangle.getWidth();
        int height = (int) this.rectangle.getHeight();
        int x = (int) this.rectangle.getUpperLeft().getX();
        int y = (int) this.rectangle.getUpperLeft().getY();
        d.fillRectangle(x, y, width, height);
        d.setColor(Color.BLACK);
        //draw the number in the middle of the block.
        int y2 = (int) (this.rectangle.getUpperLeft().getY() + 0.7 * this.rectangle.getHeight());
        int x2 = (int) (this.rectangle.getUpperLeft().getX() + this.location * this.rectangle.getWidth());
        String value = String.valueOf(this.score.getValue());
        String txt = "Score: " + value;
        d.drawText(x2, y2, txt, 20);
    }

    /**
     * Time passed responsible to the changes in the environment of the game.
     */
    public void timePassed() {
        return;
    }
}
