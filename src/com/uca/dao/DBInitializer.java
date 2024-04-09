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
                statement = connection.prepareStatement("CREATE TABLE TRAIN (NoTrain integer CHECK (NoTrain >= 0001 AND NoTrain <= 9999), Type text CHECK (Type='TER' OR Type='TGV' OR Type='INTERCITE'), primary key(NoTrain));");
                statement.executeUpdate();
            }

            if (!tableExists(connection, "LIGNE")) {
                statement = connection.prepareStatement("CREATE TABLE LIGNE (NoLigne integer CHECK (NoLigne >= 0000), Nom text, primary key(NoLigne));");
                statement.executeUpdate();
            }

            if (!tableExists(connection, "DEPART")) {
                statement = connection.prepareStatement("CREATE TABLE DEPART (NoLigne integer CHECK (NoLigne >= 0000), Heure integer CHECK (NoLigne >= 0), NoTrain integer CHECK (NoTrain >= 0001 AND NoTrain <= 9999), primary key(NoLigne, Heure, NoTrain), foreign key(NoTrain) references TRAIN(NoTrain), foreign key(NoLigne) references LIGNE(NoLigne));");
                statement.executeUpdate();
            }

            if(!tableExists(connection, "ARRET")) {
                statement = connection.prepareStatement("CREATE TABLE ARRET (NoLigne integer CHECK (NoLigne >= 0000), Rang integer CHECK (Rang >= 0), Ville text, Chrono integer CHECK (NoLigne >= 0), primary key(NoLigne,Rang), foreign key(NoLigne) references LIGNE(NoLigne));");
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
