package listenners;

/**
 * The interface Hit notifier.
 */
public interface HitNotifier {
    /**
     * Add hit listener.
     *
     * Add hl as a listener to hit events.
     *
     * @param hl the listener that is going to be added.
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hit listener.
     *
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl the the listener that is going to be removed.
     */
    void removeHitListener(HitListener hl);
}
