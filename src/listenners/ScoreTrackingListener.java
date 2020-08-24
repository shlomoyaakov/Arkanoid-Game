package listenners;

import sprite.Ball;
import sprite.Block;
import game.Counter;

/**
 * The type Score tracking listener.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Instantiates a new Score tracking listener.
     *
     * @param scoreCounter the score counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }
    /**
     * Hitevent responsible to increase the counter of the score.
     *
     * every hit the score increase by 5 and if the block was destroyed then the
     * score increase by 10.
     *
     * @param beingHit that just get hit from a ball.
     * @param hitter the ball that just hit a block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
        if (beingHit.getHitPoints() == 0) {
            this.currentScore.increase(5);
        }
    }
}