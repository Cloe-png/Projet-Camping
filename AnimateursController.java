package com.example.tp_camping.controller;

import com.example.tp_camping.dao.AnimateurDAO;
import com.example.tp_camping.model.Animateur;
import com.example.tp_camping.util.ValidationUtil;
import com.example.tp_camping.util.EmailService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Pair;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class AnimateursController implements Initializable {

    @FXML private TextField nomField;
    @FXML private TextField prenomField;
    @FXML private TextField mailField;
    @FXML private TextField codePostalField;
    @FXML private TextField rueField;
    @FXML private TextField villeField;
    @FXML private TextField telField;
    @FXML private TableView<Animateur> animateursTable;
    @FXML private TableColumn<Animateur, String> nomColumn;
    @FXML private TableColumn<Animateur, String> prenomColumn;
    @FXML private TableColumn<Animateur, String> mailColumn;
    @FXML private TableColumn<Animateur, String> codePostalColumn;
    @FXML private TableColumn<Animateur, String> rueColumn;
    @FXML private TableColumn<Animateur, String> villeColumn;
    @FXML private TableColumn<Animateur, String> telColumn;
    @FXML 
    private Button ajouterBtn;
    @FXML 
    private Button modifierBtn;
    @FXML 
    private Button supprimerBtn;
    @FXML private TextField searchField;
    @FXML private Button searchButton;
    @FXML private Button envoyerEmailBtn;

    private AnimateurDAO animateurDAO;
    private ObservableList<Animateur> animateurs;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        animateurDAO = new AnimateurDAO();
        animateurs = FXCollections.observableArrayList();

        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        mailColumn.setCellValueFactory(new PropertyValueFactory<>("mail"));
        codePostalColumn.setCellValueFactory(new PropertyValueFactory<>("code_postal"));
        rueColumn.setCellValueFactory(new PropertyValueFactory<>("rue_animateur"));
        villeColumn.setCellValueFactory(new PropertyValueFactory<>("ville_animateur"));
        telColumn.setCellValueFactory(new PropertyValueFactory<>("num_tel"));

        animateursTable.setItems(animateurs);

        chargerAnimateurs();

        ajouterBtn.setOnAction(event -> ajouterAnimateur());
        if (ajouterBtn.isPressed()) {
            afficherNotification("Succès", "L'animateur a été ajouté à la base de données avec succès");
        }

        modifierBtn.setOnAction(event -> modifierAnimateur());
        if (modifierBtn.isPressed()) {
            afficherNotification("Succès", "L'animateur a été modifié avec succès");
        }
        supprimerBtn.setOnAction(event -> supprimerAnimateur());
        if (supprimerBtn.isPressed()) {
            afficherNotification("Succès", "L'animateur a été supprimé de la base de données avec succès");
        }

        animateursTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                afficherDetailsAnimateur(newSelection);
            }
        });

        searchButton.setOnAction(event -> rechercherAnimateurs());

        envoyerEmailBtn.setOnAction(event -> envoyerEmailAnimateur());
    }

    private void chargerAnimateurs() {
        try {
            animateurs.clear();
            List<Animateur> listeAnimateurs = animateurDAO.getAllAnimateurs();
            System.out.println("Nombre d'animateurs récupérés : " + listeAnimateurs.size()); // Débogage
            if (listeAnimateurs != null && !listeAnimateurs.isEmpty()) {
                animateurs.addAll(listeAnimateurs);
            } else {
                afficherErreur("Aucun animateur trouvé", new Exception("La liste des animateurs est vide."));
            }
        } catch (SQLException e) {
            afficherErreur("Erreur lors du chargement des animateurs", e);
        }
    }

    private void ajouterAnimateur() {
        String nom = nomField.getText();
        String prenom = prenomField.getText();
        String email = mailField.getText();

        if (!ValidationUtil.isValidName(nom, prenom)) {
            afficherErreur("Erreur", new Exception("Le nom et le prénom doivent contenir uniquement des lettres, des espaces, des apostrophes ou des tirets."));
            return;
        }
        if (!ValidationUtil.isValidEmail(email)) {
            afficherErreur("Erreur", new Exception("L'email n'est pas valide."));
            return;
        }

        Animateur animateur = new Animateur(0, nom, prenom, email,
                codePostalField.getText(), rueField.getText(), villeField.getText(), telField.getText());
        try {
            animateurDAO.ajouterAnimateur(animateur);
            chargerAnimateurs();
            viderChamps();
        } catch (SQLException e) {
            afficherErreur("Erreur lors de l'ajout de l'animateur", e);
        }
    }

    private void modifierAnimateur() {
        Animateur animateurSelectionne = animateursTable.getSelectionModel().getSelectedItem();
        if (animateurSelectionne != null) {
            animateurSelectionne.setNom(nomField.getText());
            animateurSelectionne.setPrenom(prenomField.getText());
            animateurSelectionne.setMail(mailField.getText());
            animateurSelectionne.setCodePostal(codePostalField.getText());
            animateurSelectionne.setRueAnimateur(rueField.getText());
            animateurSelectionne.setVilleAnimateur(villeField.getText());
            animateurSelectionne.setNumTel(telField.getText());

            try {
                animateurDAO.modifierAnimateur(animateurSelectionne);
                chargerAnimateurs();
            } catch (SQLException e) {
                afficherErreur("Erreur lors de la modification de l'animateur", e);
            }
        }
    }

    private void supprimerAnimateur() {
        Animateur animateurSelectionne = animateursTable.getSelectionModel().getSelectedItem();
        if (animateurSelectionne != null) {
            try {
                if (!animateurDAO.animateurAAnimeActivite(animateurSelectionne.getIdAnimateur())) {
                    animateurDAO.supprimerAnimateur(animateurSelectionne.getIdAnimateur());
                    chargerAnimateurs();
                    viderChamps();
                } else {
                    afficherErreur("Impossible de supprimer l'animateur", new Exception("L'animateur a déjà animé une activité"));
                }
            } catch (SQLException e) {
                afficherErreur("Erreur lors de la suppression de l'animateur", e);
            }
        }
    }

    private void afficherDetailsAnimateur(Animateur animateur) {
        nomField.setText(animateur.getNom());
        prenomField.setText(animateur.getPrenom());
        mailField.setText(animateur.getMail());
        codePostalField.setText(animateur.getCodePostal());
        rueField.setText(animateur.getRueAnimateur());
        villeField.setText(animateur.getVilleAnimateur());
        telField.setText(animateur.getNumTel());
    }

    private void viderChamps() {
        nomField.clear();
        prenomField.clear();
        mailField.clear();
        codePostalField.clear();
        rueField.clear();
        villeField.clear();
        telField.clear();
    }

    private void afficherErreur(String titre, Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(e.getMessage());
        alert.showAndWait();
    }

    private void rechercherAnimateurs() {
        String searchText = searchField.getText().toLowerCase();
        List<Animateur> filteredAnimateurs = animateurs.stream()
            .filter(animateur -> animateur.getNom().toLowerCase().contains(searchText) || 
                                animateur.getPrenom().toLowerCase().contains(searchText))
            .collect(Collectors.toList());
        animateursTable.setItems(FXCollections.observableArrayList(filteredAnimateurs));
    }

    private void afficherNotification(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.getDialogPane().setStyle("-fx-font-family: 'Arial';");
        alert.showAndWait();
    }

    private void envoyerEmailAnimateur() {
        Animateur animateurSelectionne = animateursTable.getSelectionModel().getSelectedItem();
        if (animateurSelectionne != null) {
            EmailService emailService = new EmailService();
            String to = animateurSelectionne.getMail();
            if (!ValidationUtil.isValidEmail(to)) {
                afficherErreur("Erreur", new Exception("L'adresse e-mail n'est pas valide."));
                return;
            }

            String subject = "Notification d'animation";
            String body = "Bonjour " + animateurSelectionne.getNom() + ",\n\nVous avez une nouvelle animation à gérer.";

            try {
                emailService.envoyerEmail(to, subject, body);
                afficherNotification("Succès", "E-mail envoyé à " + animateurSelectionne.getNom());
            } catch (Exception e) {
                afficherErreur("Erreur lors de l'envoi de l'e-mail", e);
            }
        } else {
            afficherErreur("Erreur", new Exception("Veuillez sélectionner un animateur."));
        }
    }
}
