package animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The type Key press stoppable animation.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keyboard;
    private String key;
    private Animation animation;
    private Boolean stop;
    private boolean isAlreadyPressed;

    /**
     * Instantiates a keypressstoppableanimation.
     *
     * @param key the keyboardSensor that gets information from the keyboard.
     * @param sensor the score of the player.
     * @param animation the animation keypressstoppableanimation is wraping.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.keyboard = sensor;
        this.key = key;
        this.animation = animation;
        this.stop = false;
        this.isAlreadyPressed = true;
    }
    /**
     * do one frame responsible to call the do on frame of the inner animation.
     *
     * the methods also checks if the stopping key was pressed, if it is
     * and it wasn't pressed before the animation was created then the animation
     * stops.
     *
     * @param d the surface the method draw on.
     */
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
        if (this.keyboard.isPressed(this.key) && isAlreadyPressed) {
            return;
        }
        if (!this.keyboard.isPressed(this.key)) {
            isAlreadyPressed = false;
        }
        if (this.keyboard.isPressed(this.key)) {
            this.stop = true;
        }
    }
    /**
     * shouldStop tells the animation runner if it can run the game over screen or not.
     * @return this.running a boolean the tells if to stop or not.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
