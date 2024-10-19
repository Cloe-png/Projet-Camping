package com.example.tp_camping;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class AnimationTest {

    private Animation animation;
    private Date dateTest;

    @BeforeEach
    void setUp() {
        dateTest = new Date();
        animation = new Animation(1, "Jeux", "Tournoi de foot", dateTest);
    }

    @Test
    void testGetIdAnimation() {
        assertEquals(1, animation.getIdAnimation(), "L'ID de l'animation devrait être 1.");
    }

    @Test
    void testSetIdAnimation() {
        animation.setIdAnimation(2);
        assertEquals(2, animation.getIdAnimation(), "L'ID de l'animation devrait être 2 après modification.");
    }

    @Test
    void testGetLibelleAnimations() {
        assertEquals("Jeux", animation.getLibelleAnimations(), "Le libellé de l'animation devrait être 'Jeux'.");
    }

    @Test
    void testSetLibelleAnimations() {
        animation.setLibelleAnimations("Sport");
        assertEquals("Sport", animation.getLibelleAnimations(), "Le libellé de l'animation devrait être 'Sport' après modification.");
    }

    @Test
    void testGetNomAnimation() {
        assertEquals("Tournoi de foot", animation.getNomAnimation(), "Le nom de l'animation devrait être 'Tournoi de foot'.");
    }

    @Test
    void testSetNomAnimation() {
        animation.setNomAnimation("Course de relais");
        assertEquals("Course de relais", animation.getNomAnimation(), "Le nom de l'animation devrait être 'Course de relais' après modification.");
    }

    @Test
    void testGetDateAnimations() {
        assertEquals(dateTest, animation.getDateAnimations(), "La date de l'animation devrait correspondre à la date de test initialisée.");
    }

    @Test
    void testSetDateAnimations() {
        Date nouvelleDate = new Date();
        animation.setDateAnimations(nouvelleDate);
        assertEquals(nouvelleDate, animation.getDateAnimations(), "La date de l'animation devrait être modifiée correctement.");
    }
}
