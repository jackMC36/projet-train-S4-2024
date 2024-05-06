package com.uca.entity;

public class Arret {

    private int NoLigne;
    private int rang;
    private String Ville;
    private int Chrono;

    public Arret(int NoLigne, int rang, String Ville, int Chrono) {
        this.NoLigne = NoLigne;
        this.rang = rang;
        this.Ville = Ville;
        this.Chrono = Chrono;
    }

    public int getNoLigne() {
        return this.NoLigne;
    }

    public int getRang() {
        return this.rang;
    }

    public String getVille() {
        return this.Ville;
    }

    public int getChrono() {
        return this.Chrono;
    }

    public void setNoLigne(int NoLigne) {
        this.NoLigne = NoLigne;
    }

    public void setRang(int rang) {
        this.rang = rang;
    }

    public void setVille(String Ville) {
        this.Ville = Ville;
    }

    public void setChrono(int Chrono) {
        this.Chrono = Chrono;
    }

    @Override
    public String toString() {
        return "Arret{" + "NoLigne=" + NoLigne + ", rang=" + rang + ", Ville=" + Ville + ", Chrono=" + Chrono + '}';
    }


    
}
