package com.uca.dao;

import com.uca.InvalidInputException;

import com.uca.entity.Arret;

import java.sql.*;
import java.util.*;

/**
 * Classe permet de modifier les Arrets stockés dans la table Arret
 * Elle est un singleton, c'est à dire une classe qui n'a qu'une seule instance
 * Plus d'info : https://fr.wikipedia.org/wiki/Singleton_(patron_de_conception)
 */
public class ArretDAO extends AbstractDAO<Arret> {

    // l'unique instance de ce singleton 
    private final static ArretDAO INSTANCE = new ArretDAO();
    
    protected ArretDAO() {
        super("Arret");
    }

    // accès à l'instance de la classe
    public static ArretDAO getInstance() {
        return INSTANCE;
    }

    // implémentation de la méthode abstraite héritée
    // elle transforme un tuple du ResultSet en un objet Arret
    public Arret getFromResultSet(ResultSet rs) throws SQLException {
        return new Arret(rs.getInt("NoLigne"), rs.getInt("Rang"), rs.getString("Ville"), rs.getInt("Chrono"));
    }

    // insère un nouveau Arret
    public void add(Arret Arret) throws SQLException, InvalidInputException {
        Connection connection = ConnectionPool.getConnection();
        try {

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Arret(NoLigne, Rang, Ville, Chrono) VALUES (?, ?, ?, ?)0;");
            preparedStatement.setInt(1, Arret.getNoLigne());
            preparedStatement.setInt(2, Arret.getRang());
            preparedStatement.setString(3, Arret.getVille());
            preparedStatement.setInt(4, Arret.getChrono());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            // dès qu'une exception SQL est levée, il faut annuler la transaction
            connection.rollback();

            // on récupère le code de l'état SQL
            // plus d'info : https://www.ibm.com/docs/en/i/7.4?topic=codes-listing-sqlstate-values
            String sqlState = ((SQLException)e).getSQLState();

            // dans le cas où c'est une erreur due à une violation de la clé primaire
            if (sqlState.equalsIgnoreCase("23505")) {
                String message = String.format("le Arret existe déjà");
                throw new InvalidInputException(message);
            }

            // sinon propager l'erreur
            // pour le moment, il n'y a pas d'autre cas à gérer
            // (pas de pb de clé étrangère, ni de sérialisabilité possible) 
            throw e;
        }
        connection.commit();
        ConnectionPool.releaseConnection(connection);
    }

    public static void delete(int noLigne, String Ville, int Rang, int Chrono) throws SQLException {
        Connection connection = ConnectionPool.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Arret WHERE NoLigne =? AND Ville =? AND Rang =? AND Chrono =?;");
            preparedStatement.setInt(1, noLigne);
            preparedStatement.setString(2, Ville);
            preparedStatement.setInt(3, Rang);
            preparedStatement.setInt(4, Chrono);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            // dès qu'une exception SQL est levée, il faut annuler la transaction
            connection.rollback();
            // on propage l'exception
            throw e;
        }
        
        connection.commit();
        ConnectionPool.releaseConnection(connection);
    }

}
