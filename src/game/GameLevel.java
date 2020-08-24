package game;
/**
 * @ author Shlomo Yakov.
 */

import animations.Animation;
import animations.KeyPressStoppableAnimation;
import animations.CountdownAnimation;
import animations.PauseScreen;
import sprite.Sprite;
import sprite.Ball;
import sprite.Paddle;
import animations.AnimationRunner;
import biuoop.DrawSurface;
import sprite.Collidable;
import sprite.Block;
import listenners.HitListener;
import listenners.BlockRemover;
import listenners.ScoreTrackingListener;
import biuoop.KeyboardSensor;
import geometry.Point;
import geometry.Rectangle;
import listenners.BallRemover;
import sprite.LevelIndicator;
import sprite.LivesIndicator;
import sprite.ScoreIndicator;

import java.awt.Color;
import java.util.Map;
import java.util.TreeMap;


/**
 * The type game.GameLevel.
 */
public class GameLevel implements Animation {
    /**
     * The Height of the surface.
     */
    static final int HEIGHT = 600;
    /**
     * The Width of the surface.
     */
    static final int WIDTH = 800;
    /**
     * The Width/Height of the block in the edges of the surface.
     */
    static final int EDGE_BLOCK = 25;
    //fields.
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Ball[] balls = null;
    private Paddle paddle = null;
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private Counter currentScore;
    private Counter lives;
    private AnimationRunner runner;
    private Boolean running;
    private biuoop.KeyboardSensor keyboard;
    private LevelInformation levelInfo;


    /**
     * Instantiates a new game.GameLevel.
     *
     * @param info            the information about the level.
     * @param keyboard        the sensor that get information from the keyboard.
     * @param runner          the animation runner that run the game level
     * @param lives           the counter of the lives
     * @param score           the counter of the score.
     * @param remainingBlocks the counter of the remaining blocks
     * @param remainingBalls  the counter of the remaining balls.
     */
    public GameLevel(LevelInformation info, biuoop.KeyboardSensor keyboard, AnimationRunner runner,
                     Counter lives, Counter score, Counter remainingBlocks, Counter remainingBalls) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.levelInfo = info;
        this.keyboard = keyboard;
        this.runner = runner;
        this.lives = lives;
        this.remainingBalls = remainingBalls;
        this.remainingBlocks = remainingBlocks;
        this.currentScore = score;
    }

    /**
     * Add collidable to the game by adding it to the list of the field environment.
     *
     * @param c the collidable object that we want to add to game.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Remove a collidable from the game by removing it from the list of the field environment.
     *
     * @param c the collidable object that we want to remove from the game.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Remove sprite object to the game by removing it from the list of the field sprite.
     *
     * @param s the sprtie object that we want to remove from the game.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Add sprite object to the game by adding it to the list of the field sprite.
     *
     * @param s the sprtie object that we want to add to the game.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Add 4 block to the edges of the surface so the balls cant go away.
     * <p>
     * make 4 block in the edges of the surface and draw them in grey and
     * add them to the game.
     */
    private void makeBoard() {
        HitListener hl = new BallRemover(this, this.remainingBalls);
        Block[] boards = new Block[4];
        //makes the 4 rectangles according to the limits of the surfaces
        Rectangle rec1 = new Rectangle(new Point(0, 0), WIDTH - EDGE_BLOCK, EDGE_BLOCK);
        Rectangle rec2 = new Rectangle(new Point(WIDTH - EDGE_BLOCK, EDGE_BLOCK), EDGE_BLOCK,
                HEIGHT - EDGE_BLOCK);
        Rectangle rec3 = new Rectangle(new Point(0, HEIGHT), WIDTH - EDGE_BLOCK, EDGE_BLOCK);
        Rectangle rec4 = new Rectangle(new Point(0, EDGE_BLOCK), EDGE_BLOCK, HEIGHT - EDGE_BLOCK);
        boards[0] = new Block(rec1);
        boards[1] = new Block(rec2);
        boards[2] = new Block(rec3);
        boards[3] = new Block(rec4);
        boards[2].addHitListener(hl);
        Map<Integer,Color> map =new TreeMap<>();
        map.put(1,Color.gray);
        //adding them to the game and give them color.
        for (int i = 0; i < 4; i++) {
            boards[i].setValue(0);
            boards[i].setColor(map);
            this.environment.addCollidable(boards[i]);
            this.sprites.addSprite(boards[i]);
        }
    }

    /**
     * Add a paddle to the game.
     * <p>
     * make a paddle complete paddle with color and boards and adding it to the game.
     */
    private void makePaddle() {
        if (this.paddle != null) {
            this.removeCollidable(this.paddle);
            this.removeSprite(this.paddle);
        }
        Point paddleP = new Point(400 - 0.5 * this.levelInfo.paddleWidth(), HEIGHT - 30);
        Rectangle paddleRec = new Rectangle(paddleP, this.levelInfo.paddleWidth(), 10);
        this.paddle = new Paddle(this.keyboard, paddleRec);
        this.paddle.setColor(Color.ORANGE);
        this.paddle.addToGame(this);
        this.paddle.setBoard(EDGE_BLOCK, WIDTH - EDGE_BLOCK);
        this.paddle.setSpeed(this.levelInfo.paddleSpeed());
    }

    /**
     * Add two balls to the game.
     * <p>
     * making an array of 2 balls and initialize them with the necessary features and add
     * them to the game.
     */
    private void makeBalls() {
        Point center = new Point(400, HEIGHT - 35);
        this.balls = new Ball[this.levelInfo.numberOfBalls()];
        for (int i = 0; i < this.levelInfo.numberOfBalls(); i++) {
            this.remainingBalls.increase(1);
            this.balls[i] = new Ball(center, 6, Color.WHITE);
            this.balls[i].setEnvironment(this.environment);
            this.balls[i].setVelocity(this.levelInfo.initialBallVelocities().get(i));
            this.balls[i].addToGame(this);
        }
    }

    /**
     * Add some block arrange properly to the game.
     * <p>
     * arrange block in the surface in 6 rows each rows gets a different color and the top
     * row has value of and the other has value of 1.
     * also adding them to the game.
     */
    private void makeBlocks() {
        HitListener hl = new BlockRemover(this, this.remainingBlocks);
        HitListener score = new ScoreTrackingListener(this.currentScore);
        //each row has a different color and amount of blocks
        for (Block b : this.levelInfo.blocks()) {
            b.addHitListener(hl);
            b.addHitListener(score);
            b.addToGame(this);
        }
        this.remainingBlocks.increase(this.levelInfo.numberOfBlocksToRemove());
    }

    /**
     * make indicator responisble to create the sprite that prints to screen the counters.
     */
    public void makeIndicators() {
        Sprite score = new ScoreIndicator(new Rectangle(new Point(0, 0),
                WIDTH, EDGE_BLOCK), this.currentScore, 0.1);
        Sprite livesNum = new LivesIndicator(new Rectangle(new Point(0, 0),
                WIDTH, EDGE_BLOCK), this.lives, 0.4);
        Sprite levelName = new LevelIndicator(new Rectangle(new Point(0, 0),
                WIDTH, EDGE_BLOCK), 0.6, this.levelInfo.levelName());
        this.sprites.addSprite(score);
        this.sprites.addSprite(levelName);
        this.sprites.addSprite(livesNum);
    }

    /**
     * Initialize the game make all the necessary steps so the game will run.
     * <p>
     * it crate a gui and then create a paddle blocks and balls and add them to
     * the game.
     */
    public void initialize() {
        // Initialize a new game make board and blocks
        this.sprites.addSprite(this.levelInfo.getBackground());
        makeBoard();
        makeIndicators();
        makeBlocks();
    }

    /**
     * shouldStop tells the animation runner if it can run the gamelevel or not.
     *
     * @return this.running a boolean the tells if to stop or not.
     */
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * doOneframe responsible to logic of the game.
     * <p>
     * the methods responsible to draw all the sprites and checks all the counters
     * and responsible to stop the game if it ends.
     *
     * @param d the surface that the sprite draw on.
     */
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (this.remainingBlocks.getValue() == 0) {
            this.currentScore.increase(100);
            this.running = false;
        }
        if (this.remainingBalls.getValue() == 0) {
            this.running = false;
        }
        if (this.lives.getValue() == 0) {
            this.running = false;
        }
        if (this.keyboard.isPressed("p")) {
            Animation a = new PauseScreen(this.keyboard);
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY, a));
        }
    }

    /**
     * play one turn until thre are no more blocks or balls on the screen.
     * <p>
     * using an endless loop and the rules of the games
     * the balls and the blocks and the paddle always change every one in its own way.
     * whenever there is hit on a block with a value of 1 the block is removed from the game.
     */
    public void playOneTurn() {
        makePaddle();
        makeBalls();
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.running = true;
        this.runner.run(this);
    }

}

