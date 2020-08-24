package game;

import biuoop.DrawSurface;
import sprite.Sprite;

import java.util.ArrayList;
import java.util.List;

/**
 * The type sprite.Sprite collection.
 */
public class SpriteCollection {
    private List<Sprite> list;

    /**
     * Instantiates a new sprite.Sprite collection.
     *
     * @param list - list of Sprites objects.
     */
    public SpriteCollection(List<Sprite> list) {
        this.list = list;
    }

    /**
     * Instantiates a new sprite.Sprite collection, and making a new list of sprites objects.
     */
    public SpriteCollection() {
        this.list = new ArrayList<Sprite>();
    }

    /**
     * Add sprite to the list of sprites objects.
     *
     * @param s the sprite objects that we want to add to the list.
     */
    public void addSprite(Sprite s) {
        this.list.add(s);
    }

    /**
     * Remove a sprite object from the list of the sprites.
     *
     * @param s the sprite object that we want to remove from the list.
     */
    public void removeSprite(Sprite s) {
        this.list.remove(s);
    }

    /**
     * It calls to timePassed in each sprite object in the list.
     */
    public void notifyAllTimePassed() {
        List<Sprite> list2 = new ArrayList<>(this.list);
        for (Sprite s : list2) {
            s.timePassed();
        }
    }

    /**
     * It calls to drawOn(d) in each sprite object in the list.
     *
     * @param d the surface that we want to draw on.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : this.list) {
            s.drawOn(d);
        }
    }
}
