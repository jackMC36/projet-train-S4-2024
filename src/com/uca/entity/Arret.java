package com.uca.entity;

public class Arret {

    private int noLigne;
    private int rang;
    private String ville;
    private int chrono;

    public Arret(int noLigne, int rang, String ville, int chrono) {
        this.noLigne = noLigne;
        this.rang = rang;
        this.ville = ville;
        this.chrono = chrono;
    }

    public int getNoLigne() {
        return this.noLigne;
    }

    public int getRang() {
        return this.rang;
    }

    public String getVille() {
        return this.ville;
    }

    public int getChrono() {
        return this.chrono;
    }

    public void setNoLigne(int noLigne) {
        this.noLigne = noLigne;
    }

    public void setRang(int rang) {
        this.rang = rang;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setChrono(int chrono) {
        this.chrono = chrono;
    }
    
}
