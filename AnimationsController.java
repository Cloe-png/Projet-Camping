package com.example.tp_camping.controller;

import com.example.tp_camping.dao.AnimationDAO;
import com.example.tp_camping.model.Animation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.time.LocalDate;
import java.time.ZoneId;

public class AnimationsController implements Initializable {

    @FXML private TextField libelleField;
    @FXML private TextField nomField;
    @FXML private DatePicker dateField;
    @FXML private TableView<Animation> animationsTable;
    @FXML private TableColumn<Animation, String> libelleColumn;
    @FXML private TableColumn<Animation, String> nomColumn;
    @FXML private TableColumn<Animation, Date> dateColumn;
    @FXML private Button ajouterBtn;
    @FXML private Button modifierBtn;
    @FXML private Button supprimerBtn;

    private AnimationDAO animationDAO;
    private ObservableList<Animation> animations;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        animationDAO = new AnimationDAO();
        animations = FXCollections.observableArrayList();

        libelleColumn.setCellValueFactory(new PropertyValueFactory<>("libelleAnimations"));
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nomAnimation"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("dateAnimations"));

        animationsTable.setItems(animations);

        chargerAnimations();

        ajouterBtn.setOnAction(event -> ajouterAnimation());
        modifierBtn.setOnAction(event -> modifierAnimation());
        supprimerBtn.setOnAction(event -> supprimerAnimation());

        animationsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                afficherDetailsAnimation((Animation) newSelection);
            }
        });
    }

    private void chargerAnimations() {
        try {
            animations.clear();
            animations.addAll(animationDAO.getAllAnimations());
        } catch (SQLException e) {
            afficherErreur("Erreur lors du chargement des animations", e);
        }
    }

    private void ajouterAnimation() {
        Animation animation = new Animation(0, libelleField.getText(), nomField.getText(), java.sql.Date.valueOf(dateField.getValue()));
        try {
            animationDAO.ajouterAnimation(animation);
            chargerAnimations();
            viderChamps();
        } catch (SQLException e) {
            afficherErreur("Erreur lors de l'ajout de l'animation", e);
        }
    }

    private void modifierAnimation() {
        Animation animationSelectionnee = (Animation) animationsTable.getSelectionModel().getSelectedItem();
        if (animationSelectionnee != null) {
            animationSelectionnee.setLibelleAnimations(libelleField.getText());
            animationSelectionnee.setNomAnimation(nomField.getText());
            animationSelectionnee.setDateAnimations(java.sql.Date.valueOf(dateField.getValue()));

            try {
                animationDAO.modifierAnimation(animationSelectionnee);
                chargerAnimations();
            } catch (SQLException e) {
                afficherErreur("Erreur lors de la modification de l'animation", e);
            }
        }
    }

    private void supprimerAnimation() {
        Animation animationSelectionnee = (Animation) animationsTable.getSelectionModel().getSelectedItem();
        if (animationSelectionnee != null) {
            try {
                animationDAO.supprimerAnimation(animationSelectionnee.getIdAnimation());
                chargerAnimations();
                viderChamps();
            } catch (SQLException e) {
                afficherErreur("Erreur lors de la suppression de l'animation", e);
            }
        }
    }

    private void afficherDetailsAnimation(Animation animation) {
        libelleField.setText(animation.getLibelleAnimations());
        nomField.setText(animation.getNomAnimation());
        dateField.setValue(animation.getDateAnimations().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }

    private void viderChamps() {
        libelleField.clear();
        nomField.clear();
        dateField.setValue(null);
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
