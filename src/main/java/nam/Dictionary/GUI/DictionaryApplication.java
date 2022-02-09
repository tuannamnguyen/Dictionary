package nam.Dictionary.GUI;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import nam.Dictionary.Word;

public class DictionaryApplication extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        DictionaryManagementGUI dict = new DictionaryManagementGUI();
        dict.insertFromFile();
        

        ArrayList<String> wordList = new ArrayList<>();
        ObservableList<String> observableWordList = FXCollections.observableList(wordList);
        for (Word w : dict.getWordArray()) {
            wordList.add(w.getTarget());
        }
        
        ListView<String> left = leftSection(observableWordList);
        HBox top = topSection(dict, observableWordList, left);
        ScrollPane center = centerSection(left, dict);
        
        BorderPane root = new BorderPane();
        root.setTop(top);
        root.setLeft(left);
        root.setCenter(center);

        // Main window
        Scene scene1 = new Scene(root, 800, 600);
        primaryStage.setScene(scene1);
        primaryStage.setTitle("Dictionary Application");
        primaryStage.show();
    }

    /**
     * top section that contains functional buttons.
     * @param dict dictionary.
     * @param observableWordList data of listview.
     * @param lView listview.
     * @return top bar of dictionary app.
     */
    public HBox topSection(DictionaryManagementGUI dict, ObservableList<String> observableWordList, ListView<String> lView) {
        TextField searchBox = new TextField();
        searchBox.setPromptText("Search word");

        List<String> copy = new ArrayList<>();

        for (Word w : dict.getWordArray()) {
            copy.add(w.getTarget());
        }

        Trie trie = new Trie(copy);
        searchBox.textProperty().addListener((ov, oldV, newV) -> {
            System.out.println(newV.trim().isBlank());

            if (newV.trim().isBlank()) {
                observableWordList.clear();
                observableWordList.addAll(copy);
            } else {
                observableWordList.clear();
                observableWordList.addAll(trie.suggest(newV.trim()));
            }
        });

        Button addWord = new Button("Add new word");
        addWord.setOnAction(e -> AddWordWindow.addNewWord(dict, observableWordList));

        Button deleteWord = new Button("Delete word");
        deleteWord.setOnAction(e -> DeleteWordWindow.deleteWord(dict, observableWordList));

        Button pronunciation = new Button("Pronunciation");
        pronunciation.setOnAction(e -> {
            String current = lView.getSelectionModel().getSelectedItem();
            TextToSpeech tts = new TextToSpeech(current);
            tts.SpeakText(current);
        });

        HBox topBar = new HBox();
        topBar.setPadding(new Insets(15, 12, 15, 12));
        topBar.getChildren().addAll(searchBox, pronunciation, addWord, deleteWord);
        topBar.setSpacing(10);
        topBar.setStyle("-fx-background-color: #336699;");

        return topBar;
    }

    /**
     * left section: listview that shows all words in dictionary.
     * @param observableWordList data for listview.
     * @return listview.
     */
    public ListView<String> leftSection(ObservableList<String> observableWordList) {
        ListView<String> targetWords = new ListView<>();
        targetWords.setItems(observableWordList);

        targetWords.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        return targetWords;
    }

    /**
     * center section: shows translation.
     * @param targetWords listview of application (list of english words).
     * @param dict 
     * @return center section of application.
     */
    public ScrollPane centerSection(ListView<String> targetWords, DictionaryManagementGUI dict) {
        Label explain = new Label();

        targetWords.getSelectionModel().selectedItemProperty()
                .addListener((ObservableValue<? extends String> ov, String old_val, String new_val) -> {
                    explain.setText(dict.dictionaryLookup(new_val));
                });

        ScrollPane explanationView = new ScrollPane();
        explanationView.setContent(explain);

        explanationView.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        explanationView.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);

        return explanationView;
    }
}