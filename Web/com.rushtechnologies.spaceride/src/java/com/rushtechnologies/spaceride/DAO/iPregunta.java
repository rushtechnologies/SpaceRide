package com.rushtechnologies.spaceride.DAO;

import com.rushtechnologies.spaceride.Models.Pregunta;
import java.util.List;

/**
 *
 * @author CARLOSHG
 */
public interface iPregunta {
    public boolean createPregunta (Pregunta pregunta);
    public Pregunta readPregunta(int u_id1, int u_id2, int area_id, int dif);
    public Pregunta readRandomPregunta (String p_area, int p_dif);
    public Pregunta updateAndReadCustomPregunta_seleccion (int p_dif, String p_area);
    public Pregunta updateAndReadPregunta_seleccion (int p_id);
    public boolean updatePregunta_seleccion (int p_id);
    public boolean updateResetP_seleccion();
}
