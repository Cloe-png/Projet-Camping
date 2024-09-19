package com.example.tp_camping;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class GestionAnimations {

    public VBox getView() {
        VBox view = new VBox();
        view.getChildren().add(new Text("Gestion des animations"));
        return view;
    }
}
