package Dictionary;

import javafx.application.Application;
import javafx.stage.Stage;


public class DictionaryApplication extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Dictionary Application");

        
        primaryStage.show();
    }
    
    public static void main(String[] args) throws Exception {
        launch();
    }
}
