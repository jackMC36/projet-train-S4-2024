package com.uca.dao;

import com.uca.InvalidInputException;

import com.uca.entity.Depart;

import java.sql.*;
import java.util.*;

/**
 * Classe permet de modifier les Departs stockés dans la table Depart
 * Elle est un singleton, c'est à dire une classe qui n'a qu'une seule instance
 * Plus d'info : https://fr.wikipedia.org/wiki/Singleton_(patron_de_conception)
 */
public class DepartDAO extends AbstractDAO<Depart> {

    // l'unique instance de ce singleton 
    private final static DepartDAO INSTANCE = new DepartDAO();
    
    protected DepartDAO() {
        super("Depart");
    }

    // accès à l'instance de la classe
    public static DepartDAO getInstance() {
        return INSTANCE;
    }

    // implémentation de la méthode abstraite héritée
    // elle transforme un tuple du ResultSet en un objet Depart
    public Depart getFromResultSet(ResultSet rs) throws SQLException {
        return new Depart(rs.getInt("noDepart"), rs.getString("type"));
    }

    // insère un nouveau Depart
    public void add(Depart Depart) throws SQLException, InvalidInputException {
        Connection connection = ConnectionPool.getConnection();
        try {

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Depart(NoTrain, Heure, NoLigne) VALUES (?, ?);");
            preparedStatement.setInt(1, Depart.getNoDepart());
            preparedStatement.setString(2, Depart.getHeure());
            preparedStatement.setInt(3, Depart.getNoLigne());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            // dès qu'une exception SQL est levée, il faut annuler la transaction
            connection.rollback();

            // on récupère le code de l'état SQL
            // plus d'info : https://www.ibm.com/docs/en/i/7.4?topic=codes-listing-sqlstate-values
            String sqlState = ((SQLException)e).getSQLState();

            // dans le cas où c'est une erreur due à une violation de la clé primaire
            if (sqlState.equalsIgnoreCase("23505")) {
                String message = String.format("le Depart existe déjà");
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

    public static void delete(int NoLigne, String Heure, int NoTrain) throws SQLException {
        Connection connection = ConnectionPool.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Depart WHERE NoLigne =? AND Heure =? AND NoTrain =?;");
            preparedStatement.setInt(1, no);
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