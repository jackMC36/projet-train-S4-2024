package com.uca.entity;

public class Depart {

    private int noLigne;
    private String heure;
    private int noTrain;

    public Depart(int noLigne, String Heure, int noTrain) {
        this.noLigne = noLigne;
        this.heure = Heure;
        this.noTrain = noTrain;
    }

    public int getNoLigne() {
        return this.noLigne;
    }

    public String getHeure() {
        return this.heure;
    }

    public int getNoTrain() {
        return this.noTrain;
    }

    public void setNoLigne(int noLigne) {
        this.noLigne = noLigne;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public void setNoTrain(int noTrain) {
        this.noTrain = noTrain;
    }
    
}
