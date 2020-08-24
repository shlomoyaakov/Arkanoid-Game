package sprite; /**
 * @ author Shlomo Yakov.
 */

import biuoop.DrawSurface;
import geometry.Point;
import geometry.Rectangle;
import listenners.HitNotifier;
import listenners.HitListener;
import game.GameLevel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The type sprite.Block.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rectangle;
    private Map<Integer, Color> color;
    private int value;
    private List<HitListener> hitListeners;
    private Color stroke;
    private Map<Integer, Image> img;
    private int initalLife;

    /**
     * Instantiates a new sprite.Block.
     *
     * @param rec the rectangle of the block.
     */
    public Block(Rectangle rec) {
        this.rectangle = rec;
        hitListeners = new ArrayList<>();
        this.value = 0;
        this.stroke = null;
        this.img = null;
    }

    /**
     * Sets value to the block.
     * <p>
     * the value represents the the number on the block.
     *
     * @param val the value of the block.
     */
    public void setValue(int val) {
        this.value = val;
        this.initalLife = val;
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
     * timePassed is responsible to make the changes in the environment of each sprite.Sprite object.
     * <p>
     * currently it does nothing.
     */
    public void timePassed() {
        return;
    }

    /**
     * Hit velocity responsible to return the updated velocity.
     * <p>
     * If the ball hits from below or above the ball changes the
     * vertical direction.
     * If the ball hits from the sides the ball changes the horizontal direction.
     *
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity.
     * @param hitter          the ball that hit the block.
     * @return the updated velocity.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        this.value--;
        this.notifyHit(hitter);
        double dx = currentVelocity.getDx(), x = collisionPoint.getX();
        double dy = currentVelocity.getDy(), y = collisionPoint.getY();
        double width = this.rectangle.getWidth(), height = this.rectangle.getHeight();
        double upperLeftX = this.rectangle.getUpperLeft().getX();
        double upperLeftY = this.rectangle.getUpperLeft().getY();
        //changes the velocity according to the collision point.
        if (y == upperLeftY || y == upperLeftY + height) {
            dy = -dy;
        }
        if (x == upperLeftX || x == upperLeftX + width) {
            dx = -dx;
        }
        Velocity v = new Velocity(dx, dy);
        return v;
    }

    /**
     * Sets the fill color.
     *
     * @param recColor the rectangle color
     */
    public void setColor(Map<Integer, Color> recColor) {
        this.color = recColor;
    }

    /**
     * Sets the stroke color.
     *
     * @param color the stroke color
     */
    public void setStroke(Color color) {
        this.stroke = color;
    }

    public void setImg(Map<Integer, Image> img) {
        this.img = img;
    }

    /**
     * drawOn draw the block according to its rectangle, and its color.
     * <p>
     * its fill the rectangle in the color of the block and the frame in black.
     *
     * @param surface the surface that we draw on.
     */
    public void drawOn(DrawSurface surface) {
        if (img != null) {
            drawImg(surface);
        }
        if (this.img == null || !this.img.containsKey(this.value)) {
            if (this.color.containsKey(this.value)) {
                surface.setColor(this.color.get(this.value));
            } else {
                surface.setColor(this.color.get(1));
            }
            int width = (int) this.rectangle.getWidth();
            int height = (int) this.rectangle.getHeight();
            int x = (int) this.rectangle.getUpperLeft().getX();
            int y = (int) this.rectangle.getUpperLeft().getY();
            surface.fillRectangle(x, y, width, height);
            if (this.stroke != null) {
                surface.setColor(this.stroke);
                surface.drawRectangle(x, y, width, height);
            }
        }
    }

    public void drawImg(DrawSurface d) {
        if (this.img.containsKey(this.value)) {
            d.drawImage((int) this.rectangle.getUpperLeft().getX()
                    , (int) this.rectangle.getUpperLeft().getY(), this.img.get(this.value));
        } else if (this.color.containsKey(this.value)) {
            return;
        } else {
            d.drawImage((int) this.rectangle.getUpperLeft().getX()
                    , (int) this.rectangle.getUpperLeft().getY(), this.img.get(1));
        }
    }

    /**
     * Add to game.
     * <p>
     * add the block to the an object from game.GameLevel class,connect the block to a sprites and collidable lists.
     *
     * @param g the game that the block is participate in.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Remove from the game.
     * <p>
     * remove the block from the game by removing him from the sprites and
     * collidable lists.
     *
     * @param g the game that the block is participate in.
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
        g.removeCollidable(this);
    }

    /**
     * Add the listener to the list of listener that in the block's field.
     *
     * @param hl the listener that listen to the block.
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * Remove hitListener from the block.
     * <p>
     * remove the listener from the list of listener that in the block's field.
     *
     * @param hl the listener that listen to the block.
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * Notify all the listener in the list of listeners about the hit event.
     * <p>
     * by using a for loop all the listener in the list called to their hitevent method.
     *
     * @param hitter the ball that hit the block.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Get the value of the block that indicates how much more hit the block can get before disappearing.
     *
     * @return int, the value of the block.
     */
    public int getHitPoints() {
        return value;
    }
    public double getWidth() {
        return this.rectangle.getWidth();
    }
    public double getHeight(){
        return this.rectangle.getHeight();
    }
    public void restart() {
        this.value = this.initalLife;
    }
}
