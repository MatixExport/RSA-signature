package com.example.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 400);
        stage.setTitle("RSA-signature");
        HelloController controller = (HelloController) fxmlLoader.getController();
        controller.setPanes(loadPanes(controller));
        controller.setScene(scene);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    private ArrayList<Pane> loadPanes(HelloController controller) {
        String[] paneNames = {"generate-signature-view.fxml", "verify-signature-view.fxml", "keys.fxml"};
        ArrayList<Pane> panes = new ArrayList<Pane>();
        for (String paneName : paneNames) {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(paneName));
            loader.setController(controller);
            try {
                panes.add(loader.load());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return panes;
    }
}