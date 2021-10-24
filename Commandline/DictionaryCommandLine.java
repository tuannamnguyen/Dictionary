package Dictionary.Commandline;

import java.util.Scanner;

public class DictionaryCommandLine {
    private static Scanner sc = new Scanner(System.in);
    public static void dictionaryBasic(DictionaryManagement dict) {
        dict.insertFromCommandline();
        System.out.println("\n");

        dict.showAllWords();
        System.out.println("\n");
    }

    public static void dictionaryAdvanced(DictionaryManagement dict) {
        dict.insertFromFile();
        System.out.println("\n");

        dict.showAllWords();
        System.out.println("\n");

        System.out.println("Nhap tu ma ban muon tra nghia: ");
        String target = sc.nextLine();
        System.out.println(dict.dictionaryLookup(target));
        System.out.println("\n");

        dict.dictionaryEdit();
        System.out.println("\n");

        dict.exportToFile();
        System.out.println("\n");
    }

    public static void dictionarySearcher(DictionaryManagement dict) {
        
        System.out.print("Nhap tu ban muon tim: ");
        String search = sc.nextLine();

        System.out.println("English           | Vietnamese");

        for (int i = 0; i < dict.getWordArray().size(); i++) {
            if (dict.getWordArray().get(i).getTarget().contains(search)) {
                System.out.println(dict.getWordArray().get(i).getTarget() + "           | "
                + dict.getWordArray().get(i).getExplain());
            }
        }

        sc.close();
    }
}
