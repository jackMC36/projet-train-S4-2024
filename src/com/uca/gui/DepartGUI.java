package com.uca.gui;

import freemarker.template.TemplateException;

import com.uca.entity.Depart;

import java.io.IOException;
import java.util.*;

/**
 * Cette classe permet de générer les pages HTML qui concernent les Departs
 */
public class DepartGUI {

    // retourne la page HTML affichant les Departs dans une liste
    public static String list(List<Depart> Departs) throws IOException, TemplateException {
        Map<String, Object> input = new HashMap<>();
        input.put("title", "Departs");
        input.put("departs", Departs);
        
        return AbstractGUI.callTemplate("depart.ftl", input);
    }

    // retourne la page du formulaire pour ajouter un Depart
    public static String add() throws IOException, TemplateException {
        Map<String, Object> input = new HashMap<>();
        input.put("title", "Ajouter un Depart");

        return AbstractGUI.callTemplate("depart-add.ftl", input);
    }

    public static String delete() throws IOException, TemplateException {
        Map<String, Object> input = new HashMap<>();
        input.put("title", "Supprimer un Depart");

        return AbstractGUI.callTemplate("depart-delete.ftl", input);
    }

    

}