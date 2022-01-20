package at.vca.model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../view/homepage.fxml"));
        primaryStage.setTitle("VCA");
        primaryStage.setScene(new Scene(root, 630, 340));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
