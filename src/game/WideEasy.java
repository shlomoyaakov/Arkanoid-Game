package game;

import geometry.Point;
import geometry.Rectangle;
import sprite.Block;
import sprite.Sprite;
import sprite.Velocity;
import sprite.WideEasyBackGround;

import java.util.List;
import java.util.ArrayList;
import java.awt.Color;
import java.util.Map;
import java.util.TreeMap;

/**
 * The type Wide easy.
 */
public class WideEasy implements LevelInformation {
    /**
     * The Paddle width.
     */
    static final int PADDLE_WIDTH = 600;
    /**
     * The Paddle speed.
     */
    static final int PADDLE_SPEED = 1;
    /**
     * The Num balls.
     */
    static final int NUM_BALLS = 10;
    /**
     * The Num blocks.
     */
    static final int NUM_BLOCKS = 15;
    /**
     * The amount of blocks need to be removed.
     */
    static final int BLOCKS_AMOUNT = 15;


    private String levelName;
    private List<Block> blocks;
    private List<Velocity> velocities;
    private Sprite backGround;

    /**
     * Instantiates a new Wide easy.
     */
    public WideEasy() {
        this.levelName = "Wide Easy";
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
        Point p = new Point(150, 150);
        this.backGround = new WideEasyBackGround(Color.WHITE,
                new Rectangle(new Point(25, 25), 750, 575), p);
    }

    /**
     * make Velocities add to the velocities list a velocity that fit to number of balls.
     */
    private void makeVelocities() {
        int angle = 60;
        for (int i = 0; i < NUM_BALLS; i++) {
            this.velocities.add(Velocity.fromAngleAndSpeed(angle, 7));
            angle = angle - 12;
        }
    }

    /**
     * make Blocks responsible to create block that fits to the wide easy level.
     * <p>
     * in final four there is 15 blocks in a row.
     */
    private void makeBlocks() {
        Rectangle rec;
        Block b;
        Map<Integer,Color> map;
        java.awt.Color[] color = {Color.RED, Color.RED, Color.BLUE, Color.BLUE, Color.GRAY, Color.GRAY,
                Color.GREEN, Color.GREEN, Color.GREEN, Color.CYAN, Color.CYAN,
                Color.ORANGE, Color.ORANGE, Color.PINK.darker().darker(), Color.PINK.darker().darker()};
        double x = 25, width = 750.0 / 15.0;
        for (int i = 0; i < NUM_BLOCKS; i++) {
            rec = new Rectangle(new Point(x, 250), width, 30);
            b = new Block(rec);
            map = new TreeMap<>();
            map.put(1,color[i]);
            b.setColor(map);
            b.setValue(1);
            this.blocks.add(b);
            x = x + width;
        }
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
     * @return background the WideEasyBackGround sprite.
     */
    public Sprite getBackground() {
        return this.backGround;
    }

    /**
     * Get the number of block.
     *
     * @return the amount of blocks need to be removed.
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
