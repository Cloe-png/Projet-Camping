package com.example.tp_camping.controller;

import com.example.tp_camping.dao.LieuDAO;
import com.example.tp_camping.model.Lieu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LieuController implements Initializable {
    @FXML private TextField libelleField;
    @FXML private TextField coordonnesField;
    @FXML private TableView<Lieu> lieuxTable;
    @FXML private TableColumn<Lieu, String> libelleColumn;
    @FXML private TableColumn<Lieu, String> coordonnesColumn;
    @FXML private Button ajouterBtn;
    @FXML private Button modifierBtn;
    @FXML private Button supprimerBtn;

    private LieuDAO lieuDAO;
    private ObservableList<Lieu> lieux;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lieuDAO = new LieuDAO();
        lieux = FXCollections.observableArrayList();

        libelleColumn.setCellValueFactory(new PropertyValueFactory<>("libelleLieu"));
        coordonnesColumn.setCellValueFactory(new PropertyValueFactory<>("coordonnes"));

        lieuxTable.setItems(lieux);

        chargerLieux();

        ajouterBtn.setOnAction(event -> ajouterLieu());
        modifierBtn.setOnAction(event -> modifierLieu());
        supprimerBtn.setOnAction(event -> supprimerLieu());
    }

    private void chargerLieux() {
        try {
            lieux.clear();
            lieux.addAll(lieuDAO.getAllLieux());
        } catch (SQLException e) {
            afficherErreur("Erreur lors du chargement des lieux", e);
        }
    }

    private void ajouterLieu() {
        Lieu lieu = new Lieu(0, libelleField.getText(), coordonnesField.getText());
        try {
        lieuDAO.ajouterLieu(lieu);
        chargerLieux();
        viderChamps();
    } catch (SQLException e) {
        afficherErreur("Erreur lors de l'ajout du lieu", e);
    }
}

    private void modifierLieu() {
        Lieu lieuSelectionne = lieuxTable.getSelectionModel().getSelectedItem();
        if (lieuSelectionne != null) {
            lieuSelectionne.setLibelleLieu(libelleField.getText());
            lieuSelectionne.setCoordonnes(coordonnesField.getText());

            try {
                lieuDAO.modifierLieu(lieuSelectionne);
                chargerLieux();
            } catch (SQLException e) {
                afficherErreur("Erreur lors de la modification du lieu", e);
            }
        }
    }

    private void supprimerLieu() {
        Lieu lieuSelectionne = lieuxTable.getSelectionModel().getSelectedItem();
        if (lieuSelectionne != null) {
            try {
                lieuDAO.supprimerLieu(lieuSelectionne.getIdLieu());
                chargerLieux();
                viderChamps();
            } catch (SQLException e) {
                afficherErreur("Erreur lors de la suppression du lieu", e);
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
        libelleField.clear();
        coordonnesField.clear();
    }

}
