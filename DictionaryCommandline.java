package Dictionary;

class DictionaryCommandLine {
//    public static void dictionaryBasic(Dictionary dict) {
//        dict.insertFromCommandline();
//        dict.showAllWords();
//    }

    public static void dictionaryAdvanced(Dictionary dict) {
        dict.insertFromFile();
        dict.insertFromCommandline();
        dict.showAllWords();
        dict.dictionaryLookup();
        boolean x = dict.dictionaryEdit();
        if (x) {
            dict.showAllWords();
        }
//        dict.exportFile();


    }

}
