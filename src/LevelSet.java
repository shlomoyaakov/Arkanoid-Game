import game.LevelInformation;

import java.io.*;
import java.util.Map;

public class LevelSet {
    public LevelSet() {

    }

    public static void readFile(Map<String, String> mesMap, Map<String, String> fileNameMap, String fileName) {
        java.io.Reader reader = null;
        try {
            reader = new LineNumberReader(new BufferedReader(new InputStreamReader(
                    ClassLoader.getSystemClassLoader().getResourceAsStream(fileName))));
            String line = ((LineNumberReader) reader).readLine();
            String symbol = "";
            while (line != null) {
                if (((LineNumberReader) reader).getLineNumber() % 2 == 1) {
                    String[] splitLine = line.split(":");
                    mesMap.put(splitLine[0], splitLine[1]);
                    symbol = splitLine[0];
                } else {
                    fileNameMap.put(symbol, line);
                }
                line = ((LineNumberReader) reader).readLine();
            }
        } catch (FileNotFoundException e) {
            System.err.println("Unable to find file");
        } catch (IOException e) {
            System.err.println("Failed reading file:"
                    + ", message:" + e.getMessage());
            e.printStackTrace(System.err);
        } catch (Exception ex) {
            System.err.println("Wrong Level Set format");
            System.exit(1);
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
