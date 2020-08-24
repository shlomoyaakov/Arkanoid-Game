import game.LevelInformation;

import java.io.*;
import java.util.List;

public class Test2 {
    public static void main(String[] args) {
        String fileName = "level_definitions.txt";
        File file = new File(fileName);
        List<LevelInformation> list;
        java.io.Reader reader = null;
        try {
            reader = new InputStreamReader(new FileInputStream(fileName));
            list = new LevelSpecificationReader().fromReader(reader);
        } catch (FileNotFoundException e) {
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
    }
}
