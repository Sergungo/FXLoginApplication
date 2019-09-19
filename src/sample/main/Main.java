package sample.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/sample/resources/login.fxml"));
        primaryStage.setTitle("FXLoginApplication");
        Scene scene = new Scene(root, 1400, 960);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Login Page");
        primaryStage.getIcons().add(new Image("/sample/resources/favicon.png"));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}