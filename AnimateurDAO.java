package com.example.tp_camping.dao;

import com.example.tp_camping.model.Animateur;
import com.example.tp_camping.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnimateurDAO {
    public void ajouterAnimateur(Animateur animateur) throws SQLException {
        String sql = "INSERT INTO animateurs (nom, prenom, mail, code_postal, rue_animateur, ville_animateur, num_tel) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, animateur.getNom());
            pstmt.setString(2, animateur.getPrenom());
            pstmt.setString(3, animateur.getMail());
            pstmt.setString(4, animateur.getCodePostal());
            pstmt.setString(5, animateur.getRueAnimateur());
            pstmt.setString(6, animateur.getVilleAnimateur());
            pstmt.setString(7, animateur.getNumTel());
            pstmt.executeUpdate();
            
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    animateur.setIdAnimateur(generatedKeys.getInt(1));
                }
            }
        }
    }

    public void modifierAnimateur(Animateur animateur) throws SQLException {
        String sql = "UPDATE animateurs SET nom = ?, prenom = ?, mail = ?, code_postal = ?, rue_animateur = ?, ville_animateur = ?, num_tel = ? WHERE id_animateur = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, animateur.getNom());
            pstmt.setString(2, animateur.getPrenom());
            pstmt.setString(3, animateur.getMail());
            pstmt.setString(4, animateur.getCodePostal());
            pstmt.setString(5, animateur.getRueAnimateur());
            pstmt.setString(6, animateur.getVilleAnimateur());
            pstmt.setString(7, animateur.getNumTel());
            pstmt.setInt(8, animateur.getIdAnimateur());
            pstmt.executeUpdate();
        }
    }

    public void supprimerAnimateur(int idAnimateur) throws SQLException {
        String sql = "DELETE FROM animateurs WHERE id_animateur = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idAnimateur);
            pstmt.executeUpdate();
        }
    }

    public Animateur getAnimateurById(int idAnimateur) throws SQLException {
        String sql = "SELECT * FROM animateurs WHERE id_animateur = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idAnimateur);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return extractAnimateurFromResultSet(rs);
                }
            }
        }
        return null;
    }

    public List<Animateur> getAllAnimateurs() throws SQLException {
        List<Animateur> animateurs = new ArrayList<>();
        String sql = "SELECT * FROM animateurs";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    animateurs.add(new Animateur(
                        rs.getInt("id_animateur"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("mail"),
                        rs.getString("code_postal"),
                        rs.getString("rue_animateur"),
                        rs.getString("ville_animateur"),
                        rs.getString("num_tel")
                    ));
                }
                if (animateurs.isEmpty()) {
                    System.out.println("Aucun animateur trouvé.");
                }
             }
        return animateurs;
    }

    private Animateur extractAnimateurFromResultSet(ResultSet rs) throws SQLException {
        return new Animateur(
            rs.getInt("id_animateur"),
            rs.getString("nom"),
            rs.getString("prenom"),
            rs.getString("mail"),
            rs.getString("code_postal"),
            rs.getString("rue_animateur"),
            rs.getString("ville_animateur"),
            rs.getString("num_tel")
        );
    }

    public boolean animateurAAnimeActivite(int idAnimateur) throws SQLException {
        String sql = "SELECT COUNT(*) FROM encadrer WHERE id_animateur = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idAnimateur);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }
}