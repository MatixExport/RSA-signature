package com.example.view;

import javafx.event.ActionEvent;
import javafx.scene.Node;

public class Util {
    public static int getUserDataValue(ActionEvent event){
        Node node = (Node) event.getSource() ;
        String data = (String) node.getUserData();
        return Integer.parseInt(data);
    }

    public static boolean isInputWindowLoaded(InputWindow window){
        return !window.getFiletext().getText().isEmpty();
    }
    public static boolean areInputWindowsLoaded(InputWindow[] inputWindows){
        for (InputWindow window: inputWindows ) {
            if(!isInputWindowLoaded(window)){
                return false;
            }
        }
        return true;
    }
}
