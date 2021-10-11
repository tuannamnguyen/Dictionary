package Dictionary;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;

public class DictionaryApplication extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));

        TextField searchBox = new TextField();
        searchBox.setPromptText("Type in the word you want to look up");

        hbox.getChildren().addAll(searchBox);
        hbox.setStyle("-fx-background-color: #336699;");

        Dictionary dict = new Dictionary();
        dict.insertFromFile();
        ListView<String> targetWords = new ListView<>();
        for (int i = 0; i < dict.wordArray.size(); i++) {
            targetWords.getItems().add(dict.wordArray.get(i).getTarget());
        }
        targetWords.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);


        root.setTop(hbox);
        root.setLeft(targetWords);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Dictionary Application");
        primaryStage.show();
    }
    
    public static void main(String[] args) throws Exception {
        launch();
    }
}
