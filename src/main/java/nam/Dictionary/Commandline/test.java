package nam.Dictionary.Commandline;

public class test {

    /**
     * test runner.
     * 
     * @param args commandline args.
     */
    public static void main(String[] args) {
        DictionaryManagement dict = new DictionaryManagement();

        DictionaryCommandLine.dictionaryBasic(dict);
        DictionaryCommandLine.dictionaryAdvanced(dict);
        DictionaryCommandLine.dictionarySearcher(dict);
    }
}
