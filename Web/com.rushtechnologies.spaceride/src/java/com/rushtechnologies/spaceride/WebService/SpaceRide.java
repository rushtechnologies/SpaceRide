package com.rushtechnologies.spaceride.WebService;

import com.rushtechnologies.spaceride.DAO.iMuerto;
import com.rushtechnologies.spaceride.DAO.iUsuario;
import com.rushtechnologies.spaceride.ImpDAO.ImpMuerto;
import com.rushtechnologies.spaceride.ImpDAO.ImpUsuario;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import org.owasp.esapi.ESAPI;

/**
 *
 * @author CARLOSHG
 */
@WebService(serviceName = "SpaceRide")
public class SpaceRide {

    /**
     * @param u_id
     * @return u_name
     */
    @WebMethod(operationName = "readU_name")
    public String readU_name(@WebParam(name = "u_id") int u_id) {
        
        String u_name;
        
        iUsuario user = new ImpUsuario();
        u_name = ESAPI.encoder().decodeForHTML(user.readUser(u_id).getU_nombre());
        
        return u_name;
        
    }
    
    /**
     * @param u_id
     * @return m_correcta
     */
    @WebMethod(operationName = "readM_correcta")
    public int readM_correcta(@WebParam(name = "u_id") int u_id) {
        
        int m_correcta = 0;
        
        iMuerto muerto = new ImpMuerto();
        m_correcta = muerto.readM_correcta(u_id);
        
        return m_correcta;
        
    }

    /**
     * @return ids
     */
    @WebMethod(operationName = "readU_ids")
    public int[] readU_ids() {
        
        int[] ids = new int[2];
        
        iMuerto muerto = new ImpMuerto();
        ids[0] = muerto.readMuerto().getM_u_id1();
        ids[1] = muerto.readMuerto().getM_u_id2();
        
        return ids;
        
    }
    
    /**
     * @param u_id
     * @return muerto
     */
    @WebMethod(operationName = "updateU_muerto")
    public boolean updateU_muerto(@WebParam(name = "u_id") int u_id) {
        
        boolean muerto = false;
        
        iUsuario user = new ImpUsuario();
        muerto = user.updateU_muerto(u_id);
        
        return muerto;
        
    }
    
}
