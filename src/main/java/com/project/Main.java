package com.project;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Main {
    public static void main(String[] args) {
        // Crear productos
        Producte p0 = new Producte(0, "Llibre");
        Producte p1 = new Producte(1, "Boli");
        Producte p2 = new Producte(2, "Rotulador");
        Producte p3 = new Producte(3, "Carpeta");
        Producte p4 = new Producte(4, "Motxilla");

        // Crear almacén y entregas
        Magatzem magatzem = new Magatzem();
        Entregues entregues = new Entregues();

        // Listener para cambios en los productos
        PropertyChangeListener productListener = (PropertyChangeEvent evt) -> {
            if ("producteId".equals(evt.getPropertyName())) {
                System.out.println("Producte ha canviat l'id de '" + evt.getOldValue() + "' a '" + evt.getNewValue() + "'");
            } else if ("producteName".equals(evt.getPropertyName())) {
                System.out.println("Producte ha canviat el nom de '" + evt.getOldValue() + "' a '" + evt.getNewValue() + "'");
            }
        };

        // Añadir listener a los productos
        // permite que esos objetos notifiquen a otros componentes cuando ocurren cambios en sus propiedades.
        p0.addPropertyChangeListener("producteId", productListener);
        p0.addPropertyChangeListener("producteName", productListener);
        p1.addPropertyChangeListener("producteId", productListener);
        p1.addPropertyChangeListener("producteName", productListener);
        p2.addPropertyChangeListener("producteId", productListener);
        p2.addPropertyChangeListener("producteName", productListener);
        p3.addPropertyChangeListener("producteId", productListener);
        p3.addPropertyChangeListener("producteName", productListener);
        p4.addPropertyChangeListener("producteId", productListener);
        p4.addPropertyChangeListener("producteName", productListener);

        // Listener para cambios en el almacén
        PropertyChangeListener magatzemListener = (PropertyChangeEvent evt) -> {
            if ("magatzemAdd".equals(evt.getPropertyName())) {
                System.out.println("S'ha afegit el producte amb id " + ((Producte) evt.getNewValue()).getProducteid() + " al magatzem.");
            } else if ("magatzemRemove".equals(evt.getPropertyName())) {
                System.out.println("S'ha esborrat el producte amb id " + ((Producte) evt.getOldValue()).getProducteid() + " del magatzem.");
            }
        };

        // Listener para cambios en las entregas
        PropertyChangeListener entreguesListener = (PropertyChangeEvent evt) -> {
            if ("entreguesAdd".equals(evt.getPropertyName())) {
                System.out.println("S'ha afegit el producte amb id " + ((Producte) evt.getNewValue()).getProducteid() + " a les entregues.");
            } else if ("entreguesRemove".equals(evt.getPropertyName())) {
                System.out.println("S'ha entregat el producte amb id " + ((Producte) evt.getOldValue()).getProducteid() + ".");
            }
        };

        // Realizar cambios en los productos
        p0.setId(5);
        p0.setNom("Llibreta");
        p1.setNom("Bolígraf");

        // Añadir productos al almacén
        magatzem.magatzemAdd(p0);
        magatzem.magatzemAdd(p1);
        magatzem.magatzemAdd(p2);
        magatzem.magatzemAdd(p3);
        magatzem.magatzemAdd(p4);

        // Eliminar productos del almacén
        magatzem.magatzemRemove(2, entregues);
        magatzem.magatzemRemove(3, entregues);
        magatzem.magatzemRemove(4, entregues);

        // Eliminar productos de las entregas
        entregues.entreguesRemove(2);
        entregues.entreguesRemove(3);

        // Mostrar el estado final de almacén y entregas
        System.out.println(magatzem);
        System.out.println(entregues);
    }
}
