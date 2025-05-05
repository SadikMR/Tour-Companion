package com.example.tour;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class SceneSwitcher {

    public static void switchScene(Node eventSource, String resource, String title, int width, int height) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneSwitcher.class.getResource(resource));
            Parent root = loader.load();
            Stage stage = (Stage) eventSource.getScene().getWindow();

            stage.setScene(new Scene(root, width, height));
            stage.setTitle(title);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error in switching scenes.");
        }
    }

}
