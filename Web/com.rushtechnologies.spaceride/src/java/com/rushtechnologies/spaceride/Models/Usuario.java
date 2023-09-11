package com.rushtechnologies.spaceride.Models;

/**
 *
 * @author CARLOSHG
 */
public class Usuario {
    
    private int u_id;
    private String u_nombre;
    private String u_contra;
    private String u_correo;
    private int u_type;
    private int u_victorias;
    private int u_derrotas;
    private boolean u_muerto;

    public Usuario() {
    }

    public Usuario(int u_id, String u_nombre, String u_contra, String u_correo, int u_type) {
        this.u_id = u_id;
        this.u_nombre = u_nombre;
        this.u_contra = u_contra;
        this.u_correo = u_correo;
        this.u_type = u_type;
    }

    public Usuario(int u_id, String u_nombre, String u_contra, String u_correo, int u_type, int u_victorias, int u_derrotas, boolean u_muerto) {
        this.u_id = u_id;
        this.u_nombre = u_nombre;
        this.u_contra = u_contra;
        this.u_correo = u_correo;
        this.u_type = u_type;
        this.u_victorias = u_victorias;
        this.u_derrotas = u_derrotas;
        this.u_muerto = u_muerto;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public String getU_nombre() {
        return u_nombre;
    }

    public void setU_nombre(String u_nombre) {
        this.u_nombre = u_nombre;
    }

    public String getU_contra() {
        return u_contra;
    }

    public void setU_contra(String u_contra) {
        this.u_contra = u_contra;
    }

    public String getU_correo() {
        return u_correo;
    }

    public void setU_correo(String u_correo) {
        this.u_correo = u_correo;
    }

    public int getU_type() {
        return u_type;
    }

    public void setU_type(int u_type) {
        this.u_type = u_type;
    }

    public int getU_victorias() {
        return u_victorias;
    }

    public void setU_victorias(int u_victorias) {
        this.u_victorias = u_victorias;
    }

    public int getU_derrotas() {
        return u_derrotas;
    }

    public void setU_derrotas(int u_derrotas) {
        this.u_derrotas = u_derrotas;
    }

    public boolean isU_muerto() {
        return u_muerto;
    }

    public void setU_muerto(boolean u_muerto) {
        this.u_muerto = u_muerto;
    }

    @Override
    public String toString() {
        return "Usuario{" + "u_id=" + u_id + ", u_nombre=" + u_nombre + ", u_contra=" + u_contra + ", u_correo=" + u_correo + ", u_type=" + u_type + ", u_victorias=" + u_victorias + ", u_derrotas=" + u_derrotas + ", u_muerto=" + u_muerto + '}';
    }

}