package com.example.tour;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;

public class UserDashboardController {

    @FXML
    private Button BookGuide;

    @FXML
    void bookGuide(ActionEvent event) {
        BookGuide.setDisable(true);
        SceneSwitcher.switchScene((Node) event.getSource(), "booking.fxml", "booking", 1024, 650);
    }

    @FXML
    void bookingHistory(ActionEvent event) {
        SceneSwitcher.switchScene((Node) event.getSource(), "bookingHistory.fxml", "login", 1024, 650);
    }

    @FXML
    void logout(ActionEvent event) {
        SceneSwitcher.switchScene((Node) event.getSource(), "login.fxml", "login", 1024, 650);
    }

    @FXML
    void profile(ActionEvent event) {
        SceneSwitcher.switchScene((Node) event.getSource(), "profile.fxml", "profile", 1024, 650);
    }

    @FXML
    void settings(ActionEvent event) {

    }

}
