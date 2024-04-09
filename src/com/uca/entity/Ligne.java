package src.com.uca.entity;

public class Ligne {
    private int no;
    private String nom;

    public Ligne(int no, String nom) {
        this.no = no;
        this.nom = nom;
    }

    public int getNo() {
        return this.no;
    }

    public String getNom() {
        return this.nom;
    }
}

