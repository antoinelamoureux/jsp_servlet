/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antoinelamoureux.mavenproject1.controllers;

import com.antoinelamoureux.mavenproject1.beans.Tag;
import com.antoinelamoureux.mavenproject1.dao.TagDao;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author admin
 */
@WebServlet(name = "TagsController", urlPatterns = {"/tags"})
public class TagsController extends HttpServlet {
    private Connection cnx;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       System.out.println("ThemesController::DOGET");
        
        // Une connexion JDBC 
        cnx = (Connection)getServletContext().getAttribute("connexion");
        
        // Un objet ThemeDao
        TagDao dao = new TagDao(cnx);
        System.out.println("*******CONNECTION*********" + cnx);
        Tag tag = new Tag();
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
                List<Tag> listeTags = new ArrayList();
                listeTags = dao.liste();
       
                // On crée un attribut de requète 
                request.setAttribute("listeTags", listeTags);
                view = "tags/liste.jsp";
            break;
            //Création
            case "1":
                view = "tags/create.jsp";
            break;
            // Modification
            case "2":
                if (request.getParameter("id") != null) id = Long.parseLong(request.getParameter("id"));
                tag = dao.find(id);
                request.setAttribute("tag", tag);
                view = "tags/modify.jsp";
            break;
            // Suppression
            case "3":
                if (request.getParameter("id") != null) id = Long.parseLong(request.getParameter("id"));
                tag = dao.find(id);
                request.setAttribute("tag", tag);
                view = "tags/delete.jsp";
            break;  
        }
        
        // On forward la requète
        request.getRequestDispatcher(view).forward(request, response);  
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       System.out.println("ThemesController::DOPOST");
        
         // Une connexion JDBC 
        cnx = (Connection)getServletContext().getAttribute("connexion");
        
        // Un objet ThemeDao
        TagDao dao = new TagDao(cnx);
        Tag tag = new Tag();
        List<Tag> listeTags = new ArrayList();
        String action = "";
        
        // Présence d'un paramètre 'action' ?
        if(request.getParameter("action") != null){
            action = request.getParameter("action");      
        }
        
         String view = "";
     
        switch(action){
            
            case "0":
                listeTags = dao.liste();
 
                // On crée un attribut de requète 
                request.setAttribute("listeTags", listeTags);
                view = "tags/liste.jsp";
            break;
            
            case "1":
                // On récupère les données du formulaire
                if (request.getParameter("libelle") != null) {
                    tag.setLibelle(request.getParameter("libelle"));
                }
                    System.out.println("libelle" + tag.getLibelle());
                    
                // On insère en BD
                dao.insert(tag);
                
                // On rafraichit la liste
                listeTags = dao.liste();
                request.setAttribute("listeTags", listeTags);
                
                // On forward
                view = "tags/liste.jsp";
            break;
            
            case "2":
                if (request.getParameter("id") != null) tag.setIdTag(Long.parseLong(request.getParameter("id")));
                if (request.getParameter("libbelle") != null) tag.setLibelle(request.getParameter("libelle"));
                System.out.println("libelle" + tag.getLibelle());
                dao.update(tag);
                listeTags = dao.liste();
                request.setAttribute("listeTags", listeTags);
                view = "tags/liste.jsp";
            break;
            
            case "3":
                if (request.getParameter("id") != null) dao.delete(Long.parseLong(request.getParameter("id")));
            
                System.out.println("libelle" + tag.getLibelle());
   
                listeTags = dao.liste();
                request.setAttribute("listeTags", listeTags);
                view = "tags/liste.jsp";
            break;
            
            case "4":
                // On crée un object Theme
                // On récupère les données du formulaire
                if (request.getParameter("libelle") != null) {
                    tag.setLibelle(request.getParameter("libelle"));
                }
                    System.out.println("libelle" + tag.getLibelle());
                    
                // On insère en BD
                dao.insert(tag);
                
                // On rafraichit la liste
                listeTags = dao.liste();
                request.setAttribute("listeTags", listeTags);
                
                // On forward
                view = "tags/liste.jsp";
            break;
        }
        
        // On forward la requète
        request.getRequestDispatcher(view).forward(request, response); 

        
        System.out.println(listeTags);
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
