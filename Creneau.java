package com.example.tp_camping.model;

import java.util.Date;

public class Creneau {
    private int idPlanning;
    private String heureDebut;
    private String heureFin;
    private Date date;
    private int nombrePlace;
    private int idAnimation;
    private int idLieu;


    public Creneau(int idPlanning, String heureDebut, String heureFin, Date date, int nombrePlace, int idAnimation, int idLieu) {
        this.idPlanning = idPlanning;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.date = date;
        this.nombrePlace = nombrePlace;
        this.idAnimation = idAnimation;
        this.idLieu = idLieu;
    }


    public int getIdPlanning() {
        return idPlanning;
    }

    public void setIdPlanning(int idPlanning) {
        this.idPlanning = idPlanning;
    }

    public String getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(String heureDebut) {
        this.heureDebut = heureDebut;
    }

    public String getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(String heureFin) {
        this.heureFin = heureFin;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNombrePlace() {
        return nombrePlace;
    }

    public void setNombrePlace(int nombrePlace) {
        this.nombrePlace = nombrePlace;
    }

    public int getIdAnimation() {
        return idAnimation;
    }

    public void setIdAnimation(int idAnimation) {
        this.idAnimation = idAnimation;
    }

    public int getIdLieu() {
        return idLieu;
    }

    public void setIdLieu(int idLieu) {
        this.idLieu = idLieu;
    }
}