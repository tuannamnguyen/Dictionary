package Dictionary.GUI;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class DeleteWordWindow {
    public static void deleteWord(DictionaryManagementGUI dict, ObservableList<String> observableWordList) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Delete word");

        TextField deleteField = new TextField();
        deleteField.setPromptText("Word to delete");

        Button delete = new Button("Delete word");
        delete.setOnAction(e -> {
            String target = deleteField.getText();

            if (dict.wordBinarySearch(target) == -1) {
                Stage AlertBox = new Stage();
                AlertBox.initModality(Modality.APPLICATION_MODAL);
                AlertBox.setTitle("Warning");

                Label alert = new Label();
                alert.setText("No such word in dictionary");

                Button close = new Button("OK");
                close.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent arg0) {
                        AlertBox.close();
                    }
                });

                VBox vboxDelete = new VBox();
                vboxDelete.getChildren().addAll(alert, close);
                vboxDelete.setSpacing(10);
                vboxDelete.setAlignment(Pos.CENTER);

                Scene deleteScene = new Scene(vboxDelete, 320, 240);

                AlertBox.setScene(deleteScene);
                AlertBox.showAndWait();
            } else {
                dict.getWordArray().remove(dict.wordBinarySearch(target));
                observableWordList.remove(target);

                Stage AlertBox = new Stage();
                AlertBox.initModality(Modality.APPLICATION_MODAL);

                Label alert = new Label();
                alert.setText("Word deleted successfully!");

                Button close = new Button("OK");
                close.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent arg0) {
                        dict.exportToFile();
                        AlertBox.close();
                    }
                });

                VBox vboxDelete = new VBox();
                vboxDelete.getChildren().addAll(alert, close);
                vboxDelete.setSpacing(10);
                vboxDelete.setAlignment(Pos.CENTER);

                Scene deleteScene = new Scene(vboxDelete, 320, 240);

                AlertBox.setScene(deleteScene);
                AlertBox.showAndWait();
            }
        });

        VBox vbox = new VBox();
        vbox.getChildren().addAll(deleteField, delete);
        vbox.setSpacing(10);
        vbox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vbox, 640, 480);
        window.setScene(scene);
        window.showAndWait();
    }
}
