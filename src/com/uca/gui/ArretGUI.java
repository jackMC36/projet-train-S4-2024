package com.uca.gui;

import freemarker.template.TemplateException;

import com.uca.entity.Arret;

import java.io.IOException;
import java.util.*;

/**
 * Cette classe permet de générer les pages HTML qui concernent les Arrets
 */
public class ArretGUI {

    // retourne la page HTML affichant les Arrets dans une liste
    public static String list(List<Arret> Arrets) throws IOException, TemplateException {
        Map<String, Object> input = new HashMap<>();
        input.put("title", "Arrets");
        input.put("arrets", Arrets);
        
        return AbstractGUI.callTemplate("arret.ftl", input);
    }

    // retourne la page du formulaire pour ajouter un Arret
    public static String add() throws IOException, TemplateException {
        Map<String, Object> input = new HashMap<>();
        input.put("title", "Ajouter un Arret");

        return AbstractGUI.callTemplate("arret-add.ftl", input);
    }

    public static String delete() throws IOException, TemplateException {
        Map<String, Object> input = new HashMap<>();
        input.put("title", "Supprimer un Arret");

        return AbstractGUI.callTemplate("arret-delete.ftl", input);
    }

}