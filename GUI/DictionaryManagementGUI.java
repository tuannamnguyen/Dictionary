package Dictionary.GUI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Scanner;

import Dictionary.Dictionary;
import Dictionary.Word;

public class DictionaryManagementGUI extends Dictionary {
    public DictionaryManagementGUI() {
        super();
    }

    @Override
    public void insertFromFile() {
        try {
            File wordFile = new File("src\\Dictionary\\resources\\UpdatedDictionary.txt");
            Scanner fileReader = new Scanner(wordFile);
            String eng = new String();
            String vn = new String();

            StringBuilder line = new StringBuilder();
            String temp = "";
            while (fileReader.hasNextLine()) {
                temp = fileReader.nextLine() + "\n";
                line.append(temp);
            }
            String[] eachWord = line.toString().split("@");
            for (int i = 1; i < eachWord.length; i++) {
                if (eachWord[i].contains("/")) {
                    int k = eachWord[i].indexOf("/");
                    eng = eachWord[i].substring(0, k - 1);
                    vn = eachWord[i].substring(k, eachWord[i].length() - 1);
                    Word w = new Word(eng, vn);
                    this.wordArray.add(w);
                }
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error. Khong tim thay file.");
        }
    }

    @Override
    public void exportToFile() {
        try {
            FileWriter myWriter = new FileWriter("src\\Dictionary\\resources\\UpdatedDictionary.txt");
            for (int i = 0; i < this.wordArray.size(); i++) {
                myWriter.write(String.format("@%s %s\n", this.wordArray.get(i).getTarget(),
                        this.wordArray.get(i).getExplain()));
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DictionaryManagementGUI d = new DictionaryManagementGUI();
        d.insertFromFile();
        Collections.sort(d.getWordArray());
        d.exportToFile();
    }
}
