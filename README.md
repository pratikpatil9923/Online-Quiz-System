import java.io.*;
import java.util.*;

class Question {
    private String question;
    private List<String> options;
    private int correctOption;

    public Question(String question, List<String> options, int correctOption) {
        this.question = question;
        this.options = options;
        this.correctOption = correctOption;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectOption() {
        return correctOption;
    }
}

class Quiz {
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

class User {
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
        questions.add(new Question("Who is known as the father of computers?", Arrays.asList("Alan Turing", "Charles Babbage", "Thomas Edison", "Isaac Newton"), 2));
        questions.add(new Question("What is the capital of Italy?", Arrays.asList("Rome", "Venice", "Milan", "Florence"), 1));
        questions.add(new Question("Which ocean is the largest?", Arrays.asList("Atlantic Ocean", "Indian Ocean", "Arctic Ocean", "Pacific Ocean"), 4));
        questions.add(new Question("Who discovered penicillin?", Arrays.asList("Marie Curie", "Alexander Fleming", "Louis Pasteur", "Isaac Newton"), 2));
        questions.add(new Question("What is the smallest prime number?", Arrays.asList("1", "2", "3", "5"), 2));
        questions.add(new Question("What is the main ingredient in guacamole?", Arrays.asList("Tomato", "Cucumber", "Avocado", "Pepper"), 3));
        questions.add(new Question("Which language is used to create Android apps?", Arrays.asList("Swift", "Java", "Python", "Kotlin"), 2));
        questions.add(new Question("Which planet has the most moons?", Arrays.asList("Earth", "Mars", "Jupiter", "Saturn"), 4));
        questions.add(new Question("What is the hardest natural substance on Earth?", Arrays.asList("Gold", "Iron", "Diamond", "Silver"), 3));
        questions.add(new Question("Who developed the theory of relativity?", Arrays.asList("Isaac Newton", "Nikola Tesla", "Albert Einstein", "Stephen Hawking"), 3));
        questions.add(new Question("What is the chemical symbol for water?", Arrays.asList("O", "H", "H2O", "HO2"), 3));

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
