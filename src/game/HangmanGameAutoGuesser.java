package game;

import util.ConsoleReader;
import util.DisplayWord;
import util.HangmanDictionary;


/**
 * This class represents the traditional word-guessing game Hangman
 * where the computer guesses letters based on a predictable pattern.
 *
 * @author Robert C. Duvall
 */
public class HangmanGameAutoGuesser {
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    // word that is being guessed
    private String secretWord;

    private AutoGuesser autoGuesser;
    private UserExecutioner executioner;

    /**
     * Create Hangman game with the given dictionary of words to play a game with words
     * of the given length and giving the user the given number of chances.
     */
    public HangmanGameAutoGuesser (HangmanDictionary dictionary, int wordLength, int numGuesses) {
        executioner = new UserExecutioner(dictionary);
        autoGuesser = new AutoGuesser();



        // Generate and assign the secret word before any guesses
        executioner.setSecretWord(wordLength,dictionary);
        System.out.println("Secret Word: " + executioner.getSecretWord());
        autoGuesser.getLettersLeftToGuess().append(ALPHABET);
        autoGuesser.setNumGuessesLeft(numGuesses);

        // Initialize the display word for the secret word
        executioner.setDisplayWord(new DisplayWord(executioner.getSecretWord()));
    }



    // Returns a secret word.
    private String getSecretWord (HangmanDictionary dictionary, int wordLength) {
        String result = ConsoleReader.promptString("Choose a secret word that is " + wordLength + " letters long: ");
        while (! dictionary.contains(result, wordLength)) {
            result = ConsoleReader.promptString("That word is not recognized, please choose another: ");
        }
        return result;
    }

    /**
     * Play one complete game.
     */
    public void play () {
        boolean gameOver = false;
        while (!gameOver) {
            printStatus();

            autoGuesser.makeGuess(autoGuesser.getLettersLeftToGuess(),executioner);
            if (autoGuesser.isGameLost()) {
                System.out.println("YOU ARE HUNG!!!");
                gameOver = true;
            }
            else if (executioner.isGameWon()) {
                System.out.println("YOU WIN!!!");
                gameOver = true;
            }
        }
        System.out.println("The secret word was " + executioner.getSecretWord());
    }



    // Print game stats
    private void printStatus () {
        System.out.println(executioner.getDisplayWord());
        System.out.println("# misses left = " + autoGuesser.getNumGuessesLeft());
        System.out.println("letters not yet guessed = " + autoGuesser.getLettersLeftToGuess());
        // NOT PUBLIC, but makes it easier to test
        //System.out.println("*** " + mySecretWord);
        System.out.println();
    }
}
