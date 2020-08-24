package game;

import geometry.Point;
import geometry.Rectangle;
import sprite.Block;
import sprite.DirectHitBackGround;
import sprite.Sprite;
import sprite.Velocity;

import java.util.List;
import java.util.ArrayList;
import java.awt.Color;
import java.util.Map;
import java.util.TreeMap;

/**
 * The type Direct hit.
 */
public class DirectHit implements LevelInformation {
    /**
     * The Paddle width.
     */
    static final int PADDLE_WIDTH = 60;
    /**
     * The Paddle speed.
     */
    static final int PADDLE_SPEED = 10;
    /**
     * The Amount of blocks.
     */
    static final int BLOCKS_AMOUNT = 1;


    private String levelName;
    private List<Block> blocks;
    private List<Velocity> velocities;
    private Sprite backGround;

    /**
     * Instantiates a new Direct hit.
     */
    public DirectHit() {
        this.levelName = "Direct Hit";
        this.blocks = new ArrayList<>();
        this.velocities = new ArrayList<>();
        makeBlocks();
        makeBackGround();
        makeVelocities();
    }

    /**
     * make Background initialized the background field.
     */
    private void makeBackGround() {
        Point p = new Point(400, 300);
        this.backGround = new DirectHitBackGround(Color.BLACK, new Rectangle(new Point(25, 25),
                750, 575),
                p);
    }

    /**
     * make Velocities add to the velocities list a velocity that fit to 1 ball.
     */
    private void makeVelocities() {
        this.velocities.add(Velocity.fromAngleAndSpeed(0, 6));
    }

    /**
     * make Blocks responsible to create block that fits to the direct hit level.
     * <p>
     * in direct hit the block is directly above the paddle and in the trajectory of the ball.
     */
    private void makeBlocks() {
        Rectangle rec = new Rectangle(new Point(385, 285), 30, 30);
        Block b = new Block(rec);
        Map<Integer,Color> map = new TreeMap<>();
        map.put(1,Color.red);
        b.setColor(map);
        b.setValue(1);
        this.blocks.add(b);
    }

    /**
     * Get the name of the level.
     *
     * @return levelName string with the name of the level.
     */
    public String levelName() {
        return this.levelName;
    }

    /**
     * Get the blocks of the level.
     *
     * @return blocks a list of blocks.
     */
    public List<Block> blocks() {
        return new ArrayList<>(this.blocks);
    }

    /**
     * Get the initial velocity of the ball.
     *
     * @return velocities a list of the initial velocities.
     */
    public List<Velocity> initialBallVelocities() {
        return new ArrayList<>(this.velocities);
    }

    /**
     * Get the background of the level.
     *
     * @return background the DirectHitBackGround sprite.
     */
    public Sprite getBackground() {
        return this.backGround;
    }

    /**
     * Get the number of block.
     *
     * @return the number of block to be removed.
     */
    public int numberOfBlocksToRemove() {
        return BLOCKS_AMOUNT;
    }

    /**
     * Get the paddle's width.
     *
     * @return the paddle width in int.
     */
    public int paddleWidth() {
        return PADDLE_WIDTH;
    }

    /**
     * Get the paddle's speed.
     *
     * @return the paddle speed in int.
     */
    public int paddleSpeed() {
        return PADDLE_SPEED;
    }

    /**
     * Get the number of balls.
     *
     * @return the amount of balls.
     */
    public int numberOfBalls() {
        return this.velocities.size();
    }
}
