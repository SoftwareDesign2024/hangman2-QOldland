package game;

public class AutoGuesser extends BaseGuesser {


    private StringBuilder MY_LETTERS;
    private int MY_INDEX;

    public AutoGuesser() {
        super();
        MY_LETTERS = new StringBuilder("etaoinshrldcumfpgwybvkxjqz");
        MY_INDEX = 0;
    }


    public void makeGuess (StringBuilder LETTERS_LEFT_TO_GUESS,BaseExecutioner executioner) {
        Character guess = MY_LETTERS.charAt(MY_INDEX++);
        // do not count repeated guess as a miss
        int index = LETTERS_LEFT_TO_GUESS.indexOf("" + guess);
        if (index >= 0) {
            recordGuess(index);
            if (! executioner.checkGuessInSecret(guess)) {
                MY_NUM_GUESSES_LEFT -= 1;
            }
        }
    }

    public StringBuilder getMyLetters() {
        return MY_LETTERS;
    }




}
