package quizgame.model;

public class Player {
    private String playerName;
    private int score;

    public String getPlayerName() {
        return playerName;
    }
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getScore() {
        return score;
    }
    public void resetScore(int score) {
        this.score = 0;
    }

    public void addPoint() {
        score++;
    }

    public Player(String playerName) {
        this.playerName = playerName;
        this.score = 0;
    }

    public Player(String name, int score) {
        this.playerName = name;
        this.score = score;
    }
}
