package com.rushtechnologies.spaceride.ImpDAO;

import com.rushtechnologies.spaceride.DAO.iUsuario;
import com.rushtechnologies.spaceride.Database.Connect;
import com.rushtechnologies.spaceride.Encrypt.Encrypt;
import com.rushtechnologies.spaceride.Models.UserChart;
import com.rushtechnologies.spaceride.Models.Usuario;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author CARLOSHG
 */
public class ImpUsuario implements iUsuario{
    
    private final Connection connect;
    private CallableStatement call;
    private ResultSet result;
    private final Encrypt encrypt = new Encrypt();
    private final String querycreate = "call createUser(?,?,?,?,?)";
    private final String querylogin = "call loginUser(?,?)";
    private final String queryread = "call readUser(?)";
    private final String queryreadU_muerto = "call readU_muerto()";
    private final String queryreadU_derrotasAndU_victorias = "call readU_derrotasAndU_victorias(?)";
    private final String queryupdatedata = "call updateUserData(?,?,?)";
    private final String queryupdatepsd = "call updateUserPsd(?,?)";
    private final String queryupdateU_derrotas = "call updateU_derrotas(?)";
    private final String queryupdateFirstU_derrotas = "call updateFirstU_derrotas(?,?)";
    private final String queryupdateU_muerto = "call updateU_muerto(?)";
    private final String queryupdateU_muertoReset = "call updateU_muertoReset(?,?)";
    private final String queryupdateU_victoria = "call updateU_victorias(?)";

    public ImpUsuario() {
        Connect connection = new Connect();
        this.connect = connection.getConnect();
    }

    @Override
    public boolean createUser(Usuario usuario) {
        
        boolean create = false;
        call = null;
        result = null;
        try{
            call=connect.prepareCall(querycreate);
            call.setInt(1, usuario.getU_id());
            call.setString(2, usuario.getU_nombre());
            call.setString(3, encrypt.encryptst(usuario.getU_contra()));
            call.setString(4, usuario.getU_correo());
            call.setInt(5, usuario.getU_type());
            result = call.executeQuery();
            if (result.next()){
                create = result.getBoolean(1);
            }
        } catch(SQLException e){
            System.out.println("Error at createUser: "+e.getMessage());
        }
        finally{
            if(call!=null){
                try{
                    call.close();
                } catch(SQLException e){
                    System.out.println("Error at createUser: "+e.getMessage());
                }
                finally{
                }
            }
        }
        
        return create;
        
    }

    @Override
    public int[] loginUser(String u_nombre, String u_contra) {
        
        int[] login = new int[2];
        
        call = null;
        result = null;
        try{
            call=connect.prepareCall(querylogin);
            call.setString(1, u_nombre);
            call.setString(2, encrypt.encryptst(u_contra));
            result = call.executeQuery();
            if(result.next()){
                login[0] = result.getInt(1);
                login[1] = result.getInt(2);
            }
            else{
                login[0] = 0;
                login[1] = 0;
            }
        } catch(SQLException e){
            System.out.println("Error at loginUser: "+e.getMessage());
        }
        finally{
            if(call!=null){
                try{
                    call.close();
                } catch(SQLException e){
                    System.out.println("Error at loginUser: "+e.getMessage());
                }
                finally{
                }
            }
        }
        
        return login;
        
    }

    @Override
    public Usuario readUser(int u_id) {
        
        Usuario usuario = null;
        call = null;
        result = null;
        try{
            call=connect.prepareCall(queryread);
            call.setInt(1, u_id);
            result=call.executeQuery();
            if(result.next()){
                usuario = new Usuario();
                usuario.setU_id(result.getInt("u_id"));
                usuario.setU_nombre(result.getString("u_nombre"));
                usuario.setU_contra(encrypt.decrypt(result.getString("u_contra")));
                usuario.setU_correo(result.getString("u_correo"));
                usuario.setU_type(result.getInt("u_type"));
                usuario.setU_victorias(result.getInt("u_victorias"));
                usuario.setU_derrotas(result.getInt("u_derrotas"));
                usuario.setU_muerto(result.getBoolean("u_muerto"));
            }
        } catch(SQLException e){
            System.out.println("Error at readUser: "+e.getMessage());
        }
        finally{
            if(call!=null){
                try{
                    call.close();
                } catch(SQLException e){
                    System.out.println("Error at readUser: "+e.getMessage());
                }
                finally{
                }
            }
        }
        return usuario;
        
    }

    @Override
    public Usuario readU_muerto() {
        
        Usuario usuario = null;
        call = null;
        result = null;
        try{
            call=connect.prepareCall(queryreadU_muerto);
            result=call.executeQuery();
            if(result.next()){
                usuario = new Usuario();
                usuario.setU_id(result.getInt("u_id"));
                usuario.setU_nombre(result.getString("u_nombre"));
                usuario.setU_contra(encrypt.decrypt(result.getString("u_contra")));
                usuario.setU_correo(result.getString("u_correo"));
                usuario.setU_type(result.getInt("u_type"));
                usuario.setU_victorias(result.getInt("u_victorias"));
                usuario.setU_derrotas(result.getInt("u_derrotas"));
                usuario.setU_muerto(result.getBoolean("u_muerto"));
            }
        } catch(SQLException e){
            System.out.println("Error at readU_muerto: "+e.getMessage());
        }
        finally{
            if(call!=null){
                try{
                    call.close();
                } catch(SQLException e){
                    System.out.println("Error at readU_muerto: "+e.getMessage());
                }
                finally{
                }
            }
        }
        
        return usuario;
    }
    
    @Override
    public boolean updateUserData(Usuario usuario) {
        
        boolean update = false;
        call = null;
        result = null;
        try{
            call=connect.prepareCall(queryupdatedata);
            call.setInt(1, usuario.getU_id());
            call.setString(2, usuario.getU_nombre());
            call.setString(3, usuario.getU_correo());
            result = call.executeQuery();
            if (result.next()){
                update = result.getBoolean(1);
            }
        } catch(SQLException e){
            System.out.println("Error at updateUserData: "+e.getMessage());
        }
        finally{
            if(call!=null){
                try{
                    call.close();
                } catch(SQLException e){
                    System.out.println("Error at updateUserData: "+e.getMessage());
                }
                finally{
                }
            }
        }
        
        return update;
        
    }

    @Override
    public boolean updateUserPsd(int u_id, String u_contra) {
        
        boolean update = false;
        
        call = null;
        result = null;
        try{
            call=connect.prepareCall(queryupdatepsd);
            call.setInt(1, u_id);
            call.setString(2, encrypt.encryptst(u_contra));
            result = call.executeQuery();
            if (result.next()){
                update = result.getBoolean(1);
            }
        } catch(SQLException e){
            System.out.println("Error at updateUserPsd: "+e.getMessage());
        }
        finally{
            if(call!=null){
                try{
                    call.close();
                } catch(SQLException e){
                    System.out.println("Error at updateUserPsd: "+e.getMessage());
                }
                finally{
                }
            }
        }
        
        return update;
        
    }

    @Override
    public boolean updateU_derrotas(int u_id) {
        
        boolean update = false;
        call = null;
        result = null;
        try{
            call=connect.prepareCall(queryupdateU_derrotas);
            call.setInt(1, u_id);
            result = call.executeQuery();
            if (result.next()){
                update = result.getBoolean(1);
            }
        } catch(SQLException e){
            System.out.println("Error at updateU_derrotas: "+e.getMessage());
        }
        finally{
            if(call!=null){
                try{
                    call.close();
                } catch(SQLException e){
                    System.out.println("Error at updateU_derrotas: "+e.getMessage());
                }
                finally{
                }
            }
        }
        
        return update;
        
    }

    @Override
    public boolean updateFirstU_derrotas (int u_id, int area_max_id){
        
        boolean update = false;
        call = null;
        result = null;
        try{
            call=connect.prepareCall(queryupdateFirstU_derrotas);
            call.setInt(1, u_id);
            call.setInt(2, area_max_id);
            result = call.executeQuery();
            if (result.next()){
                update = result.getBoolean(1);
            }
        } catch(SQLException e){
            System.out.println("Error at updateFirstU_derrotas: "+e.getMessage());
        }
        finally{
            if(call!=null){
                try{
                    call.close();
                } catch(SQLException e){
                    System.out.println("Error at updateFirstU_derrotas: "+e.getMessage());
                }
                finally{
                }
            }
        }
        
        return update;
        
    }
    
    @Override
    public boolean updateU_muerto(int u_id) {
        
        boolean update = false;
        call = null;
        result = null;
        try{
            call=connect.prepareCall(queryupdateU_muerto);
            call.setInt(1, u_id);
            result = call.executeQuery();
            if (result.next()){
                update = result.getBoolean(1);
            }
        } catch(SQLException e){
            System.out.println("Error at updateU_muerto: "+e.getMessage());
        }
        finally{
            if(call!=null){
                try{
                    call.close();
                } catch(SQLException e){
                    System.out.println("Error at updateU_muerto: "+e.getMessage());
                }
                finally{
                }
            }
        }
        
        return update;
    }

    @Override
    public boolean updateU_muertoReset(int u_id1, int u_id2) {
        
        boolean update = false;
        call = null;
        result = null;
        try{
            call=connect.prepareCall(queryupdateU_muertoReset);
            call.setInt(1, u_id1);
            call.setInt(2, u_id2);
            result = call.executeQuery();
            if (result.next()){
                update = result.getBoolean(1);
            }
        } catch(SQLException e){
            System.out.println("Error at updateU_muertoReset: "+e.getMessage());
        }
        finally{
            if(call!=null){
                try{
                    call.close();
                } catch(SQLException e){
                    System.out.println("Error at updateU_muertoReset: "+e.getMessage());
                }
                finally{
                }
            }
        }
        
        return update;
    }

    @Override
    public boolean updateU_victoria(int u_id) {
        
        boolean update = false;
        call = null;
        result = null;
        try{
            call=connect.prepareCall(queryupdateU_victoria);
            call.setInt(1, u_id);
            result = call.executeQuery();
            if (result.next()){
                update = result.getBoolean(1);
            }
        } catch(SQLException e){
            System.out.println("Error at updateU_victoria: "+e.getMessage());
        }
        finally{
            if(call!=null){
                try{
                    call.close();
                } catch(SQLException e){
                    System.out.println("Error at updateU_victoria: "+e.getMessage());
                }
                finally{
                }
            }
        }
        
        return update;
    }

    @Override
    public List<UserChart> readU_derrotasAndU_victorias(int u_id) {
        List<UserChart> U_derrotasAndU_victorias = new ArrayList<UserChart>();
        
        call = null;
        result = null;
        try{
            call=connect.prepareCall(queryreadU_derrotasAndU_victorias);
            call.setInt(1, u_id);
            result = call.executeQuery();
            if(result.next()){
                U_derrotasAndU_victorias.add(new UserChart(result.getInt(1)));
                U_derrotasAndU_victorias.add(new UserChart(result.getInt(2)));
            }
        } catch(SQLException e){
            System.out.println("Error at readU_derrotasAndU_victorias: "+e.getMessage());
        }
        finally{
            if(call!=null){
                try{
                    call.close();
                } catch(SQLException e){
                    System.out.println("Error at readU_derrotasAndU_victorias: "+e.getMessage());
                }
                finally{
                }
            }
        }
        
        return U_derrotasAndU_victorias;
        
    }
    
}