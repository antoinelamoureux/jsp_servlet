/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antoinelamoureux.mavenproject1.controllers;

import com.antoinelamoureux.mavenproject1.beans.News;
import com.antoinelamoureux.mavenproject1.dao.NewsDao;
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
@WebServlet(name = "NewsController", urlPatterns = {"/news"})
public class NewsController extends HttpServlet {
    private Connection cnx;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("NewsController::DOGET");
        
         // Une connexion JDBC 
        cnx = (Connection)getServletContext().getAttribute("connexion");
        
        // Un objet ThemeDao
        NewsDao dao = new NewsDao(cnx);
        News news = new News();
        List<News> listeNews = new ArrayList();
        Long id = -1L;
        
        String action = "0";
        
        // Présence d'un paramètre 'action' ?
        if(request.getParameter("action") != null){
            action = request.getParameter("action");      
        }
        
         String view = "";
     
        switch(action){
            
            case "0":
                listeNews = dao.liste();
 
                // On crée un attribut de requète 
                request.setAttribute("listeNews", listeNews);
                view = "news/liste.jsp";
            break;
            
            case "1":
                // On récupère les données du formulaire
                if (request.getParameter("titre") != null) {
                    news.setTitre(request.getParameter("titre"));
                }
                    System.out.println("titre" + news.getTitre());
                    
                // On insère en BD
                dao.insert(news);
                
                // On rafraichit la liste
                listeNews = dao.liste();
                request.setAttribute("listeNews", listeNews);
                
                // On forward
                view = "news/liste.jsp";
            break;
            
            case "2":
                if (request.getParameter("id") != null) news.setIdNews(Long.parseLong(request.getParameter("id")));
                if (request.getParameter("titre") != null) news.setTitre(request.getParameter("titre"));
                System.out.println("titre" + news.getTitre());
                dao.update(news);
                listeNews = dao.liste();
                request.setAttribute("listeNews", listeNews);
                view = "news/liste.jsp";
            break;
            
            case "3":
                if (request.getParameter("id") != null) dao.delete(Long.parseLong(request.getParameter("id")));
            
                System.out.println("news" + news.getTitre());
   
                listeNews = dao.liste();
                request.setAttribute("listeNews", listeNews);
                view = "news/liste.jsp";
            break;
            
            case "4":
                // On crée un object Theme
                // On récupère les données du formulaire
                if (request.getParameter("titre") != null) {
                    news.setTitre(request.getParameter("titre"));
                }
                    System.out.println("titre" + news.getTitre());
                    
                // On insère en BD
                dao.insert(news);
                
                // On rafraichit la liste
                listeNews = dao.liste();
                request.setAttribute("listeNews", listeNews);
                
                // On forward
                view = "news/liste.jsp";
            break;
        }
        
        // On forward la requète
        request.getRequestDispatcher(view).forward(request, response); 

        
        System.out.println(listeNews);
  
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
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
