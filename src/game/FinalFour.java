package game;

import geometry.Point;
import geometry.Rectangle;
import sprite.Block;
import sprite.FinalFourBackGround;
import sprite.Sprite;
import sprite.Velocity;

import java.util.List;
import java.util.ArrayList;
import java.awt.Color;
import java.util.Map;
import java.util.TreeMap;

/**
 * The type Final four.
 */
public class FinalFour implements LevelInformation {
    /**
     * The Paddle width.
     */
    static final int PADDLE_WIDTH = 100;
    /**
     * The Paddle speed.
     */
    static final int PADDLE_SPEED = 10;
    /**
     * The Num balls.
     */
    static final int NUM_BALLS = 3;
    /**
     * The Amount of blocks.
     */
    static final int BLOCKS_AMOUNT = 105;


    private String levelName;
    private List<Block> blocks;
    private List<Velocity> velocities;
    private Sprite backGround;

    /**
     * Instantiates a new Final four.
     */
    public FinalFour() {
        this.levelName = "Final Four";
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
        Point p = new Point(600, 500);
        this.backGround = new FinalFourBackGround(new Color(68, 175, 255), new Rectangle(
                new Point(25, 25),
                750, 575), p);
    }

    /**
     * make Velocities add to the velocities list a velocity that fit to number of balls.
     */
    private void makeVelocities() {
        int angle = 60;
        for (int i = 0; i < NUM_BALLS; i++) {
            this.velocities.add(Velocity.fromAngleAndSpeed(angle, 7));
            angle = angle - 60;
        }
    }

    /**
     * make Blocks responsible to create block that fits to the final four level.
     * <p>
     * in final four there is 105 blocks 7 rows and 15 blocks in each row.
     */
    private void makeBlocks() {
        Rectangle rec;
        Block b;
        Map<Integer,Color> map;
        java.awt.Color[] color = {Color.RED, Color.BLUE, Color.GRAY,
                Color.GREEN, Color.WHITE, Color.CYAN, Color.magenta, Color.PINK};
        double x = 25, width = 750.0 / 15.0, amount = 15, y = 100;
        int value = 2;
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < amount; j++) {
                rec = new Rectangle(new Point(x, y), width, 30);
                b = new Block(rec);
                map = new TreeMap<>();
                map.put(1,color[i]);
                b.setColor(map);
                b.setValue(value);
                this.blocks.add(b);
                x = x + width;
            }
            value = 1;
            x = 25;
            y = y + 30;
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
     * @return background the FinalFourBackGround sprite.
     */
    public Sprite getBackground() {
        return this.backGround;
    }

    /**
     * Get the number of block.
     *
     * @return the number of block need to be removed.
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
