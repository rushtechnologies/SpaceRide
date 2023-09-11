package com.rushtechnologies.spaceride.ImpDAO;

import com.rushtechnologies.spaceride.DAO.iMuerto;
import com.rushtechnologies.spaceride.Database.Connect;
import com.rushtechnologies.spaceride.Models.Muerto;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author CARLOSHG
 */
public class ImpMuerto implements iMuerto{
    
    private final Connection connect;
    private CallableStatement call;
    private ResultSet result;
    private final String querycreateMuerto = "call createMuerto(?,?,?,?)";
    private final String queryreadM_correcta = "call readM_correcta(?)";
    private final String queryreadMuerto = "call readMuerto()";
    private final String queryupdateM_correcta = "call updateM_correcta(?,?)";
    private final String queryupdateMuerto = "call updateMuerto(?,?,?,?,?)";
    private final String queryupdateM_areaAndM_dif = "call updateM_areaAndM_dif(?,?,?)";

    public ImpMuerto() {
        Connect connection=new Connect();
        this.connect=connection.getConnect();
    }

    @Override
    public boolean createMuerto(Muerto muerto) {
        boolean create = false;
        
        call = null;
        result = null;
        try{
            call=connect.prepareCall(querycreateMuerto);
            call.setInt(1, muerto.getM_u_id1());
            call.setInt(2, muerto.getM_u_id2());
            call.setInt(3, muerto.getM_area());
            call.setInt(4, muerto.getM_dif());
            result = call.executeQuery();
            if (result.next()){
                create = result.getBoolean(1);
            }
        } catch(SQLException e){
            System.out.println("Error: "+e.getMessage());
        }
        finally{
            if(call!=null){
                try{
                    call.close();
                } catch(SQLException e){
                    System.out.println("Error: "+e.getMessage());
                }
                finally{
                }
            }
        }
        
        return create;
    }

    @Override
    public int readM_correcta(int m_u_id1) {
        
        int m_correcta = 0;
        call = null;
        result = null;
        try{
            call=connect.prepareCall(queryreadM_correcta);
            call.setInt(1, m_u_id1);
            result=call.executeQuery();
            if(result.next()){
                m_correcta = result.getInt(1);
            }
        } catch(SQLException e){
            System.out.println("Error: "+e.getMessage());
        }
        finally{
            if(call!=null){
                try{
                    call.close();
                } catch(SQLException e){
                    System.out.println("Error: "+e.getMessage());
                }
                finally{
                }
            }
        }
        
        return m_correcta;
    }

    @Override
    public Muerto readMuerto() {
        
        Muerto muerto = null;
        
        call = null;
        result = null;
        try{
            call = connect.prepareCall(queryreadMuerto);
            result=call.executeQuery();
            if(result.next()){
                muerto = new Muerto();
                muerto.setM_u_id1(result.getInt(1));
                muerto.setM_u_id2(result.getInt(2));
                muerto.setM_area(result.getInt(3));
                muerto.setM_dif(result.getInt(4));
                muerto.setM_correcta(result.getInt(5));
            } else {
                muerto = null;
            }
        } catch(SQLException e){
            System.out.println("Error: "+e.getMessage());
        }
        finally{
            if(call!=null){
                try{
                    call.close();
                } catch(SQLException e){
                    System.out.println("Error: "+e.getMessage());
                }
                finally{
                }
            }
        }
        
        return muerto;
    }

    @Override
    public boolean updateMuerto(Muerto muerto, int exist) {
        
        boolean update = false;
        call = null;
        result = null;
        try{
            call=connect.prepareCall(queryupdateMuerto);
            call.setInt(1, muerto.getM_u_id1());
            call.setInt(2, muerto.getM_u_id2());
            call.setInt(3, muerto.getM_area());
            call.setInt(4, muerto.getM_dif());
            call.setInt(5, exist);
            result = call.executeQuery();
            if (result.next()){
                update = result.getBoolean(1);
            }
        } catch(SQLException e){
            System.out.println("Error: "+e.getMessage());
        }
        finally{
            if(call!=null){
                try{
                    call.close();
                } catch(SQLException e){
                    System.out.println("Error: "+e.getMessage());
                }
                finally{
                }
            }
        }
        
        return update;
    }

    
    @Override
    public boolean updateM_correcta(int m_u_id1, int m_correcta) {
        
        boolean update = false;
        call = null;
        result = null;
        try{
            call=connect.prepareCall(queryupdateM_correcta);
            call.setInt(1, m_u_id1);
            call.setInt(2, m_correcta);
            result = call.executeQuery();
            if (result.next()){
                update = result.getBoolean(1);
            }
        } catch(SQLException e){
            System.out.println("Error: "+e.getMessage());
        }
        finally{
            if(call!=null){
                try{
                    call.close();
                } catch(SQLException e){
                    System.out.println("Error: "+e.getMessage());
                }
                finally{
                }
            }
        }
        
        return update;
    }

    @Override
    public boolean updateM_areaAndM_dif(int m_u_id1, int m_area, int m_dif) {
        
        boolean update = false;
        call = null;
        result = null;
        try{
            call=connect.prepareCall(queryupdateM_areaAndM_dif);
            call.setInt(1, m_u_id1);
            call.setInt(2, m_area);
            call.setInt(3, m_dif);
            result = call.executeQuery();
            if (result.next()){
                update = result.getBoolean(1);
            }
        } catch(SQLException e){
            System.out.println("Error: "+e.getMessage());
        }
        finally{
            if(call!=null){
                try{
                    call.close();
                } catch(SQLException e){
                    System.out.println("Error: "+e.getMessage());
                }
                finally{
                }
            }
        }
        
        return update;
    }
    
}
