package com.uca;

import com.uca.InvalidInputException;

import com.uca.dao.*;
import com.uca.gui.*;
import com.uca.gui.*;

import com.uca.entity.*;

import com.google.gson.Gson;

import static spark.Spark.*;
import spark.*;
import com.uca.dao.ConnectionPool;
import com.uca.dao.TrainDAO;
import com.uca.entity.Train;
import com.uca.gui.TrainGUI;
import com.uca.InvalidInputException;
import com.uca.dao.DBInitializer;
import com.uca.dao.LigneDAO;
import com.uca.entity.Ligne;
import com.uca.dao.DepartDAO;
import com.uca.entity.Depart;
import com.uca.gui.ArretGUI;
import com.uca.gui.LigneGUI;
import com.uca.gui.DepartGUI;

public class StartServer {

    /**
     * Classe principale du serveur Spark
     * pour plus d'info : http://sparkjava.com/documentation
     */
    public static void main(String[] args) {

        //Configuration de Spark
        staticFiles.location("/static/");
        port(8081);

        // initialisation du pool de connections
        ConnectionPool.init();
        
        // Création de la base de données, si besoin
        DBInitializer.init();

        /**
         * Définition des routes
         */

        // index de l'application
        get("/", (req, res) -> {
                return IndexGUI.getIndex();
            });



        // page listant les trains
        get("/train", (req, res) -> {
                return TrainGUI.list(TrainDAO.getInstance().getAll());
            });

        // page du formulaire pour l'ajout d'un nouveau train
        get("/train/ajout", (req, res) -> {
            return TrainGUI.add();
                });

        // requête pour l'ajout d'un nouveau train
        post("/train", (req, res) -> {
                Integer no = 0;
                String type = "";

                try {
                    no = Integer.valueOf(req.queryParams("no"));
                    type = req.queryParams("type");
                } catch (Exception e) {
                    // en cas d'erreur de lecture des paramètre de la requête HTTP,
                    // on retourne une erreur HTTP 400 
                    halt(400, "un paramètre est manquant ou n'est pas au bon format");
                }

                TrainDAO.getInstance().add(new Train(no, type));

                return TrainGUI.list(TrainDAO.getInstance().getAll());
            });

        // requête pour la suppression d'un train 
        post("/train/supprimer", (req, res) -> {
                int no = Integer.parseInt(req.queryParams("no"));
                TrainDAO.delete(no);
                // une fois le train supprimé,
                // on redirige le client sur la page listant les trains avec une redirection temporaire
                res.redirect("/train");
                return "";
            });

            
        
        // page listant les arrets
        get("/arret", (req, res) -> {
            return ArretGUI.list(ArretDAO.getInstance().getAll());
            });

        // page du formulaire pour l'ajout d'un nouvel arret
        get("/arret/ajout", (req, res) -> {
            return ArretGUI.add();
            });


        // requête pour l'ajout d'un nouvel arret
        post("arret", (req, res) -> {
            Integer noLigne = 0;
            Integer rang = 0;
            String ville = "";
            Integer chrono = 0;

            try {
                noLigne = Integer.valueOf(req.queryParams("NoLigne"));
                rang = Integer.valueOf(req.queryParams("rang"));
                ville = req.queryParams("Ville");
                chrono = Integer.valueOf(req.queryParams("Chrono"));
            } catch (Exception e) {
                // en cas d'erreur de lecture des paramètre de la requête HTTP,
                // on retourne une erreur HTTP 400 
                halt(400, "un paramètre est manquant ou n'est pas au bon format");
            }

            ArretDAO.getInstance().add(new Arret(noLigne, rang, ville, chrono));

            return ArretGUI.list(ArretDAO.getInstance().getAll());
        });

        post("arret/supprimer", (req, res) -> {
            int noLigne = Integer.parseInt(req.queryParams("noLigne"));
            int rang = Integer.parseInt(req.queryParams("rang"));
            int chrono = Integer.parseInt(req.queryParams("chrono"));
            String ville = req.queryParams("ville");
            ArretDAO.delete(noLigne, ville, rang, chrono);
            // une fois l'arret supprimé,
            // on redirige le client sur la page listant les arrets avec une redirection temporaire
            res.redirect("/arret");
            return "";
        });


        
        // page listant les lignes
        get("ligne", (req, res) -> {
            return LigneGUI.list(LigneDAO.getInstance().getAll());
            });

        // page du formulaire pour l'ajout d'une nouvelle ligne
        get("ligne/ajout", (req, res) -> {
            return LigneGUI.add();
            });

        // requête pour l'ajout d'une nouvelle ligne
        post("ligne", (req, res) -> {
            Integer noLigne = 0;
            String nom = "";

            try {
                NoLigne = Integer.valueOf(req.queryParams("NoLigne"));
                Nom = req.queryParams("Nom");
            } catch (Exception e) {
                // en cas d'erreur de lecture des paramètre de la requête HTTP,
                // on retourne une erreur HTTP 400 
                halt(400, "un paramètre est manquant ou n'est pas au bon format");
            }

            LigneDAO.getInstance().add(new Ligne(NoLigne, Nom));
            return LigneGUI.list(LigneDAO.getInstance().getAll());
        });

        // requête pour la suppression d'une ligne
        post("ligne/supprimer", (req, res) -> {
            int NoLigne = Integer.parseInt(req.queryParams("NoLigne"));
            LigneDAO.delete(NoLigne);
            // une fois la ligne supprimée,
            // on redirige le client sur la page listant les lignes avec une redirection temporaire
            res.redirect("/ligne");
            return "";
        });
        
        // page listant les departs
        get("depart", (req, res) -> {
            return DepartGUI.list(DepartDAO.getInstance().getAll());
            });

        // page du formulaire pour l'ajout d'un nouveau depart
        get("depart/ajout", (req, res) -> {
            return DepartGUI.add();
            });

        // requête pour l'ajout d'un nouveau depart
        post("depart", (req, res) -> {
            Integer NoLigne = 0;
            Integer NoTrain = 0;
            String DateDepart = "";
            String HeureDepart = "";
            Integer NoArret = 0;

            try {
                NoLigne = Integer.valueOf(req.queryParams("NoLigne"));
                NoTrain = Integer.valueOf(req.queryParams("NoTrain"));
                DateDepart = req.queryParams("DateDepart");
                HeureDepart = req.queryParams("HeureDepart");
                NoArret = Integer.valueOf(req.queryParams("NoArret"));
            } catch (Exception e) {
                // en cas d'erreur de lecture des paramètre de la requête HTTP,
                // on retourne une erreur HTTP 400 
                halt(400, "un paramètre est manquant ou n'est pas au bon format");
            }

            DepartDAO.getInstance().add(new Depart(NoLigne, NoTrain, DateDepart, HeureDepart, NoArret));

            return DepartGUI.list(DepartDAO.getInstance().getAll());
        });
        
        // requête pour la suppression d'un depart
        post("depart/supprimer", (req, res) -> {
            int NoLigne = Integer.parseInt(req.queryParams("noLigne"));
            int NoTrain = Integer.parseInt(req.queryParams("noTrain"));
            String Heure = req.queryParams("heure");
            DepartDAO.delete(NoLigne, NoTrain, Heure);
            // une fois le depart supprimé,
            // on redirige le client sur la page listant les departs avec une redirection temporaire
            res.redirect("/depart");
            return "";
        });




        

        

        // gestion des exceptions InvalidInputException
        // Cette exception est levée quand un paramètre d'une requête est invalide
        exception(InvalidInputException.class, (exception, req, res) -> {
                // On retourne le code HTTP 400 signifiant une erreur dans la requête du client
                // plus d'info : https://fr.wikipedia.org/wiki/Liste_des_codes_HTTP
                res.status(400);

                // On précise ici le corps de la page retournée
                // avec le message stocké dans l'exception
                res.body("Invalid input: " + exception.getMessage());
            });
        }
    }

