package com.rushtechnologies.spaceride.ImpDAO;

import com.rushtechnologies.spaceride.DAO.iMaintenanceReport;
import com.rushtechnologies.spaceride.Database.Connect;
import com.rushtechnologies.spaceride.Models.MaintenanceReport;
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
public class ImpMaintenanceReport implements iMaintenanceReport {

    private final Connection connect;
    private CallableStatement call;
    private ResultSet result;
    private final String querycreateMaintenanceReport = "call createMaintenanceReport(?,?,?)";
    private final String queryreadMaintenanceReport = "call readMaintenanceReport(?)";
    private final String queryreadDeveloperMR = "call readDeveloperMR(?)";
    private final String queryreadAllMR = "call readAllMR()";
    private final String queryupdateMRChief = "call updateMRChief(?,?)";
    private final String queryupdateMRDeveloper = "call updateMRDeveloper(?,?)";
    private final String querydeleteMaintenanceReport = "call deleteMaintenanceReport(?)";

    public ImpMaintenanceReport() {
        Connect connection = new Connect();
        this.connect = connection.getConnect();
    }

    @Override
    public boolean createMaintenanceReport(MaintenanceReport maintenanceReport) {

        boolean create = false;
        call = null;
        result = null;
        try {
            call = connect.prepareCall(querycreateMaintenanceReport);
            call.setString(1, maintenanceReport.getMR_Module());
            call.setString(2, maintenanceReport.getMR_Description());
            call.setInt(3, maintenanceReport.getMR_S_Id());
            result = call.executeQuery();
            if (result.next()) {
                create = result.getBoolean(1);
            }
        } catch (SQLException e) {
            System.out.println("Error at createMaintenanceReport: " + e.getMessage());
        } finally {
            if (call != null) {
                try {
                    call.close();
                } catch (SQLException e) {
                    System.out.println("Error at createMaintenanceReport: " + e.getMessage());
                } finally {
                }
            }
        }

        return create;

    }

    @Override
    public MaintenanceReport readMaintenanceReport(int id) {

        MaintenanceReport maintenanceReport = null;
        call = null;
        result = null;
        try {
            call = connect.prepareCall(queryreadMaintenanceReport);
            call.setInt(1, id);
            result = call.executeQuery();
            if (result.next()) {
                maintenanceReport = new MaintenanceReport();
                maintenanceReport.setMR_Id(result.getInt(1));
                maintenanceReport.setMR_Module(result.getString(2));
                maintenanceReport.setMR_Description(result.getString(3));
                maintenanceReport.setMR_Status(result.getString(4));
                maintenanceReport.setMR_Solution(result.getString(5));
                maintenanceReport.setMR_S_Id(result.getInt(6));
                maintenanceReport.setMR_Timestamp(result.getTimestamp(7));
            } else {
                maintenanceReport = null;
            }
        } catch (SQLException e) {
            System.out.println("Error at readMaintenanceReport: " + e.getMessage());
        } finally {
            if (call != null) {
                try {
                    call.close();
                } catch (SQLException e) {
                    System.out.println("Error at readMaintenanceReport: " + e.getMessage());
                } finally {
                }
            }
        }

        return maintenanceReport;

    }

    @Override
    public List<MaintenanceReport> readAllMR() {
        
        List<MaintenanceReport> maintenanceReports = new ArrayList<>();
        call = null;
        result = null;
        try {
            call = connect.prepareCall(queryreadAllMR);
            result = call.executeQuery();
            while (result.next()) {
                MaintenanceReport maintenanceReport = new MaintenanceReport();
                maintenanceReport.setMR_Id(result.getInt(1));
                maintenanceReport.setMR_Module(result.getString(2));
                maintenanceReport.setMR_Description(result.getString(3));
                maintenanceReport.setMR_Status(result.getString(4));
                maintenanceReport.setMR_Solution(result.getString(5));
                maintenanceReport.setMR_S_Id(result.getInt(6));
                maintenanceReport.setMR_Timestamp(result.getTimestamp(7));
                maintenanceReports.add(maintenanceReport);
            }
        } catch (SQLException e) {
            System.out.println("Error at readAllMR: " + e.getMessage());
        } finally {
            if (call != null) {
                try {
                    call.close();
                } catch (SQLException e) {
                    System.out.println("Error at readAllMR: " + e.getMessage());
                } finally {
                }
            }
        }

        return maintenanceReports;

    }

    @Override
    public List<MaintenanceReport> readDeveloperMR(int s_id) {
        
        List<MaintenanceReport> maintenanceReports = new ArrayList<>();
        call = null;
        result = null;
        try {
            call = connect.prepareCall(queryreadDeveloperMR);
            call.setInt(1, s_id);
            result = call.executeQuery();
            while (result.next()) {
                MaintenanceReport maintenanceReport = new MaintenanceReport();
                maintenanceReport.setMR_Id(result.getInt(1));
                maintenanceReport.setMR_Module(result.getString(2));
                maintenanceReport.setMR_Description(result.getString(3));
                maintenanceReport.setMR_Status(result.getString(4));
                maintenanceReport.setMR_Solution(result.getString(5));
                maintenanceReport.setMR_S_Id(result.getInt(6));
                maintenanceReport.setMR_Timestamp(result.getTimestamp(7));
                maintenanceReports.add(maintenanceReport);
            }
        } catch (SQLException e) {
            System.out.println("Error at readDeveloperMR: " + e.getMessage());
        } finally {
            if (call != null) {
                try {
                    call.close();
                } catch (SQLException e) {
                    System.out.println("Error at readDeveloperMR: " + e.getMessage());
                } finally {
                }
            }
        }

        return maintenanceReports;

    }

    @Override
    public boolean updateMRChief(int s_id, int id) {
        
        boolean update = false;
        call = null;
        result = null;
        try {
            call = connect.prepareCall(queryupdateMRChief);
            call.setInt(1, id);
            call.setInt(2, s_id);
            result = call.executeQuery();
            if (result.next()) {
                update = result.getBoolean(1);
            }
        } catch (SQLException e) {
            System.out.println("Error at updateMRChief: " + e.getMessage());
        } finally {
            if (call != null) {
                try {
                    call.close();
                } catch (SQLException e) {
                    System.out.println("Error at updateMRChief: " + e.getMessage());
                } finally {
                }
            }
        }

        return update;

    }

    @Override
    public boolean updateMRDeveloper(String solution, int id) {
        
        boolean update = false;
        call = null;
        result = null;
        try {
            call = connect.prepareCall(queryupdateMRDeveloper);
            call.setInt(1, id);
            call.setString(2, solution);
            result = call.executeQuery();
            if (result.next()) {
                update = result.getBoolean(1);
            }
        } catch (SQLException e) {
            System.out.println("Error at updateMRDeveloper: " + e.getMessage());
        } finally {
            if (call != null) {
                try {
                    call.close();
                } catch (SQLException e) {
                    System.out.println("Error at updateMRDeveloper: " + e.getMessage());
                } finally {
                }
            }
        }

        return update;

    }

    @Override
    public boolean deleteMaintenanceReport(int id) {

        boolean delete = false;
        call = null;
        result = null;
        try {
            call = connect.prepareCall(querydeleteMaintenanceReport);
            call.setInt(1, id);
            result = call.executeQuery();
            if (result.next()) {
                delete = result.getBoolean(1);
            }
        } catch (SQLException e) {
            System.out.println("Error at deleteMaintenanceReport: " + e.getMessage());
        } finally {
            if (call != null) {
                try {
                    call.close();
                } catch (SQLException e) {
                    System.out.println("Error at deleteMaintenanceReport: " + e.getMessage());
                } finally {
                }
            }
        }

        return delete;

    }

}
