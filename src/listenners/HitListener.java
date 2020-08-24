package listenners;

import sprite.Ball;
import sprite.Block;

/**
 * The interface Hit listener.
 */
public interface HitListener {
    /**
     * Hit event.
     *
     * This method is called whenever the beingHit object is hit.
     *
     * @param beingHit the block that are being hit
     * @param hitter   the ball that hit the block.
     */
    void hitEvent(Block beingHit, Ball hitter);
}