package com.example.tp_camping.controller;

import com.example.tp_camping.dao.UtilisateurDAO;
import com.example.tp_camping.model.Utilisateur;
import com.example.tp_camping.util.ValidationUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UtilisateurController implements Initializable {

    @FXML private TextField nomField;
    @FXML private TextField emailField;
    @FXML private TextField motDePasseField;
    @FXML private TableView<Utilisateur> utilisateursTable;
    @FXML private TableColumn<Utilisateur,String> nomColumn;
    @FXML private TableColumn<Utilisateur, String> emailColumn;
    @FXML private Button ajouterBtn;
    @FXML private Button modifierBtn;
    @FXML private Button supprimerBtn;

    private UtilisateurDAO utilisateurDAO;
    private ObservableList<Utilisateur> utilisateurs;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        utilisateurDAO = new UtilisateurDAO();
        utilisateurs = FXCollections.observableArrayList();

        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        utilisateursTable.setItems(utilisateurs);
        chargerUtilisateurs();

        ajouterBtn.setOnAction(event -> ajouterUtilisateur());
        modifierBtn.setOnAction(event -> modifierUtilisateur());
        supprimerBtn.setOnAction(event -> supprimerUtilisateur());
    }

    private void chargerUtilisateurs() {
        try {
            utilisateurs.clear();
            utilisateurs.addAll(utilisateurDAO.getAllUtilisateurs());
        } catch (SQLException e) {
            afficherErreur("Erreur lors du chargement des utilisateurs", e);
        }
    }

    private void ajouterUtilisateur() {
        String nom = nomField.getText();
        String email = emailField.getText();
        String motDePasse = motDePasseField.getText();

        if (!ValidationUtil.isValidUsername(nom)) {
            afficherErreur("Erreur", new Exception("Le nom doit contenir uniquement des lettres."));
            return;
        }
        if (!ValidationUtil.isValidEmail(email)) {
            afficherErreur("Erreur", new Exception("L'email n'est pas valide."));
            return;
        }
        if (!ValidationUtil.isValidPassword(motDePasse)) {
            afficherErreur("Erreur", new Exception("Le mot de passe doit contenir au moins 8 caractères, une lettre et un chiffre."));
            return;
        }

        Utilisateur utilisateur = new Utilisateur(0, nom, email, motDePasse);
        try {
            utilisateurDAO.ajouterUtilisateur(utilisateur);
            utilisateursTable.refresh();
            chargerUtilisateurs();
            viderChamps();
            afficherNotification("Succès", "Utilisateur ajouté avec succès !");
        } catch (SQLException e) {
            afficherErreur("Erreur lors de l'ajout de l'utilisateur", e);
        }
    }

    private void modifierUtilisateur() {
        Utilisateur utilisateurSelectionne = utilisateursTable.getSelectionModel().getSelectedItem();
        if (utilisateurSelectionne != null) {
            utilisateurSelectionne.setNom(nomField.getText());
            utilisateurSelectionne.setEmail(emailField.getText());
            utilisateurSelectionne.setMotDePasse(motDePasseField.getText());

            try {
                utilisateurDAO.modifierUtilisateur(utilisateurSelectionne);
                chargerUtilisateurs();
            } catch (SQLException e) {
                afficherErreur("Erreur lors de la modification de l'utilisateur", e);
            }
        }
    }

    private void supprimerUtilisateur() {
        Utilisateur utilisateurSelectionne = utilisateursTable.getSelectionModel().getSelectedItem();
        if (utilisateurSelectionne != null) {
            try {
                utilisateurDAO.supprimerUtilisateur(utilisateurSelectionne.getIdUtilisateur());
                chargerUtilisateurs();
                viderChamps();
            } catch (SQLException e) {
                afficherErreur("Erreur lors de la suppression de l'utilisateur", e);
            }
        }
    }

    private void afficherErreur(String titre, Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(e.getMessage());
        alert.getDialogPane().setStyle("-fx-font-family: 'Arial';");
        alert.showAndWait();
    }

    private void viderChamps() {
        nomField.clear();
        emailField.clear();
        motDePasseField.clear();
    }

    private void afficherNotification(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.getDialogPane().setStyle("-fx-font-family: 'Arial';");
        alert.showAndWait();
    }
}
