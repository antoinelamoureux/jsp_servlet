/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antoinelamoureux.mavenproject1.controllers;

import com.antoinelamoureux.mavenproject1.beans.Theme;
import com.antoinelamoureux.mavenproject1.dao.ThemeDao;
import com.antoinelamoureux.mavenproject1.utils.ConnexionFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author admin
 */
@WebServlet(name = "ThemesController", urlPatterns = {"/themes"})
public class ThemesController extends HttpServlet {
    private Connection cnx;
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        System.out.println("ThemesController::DOGET");
        
        // Une connexion JDBC 
        //cnx = (Connection)getServletContext().getAttribute("connexion");
        ConnexionFactory connection = new ConnexionFactory();
        try {
            cnx = DriverManager.getConnection(connection.getDbUrl(), connection.getDbUser(), connection.getDbPass());
        } catch (SQLException ex) {
            Logger.getLogger(ThemesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Un objet ThemeDao
        ThemeDao dao = new ThemeDao(cnx);
        System.out.println("*******CONNECTION*********" + cnx);
        Theme theme = new Theme();
        Long id = -1L;
        
        String action = "0";
        
        // Présence d'un paramètre 'action' ?
        if(request.getParameter("action") != null){
            action = request.getParameter("action");      
        }
        request.setAttribute("action", action);
        String view = "";
     
        switch(action){
            // Listing
            case "0":
                List<Theme> listeThemes = new ArrayList();
                listeThemes = dao.liste();
       
                // On crée un attribut de requète 
                request.setAttribute("listeThemes", listeThemes);
                view = "themes/liste.jsp";
            break;
            //Création
            case "1":
                view = "themes/create.jsp";
            break;
            // Modification
            case "2":
                if (request.getParameter("id") != null) id = Long.parseLong(request.getParameter("id"));
                theme = dao.find(id);
                request.setAttribute("theme", theme);
                view = "themes/modify.jsp";
            break;
            // Suppression
            case "3":
                if (request.getParameter("id") != null) id = Long.parseLong(request.getParameter("id"));
                theme = dao.find(id);
                request.setAttribute("theme", theme);
                view = "themes/delete.jsp";
            break;  
            // Duplication
              case "4":
                // Vers le formulaire comme pour modify. 
                 /*
                if (request.getParameter("id") != null) id = Long.parseLong(request.getParameter("id"));
                theme = dao.find(id);
                request.setAttribute("theme", theme);
                view = "themes/delete.jsp";
                */
            break;  
        }
        
        // On forward la requète
        request.getRequestDispatcher(view).forward(request, response);       
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        System.out.println("ThemesController::DOPOST");
        
         // Une connexion JDBC 
        cnx = (Connection)getServletContext().getAttribute("connexion");
        
        // Un objet ThemeDao
        ThemeDao dao = new ThemeDao(cnx);
        Theme theme = new Theme();
        List<Theme> listeThemes = new ArrayList();
        String action = "";
        
        // Présence d'un paramètre 'action' ?
        if(request.getParameter("action") != null){
            action = request.getParameter("action");      
        }
        
         String view = "";
     
        switch(action){
            
            case "0":
                listeThemes = dao.liste();
 
                // On crée un attribut de requète 
                request.setAttribute("listeThemes", listeThemes);
                view = "themes/liste.jsp";
            break;
            
            case "1":
                // On récupère les données du formulaire
                if (request.getParameter("libelle") != null) {
                    theme.setLibelle(request.getParameter("libelle"));
                }
                    System.out.println("libelle" + theme.getLibelle());
                    
                // On insère en BD
                dao.insert(theme);
                
                // On rafraichit la liste
                listeThemes = dao.liste();
                request.setAttribute("listeThemes", listeThemes);
                
                // On forward
                view = "themes/liste.jsp";
            break;
            
            case "2":
                if (request.getParameter("id") != null) theme.setIdTheme(Long.parseLong(request.getParameter("id")));
                if (request.getParameter("libbelle") != null) theme.setLibelle(request.getParameter("libelle"));
                System.out.println("libelle" + theme.getLibelle());
                dao.update(theme);
                listeThemes = dao.liste();
                request.setAttribute("listeThemes", listeThemes);
                view = "themes/liste.jsp";
            break;
            
            case "3":
                if (request.getParameter("id") != null) dao.delete(Long.parseLong(request.getParameter("id")));
            
                System.out.println("libelle" + theme.getLibelle());
   
                listeThemes = dao.liste();
                request.setAttribute("listeThemes", listeThemes);
                view = "themes/liste.jsp";
            break;
            
            case "4":
                // On crée un object Theme
                // On récupère les données du formulaire
                if (request.getParameter("libelle") != null) {
                    theme.setLibelle(request.getParameter("libelle"));
                }
                    System.out.println("libelle" + theme.getLibelle());
                    
                // On insère en BD
                dao.insert(theme);
                
                // On rafraichit la liste
                listeThemes = dao.liste();
                request.setAttribute("listeThemes", listeThemes);
                
                // On forward
                view = "themes/liste.jsp";
            break;
        }
        
        // On forward la requète
        request.getRequestDispatcher(view).forward(request, response); 

        
        System.out.println(listeThemes);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

// Valuers pour les actions
// 0 ==> liste
// 1 ==> création
// 2 ==> modification
// 3 ==> supression

