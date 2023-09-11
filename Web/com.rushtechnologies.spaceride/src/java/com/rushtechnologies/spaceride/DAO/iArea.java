package com.rushtechnologies.spaceride.DAO;

import com.rushtechnologies.spaceride.Models.Area;

/**
 *
 * @author CARLOSHG
 */
public interface iArea {
    Area readArea (int area_u_id);
    Area readAreaMax (int area_u_id);
    boolean updateArea_arte (int area_u_id, int area_max, int area_max_id);
    boolean updateArea_astro (int area_u_id, int area_max, int area_max_id);
    boolean updateArea_bio (int area_u_id, int area_max, int area_max_id);
    boolean updateArea_ent (int area_u_id, int area_max, int area_max_id);
    boolean updateArea_esp (int area_u_id, int area_max, int area_max_id);
    boolean updateArea_fis (int area_u_id, int area_max, int area_max_id);
    boolean updateArea_geo (int area_u_id, int area_max, int area_max_id);
    boolean updateArea_hmex ( int area_u_id, int area_max, int area_max_id);
    boolean updateArea_huni (int area_u_id, int area_max, int area_max_id);
    boolean updateArea_ing (int area_u_id, int area_max, int area_max_id);
    boolean updateArea_mate (int area_u_id, int area_max, int area_max_id);
    boolean updateArea_qui (int area_u_id, int area_max, int area_max_id);
    boolean updateArea_tec (int area_u_id, int area_max, int area_max_id);
}
