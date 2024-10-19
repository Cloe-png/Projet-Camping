package com.example.tp_camping;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LieuTest {

    private Lieu lieu;

    @BeforeEach
    void setUp() {
        lieu = new Lieu(1, "Salle de sport", "48.8566, 2.3522");
    }

    @Test
    void testGetIdLieu() {
        assertEquals(1, lieu.getIdLieu(), "L'ID du lieu devrait être 1.");
    }

    @Test
    void testSetIdLieu() {
        lieu.setIdLieu(2);
        assertEquals(2, lieu.getIdLieu(), "L'ID du lieu devrait être 2 après modification.");
    }

    @Test
    void testGetLibelleLieu() {
        assertEquals("Salle de sport", lieu.getLibelleLieu(), "Le libellé du lieu devrait être 'Salle de sport'.");
    }

    @Test
    void testSetLibelleLieu() {
        lieu.setLibelleLieu("Gymnase");
        assertEquals("Gymnase", lieu.getLibelleLieu(), "Le libellé du lieu devrait être 'Gymnase' après modification.");
    }

    @Test
    void testGetCoordonnes() {
        assertEquals("48.8566, 2.3522", lieu.getCoordonnes(), "Les coordonnées devraient être '48.8566, 2.3522'.");
    }

    @Test
    void testSetCoordonnes() {
        lieu.setCoordonnes("40.7128, -74.0060");
        assertEquals("40.7128, -74.0060", lieu.getCoordonnes(), "Les coordonnées devraient être '40.7128, -74.0060' après modification.");
    }
}
