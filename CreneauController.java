package com.example.tp_camping.controller;

import com.example.tp_camping.dao.CreneauDAO;
import com.example.tp_camping.model.Creneau;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;
import java.util.Date;

public class CreneauController {
    @FXML private TextField heureDebutField;
    @FXML private TextField heureFinField;
    @FXML private DatePicker dateField;
    @FXML private TextField nombrePlaceField;
    @FXML private Button ajouterBtn;

    private CreneauDAO creneauDAO;

    @FXML
    public void initialize() {
        creneauDAO = new CreneauDAO();
        ajouterBtn.setOnAction(event -> ajouterCreneau());
    }

    private void ajouterCreneau() {
        String heureDebut = heureDebutField.getText();
        String heureFin = heureFinField.getText();
        Date date = java.sql.Date.valueOf(dateField.getValue());
        int nombrePlace = Integer.parseInt(nombrePlaceField.getText());

        Creneau creneau = new Creneau(0, heureDebut, heureFin, date, nombrePlace, 0, 0);

        try {
            creneauDAO.ajouterCreneau(creneau);
            afficherNotification("Succès", "Créneau ajouté avec succès");
        } catch (SQLException e) {
            afficherErreur("Erreur lors de l'ajout du créneau", e);
        }
    }

    private void afficherNotification(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.getDialogPane().setStyle("-fx-font-family: 'Arial';");
        alert.showAndWait();
    }

    private void afficherErreur(String titre, Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(e.getMessage());
        alert.getDialogPane().setStyle("-fx-font-family: 'Arial';");
        alert.showAndWait();
    }
}
