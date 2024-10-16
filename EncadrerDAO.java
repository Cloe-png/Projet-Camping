package com.example.tp_camping.dao;

import com.example.tp_camping.model.Encadrer;
import com.example.tp_camping.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EncadrerDAO {

    public void ajouterEncadrement(Encadrer encadrer) throws SQLException {
        String sql = "INSERT INTO encadrer (id_animateur, id_planning) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, encadrer.getIdAnimateur());
            pstmt.setInt(2, encadrer.getIdPlanning());
            pstmt.executeUpdate();
        }
    }

    public void supprimerEncadrement(int idAnimateur, int idPlanning) throws SQLException {
        String sql = "DELETE FROM encadrer WHERE id_animateur = ? AND id_planning = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idAnimateur);
            pstmt.setInt(2, idPlanning);
            pstmt.executeUpdate();
        }
    }

    public List<Encadrer> getEncadrementsByAnimateur(int idAnimateur) throws SQLException {
        List<Encadrer> encadrements = new ArrayList<>();
        String sql = "SELECT * FROM encadrer WHERE id_animateur = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idAnimateur);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    encadrements.add(new Encadrer(rs.getInt("id_animateur"), rs.getInt("id_planning")));
                }
            }
        }
        return encadrements;
    }

    public List<Encadrer> getEncadrementsByCreneau(int idPlanning) throws SQLException {
        List<Encadrer> encadrements = new ArrayList<>();
        String sql = "SELECT * FROM encadrer WHERE id_planning = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idPlanning);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    encadrements.add(new Encadrer(rs.getInt("id_animateur"), rs.getInt("id_planning")));
                }
            }
        }
        return encadrements;
    }

    public boolean existeEncadrement(int idAnimateur, int idPlanning) throws SQLException {
        String sql = "SELECT COUNT(*) FROM encadrer WHERE id_animateur = ? AND id_planning = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idAnimateur);
            pstmt.setInt(2, idPlanning);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    public int getNombreAnimationsParJour(int idAnimateur, Date date) throws SQLException {
        String sql = "SELECT COUNT(*) FROM encadrer e " +
                     "JOIN creneaux c ON e.id_planning = c.id_planning " +
                     "WHERE e.id_animateur = ? AND DATE(c.date) = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idAnimateur);
            pstmt.setDate(2, new java.sql.Date(date.getTime()));
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return 0;
    }
}
