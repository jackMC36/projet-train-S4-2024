package com.uca.gui;

import freemarker.template.TemplateException;

import com.uca.entity.Train;

import java.io.IOException;
import java.util.*;

/**
 * Cette classe permet de générer les pages HTML qui concernent les trains
 */
public class LigneGUI {

    // retourne la page HTML affichant les lignes dans une liste
    public static String list(List<ligne> lignes) throws IOException, TemplateException {
        Map<String, Object> input = new HashMap<>();
        input.put("title", "Lignes");
        input.put("Lignes", lignes);
        
        return AbstractGUI.callTemplate("ligne.ftl", input);
    }

    // retourne la page du formulaire pour ajouter un train
    public static String add() throws IOException, TemplateException {
        Map<String, Object> input = new HashMap<>();
        input.put("title", "Ajouter une ligne");

        return AbstractGUI.callTemplate("ligne-add.ftl", input);
    }

}
