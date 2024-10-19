package com.example.tp_camping.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UtilisateurTest {

    private Utilisateur utilisateur;

    @BeforeEach
    public void setUp() {
        // Crée un utilisateur avant chaque test
        utilisateur = new Utilisateur(1, "Dupont", "dupont@example.com", "password123");
    }

    @Test
    public void testGetIdUtilisateur() {
        assertEquals(1, utilisateur.getIdUtilisateur(), "L'ID de l'utilisateur doit être 1.");
    }

    @Test
    public void testSetIdUtilisateur() {
        utilisateur.setIdUtilisateur(2);
        assertEquals(2, utilisateur.getIdUtilisateur(), "L'ID de l'utilisateur doit être 2 après modification.");
    }

    @Test
    public void testGetNom() {
        assertEquals("Dupont", utilisateur.getNom(), "Le nom de l'utilisateur doit être Dupont.");
    }

    @Test
    public void testSetNom() {
        utilisateur.setNom("Durand");
        assertEquals("Durand", utilisateur.getNom(), "Le nom de l'utilisateur doit être Durand après modification.");
    }

    @Test
    public void testGetEmail() {
        assertEquals("dupont@example.com", utilisateur.getEmail(), "L'email doit être dupont@example.com.");
    }

    @Test
    public void testSetEmail() {
        utilisateur.setEmail("durand@example.com");
        assertEquals("durand@example.com", utilisateur.getEmail(), "L'email doit être durand@example.com après modification.");
    }

    @Test
    public void testGetMotDePasse() {
        assertEquals("password123", utilisateur.getMotDePasse(), "Le mot de passe doit être 'password123'.");
    }

    @Test
    public void testSetMotDePasse() {
        utilisateur.setMotDePasse("newpassword");
        assertEquals("newpassword", utilisateur.getMotDePasse(), "Le mot de passe doit être 'newpassword' après modification.");
    }
}
