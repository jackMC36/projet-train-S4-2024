package com.uca.entity;

public class Depart {

    private int NoLigne;
    private String Heure;
    private int NoTrain;

    public Depart(int NoLigne, String Heure, int NoTrain) {
        this.NoLigne = NoLigne;
        this.Heure = Heure;
        this.NoTrain = NoTrain;
    }

    public int getNoLigne() {
        return this.NoLigne;
    }

    public String getHeure() {
        return this.Heure;
    }

    public int getNoTrain() {
        return this.NoTrain;
    }

    public void setNoLigne(int NoLigne) {
        this.NoLigne = NoLigne;
    }

    public void setHeure(String Heure) {
        this.Heure = Heure;
    }

    public void setNoTrain(int NoTrain) {
        this.NoTrain = NoTrain;
    }

    @Override
    public String toString() {
        return "Depart{" + "NoLigne=" + NoLigne + ", Heure=" + Heure + ", NoTrain=" + NoTrain + '}';
    }
    
}
