package Dictionary.GUI;

import Dictionary.Commandline.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class addWordWindow {
    public static Word addNewWord() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Add word");

        TextField targetField = new TextField();
        targetField.setPromptText("Enter English word");

        TextField explanationField = new TextField();
        explanationField.setPromptText("Enter Vietnamese translation");

        Button add = new Button("add");
        Word addedWord = new Word();
        add.setOnAction(e -> {
            String target = targetField.getText();
            String explain = explanationField.getText();
            addedWord.setTarget(target);
            addedWord.setExplain(explain);
            window.close();
        });

        VBox vbox = new VBox();
        vbox.getChildren().addAll(targetField, explanationField, add);
        vbox.setSpacing(10);
        vbox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vbox, 640, 480);    
        window.setScene(scene);
        window.showAndWait();
        return addedWord;
    }
}
