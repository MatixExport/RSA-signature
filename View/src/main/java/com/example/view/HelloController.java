package com.example.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import com.example.model.Base64CharsetAdapter;
import com.example.model.Data.RsaKeySet;
import com.example.model.Data.RsaPublicKey;
import com.example.model.RsaEncoder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.*;


public class HelloController {
    @FXML
    public TextField key_len;
    @FXML
    private Label welcomeText;
    @FXML
    private GridPane key_form_1,key_form_2,key_form_3;
    @FXML
    public TextField filename1,filename2,filename3;
    @FXML
    public TextField key0,key1,key2;


    @FXML
    private Pane main_pane;

    private final ArrayList<KeyWindow> keys = new ArrayList<KeyWindow>();
    private ArrayList<Pane> panes = new ArrayList<>();

    private Scene scene;

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void setPanes(ArrayList<Pane> panes) {
        this.panes = panes;
        loadKeys();
    }

    private void loadKeys(){
        keys.add(new KeyWindow(filename1,key0));
        keys.add(new KeyWindow(filename2,key1));
        keys.add(new KeyWindow(filename3,key2));
        System.out.println(keys);
    }

    public void switchPane (ActionEvent event) throws IOException {
        int paneIndex =  getUserDataValue(event);
        main_pane.getChildren().clear();
        main_pane.getChildren().add(panes.get(paneIndex));
    }


    int getUserDataValue(ActionEvent event){
        Node node = (Node) event.getSource() ;
        String data = (String) node.getUserData();
        return Integer.parseInt(data);
    }

    @FXML
    private void handle_load_key_button(ActionEvent event){
        KeyWindow keyWindow = keys.get(getUserDataValue(event));
        byte[] data = load_file(keyWindow.getFilename());
        String base64_key = Base64.getEncoder().encodeToString(data);
        keyWindow.getFiletext().setText(base64_key);
        keyWindow.setKey(base64_key);

    }

    @FXML
    private void handle_save_key_button(ActionEvent event){
        KeyWindow keyWindow = keys.get(getUserDataValue(event));
        save_file(Base64.getDecoder().decode(keyWindow.getFiletext().getText()));
    }

    @FXML
    private void generate_keys(){
        int key_length = Integer.parseInt(key_len.getText());
        RsaKeySet rsaKeySet1 = RsaEncoder.generateKeys(key_length);
        RsaPublicKey blindKey = RsaEncoder.generateKeys(key_length).getPublicKey();

        try {
            keys.get(0).getFiletext().setText(Base64CharsetAdapter.privateToBase64String(rsaKeySet1.getPrivateKey()));
            keys.get(1).getFiletext().setText(Base64CharsetAdapter.publicToBase64String(rsaKeySet1.getPublicKey()));
            keys.get(2).getFiletext().setText(Base64CharsetAdapter.publicToBase64String(blindKey));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    private byte[] load_file(TextField filepath) {
        File file = select_file_load_dialog("Select file", "");
        filepath.setText(String.valueOf(file.toURI()));
        byte[] data;
        try {
            data = Files.readAllBytes(Paths.get(file.toURI()));
        } catch (Exception e) {
            throw  new RuntimeException();
        }
        return data;
    }

    @FXML
    private void save_file(byte[] data) {
        File file = select_file_save_dialog("Save file", "");
        try {
            Files.createFile(file.toPath());
            Files.write(file.toPath(), data);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    private File select_file_load_dialog(String title, String filename) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(title);
        fileChooser.setInitialFileName(filename);
        return fileChooser.showOpenDialog(scene.getWindow());
    }

    private File select_file_save_dialog(String title, String filename) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(title);
        fileChooser.setInitialFileName(filename);
        return fileChooser.showSaveDialog(scene.getWindow());
    }

}