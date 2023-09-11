package com.rushtechnologies.spaceride.Encrypt;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author CARLOSHG
 */
public class Encrypt {
    
    private final Connection connect;
    private final String  encryptid = "call encryptidSR(?);";
    private final String  encryptst = "call encryptSR(?);";
    private final String  decrypt = "call decryptSR(?);";
    private CallableStatement call;
    private ResultSet result;
    
    public Encrypt(){
        com.rushtechnologies.spaceride.Database.Connect connection=new com.rushtechnologies.spaceride.Database.Connect();
        this.connect=connection.getConnect();
    }
    
    public String encryptid(int id){
        
        String encrypted="";
        
        call = null;
        result = null;
        try{
            call=connect.prepareCall(encryptid);
            call.setInt(1, id);
            result=call.executeQuery();
            if(result.next()){
                encrypted=result.getString(1);
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
        
        return encrypted;
    
    }
    
    public String encryptst(String toencrypt){
        
        String encrypted="";
        
        call=null;
        result = null;
        try{
            call=connect.prepareCall(encryptst);
            call.setString(1, toencrypt);
            result=call.executeQuery();
            if(result.next()){
                encrypted=result.getString(1);
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
        
        return encrypted;
    
    }
    
    public String decrypt(String encrypted){
        
        String decrypted="";
        
        call = null;
        result = null;
        try{
            call=connect.prepareCall(decrypt);
            call.setString(1, encrypted);
            result=call.executeQuery();
            if(result.next()){
                decrypted=result.getString(1);
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
        
        return decrypted;
    
    }
    
}