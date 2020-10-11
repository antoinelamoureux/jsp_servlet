/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antoinelamoureux.mavenproject1.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author admin
 */
public class ConnexionFactory {

    private String dbUrl;
    private String dbUser;
    private String dbPass;

    public ConnexionFactory() {
    }

    public Connection getConnexion(){

        Connection maConnexion = null;

        try {
            maConnexion = DriverManager.getConnection(getDbUrl(), getDbUser(), getDbPass());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return maConnexion;

    } 


    public String getDbUrl() {
        return dbUrl;
    }


    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }


    public String getDbUser() {
        return dbUser;
    }


    public void setDbUser(String dbUser) {
        this.dbUser = dbUser;
    }


    public String getDbPass() {
        return dbPass;
    }

    /**
     * @param dbPass the dbPass to set
     */
    public void setDbPass(String dbPass) {
        this.dbPass = dbPass;
    }

}
