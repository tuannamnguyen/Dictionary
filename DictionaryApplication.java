package Dictionary;

import javafx.application.Application;
import javafx.collections.ObservableList;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import java.io.File;
import java.lang.annotation.Target;



public class DictionaryApplication extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();

        Dictionary dict = new Dictionary();
        dict.insertFromFile();

        ListView<String> targetWords = new ListView<>();
        for (int i = 0; i < dict.wordArray.size(); i++) {
            targetWords.getItems().add(dict.wordArray.get(i).getTarget());
        }
        targetWords.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));

        TextField searchBox = new TextField();
        searchBox.setPromptText("Search word");

        VBox explanationView = new VBox();

        Button translate = new Button();
        translate.setText("Translate");
        translate.setOnAction(e -> {
            ObservableList<String> targetWord = targetWords.getSelectionModel().getSelectedItems();

            for (String t : targetWord) {
                Label explanation = new Label();
                explanation.setText(dict.dictionaryLookupForGUI(t));
                explanationView.getChildren().addAll(explanation);
            }
        });
        
        /*File file = new File("src\\Dictionary\\speaker.png");
        String localPath = file.toURI().toURL().toString();
        Image speaker = new Image(localPath);*/
        Button pronunciation = new Button();
        pronunciation.setText("Pronunciation");

        hbox.getChildren().addAll(searchBox, translate, pronunciation);
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #336699;");

        root.setTop(hbox);
        root.setLeft(targetWords);
        root.setCenter(explanationView);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Dictionary Application");
        primaryStage.show();
    }
    
    public static void main(String[] args) throws Exception {
        launch();
    }
}