package animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import sprite.Sprite;

import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class MenuAnimation<T> implements Menu<T> {
    private KeyboardSensor sensor;
    private String name;
    private Boolean stop;
    private Map<String, T> keyValMap;
    private Map<String, String> mesKeyMap;
    private Map<String, Menu<T>> subMenuMap;
    private T returnVal;
    private Menu<T> chosenSubMenu;
    private Sprite background;

    public MenuAnimation(String name, KeyboardSensor sensor) {
        this.name = name;
        this.stop = false;
        this.keyValMap = new LinkedHashMap<>();
        this.mesKeyMap = new LinkedHashMap<>();
        this.sensor = sensor;
        background = null;
        this.chosenSubMenu = null;
        this.subMenuMap = new LinkedHashMap<>();
    }

    public T getStatus() {
        this.stop = false;
        if (chosenSubMenu != null) {
            this.returnVal = chosenSubMenu.getStatus();
            chosenSubMenu = null;
        }
        return this.returnVal;
    }

    public void addSelection(String key, String message, T returnVal) {
        this.keyValMap.put(key, returnVal);
        this.mesKeyMap.put(message, key);
    }

    /**
     * Do one frame.
     *
     * @param d the surface that the sprites draw on
     */
    public void doOneFrame(DrawSurface d) {
        if (this.chosenSubMenu != null) {
            chosenSubMenu.doOneFrame(d);
            return;
        }
        draw(d);
        for (Map.Entry<String, T> entry : this.keyValMap.entrySet()) {
            if (this.sensor.isPressed(entry.getKey())) {
                this.stop = true;
                this.returnVal = entry.getValue();
            }
        }
        for (Map.Entry<String, Menu<T>> entry : this.subMenuMap.entrySet()) {
            if (this.sensor.isPressed(entry.getKey())) {
                this.stop = true;
                this.chosenSubMenu = entry.getValue();
            }
        }
    }

    public void draw(DrawSurface d) {
        if (this.background != null) {
            this.background.drawOn(d);
        }
        int dx = 30;
        d.setColor(Color.BLUE);
        d.drawText(120, 100, this.name, 50);
        d.setColor(Color.BLACK);
        for (Map.Entry<String, String> entry : this.mesKeyMap.entrySet()) {
            d.drawText(120, 120 + dx, "( " + entry.getValue() + " )", 25);
            d.drawText(170, 120 + dx, entry.getKey(), 25);
            dx = dx + 30;
        }
    }

    public void setBackground(Sprite s) {
        this.background = s;
    }

    /**
     * Should stop boolean responsible to stop the animation runner.
     *
     * @return the boolean that tells us if to stop the animation runner.
     */
    public boolean shouldStop() {
        if (chosenSubMenu != null) {
            return chosenSubMenu.shouldStop();
        }
        return this.stop;
    }

    public void addSubMenu(String key, String message, Menu<T> subMenu) {
        this.subMenuMap.put(key, subMenu);
        this.mesKeyMap.put(message, key);
    }
}