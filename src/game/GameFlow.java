package game;

import animations.*;
import biuoop.DialogManager;
import biuoop.KeyboardSensor;

import java.util.List;

/**
 * The type Game flow.
 */
public class GameFlow {
    private biuoop.KeyboardSensor keyboardSensor;
    private AnimationRunner runner;
    private Counter lives;
    private Counter currentScore;
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private DialogManager dialog;
    private HighScoresTable table;

    /**
     * Instantiates a new Game flow.
     *
     * @param ar the animation runner the run the game level.
     * @param ks the keyboard sensor that get information from the keyboard
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, DialogManager dialog, HighScoresTable table) {
        this.keyboardSensor = ks;
        this.runner = ar;
        makeCounters();
        this.dialog = dialog;
        this.table = table;
    }

    /**
     * make BackCounter make the score and the lives counter that goes from level to level.
     */
    private void makeCounters() {
        this.currentScore = new Counter();
        this.lives = new Counter();
    }

    /**
     * Run the levels in the game.
     * <p>
     * game flow gets a list of level information and it run each level in the list.
     *
     * @param levels a list of level information that the run method run every object there.
     */
    public void runLevels(List<LevelInformation> levels) {
        // sets the lives
        this.lives.increase(1);
        //now the game run each level in the list of levels.
        for (LevelInformation levelInfo : levels) {
            this.remainingBlocks = new Counter();
            this.remainingBalls = new Counter();
            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.runner,
                    this.lives, this.currentScore, this.remainingBlocks, this.remainingBalls);
            level.initialize();
            while (this.lives.getValue() != 0 && this.remainingBlocks.getValue() != 0) {
                level.playOneTurn();
                if (this.remainingBalls.getValue() == 0) {
                    this.lives.decrease(1);
                }
            }
            if (this.lives.getValue() == 0) {
                break;
            }
        }
        //makes a screen with the the note of score and if the player won or lose
        if (this.lives.getValue() != 0) {
            Animation a = new YouWin(this.keyboardSensor, this.currentScore.getValue());
            this.runner.run(new KeyPressStoppableAnimation(this.keyboardSensor, KeyboardSensor.SPACE_KEY, a));
        } else {
            Animation a = new GameOver(this.keyboardSensor, this.currentScore.getValue());
            this.runner.run(new KeyPressStoppableAnimation(this.keyboardSensor, KeyboardSensor.SPACE_KEY, a));
        }
        if(this.table.getRank(this.currentScore.getValue())<= this.table.size()) {
            String name =dialog.showQuestionDialog("Name", "What is your name?", "");
            this.table.add(new ScoreInfo(name,this.currentScore.getValue()));
            this.runner.run(new KeyPressStoppableAnimation(this.keyboardSensor,KeyboardSensor.SPACE_KEY,new
                    HighScoresAnimation(this.table)));
        }
    }
}