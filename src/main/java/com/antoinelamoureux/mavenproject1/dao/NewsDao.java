/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antoinelamoureux.mavenproject1.dao;

import com.antoinelamoureux.mavenproject1.beans.News;
import com.antoinelamoureux.mavenproject1.beans.Tag;
import com.antoinelamoureux.mavenproject1.beans.Theme;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class NewsDao implements Dao<News>{
     private final Connection cnx;

    public NewsDao(Connection con) {
        cnx = con;
    }

    @Override
    public List<News> liste() {
        List<News> listeNews = new ArrayList();
        
        String sql = "SELECT * FROM news";
        
        // Avec les 'try-with-ressources'
        // Les classes qui implantent l'interface autoCloseable sont acceptées comme ressources
        try(PreparedStatement ps = cnx.prepareStatement(sql);
	ResultSet rs = ps.executeQuery();
	){
	
	while(rs.next()) {
		News obj = new News();
                
                obj.setIdNews(rs.getLong("id_news")); // nom de la colonne
                obj.setTitre(rs.getString("titre"));	
                obj.setContent(rs.getString("content"));
                //obj.setDatePub(rs.getDate("datepub").toLocalDate().atTime(LocalTime.now()));
                LocalDateTime datePub = rs.getTimestamp("date_pub").toLocalDateTime();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String formatDateTime = datePub.format(formatter);
                obj.setDatePub(formatDateTime);
                //obj.setTheme((Theme) rs.getObject("theme"));;
                obj.setTheme(getThemeForNews(rs.getLong("theme")));
                obj.setTags(getTagsForNews(rs.getLong("id_news")));
                System.out.println(obj.getTheme().getLibelle());
                System.out.println(obj);
		listeNews.add(obj);		
	}
	
        } catch (SQLException e) {
        System.out.println(e.getMessage());
        }
        
        return listeNews;
    }

    @Override
    public boolean insert(News obj) {
        String sql = "INSERT INTO news (titre, content, datePub, theme) VALUES (?)";
        
        try(PreparedStatement ps = cnx.prepareStatement(sql);){

	ps.setString(0, obj.getTitre());
        ps.setString(1, obj.getContent());
        //ps.setDate(2, java.sql.Date.valueOf(java.time.LocalDate.now()));
        ps.setTimestamp(2, java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()));
        ps.setLong(3, obj.getTheme().getIdTheme());
        
        int i = ps.executeUpdate();
        if (i == 1) {
            return true;
        }
        } catch (SQLException e) {
        System.out.println(e.getMessage());
        }
        
        return false;
    }

    @Override
    public News find(Long id) {
        News obj = new News();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String sql = "SELECT * FROM news WHERE id_news=?";
        
        try {
            if (rs != null && ps != null) {
                ps.executeQuery();
                ps.executeUpdate();
            }
        } catch (SQLException e) {
        System.out.println(e.getMessage());
        }
        return obj;
    }

    @Override
    public boolean update(News obj) {
        String sql = "SELECT * FROM news WHERE id_news=?";
        
        try(PreparedStatement ps = cnx.prepareStatement(sql);){

	ps.setString(1, obj.getTitre());
        ps.setString(2, obj.getContent());
        
        int i = ps.executeUpdate();
        if (i >= 1) {
            return true;
        }
        } catch (SQLException e) {
        System.out.println(e.getMessage());
        }
        
        return false;
    }

    @Override
    public void delete(Long id) {
         String sql = "DELETE FROM news WHERE id_news=?";
        
        try(PreparedStatement ps = cnx.prepareStatement(sql);){

	ps.setLong(1, id);
        ps.executeUpdate();
        
        } catch (SQLException e) {
        System.out.println(e.getMessage());
        }
    }

    @Override
    public long count() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private List<Tag> getTagsForNews(long idNews) {
        List<Tag> listeTags = new ArrayList();
        
        String sql = "SELECT * FROM tags INNER JOIN news_tags ON tags.id_tag = news_tags.tag INNERJOIN news ON news_tags.news = news.id_news WHERE id_news = ?";
        
        try(PreparedStatement ps = cnx.prepareStatement(sql); 
	ResultSet rs = ps.executeQuery();
	){
	ps.setLong(1, idNews);
        
	while(rs.next()) {
		Tag tag = new Tag();
                tag.setIdTag(rs.getLong("id_tag"));
                tag.setLibelle(rs.getString("libelle"));
		listeTags.add(tag);		
	}
	
        } catch (SQLException e) {
        System.out.println(e.getMessage());
        }
        
        return listeTags;
    }
    
    private Theme getThemeForNews(long idTheme) {
        Theme theme = new Theme(); 
        
        String sql = "SELECT * FROM themes ?";
        
        try(PreparedStatement ps = cnx.prepareStatement(sql); 
	ResultSet rs = ps.executeQuery();
	){
	ps.setLong(1, idTheme);
        
	while(rs.next()) {
            theme.setIdTheme(rs.getLong("id_theme"));
            theme.setLibelle(rs.getString("libelle"));  	
	}
	
        } catch (SQLException e) {
        System.out.println(e.getMessage());
        }
        
        return theme;
    }
    
}
