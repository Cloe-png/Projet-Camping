package com.example.tp_camping.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.application.Application;

import java.io.IOException;
import java.sql.SQLException;

import com.example.tp_camping.dao.UtilisateurDAO;
import com.example.tp_camping.model.Utilisateur;

public class LoginController extends Application {

    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tp_camping/login-view.fxml")); // Chemin corrigé
        VBox vbox = loader.load();
        primaryStage.setTitle("Connexion - Gestion du Camping des Campagnes");
        primaryStage.setScene(new Scene(vbox, 800, 600));
        primaryStage.show();

        vbox.setStyle("-fx-font-family: 'Arial';");
    }

    @FXML
    private void handleLogin() {
        String email = emailField.getText();
        String motDePasse = passwordField.getText();
        UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
        try {
            Utilisateur utilisateur = utilisateurDAO.authentifier(email, motDePasse);
            if (utilisateur != null) {
                System.out.println("Connexion réussie !");
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Email ou mot de passe incorrect.\nVeuillez vérifier votre saisie et réessayer.");
                alert.showAndWait();
                alert.getDialogPane().setStyle("-fx-font-family: 'Arial';");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Erreur lors de la connexion.\nVeuillez réessayer ultérieurement");
            alert.showAndWait();
            alert.getDialogPane().setStyle("-fx-font-family: 'Arial';");
        }
    }

    @FXML
    private void handleRegister() {
        String email = emailField.getText();
        String motDePasse = passwordField.getText();
        UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
        Utilisateur nouvelUtilisateur = new Utilisateur(0, "NewUser1", email, motDePasse);
        try {
            utilisateurDAO.ajouterUtilisateur(nouvelUtilisateur);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Compte créé avec succès !");
            alert.showAndWait();
            alert.getDialogPane().setStyle("-fx-font-family: 'Arial';");
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Erreur lors de la connexion.\nVeuillez réessayer ultérieurement");
            alert.showAndWait();
            alert.getDialogPane().setStyle("-fx-font-family: 'Arial';");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
