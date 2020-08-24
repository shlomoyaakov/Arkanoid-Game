package animations;

import biuoop.DrawSurface;
import java.awt.Color;

public class HighScoresAnimation implements Animation {
    private HighScoresTable scores;
    private Boolean stop;

    public HighScoresAnimation(HighScoresTable scores) {
        this.scores = scores;
        this.stop = false;
    }

    /**
     * do one frame responsible to draw on the surface the you win screen.
     *
     * @param d the surface the method draw on.
     */
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.LIGHT_GRAY);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        int x = d.getWidth() / 2, dx = 30;
        d.setColor(Color.BLUE);
        d.drawText(x - 185, 100, "High Scores", 50);
        d.setColor(Color.green.darker());
        d.drawText(x - 300, 160, "Player Name", 25);
        d.drawText(x + 50, 160, "Score", 25);
        d.setColor(Color.BLACK);
        d.fillRectangle(100, 170, 550, 3);
        for (ScoreInfo score : this.scores.getHighScores()) {
            d.drawText(x - 300, 170 + dx, score.getName(), 20);
            d.drawText(x + 50, 170 + dx, Integer.toString(score.getScore()), 20);
            dx = dx + 30;
        }
        d.drawText(x - 250, 550, "Press space to continue", 35);
    }

    /**
     * shouldStop tells the animation runner if it can run the game over screen or not.
     *
     * @return this.running a boolean the tells if to stop or not.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
