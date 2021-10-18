package Dictionary.Commandline;
public class Word implements Comparable<Word> {
    private String word_target;
    private String word_explain;

    public Word(String target, String explain) {
        word_explain = explain;
        word_target = target;
    }

    public Word() {
        word_explain = "";
        word_target = "";
    }

    public String getTarget() {
        return this.word_target;
    }

    public String getExplain() {
        return this.word_explain;
    }

    public void setTarget(String target) {
        this.word_target = target;
    }

    public void setExplain(String explain) {
        this.word_explain = explain;
    }

    @Override
    public int compareTo(Word other) {
        return word_target.compareTo(other.word_target);
    }
}