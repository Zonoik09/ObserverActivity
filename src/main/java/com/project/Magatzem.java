package com.project;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class Magatzem {
    private ArrayList<Producte> productes;
    private int capacitat;
    private PropertyChangeSupport support;

    public Magatzem() {
        this.productes = new ArrayList<>();
        this.capacitat = 10;
        this.support = new PropertyChangeSupport(this);
    }

    public ArrayList<Producte> getProductes() {
        return productes;
    }

    public void magatzemAdd(Producte producte) {
        if (productes.size() < capacitat) {
            productes.add(producte);
            support.firePropertyChange("magatzemAdd", null, producte);
            System.out.printf("S’ha afegit el producte amb id %d al magatzem, capacitat: %d%n", producte.getProducteid(), capacitat - productes.size());
        } else {
            System.out.println("No hi ha suficient capacitat al magatzem.");
        }
    }

    public void magatzemRemove(int id, Entregues entregues) {
        Producte producteToRemove = null;
        for (Producte producte : productes) {
            if (producte.getProducteid() == id) {
                producteToRemove = producte;
                break;
            }
        }
        if (producteToRemove != null) {
            productes.remove(producteToRemove);
            support.firePropertyChange("magatzemRemove", producteToRemove, null);
            entregues.entreguesAdd(producteToRemove);
            System.out.printf("S’ha mogut el producte amb id %d del magatzem cap a les entregues%n", producteToRemove.getProducteid());
        } else {
            System.out.printf("El producte amb id %d no es troba al magatzem.%n", id);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Magatzem{capacitat=").append(capacitat).append(", productes=[");
        for (Producte producte : productes) {
            sb.append(producte.toString()).append(", ");
        }
        // Remove the last comma and space if there are products
        if (!productes.isEmpty()) {
            sb.setLength(sb.length() - 2);
        }
        sb.append("]}");
        return sb.toString();
    }
}
