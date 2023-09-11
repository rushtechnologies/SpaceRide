/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Implement;

import Conexion.conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import Interfaz.Mantenimiento;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;

/**
 *
 * @author salva
 * 
 */

public class impMantenimiento implements Mantenimiento {

    Connection conexion;
    ResultSet rs;
    CallableStatement cs;
    String Request = "call RequestTodo();";
    String Insert = "call Altas(?,?,?,?,?,?);";
    String RequestEspecifico = "call RequestEspecifico(?);";
    String Update = "call Actualizar(?,?,?,?,?);";
    String Delete = "call Borrar(?);";
    String Requestdesa = "call RequestDesarrollador(?);";

    public impMantenimiento() throws ClassNotFoundException, SQLException {
        conexion con = new conexion();
        this.conexion = con.getConnection();
    }

    @Override
    public boolean Altas(String modulo,String descripcion,String status,String solucion,int idres) {
        Timestamp date = new Timestamp(System.currentTimeMillis());
        int dia = date.getDay();
        int mes = date.getMonth();
        int año = date.getYear() + 1900;
        Boolean check = false;
        try {
            cs = conexion.prepareCall(Insert);
            cs.setString(1, modulo);
            cs.setString(2, descripcion);
            cs.setString(3, status);
            cs.setString(4, solucion);
            cs.setInt(5, idres);
            cs.setTimestamp(6, date);
            cs.execute();
            conexion.close();
            check = true;
        } catch (SQLException ex) {
            System.out.println("Error Altas " + ex.toString());
        }
        return check;
    }

    @Override
    public String[][] RequestTodo() {
        String[][] requestReturn = null;
        int ayuda = 0;
        Format formatter = new SimpleDateFormat("MM/dd/yyyy");
        try {
            cs = conexion.prepareCall(Request);
            rs = cs.executeQuery();
            if (rs.next()) {
                rs.last();
                ayuda = rs.getRow();
                rs.first();
                requestReturn = new String[ayuda][7];
                for (int i = 0; i < ayuda; i++) {
                    requestReturn[i][0] = Integer.toString(rs.getInt(1));
                    requestReturn[i][1] = rs.getString(2);
                    requestReturn[i][2] = rs.getString(3);
                    requestReturn[i][3] = rs.getString(4);
                    requestReturn[i][4] = rs.getString(5);
                    requestReturn[i][5] = rs.getString(6);
                    requestReturn[i][6] = formatter.format(rs.getTimestamp(7));
                    rs.next();
                }
                rs.close();
            }else{
                requestReturn = new String[1][1];
                requestReturn[0][0] = "Empty";
            }
            conexion.close();
        } catch (Exception e) {
            System.out.println("Error RequestTodo" + e.toString());
            requestReturn = new String[1][1];
            requestReturn[0][0] = "Error";
        }
        conexion =null;
        rs = null;
        cs = null;
        return requestReturn;
    }
    

    @Override
    public String[] RequestEspecifico(int folio) {
        String[] requestReturn;
        int ayuda;
        Format formatter = new SimpleDateFormat("MM/dd/yyyy");
        try {
            cs = conexion.prepareCall(RequestEspecifico);
            cs.setInt(1, folio);
            rs = cs.executeQuery();
            if (rs.next()) {
                rs.last();
                ayuda = rs.getRow();
                rs.first();
                requestReturn = new String[7];
                requestReturn[0] = Integer.toString(rs.getInt(1));
                requestReturn[1] = rs.getString(2);
                requestReturn[2] = rs.getString(3);
                requestReturn[3] = rs.getString(4);
                requestReturn[4] = rs.getString(5);
                requestReturn[5] = rs.getString(6);
                requestReturn[6] = formatter.format(rs.getTimestamp(7));
            }else{
                requestReturn = new String[1];
                requestReturn[0] = "Empty";
            }
            conexion.close();
        } catch (Exception e) {
            System.out.println("Error RequestEspecifico " + e.toString());
            requestReturn = new String[1];
            requestReturn[0] = "Error";
        }
        return requestReturn;
    }

    @Override
    public boolean Update(String descripcion, String solucion, String estatus,int folio) {
        Timestamp date = new Timestamp(System.currentTimeMillis());
        int dia = date.getDay();
        int mes = date.getMonth();
        int año = date.getYear();
        System.out.println(descripcion);
        System.out.println(solucion);
        System.out.println(folio);
        Boolean check = false;
        try {
            cs = conexion.prepareCall(Update);
            cs.setTimestamp(1, date);
            cs.setString(2, descripcion);
            cs.setString(3, solucion);
            cs.setString(4, estatus);
            cs.setInt(5, folio);
            cs.execute();
            conexion.close();
            check = true;
        } catch (Exception e) {
            System.out.println("Error Update " + e.toString());
        }
        return check;
    }

    @Override
    public boolean Delete(int folio) {
        Boolean check = false;
        try {
            cs = conexion.prepareCall(Delete);
            cs.setInt(1, folio);
            cs.execute();
            conexion.close();
            check = true;
        } catch (Exception e) {
            System.out.println("Error Delete " + e.toString());
        }
        return check;
    }

    @Override
    public String[][] RequestDesarollador(int idres) {
        String[][] requestReturn;
        int ayuda;
        Format formatter = new SimpleDateFormat("MM/dd/yyyy");
        try {
            cs = conexion.prepareCall(Requestdesa);
            cs.setInt(1, idres);
            rs = cs.executeQuery();
            if (rs.next()) {
                rs.last();
                ayuda = rs.getRow();
                rs.first();
                requestReturn = new String[ayuda][6];
                for (int i = 0; i < ayuda; i++) {
                    requestReturn[i][0] = Integer.toString(rs.getInt(1));
                    requestReturn[i][1] = rs.getString(2);
                    requestReturn[i][2] = rs.getString(3);
                    requestReturn[i][3] = rs.getString(5);
                    requestReturn[i][4] = rs.getString(4);
                    requestReturn[i][5] = formatter.format(rs.getTimestamp(7));
                    rs.next();
                }
                rs.close();
            }else{
                requestReturn = new String[1][1];
                requestReturn[0][0] = "Empty";
            }
            conexion.close();
        } catch (Exception e) {
            System.out.println("Error Request desarollador" + e.toString());
            requestReturn = new String[1][1];
            requestReturn[0][0] = "Error";
        }
        return requestReturn;
    }

}
