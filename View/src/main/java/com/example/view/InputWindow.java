package com.example.view;

import javafx.scene.control.TextField;

import java.util.Base64;

public class InputWindow {

    private TextField filename;
    private TextField filetext;


    public InputWindow(TextField filename, TextField filetext) {
        this.filename = filename;
        this.filetext = filetext;
    }

    public TextField getFilename() {
        return filename;
    }

    public void setFilename(TextField filename) {
        this.filename = filename;
    }

    public TextField getFiletext() {
        return filetext;
    }

    public void setFiletext(TextField filetext) {
        this.filetext = filetext;
    }

    public byte[] getData(){
        return Base64.getDecoder().decode(getFiletext().getText());
    }


}
