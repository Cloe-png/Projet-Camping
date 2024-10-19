package com.example.tp_camping;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EncadrerTest {

    private Encadrer encadrer;

    @BeforeEach
    void setUp() {
        encadrer = new Encadrer(1, 100);
    }

    @Test
    void testGetIdAnimateur() {
        assertEquals(1, encadrer.getIdAnimateur(), "L'ID de l'animateur devrait être 1.");
    }

    @Test
    void testSetIdAnimateur() {
        encadrer.setIdAnimateur(2);
        assertEquals(2, encadrer.getIdAnimateur(), "L'ID de l'animateur devrait être 2 après modification.");
    }

    @Test
    void testGetIdPlanning() {
        assertEquals(100, encadrer.getIdPlanning(), "L'ID du planning devrait être 100.");
    }

    @Test
    void testSetIdPlanning() {
        encadrer.setIdPlanning(200);
        assertEquals(200, encadrer.getIdPlanning(), "L'ID du planning devrait être 200 après modification.");
    }
}
