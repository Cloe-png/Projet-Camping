package com.example.tp_camping.model;

public class Lieu {
    private int idLieu;
    private String libelleLieu;
    private String coordonnes;


    public Lieu(int idLieu, String libelleLieu, String coordonnes) {
        this.idLieu = idLieu;
        this.libelleLieu = libelleLieu;
        this.coordonnes = coordonnes;
    }


    public int getIdLieu() {
        return idLieu;
    }

    public void setIdLieu(int idLieu) {
        this.idLieu = idLieu;
    }

    public String getLibelleLieu() {
        return libelleLieu;
    }

    public void setLibelleLieu(String libelleLieu) {
        this.libelleLieu = libelleLieu;
    }

    public String getCoordonnes() {
        return coordonnes;
    }

    public void setCoordonnes(String coordonnes) {
        this.coordonnes = coordonnes;
    }
}