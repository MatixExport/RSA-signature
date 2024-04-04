package com.example.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Objects;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private Pane main_pane;


    public void switchPane (ActionEvent event) throws IOException {
        String fxml_name  = (String) ((Button)event.getSource()).getUserData();
        Pane newLoadedPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxml_name)));
        main_pane.getChildren().clear();
        main_pane.getChildren().add(newLoadedPane);
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}