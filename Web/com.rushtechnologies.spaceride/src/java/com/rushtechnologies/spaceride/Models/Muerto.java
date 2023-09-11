package com.rushtechnologies.spaceride.Models;

/**
 *
 * @author CARLOSHG
 */
public class Muerto {
    private int m_u_id1;
    private int m_u_id2;
    private int m_area;
    private int m_dif;
    private int m_correcta;

    public Muerto() {
    }

    public Muerto(int m_u_id1, int m_u_id2, int m_area, int m_dif, int m_correcta) {
        this.m_u_id1 = m_u_id1;
        this.m_u_id2 = m_u_id2;
        this.m_area = m_area;
        this.m_dif = m_dif;
        this.m_correcta = m_correcta;
    }

    public int getM_u_id1() {
        return m_u_id1;
    }

    public void setM_u_id1(int m_u_id1) {
        this.m_u_id1 = m_u_id1;
    }

    public int getM_u_id2() {
        return m_u_id2;
    }

    public void setM_u_id2(int m_u_id2) {
        this.m_u_id2 = m_u_id2;
    }

    public int getM_area() {
        return m_area;
    }

    public void setM_area(int m_area) {
        this.m_area = m_area;
    }

    public int getM_dif() {
        return m_dif;
    }

    public void setM_dif(int m_dif) {
        this.m_dif = m_dif;
    }

    public int getM_correcta() {
        return m_correcta;
    }

    public void setM_correcta(int m_correcta) {
        this.m_correcta = m_correcta;
    }

    @Override
    public String toString() {
        return "Muerto{" + "m_u_id1=" + m_u_id1 + ", m_u_id2=" + m_u_id2 + ", m_area=" + m_area + ", m_dif=" + m_dif + ", m_correcta=" + m_correcta + '}';
    }
    
}
