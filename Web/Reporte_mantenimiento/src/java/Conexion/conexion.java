package Conexion;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author salva
 */
public class conexion {
    
    private Connection conexion=null;
    String class_name="com.mysql.jdbc.Driver";
    String url="jdbc:mysql://localhost/spaceride";
    String user="root";
    String password="n0m3l0";

    public conexion() throws ClassNotFoundException, SQLException {
        Class.forName(class_name);
        conexion = DriverManager.getConnection(url, user, password);
    }

    public Connection getConnection() {
        return conexion;
    }
    
}
