package quizgame.controller;

import quizgame.data.DatabaseRepository;
import quizgame.model.Player;
import quizgame.model.Question;
import quizgame.model.QuestionBank;

import java.util.List;

public class QuizSession {
    private Player player;
    private QuestionBank questionBank;
    private final DatabaseRepository db;

    public QuizSession(DatabaseRepository db) {
        this.db = db;
    }

    public void startGame(String playerName) {
        player = new Player(playerName);

        List<Question> questions = db.fetchAllQuestions();
        questionBank = new QuestionBank(questions);
        questionBank.shuffleQuestions();
    }

    public Question getNextQuestion() {
        return questionBank.getNextQuestion();
    }

    public boolean processAnswer(Question question, String answer) {
        if (question.checkAnswer(answer)) {
            player.addPoint();
            return true;
        }
        return false;
    }

    public void endGame() {
        db.saveHighscore(player);
    }

    public Player getPlayer() {
        return player;
    }
}