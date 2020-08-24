package animations;

import biuoop.GUI;
import biuoop.DrawSurface;

/**
 * The type Animation runner.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private biuoop.Sleeper sleeper;

    /**
     * Instantiates a new Animation runner.
     *
     * @param framesPerSecond the frames per second.
     * @param gui             the gui that we draw on the sprites
     */
    public AnimationRunner(int framesPerSecond, GUI gui) {
        this.framesPerSecond = framesPerSecond;
        this.sleeper = new biuoop.Sleeper();
        this.gui = gui;
    }

    /**
     * the methods that run the animation.
     * <p>
     * the method run the animation by according to the animation do one frame.
     * it stops to run the animation according to animation boolean should stop
     *
     * @param animation the animation that we are going to run.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}

