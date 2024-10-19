package game;

public abstract class BaseGuesser {

    protected int MY_NUM_GUESSES_LEFT;

    protected StringBuilder LETTERS_LEFT_TO_GUESS;


    public BaseGuesser() {
        // Initialize the StringBuilder for letters left to guess
        LETTERS_LEFT_TO_GUESS = new StringBuilder();
    }

     //Process a guess by updating the necessary internal state.
    public void makeGuess (char guess, StringBuilder LETTERS_LEFT_TO_GUESS,BaseExecutioner executioner) {}


    //records the guess comparing to the letters left to guess
    protected void recordGuess(int index) {
        LETTERS_LEFT_TO_GUESS.deleteCharAt(index);
    }

    //checks if the guesser exhausted all guesses
    public boolean isGameLost () {
        return MY_NUM_GUESSES_LEFT == 0;
    }

    //gets the letters left to guess
    public StringBuilder getLettersLeftToGuess() {
        return LETTERS_LEFT_TO_GUESS;
    }
    //gets number of letters left to guess
    public int getNumGuessesLeft() {
        return MY_NUM_GUESSES_LEFT;
    }

    //initializes the number of guesses
    public void setNumGuessesLeft(int MY_NUM_GUESSES_LEFT) {
        this.MY_NUM_GUESSES_LEFT = MY_NUM_GUESSES_LEFT;
    }


}
