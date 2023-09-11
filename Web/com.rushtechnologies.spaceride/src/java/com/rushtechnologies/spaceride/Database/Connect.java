package com.rushtechnologies.spaceride.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author CARLOSHG
 */
public class Connect {
    
    private final String url = "jdbc:mysql://localhost/spaceride";
    private final String usr = "root";
    private final String pass = "n0m3l0";
    public Connection connect = null;

    public Connection getConnect() {
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connect = DriverManager.getConnection(url,usr,pass);
        } catch(ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e){
            System.out.println("Error at getConnect: "+e.getMessage());
        }
        return connect;
    }
    
}