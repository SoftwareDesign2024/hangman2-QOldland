package game;

import util.ConsoleReader;
import util.DisplayWord;
import util.HangmanDictionary;

public class HangmanGameInteractiveGuesser {
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    private UserGuesser guesser;
    private UserExecutioner executioner;

    /**
     * Create Hangman game with the given dictionary of words to play a game with words
     * of the given length and giving the user the given number of chances.
     */
    public HangmanGameInteractiveGuesser (HangmanDictionary dictionary, int wordLength, int numGuesses) {
        executioner = new UserExecutioner(dictionary);
        guesser = new UserGuesser();

        // Generate and assign the secret word before any guesses
        String secretWord = executioner.makeSecretWord(dictionary, wordLength);// Generates the secret word
        System.out.println("Secret Word: " + secretWord);
        guesser.getLettersLeftToGuess().append(ALPHABET);
        guesser.setNumGuessesLeft(numGuesses);

        // Initialize the display word for the secret word
        executioner.setDisplayWord(new DisplayWord(secretWord));
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
                guesser.makeGuess(guess.toLowerCase().charAt(0), guesser.getLettersLeftToGuess(), executioner);
                if (guesser.isGameLost()) {
                    System.out.println("YOU ARE HUNG!!!");
                    gameOver = true;
                }
                else if (executioner.isGameWon()) {
                    System.out.println("YOU WIN!!!");
                    gameOver = true;
                }
            }
            else {
                System.out.println("Please enter a single letter ...");
            }
        }
        System.out.println("The secret word was " + executioner.getSecretWord());
    }


    // Print game stats
    private void printStatus () {
        System.out.println(executioner.getDisplayWord());
        System.out.println("# misses left = " + guesser.getNumGuessesLeft());
        System.out.println("letters not yet guessed = " + guesser.getLettersLeftToGuess());
        // NOT PUBLIC, but makes it easier to test
        //System.out.println("*** " + mySecretWord);
        System.out.println();
    }

}
