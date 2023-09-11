package com.rushtechnologies.spaceride.ImpDAO;

import com.rushtechnologies.spaceride.DAO.iEventReport;
import com.rushtechnologies.spaceride.Database.Connect;
import com.rushtechnologies.spaceride.Models.EventReport;
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
public class ImpEventReport implements iEventReport {

    private final Connection connect;
    private CallableStatement call;
    private ResultSet result;
    private final String querycreateEventReport = "call createEventReport(?,?,?,?,?)";
    private final String queryreadEventReport = "call readEventReport(?)";
    private final String queryreadERUser = "call readERUser(?)";
    private final String queryreadERSupport = "call readERSupport(?)";
    private final String queryreadERMaintenance = "call readERMaintenance()";
    private final String queryreadAllER = "call readAllER()";
    private final String queryupdateERSupport = "call updateERSupport(?,?)";
    private final String queryupdateERChief = "call updateERChief(?,?)";
    private final String queryupdateERMaintenance = "call updateERMaintenance(?)";
    private final String queryupdateERUser = "call updateERUser(?,?)";
    private final String querydeleteEventReport = "call deleteEventReport(?)";

    public ImpEventReport() {
        Connect connection = new Connect();
        this.connect = connection.getConnect();
    }

    @Override
    public boolean createEventReport(EventReport eventReport) {
        
        boolean create = false;
        call = null;
        result = null;
        try {
            call = connect.prepareCall(querycreateEventReport);
            call.setInt(1, eventReport.getER_U_Id());
            call.setString(2, eventReport.getER_Transcript());
            call.setString(3, eventReport.getER_Description());
            call.setString(4, eventReport.getER_Category());
            call.setInt(5, eventReport.getER_S_Id());
            result = call.executeQuery();
            if (result.next()) {
                create = result.getBoolean(1);
            }
        } catch (SQLException e) {
            System.out.println("Error at createEventReport: " + e.getMessage());
        } finally {
            if (call != null) {
                try {
                    call.close();
                } catch (SQLException e) {
                    System.out.println("Error at createEventReport: " + e.getMessage());
                } finally {
                }
            }
        }

        return create;

    }

    @Override
    public EventReport readEventReport(int id) {
        
        EventReport eventReport = null;
        call = null;
        result = null;
        try {
            call = connect.prepareCall(queryreadEventReport);
            call.setInt(1, id);
            result = call.executeQuery();
            if (result.next()) {
                eventReport = new EventReport();
                eventReport.setER_Id(result.getInt(1));
                eventReport.setER_U_Id(result.getInt(2));
                eventReport.setER_Transcript(result.getString(3));
                eventReport.setER_Description(result.getString(4));
                eventReport.setER_Category(result.getString(5));
                eventReport.setER_Status(result.getString(6));
                eventReport.setER_Solution(result.getString(7));
                eventReport.setER_S_Id(result.getInt(8));
                eventReport.setER_Timestamp(result.getTimestamp(9));
            } else{
                eventReport = null;
            }
        } catch (SQLException e) {
            System.out.println("Error at readERUser: " + e.getMessage());
        } finally {
            if (call != null) {
                try {
                    call.close();
                } catch (SQLException e) {
                    System.out.println("Error at readERUser: " + e.getMessage());
                } finally {
                }
            }
        }
        
        return eventReport;

    }

    @Override
    public List<EventReport> readERUser(int u_id) {
        
        List<EventReport> eventReports = new ArrayList<>();
        call = null;
        result = null;
        try {
            call = connect.prepareCall(queryreadERUser);
            call.setInt(1, u_id);
            result = call.executeQuery();
            while (result.next()) {
                EventReport eventReport = new EventReport();
                eventReport.setER_Id(result.getInt(1));
                eventReport.setER_U_Id(result.getInt(2));
                eventReport.setER_Transcript(result.getString(3));
                eventReport.setER_Description(result.getString(4));
                eventReport.setER_Category(result.getString(5));
                eventReport.setER_Status(result.getString(6));
                eventReport.setER_Solution(result.getString(7));
                eventReport.setER_S_Id(result.getInt(8));
                eventReport.setER_Timestamp(result.getTimestamp(9));
                eventReports.add(eventReport);
            }
        } catch (SQLException e) {
            System.out.println("Error at readERUser: " + e.getMessage());
        } finally {
            if (call != null) {
                try {
                    call.close();
                } catch (SQLException e) {
                    System.out.println("Error at readERUser: " + e.getMessage());
                } finally {
                }
            }
        }
        
        return eventReports;

    }

    @Override
    public List<EventReport> readERSupport(int s_id) {
        
        List<EventReport> eventReports = new ArrayList<>();
        call = null;
        result = null;
        try {
            call = connect.prepareCall(queryreadERSupport);
            call.setInt(1, s_id);
            result = call.executeQuery();
            while (result.next()) {
                EventReport eventReport = new EventReport();
                eventReport.setER_Id(result.getInt(1));
                eventReport.setER_U_Id(result.getInt(2));
                eventReport.setER_Transcript(result.getString(3));
                eventReport.setER_Description(result.getString(4));
                eventReport.setER_Category(result.getString(5));
                eventReport.setER_Status(result.getString(6));
                eventReport.setER_Solution(result.getString(7));
                eventReport.setER_S_Id(result.getInt(8));
                eventReport.setER_Timestamp(result.getTimestamp(9));
                eventReports.add(eventReport);
            }
        } catch (SQLException e) {
            System.out.println("Error at readERSupport: " + e.getMessage());
        } finally {
            if (call != null) {
                try {
                    call.close();
                } catch (SQLException e) {
                    System.out.println("Error at readERSupport: " + e.getMessage());
                } finally {
                }
            }
        }
        
        return eventReports;

    }

    @Override
    public List<EventReport> readERMaintenance() {
        
        List<EventReport> eventReports = new ArrayList<>();
        call = null;
        result = null;
        try {
            call = connect.prepareCall(queryreadERMaintenance);
            result = call.executeQuery();
            while (result.next()) {
                EventReport eventReport = new EventReport();
                eventReport.setER_Id(result.getInt(1));
                eventReport.setER_U_Id(result.getInt(2));
                eventReport.setER_Transcript(result.getString(3));
                eventReport.setER_Description(result.getString(4));
                eventReport.setER_Category(result.getString(5));
                eventReport.setER_Status(result.getString(6));
                eventReport.setER_Solution(result.getString(7));
                eventReport.setER_S_Id(result.getInt(8));
                eventReport.setER_Timestamp(result.getTimestamp(9));
                eventReports.add(eventReport);
            }
        } catch (SQLException e) {
            System.out.println("Error at readERMaintenance: " + e.getMessage());
        } finally {
            if (call != null) {
                try {
                    call.close();
                } catch (SQLException e) {
                    System.out.println("Error at readERMaintenance: " + e.getMessage());
                } finally {
                }
            }
        }
        
        return eventReports;

    }

    @Override
    public List<EventReport> readAllER() {
        
        List<EventReport> eventReports = new ArrayList<>();
        call = null;
        result = null;
        try {
            call = connect.prepareCall(queryreadAllER);
            result = call.executeQuery();
            while (result.next()) {
                EventReport eventReport = new EventReport();
                eventReport.setER_Id(result.getInt(1));
                eventReport.setER_U_Id(result.getInt(2));
                eventReport.setER_Transcript(result.getString(3));
                eventReport.setER_Description(result.getString(4));
                eventReport.setER_Category(result.getString(5));
                eventReport.setER_Status(result.getString(6));
                eventReport.setER_Solution(result.getString(7));
                eventReport.setER_S_Id(result.getInt(8));
                eventReport.setER_Timestamp(result.getTimestamp(9));
                eventReports.add(eventReport);
            }
        } catch (SQLException e) {
            System.out.println("Error at readAllER: " + e.getMessage());
        } finally {
            if (call != null) {
                try {
                    call.close();
                } catch (SQLException e) {
                    System.out.println("Error at readAllER: " + e.getMessage());
                } finally {
                }
            }
        }
        
        return eventReports;

    }

    @Override
    public boolean updateERSupport(String solution, int id) {
        
        boolean update = false;
        call = null;
        result = null;
        try {
            call = connect.prepareCall(queryupdateERSupport);
            call.setInt(1, id);
            call.setString(2, solution);
            result = call.executeQuery();
            if (result.next()) {
                update = result.getBoolean(1);
            }
        } catch (SQLException e) {
            System.out.println("Error at updateERSupport: " + e.getMessage());
        } finally {
            if (call != null) {
                try {
                    call.close();
                } catch (SQLException e) {
                    System.out.println("Error at updateERSupport: " + e.getMessage());
                } finally {
                }
            }
        }

        return update;

    }

    @Override
    public boolean updateERChief(int s_id, int id) {
        
        boolean update = false;
        call = null;
        result = null;
        try {
            call = connect.prepareCall(queryupdateERChief);
            call.setInt(1, id);
            call.setInt(2, s_id);
            result = call.executeQuery();
            if (result.next()) {
                update = result.getBoolean(1);
            }
        } catch (SQLException e) {
            System.out.println("Error at updateERChief: " + e.getMessage());
        } finally {
            if (call != null) {
                try {
                    call.close();
                } catch (SQLException e) {
                    System.out.println("Error at updateERChief: " + e.getMessage());
                } finally {
                }
            }
        }

        return update;

    }

    @Override
    public boolean updateERMaintenance(int id) {
        
        boolean update = false;
        call = null;
        result = null;
        try {
            call = connect.prepareCall(queryupdateERMaintenance);
            call.setInt(1, id);
            result = call.executeQuery();
            if (result.next()) {
                update = result.getBoolean(1);
            }
        } catch (SQLException e) {
            System.out.println("Error at updateERMaintenance: " + e.getMessage());
        } finally {
            if (call != null) {
                try {
                    call.close();
                } catch (SQLException e) {
                    System.out.println("Error at updateERMaintenance: " + e.getMessage());
                } finally {
                }
            }
        }

        return update;

    }

    @Override
    public boolean updateERUser(int id, String status) {
        
        boolean update = false;
        call = null;
        result = null;
        try {
            call = connect.prepareCall(queryupdateERUser);
            call.setInt(1, id);
            call.setString(2, status);
            result = call.executeQuery();
            if (result.next()) {
                update = result.getBoolean(1);
            }
        } catch (SQLException e) {
            System.out.println("Error at updateERUser: " + e.getMessage());
        } finally {
            if (call != null) {
                try {
                    call.close();
                } catch (SQLException e) {
                    System.out.println("Error at updateERUser: " + e.getMessage());
                } finally {
                }
            }
        }

        return update;

    }

    @Override
    public boolean deleteEventReport(int id) {
        
        boolean delete = false;
        call = null;
        result = null;
        try{
            call=connect.prepareCall(querydeleteEventReport);
            call.setInt(1, id);
            result = call.executeQuery();
            if (result.next()){
                delete = result.getBoolean(1);
            }
        } catch(SQLException e){
            System.out.println("Error at deleteEventReport: "+e.getMessage());
        }
        finally{
            if(call!=null){
                try{
                    call.close();
                } catch(SQLException e){
                    System.out.println("Error at deleteEventReport: "+e.getMessage());
                }
                finally{
                }
            }
        }
        
        return delete;
        
    }

}
