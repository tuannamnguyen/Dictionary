package Dictionary;

public class Word implements Comparable<Word> {
    private String word_target;
    private String word_explain;

    /**
     * word object.
     * 
     * @param target  vietnamese.
     * @param explain english.
     */
    public Word(String target, String explain) {
        word_explain = explain;
        word_target = target;
    }

    /**
     * constructor.
     */
    public Word() {
        word_explain = "";
        word_target = "";
    }

    /**
     * english of this word.
     * 
     * @return english.
     */
    public String getTarget() {
        return this.word_target;
    }

    /**
     * vietnamese of this word.
     * 
     * @return vietnamese.
     */
    public String getExplain() {
        return this.word_explain;
    }

    /**
     * set english meaning for word.
     * 
     * @param target english.
     */
    public void setTarget(String target) {
        this.word_target = target;
    }

    /**
     * set vietnamese meaning for word.
     * 
     * @param explain vietnamese.
     */
    public void setExplain(String explain) {
        this.word_explain = explain;
    }

    /**
     * compare word a and b based on their english meaning.
     */
    @Override
    public int compareTo(Word other) {
        return word_target.compareTo(other.word_target);
    }
}