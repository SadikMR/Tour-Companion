package com.example.tour;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {

    @FXML
    private Hyperlink Create;

    @FXML
    private Button Login;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField email;

    @FXML
    private TextField fullName;

    @FXML
    private Label loginActionMessage;

    private Stage stage;
    private Scene scene;

    @FXML
    void signup(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String mail = email.getText();
        String name = fullName.getText();

        if (insertUser(username, password, mail, name)) {

            //DashboardController dashboardController = loader.getController();
            //dashboardController.setWelcomeMessage("Hey, " + username+"!");
            SceneSwitcher.switchScene((Node) event.getSource(), "UserDashboard.fxml", "dashboard", 1024, 650);


        } else {
            //loginMessage.setText("Enter valid UserName and Password");
        }
    }
    private boolean insertUser(String username, String password, String Email, String fullname) {
        String sql = "INSERT INTO userInfo (username, password, email, fullname) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, Email);
            preparedStatement.setString(4, fullname);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @FXML
    void back(ActionEvent event) {
        SceneSwitcher.switchScene((Node) event.getSource(), "login.fxml", "login", 1024, 650);
    }


    @FXML
    void create(ActionEvent event) {
        SceneSwitcher.switchScene((Node) event.getSource(), "signup.fxml", "sign up", 1024, 650);
    }

    @FXML
    void login(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        Login.setDisable(true); // Disable the button
        loginActionMessage.setText("Please Wait!...");

        Platform.runLater(() -> {
            boolean flag = validateCredentials(username, password);

            if (!flag) {
                infoBox("Please enter correct Email and Password", null, "Failed");
            } else {
                SceneSwitcher.switchScene((Node) event.getSource(), "UserDashboard.fxml", "dashboard", 1024, 650);
            }

            // Re-enable the button
            Login.setDisable(false);
            loginActionMessage.setText("");
        });
    }

    private boolean validateCredentials(String username, String password) {
        Connection connection = DatabaseConnector.getConnection();
        final String SELECT_QUERY = "SELECT * FROM userInfo WHERE username = ? and password = ?";

        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY)) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }
    public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }


}
