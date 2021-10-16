package Dictionary;

import java.util.Scanner;

class DictionaryCommandLine {
    public static void dictionaryBasic(Dictionary dict) {
        dict.insertFromCommandline();
        System.out.println("\n");

        dict.showAllWords();
        System.out.println("\n");
    }

    public static void dictionaryAdvanced(Dictionary dict) {
        dict.insertFromFile();
        System.out.println("\n");

        dict.showAllWords();
        System.out.println("\n");

        dict.dictionaryLookup();
        System.out.println("\n");

        dict.dictionaryEdit();
        System.out.println("\n");

        dict.exportToFile();
        System.out.println("\n");
    }

    public static void dictionarySearcher(Dictionary dict) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap tu ban muon tim: ");
        String search = sc.nextLine();

        System.out.println("English           | Vietnamese");

        for (int i = 0; i < dict.wordArray.size(); i++) {
            if (dict.wordArray.get(i).getTarget().contains(search)) {
                System.out.println(dict.wordArray.get(i).getTarget() + "           | "
                + dict.wordArray.get(i).getExplain());
            }
        }

        sc.close();
    }
}
