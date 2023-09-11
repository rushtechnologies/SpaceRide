package com.rushtechnologies.spaceride.DAO;

import com.rushtechnologies.spaceride.Models.UserChart;
import com.rushtechnologies.spaceride.Models.Usuario;
import java.util.List;

/**
 *
 * @author CARLOSHG
 */
public interface iUsuario {

    public boolean createUser(Usuario usuario);

    public int[] loginUser(String u_nombre, String u_contra);

    public Usuario readUser(int u_id);

    public Usuario readU_muerto();

    public List<UserChart> readU_derrotasAndU_victorias(int u_id);

    public boolean updateUserData(Usuario usuario);

    public boolean updateUserPsd(int u_id, String u_contra);

    public boolean updateU_derrotas(int u_id);

    public boolean updateFirstU_derrotas(int u_id, int area_max_id);

    public boolean updateU_muerto(int u_id);

    public boolean updateU_muertoReset(int u_id1, int u_id2);

    public boolean updateU_victoria(int u_id);
}
