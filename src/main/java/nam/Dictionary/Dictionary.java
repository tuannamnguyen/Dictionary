package nam.Dictionary;

import java.util.*;

public abstract class Dictionary {
    /**
     * word array to store data.
     */
    protected ArrayList<Word> wordArray;

    public Dictionary() {
        wordArray = new ArrayList<>();
    }

    public ArrayList<Word> getWordArray() {
        return this.wordArray;
    }

    /**
     * custom binary search that returns position in word array.
     * 
     * @param target word to search for.
     * @return position of word.
     */
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

    /**
     * import data from file into word array.
     */
    public abstract void insertFromFile();

    /**
     * export all words to file.
     */
    public abstract void exportToFile();

    /**
     * look for translation of a word.
     * 
     * @param target english.
     * @return vietnamese.
     */
    public String dictionaryLookup(String target) {
        if (target != null) {
            return this.wordArray.get(wordBinarySearch(target)).getExplain();
        } else {
            return "";
        }
    }

}
