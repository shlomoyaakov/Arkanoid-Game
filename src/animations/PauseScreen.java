package animations;

import biuoop.KeyboardSensor;
import biuoop.DrawSurface;

/**
 * The type Pause screen.
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * Instantiates a new pause screen.
     *
     * @param k the keyboardSensor that gets information from the keyboard.
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }

    /**
     * do one frame responsible to draw on the surface the pause screen.
     *
     * @param d the surface the method draw on.
     */
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    /**
     * shouldStop tells the animation runner if it can run the game over screen or not.
     *
     * @return this.running a boolean the tells if to stop or not.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}