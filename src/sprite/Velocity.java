package sprite;

import geometry.Point;

/**
 * @ author Shlomo Yakov
 */
// game.sprite.Velocity specifies the change in position on the `x` and the `y` axes.
public class Velocity {
    //the fields
    private double dx;
    private double dy;

    /**
     * Instantiates a new game.sprite.Velocity using the x,y components.
     *
     * @param deltaX the x component of the velocity vector.
     * @param deltaY the y component of the velocity vector.
     */
    public Velocity(double deltaX, double deltaY) {
        this.dx = deltaX;
        this.dy = deltaY;
    }

    /**
     * Gives the the x component of the velocity.
     *
     * @return the dx the x component of the velocity vector(double).
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Gives the the y component of the velocity.
     *
     * @return the dy the y component of the velocity vector(double).
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * gives a new point according to the velocity.
     * <p>
     * Take a point with position (x,y) and return a new point with position (x+dx, y+dy)
     *
     * @param p the given point that we want to change according to the velocity.
     * @return the point after the adding the dx ,dy of the velocity.
     */
    public Point applyToPoint(Point p) {
        p = new Point(p.getX() + dx, p.getY() + dy);
        return p;
    }

    /**
     * gives the size of the velocity.
     *
     * @return a double number that indicate the size of the velocity.
     */
    public double getSpeed() {
        return Math.sqrt(this.dx * this.dx + this.dy * this.dy);
    }

    /**
     * create a new game.sprite.Velocity according to the size of the velocity and the angle.
     *
     * @param angle the angle of the velocity.
     * @param speed the size of the velocity.
     * @return a new game.sprite.Velocity.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        //calculate dx ,dy according to cos and sin.
        angle = Math.toRadians(angle);
        double dy = -1 * speed * Math.cos(angle);
        double dx = speed * Math.sin(angle);
        return new Velocity(dx, dy);
    }

}