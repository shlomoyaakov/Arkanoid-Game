package geometry;

import java.util.Random;

/**
 * @ author Shlomo Yakov
 */
public class Point {
    private double x;
    private double y;

    /**
     * The constructor, initialize a new geometry.Point.
     *
     * @param x the coordinates by x.
     * @param y the coordinates by x.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Distance double between two points.
     *
     * The program calculate the distance between this line to the
     * the other line, and return the number in a double variable.
     *
     * @param other the other
     * @return the distance between this point to the other point.
     */
    public double distance(Point other) {
        double deltaX = this.x - other.getX();
        double deltaY = this.y - other.getY();
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

    /**
     * Boolean equals, check if two points are equals.
     *
     * @param other the other points that need to be compare.
     * @return boolean true/false.
     */
    public boolean equals(Point other) {
        if (other.getX() == this.x && other.getY() == this.y) {
            return true;
        }
        return false;
    }

    /**
     * Gets x coordinate of the point.
     *
     * @return the x coordinate.
     */
    public double getX() {
        return this.x;
    }

    /**
     * Gets y coordinate of the point.
     *
     * @return the y coordinate.
     */
    public double getY() {
        return this.y;
    }

    /**
     * Create a random point in a certain range.
     *
     * @param minRangeX the min range in x coordinates.
     * @param maxRangeX the max range in x coordinates.
     * @param minRangeY the min range in y coordinates.
     * @param maxRangeY the max range in y coordinates.
     * @return point in the given range.
     */
    public static Point randomPoint(int minRangeX, int maxRangeX, int minRangeY, int maxRangeY) {
        Random rand = new Random();
        //Create the x coordinate.
        double x = (Math.random() * ((maxRangeX - minRangeX) + 1)) + minRangeX;
        //Create the y coordinate.
        double y = (Math.random() * ((maxRangeY - minRangeY) + 1)) + minRangeY;
        Point newPoint = new Point(x, y);
        return newPoint;
    }
}