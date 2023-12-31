import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Solvdle {

    public static void main(String[] args) {
        List<String> initialWordList = Arrays.asList("soare", "crane", "heart", "grape", "melon", "peach", "lemon");
        List<String> remainingWords = new ArrayList<>(initialWordList);
        Random random = new Random();

        int numGuesses = 0;
        double successRate = 0.0;

        while (successRate < 0.4) {
            String currentGuess = remainingWords.get(random.nextInt(remainingWords.size()));
            numGuesses++;

            // Simulate receiving feedback (replace with actual feedback)
            String feedback = simulateFeedback(currentGuess);

            // Update the word list based on feedback
            updateWordList(remainingWords, currentGuess, feedback);

            // Calculate the success rate
            successRate = calculateSuccessRate(remainingWords);
        }

        System.out.println("Bot took " + numGuesses + " guesses to achieve a success rate of " + (successRate * 100) + "%.");
    }

    private static String simulateFeedback(String guess, String targetWord) {
        StringBuilder feedback = new StringBuilder();

        // Loop through each letter in the guess and compare it with the target word
        for (int i = 0; i < guess.length(); i++) {
            char guessChar = guess.charAt(i);
            char targetChar = targetWord.charAt(i);

            if (guessChar == targetChar) {
                feedback.append("G"); // Green for correct position
            } else if (targetWord.contains(String.valueOf(guessChar))) {
                feedback.append("Y"); // Yellow for correct letter in wrong position
            } else {
                feedback.append("B"); // Black for incorrect letter
            }
        }

        return feedback.toString();
    }




    private static void updateWordList(List<String> wordList, String guess, String feedback) {
        // Update the word list based on feedback
        // Remove words that do not match the feedback
        // Replace this with actual word list updating logic
    }

    private static String selectNextGuess(List<String> remainingWords) {
        int maxEliminationCount = 0;
        String bestGuess = "";

        for (String word : remainingWords) {
            int eliminationCount = calculateEliminationCount(word, remainingWords);
            if (eliminationCount > maxEliminationCount) {
                maxEliminationCount = eliminationCount;
                bestGuess = word;
            }
        }

        return bestGuess;
    }

    private static int calculateEliminationCount(String guess, List<String> remainingWords) {
        int count = 0;

        for (String word : remainingWords) {
            if (feedbackMatches(guess, word)) {
                count++;
            }
        }

        return count;
    }

    private static boolean feedbackMatches(String guess, String word) {
        // Compare the feedback generated by the guess with the feedback generated by the word
        String feedbackGuess = simulateFeedback(guess, word);
        String feedbackWord = simulateFeedback(word, guess);

        return feedbackGuess.equals(feedbackWord);
    }

}

