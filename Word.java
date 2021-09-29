class Word {
    private String word_target;
    private String word_explain;

    public Word(String target, String explain) {
        word_explain = explain;
        word_target = target;
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
}