package com.example.tp_camping.dao;

import com.example.tp_camping.model.Creneau;
import com.example.tp_camping.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CreneauDAO {
    public void ajouterCreneau(Creneau creneau) throws SQLException {
        String sql = "INSERT INTO creneaux (heure_debut, heure_fin, date, nombre_place, id_animation, id_lieu) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, creneau.getHeureDebut());
            pstmt.setString(2, creneau.getHeureFin());
            pstmt.setTimestamp(3, new java.sql.Timestamp(creneau.getDate().getTime()));
            pstmt.setInt(4, creneau.getNombrePlace());
            pstmt.setInt(5, creneau.getIdAnimation());
            pstmt.setInt(6, creneau.getIdLieu());
            pstmt.executeUpdate();
            
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    creneau.setIdPlanning(generatedKeys.getInt(1));
                }
            }
        }
    }

    public void modifierCreneau(Creneau creneau) throws SQLException {
        String sql = "UPDATE creneaux SET heure_debut = ?, heure_fin = ?, date = ?, nombre_place = ?, id_animation = ?, id_lieu = ? WHERE id_planning = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, creneau.getHeureDebut());
            pstmt.setString(2, creneau.getHeureFin());
            pstmt.setTimestamp(3, new java.sql.Timestamp(creneau.getDate().getTime()));
            pstmt.setInt(4, creneau.getNombrePlace());
            pstmt.setInt(5, creneau.getIdAnimation());
            pstmt.setInt(6, creneau.getIdLieu());
            pstmt.setInt(7, creneau.getIdPlanning());
            pstmt.executeUpdate();
        }
    }

    public void supprimerCreneau(int idPlanning) throws SQLException {
        String sql = "DELETE FROM creneaux WHERE id_planning = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idPlanning);
            pstmt.executeUpdate();
        }
    }

    public Creneau getCreneauById(int idPlanning) throws SQLException {
        String sql = "SELECT * FROM creneaux WHERE id_planning = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idPlanning);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return extractCreneauFromResultSet(rs);
                }
            }
        }
        return null;
    }

    public List<Creneau> getAllCreneaux() throws SQLException {
        List<Creneau> creneaux = new ArrayList<>();
        String sql = "SELECT * FROM creneaux";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                creneaux.add(extractCreneauFromResultSet(rs));
            }
        }
        return creneaux;
    }

    private Creneau extractCreneauFromResultSet(ResultSet rs) throws SQLException {
        return new Creneau(
            rs.getInt("id_planning"),
            rs.getString("heure_debut"),
            rs.getString("heure_fin"),
            rs.getTimestamp("date"),
            rs.getInt("nombre_place"),
            rs.getInt("id_animation"),
            rs.getInt("id_lieu")
        );
    }

    public List<Creneau> getCreneauxByPlanningId(int idPlanning) throws SQLException {
        List<Creneau> creneaux = new ArrayList<>();
        String sql = "SELECT * FROM creneaux WHERE id_planning = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idPlanning);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    creneaux.add(extractCreneauFromResultSet(rs));
                }
            }
        }
        return creneaux;
    }
}