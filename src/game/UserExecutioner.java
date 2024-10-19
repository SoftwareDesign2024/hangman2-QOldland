package game;

import util.ConsoleReader;
import util.HangmanDictionary;

public class UserExecutioner extends BaseExecutioner{


    UserExecutioner(HangmanDictionary dictionary) {
        super();
    }

    public void setSecretWord(int wordLength, HangmanDictionary dictionary) {
        String result = ConsoleReader.promptString("Choose a secret word that is " + wordLength + " letters long: ");
        while (! dictionary.contains(result, wordLength)) {
            result = ConsoleReader.promptString("That word is not recognized, please choose another: ");
        }
        MY_SECRET_WORD = result;
    }


}
