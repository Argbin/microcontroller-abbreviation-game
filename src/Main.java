
import quizgame.data.ConfigReader;
import quizgame.data.PostgresDatabaseHandler;
import quizgame.controller.QuizSession;
import quizgame.ui.ConsoleUI;

public class Main {
    public static void main(String[] args) {
        ConfigReader config = new ConfigReader("config.properties");
        PostgresDatabaseHandler db = new PostgresDatabaseHandler(config);

        QuizSession session = new QuizSession(db);

        ConsoleUI ui = new ConsoleUI(session);
        ui.start();
    }
}