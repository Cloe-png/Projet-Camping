package com.example.tp_camping.dao;

import com.example.tp_camping.model.Lieu;
import com.example.tp_camping.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LieuDAO {
    public void ajouterLieu(Lieu lieu) throws SQLException {
        String sql = "INSERT INTO lieu (libelle_lieu, coordonnes) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, lieu.getLibelleLieu());
            pstmt.setString(2, lieu.getCoordonnes());
            pstmt.executeUpdate();
            
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    lieu.setIdLieu(generatedKeys.getInt(1));
                }
            }
        }
    }

    public void modifierLieu(Lieu lieu) throws SQLException {
        String sql = "UPDATE lieu SET libelle_lieu = ?, coordonnes = ? WHERE id_lieu = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, lieu.getLibelleLieu());
            pstmt.setString(2, lieu.getCoordonnes());
            pstmt.setInt(3, lieu.getIdLieu());
            pstmt.executeUpdate();
        }
    }

    public void supprimerLieu(int idLieu) throws SQLException {
        String sql = "DELETE FROM lieu WHERE id_lieu = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idLieu);
            pstmt.executeUpdate();
        }
    }

    public Lieu getLieuById(int idLieu) throws SQLException {
        String sql = "SELECT * FROM lieu WHERE id_lieu = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idLieu);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return extractLieuFromResultSet(rs);
                }
            }
        }
        return null;
    }

    public List<Lieu> getAllLieux() throws SQLException {
        List<Lieu> lieux = new ArrayList<>();
        String sql = "SELECT * FROM lieu";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lieux.add(extractLieuFromResultSet(rs));
            }
        }
        return lieux;
    }

    private Lieu extractLieuFromResultSet(ResultSet rs) throws SQLException {
        return new Lieu(
            rs.getInt("id_lieu"),
            rs.getString("libelle_lieu"),
            rs.getString("coordonnes")
        );
    }
}