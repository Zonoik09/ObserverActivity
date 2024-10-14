package com.project;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Producte {
    private int producteid;
    private String producteName;
    private PropertyChangeSupport support;

    public Producte(int id, String nom) {
        this.producteid = id;
        this.producteName = nom;
        support = new PropertyChangeSupport(this);
    }

    public void setId(int newId) {
        int oldId = this.producteid;
        this.producteid = newId;
        support.firePropertyChange("producteId", oldId, newId);
    }

    public void setNom(String newName) {
        String oldName = this.producteName;
        this.producteName = newName;
        support.firePropertyChange("producteName", oldName, newName);
    }

    public String getProducteName() {
        return producteName;
    }

    public int getProducteid() {
        return producteid;
    }

    public void addPropertyChangeListener(String name, PropertyChangeListener listener) {
        support.addPropertyChangeListener(name, listener);
    }

    public void removePropertyChangeListener(String name, PropertyChangeListener listener) {
        support.removePropertyChangeListener(name, listener);
    }

    @Override
    public String toString() {
        return "Producte{id=" + producteid + ", nom='" + producteName + "'}";
    }
}
