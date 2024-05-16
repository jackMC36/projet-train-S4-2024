package com.uca.gui;

import freemarker.template.TemplateException;

import com.uca.entity.Ligne;

import java.io.IOException;
import java.util.*;

/**
 * Cette classe permet de générer les pages HTML qui concernent les Lignes
 */
public class LigneGUI {

    // retourne la page HTML affichant les Lignes dans une liste
    public static String list(List<Ligne> Lignes) throws IOException, TemplateException {
        Map<String, Object> input = new HashMap<>();
        input.put("title", "Lignes");
        input.put("lignes", Lignes);
        
        return AbstractGUI.callTemplate("ligne.ftl", input);
    }

    // retourne la page du formulaire pour ajouter un Ligne
    public static String add() throws IOException, TemplateException {
        Map<String, Object> input = new HashMap<>();
        input.put("title", "Ajouter un Ligne");

        return AbstractGUI.callTemplate("ligne-add.ftl", input);
    }

    public static String delete() throws IOException, TemplateException {
        Map<String, Object> input = new HashMap<>();
        input.put("title", "Supprimer un Ligne");

        return AbstractGUI.callTemplate("ligne-delete.ftl", input);
    }

}