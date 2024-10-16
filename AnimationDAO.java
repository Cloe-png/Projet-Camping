package com.example.tp_camping.dao;

import com.example.tp_camping.model.Animation;
import com.example.tp_camping.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnimationDAO {
    public void ajouterAnimation(Animation animation) throws SQLException {
        String sql = "INSERT INTO animations (libelle_animations, nom_animation, date_animations) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, animation.getLibelleAnimations());
            pstmt.setString(2, animation.getNomAnimation());
            pstmt.setDate(3, new java.sql.Date(animation.getDateAnimations().getTime()));
            pstmt.executeUpdate();
            
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    animation.setIdAnimation(generatedKeys.getInt(1));
                }
            }
        }
    }

    public void modifierAnimation(Animation animation) throws SQLException {
        String sql = "UPDATE animations SET libelle_animations = ?, nom_animation = ?, date_animations = ? WHERE id_animation = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, animation.getLibelleAnimations());
            pstmt.setString(2, animation.getNomAnimation());
            pstmt.setDate(3, new java.sql.Date(animation.getDateAnimations().getTime()));
            pstmt.setInt(4, animation.getIdAnimation());
            pstmt.executeUpdate();
        }
    }

    public void supprimerAnimation(int idAnimation) throws SQLException {
        String sql = "DELETE FROM animations WHERE id_animation = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idAnimation);
            pstmt.executeUpdate();
        }
    }

    public Animation getAnimationById(int idAnimation) throws SQLException {
        String sql = "SELECT * FROM animations WHERE id_animation = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idAnimation);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return extractAnimationFromResultSet(rs);
                }
            }
        }
        return null;
    }

    public List<Animation> getAllAnimations() throws SQLException {
        List<Animation> animations = new ArrayList<>();
        String sql = "SELECT * FROM animations";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                animations.add(extractAnimationFromResultSet(rs));
            }
        }
        return animations;
    }

    private Animation extractAnimationFromResultSet(ResultSet rs) throws SQLException {
        return new Animation(
            rs.getInt("id_animation"),
            rs.getString("libelle_animations"),
            rs.getString("nom_animation"),
            rs.getDate("date_animations")
        );
    }
}