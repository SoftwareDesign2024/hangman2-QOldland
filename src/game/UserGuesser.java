package game;

public class UserGuesser extends BaseGuesser{

    public UserGuesser() {
        super();
    }

    @Override
    public void makeGuess (char guess, StringBuilder LETTERS_LEFT_TO_GUESS,BaseExecutioner executioner) {
        // do not count repeated guess as a miss
        int index = LETTERS_LEFT_TO_GUESS.indexOf("" + guess);
        if (index >= 0) {
            recordGuess(index);
            if (!executioner.checkGuessInSecret(guess)) {
                MY_NUM_GUESSES_LEFT -= 1;
            }
        }
    }

}
