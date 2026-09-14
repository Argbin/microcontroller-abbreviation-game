package quizgame.ui;

import quizgame.controller.QuizSession;
import quizgame.model.Question;

import java.util.Scanner;

public class ConsoleUI {
    private final QuizSession session;
    private final Scanner scanner;

    public ConsoleUI(QuizSession session) {
        this.session = session;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("=========================================");
        System.out.println(" VÄLKOMMEN TILL MIKROPROCESSOR-QUIZET! ");
        System.out.println("=========================================");

        System.out.print("Ange ditt namn: ");
        String name = scanner.nextLine();

        session.startGame(name);

        System.out.println("\nSpelet startar! Skriv 'exit' när som helst för att avsluta.");

        Question currentQuestion = session.getNextQuestion();

        while (currentQuestion != null) {
            System.out.println("\nVad står förkortningen '" + currentQuestion.getAbbreviation() + "' för?");
            System.out.print("Ditt svar: ");
            String answer = scanner.nextLine();

            if (answer.equalsIgnoreCase("exit")) {
                break;
            }

            boolean isCorrect = session.processAnswer(currentQuestion, answer);

            if (isCorrect) {
                System.out.println("Rätt! Snyggt jobbat.");
            } else {
                System.out.println("Fel. Rätt svar är: " + currentQuestion.getFullTerm());
            }

            currentQuestion = session.getNextQuestion();
        }

        session.endGame();
        System.out.println("\n=========================================");
        System.out.println("Spelet är slut, " + session.getPlayer().getPlayerName() + "!");
        System.out.println("Du fick ihop " + session.getPlayer().getScore() + " poäng.");
        System.out.println("Poängen har sparats i databasen.");
        System.out.println("=========================================");

        scanner.close();
    }
}