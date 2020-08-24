package sprite;
/**
 * @ author Shlomo Yakov.
 */

import biuoop.DrawSurface;

/**
 * The interface sprite.Sprite.
 */
public interface Sprite {
    /**
     * Draw on, draw objects on the surface.
     *
     * @param d the d
     */
    void drawOn(DrawSurface d);

    /**
     * Time passed responsible to the changes in the environment of the game.
     */
    void timePassed();
}
