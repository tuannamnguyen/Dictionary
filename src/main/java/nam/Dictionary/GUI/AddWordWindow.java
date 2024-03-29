package nam.Dictionary.GUI;

import java.util.Collections;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import nam.Dictionary.Word;

public class AddWordWindow {

    /**
     * add word to application.
     * 
     * @param dict               dictionary.
     * @param observableWordList data of listview.
     */
    public static void addNewWord(DictionaryManagementGUI dict, ObservableList<String> observableWordList) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Add word");

        TextField targetField = new TextField();
        targetField.setPromptText("Enter English word");

        TextField pronunciationField = new TextField();
        pronunciationField.setPromptText("Enter pronunciation");

        TextField explanationField = new TextField();
        explanationField.setPromptText("Enter Vietnamese translation");

        Button add = new Button("add");

        add.setOnAction(e -> {
            String target = targetField.getText();
            String pronunciation = pronunciationField.getText();
            String explain = explanationField.getText();

            if (dict.wordBinarySearch(target) != -1) {
                Stage AlertBox = new Stage();
                AlertBox.initModality(Modality.APPLICATION_MODAL);
                AlertBox.setTitle("Warning");

                Label alert = new Label();
                alert.setText("Word already exists!");

                Button close = new Button("OK");
                close.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent arg0) {
                        AlertBox.close();
                    }
                });

                VBox vboxAdd = new VBox();
                vboxAdd.getChildren().addAll(alert, close);
                vboxAdd.setSpacing(10);
                vboxAdd.setAlignment(Pos.CENTER);

                Scene alertScene = new Scene(vboxAdd, 320, 240);

                AlertBox.setScene(alertScene);
                AlertBox.showAndWait();
            } else {
                dict.getWordArray().add(new Word(target, "/" + pronunciation + "/\n" + explain));
                Collections.sort(dict.getWordArray());
                dict.exportToFile();
                observableWordList.add(target);
                Collections.sort(observableWordList);

                Stage AlertBox = new Stage();
                AlertBox.initModality(Modality.APPLICATION_MODAL);
                AlertBox.setTitle("Warning");

                Label alert = new Label();
                alert.setText("Word added successfully!");

                Button close = new Button("OK");
                close.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent arg0) {
                        AlertBox.close();
                    }
                });

                VBox vboxAdd = new VBox();
                vboxAdd.getChildren().addAll(alert, close);
                vboxAdd.setSpacing(10);
                vboxAdd.setAlignment(Pos.CENTER);

                Scene alertScene = new Scene(vboxAdd, 320, 240);

                AlertBox.setScene(alertScene);
                AlertBox.showAndWait();

                window.close();
            }
        });

        VBox vbox = new VBox();
        vbox.getChildren().addAll(targetField, pronunciationField, explanationField, add);
        vbox.setSpacing(10);
        vbox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vbox, 640, 480);
        window.setScene(scene);
        window.showAndWait();
    }
}
