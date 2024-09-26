package com.example.tp_camping;

        import javafx.fxml.FXML;
        import javafx.scene.control.TableView;
        import javafx.scene.control.TextArea;
        import javafx.scene.control.TextField;
        import javafx.scene.control.Alert;
        import javafx.scene.control.Alert.AlertType;

        public class PlanningController {

        @FXML
        private TableView animateursTable;

        @FXML
        private TextField emailSubject;

        @FXML
        private TextArea emailBody;

        // Méthode appelée lors de l'appui sur le bouton "Générer Planning en PDF"
        @FXML
        private void handleGeneratePDF() {
        // Logique pour générer un fichier PDF contenant le planning
        System.out.println("Générer un PDF avec le planning...");

        // Afficher un message de confirmation à l'utilisateur
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("PDF généré");
        alert.setHeaderText(null);
        alert.setContentText("Le planning a été généré avec succès en PDF.");
        alert.showAndWait();
        }

        // Méthode appelée lors de l'appui sur le bouton "Envoyer le Planning"
        @FXML
        private void handleSendEmail() {
        String subject = emailSubject.getText();
        String message = emailBody.getText();

        // Logique pour envoyer un email avec le planning en pièce jointe
        System.out.println("Envoyer l'email avec l'objet: " + subject);
        System.out.println("Message: " + message);

        // Afficher un message de confirmation
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Email envoyé");
        alert.setHeaderText(null);
        alert.setContentText("Le planning a été envoyé aux animateurs avec succès.");
        alert.showAndWait();
        }

        }
