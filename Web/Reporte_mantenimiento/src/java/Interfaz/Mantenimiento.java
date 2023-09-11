/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

/**
 *
 * @author salva
 */
public interface Mantenimiento {
    boolean Altas(String modulo,String descripcion,String status,String solucion,int idres);
    String [][] RequestTodo();
    String [] RequestEspecifico(int folio); 
    String [][]RequestDesarollador(int idres);
    boolean Update(String descripcion, String solucion, String status,int folio);
    boolean Delete(int folio);
}
