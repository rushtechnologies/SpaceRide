package com.rushtechnologies.spaceride.Models;

/**
 *
 * @author CARLOSHG
 */
public class Pregunta {
    private int p_id;
    private String p_p;
    private String p_r1;
    private String p_r2;
    private String p_r3;
    private String p_r4;
    private int p_correcta;
    private int p_dif;
    private String p_area;
    private boolean p_seleccion;

    public Pregunta() {
    }

    public Pregunta(int p_id, String p_p, String p_r1, String p_r2, String p_r3, String p_r4, int p_correcta, int p_dif, String p_area, boolean p_seleccion) {
        this.p_id = p_id;
        this.p_p = p_p;
        this.p_r1 = p_r1;
        this.p_r2 = p_r2;
        this.p_r3 = p_r3;
        this.p_r4 = p_r4;
        this.p_correcta = p_correcta;
        this.p_dif = p_dif;
        this.p_area = p_area;
        this.p_seleccion = p_seleccion;
    }

    public int getP_id() {
        return p_id;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    public String getP_p() {
        return p_p;
    }

    public void setP_p(String p_p) {
        this.p_p = p_p;
    }

    public String getP_r1() {
        return p_r1;
    }

    public void setP_r1(String p_r1) {
        this.p_r1 = p_r1;
    }

    public String getP_r2() {
        return p_r2;
    }

    public void setP_r2(String p_r2) {
        this.p_r2 = p_r2;
    }

    public String getP_r3() {
        return p_r3;
    }

    public void setP_r3(String p_r3) {
        this.p_r3 = p_r3;
    }

    public String getP_r4() {
        return p_r4;
    }

    public void setP_r4(String p_r4) {
        this.p_r4 = p_r4;
    }

    public int getP_correcta() {
        return p_correcta;
    }

    public void setP_correcta(int p_correcta) {
        this.p_correcta = p_correcta;
    }

    public int getP_dif() {
        return p_dif;
    }

    public void setP_dif(int p_dif) {
        this.p_dif = p_dif;
    }

    public String getP_area() {
        return p_area;
    }

    public void setP_area(String p_area) {
        this.p_area = p_area;
    }

    public boolean isP_seleccion() {
        return p_seleccion;
    }

    public void setP_seleccion(boolean p_seleccion) {
        this.p_seleccion = p_seleccion;
    }

    @Override
    public String toString() {
        return "Pregunta{" + "p_id=" + p_id + ", p_p=" + p_p + ", p_r1=" + p_r1 + ", p_r2=" + p_r2 + ", p_r3=" + p_r3 + ", p_r4=" + p_r4 + ", p_correcta=" + p_correcta + ", p_dif=" + p_dif + ", p_area=" + p_area + ", p_seleccion=" + p_seleccion + '}';
    }
    
}
