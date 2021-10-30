package Dictionary;

import java.util.*;

import Dictionary.Commandline.Word;

public abstract class Dictionary {
    protected ArrayList<Word> wordArray;

    public Dictionary() {
        wordArray = new ArrayList<>();
    }

    public ArrayList<Word> getWordArray() {
        return this.wordArray;
    }

    public int wordBinarySearch(String target) {
        int left = 0;
        int right = this.wordArray.size() - 1;

        while (left <= right) {
            int mid = right + (left - right) / 2;

            if (target.compareTo(this.wordArray.get(mid).getTarget()) == 0) {
                return mid;
            } else if (target.compareTo(this.wordArray.get(mid).getTarget()) > 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    public abstract void insertFromFile();

    public abstract void exportToFile();

    public abstract String dictionaryLookup(String target);
    
}
