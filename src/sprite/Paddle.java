package sprite;
/**
 * @ author Shlomo Yakov.
 */

import biuoop.KeyboardSensor;
import biuoop.DrawSurface;
import geometry.Point;
import game.GameLevel;
import geometry.Rectangle;


import java.awt.Color;

/**
 * The type sprite.Paddle.
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rectangle;
    private java.awt.Color color;
    private int[] board = null;
    private int speed;

    /**
     * Instantiates a new sprite.Paddle.
     *
     * @param keyboard the keyboard sensor that allowing us to move the paddle.
     * @param rec      the rectangle of the paddle,its shape.
     */
    public Paddle(biuoop.KeyboardSensor keyboard, Rectangle rec) {
        this.keyboard = keyboard;
        this.rectangle = rec;
        this.speed = 10;
    }

    /**
     * Move left the paddle in surface, updating its position according to DX and the board.
     */
    public void moveLeft() {
        //gets the location of the next step.
        double x = this.rectangle.getUpperLeft().getX() - this.speed;
        //verifying that the paddle is not going to exit the board.
        if (x <= this.board[0]) {
            x = board[0];
        }
        double y = this.rectangle.getUpperLeft().getY();
        Point newUpperLeft = new Point(x, y);
        //updating the location of the paddle on the surface.
        this.rectangle = new Rectangle(newUpperLeft, this.rectangle.getWidth(), this.rectangle.getHeight());
    }

    /**
     * Move right the paddle in surface, updating its position according to DX and the board.
     */
    public void moveRight() {
        //gets the location of the next step.
        double x = this.rectangle.getUpperLeft().getX() + this.speed;
        //verifying that the paddle is not going to exit the board.
        if (x + this.rectangle.getWidth() > this.board[1]) {
            x = board[1] - this.rectangle.getWidth();
        }
        double y = this.rectangle.getUpperLeft().getY();
        Point newUpperLeft = new Point(x, y);
        //updating the location of the paddle on the surface.
        this.rectangle = new Rectangle(newUpperLeft, this.rectangle.getWidth(), this.rectangle.getHeight());
    }

    /**
     * Time passed responsible to the changes in the environment of the game.
     * <p>
     * checks which of the keys were pressed and then called to the appropriate function.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * Sets the speed of the paddle.
     *
     * @param s the speed of the paddle.
     */
    public void setSpeed(int s) {
        this.speed = s;
    }

    /**
     * Sets color to the paddle.
     *
     * @param recColor the color that is going to fill the rectangle of the paddle.
     */
    public void setColor(java.awt.Color recColor) {
        this.color = recColor;
    }

    /**
     * Sets board to the paddle, limits its movements in the surface in order to keep it in the screen.
     *
     * @param min the min limits in the x coordinate.
     * @param max the max limits in the x coordinate.
     */
    public void setBoard(int min, int max) {
        this.board = new int[2];
        this.board[0] = min;
        this.board[1] = max;
    }

    /**
     * Draw on, draw the paddle on the surface.
     * <p>
     * fill the rectangle of the paddle in the color of the field and then draw the
     * frame of the rectangle in black.
     *
     * @param d the surface that the paddle is going to be draw on.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        int width = (int) this.rectangle.getWidth();
        int height = (int) this.rectangle.getHeight();
        int x = (int) this.rectangle.getUpperLeft().getX();
        int y = (int) this.rectangle.getUpperLeft().getY();
        d.fillRectangle(x, y, width, height);
        d.setColor(Color.BLACK);
        d.drawRectangle(x, y, width, height);
    }

    /**
     * Gets collision rectangle.
     *
     * @return the collision rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * Hit velocity responsible to return the updated velocity.
     * <p>
     * If the ball hits from below the ball changes the
     * vertical direction.
     * If the ball hits from the sides the ball changes the horizontal direction.
     * if the ball hits from above the ball bounces according to hit points on the paddle.
     * region 1 is the left most and region 5 is the right most of the paddle.
     * region 1 the ball bounces in 330 degrees.
     * region 2 the ball bounces in 300 degrees.
     * region 3 changes is vertical velocity and keeps its horizontal velocity.
     * region 4 the ball bounces in 30 degrees.
     * region 5 the ball bounces in 60 degrees.
     * when 0 degrees is "up:.
     *
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity.
     * @param hitter          the ball that hit the paddle.
     * @return the updated velocity.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx(), x = collisionPoint.getX();
        double dy = currentVelocity.getDy(), y = collisionPoint.getY();
        double v = currentVelocity.getSpeed();
        double width = this.rectangle.getWidth(), height = this.rectangle.getHeight();
        double upperLeftX = this.rectangle.getUpperLeft().getX();
        double upperLeftY = this.rectangle.getUpperLeft().getY();
        //sets the regions
        double region1 = upperLeftX + (0.2) * width, region2 = upperLeftX + (0.4) * width;
        double region3 = upperLeftX + (0.6) * width;
        double region4 = upperLeftX + (0.8) * width, region5 = upperLeftX + width;
        //check where the hit points is and changes the velocity according to it.
        if (y == upperLeftY + height) {
            dy = -dy;
        }
        //if the hit is on the sides.
        if (x == upperLeftX || x == upperLeftX + width) {
            dx = -dx;
        }
        //if the hits is above then by the regions that i mention before.
        if (y == upperLeftY) {
            if (x <= region1) {
                return Velocity.fromAngleAndSpeed(300, v);
            }
            if (x <= region2) {
                return Velocity.fromAngleAndSpeed(330, v);
            }
            if (x <= region3) {
                return new Velocity(dx, -dy);
            }
            if (x <= region4) {
                return Velocity.fromAngleAndSpeed(30, v);
            }
            if (x <= region5) {
                return Velocity.fromAngleAndSpeed(60, v);
            }

        }
        return new Velocity(dx, dy);
    }

    /**
     * Add to game.
     * <p>
     * add the paddle to the an object from game.GameLevel class,connect the block to a sprites and collidable lists.
     *
     * @param g the game that the paddle is participate in.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}
