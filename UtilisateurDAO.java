package com.example.tp_camping.dao;

import com.example.tp_camping.model.Utilisateur;
import com.example.tp_camping.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtilisateurDAO {
    public void ajouterUtilisateur(Utilisateur utilisateur) throws SQLException {
        String sql = "INSERT INTO utilisateurs (nom, email, mot_de_passe) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, utilisateur.getNom());
            pstmt.setString(2, utilisateur.getEmail());
            pstmt.setString(3, utilisateur.getMotDePasse());
            pstmt.executeUpdate();
        }
    }

    public void modifierUtilisateur(Utilisateur utilisateur) throws SQLException {
        String sql = "UPDATE utilisateurs SET nom = ?, email = ?, mot_de_passe = ? WHERE id_utilisateur = ?";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, utilisateur.getNom());
                pstmt.setString(2, utilisateur.getEmail());
                pstmt.setString(3, utilisateur.getMotDePasse());
                pstmt.setInt(4, utilisateur.getIdUtilisateur());
                pstmt.executeUpdate();
        }
    }

    public void supprimerUtilisateur(int idUtilisateur) throws SQLException {
        String sql = "DELETE FROM utilisateurs WHERE id_utilisateur = ?";
        try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idUtilisateur);
            pstmt.executeUpdate();
        }
    }

    public List<Utilisateur> getAllUtilisateurs() throws SQLException {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        String sql = "SELECT * FROM utilisateurs";
        try (Connection conn = DatabaseConnection.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs =stmt.executeQuery(sql)) {
            while (rs.next()) {
                utilisateurs.add(new Utilisateur(
                    rs.getInt("id_utilisateur"),
                    rs.getString("nom"),
                    rs.getString("email"),
                    rs.getString("mot_de_passe")
                ));
            }
        }
        return utilisateurs; 
    }

    public Utilisateur authentifier(String email, String motDePasse) throws SQLException {
        String sql = "SELECT * FROM utilisateurs WHERE email = ? AND mot_de_passe = ?";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, email);
                pstmt.setString(2, motDePasse);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    return new Utilisateur(
                        rs.getInt("id_utilisateur"),
                        rs.getString("nom"),
                        rs.getString("email"),
                        rs.getString("mot_de_passe")
                    );
                }
            }
    return null;
    
    }
}
