package Dictionary;

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

public class DictionaryApplication extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {
        Dictionary dict = new Dictionary();
        dict.insertFromFile();

        ListView<String> targetWords = new ListView<>();
        for (int i = 0; i < dict.wordArray.size(); i++) {
            targetWords.getItems().add(dict.wordArray.get(i).getTarget());
        }
        targetWords.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        Label explain = new Label();
        targetWords.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> ov, String old_val, String new_val) -> {
            explain.setText(dict.dictionaryLookupForGUI(new_val));
        });
        VBox explanationView = new VBox();
        explanationView.setAlignment(Pos.CENTER);
        explanationView.getChildren().addAll(explain);

        TextField searchBox = new TextField();
        searchBox.setPromptText("Search word");

        Button addWord = new Button("Add new word");

        Button deleteWord = new Button("Delete word");

        Button pronunciation = new Button("Pronunciation");

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.getChildren().addAll(searchBox, pronunciation, addWord, deleteWord);
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #336699;");

        BorderPane root = new BorderPane();
        root.setTop(hbox);
        root.setLeft(targetWords);
        root.setCenter(explanationView);


        // Main window
        Scene scene1 = new Scene(root, 800, 600);
        primaryStage.setScene(scene1);
        primaryStage.setTitle("Dictionary Application");
        primaryStage.show();
    }
    public static void main(String[] args) throws Exception {
        launch(args);
    }
}