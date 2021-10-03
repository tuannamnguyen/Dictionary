package Dictionary;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

class Dictionary {
    ArrayList<Word> wordArray = new ArrayList<>();

    Scanner sc = new Scanner(System.in);
    
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
            File wordFile = new File("src\\Dictionary\\dictionaries.txt");
            Scanner fileReader = new Scanner(wordFile);

            while (fileReader.hasNextLine()) {
                String content = fileReader.nextLine();
                content.trim();
                String[] postSplit = content.split("    "); // Lấy từ tiếng Anh và tiếng Việt

                Word w = new Word(postSplit[0], postSplit[1]);
                this.wordArray.add(w);
            }

            Collections.sort(this.wordArray);
        } catch (FileNotFoundException e) {
            System.out.println("Error. Khong tim thay file.");
        }
    }

    private String wordBinarySearch(String target) {
        int left = 0;
        int right = this.wordArray.size() - 1;

        while (left <= right) {
            int mid = right + (left - right) / 2;

            if (target.compareTo(this.wordArray.get(mid).getTarget()) == 0) {
                return wordArray.get(mid).getExplain();
            } else if (target.compareTo(this.wordArray.get(mid).getTarget()) > 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return "Khong tim thay ket qua mong muon";
    }

    public void dictionaryLookup() {
        System.out.println("Nhap tu ma ban muon tra nghia: ");
        String target = sc.nextLine();

        System.out.println(this.wordBinarySearch(target));

    }
}
