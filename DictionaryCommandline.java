class DictionaryCommandLine {
    public static void dictionaryBasic(Dictionary dict) {
        dict.insertFromCommandline();
        dict.showAllWords();
    }

    public static  void dictionaryAdvanced(Dictionary dict) {
        dict.insertFromFile();
        dict.dictionaryLookup();
        dict.showAllWords();
    }
}
