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
}

