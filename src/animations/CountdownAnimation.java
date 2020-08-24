package animations;

import game.SpriteCollection;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Countdown animation.
 */
public class CountdownAnimation implements Animation {
    private SpriteCollection sprites;
    private double seconds;
    private int count;
    private Boolean running;
    private long sleep;
    private Boolean flag;

    /**
     * Instantiates a new Countdown animation.
     *
     * @param numOfSeconds the num of seconds the needs to be wait.
     * @param countFrom    the number that we count from.
     * @param gameScreen   the collection of sprite that need to be draw.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.sprites = gameScreen;
        this.seconds = numOfSeconds;
        this.count = countFrom;
        this.running = true;
        this.sleep = 1000 * (long) seconds / count;
        this.flag = true;
    }

    /**
     * doOneframe responsible to draw the sprites and wait equall seconds to each number.
     *
     * @param d the surface that the sprite draw on.
     */
    public void doOneFrame(DrawSurface d) {
        if (count == -1) {
            this.running = false;
            return;
        }
        this.sprites.drawAllOn(d);
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        if (count > 0) {
            String value = String.valueOf(this.count);
            d.setColor(Color.BLACK);
            d.drawText(d.getWidth() / 2 - 5, d.getHeight() / 2 + 100, value, 60);
            d.drawText(d.getWidth() / 2 - 9, d.getHeight() / 2 + 100, value, 60);
            d.drawText(d.getWidth() / 2 - 7, d.getHeight() / 2 + 102, value, 60);
            d.drawText(d.getWidth() / 2 - 7, d.getHeight() / 2 + 98, value, 60);
            d.setColor(Color.WHITE);
            d.drawText(d.getWidth() / 2 - 7, d.getHeight() / 2 + 100, value, 60);
        }
        count--;
        if (flag) {
            flag = false;
            return;
        }
        sleeper.sleepFor(this.sleep);
    }

    /**
     * shouldStop tells the animation runner if it can run the countdown.
     *
     * @return this.running a boolean the tells if to stop or not.
     */
    public boolean shouldStop() {
        return !this.running;
    }
}