package game;
/**
 * @ author Shlomo Yakov.
 */

import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import sprite.Collidable;

import java.util.ArrayList;
import java.util.List;

/**
 * The type game.GameLevel environment.
 */
public class GameEnvironment {
    private List<Collidable> list;

    /**
     * Instantiates a new game.GameLevel environment.
     *
     * @param list the list of a collidable objects.
     */
    public GameEnvironment(List<Collidable> list) {
        this.list = list;
    }

    /**
     * Instantiates a new game.GameLevel environment.
     */
    public GameEnvironment() {
        this.list = new ArrayList<Collidable>();
    }

    /**
     * Add collidable object to the list of the collidable.
     *
     * @param c the an collidable object that we want to add to the list.
     */
    public void addCollidable(Collidable c) {
        this.list.add(c);
    }
    /**
     * Remove a collidable object from the list of the collidable.
     *
     * @param c the an collidable object that we want to remove from the list.
     */
    public void removeCollidable(Collidable c) {
        this.list.remove(c);
    }

    /**
     * Gets the information about the closets collision point if exists.
     *
     * the program gets a list of intersection points from the geometry.Rectangle class in
     * the collidable object and if the list is not empty it return game.GameFlow.CollisionInfo that include
     * the collision point the collidable object.
     *
     * @param trajectory a line that indicates the trajectory a ball object.
     * @return an object from game.GameFlow.CollisionInfo class that has the information about the collision or null.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        //if there is no collidable objects in the list of the environment.
        if (this.list.isEmpty()) {
            return null;
        }
        Point cIntersection = null, check;
        List<Point> pointList = null;
        Rectangle r;
        Collidable col = null;
        List<Collidable> list2 = new ArrayList<>(this.list);
        for (Collidable c : list2) {
            r = c.getCollisionRectangle();
            //gets the collision points from the rectangle.
            pointList = r.intersectionPoints(trajectory);
            //if there is no intersection points
            if (pointList.isEmpty()) {
                continue;
            }
            //for the first round
            if (cIntersection == null) {
                cIntersection = trajectory.closestIntersectionToStartOfLine(r);
                col = c;
                continue;
            }
            //gets the closets intersection point
            check = trajectory.closestIntersectionToStartOfLine(r);
            if (cIntersection.distance(trajectory.start()) > check.distance(trajectory.start())) {
                cIntersection = check;
                col = c;
            }
        }
        //if there is no intersection.
        if (cIntersection == null) {
            return null;
        }
        //return the information about the closets collision.
        return new CollisionInfo(col, cIntersection);
    }

}