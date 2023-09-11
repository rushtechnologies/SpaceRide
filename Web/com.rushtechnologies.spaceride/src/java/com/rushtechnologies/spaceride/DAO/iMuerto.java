package com.rushtechnologies.spaceride.DAO;

import com.rushtechnologies.spaceride.Models.Muerto;

/**
 *
 * @author CARLOSHG
 */
public interface iMuerto {
    public boolean createMuerto(Muerto muerto);
    public int readM_correcta(int m_u_id1);
    public Muerto readMuerto();
    public boolean updateMuerto(Muerto muerto, int exist);
    public boolean updateM_correcta(int m_u_id1, int m_correcta);
    public boolean updateM_areaAndM_dif (int m_u_id1, int m_area, int m_dif);
}
