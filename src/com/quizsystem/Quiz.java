package com.quizsystem;

import java.util.*;

public class Quiz {
    private List<Question> questions;
    private int score;
    private int timeLimit;
    private List<Integer> incorrectAnswers;

    public Quiz(List<Question> questions, int timeLimit) {
        this.questions = questions;
        this.score = 0;
        this.timeLimit = timeLimit;
        this.incorrectAnswers = new ArrayList<>();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < questions.size(); i++) {
            long currentTime = System.currentTimeMillis();
            if ((currentTime - startTime) / 1000 > timeLimit) {
                System.out.println("Time's up!");
                break;
            }

            Question q = questions.get(i);
            System.out.println((i + 1) + ". " + q.getQuestion());
            List<String> options = q.getOptions();

            for (int j = 0; j < options.size(); j++) {
                System.out.println((j + 1) + ". " + options.get(j));
            }

            System.out.print("Your answer: ");
            int userAnswer = scanner.nextInt();

            if (userAnswer == q.getCorrectOption()) {
                score++;
            } else {
                incorrectAnswers.add(i);
            }
        }

        System.out.println("Quiz over! Your score: " + score + "/" + questions.size());
    }

    public void reviewIncorrectAnswers() {
        if (incorrectAnswers.isEmpty()) {
            System.out.println("No incorrect answers to review.");
        } else {
            System.out.println("Reviewing incorrect answers:");
            for (int i : incorrectAnswers) {
                Question q = questions.get(i);
                System.out.println("Q: " + q.getQuestion());
                System.out.println("Correct Answer: " + q.getOptions().get(q.getCorrectOption() - 1));
            }
        }
    }

    public void displayQuizSummary() {
        System.out.println("Quiz Summary:");
        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            System.out.println((i + 1) + ". " + q.getQuestion());
            System.out.println("Your Answer: " + (incorrectAnswers.contains(i) ? "Incorrect" : "Correct"));
            System.out.println("Correct Answer: " + q.getOptions().get(q.getCorrectOption() - 1));
            System.out.println();
        }
    }

    public void shuffleQuestions() {
        Collections.shuffle(questions);
        System.out.println("Questions have been shuffled.");
    }

    public void resetQuiz() {
        score = 0;
        incorrectAnswers.clear();
        System.out.println("Quiz has been reset.");
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
        System.out.println("Time limit set to " + timeLimit + " seconds.");
    }

    public void addQuestion(Question question) {
        questions.add(question);
        System.out.println("New question added to the quiz.");
    }

    public void displayQuestions() {
        System.out.println("Quiz Questions:");
        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            System.out.println((i + 1) + ". " + q.getQuestion());
        }
    }

    public int getScore() {
        return score;
    }

    public List<Question> getQuestions() {
        return questions;
    }
}
