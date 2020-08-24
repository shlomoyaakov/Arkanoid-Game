import animations.*;
import animations.Menu;
import biuoop.DialogManager;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import game.*;
import sprite.StandardBackground;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Test {
    public static void main(String[] args) {
        File file = new File("highscores.txt");
        HighScoresTable table = new HighScoresTable(9);
        try {
            if (!file.isFile()) {
                table.save(file);
            } else {
                table.load(file);
            }
        } catch (Exception ex) {
            System.err.println("Unable to open the file");
            System.exit(1);
        }

        GUI gui = new GUI("title", 800, 600);
        biuoop.KeyboardSensor keyboard = gui.getKeyboardSensor();
        AnimationRunner ar = new AnimationRunner(60, gui);
        HighScoresAnimation highScoresAnimation = new HighScoresAnimation(table);
        MenuAnimation<Task<Void>> menu = new MenuAnimation<>("arknoid", keyboard);
        menu.setBackground(new StandardBackground(Color.lightGray));
        DialogManager dialog = gui.getDialogManager();
        Menu<Task<Void>> subMenu = makeSubMenu(ar, keyboard, dialog, table, args[0]);
        while (true) {
            menu.addSubMenu("s", "Start Game", subMenu);
            menu.addSelection("h", "Highscores", HiSCoresTask(ar, keyboard, highScoresAnimation));
            menu.addSelection("q", "Quit", quitTask(gui, table, file));
            ar.run(menu);
            menu.getStatus().run();

        }
    }

    static private Task<Void> quitTask(GUI gui, HighScoresTable table, File file) {
        return new Task<Void>() {
            @Override
            public Void run() {
                try {
                    table.save(file);
                } catch (Exception ex) {
                    System.out.println("problem with saving the file");
                }
                gui.close();
                System.exit(1);
                return null;
            }
        };
    }

    static private Task<Void> HiSCoresTask(AnimationRunner ar, KeyboardSensor kb,
                                           HighScoresAnimation scoresAnimation) {
        return new Task<Void>() {
            @Override
            public Void run() {
                ar.run(new KeyPressStoppableAnimation(kb, KeyboardSensor.SPACE_KEY, scoresAnimation));
                return null;
            }
        };
    }

    static private List<LevelInformation> makeLevels(String fileName) {
        List<LevelInformation> list = new ArrayList<>();
        java.io.Reader reader = null;
        try {
            reader = new InputStreamReader(ClassLoader.getSystemClassLoader().getResourceAsStream(fileName));
            list = new LevelSpecificationReader().fromReader(reader);
        } catch (Exception e) {
            System.err.println("Unable to find file");
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file");
            }
        }
        return list;
    }

    static private Task<Void> makeGame(AnimationRunner ar, KeyboardSensor keyboard,
                                       DialogManager dialogManger, HighScoresTable table,
                                       List<LevelInformation> levels) {
        return new Task<Void>() {
            @Override
            public Void run() {
                GameFlow flow = new GameFlow(ar, keyboard, dialogManger, table);
                flow.runLevels(levels);
                return null;
            }
        };
    }

    static private Menu<Task<Void>> makeSubMenu(AnimationRunner ar, KeyboardSensor keyboard,
                                                DialogManager dialogManger, HighScoresTable table,
                                                String name) {
        MenuAnimation<Task<Void>> subMenu = new MenuAnimation<>("Level Sets", keyboard);
        subMenu.setBackground(new StandardBackground(Color.lightGray));
        Map<String, String> messageMap = new TreeMap<>(), fileMap = new TreeMap<>();
        LevelSet.readFile(messageMap, fileMap, name);
        for (Map.Entry<String, String> entry : messageMap.entrySet()) {
            subMenu.addSelection(entry.getKey(), entry.getValue(), makeGame(ar, keyboard,
                    dialogManger, table, makeLevels(fileMap.get(entry.getKey()))));
        }
        return subMenu;
    }
}
