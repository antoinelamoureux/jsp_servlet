/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antoinelamoureux.mavenproject1.beans;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class News implements Serializable {
    private long idNews;
    private String titre;
    private String content;
    private String datePub;
    private Theme theme;
    private List<Tag> tags;
    
    public News() {
        this.theme = new Theme();
        this.tags = new ArrayList();
        
    }

    public long getIdNews() {
        return idNews;
    }

    public void setIdNews(long idNews) {
        this.idNews = idNews;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDatePub() {
        return datePub;
    }

    public void setDatePub(String datePub) {
        this.datePub = datePub;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "News{" + "theme=" + theme + '}';
    }  
}
