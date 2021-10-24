package Dictionary.GUI;

import java.util.ArrayList;

import Dictionary.Commandline.*;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DictionaryApplication extends Application {
    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Dictionary dict = new Dictionary();
        dict.insertFromFileUpdated();
        
        ArrayList<String> wordList = new ArrayList<>();
        ObservableList<String> observableWordList = FXCollections.observableList(wordList);
        for (Word w : dict.getWordArray()) {
            wordList.add(w.getTarget());
        }

        HBox top = topSection(dict, observableWordList);
        ListView<String> left = leftSection(observableWordList);
        VBox center = centerSection(left, dict);
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

    public HBox topSection(Dictionary dict, ObservableList<String> observableWordList) {
        TextField searchBox = new TextField();
        searchBox.setPromptText("Search word");

        Button addWord = new Button("Add new word");
        addWord.setOnAction(e -> addWordWindow.addNewWord(dict, observableWordList));

        Button deleteWord = new Button("Delete word");
        deleteWord.setOnAction(e -> deleteWordWindow.deleteWord(dict, observableWordList));

        Button pronunciation = new Button("Pronunciation");

        HBox topBar = new HBox();
        topBar.setPadding(new Insets(15, 12, 15, 12));
        topBar.getChildren().addAll(searchBox, pronunciation, addWord, deleteWord);
        topBar.setSpacing(10);
        topBar.setStyle("-fx-background-color: #336699;");

        return topBar;
    }

    public ListView<String> leftSection(ObservableList<String> observableWordList) {
        ListView<String> targetWords = new ListView<>();
        targetWords.setItems(observableWordList);

        targetWords.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        return targetWords;
    }

    public VBox centerSection(ListView<String> targetWords, Dictionary dict) {
        Label explain = new Label();

        targetWords.getSelectionModel().selectedItemProperty()
                .addListener((ObservableValue<? extends String> ov, String old_val, String new_val) -> {
                    explain.setText(dict.dictionaryLookupForGUI(new_val));
                });
        VBox explanationView = new VBox();
        explanationView.setAlignment(Pos.CENTER);
        explanationView.getChildren().addAll(explain);
        return explanationView;
    }
}