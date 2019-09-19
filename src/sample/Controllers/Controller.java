package sample.Controllers;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import sample.model.User;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class Controller {

    public static String welcomeName = null;

    @FXML
    private PasswordField passField;
    @FXML
    private Button authSignInButton;
    @FXML
    private Button buttonClear;
    @FXML
    private Button buttonBackspace;
    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button3;
    @FXML
    private Button button4;
    @FXML
    private Button button5;
    @FXML
    private Button button6;
    @FXML
    private Button button7;
    @FXML
    private Button button8;
    @FXML
    private Button button9;
    @FXML
    private Button button0;

    @FXML
    void initialize() {

        passField.setOnKeyTyped(Event::consume); // Prevents input from keyboard
        passField.setOnKeyPressed(Event::consume); // Prevents input from CTRL + V
        authSignInButton.setOnAction(event -> {
            String loginPassword = passField.getText();
            loginUser(loginPassword);
        });
        buttonClear.setOnAction(event -> {
            passField.clear();
        });
        buttonBackspace.setOnAction(event -> {
            passField.setText(passField.getText(0, passField.getText().length() - 1));
        });
        button0.setOnAction(event -> {
            numButtonClick(button0);
        });
        button1.setOnAction(event -> {
            numButtonClick(button1);
        });
        button2.setOnAction(event -> {
            numButtonClick(button2);
        });
        button3.setOnAction(event -> {
            numButtonClick(button3);
        });
        button4.setOnAction(event -> {
            numButtonClick(button4);
        });
        button5.setOnAction(event -> {
            numButtonClick(button5);
        });
        button6.setOnAction(event -> {
            numButtonClick(button6);
        });
        button7.setOnAction(event -> {
            numButtonClick(button7);
        });
        button8.setOnAction(event -> {
            numButtonClick(button8);
        });
        button9.setOnAction(event -> {
            numButtonClick(button9);
        });
    }

    private void numButtonClick(Button but) {
        if (passField.getText().length() < 4)
            passField.setText(passField.getText().concat(but.getText()).trim());
        else
            passField.setText(passField.getText());
    }

    private void credErrorMessageWindow(String message) {
        Label error = new Label(message);
        StackPane secondaryLayout = new StackPane();
        secondaryLayout.getChildren().add(error);
        Scene secondScene = new Scene(secondaryLayout, 500, 50);

        // New window (Stage)
        Stage newWindow = new Stage();
        newWindow.setTitle("CREDENTIALS ERROR!");
        newWindow.setScene(secondScene);
        newWindow.showAndWait();
    }

    private void loginUser(String password) {
        User[] allUsers = User.values();
        int successCount = 0;
        for (int i = 0; i < allUsers.length; i++) {
            if (allUsers[i].getPassword().equals(password)) {
                welcomeName = allUsers[i].getUserName();
                int timeoutValue = allUsers[i].getTimer();
                successCount++;
                authSignInButton.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/sample/resources/home.fxml"));

                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Home Page");
                stage.show();

                autoLogOut(stage, timeoutValue);

            }
        }

        if (password.length() < 4) {
            credErrorMessageWindow("Not enough digits in the password.");
        }
        if (successCount == 0 && password.length() == 4) {
            credErrorMessageWindow("No users found with the password you've entered.");
        }
    }

    private void autoLogOut(Stage stage, int timeoutValue) {
        Timer timer = new Timer();

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                timer.cancel();     // Terminate the timer thread

                System.out.println("Timer Expired!");
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        stage.getScene().getWindow().hide();
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("/sample/resources/login.fxml"));

                        try {
                            loader.load();
                        } catch (
                                IOException e) {
                            e.printStackTrace();
                        }

                        Parent root = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.setTitle("Login Page");
                        stage.show();
                    }
                });
            }
        };
        timer.schedule(timerTask, timeoutValue);
        System.out.println("Timer Started!");
    }
}