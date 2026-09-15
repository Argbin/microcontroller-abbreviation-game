package quizgame.data;

import quizgame.model.Player;
import quizgame.model.Question;
import java.util.List;

public interface DatabaseRepository {
    List<Question> fetchAllQuestions();
    void saveHighscore(Player player);
    List<Player> getTopHighscores(int limit);
}