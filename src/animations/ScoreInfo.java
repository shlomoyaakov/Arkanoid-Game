package animations;

public class ScoreInfo {
    private int score;
    private String name;
    public ScoreInfo(String name, int score) {
        this.name = name;
        this.score = score;
    }
    public String getName() {
        return this.name;
    }
    public int getScore() {
        return this.score;
    }
}