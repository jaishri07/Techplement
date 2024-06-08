package com.example.quiz;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static List<Quiz> quizzes = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            printMenu();
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    createQuiz();
                    break;
                case 2:
                    addQuestionToQuiz();
                    break;
                case 3:
                    takeQuiz();
                    break;
                case 4:
                    System.out.println("Exiting the application.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\nQuiz Generator Menu:");
        System.out.println("1. Create a new quiz");
        System.out.println("2. Add a question to an existing quiz");
        System.out.println("3. Take a quiz");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void createQuiz() {
        System.out.print("Enter quiz title: ");
        String title = scanner.nextLine();
        quizzes.add(new Quiz(title));
        System.out.println("Quiz \"" + title + "\" created successfully.");
    }

    private static void addQuestionToQuiz() {
        if (quizzes.isEmpty()) {
            System.out.println("No quizzes available. Create a quiz first.");
            return;
        }

        System.out.println("Select a quiz to add a question to:");
        for (int i = 0; i < quizzes.size(); i++) {
            System.out.println((i + 1) + ". " + quizzes.get(i).title);
        }
        int quizChoice = Integer.parseInt(scanner.nextLine()) - 1;

        if (quizChoice < 0 || quizChoice >= quizzes.size()) {
            System.out.println("Invalid quiz selection.");
            return;
        }

        System.out.print("Enter question text: ");
        String questionText = scanner.nextLine();
        List<String> options = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            System.out.print("Enter option " + (i + 1) + ": ");
            options.add(scanner.nextLine());
        }
        System.out.print("Enter the number of the correct option (1-4): ");
        int correctOption = Integer.parseInt(scanner.nextLine()) - 1;

        quizzes.get(quizChoice).addQuestion(new Question(questionText, options, correctOption));
        System.out.println("Question added successfully.");
    }

    private static void takeQuiz() {
        if (quizzes.isEmpty()) {
            System.out.println("No quizzes available.");
            return;
        }

        System.out.println("Select a quiz to take:");
        for (int i = 0; i < quizzes.size(); i++) {
            System.out.println((i + 1) + ". " + quizzes.get(i).title);
        }
        int quizChoice = Integer.parseInt(scanner.nextLine()) - 1;

        if (quizChoice < 0 || quizChoice >= quizzes.size()) {
            System.out.println("Invalid quiz selection.");
            return;
        }

        Quiz selectedQuiz = quizzes.get(quizChoice);
        int score = 0;
        for (Question question : selectedQuiz.questions) {
            System.out.println("\n" + question.questionText);
            for (int i = 0; i < question.options.size(); i++) {
                System.out.println((i + 1) + ". " + question.options.get(i));
            }
            System.out.print("Your answer: ");
            int answer = Integer.parseInt(scanner.nextLine()) - 1;
            if (answer == question.correctOption) {
                score++;
            }
        }
        System.out.println("\nYou scored " + score + " out of " + selectedQuiz.questions.size());
    }
}
