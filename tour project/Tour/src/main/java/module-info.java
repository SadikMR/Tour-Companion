module com.example.tour {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.tour to javafx.fxml;
    exports com.example.tour;
}