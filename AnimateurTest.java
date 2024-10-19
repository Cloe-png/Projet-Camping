package com.example.tp_camping;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AnimateurTest {

    private Animateur animateur;

    @BeforeEach
    public void setUp() {
        // Initialiser un objet Animateur avant chaque test
        animateur = new Animateur(1, "Dupont", "Jean", "jean.dupont@example.com", "75001", "5 Rue de Paris", "Paris", "0102030405");
    }

    @Test
    public void testGetIdAnimateur() {
        assertEquals(1, animateur.getIdAnimateur(), "L'ID de l'animateur doit être 1.");
    }

    @Test
    public void testSetIdAnimateur() {
        animateur.setIdAnimateur(2);
        assertEquals(2, animateur.getIdAnimateur(), "L'ID de l'animateur doit être 2 après modification.");
    }

    @Test
    public void testGetNom() {
        assertEquals("Dupont", animateur.getNom(), "Le nom de l'animateur doit être Dupont.");
    }

    @Test
    public void testSetNom() {
        animateur.setNom("Durand");
        assertEquals("Durand", animateur.getNom(), "Le nom de l'animateur doit être Durand après modification.");
    }

    @Test
    public void testGetPrenom() {
        assertEquals("Jean", animateur.getPrenom(), "Le prénom de l'animateur doit être Jean.");
    }

    @Test
    public void testSetPrenom() {
        animateur.setPrenom("Paul");
        assertEquals("Paul", animateur.getPrenom(), "Le prénom de l'animateur doit être Paul après modification.");
    }

    @Test
    public void testGetMail() {
        assertEquals("jean.dupont@example.com", animateur.getMail(), "L'email doit être jean.dupont@example.com.");
    }

    @Test
    public void testSetMail() {
        animateur.setMail("paul.durand@example.com");
        assertEquals("paul.durand@example.com", animateur.getMail(), "L'email doit être paul.durand@example.com après modification.");
    }

    @Test
    public void testGetCodePostal() {
        assertEquals("75001", animateur.getCodePostal(), "Le code postal doit être 75001.");
    }

    @Test
    public void testSetCodePostal() {
        animateur.setCodePostal("75002");
        assertEquals("75002", animateur.getCodePostal(), "Le code postal doit être 75002 après modification.");
    }

    @Test
    public void testGetRueAnimateur() {
        assertEquals("5 Rue de Paris", animateur.getRueAnimateur(), "L'adresse doit être 5 Rue de Paris.");
    }

    @Test
    public void testSetRueAnimateur() {
        animateur.setRueAnimateur("10 Rue de Lyon");
        assertEquals("10 Rue de Lyon", animateur.getRueAnimateur(), "L'adresse doit être 10 Rue de Lyon après modification.");
    }

    @Test
    public void testGetVilleAnimateur() {
        assertEquals("Paris", animateur.getVilleAnimateur(), "La ville doit être Paris.");
    }

    @Test
    public void testSetVilleAnimateur() {
        animateur.setVilleAnimateur("Lyon");
        assertEquals("Lyon", animateur.getVilleAnimateur(), "La ville doit être Lyon après modification.");
    }

    @Test
    public void testGetNumTel() {
        assertEquals("0102030405", animateur.getNumTel(), "Le numéro de téléphone doit être 0102030405.");
    }

    @Test
    public void testSetNumTel() {
        animateur.setNumTel("0607080910");
        assertEquals("0607080910", animateur.getNumTel(), "Le numéro de téléphone doit être 0607080910 après modification.");
    }
}
