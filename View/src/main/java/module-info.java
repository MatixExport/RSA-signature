module com.example.view {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.example.model;


    opens com.example.view to javafx.fxml;
    exports com.example.view;

}