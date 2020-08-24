package listenners;

import sprite.Ball;
import sprite.Block;
import game.GameLevel;
import game.Counter;

/**
 * The type sprite.Ball remover.
 */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**
     * Instantiates a new sprite.Ball remover.
     *
     * @param gameLevel   the gameLevel that the hitlistener is "listening" to.
     * @param balls counter that hold the number of the remaining blocks in the gameLevel.
     */
    public BallRemover(GameLevel gameLevel, Counter balls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = balls;
    }

    /**
     * Hitevent responsible to decrease the counter of the remaining balls and removing the ball from the gameLevel.
     *
     * @param beingHit that just get hit from a ball.
     * @param hitter the ball that just hit a block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(gameLevel);
        this.remainingBalls.decrease(1);
    }
}