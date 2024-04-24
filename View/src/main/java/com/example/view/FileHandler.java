package com.example.view;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileHandler {

    public static byte[] load_file(TextField filepath, Scene scene) {
        File file = select_file_load_dialog("Select file", "",scene);
        filepath.setText(String.valueOf(file.toURI()));
        byte[] data;
        try {
            data = Files.readAllBytes(Paths.get(file.toURI()));
        } catch (Exception e) {
            throw  new RuntimeException();
        }
        return data;
    }


    public static void save_file(byte[] data,Scene scene) {
        File file = select_file_save_dialog("Save file", "",scene);
        try {
            Files.createFile(file.toPath());
            Files.write(file.toPath(), data);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    private static File select_file_load_dialog(String title, String filename,Scene scene) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(title);
        fileChooser.setInitialFileName(filename);
        return fileChooser.showOpenDialog(scene.getWindow());
    }

    private static File select_file_save_dialog(String title, String filename,Scene scene) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(title);
        fileChooser.setInitialFileName(filename);
        return fileChooser.showSaveDialog(scene.getWindow());
    }
}
