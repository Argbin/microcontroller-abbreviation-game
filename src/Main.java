
import quizgame.data.ConfigReader;
import quizgame.data.PostgresDatabaseHandler;
import quizgame.model.Player;
import quizgame.model.Question;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Connecting to database...");

        ConfigReader config = new ConfigReader("config.properties");
        PostgresDatabaseHandler db = new PostgresDatabaseHandler(config);

        List<Question> questions = db.fetchAllQuestions();
        System.out.println("Found " + questions.size() + " questions in the database:");
        for (Question q : questions) {
            System.out.println(" - " + q.toString());
        }

        System.out.println("\nTrying to save player data");
        Player testPlayer = new Player("Albin");
        testPlayer.addPoint();
        testPlayer.addPoint();

        db.saveHighscore(testPlayer);

        System.out.println("Database operations complete");
    }
}