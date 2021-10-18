package Dictionary.Commandline;
public class test {
    public static void main(String[] args) {
        Dictionary dict = new Dictionary();

        DictionaryCommandLine.dictionaryBasic(dict);
        DictionaryCommandLine.dictionaryAdvanced(dict);
        DictionaryCommandLine.dictionarySearcher(dict);
    }
}
