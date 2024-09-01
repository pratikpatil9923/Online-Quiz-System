package com.quizsystem;

import java.io.IOException;
import java.util.*;

public class OnlineQuizSystem {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        // User Registration/Login
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        User user = new User(username);

        // Load User Progress
        user.loadProgress();

        // Sample Questions
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("What is the capital of France?", Arrays.asList("Berlin", "Madrid", "Paris", "Lisbon"), 3));
        questions.add(new Question("Which planet is known as the Red Planet?", Arrays.asList("Earth", "Mars", "Jupiter", "Saturn"), 2));
        questions.add(new Question("What is the square root of 64?", Arrays.asList("6", "7", "8", "9"), 3));
        questions.add(new Question("Who wrote 'Hamlet'?", Arrays.asList("Charles Dickens", "William Shakespeare", "Mark Twain", "Jane Austen"), 2));
        questions.add(new Question("Which element has the chemical symbol 'O'?", Arrays.asList("Oxygen", "Osmium", "Gold", "Oganesson"), 1));
        questions.add(new Question("What is the largest planet in our solar system?", Arrays.asList("Earth", "Jupiter", "Mars", "Saturn"), 2));
        questions.add(new Question("Which country is known as the Land of the Rising Sun?", Arrays.asList("China", "Japan", "Thailand", "South Korea"), 2));
        questions.add(new Question("Who painted the Mona Lisa?", Arrays.asList("Vincent van Gogh", "Leonardo da Vinci", "Pablo Picasso", "Claude Monet"), 2));
        questions.add(new Question("Which gas is most abundant in the Earth's atmosphere?", Arrays.asList("Oxygen", "Nitrogen", "Carbon Dioxide", "Hydrogen"), 2));
        questions.add(new Question("Who is known as the father of computers?", Arrays.asList("Alan Turing", "Charles Babbage", "Ada Lovelace", "John von Neumann"), 2));
        questions.add(new Question("What is the chemical formula for table salt?", Arrays.asList("NaCl", "KCl", "CaCl2", "MgCl2"), 1));
        questions.add(new Question("Which is the longest river in the world?", Arrays.asList("Nile", "Amazon", "Yangtze", "Mississippi"), 2));
        questions.add(new Question("What is the powerhouse of the cell?", Arrays.asList("Nucleus", "Mitochondria", "Ribosome", "Endoplasmic Reticulum"), 2));
        questions.add(new Question("In which year did World War I begin?", Arrays.asList("1912", "1914", "1916", "1918"), 2));
        questions.add(new Question("What is the smallest planet in our solar system?", Arrays.asList("Mercury", "Mars", "Venus", "Earth"), 1));
        questions.add(new Question("Who discovered penicillin?", Arrays.asList("Louis Pasteur", "Alexander Fleming", "Joseph Lister", "Robert Koch"), 2));
        questions.add(new Question("What is the speed of light?", Arrays.asList("299,792 km/s", "300,000 km/s", "150,000 km/s", "1,000,000 km/s"), 1));
        questions.add(new Question("Which element is used in the batteries of electric cars?", Arrays.asList("Lead", "Lithium", "Nickel", "Cobalt"), 2));
        questions.add(new Question("What is the capital of Japan?", Arrays.asList("Beijing", "Seoul", "Tokyo", "Bangkok"), 3));
        questions.add(new Question("Who is the author of '1984'?", Arrays.asList("Aldous Huxley", "George Orwell", "H.G. Wells", "Ray Bradbury"), 2));
        questions.add(new Question("What is the freezing point of water?", Arrays.asList("0°C", "32°C", "100°C", "-1°C"), 1));
        questions.add(new Question("What is the hardest natural substance on Earth?", Arrays.asList("Gold", "Iron", "Diamond", "Silver"), 3));

        // Create Quiz
        Quiz quiz = new Quiz(questions, 60); // 60 seconds time limit

        // Shuffle questions
        quiz.shuffleQuestions();

        // Start Quiz
        user.takeQuiz(quiz);

        // Display Quiz Summary
        quiz.displayQuizSummary();

        // Review Incorrect Answers
        user.reviewQuiz(quiz);

        // Display Progress
        user.displayProgress();

        // Reset Quiz
        quiz.resetQuiz();

        // Add a new question
        quiz.addQuestion(new Question("What is the currency of Japan?", Arrays.asList("Dollar", "Yuan", "Euro", "Yen"), 4));

        // Display all questions
        quiz.displayQuestions();

        // Save Progress
        user.saveProgress();

        // Reset Progress
        user.resetProgress();

        // Update Username
        user.updateUsername("NewUsername");

        // Delete Progress File
        user.deleteProgressFile();

        // Display User Information
        user.displayUserInfo();
    }
}
