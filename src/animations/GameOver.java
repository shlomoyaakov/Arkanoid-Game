package animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The type Game over.
 */
public class GameOver implements Animation {

    private KeyboardSensor keyboard;
    private boolean stop;
    private int score;

    /**
     * Instantiates a new Game over.
     *
     * @param k the keyboardSensor that gets information from the keyboard.
     * @param s the score of the player.
     */
    public GameOver(KeyboardSensor k, int s) {
        this.keyboard = k;
        this.stop = false;
        this.score = s;
    }
    /**
     * do one frame respobsible to draw on the surface the screen.
     *
     * @param d the surface the method draw on.
     */
    public void doOneFrame(DrawSurface d) {
        String txt = "game Over. Your score is " + Integer.toString(this.score);
        d.drawText(d.getWidth() / 4, d.getHeight() / 2, txt, 32);
    }
    /**
     * shouldStop tells the animation runner if it can run the game over screen or not.
     * @return this.running a boolean the tells if to stop or not.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
