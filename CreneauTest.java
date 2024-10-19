package com.example.tp_camping;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class CreneauTest {

    private Creneau creneau;
    private Date dateTest;

    @BeforeEach
    void setUp() {
        dateTest = new Date();  // Initialiser une date pour les tests
        creneau = new Creneau(1, "08:00", "10:00", dateTest, 50, 2, 3);
    }

    @Test
    void testGetIdPlanning() {
        assertEquals(1, creneau.getIdPlanning(), "L'ID du planning devrait être 1.");
    }

    @Test
    void testSetIdPlanning() {
        creneau.setIdPlanning(5);
        assertEquals(5, creneau.getIdPlanning(), "L'ID du planning devrait être 5 après modification.");
    }

    @Test
    void testGetHeureDebut() {
        assertEquals("08:00", creneau.getHeureDebut(), "L'heure de début devrait être '08:00'.");
    }

    @Test
    void testSetHeureDebut() {
        creneau.setHeureDebut("09:00");
        assertEquals("09:00", creneau.getHeureDebut(), "L'heure de début devrait être '09:00' après modification.");
    }

    @Test
    void testGetHeureFin() {
        assertEquals("10:00", creneau.getHeureFin(), "L'heure de fin devrait être '10:00'.");
    }

    @Test
    void testSetHeureFin() {
        creneau.setHeureFin("11:00");
        assertEquals("11:00", creneau.getHeureFin(), "L'heure de fin devrait être '11:00' après modification.");
    }

    @Test
    void testGetDate() {
        assertEquals(dateTest, creneau.getDate(), "La date devrait correspondre à la date de test initialisée.");
    }

    @Test
    void testSetDate() {
        Date nouvelleDate = new Date();
        creneau.setDate(nouvelleDate);
        assertEquals(nouvelleDate, creneau.getDate(), "La date devrait être modifiée correctement.");
    }

    @Test
    void testGetNombrePlace() {
        assertEquals(50, creneau.getNombrePlace(), "Le nombre de places devrait être 50.");
    }

    @Test
    void testSetNombrePlace() {
        creneau.setNombrePlace(60);
        assertEquals(60, creneau.getNombrePlace(), "Le nombre de places devrait être 60 après modification.");
    }

    @Test
    void testGetIdAnimation() {
        assertEquals(2, creneau.getIdAnimation(), "L'ID de l'animation devrait être 2.");
    }

    @Test
    void testSetIdAnimation() {
        creneau.setIdAnimation(5);
        assertEquals(5, creneau.getIdAnimation(), "L'ID de l'animation devrait être 5 après modification.");
    }

    @Test
    void testGetIdLieu() {
        assertEquals(3, creneau.getIdLieu(), "L'ID du lieu devrait être 3.");
    }

    @Test
    void testSetIdLieu() {
        creneau.setIdLieu(10);
        assertEquals(10, creneau.getIdLieu(), "L'ID du lieu devrait être 10 après modification.");
    }
}
