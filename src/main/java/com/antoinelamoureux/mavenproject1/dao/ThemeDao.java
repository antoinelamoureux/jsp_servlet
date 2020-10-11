/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antoinelamoureux.mavenproject1.dao;

import com.antoinelamoureux.mavenproject1.beans.Theme;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class ThemeDao implements Dao<Theme> {
    private final Connection cnx;

    public ThemeDao(Connection con) {
        cnx = con;
    }
    
    @Override
    public List<Theme> liste() {
        
        List<Theme> listeThemes = new ArrayList();
        
        String sql = "SELECT * FROM themes";
        
        // Avec les 'try-with-ressources'
        // Les classes qui implantent l'interface autoCloseable sont acceptées comme ressources
        try(PreparedStatement ps = cnx.prepareStatement(sql);
	ResultSet rs = ps.executeQuery();
	){
	
	while(rs.next()) {
		Theme obj = new Theme();
                obj.setIdTheme(rs.getLong("id_theme")); // nom de la colonne
                obj.setLibelle(rs.getString("libelle"));				
		listeThemes.add(obj);		
	}
	
        } catch (SQLException e) {
        System.out.println(e.getMessage());
        }
        
        return listeThemes;
    }

    @Override
    public boolean insert(Theme obj) {
        String sql = "INSERT INTO themes (libelle) VALUES (?)";
        
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
    public Theme find(Long id) {
        Theme obj = new Theme();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String sql = "SELECT * FROM themes WHERE id_theme=?";
        
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
    public boolean update(Theme obj) {
        String sql = "SELECT * FROM themes WHERE id_theme=?";
        
        try(PreparedStatement ps = cnx.prepareStatement(sql);){

	ps.setString(1, obj.getLibelle());
        ps.setLong(2, obj.getIdTheme());
        
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
        String sql = "DELETE FROM themes WHERE id_theme=?";
        
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
