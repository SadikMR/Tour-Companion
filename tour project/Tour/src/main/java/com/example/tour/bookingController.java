package com.example.tour;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class bookingController implements Initializable {

    @FXML
    private DatePicker date;

    @FXML
    private ComboBox<String> placeBox;

    @FXML
    private TableView<Guide> guideTable;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize ComboBox items here.
        String[] items = {"Sylhet", "Bandarban", "Khagrachhari", "Cox's Bazar", "Sundarbans", "Rangamati"};
        placeBox.getItems().addAll(items);
        TableColumn<Guide, String> actionColumn = new TableColumn<>("Action");
        actionColumn.setPrefWidth(164.79998779296875);
        actionColumn.setCellValueFactory(new PropertyValueFactory<>("button"));
        guideTable.getColumns().add(actionColumn);
    }

    @FXML
    void dateAction(ActionEvent event) {
        // Handle date action here.
    }

    @FXML
    void placeBoxAction(ActionEvent event) {
        // Handle ComboBox action here.
    }

    @FXML
    void dashboard(ActionEvent event) {
        SceneSwitcher.switchScene((Node) event.getSource(), "UserDashboard.fxml", "dashboard", 1024, 650);
    }

    @FXML
    public void search(ActionEvent event) {
        String selectedPlace = placeBox.getValue();
        LocalDate selectedDate = date.getValue();
        guideTable.getItems().clear();

        ArrayList<Guide> guides = getGuidesFromDatabase(selectedPlace, selectedDate);

        guideTable.getItems().addAll(guides);
    }

    private ArrayList<Guide> getGuidesFromDatabase(String place, LocalDate date) {
        ArrayList<Guide> guides = new ArrayList<>();

        try (Connection connection = DatabaseConnector.getConnection()) {

            String query = "SELECT name, services, ratings FROM guideInfo WHERE place = ? ";
            System.out.println(place);

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, place);
                //preparedStatement.setDate(2, Date.valueOf(date));
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        String name = resultSet.getString("name");
                        int successfulServices = resultSet.getInt("services");
                        float ratings = resultSet.getFloat("ratings");
                        Guide guide = new Guide(name, successfulServices, ratings);
                        guides.add(guide);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return guides;
    }

}

