package animations;

import biuoop.DrawSurface;

/**
 * The interface Animation.
 */
public interface Animation {
    /**
     * Do one frame.
     *
     * @param d the surface that the sprites draw on
     */
    void doOneFrame(DrawSurface d);

    /**
     * Should stop boolean responsible to stop the animation runner.
     *
     * @return the boolean that tells us if to stop the animation runner.
     */
    boolean shouldStop();
}