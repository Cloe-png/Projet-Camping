package com.example.tp_camping.controller;

import com.example.tp_camping.dao.PlanningDAO;
import com.example.tp_camping.model.Planning;
import com.example.tp_camping.util.EmailService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.time.ZoneId;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class PlanningController implements Initializable {

    @FXML private DatePicker dateDebutField;
    @FXML private DatePicker dateFinField;
    @FXML private TableView<Planning> planningsTable;
    @FXML private TableColumn<Planning, String> dateDebutColumn;
    @FXML private TableColumn<Planning, String> dateFinColumn;
    @FXML private Button ajouterButton;
    @FXML private Button modifierButton;
    @FXML private Button supprimerButton;
    @FXML private Button exporterButton;

    private PlanningDAO planningDAO;
    private ObservableList<Planning> plannings;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        planningDAO = new PlanningDAO();
        plannings = FXCollections.observableArrayList();

        dateDebutColumn.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
        dateFinColumn.setCellValueFactory(new PropertyValueFactory<>("dateFin"));

        planningsTable.setItems(plannings);

        chargerPlannings();

        ajouterButton.setOnAction(event -> ajouterPlanning());
        modifierButton.setOnAction(event -> modifierPlanning());
        supprimerButton.setOnAction(event -> supprimerPlanning());
        exporterButton.setOnAction(event -> exporterPlanningsCSV());

        planningsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                afficherDetailsPlanning(newSelection);
            }
        });
    }

    private void chargerPlannings() {
        try {
            plannings.clear();
            plannings.addAll(planningDAO.getAllPlannings());
        } catch (SQLException e) {
            afficherErreur("Erreur lors du chargement des plannings", e);
        }
    }

    private void ajouterPlanning() {
        Planning planning = new Planning(0, java.sql.Date.valueOf(dateDebutField.getValue()), java.sql.Date.valueOf(dateFinField.getValue()));
        try {
            planningDAO.ajouterPlanning(planning);
            chargerPlannings();
            viderChamps();
            genererRapport();

            EmailService emailService = new EmailService();
            String to = "recipient@example.com"; // à remplacer plus tard
            String subject = "Nouveau Planning Ajouté";
            String body = "Un nouveau planning a été ajouté avec les dates suivantes :\n" +
                          "Date de début : " + planning.getDateDebut() + "\n" +
                          "Date de fin : " + planning.getDateFin();
            emailService.envoyerEmail(to, subject, body);

            afficherNotification("Succès", "Planning ajouté avec succès !");
        } catch (SQLException e) {
            afficherErreur("Erreur lors de l'ajout du planning", e);
        }
    }

    private void modifierPlanning() {
        Planning planningSelectionne = planningsTable.getSelectionModel().getSelectedItem();
        if (planningSelectionne != null) {
            planningSelectionne.setDateDebut(java.sql.Date.valueOf(dateDebutField.getValue()));
            planningSelectionne.setDateFin(java.sql.Date.valueOf(dateFinField.getValue()));

            try {
                planningDAO.modifierPlanning(planningSelectionne);
                chargerPlannings();
                genererRapport();
            } catch (SQLException e) {
                afficherErreur("Erreur lors de la modification du planning", e);
            }
        }
    }

    private void supprimerPlanning() {
        Planning planningSelectionne = planningsTable.getSelectionModel().getSelectedItem();
        if (planningSelectionne != null) {
            try {
                planningDAO.supprimerPlanning(planningSelectionne.getIdPlanning());
                chargerPlannings();
                viderChamps();
            } catch (SQLException e) {
                afficherErreur("Erreur lors de la suppression du planning", e);
            }
        }
    }

    private void afficherDetailsPlanning(Planning planning) {
        // Remplacer les appels à toLocalDate() par une conversion appropriée
        dateDebutField.setValue(planning.getDateDebut().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        dateFinField.setValue(planning.getDateFin().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }

    private void viderChamps() {
        dateDebutField.setValue(null);
        dateFinField.setValue(null);
    }

    private void afficherErreur(String titre, Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(e.getMessage());
        alert.getDialogPane().setStyle("-fx-font-family: 'Arial';");
        alert.showAndWait();
    }

    private void genererRapport() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("rapport_plannings.txt"))) {
            for (Planning planning : plannings) {
                writer.write("Planning ID : " + planning.getIdPlanning());
                writer.write(", Date de début : " + planning.getDateDebut().toString());
                writer.write(", Date de fin : " + planning.getDateFin());
                writer.newLine();
            }
            System.out.println("Rapport généré avec succès !");
        } catch (IOException e) {
            afficherErreur("Erreur lors de la génération du rapport", e);
        }
    }

    private void exporterPlanningsCSV() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("plannings.csv"))) {
            writer.write("ID,Date de début,Date de fin");
            writer.newLine();
            for (Planning planning : plannings) {
                writer.write(planning.getIdPlanning() + "," + planning.getDateDebut() + "," + planning.getDateFin());
                writer.newLine();
            }
            System.out.println("Exportation réussie vers un fichier CSV.");
        } catch (IOException e) {
            afficherErreur("Erreur lors de l'exportation des plannings", e);
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
}