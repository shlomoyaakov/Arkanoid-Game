package game;

import sprite.Block;
import sprite.Sprite;
import sprite.Velocity;

import java.util.List;

/**
 * The interface Level information.
 */
public interface LevelInformation {
    /**
     * Number of balls int.
     *
     * @return the int
     */
    int numberOfBalls();

    /**
     * Initial ball velocities list.
     *
     * @return the list
     */
    List<Velocity> initialBallVelocities();

    /**
     * Paddle speed int.
     *
     * @return the int
     */
    int paddleSpeed();

    /**
     * Paddle width int.
     *
     * @return the int
     */
    int paddleWidth();

    /**
     * Get the Level name string.
     *
     * @return the string
     */
    String levelName();

    /**
     * Gets background.
     *
     * @return the background, a sprite with the background of the level.
     */
    Sprite getBackground();

    /**
     * The Blocks that make up this level.
     *
     * @return the list
     */
    List<Block> blocks();

    /**
     * Number of blocks that should be removed.
     *
     * @return the size of the block list.
     */
    int numberOfBlocksToRemove();
}