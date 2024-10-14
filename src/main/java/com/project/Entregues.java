package com.project;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class Entregues {
    private ArrayList<Producte> productes;
    private PropertyChangeSupport support;

    public Entregues() {
        this.productes = new ArrayList<>();
        this.support = new PropertyChangeSupport(this);
    }

    public void entreguesAdd(Producte producte) {
        productes.add(producte);
        support.firePropertyChange("entreguesAdd", null, producte);
        System.out.printf("S’ha afegit el producte amb id %d a la llista d'entregues%n", producte.getProducteid());
    }

    public void entreguesRemove(int id) {
        Producte producteToRemove = null;
        for (Producte producte : productes) {
            if (producte.getProducteid() == id) {
                producteToRemove = producte;
                break;
            }
        }
        if (producteToRemove != null) {
            productes.remove(producteToRemove);
            support.firePropertyChange("entreguesRemove", producteToRemove, null);
            System.out.printf("S’ha entregat el producte amb id %d%n", producteToRemove.getProducteid());
        } else {
            System.out.printf("El producte amb id %d no es troba a les entregues.%n", id);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Entregues{productes=[");
        for (Producte producte : productes) {
            sb.append(producte.toString()).append(", ");
        }
        if (!productes.isEmpty()) {
            sb.setLength(sb.length() - 2); // Remueve la última coma y espacio
        }
        sb.append("]}");
        return sb.toString();
    }
}
