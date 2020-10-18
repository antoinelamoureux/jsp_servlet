/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antoinelamoureux.mavenproject1.beans;

import java.io.Serializable;

/**
 *
 * @author admin
 */
public class Tag implements Serializable {
    private long idTag;
    private String libelle; 
    
    public Tag() {
    }

    public long getIdTag() {
        return idTag;
    }

    public void setIdTag(long idTag) {
        this.idTag = idTag;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
