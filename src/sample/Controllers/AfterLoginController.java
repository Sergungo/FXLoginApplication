package sample.Controllers;

import com.sun.org.apache.bcel.internal.generic.NEW;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;


public class AfterLoginController {

    @FXML
    private Text welcomeText;

    @FXML
    private Button authSignOutButton;

    @FXML
    void initialize() {

        welcomeText.setText(welcomeText.getText().concat(Controller.welcomeName));

        authSignOutButton.setOnAction(event -> {

            authSignOutButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/resources/login.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Login Page");
            stage.show();
        });
    }
}
