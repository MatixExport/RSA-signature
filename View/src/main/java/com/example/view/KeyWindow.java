package com.example.view;

import javafx.scene.control.TextField;

public class KeyWindow {

    private TextField filename;
    private TextField filetext;
    private byte[] data;

    String key;

    public KeyWindow(TextField filename, TextField filetext) {
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

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
