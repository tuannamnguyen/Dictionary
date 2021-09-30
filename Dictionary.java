import java.util.ArrayList;
import java.util.Scanner;

class Dictionary {
    ArrayList<Word> wordArray = new ArrayList<>();

    public void insertFromCommandline() {
        System.out.println("Nhap vao so luong tu muon nhap: ");
        
        Scanner sc = new Scanner(System.in);
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

        sc.close();
    }

    public void showAllWords() {
        System.out.println("Danh sach tat ca cac tu dang co trong tu dien: ");
        System.out.println("No  | English           | Vietnamese");

        for (int i = 0; i < this.wordArray.size(); i++) {
            System.out.println((i + 1) + "  | " + this.wordArray.get(i).getTarget() + "           | " + this.wordArray.get(i).getExplain());
        }
    }

    public void insertFromFile() {
        try {
            File x = new File("C:\\Data\\dictionaries.txt");
            Scanner sc = new Scanner(x);
            String content = "";
            while (sc.hasNextLine()) {
                content += sc.nextLine() + "\r\n";
            }
            System.out.println(content);
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error");
        }
    }

    public void dictionaryLookup() {
        try {
            File x = new File("C:\\Data\\dictionaries.txt");
            Scanner sc = new Scanner(x);
            Scanner scanner = new Scanner(System.in);
            String[] words = new String[100];
            int i = 0;
            System.out.print("Nhap tu ban muon tim kiem: ");
            String s = scanner.next();
            while (sc.hasNextLine()) {
                String content = sc.nextLine();
                words[i] = content;
                i++;
            }
            for(String word : words){
                if(word == null){
                    break;
                }
                if(word.startsWith(s)){
                    System.out.println(word);
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error");
        }
    }
}

