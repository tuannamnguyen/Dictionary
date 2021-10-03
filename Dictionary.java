package Dictionary;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;


class Dictionary {
    //    ArrayList<Word> wordArray = new ArrayList<>();
    Map<String, String> wordArray = new HashMap<>();
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

//            Word w = new Word(eng, vn);
//            this.wordArray.add(w);
            wordArray.put(eng, vn);
        }

//        Collections.sort(this.wordArray); 


    }

    public void showAllWords() {
//        System.out.println(this.wordArray.toString());
        System.out.println("Danh sach tat ca cac tu dang co trong tu dien: ");
        System.out.println("No  | English           | Vietnamese");

//        for (int i = 0; i < this.wordArray.size(); i++) { 
//            System.out.println((i + 1) + "  | " + this.wordArray.get(i).getTarget() + "           | " 
//                    + this.wordArray.get(i).getExplain()); 
//        }
        AtomicInteger index = new AtomicInteger(1);
        this.wordArray.forEach((key, value) -> {
            System.out.println(index + "  | " + key + "           | " + value);
            index.addAndGet(1);
        });
    }

    public void insertFromFile() {
        try {
            File wordFile;
            wordFile = new File("dictionary\\src\\dictionaries.txt");
            Scanner fileReader = new Scanner(wordFile);

            while (fileReader.hasNextLine()) {
                String content = fileReader.nextLine();
                content.trim();
                String[] postSplit = content.split("    "); // Lấy từ tiếng Anh và tiếng Việt

//                Word w = new Word(postSplit[0], postSplit[1]);
                wordArray.put(postSplit[0], postSplit[1]);
            }

//            Collections.sort(this.wordArray);
        } catch (FileNotFoundException e) {
            System.out.println("Error. Khong tim thay file.");
        }
    }

//    private String wordBinarySearch(String target) {
////        int left = 0;
////        int right = this.wordArray.size() - 1;
////
//////        while (left <= right) {
//////            int mid = right + (left - right) / 2;
//////
//////            if (target.compareTo(this.wordArray.get(mid).getTarget()) == 0) {
//////                return wordArray.get(mid).getExplain();
//////            } else if (target.compareTo(this.wordArray.get(mid).getTarget()) > 0) {
//////                left = mid + 1;
//////            } else {
//////                right = mid - 1;
//////            }
//////        }
//        if (this.wordArray.containsKey(target)) {
//            return this.wordArray.get(target);
//        } else
//
//            return "Khong tim thay ket qua mong muon";
//    }

    public void dictionaryLookup() {
        System.out.println("Nhap tu ma ban muon tra nghia: ");
        String target = sc.nextLine();
        String value = this.wordArray.get(target);
//        System.out.println(this.wordBinarySearch(target));
        System.out.println(value);
    }

    public boolean dictionaryEdit() {
        boolean check = false;
        System.out.println("1. Xoa tu");
        System.out.println("2. Sua tu");
        System.out.println("Chon thao tac ma ban muon sua: ");
        int key = sc.nextInt();
        if (key == 1) {
            System.out.print("Nhap tu muon xoa: ");
            String delWord = sc.next();
//            for (int i = 0; i < this.wordArray.size(); i++) {
//                if (delWord.equals(this.wordArray.get(i).getTarget())) {
//                    this.wordArray.remove(i);
//                    break;
//                } else {
//                    System.out.println("Khong tim thay ket qua mong muon");
//                }
//            }
            if (wordArray.containsKey(delWord)) {
                this.wordArray.remove(delWord);
                check = true;
            } else
                check = false;
        } else if (key == 2) {
            System.out.print("Nhap tu muon sua: ");
            String editWord = sc.next();
//            for (int i = 0; i < this.wordArray.size(); i++) {
//                if (delWord.equals(this.wordArray.get(i).getTarget())) {
//                    System.out.print("Sua lai nghia cua tu: ");
//                    String exWord = sc.nextLine();
//                    this.wordArray.get(i).setExplain(exWord);
//                    break;
//                } else {
//                    System.out.println("Khong tim thay ket qua mong muon");
//                }
//            }
            if (this.wordArray.containsKey(editWord)) {
                Scanner cin = new Scanner(System.in);
                System.out.print("Sua lai nghia cua tu: ");
                String newMeaningWord = cin.nextLine();
                this.wordArray.put(editWord, newMeaningWord);
                check = true;
            } else
                check = false;
        }
        return check;
    }

        public void exportFile() {

        }

}
