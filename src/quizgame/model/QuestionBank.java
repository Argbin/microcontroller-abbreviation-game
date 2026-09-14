package quizgame.model;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class QuestionBank {
    private List<Question> questions;

    public QuestionBank(List<Question> initialQuestions) {
        this.questions = new ArrayList<>(initialQuestions);
    }

    public void shuffleQuestions() {
        Collections.shuffle(questions);
    }

    public boolean hasQuestions() {
        return !questions.isEmpty();
    }

    public Question getNextQuestion() {
        if (hasQuestions()) {
            return questions.removeFirst();
        }
        return null;
    }
}