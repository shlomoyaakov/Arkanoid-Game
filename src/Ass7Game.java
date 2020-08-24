import animations.AnimationRunner;
import game.WideEasy;
import biuoop.GUI;
import game.GameFlow;
import game.DirectHit;
import game.Green3;
import game.FinalFour;
import game.LevelInformation;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @ author Shlomo Yakov.
 */
public class Ass7Game {
    /**
     * Create a game.GameLevel object and run it
     * <p>
     * create a animation runner, list of level information and a game flow
     * initialized it and then run the according to arguments from the command line.
     *
     * @param args a command line argument.
     */
    /*
    public static void main(String[] args) {
        GUI gui = new GUI("title", 800, 600);
        biuoop.KeyboardSensor keyboard = gui.getKeyboardSensor();
        AnimationRunner ar = new AnimationRunner(60, gui);
        GameFlow flow = new GameFlow(ar, keyboard);
        if (args.length == 0) {
            orderedLevel(flow);
        } else {
            List<Integer> levelNum = new ArrayList<>();
            for (int i = 0; i < args.length; i++) {
                if (args[i].equals("1") || args[i].equals("2") || args[i].equals("3") || args[i].equals("4")) {
                    levelNum.add(Integer.parseInt(args[i]));
                }
            }
            if (levelNum.size() != 0) {
                byArguments(levelNum, flow);
            }
        }
        gui.close();
    }

    /**
     * run the game flow according to level order.
     *
     * @param flow the game flow object that we want to run.
     */
    /*
    private static void orderedLevel(GameFlow flow) {
        List<LevelInformation> list = new ArrayList<>();
        list.add(new DirectHit());
        list.add(new WideEasy());
        list.add(new Green3());
        list.add(new FinalFour());
        flow.runLevels(list);
    }

    /**
     * run the game flow according to user argument.
     *
     * @param levelNum list of numbers 1-4 that each number indicate a level.
     * @param flow     the game flow object that we want to run.
     */
    /*
    private static void byArguments(List<Integer> levelNum, GameFlow flow) {
        List<LevelInformation> list = new ArrayList<>();
        for (int i = 0; i < levelNum.size(); i++) {
            if (levelNum.get(i) == 1) {
                list.add(new DirectHit());
            }
            if (levelNum.get(i) == 2) {
                list.add(new WideEasy());
            }
            if (levelNum.get(i) == 3) {
                list.add(new Green3());
            }
            if (levelNum.get(i) == 4) {
                list.add(new FinalFour());
            }
        }
        flow.runLevels(list);
    }
*/
}
