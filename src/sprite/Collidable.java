package sprite; /**
 * @ author Shlomo Yakov.
 */

import geometry.Point;
import geometry.Rectangle;

/**
 * The interface sprite.Collidable.
 */
public interface Collidable {
    /**
     * Gets collision rectangle.
     *
     * @return the collision rectangle
     */
    Rectangle getCollisionRectangle();

    /**
     * Hit velocity responsible to return the updated velocity.
     *
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity.
     * @param hitter the ball that hit the block.
     * @return the updated velocity
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}