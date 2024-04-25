package com.example.view;

import com.example.model.Data.RsaPrivateKey;
import com.example.model.RsaSignature;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import com.example.model.Base64CharsetAdapter;
import com.example.model.Data.RsaKeySet;
import com.example.model.Data.RsaPublicKey;
import com.example.model.RsaEncoder;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.*;

import static com.example.view.FileHandler.load_file;
import static com.example.view.Util.areInputWindowsLoaded;
import static com.example.view.Util.getUserDataValue;

//region fxml controls
public class HelloController {
    @FXML
    public TextField key_len;

    @FXML
    public Label verify_status_label;
    @FXML
    public TextField filename1,filename2,filename3,file_filename,file_filename2,sign_filename,verify_filename;
    @FXML
    public TextArea key0,key1,key2,file1,file2,sign1,sign2;

    @FXML
    private Pane main_pane;
//endregion
    private final ArrayList<KeyInputWindow> keys = new ArrayList<KeyInputWindow>();

    private InputWindow file,verify_file,verify_sign,generate_sign;

    private ArrayList<Pane> panes = new ArrayList<>();

    private Scene scene;

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void setPanes(ArrayList<Pane> panes) {
        this.panes = panes;
        loadInputWindows();
    }
    private void loadInputWindows(){
        file = new InputWindow(file_filename,file1);
        generate_sign = new InputWindow(sign_filename,sign1);
        verify_sign = new InputWindow(verify_filename,sign2);
        verify_file = new InputWindow(file_filename2,file2);
        loadKeys();
    }
    private void loadKeys(){
        keys.add(new KeyInputWindow(filename1,key0));
        keys.add(new KeyInputWindow(filename2,key1));
        keys.add(new KeyInputWindow(filename3,key2));
    }

    public void switchPane (ActionEvent event) throws IOException {
        int paneIndex =  getUserDataValue(event);
        main_pane.getChildren().clear();
        main_pane.getChildren().add(panes.get(paneIndex));
    }




    private void save_input_window(InputWindow window){
        FileHandler.save_file(Base64.getDecoder().decode(window.getFiletext().getText()),scene);
    }
    private void load_input_window(InputWindow window){
        byte[] data = load_file(window.getFilename(),scene);
        String base64_key = Base64.getEncoder().encodeToString(data);
        window.getFiletext().setText(base64_key);
    }

    @FXML
    private void handle_load_key_button(ActionEvent event){
        InputWindow inputWindow = keys.get(getUserDataValue(event));
        load_input_window(inputWindow);
    }

    @FXML
    private void handle_save_key_button(ActionEvent event){
        InputWindow inputWindow = keys.get(getUserDataValue(event));
        save_input_window(inputWindow);
    }

    @FXML
    private void handle_load_file_button(ActionEvent event){
        load_input_window(file);
    }
    @FXML
    private void handle_load_verify_file_button(ActionEvent event){
        load_input_window(verify_file);
    }
    @FXML
    private void handle_load_sign_button(ActionEvent event){
       load_input_window(verify_sign);
    }
    @FXML
    private void handle_save_sign_button(ActionEvent event){
        save_input_window(generate_sign);
    }
    @FXML
    private void handle_sign_button(){
        //check if data/keys are loaded
        if(!areInputWindowsLoaded(new InputWindow[]{keys.get(0), keys.get(1), file, generate_sign})){
            new GuiException("Not all required data is loaded");
            return;
        }
        byte[] data = Base64.getDecoder().decode(file.getFiletext().getText());
        byte[] signature = RsaSignature.getBlindSignature(data,keys.get(2).getPublicKey(),keys.get(1).getPublicKey());
        try{
            generate_sign.getFiletext().setText(Base64.getEncoder().encodeToString(signature));
        }
        catch (Exception e){
            new GuiException(e.getMessage());
        }
    }

    @FXML
    private void handle_verify_button() throws NoSuchAlgorithmException, InvalidKeySpecException {
        if(!areInputWindowsLoaded(new InputWindow[]{verify_file,verify_sign,keys.get(2),keys.get(0)})){
            new GuiException("Not all required data is loaded");
            return;
        }
        byte[] filedata = verify_file.getData();
        byte[] sign_data = verify_sign.getData();
        RsaPublicKey blindKey = keys.get(2).getPublicKey();
        RsaPrivateKey readKey = keys.get(0).getPrivateKey();
        verify_status_label.setText("Status: Loading");
        try {
            if (RsaSignature.isBlindSignatureValid(filedata,sign_data,blindKey,readKey)){
                verify_status_label.setText("Status: Valid");
                return;
            }
            verify_status_label.setText("Status: Invalid");
        }
        catch (Exception e){
            new GuiException(e.getMessage());
        }

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



}