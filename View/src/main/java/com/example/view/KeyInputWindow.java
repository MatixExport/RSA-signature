package com.example.view;

import com.example.model.Base64CharsetAdapter;
import com.example.model.Data.RsaPrivateKey;
import com.example.model.Data.RsaPublicKey;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class KeyInputWindow extends InputWindow{

    public KeyInputWindow(TextField filename, TextArea filetext) {
        super(filename, filetext);
    }

    public RsaPublicKey getPublicKey() {
        try {
            return Base64CharsetAdapter.publicFromBase64String(getFiletext().getText());
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }
    public RsaPrivateKey getPrivateKey(){
        try {
            return Base64CharsetAdapter.privateFromBase64String(getFiletext().getText());
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }
}
