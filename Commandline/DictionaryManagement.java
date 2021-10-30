package Dictionary.Commandline;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Scanner;

import Dictionary.Dictionary;

public class DictionaryManagement extends Dictionary {
    public DictionaryManagement() {
        super();
    }

    private Scanner sc = new Scanner(System.in);

    public void insertFromCommandline() {
        System.out.println("Nhap vao so luong tu muon nhap: ");

        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {
            System.out.println("Nhap vao tu Tieng Anh muon them vao: ");
            String eng = sc.nextLine();

            System.out.println("Nhap vao tu giai nghia: ");
            String vn = sc.nextLine();

            Word w = new Word(eng, vn);
            this.wordArray.add(w);
        }

        Collections.sort(this.wordArray);
    }

    public void showAllWords() {
        System.out.println("Danh sach tat ca cac tu dang co trong tu dien: ");
        System.out.println("No  | English           | Vietnamese");

        for (int i = 0; i < this.wordArray.size(); i++) {
            System.out.println((i + 1) + "  | " + this.wordArray.get(i).getTarget() + "           | "
                    + this.wordArray.get(i).getExplain());
        }
    }

    @Override
    public void insertFromFile() {
        try {
            File wordFile = new File("src\\Dictionary\\resources\\dictionaries.txt");
            Scanner fileReader = new Scanner(wordFile);

            while (fileReader.hasNextLine()) {
                String content = fileReader.nextLine();
                content.trim();
                String[] postSplit = content.split("    ");

                Word w = new Word(postSplit[0], postSplit[1]);
                this.wordArray.add(w);
            }

            fileReader.close();
            Collections.sort(this.wordArray);
        } catch (FileNotFoundException e) {
            System.out.println("Error. Khong tim thay file.");
        }
    }

    @Override
    public void exportToFile() {
        try {
            FileWriter myWriter = new FileWriter("src\\Dictionary\\resources\\dictionaries.txt");
            for (int i = 0; i < this.wordArray.size(); i++) {
                myWriter.write(this.wordArray.get(i).getTarget() + "    " + this.wordArray.get(i).getExplain());
                myWriter.write("\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    @Override
    public String dictionaryLookup(String target) {
        if (target != null) {
            return this.wordArray.get(wordBinarySearch(target)).getExplain();
        } else {
            return "";
        }
    }

    public void dictionaryEdit() {
        System.out.println("Chon thao tac ma ban muon sua: ");
        System.out.println("1. Xoa tu");
        System.out.println("2. Sua tu");

        int key = sc.nextInt();
        sc.nextLine();

        if (key == 1) {
            System.out.print("Nhap tu muon xoa: ");
            String delWord = sc.next();

            if (this.wordBinarySearch(delWord) != -1) {
                this.wordArray.remove(this.wordBinarySearch(delWord));
            } else {
                System.out.println("Khong tim thay tu can xoa!");
            }
        } else if (key == 2) {
            System.out.print("Nhap tu muon sua: ");
            String editWord = sc.nextLine();

            if (this.wordBinarySearch(editWord) != -1) {
                System.out.print("Sua lai nghia cua tu: ");
                String exWord = sc.nextLine();

                this.wordArray.get(wordBinarySearch(editWord)).setExplain(exWord);
            } else {
                System.out.println("Khong tim ra tu can sua!");
            }
        }

    }

}
