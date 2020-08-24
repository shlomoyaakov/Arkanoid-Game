package sprite;

import biuoop.DrawSurface;

import java.awt.*;

public class StandardBackground implements Sprite {
    private Color color;
    private Image img;

    public StandardBackground(Color c) {
        this.color = c;
        this.img = null;
    }

    /**
     * Draw on, draw objects on the surface.
     *
     * @param d the d
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        if (img != null){
            drawImg(d);
        }
    }

    /**
     * Time passed responsible to the changes in the environment of the game.
     */
    public void timePassed() {
        return;
    }

    public void setImg(Image img) {
        this.img = img;
    }
    public void drawImg(DrawSurface d){
        d.drawImage(0, 0, img);
    }
}
