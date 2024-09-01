package com.quizsystem;

import java.io.*;
import java.util.*;

public class User {
    private String username;
    private int totalScore;
    private int quizzesTaken;

    public User(String username) {
        this.username = username;
        this.totalScore = 0;
        this.quizzesTaken = 0;
    }

    public void takeQuiz(Quiz quiz) {
        quiz.start();
        totalScore += quiz.getScore();
        quizzesTaken++;
        saveProgress();
    }

    public void displayProgress() {
        System.out.println("User: " + username);
        System.out.println("Total Score: " + totalScore);
        System.out.println("Quizzes Taken: " + quizzesTaken);
        if (quizzesTaken > 0) {
            System.out.println("Average Score: " + (totalScore / quizzesTaken));
        }
    }

    public void saveProgress() {
        try (FileWriter writer = new FileWriter(username + "_progress.txt")) {
            writer.write("Total Score: " + totalScore + "\n");
            writer.write("Quizzes Taken: " + quizzesTaken + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadProgress() {
        try (BufferedReader reader = new BufferedReader(new FileReader(username + "_progress.txt"))) {
            totalScore = Integer.parseInt(reader.readLine().split(": ")[1]);
            quizzesTaken = Integer.parseInt(reader.readLine().split(": ")[1]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reviewQuiz(Quiz quiz) {
        quiz.reviewIncorrectAnswers();
        quiz.displayQuizSummary();
    }

    public void resetProgress() {
        totalScore = 0;
        quizzesTaken = 0;
        saveProgress();
        System.out.println("User progress has been reset.");
    }

    public void updateUsername(String newUsername) {
        this.username = newUsername;
        System.out.println("Username updated to: " + newUsername);
    }

    public void deleteProgressFile() {
        File file = new File(username + "_progress.txt");
        if (file.delete()) {
            System.out.println("Progress file deleted.");
        } else {
            System.out.println("Failed to delete progress file.");
        }
    }

    public void displayUserInfo() {
        System.out.println("User Info:");
        System.out.println("Username: " + username);
        System.out.println("Total Score: " + totalScore);
        System.out.println("Quizzes Taken: " + quizzesTaken);
    }
}
