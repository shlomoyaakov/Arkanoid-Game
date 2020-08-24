package game;

import geometry.Point;
import sprite.Collidable;

/**
 * The type Collision info.
 */
public class CollisionInfo {
    private Collidable object;
    private Point collision;

    /**
     * Instantiates a new Collision info.
     *
     * @param col          the collidable objects that involve in the collision.
     * @param intersection the closets intersection point.
     */
    public CollisionInfo(Collidable col, Point intersection) {
        this.object = col;
        this.collision = intersection;
    }

    /**
     * gives Collision point the point of the closest collision.
     *
     * @return the point
     */
    public Point collisionPoint() {
        return this.collision;
    }

    /**
     * gives Collision object the collidable object involve in the collision.
     *
     * @return the collidable object that involve in the collision.
     */
    public Collidable collisionObject() {
        return this.object;
    }
}