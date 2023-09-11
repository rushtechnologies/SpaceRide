package com.rushtechnologies.spaceride.ImpDAO;

import com.rushtechnologies.spaceride.DAO.iPregunta;
import com.rushtechnologies.spaceride.Database.Connect;
import com.rushtechnologies.spaceride.Models.Pregunta;
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
public class ImpPregunta implements iPregunta {

    private final Connection connect;
    private CallableStatement call;
    private ResultSet result;
    private final String querycreatePregunta = "call createPregunta(?,?,?,?,?,?,?,?,?)";
    private final String queryreadRandomPregunta = "call readRandomPregunta(?,?)";
    private final String queryupdateAndReadCustomPregunta_seleccion = "call updateAndReadCustomPregunta_seleccion(?,?)";
    private final String queryupdateAndReadPregunta_seleccion = "call updateAndReadPregunta_seleccion(?)";
    private final String queryupdatePregunta_seleccion = "call updatePregunta_seleccion(?)";
    private final String queryupdateResetP_seleccion = "call updateResetP_seleccion()";

    public ImpPregunta() {
        Connect connection = new Connect();
        this.connect = connection.getConnect();
    }

    @Override
    public boolean createPregunta(Pregunta pregunta) {
        boolean create = false;
        call = null;
        result = null;
        try {
            call = connect.prepareCall(querycreatePregunta);
            call.setString(1, pregunta.getP_p());
            call.setString(2, pregunta.getP_r1());
            call.setString(3, pregunta.getP_r2());
            call.setString(4, pregunta.getP_r3());
            call.setString(5, pregunta.getP_r4());
            call.setInt(6, pregunta.getP_correcta());
            call.setInt(7, pregunta.getP_dif());
            call.setString(8, pregunta.getP_area());
            call.setBoolean(9, pregunta.isP_seleccion());
            result = call.executeQuery();
            if (result.next()) {
                create = result.getBoolean(1);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            if (call != null) {
                try {
                    call.close();
                } catch (SQLException e) {
                    System.out.println("Error: " + e.getMessage());
                } finally {
                }
            }
        }

        return create;
    }

    @Override
    public Pregunta readRandomPregunta(String p_area, int p_dif) {

        Pregunta pregunta = null;
        call = null;
        result = null;
        try {
            call = connect.prepareCall(queryreadRandomPregunta);
            call.setString(1, p_area);
            call.setInt(2, p_dif);
            result = call.executeQuery();
            if (result.next()) {
                pregunta = new Pregunta();
                pregunta.setP_id(result.getInt(1));
                pregunta.setP_p(result.getString(2));
                pregunta.setP_r1(result.getString(3));
                pregunta.setP_r2(result.getString(4));
                pregunta.setP_r3(result.getString(5));
                pregunta.setP_r4(result.getString(6));
                pregunta.setP_correcta(result.getInt(7));
                pregunta.setP_dif(result.getInt(8));
                pregunta.setP_area(result.getString(9));
                pregunta.setP_seleccion(result.getBoolean(10));
            } else {
                pregunta = null;
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            if (call != null) {
                try {
                    call.close();
                } catch (SQLException e) {
                    System.out.println("Error: " + e.getMessage());
                } finally {
                }
            }
        }

        return pregunta;
    }

    @Override
    public Pregunta updateAndReadCustomPregunta_seleccion(int p_dif, String p_area) {

        Pregunta pregunta = null;
        call = null;
        result = null;
        try {
            call = connect.prepareCall(queryupdateAndReadCustomPregunta_seleccion);
            call.setInt(1, p_dif);
            call.setString(2, p_area);
            result = call.executeQuery();
            if (result.next()) {
                pregunta = new Pregunta();
                pregunta.setP_id(result.getInt(1));
                pregunta.setP_p(result.getString(2));
                pregunta.setP_r1(result.getString(3));
                pregunta.setP_r2(result.getString(4));
                pregunta.setP_r3(result.getString(5));
                pregunta.setP_r4(result.getString(6));
                pregunta.setP_correcta(result.getInt(7));
                pregunta.setP_dif(result.getInt(8));
                pregunta.setP_area(result.getString(9));
                pregunta.setP_seleccion(result.getBoolean(10));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            if (call != null) {
                try {
                    call.close();
                } catch (SQLException e) {
                    System.out.println("Error: " + e.getMessage());
                } finally {
                }
            }
        }
        return pregunta;
    }

    @Override
    public Pregunta updateAndReadPregunta_seleccion(int p_id) {

        Pregunta pregunta = null;
        call = null;
        result = null;
        try {
            call = connect.prepareCall(queryupdateAndReadPregunta_seleccion);
            call.setInt(1, p_id);
            result = call.executeQuery();
            if (result.next()) {
                pregunta = new Pregunta();
                pregunta.setP_id(result.getInt(1));
                pregunta.setP_p(result.getString(2));
                pregunta.setP_r1(result.getString(3));
                pregunta.setP_r2(result.getString(4));
                pregunta.setP_r3(result.getString(5));
                pregunta.setP_r4(result.getString(6));
                pregunta.setP_correcta(result.getInt(7));
                pregunta.setP_dif(result.getInt(8));
                pregunta.setP_area(result.getString(9));
                pregunta.setP_seleccion(result.getBoolean(10));
            } else {
                pregunta = null;
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            if (call != null) {
                try {
                    call.close();
                } catch (SQLException e) {
                    System.out.println("Error: " + e.getMessage());
                } finally {
                }
            }
        }

        return pregunta;
    }

    @Override
    public Pregunta readPregunta(int u_id1, int u_id2, int area_id, int dif) {

        Pregunta pregunta = null;
        final String[] areas = {"", "Arte", "Astronomía", "Biología", "Entretenimiento", "Español", "Física", "Geografía", "Historia de México", "Histora Universal", "Inglés", "Matemáticas", "Química", "Tecnología", "Conocimientos Generales"};
        call = null;
        result = null;
        int p_id = 0;
        int p_count = 0;
        try {
            String area = areas[area_id];
            //Conocimientos Generales y Me Siento con Suerte
            if (dif == 0 && area_id == 14) {
                do {
                    p_id = (int) (Math.random() * 156);
                    call = connect.prepareCall(queryupdateAndReadPregunta_seleccion);
                    call.setInt(1, p_id);
                    result = call.executeQuery();
                    if (result.next()) {
                        pregunta = new Pregunta();
                        pregunta.setP_id(result.getInt(1));
                        pregunta.setP_p(result.getString(2));
                        pregunta.setP_r1(result.getString(3));
                        pregunta.setP_r2(result.getString(4));
                        pregunta.setP_r3(result.getString(5));
                        pregunta.setP_r4(result.getString(6));
                        pregunta.setP_correcta(result.getInt(7));
                        pregunta.setP_dif(result.getInt(8));
                        pregunta.setP_area(result.getString(9));
                        pregunta.setP_seleccion(result.getBoolean(10));
                    }
                } while (pregunta == null);
                //Area y Me Siento con Suerte
            } else if (dif == 0 && area_id != 14) {
                do {
                    dif = (int) (Math.random() * 3);
                    call = connect.prepareCall(queryreadRandomPregunta);
                    call.setString(1, area);
                    call.setInt(2, dif);
                    result = call.executeQuery();
                    if (p_count >= 5) {
                        if (result.next()) {
                            area = result.getString(9);
                        }
                    }
                    if (result.next()) {
                        if (result.getString(9).equals(area)) {
                            pregunta = new Pregunta();
                            pregunta.setP_id(result.getInt(1));
                            pregunta.setP_p(result.getString(2));
                            pregunta.setP_r1(result.getString(3));
                            pregunta.setP_r2(result.getString(4));
                            pregunta.setP_r3(result.getString(5));
                            pregunta.setP_r4(result.getString(6));
                            pregunta.setP_correcta(result.getInt(7));
                            pregunta.setP_dif(result.getInt(8));
                            pregunta.setP_area(result.getString(9));
                            pregunta.setP_seleccion(result.getBoolean(10));
                            updatePregunta_seleccion(result.getInt(1));
                        }
                    }
                    ++p_count;
                } while (pregunta == null);
                //Dificultad y Conocimientos Generales
            } else if (area_id == 14 && dif != 0) {
                do {
                    p_id = (int) Math.random() * 156;
                    call = connect.prepareCall(queryupdateAndReadPregunta_seleccion);
                    call.setInt(1, p_id);
                    result = call.executeQuery();
                    if (result.next()) {
                        if (result.getInt(8) == dif) {
                            pregunta = new Pregunta();
                            pregunta.setP_id(result.getInt(1));
                            pregunta.setP_p(result.getString(2));
                            pregunta.setP_r1(result.getString(3));
                            pregunta.setP_r2(result.getString(4));
                            pregunta.setP_r3(result.getString(5));
                            pregunta.setP_r4(result.getString(6));
                            pregunta.setP_correcta(result.getInt(7));
                            pregunta.setP_dif(result.getInt(8));
                            pregunta.setP_area(result.getString(9));
                            pregunta.setP_seleccion(result.getBoolean(10));
                        }
                    }
                } while (pregunta == null);
                //Area y Dificultad
            } else {
                do {
                    if (p_count >= 4) {
                        if (dif == 3) {
                            p_id = (int) Math.random() * 156;
                            call = connect.prepareCall(queryupdateAndReadPregunta_seleccion);
                            call.setInt(1, p_id);
                            result = call.executeQuery();
                            if (result.next()) {
                                area = result.getString(9);
                                result.beforeFirst();
                            }
                        } else {
                            ++dif;
                            call = connect.prepareCall(queryupdateAndReadCustomPregunta_seleccion);
                            call.setString(1, area);
                            call.setInt(2, dif);
                            result = call.executeQuery();
                        }
                    } else {
                        call = connect.prepareCall(queryupdateAndReadCustomPregunta_seleccion);
                        call.setString(1, area);
                        call.setInt(2, dif);
                        result = call.executeQuery();
                    }
                    if (result.next()) {
                        if (result.getInt(8) == dif && result.getString(9).equals(area)) {
                            pregunta = new Pregunta();
                            pregunta.setP_id(result.getInt(1));
                            pregunta.setP_p(result.getString(2));
                            pregunta.setP_r1(result.getString(3));
                            pregunta.setP_r2(result.getString(4));
                            pregunta.setP_r3(result.getString(5));
                            pregunta.setP_r4(result.getString(6));
                            pregunta.setP_correcta(result.getInt(7));
                            pregunta.setP_dif(result.getInt(8));
                            pregunta.setP_area(result.getString(9));
                            pregunta.setP_seleccion(result.getBoolean(10));
                        }
                    }
                    ++p_count;
                } while (pregunta == null);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            if (call != null) {
                try {
                    call.close();
                } catch (SQLException e) {
                    System.out.println("Error: " + e.getMessage());
                } finally {
                }
            }
        }
        return pregunta;

    }

    @Override
    public boolean updatePregunta_seleccion(int p_id) {

        boolean update = false;
        call = null;
        result = null;
        try {
            call = connect.prepareCall(queryupdatePregunta_seleccion);
            call.setInt(1, p_id);
            result = call.executeQuery();
            if (result.next()) {
                update = result.getBoolean(1);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            if (call != null) {
                try {
                    call.close();
                } catch (SQLException e) {
                    System.out.println("Error: " + e.getMessage());
                } finally {
                }
            }
        }

        return update;
        
    }

    @Override
    public boolean updateResetP_seleccion() {
        
        boolean update = false;
        call = null;
        result = null;
        try {
            call = connect.prepareCall(queryupdateResetP_seleccion);
            result = call.executeQuery();
            if (result.next()) {
                update = result.getBoolean(1);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            if (call != null) {
                try {
                    call.close();
                } catch (SQLException e) {
                    System.out.println("Error: " + e.getMessage());
                } finally {
                }
            }
        }

        return update;
        
    }

}
