package game;

import util.DisplayWord;
import util.HangmanDictionary;

import java.util.*;

public class CheatingExecutioner extends BaseExecutioner{

    private List<String> MY_REMAINING_WORDS;

    public CheatingExecutioner(HangmanDictionary dictionary,int wordLength) {
        super();
        MY_REMAINING_WORDS = dictionary.getWords(wordLength);
    }


    public void cheat(char guess) {
        // create template of guesses and find one with most matching remaining words
        HashMap<DisplayWord, List<String>> templatedWords = new HashMap<DisplayWord, List<String>>();
        for (String w : MY_REMAINING_WORDS) {
            DisplayWord template = new DisplayWord(MY_DISPLAY_WORD);
            template.update(guess, w);
            if (!templatedWords.containsKey(template)) {
                templatedWords.put(template, new ArrayList<>());
            }
            templatedWords.get(template).add(w);
        }
        int max = 0;
        DisplayWord maxKey = new DisplayWord("");
        for (Map.Entry<DisplayWord, List<String>> entry : templatedWords.entrySet()) {
            //System.out.println(entry.getValue());
            if (entry.getValue().size() > max) {
                max = entry.getValue().size();
                maxKey = entry.getKey();
            }
        }

        // update secret word to match template of guesses
        MY_REMAINING_WORDS = templatedWords.get(maxKey);
        Collections.shuffle(MY_REMAINING_WORDS);
        MY_SECRET_WORD = MY_REMAINING_WORDS.get(0);
        MY_DISPLAY_WORD = maxKey;
    }


}
