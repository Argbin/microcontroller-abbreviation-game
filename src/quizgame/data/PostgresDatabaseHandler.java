package quizgame.data;

import quizgame.model.Player;
import quizgame.model.Question;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgresDatabaseHandler implements DatabaseRepository {
    private final String url;
    private final String user;
    private final String password;

    public PostgresDatabaseHandler(ConfigReader config) {
        this.url = config.getProperty("db.url");
        this.user = config.getProperty("db.user");
        this.password = config.getProperty("db.password");
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    @Override
    public List<Question> fetchAllQuestions() {
        List<Question> questions = new ArrayList<>();
        String query = "SELECT abbreviation, full_term FROM questions";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                questions.add(new Question(rs.getString("abbreviation"), rs.getString("full_term")));
            }
        } catch (SQLException e) {
            System.err.println("Could not get questions: " + e.getMessage());
        }
        return questions;
    }

    @Override
    public void saveHighscore(Player player) {
        String upsertPlayer = "INSERT INTO players (name) VALUES (?) ON CONFLICT (name) DO NOTHING";
        String getPlayerId = "SELECT id FROM players WHERE name = ?";
        String insertScore = "INSERT INTO highscores (player_id, score) VALUES (?, ?)";

        try (Connection conn = connect()) {
            try (PreparedStatement pstmt = conn.prepareStatement(upsertPlayer)) {
                pstmt.setString(1, player.getPlayerName());
                pstmt.executeUpdate();
            }

            int playerId = -1;
            try (PreparedStatement pstmt = conn.prepareStatement(getPlayerId)) {
                pstmt.setString(1, player.getPlayerName());
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        playerId = rs.getInt("id");
                    }
                }
            }

            if (playerId != -1) {
                try (PreparedStatement pstmt = conn.prepareStatement(insertScore)) {
                    pstmt.setInt(1, playerId);
                    pstmt.setInt(2, player.getScore());
                    pstmt.executeUpdate();
                }
            }
        } catch (SQLException e) {
            System.err.println("Database error when saving score: " + e.getMessage());
        }
    }
}