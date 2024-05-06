package com.uca.entity;

public class Ligne {

    private int NoLigne;
    private String Nom;

    public Ligne(int NoLigne, String Nom) {
        this.NoLigne = NoLigne;
        this.Nom = Nom;
    }

    public int getNoLigne() {
        return this.NoLigne;
    }

    public String getNom() {
        return this.Nom;
    }

    public void setNoLigne(int NoLigne) {
        this.NoLigne = NoLigne;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    @Override
    public String toString() {
        return "Ligne{" + "NoLigne=" + NoLigne + ", NomLigne=" + Nom + '}';
    }
    
}




