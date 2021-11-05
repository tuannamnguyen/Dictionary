package Dictionary.Commandline;

public class test {
    public static void main(String[] args) {
        DictionaryManagement dict = new DictionaryManagement();

        DictionaryCommandLine.dictionaryBasic(dict);
        DictionaryCommandLine.dictionaryAdvanced(dict);
        DictionaryCommandLine.dictionarySearcher(dict);
    }
}
