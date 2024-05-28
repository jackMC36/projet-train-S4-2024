package com.uca;

import com.uca.InvalidInputException;

import com.uca.dao.*;
import com.uca.gui.*;
import com.uca.gui.*;

import com.uca.entity.*;

import com.google.gson.Gson;

import static spark.Spark.*;
import spark.*;

import com.uca.InvalidInputException;

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
                    no = Integer.valueOf(req.queryParams("NoTrain"));
                    type = req.queryParams("Type");
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
            String paramStr = req.queryParams("NoTrain");
            if (paramStr == null || paramStr.isEmpty()) {
                halt(400, "NoTrain parameter is missing"); 
            }
            int NoTrain = Integer.parseInt(paramStr);
            TrainDAO.delete(NoTrain);
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
            Integer NoLigne = 0;
            Integer Rang = 0;
            String Ville = "";
            Integer Chrono = 0;

            try {
                NoLigne = Integer.valueOf(req.queryParams("NoLigne"));
                Rang = Integer.valueOf(req.queryParams("Rang"));
                Ville = req.queryParams("Ville");
                Chrono = Integer.valueOf(req.queryParams("Chrono"));
            } catch (Exception e) {
                // en cas d'erreur de lecture des paramètre de la requête HTTP,
                // on retourne une erreur HTTP 400 
                halt(400, "un paramètre est manquant ou n'est pas au bon format (la ligne doit être creer avant)");
            }

            ArretDAO.getInstance().add(new Arret(NoLigne, Rang, Ville, Chrono));

            return ArretGUI.list(ArretDAO.getInstance().getAll());
        });

        post("arret/supprimer", (req, res) -> {
            int NoLigne = Integer.parseInt(req.queryParams("NoLigne"));
            int Rang = Integer.parseInt(req.queryParams("Rang"));
            int Chrono = Integer.parseInt(req.queryParams("Chrono"));
            String Ville = req.queryParams("Ville");
            ArretDAO.delete(NoLigne, Ville, Rang, Chrono);
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
                noLigne = Integer.valueOf(req.queryParams("NoLigne"));
                nom = req.queryParams("Nom");
            } catch (Exception e) {
                // en cas d'erreur de lecture des paramètre de la requête HTTP,
                // on retourne une erreur HTTP 400 
                halt(400, "un paramètre est manquant ou n'est pas au bon format");
            }

            LigneDAO.getInstance().add(new Ligne(noLigne, nom));
            return LigneGUI.list(LigneDAO.getInstance().getAll());
        });

        // requête pour la suppression d'une ligne
        post("ligne/supprimer", (req, res) -> {
            String noLigneStr = req.queryParams("NoLigne");
            if (noLigneStr == null || noLigneStr.isEmpty()) {
                halt(400, "NoLigne parameter is missing");
            }
            int NoLigne = Integer.parseInt(noLigneStr);
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
            Integer noLigne = 0;
            Integer noTrain = 0;
            String heure = "";

            try {
                noLigne = Integer.valueOf(req.queryParams("NoLigne"));
                noTrain = Integer.valueOf(req.queryParams("NoTrain"));
                heure = req.queryParams("Heure");
            } catch (Exception e) {
                // en cas d'erreur de lecture des paramètre de la requête HTTP,
                // on retourne une erreur HTTP 400 
                halt(400, "un paramètre est manquant ou n'est pas au bon format");
            }

            DepartDAO.getInstance().add(new Depart(noLigne, heure, noTrain));
            return DepartGUI.list(DepartDAO.getInstance().getAll());
        });
        
        // requête pour la suppression d'un depart
        post("depart/supprimer", (req, res) -> {
            int noLigne = Integer.parseInt(req.queryParams("NoLigne"));
            int noTrain = Integer.parseInt(req.queryParams("NoTrain"));
            String heure = req.queryParams("Heure");
            DepartDAO.delete(noLigne, heure, noTrain);
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

