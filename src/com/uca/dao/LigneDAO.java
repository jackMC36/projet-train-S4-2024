package com.uca.dao;

import com.uca.InvalidInputException;

import com.uca.entity.Ligne;

import java.sql.*;
import java.util.*;

/**
 * Classe permet de modifier les Lignes stockés dans la table Ligne
 * Elle est un singleton, c'est à dire une classe qui n'a qu'une seule instance
 * Plus d'info : https://fr.wikipedia.org/wiki/Singleton_(patron_de_conception)
 */
public class LigneDAO extends AbstractDAO<Ligne> {

    // l'unique instance de ce singleton 
    private final static LigneDAO INSTANCE = new LigneDAO();
    
    protected LigneDAO() {
        super("Ligne");
    }

    // accès à l'instance de la classe
    public static LigneDAO getInstance() {
        return INSTANCE;
    }

    // implémentation de la méthode abstraite héritée
    // elle transforme un tuple du ResultSet en un objet Ligne
    public Ligne getFromResultSet(ResultSet rs) throws SQLException {
        return new Ligne(rs.getInt("NoLigne"), rs.getString("Nom"));
    }

    // insère un nouveau Ligne
    public void add(Ligne Ligne) throws SQLException, InvalidInputException {
        Connection connection = ConnectionPool.getConnection();
        try {

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Ligne(NoLigne, Nom) VALUES (?, ?);");
            preparedStatement.setInt(1, Ligne.getNoLigne());
            preparedStatement.setString(2, Ligne.getNom());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            // dès qu'une exception SQL est levée, il faut annuler la transaction
            connection.rollback();

            // on récupère le code de l'état SQL
            // plus d'info : https://www.ibm.com/docs/en/i/7.4?topic=codes-listing-sqlstate-values
            String sqlState = ((SQLException)e).getSQLState();

            // dans le cas où c'est une erreur due à une violation de la clé primaire
            if (sqlState.equalsIgnoreCase("23505")) {
                String message = String.format("le Ligne de numéro %s existe déjà", Ligne.getNoLigne());
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

    public static void delete(int no) throws SQLException {
        Connection connection = ConnectionPool.getConnection();

        try {
            PreparedStatement preparedStatement1 = connection.prepareStatement("DELETE FROM Arret WHERE NoLigne =?;");
            PreparedStatement preparedStatement2 = connection.prepareStatement("DELETE FROM Ligne WHERE NoLigne =?;");
            preparedStatement1.setInt(1, no);
            preparedStatement1.executeUpdate();
            preparedStatement2.setInt(1, no);
            preparedStatement2.executeUpdate();
            

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
