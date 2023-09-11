package com.rushtechnologies.spaceride.ImpDAO;

import com.rushtechnologies.spaceride.DAO.iFaq;
import com.rushtechnologies.spaceride.Database.Connect;
import com.rushtechnologies.spaceride.Models.Faq;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author CARLOSHG
 */
public class ImpFaq implements iFaq{
    
    private final Connection connect;
    private CallableStatement call;
    private ResultSet result;
    private final String querycreateFaq = "call createFaq(?,?,?,?,?,?,?)";
    private final String queryreadFaq = "call readFaq(?)";
    private final String queryreadAllFaq = "call readAllFaq()";
    private final String queryreadAllFaqwt = "call readAllFaqwt(?)";
    private final String queryreadAllFaq_tema = "call readAllFaq_tema()";
    private final String queryreadAllFaqAdmin = "call readAllFaqAdmin()";
    private final String queryupdateFaq_respuesta = "call updateFaq_respuesta(?,?,?)";
    private final String querydeleteFaq = "call deleteFaq(?)";

    public ImpFaq() {
        Connect connection=new Connect();
        this.connect=connection.getConnect();
    }

    @Override
    public boolean createFaq(Faq faq) {
        
        boolean create = false;
        call = null;
        result = null;
        try{
            call = connect.prepareCall(querycreateFaq);
            call.setString(1, faq.getFaq_pregunta());
            call.setString(2, faq.getFaq_respuesta());
            call.setString(3, faq.getFaq_tema());
            call.setInt(4, faq.getFaq_prioridad());
            call.setBoolean(5, faq.isFaq_aprobacion());
            call.setInt(6, faq.getFaq_u_id());
            call.setInt(7, faq.getFaq_a_id());
            result = call.executeQuery();
            if (result.next()){
                create = result.getBoolean(1);
            }
        } catch(SQLException e){
            System.out.println("Error at createFaq: "+e.getMessage());
        }
        finally{
            if(call!=null){
                try{
                    call.close();
                } catch(SQLException e){
                    System.out.println("Error at createFaq: "+e.getMessage());
                }
                finally{
                }
            }
        }
        
        return create;
    }

    @Override
    public Faq readFaq(int faq_id) {
        
        Faq faq = null;
        call = null;
        result = null;
        try{
            call=connect.prepareCall(queryreadFaq);
            call.setInt(1, faq_id);
            result=call.executeQuery();
            if(result.next()){
                faq = new Faq();
                faq.setFaq_id(result.getInt(1));
                faq.setFaq_pregunta(result.getString(2));
                faq.setFaq_respuesta(result.getString(3));
                faq.setFaq_tema(result.getString(4));
                faq.setFaq_prioridad(result.getInt(5));
                faq.setFaq_aprobacion(result.getBoolean(6));
                faq.setFaq_u_id(result.getInt(7));
                faq.setFaq_a_id(result.getInt(8));
            } else{
                faq = null;
            }
        } catch(SQLException e){
            System.out.println("Error at readFaq: "+e.getMessage());
        }
        finally{
            if(call!=null){
                try{
                    call.close();
                } catch(SQLException e){
                    System.out.println("Error at readFaq: "+e.getMessage());
                }
                finally{
                }
            }
        }
        return faq;
    }
    
    @Override
    public List<Faq> readAllFaq(String faq_tema){
        
        List<Faq> faqs = new ArrayList<>();
        call = null;
        result = null;
        try{
            call=connect.prepareCall(queryreadAllFaqwt);
            call.setString(1, faq_tema);
            result=call.executeQuery();
            while (result.next()){
                Faq faq = new Faq();
                faq.setFaq_id(result.getInt(1));
                faq.setFaq_pregunta(result.getString(2));
                faq.setFaq_respuesta(result.getString(3));
                faq.setFaq_tema(result.getString(4));
                faq.setFaq_prioridad(result.getInt(5));
                faq.setFaq_aprobacion(result.getBoolean(6));
                faq.setFaq_u_id(result.getInt(7));
                faq.setFaq_a_id(result.getInt(8));
                faqs.add(faq);
            }
        } catch(SQLException e){
            System.out.println("Error at readAllFaqwt: "+e.getMessage());
        }
        finally{
            if(call!=null){
                try{
                    call.close();
                } catch(SQLException e){
                    System.out.println("Error at readAllFaqwt: "+e.getMessage());
                }
                finally{
                }
            }
        }
        return faqs;
    }
    
    @Override
    public List<Faq> readAllFaq(){
        
        List<Faq> faqs = new ArrayList<>();
        call = null;
        result = null;
        try{
            call=connect.prepareCall(queryreadAllFaq);
            result=call.executeQuery();
            while (result.next()){
                Faq faq = new Faq();
                faq.setFaq_id(result.getInt(1));
                faq.setFaq_pregunta(result.getString(2));
                faq.setFaq_respuesta(result.getString(3));
                faq.setFaq_tema(result.getString(4));
                faq.setFaq_prioridad(result.getInt(5));
                faq.setFaq_aprobacion(result.getBoolean(6));
                faq.setFaq_u_id(result.getInt(7));
                faq.setFaq_a_id(result.getInt(8));
                faqs.add(faq);
            }
        } catch(SQLException e){
            System.out.println("Error at readAllFaq: "+e.getMessage());
        }
        finally{
            if(call!=null){
                try{
                    call.close();
                } catch(SQLException e){
                    System.out.println("Error at readAllFaq: "+e.getMessage());
                }
                finally{
                }
            }
        }
        
        return faqs;
        
    }
    
    @Override
    public List<String> readAllFaq_tema(){
        
        List<String> temas = new ArrayList<>();
        call = null;
        result = null;
        try{
            call=connect.prepareCall(queryreadAllFaq_tema);
            result=call.executeQuery();
            while (result.next()){
                temas.add(result.getString(1));
            }
        } catch(SQLException e){
            System.out.println("Error at readAllFaq_tema: "+e.getMessage());
        }
        finally{
            if(call!=null){
                try{
                    call.close();
                } catch(SQLException e){
                    System.out.println("Error at readAllFaq_tema: "+e.getMessage());
                }
                finally{
                }
            }
        }
        return temas;
    }
    
    @Override
    public List<Faq> readAllFaqAdmin() {
        List<Faq> faqs = new ArrayList<>();
        call = null;
        result = null;
        try{
            call=connect.prepareCall(queryreadAllFaqAdmin);
            result=call.executeQuery();
            while (result.next()){
                Faq faq = new Faq();
                faq.setFaq_id(result.getInt(1));
                faq.setFaq_pregunta(result.getString(2));
                faq.setFaq_respuesta(result.getString(3));
                faq.setFaq_tema(result.getString(4));
                faq.setFaq_prioridad(result.getInt(5));
                faq.setFaq_aprobacion(result.getBoolean(6));
                faq.setFaq_u_id(result.getInt(7));
                faq.setFaq_a_id(result.getInt(8));
                faqs.add(faq);
            }
        } catch(SQLException e){
            System.out.println("Error at readAllFaqAdmin: "+e.getMessage());
        }
        finally{
            if(call!=null){
                try{
                    call.close();
                } catch(SQLException e){
                    System.out.println("Error at readAllFaqAdmin: "+e.getMessage());
                }
                finally{
                }
            }
        }
        return faqs;
    }
    
    @Override
    public boolean updateFaq_respuesta(int faq_id, String faq_respuesta, int faq_a_id) {
        
        boolean update = false;
        call = null;
        result = null;
        try{
            call=connect.prepareCall(queryupdateFaq_respuesta);
            call.setInt(1, faq_id);
            call.setString(2, faq_respuesta);
            call.setInt(3, faq_a_id);
            result = call.executeQuery();
            if (result.next()){
                update = result.getBoolean(1);
            }
        } catch(SQLException e){
            System.out.println("Error at updateFaq_respuesta: "+e.getMessage());
        }
        finally{
            if(call!=null){
                try{
                    call.close();
                } catch(SQLException e){
                    System.out.println("Error at updateFaq_respuesta: "+e.getMessage());
                }
                finally{
                }
            }
        }
        
        return update;
        
    }

    @Override
    public boolean deleteFaq(int faq_id) {
        
        boolean delete = false;
        call = null;
        result = null;
        try{
            call=connect.prepareCall(querydeleteFaq);
            call.setInt(1, faq_id);
            result = call.executeQuery();
            if (result.next()){
                delete = result.getBoolean(1);
            }
        } catch(SQLException e){
            System.out.println("Error at deleteFaq: "+e.getMessage());
        }
        finally{
            if(call!=null){
                try{
                    call.close();
                } catch(SQLException e){
                    System.out.println("Error at deleteFaq: "+e.getMessage());
                }
                finally{
                }
            }
        }
        
        return delete;
    }

}
