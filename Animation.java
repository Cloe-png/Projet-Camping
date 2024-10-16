package com.example.tp_camping.model;

import java.util.Date;

public class Animation {
    private int idAnimation;
    private String libelleAnimations;
    private String nomAnimation;
    private Date dateAnimations;


    public Animation(int idAnimation, String libelleAnimations, String nomAnimation, Date dateAnimations) {
        this.idAnimation = idAnimation;
        this.libelleAnimations = libelleAnimations;
        this.nomAnimation = nomAnimation;
        this.dateAnimations = dateAnimations;
    }


    public int getIdAnimation() {
        return idAnimation;
    }

    public void setIdAnimation(int idAnimation) {
        this.idAnimation = idAnimation;
    }

    public String getLibelleAnimations() {
        return libelleAnimations;
    }

    public void setLibelleAnimations(String libelleAnimations) {
        this.libelleAnimations = libelleAnimations;
    }

    public String getNomAnimation() {
        return nomAnimation;
    }

    public void setNomAnimation(String nomAnimation) {
        this.nomAnimation = nomAnimation;
    }

    public Date getDateAnimations() {
        return dateAnimations;
    }

    public void setDateAnimations(Date dateAnimations) {
        this.dateAnimations = dateAnimations;
    }
}