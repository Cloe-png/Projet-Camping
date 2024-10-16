package com.example.tp_camping.model;

public class Animateur {
    private int idAnimateur;
    private String nom;
    private String prenom;
    private String mail;
    private String codePostal;
    private String rueAnimateur;
    private String villeAnimateur;
    private String numTel;


    public Animateur(int idAnimateur, String nom, String prenom, String mail, String codePostal, String rueAnimateur, String villeAnimateur, String numTel) {
        this.idAnimateur = idAnimateur;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.codePostal = codePostal;
        this.rueAnimateur = rueAnimateur;
        this.villeAnimateur = villeAnimateur;
        this.numTel = numTel;
    }


    public int getIdAnimateur() {
        return idAnimateur;
    }

    public void setIdAnimateur(int idAnimateur) {
        this.idAnimateur = idAnimateur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getRueAnimateur() {
        return rueAnimateur;
    }

    public void setRueAnimateur(String rueAnimateur) {
        this.rueAnimateur = rueAnimateur;
    }

    public String getVilleAnimateur() {
        return villeAnimateur;
    }

    public void setVilleAnimateur(String villeAnimateur) {
        this.villeAnimateur = villeAnimateur;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }
}