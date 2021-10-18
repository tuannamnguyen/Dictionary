package Dictionary.Commandline;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Dictionary {
    ArrayList<Word> wordArray = new ArrayList<>();

    public ArrayList<Word> getWordArray() {
        return this.wordArray;
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

    private int wordBinarySearch(String target) {
        int left = 0;
        int right = this.wordArray.size() - 1;

        while (left <= right) {
            int mid = right + (left - right) / 2;

            if (target.compareTo(this.wordArray.get(mid).getTarget()) == 0) {
                return mid;
            } else if (target.compareTo(this.wordArray.get(mid).getTarget()) > 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    public void dictionaryLookup() {
        System.out.println("Nhap tu ma ban muon tra nghia: ");
        String target = sc.nextLine();

        System.out.println(this.wordArray.get(wordBinarySearch(target)).getExplain());

    }

    public String dictionaryLookupForGUI(String target) {
        return this.wordArray.get(wordBinarySearch(target)).getExplain();
    }

    public void dictionaryEdit() {
        System.out.println("Chon thao tac ma ban muon sua: ");
        System.out.println("1. Xoa tu");
        System.out.println("2. Sua tu");

        int key = sc.nextInt();

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
            String editWord = sc.next();

            if (this.wordBinarySearch(editWord) != -1) {
                System.out.print("Sua lai nghia cua tu: ");
                String exWord = sc.nextLine();

                this.wordArray.get(wordBinarySearch(editWord)).setExplain(exWord);
            } else {
                System.out.println("Khong tim ra tu can sua!");
            }
        }

    }

    public void exportToFile() {
        try {
            FileWriter myWriter = new FileWriter("src\\Dictionary\\dictionaries.txt");
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
}
