package animations;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HighScoresTable {
    private int size;
    private ArrayList<ScoreInfo> list;

    // Create an empty high-scores table with the specified size.
    // The size means that the table holds up to size top scores.
    public HighScoresTable(int size) {
        this.size = size;
        this.list = new ArrayList<>();
    }

    // Add a high-score.
    public void add(ScoreInfo score) {
        int i = getRank(score.getScore());
        if (i <= this.size && i <= this.list.size()) {
            this.list.set(i - 1, score);
        } else if (i <= this.size && i > this.list.size()) {
            this.list.add(score);
        }
    }

    // Return table size.
    public int size() {
        return this.size;
    }

    // Return the current high scores.
    // The list is sorted such that the highest
    // scores come first.
    public List<ScoreInfo> getHighScores() {
        return new ArrayList<>(this.list);
    }

    // return the rank of the current score: where will it
    // be on the list if added?
    // Rank 1 means the score will be highest on the list.
    // Rank `size` means the score will be lowest.
    // Rank > `size` means the score is too low and will not
    //      be added to the list.
    public int getRank(int score) {
        int i;
        for (i = 0; i < this.list.size(); i++) {
            if (this.list.get(i).getScore() < score) {
                return i + 1;
            }
        }
        return i + 1;
    }

    // Clears the table
    public void clear() {
        this.list.clear();
    }

    // Load table data from file.
    // Current table data is cleared.
    public void load(File file) throws IOException {
        // read lines
        BufferedReader reader = null;
        try {
            reader = new BufferedReader( // buffered reader - has readLine()
                    new InputStreamReader( // bytes to characters wrapper
                            new FileInputStream(file))); // binary file stream
            // print each read line
            String line = reader.readLine();
            while (line != null) {
                String name = line;
                line = reader.readLine();
                String score = line;
                this.list.add(new ScoreInfo(name, Integer.parseInt(score)));
                line = reader.readLine();
            }

        } catch (FileNotFoundException e) {
            System.err.println("Unable to find file: " + file.getName());
        } catch (IOException e) {
            System.err.println("Failed reading file: " + file.getName()
                    + ", message:" + e.getMessage());
            e.printStackTrace(System.err);
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + file.getName());
            }
        }
    }

    // Save table data to the specified file.
    public void save(File fileName) throws IOException {
        PrintWriter os = null;
        try {
            // wrapper that can write strings
            os = new PrintWriter(new OutputStreamWriter(
                    // wrapper with many ways of writing strings
                    new FileOutputStream(fileName)));
            for (ScoreInfo score : this.list) {
                os.println(score.getName());
                os.println(score.getScore());
            }
        } finally {
            // Exception might have happened at constructor
            if (os != null) {
                // closes fileOutputStream too
                os.close();
            }
        }
    }

    // Read a table from file and return it.
    // If the file does not exist, or there is a problem with
    // reading it, an empty table is returned.
    public static HighScoresTable loadFromFile(File fileName) {
        List<ScoreInfo> list = new ArrayList();
        HighScoresTable table = new HighScoresTable(0);
        File file = fileName;
        // read lines
        BufferedReader reader = null;
        try {
            reader = new BufferedReader( // buffered reader - has readLine()
                    new InputStreamReader( // bytes to characters wrapper
                            new FileInputStream(file))); // binary file stream
            // print each read line
            String line = reader.readLine();
            while (line != null) {
                String name = line;
                line = reader.readLine();
                String score = line;
                list.add(new ScoreInfo(name, Integer.parseInt(score)));
                line = reader.readLine();
            }
            table = new HighScoresTable(list.size());
            for (int i = 0; i < list.size(); i++) {
                table.add(list.get(i));
            }
        } catch (FileNotFoundException e) {
            System.err.println("Unable to find file: " + file.getName());
        } catch (IOException e) {
            System.err.println("Failed reading file: " + file.getName()
                    + ", message:" + e.getMessage());
            e.printStackTrace(System.err);
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + file.getName());
            }
        }
        return table;
    }
}