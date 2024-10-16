package com.example.tp_camping.model;

import java.util.Date;
import java.util.List;

public class Planning {
    private int idPlanning;
    private Date dateDebut;
    private Date dateFin;
    private List<Creneau> creneaux;

    public Planning(int idPlanning, Date dateDebut, Date dateFin) {
        this.idPlanning = idPlanning;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }


    public int getIdPlanning() {
        return idPlanning;
    }

    public void setIdPlanning(int idPlanning) {
        this.idPlanning = idPlanning;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public List<Creneau> getCreneaux() {
        return creneaux;
    }

    public void setCreneaux(List<Creneau> creneaux) {
        this.creneaux = creneaux;
    }
}
