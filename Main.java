package com.example.tp_camping;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private static Scene scene;

    public static void setScene(Scene scene) {
        Main.scene = scene;
    }

    public static Scene getScene() {
        return scene;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Camping des campagnes - Gestion des animations");

        // Création du layout principal avec BorderPane
        BorderPane rootLayout = new BorderPane();

        // Ajout du menu de navigation à gauche
        VBox menuBox = new VBox();
        menuBox.setSpacing(10);
        menuBox.setStyle("-fx-padding: 10;");

        // Création des boutons pour le menu de navigation
        Button btnGestionAnimateurs = new Button("Gérer les animateurs");
        Button btnGestionAnimations = new Button("Gérer les animations");
        Button btnPlanning = new Button("Voir le planning");

        // Ajout des boutons au menu
        menuBox.getChildren().addAll(btnGestionAnimateurs, btnGestionAnimations, btnPlanning);

        // Affichage initial (par défaut la gestion des animateurs)
        GestionAnimateurs gestionAnimateurs = new GestionAnimateurs();
        rootLayout.setCenter(gestionAnimateurs.getView());

        // Configuration des boutons de navigation
        btnGestionAnimateurs.setOnAction(e -> rootLayout.setCenter(gestionAnimateurs.getView()));
        btnGestionAnimations.setOnAction(e -> {
            GestionAnimations gestionAnimations = new GestionAnimations();
            rootLayout.setCenter(gestionAnimations.getView());
        });
        btnPlanning.setOnAction(e -> {
            Planning planning = new Planning();
            rootLayout.setCenter(planning.getView());
        });

        // Ajouter le menu de navigation dans la partie gauche
        rootLayout.setLeft(menuBox);

        // Création de la scène principale
        Scene scene = new Scene(rootLayout, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        // Lancer l'application JavaFX
        launch(args);
    }
}
