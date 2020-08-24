package sprite;
/**
 * @ author Shlomo Yakov.
 */
import biuoop.DrawSurface;
import geometry.Line;
import geometry.Point;
import game.GameEnvironment;
import game.GameLevel;
import game.CollisionInfo;
import java.awt.Color;

/**
 * The type sprite.Ball.
 */
public class Ball implements Sprite {
    //the fields of the ball
    private Point center;
    private int radius;
    private java.awt.Color color;
    private Velocity velocity;
    private Line trajectory;
    private GameEnvironment environment;

    /**
     * Instantiates a new sprite.Ball using the center geometry.Point.
     *
     * @param center the center point of the ball.
     * @param r      the radius of the ball.
     * @param color  the color of the ball.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
    }

    /**
     * Instantiates a new sprite.Ball using the x,y coordinates of the center of the ball.
     *
     * @param x     the x coordinate of the ball.
     * @param y     the y coordinate of the ball.
     * @param r     the radius of the ball.
     * @param color the color of the ball.
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this.center = new Point((double) x, (double) y);
        this.radius = r;
        this.color = color;
    }

    /**
     * Gets the x coordinate of the center of the ball.
     *
     * @return the x coordinate of the center in int.
     */
    public int getX() {
        int x = (int) this.center.getX();
        return x;
    }

    /**
     * Gets the y coordinate of the center of the ball.
     *
     * @return the y coordinate of the center in int.
     */
    public int getY() {
        int y = (int) this.center.getY();
        return y;
    }

    /**
     * gives the radius of the ball.
     *
     * @return the radius of the ball(int).
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * gives the color of the ball.
     *
     * @return the color of the ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * drawOn draw the ball according to its center and radius in the given surface.
     *
     * @param surface the surface that we draw on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        //draw by its center.
        int x = (int) this.center.getX();
        int y = (int) this.center.getY();
        surface.fillCircle(x, y, this.radius);
        surface.setColor(Color.BLACK);
        surface.drawCircle(x, y, this.radius);
    }

    /**
     * Sets velocity to the ball using a vector.
     *
     * @param v the the game.sprite.Velocity that we want to set to the ball.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
        Point edgeV = this.velocity.applyToPoint(this.center);
        this.trajectory = new Line(this.center, edgeV);
    }

    /**
     * Sets velocity to the ball using delta x and delta y.
     * <p>
     * The program sets velocity and also makeing a line to the ball
     * which indicates the trajectory of the ball.
     *
     * @param dx the component in the axis x of the velocity vector.
     * @param dy the component in the axis x of the velocity vector.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
        Point edgeV = this.velocity.applyToPoint(this.center);
        this.trajectory = new Line(this.center, edgeV);
    }

    /**
     * GetVelocity gives the velocity of the ball.
     *
     * @return the velocity
     */
    public Velocity getVelocity() {
        return this.velocity;
    }


    /**
     * Sets environment.
     * <p>
     * Using the environment the ball will know if it is going to collide with a collidable, and the
     * exact information about the collision if it is going to collide.
     *
     * @param env the env
     */
    public void setEnvironment(GameEnvironment env) {
        this.environment = env;
    }

    /**
     * timePassed is responsible to make the changes in the environment of each sprite.Sprite object.
     * <p>
     * In ball class timePassed is calling to moveOneStep to update the position of the ball.
     */
    public void timePassed() {
        moveOneStep();
    }

    /**
     * Add to game.
     * <p>
     * add the ball to an object from game.GameLevel class, connect the ball to the sprites list.
     *
     * @param g the game that the ball is participate in
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * Remove the ball from the game.
     * <p>
     * remove the ball from the sprite list that in the game fiels.
     *
     * @param g the game that the ball is participate in
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }

    /**
     * Move one step.
     * <p>
     * the program responsible to the changes in the location of the ball in the surface,
     * and make sure that it doesn't go throw collidable objects.
     */
    public void moveOneStep() {
        if (velocity == null) {
            return;
        }
        Collidable object;
        CollisionInfo colInfo;
        double x, y, dx = this.velocity.getDx(), dy = this.velocity.getDy();
        //checks if there is collide in the next step.
        colInfo = this.environment.getClosestCollision(this.trajectory);
        //If there is
        if (colInfo != null) {
            object = colInfo.collisionObject();
            x = colInfo.collisionPoint().getX();
            y = colInfo.collisionPoint().getY();
            this.center = new Point(x - 0.05 * dx, y - 0.05 * dy);
            //Changes the velocity according to the hit point.
            this.velocity = object.hit(this, colInfo.collisionPoint(), this.velocity);
        } else {
            //If there isn't then the ball can keep its trajectory.
            this.center = this.getVelocity().applyToPoint(this.center);
        }
        //Updating the line that indicates the trajectory of the ball.
        Point edgeV = this.velocity.applyToPoint(this.center);
        this.trajectory = new Line(this.center, edgeV);
    }
}