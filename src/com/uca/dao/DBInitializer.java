package com.uca.dao;

import java.sql.*;

public class DBInitializer {

    /**
     * cette méthode permet d'initialiser la BD en créant les tables manquantes
     */
    public static void init(){
        Connection connection = ConnectionPool.getConnection();
        PreparedStatement statement;
        
        try {
            if (!tableExists(connection, "TRAIN")) {
                    statement = connection.prepareStatement("CREATE TABLE TRAIN (NoTrain integer, Type text,primary key(noTrain));");
                    statement.executeUpdate();
                }

            if (!tableExists(connection, "LIGNE")) {
                    statement = connection.prepareStatement("CREATE TABLE LIGNE (NoLigne integer, Nom text, primary key(NoLigne));");
                    statement.executeUpdate();
                }

            if (!tableExists(connection, "ARRET")) {
                statement = connection.prepareStatement("CREATE TABLE ARRET (NoLigne integer, Rang integer, Ville text, Chrono integer, primary key(NoLigne, Rang), FOREIGN KEY(NoLigne) REFERENCES LIGNE(NoLigne));");
                statement.executeUpdate();
                }

            if (!tableExists(connection, "DEPART")) {
                    statement = connection.prepareStatement("CREATE TABLE DEPART (NoTrain integer, Heure text, NoLigne integer, primary key(NoTrain, Heure, NoLigne), FOREIGN KEY(NoTrain) REFERENCES TRAIN(NoTrain), FOREIGN KEY(NoLigne) REFERENCES LIGNE(NoLigne));");
                    statement.executeUpdate(); 
                }

            

            

            connection.commit();
            ConnectionPool.releaseConnection(connection);
            
        } catch (SQLException e){
            System.out.println(e.toString());
            try {
                connection.rollback();
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
            e.printStackTrace();
            throw new RuntimeException("!!!! impossible d'initialiser la base !!!!");
        }
    }

    /**
     * teste si une table existe dans la base de données 
     */
    private static boolean tableExists(Connection connection, String tableName) throws SQLException {
        DatabaseMetaData meta = connection.getMetaData();
        ResultSet resultSet = meta.getTables(null, null, tableName.toLowerCase(), new String[]{"TABLE"});
        return resultSet.next();
    }
}
