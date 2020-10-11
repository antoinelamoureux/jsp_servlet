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
public class Theme implements Serializable{
    private long idTheme;
    private String libelle;

    public Theme() {
    }

    public long getIdTheme() {
        return idTheme;
    }

    public void setIdTheme(long idTheme) {
        this.idTheme = idTheme;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    
    
}
