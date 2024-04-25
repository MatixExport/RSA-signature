package com.example.view;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.Base64;

public class InputWindow {

    private TextField filename;
    private TextArea filetext;


    public InputWindow(TextField filename, TextArea filetext) {
        this.filename = filename;
        this.filetext = filetext;
    }

    public TextField getFilename() {
        return filename;
    }

    public void setFilename(TextField filename) {
        this.filename = filename;
    }

    public TextArea getFiletext() {
        return filetext;
    }

    public void setFiletext(TextArea filetext) {
        this.filetext = filetext;
    }

    public byte[] getData(){
        return Base64.getDecoder().decode(getFiletext().getText());
    }


}
