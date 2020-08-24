package listenners;

import sprite.Ball;
import sprite.Block;
import game.GameLevel;
import game.Counter;

/**
 * The type sprite.Block remover.
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * Instantiates a new sprite.Block remover.
     *
     * @param gameLevel blockremover are listen to.
     * @param blocks a counter that holds the number of blocks in the gameLevel.
     */
    public BlockRemover(GameLevel gameLevel, Counter blocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = blocks;
    }

    /**
     * Hitevent responsible to decrease the counter of the remaining blocks and to remove the blocks from the gameLevel.
     *
     * @param beingHit that just get hit from a ball.
     * @param hitter the ball that just hit a block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitPoints() <= 0) {
            beingHit.removeFromGame(gameLevel);
            beingHit.removeHitListener(this);
            this.remainingBlocks.decrease(1);
        }
    }
}