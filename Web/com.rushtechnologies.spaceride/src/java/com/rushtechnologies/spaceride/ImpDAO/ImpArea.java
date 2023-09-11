package com.rushtechnologies.spaceride.ImpDAO;

import com.rushtechnologies.spaceride.DAO.iArea;
import com.rushtechnologies.spaceride.Database.Connect;
import com.rushtechnologies.spaceride.Models.Area;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author CARLOSHG
 */
public class ImpArea implements iArea {

    private final Connection connect;
    private CallableStatement call;
    private ResultSet result;
    private final String queryreadArea = "call readArea(?)";
    private final String queryreadAreaMax = "call readArea_max(?)";
    private final String queryupdateArea_arte = "call updateArea_arte(?,?,?)";
    private final String queryupdateArea_astro = "call updateArea_astro(?,?,?)";
    private final String queryupdateArea_bio = "call updateArea_bio(?,?,?)";
    private final String queryupdateArea_ent = "call updateArea_ent(?,?,?)";
    private final String queryupdateArea_esp = "call updateArea_esp(?,?,?)";
    private final String queryupdateArea_fis = "call updateArea_fis(?,?,?)";
    private final String queryupdateArea_geo = "call updateArea_geo(?,?,?)";
    private final String queryupdateArea_hmex = "call updateArea_hmex(?,?,?)";
    private final String queryupdateArea_huni = "call updateArea_huni(?,?,?)";
    private final String queryupdateArea_ing = "call updateArea_ing(?,?,?)";
    private final String queryupdateArea_mate = "call updateArea_mate(?,?,?)";
    private final String queryupdateArea_qui = "call updateArea_qui(?,?,?)";
    private final String queryupdateArea_tec = "call updateArea_tec(?,?,?)";

    public ImpArea() {
        Connect connection = new Connect();
        this.connect = connection.getConnect();
    }

    @Override
    public Area readArea(int area_u_id) {

        Area area = null;
        call = null;
        result = null;
        try {
            call = connect.prepareCall(queryreadArea);
            call.setInt(1, area_u_id);
            result = call.executeQuery();
            if (result.next()) {
                area = new Area();
                area.setArea_u_id(result.getInt(1));
                area.setArea_max(result.getInt(2));
                area.setArea_max_id(result.getInt(3));
                area.setArea_mate(result.getInt(4));
                area.setArea_fis(result.getInt(5));
                area.setArea_esp(result.getInt(6));
                area.setArea_huni(result.getInt(7));
                area.setArea_geo(result.getInt(8));
                area.setArea_ing(result.getInt(9));
                area.setArea_qui(result.getInt(10));
                area.setArea_bio(result.getInt(11));
                area.setArea_hmex(result.getInt(12));
                area.setArea_astro(result.getInt(13));
                area.setArea_ent(result.getInt(14));
                area.setArea_arte(result.getInt(15));
                area.setArea_tec(result.getInt(16));
            } else {
                area = null;
            }
        } catch (SQLException e) {
            System.out.println("Error at readArea: " + e.getMessage());
        } finally {
            if (call != null) {
                try {
                    call.close();
                } catch (SQLException e) {
                    System.out.println("Error at readArea: " + e.getMessage());
                } finally {
                }
            }
        }

        return area;

    }

    @Override
    public Area readAreaMax(int area_u_id) {

        Area area = null;
        call = null;
        result = null;
        try {
            call = connect.prepareCall(queryreadAreaMax);
            call.setInt(1, area_u_id);
            result = call.executeQuery();
            if (result.next()) {
                area = new Area();
                area.setArea_max(result.getInt(2));
                area.setArea_max_id(result.getInt(3));
            } else {
                area = null;
            }
        } catch (SQLException e) {
            System.out.println("Error at readAreaMax: " + e.getMessage());
        } finally {
            if (call != null) {
                try {
                    call.close();
                } catch (SQLException e) {
                    System.out.println("Error at readAreaMax: " + e.getMessage());
                } finally {
                }
            }
        }

        return area;

    }

    @Override
    public boolean updateArea_arte(int area_u_id, int area_max, int area_max_id) {

        boolean update = false;
        call = null;
        result = null;
        try {
            call = connect.prepareCall(queryupdateArea_arte);
            call.setInt(1, area_u_id);
            call.setInt(2, area_max);
            call.setInt(3, area_max_id);
            result = call.executeQuery();
            if (result.next()) {
                update = result.getBoolean(1);
            }
        } catch (SQLException e) {
            System.out.println("Error at updateArea_arte: " + e.getMessage());
        } finally {
            if (call != null) {
                try {
                    call.close();
                } catch (SQLException e) {
                    System.out.println("Error at updateArea_arte: " + e.getMessage());
                } finally {
                }
            }
        }

        return update;

    }

    @Override
    public boolean updateArea_astro(int area_u_id, int area_max, int area_max_id) {

        boolean update = false;
        call = null;
        result = null;
        try {
            call = connect.prepareCall(queryupdateArea_astro);
            call.setInt(1, area_u_id);
            call.setInt(2, area_max);
            call.setInt(3, area_max_id);
            result = call.executeQuery();
            if (result.next()) {
                update = result.getBoolean(1);
            }
        } catch (SQLException e) {
            System.out.println("Error at updateArea_astro: " + e.getMessage());
        } finally {
            if (call != null) {
                try {
                    call.close();
                } catch (SQLException e) {
                    System.out.println("Error at updateArea_astro: " + e.getMessage());
                } finally {
                }
            }
        }

        return update;

    }

    @Override
    public boolean updateArea_bio(int area_u_id, int area_max, int area_max_id) {

        boolean update = false;
        call = null;
        result = null;
        try {
            call = connect.prepareCall(queryupdateArea_bio);
            call.setInt(1, area_u_id);
            call.setInt(2, area_max);
            call.setInt(3, area_max_id);
            result = call.executeQuery();
            if (result.next()) {
                update = result.getBoolean(1);
            }
        } catch (SQLException e) {
            System.out.println("Error at updateArea_bio: " + e.getMessage());
        } finally {
            if (call != null) {
                try {
                    call.close();
                } catch (SQLException e) {
                    System.out.println("Error at updateArea_bio: " + e.getMessage());
                } finally {
                }
            }
        }

        return update;

    }

    @Override
    public boolean updateArea_ent(int area_u_id, int area_max, int area_max_id) {

        boolean update = false;
        call = null;
        result = null;
        try {
            call = connect.prepareCall(queryupdateArea_ent);
            call.setInt(1, area_u_id);
            call.setInt(2, area_max);
            call.setInt(3, area_max_id);
            result = call.executeQuery();
            if (result.next()) {
                update = result.getBoolean(1);
            }
        } catch (SQLException e) {
            System.out.println("Error at updateArea_ent: " + e.getMessage());
        } finally {
            if (call != null) {
                try {
                    call.close();
                } catch (SQLException e) {
                    System.out.println("Error at updateArea_ent: " + e.getMessage());
                } finally {
                }
            }
        }

        return update;

    }

    @Override
    public boolean updateArea_esp(int area_u_id, int area_max, int area_max_id) {

        boolean update = false;
        call = null;
        result = null;
        try {
            call = connect.prepareCall(queryupdateArea_esp);
            call.setInt(1, area_u_id);
            call.setInt(2, area_max);
            call.setInt(3, area_max_id);
            result = call.executeQuery();
            if (result.next()) {
                update = result.getBoolean(1);
            }
        } catch (SQLException e) {
            System.out.println("Error at updateArea_esp: " + e.getMessage());
        } finally {
            if (call != null) {
                try {
                    call.close();
                } catch (SQLException e) {
                    System.out.println("Error at updateArea_esp: " + e.getMessage());
                } finally {
                }
            }
        }

        return update;

    }

    @Override
    public boolean updateArea_fis(int area_u_id, int area_max, int area_max_id) {

        boolean update = false;
        call = null;
        result = null;
        try {
            call = connect.prepareCall(queryupdateArea_fis);
            call.setInt(1, area_u_id);
            call.setInt(2, area_max);
            call.setInt(3, area_max_id);
            result = call.executeQuery();
            if (result.next()) {
                update = result.getBoolean(1);
            }
        } catch (SQLException e) {
            System.out.println("Error at updateArea_fis: " + e.getMessage());
        } finally {
            if (call != null) {
                try {
                    call.close();
                } catch (SQLException e) {
                    System.out.println("Error at updateArea_fis: " + e.getMessage());
                } finally {
                }
            }
        }

        return update;

    }

    @Override
    public boolean updateArea_geo(int area_u_id, int area_max, int area_max_id) {

        boolean update = false;
        call = null;
        result = null;
        try {
            call = connect.prepareCall(queryupdateArea_geo);
            call.setInt(1, area_u_id);
            call.setInt(2, area_max);
            call.setInt(3, area_max_id);
            result = call.executeQuery();
            if (result.next()) {
                update = result.getBoolean(1);
            }
        } catch (SQLException e) {
            System.out.println("Error at updateArea_geo: " + e.getMessage());
        } finally {
            if (call != null) {
                try {
                    call.close();
                } catch (SQLException e) {
                    System.out.println("Error at updateArea_geo: " + e.getMessage());
                } finally {
                }
            }
        }

        return update;

    }

    @Override
    public boolean updateArea_hmex(int area_u_id, int area_max, int area_max_id) {

        boolean update = false;
        call = null;
        result = null;
        try {
            call = connect.prepareCall(queryupdateArea_hmex);
            call.setInt(1, area_u_id);
            call.setInt(2, area_max);
            call.setInt(3, area_max_id);
            result = call.executeQuery();
            if (result.next()) {
                update = result.getBoolean(1);
            }
        } catch (SQLException e) {
            System.out.println("Error at updateArea_hmex: " + e.getMessage());
        } finally {
            if (call != null) {
                try {
                    call.close();
                } catch (SQLException e) {
                    System.out.println("Error at updateArea_hmex: " + e.getMessage());
                } finally {
                }
            }
        }

        return update;

    }

    @Override
    public boolean updateArea_huni(int area_u_id, int area_max, int area_max_id) {

        boolean update = false;
        call = null;
        result = null;
        try {
            call = connect.prepareCall(queryupdateArea_huni);
            call.setInt(1, area_u_id);
            call.setInt(2, area_max);
            call.setInt(3, area_max_id);
            result = call.executeQuery();
            if (result.next()) {
                update = result.getBoolean(1);
            }
        } catch (SQLException e) {
            System.out.println("Error at updateArea_huni: " + e.getMessage());
        } finally {
            if (call != null) {
                try {
                    call.close();
                } catch (SQLException e) {
                    System.out.println("Error at updateArea_huni: " + e.getMessage());
                } finally {
                }
            }
        }

        return update;

    }

    @Override
    public boolean updateArea_ing(int area_u_id, int area_max, int area_max_id) {

        boolean update = false;
        call = null;
        result = null;
        try {
            call = connect.prepareCall(queryupdateArea_ing);
            call.setInt(1, area_u_id);
            call.setInt(2, area_max);
            call.setInt(3, area_max_id);
            result = call.executeQuery();
            if (result.next()) {
                update = result.getBoolean(1);
            }
        } catch (SQLException e) {
            System.out.println("Error at updateArea_ing: " + e.getMessage());
        } finally {
            if (call != null) {
                try {
                    call.close();
                } catch (SQLException e) {
                    System.out.println("Error at updateArea_ing: " + e.getMessage());
                } finally {
                }
            }
        }

        return update;

    }

    @Override
    public boolean updateArea_mate(int area_u_id, int area_max, int area_max_id) {

        boolean update = false;
        call = null;
        result = null;
        try {
            call = connect.prepareCall(queryupdateArea_mate);
            call.setInt(1, area_u_id);
            call.setInt(2, area_max);
            call.setInt(3, area_max_id);
            result = call.executeQuery();
            if (result.next()) {
                update = result.getBoolean(1);
            }
        } catch (SQLException e) {
            System.out.println("Error at updateArea_mate: " + e.getMessage());
        } finally {
            if (call != null) {
                try {
                    call.close();
                } catch (SQLException e) {
                    System.out.println("Error at updateArea_mate: " + e.getMessage());
                } finally {
                }
            }
        }

        return update;

    }

    @Override
    public boolean updateArea_qui(int area_u_id, int area_max, int area_max_id) {

        boolean update = false;
        call = null;
        result = null;
        try {
            call = connect.prepareCall(queryupdateArea_qui);
            call.setInt(1, area_u_id);
            call.setInt(2, area_max);
            call.setInt(3, area_max_id);
            result = call.executeQuery();
            if (result.next()) {
                update = result.getBoolean(1);
            }
        } catch (SQLException e) {
            System.out.println("Error at updateArea_qui: " + e.getMessage());
        } finally {
            if (call != null) {
                try {
                    call.close();
                } catch (SQLException e) {
                    System.out.println("Error at updateArea_qui: " + e.getMessage());
                } finally {
                }
            }
        }

        return update;

    }

    @Override
    public boolean updateArea_tec(int area_u_id, int area_max, int area_max_id) {

        boolean update = false;
        call = null;
        result = null;
        try {
            call = connect.prepareCall(queryupdateArea_tec);
            call.setInt(1, area_u_id);
            call.setInt(2, area_max);
            call.setInt(3, area_max_id);
            result = call.executeQuery();
            if (result.next()) {
                update = result.getBoolean(1);
            }
        } catch (SQLException e) {
            System.out.println("Error at updateArea_tec: " + e.getMessage());
        } finally {
            if (call != null) {
                try {
                    call.close();
                } catch (SQLException e) {
                    System.out.println("Error at updateArea_tec: " + e.getMessage());
                } finally {
                }
            }
        }

        return update;

    }

    public boolean updateArea_area(int u_id, String a, Area ar, int area_id) {
        boolean update = false;
        switch (a) {
            case "Matemáticas":
                int mat = ar.getArea_mate() + 1;
                boolean updatemat = updateArea_mate(u_id, mat, area_id);
                if (updatemat) {
                    update = true;
                }
                break;
            case "Física":
                int fis = ar.getArea_fis() + 1;
                boolean updatefis = updateArea_fis(u_id, fis, area_id);
                if (updatefis) {
                    update = true;
                }
                break;
            case "Español":
                int esp = ar.getArea_esp() + 1;
                boolean updateesp = updateArea_esp(u_id, esp, area_id);
                if (updateesp) {
                    update = true;
                }
                break;
            case "Historia Universal":
                int huni = ar.getArea_huni() + 1;
                boolean updatehuni = updateArea_huni(u_id, huni, area_id);
                if (updatehuni) {
                    update = true;
                }
                break;
            case "Geografía":
                int geo = ar.getArea_geo() + 1;
                boolean updategeo = updateArea_geo(u_id, geo, area_id);
                if (updategeo) {
                    update = true;
                }
                break;
            case "Inglés":
                int ing = ar.getArea_ing() + 1;
                boolean updateing = updateArea_ing(u_id, ing, area_id);
                if (updateing) {
                    update = true;
                }
                break;
            case "Química":
                int qui = ar.getArea_qui() + 1;
                boolean updatequi = updateArea_qui(u_id, qui, area_id);
                if (updatequi) {
                    update = true;
                }
                break;
            case "Biología":
                int bio = ar.getArea_bio() + 1;
                boolean updatebio = updateArea_bio(u_id, bio, area_id);
                if (updatebio)  {
                    update = true;
                }
                break;
            case "Historia de México":
                int hmex = ar.getArea_hmex() + 1;
                boolean updatehmex = updateArea_hmex(u_id, hmex, area_id);
                if (updatehmex)  {
                    update = true;
                }
                break;
            case "Astronomía":
                int astro = ar.getArea_astro() + 1;
                boolean updateastro = updateArea_astro(u_id, astro, area_id);
                if (updateastro) {
                    update = true;
                }
                break;
            case "Entretenimiento":
                int ent = ar.getArea_ent() + 1;
                boolean updateent = updateArea_ent(u_id, ent, area_id);
                if (updateent) {
                    update = true;
                }
                break;
            case "Arte":
                int arte = ar.getArea_arte() + 1;
                boolean updatearte = updateArea_arte(u_id, arte, area_id);
                if (updatearte) {
                    update = true;
                }
                break;
            case "Tecnología":
                int tec = ar.getArea_tec() + 1;
                boolean updatetec = updateArea_tec(u_id, tec, area_id);
                if (updatetec) {
                    update = true;
                }
                break;
        }
        return update;
    }

}
