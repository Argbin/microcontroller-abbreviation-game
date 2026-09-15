package quizgame.ui;

import quizgame.controller.QuizSession;
import quizgame.model.Player;
import quizgame.model.Question;

import java.util.List;
import java.util.Scanner;

public class ConsoleUI {
    private final QuizSession session;
    private final Scanner scanner;

    public ConsoleUI(QuizSession session) {
        this.session = session;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("==================================");
        System.out.println("WELCOME TO THE MICROPROCESSOR QUIZ");
        System.out.println("==================================");

        System.out.print("State your name: ");
        String name = scanner.nextLine();

        session.startGame(name);

        List<Player> topScores = session.getTopScores();
        System.out.println("\nTOP 5 HIGHSCORE");
        System.out.println("-------------------------");
        if (topScores.isEmpty()) {
            System.out.println("No points yet! Become the first!");
        } else {
            int rank = 1;
            for (Player p : topScores) {
                System.out.println(" " + rank + ". " + p.getPlayerName() + " - " + p.getScore() + " points");
                rank++;
            }
        }
        System.out.println("-------------------------");

        System.out.print("\nPress enter to start the game!");
        scanner.nextLine();

        System.out.println("\nThe game is starting. Type 'exit' anytime to quit.");

        Question currentQuestion = session.getNextQuestion();

        while (currentQuestion != null) {
            System.out.println("\nWhat does the abbreviation '" + currentQuestion.getAbbreviation() + "' stand for?");
            System.out.print("Answer: ");
            String answer = scanner.nextLine();

            if (answer.equalsIgnoreCase("exit")) {
                break;
            }

            boolean isCorrect = session.processAnswer(currentQuestion, answer);

            if (isCorrect) {
                System.out.println("Correct! You got one point!");
            } else {
                System.out.println("Wrong! The correct answer is: " + currentQuestion.getFullTerm());
            }

            currentQuestion = session.getNextQuestion();
        }

        session.endGame();
        System.out.println("\n==================================");
        System.out.println("The game is over!");
        System.out.println("You got " + session.getPlayer().getScore() + " points!.");
        System.out.println("==================================");

        scanner.close();
    }
}