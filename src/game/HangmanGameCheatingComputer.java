package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import util.ConsoleReader;
import util.DisplayWord;
import util.HangmanDictionary;


/**
 * This class represents the traditional word-guessing game Hangman
 * that plays interactively with the user.
 *
 * @author Robert C. Duvall
 */
public class HangmanGameCheatingComputer {
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";


    private UserGuesser guesser;
    private CheatingExecutioner cheatingExecutioner;

    /**
     * Create Hangman game with the given dictionary of words to play a game with words
     * of the given length and giving the user the given number of chances.
     */
    public HangmanGameCheatingComputer (HangmanDictionary dictionary, int wordLength, int numGuesses) {
        cheatingExecutioner = new CheatingExecutioner(dictionary, wordLength);
        guesser = new UserGuesser();

        // Generate and assign the secret word before any guesses
        String secretWord = cheatingExecutioner.makeSecretWord(dictionary, wordLength);// Generates the secret word
        System.out.println("Secret Word: " + secretWord);
        guesser.getLettersLeftToGuess().append(ALPHABET);
        guesser.setNumGuessesLeft(numGuesses);

        // Initialize the display word for the secret word
        cheatingExecutioner.setDisplayWord(new DisplayWord(secretWord));
    }

    /**
     * Play one complete game.
     */
    public void play () {
        boolean gameOver = false;
        while (!gameOver) {
            printStatus();

            String guess = ConsoleReader.promptString("Make a guess: ");

            if (guess.length() == 1 && Character.isAlphabetic(guess.charAt(0))) {

                char letguess = guess.charAt(0);
                cheatingExecutioner.cheat(letguess);
                guesser.makeGuess(guess.toLowerCase().charAt(0), guesser.getLettersLeftToGuess(), cheatingExecutioner);

                if (guesser.isGameLost()) {
                    System.out.println("YOU ARE HUNG!!!");
                    gameOver = true;
                }
                else if (cheatingExecutioner.isGameWon()) {
                    System.out.println("YOU WIN!!!");
                    gameOver = true;
                }
            }
            else {
                System.out.println("Please enter a single letter ...");
            }
        }
        System.out.println("The secret word was " + cheatingExecutioner.getSecretWord());
    }


    private void printStatus () {
        System.out.println(cheatingExecutioner.getSecretWord());
        System.out.println("# misses left = " + guesser.getNumGuessesLeft());
        System.out.println("letters not yet guessed = " + guesser.getLettersLeftToGuess());
        // NOT PUBLIC, but makes it easier to test
        System.out.println("*** " + cheatingExecutioner.getSecretWord());
        System.out.println();
    }
}
