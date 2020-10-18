/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antoinelamoureux.mavenproject1.dao;

import com.antoinelamoureux.mavenproject1.beans.Tag;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class TagDao implements Dao<Tag> {
    private final Connection cnx;
    
    public TagDao(Connection con) {
        cnx = con;
    }

    @Override
    public List<Tag> liste() {
        
        List<Tag> listeTags = new ArrayList();
        
        String sql = "SELECT * FROM tags";
        
        // Avec les 'try-with-ressources'
        // Les classes qui implantent l'interface autoCloseable sont acceptées comme ressources
        try(PreparedStatement ps = cnx.prepareStatement(sql);
	ResultSet rs = ps.executeQuery();
	){
	
	while(rs.next()) {
		Tag obj = new Tag();
                obj.setIdTag(rs.getLong("id_tag")); // nom de la colonne
                obj.setLibelle(rs.getString("libelle"));				
		listeTags.add(obj);		
	}
	
        } catch (SQLException e) {
        System.out.println(e.getMessage());
        }
        
        return listeTags;
    }

    @Override
    public boolean insert(Tag obj) {
        String sql = "INSERT INTO tags (libelle) VALUES (?)";
        
        try(PreparedStatement ps = cnx.prepareStatement(sql);){

	ps.setString(1, obj.getLibelle());
        
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
    public Tag find(Long id) {
        Tag obj = new Tag();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String sql = "SELECT * FROM tags WHERE id_theme=?";
        
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
    public boolean update(Tag obj) {
        String sql = "SELECT * FROM tags WHERE id_theme=?";
        
        try(PreparedStatement ps = cnx.prepareStatement(sql);){

	ps.setString(1, obj.getLibelle());
        ps.setLong(2, obj.getIdTag());
        
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
        String sql = "DELETE FROM themes WHERE id_tag=?";
        
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
    
}
