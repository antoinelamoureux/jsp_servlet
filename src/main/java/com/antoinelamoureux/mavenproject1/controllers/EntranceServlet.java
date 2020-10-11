package com.antoinelamoureux.mavenproject1.controllers;

import com.antoinelamoureux.mavenproject1.utils.ConnexionFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author antoine
 */
public class EntranceServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("EntranceServlet :: INIT");
        
        // Important !
        super.init(config);
        
        // On instancie le driver
        try {
            Class.forName(config.getInitParameter("driver")).newInstance();
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException ==> " + ex.getMessage());
        } catch (InstantiationException ex) {
            System.out.println("InstantiationException ==> " + ex.getMessage());
        } catch (IllegalAccessException ex) {
             System.out.println("IllegalAccessException ==> " + ex.getMessage());
        }
        
        // On instancie une ConnexionFactory
        ConnexionFactory cnxFactory = new ConnexionFactory();
        // On nourrit l'objet
        cnxFactory.setDbUser(config.getInitParameter("user"));
        cnxFactory.setDbPass(config.getInitParameter("pass"));
        cnxFactory.setDbUrl(config.getInitParameter("url"));
        
        // On instancie une Connection
        Connection cnx = cnxFactory.getConnexion();
        System.out.println("CNX ================> " + cnx);
        
        // On crée un attribut d'application
        getServletContext().setAttribute("connexion", cnx);
        System.out.println(getServletContext().getAttribute("connexion"));
             
    }


//------------------------------------------------------------------------
//------------------------------------------------------------------------
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // ON FILTRE ICI LE DROIT D ACCES
        // SI OK AVEC USER PASS ON TAG EN SESSION OU COOKOIE L UTILISATEUR ON LE FORWARD
        // SI NOK REDIRECTION
        System.out.println("EntranceServlet::DOGET");
        
        // On FORWARD la requète http (ON LA POUSSE UN PEU PLUS LOIN)
       request.getRequestDispatcher("themes").forward(request, response);
    }

//------------------------------------------------------------------------
//------------------------------------------------------------------------
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        System.out.println("EntranceServlet::DOPOST");
        
        //processRequest(request, response);
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
