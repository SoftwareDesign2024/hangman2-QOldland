package game;

import util.DisplayWord;
import util.HangmanDictionary;

public abstract class BaseExecutioner {
    protected String MY_SECRET_WORD;

    protected DisplayWord MY_DISPLAY_WORD;

    public BaseExecutioner() {
        MY_SECRET_WORD = "";
    }

    // Generate a secret word and store it in MY_SECRET_WORD
    public String makeSecretWord(HangmanDictionary dictionary, int wordLength) {
        MY_SECRET_WORD = dictionary.getRandomWord(wordLength).toLowerCase();
        return MY_SECRET_WORD;
    }

    //alwavs the executioner to check the guess made by guesser
    public boolean checkGuessInSecret (char guess) {
        if (MY_SECRET_WORD.indexOf(guess) >= 0) {
            MY_DISPLAY_WORD.update(guess, MY_SECRET_WORD);
            return true;
        }
        return false;
    }

    //checks if the guesser correctly guessed the secret word
    public boolean isGameWon () {
        return MY_DISPLAY_WORD.equals(MY_SECRET_WORD);
    }

    public DisplayWord getDisplayWord () {
        return MY_DISPLAY_WORD;
    }
    //getter for the secret word
    public String getSecretWord() {
        return MY_SECRET_WORD;
    }
    //setter for display word
    public void setDisplayWord(DisplayWord MY_DISPLAY_WORD) {
        this.MY_DISPLAY_WORD = MY_DISPLAY_WORD;
    }


}
