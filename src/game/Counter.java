package game;

/**
 * The type game.Counter.
 */
public class Counter {
    private int value;

    /**
     * Instantiates a new game.Counter.
     */
    public Counter() {
        this.value = 0;
    }

    /**
     * Increase the value of the counter.
     *
     * @param number the number
     */
    public void increase(int number) {
        this.value += number;
    }

    /**
     * Decrease the value of the counter.
     *
     * @param number the number
     */
    public void decrease(int number) {
        this.value -= number;
    }

    /**
     * Gets the value of the counter.
     *
     * @return the value
     */
    public int getValue() {
        return this.value;
    }
}