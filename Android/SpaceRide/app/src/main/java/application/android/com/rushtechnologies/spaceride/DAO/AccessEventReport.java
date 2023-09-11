package application.android.com.rushtechnologies.spaceride.DAO;

import java.util.List;

import application.android.com.rushtechnologies.spaceride.Model.EventReport;

public interface AccessEventReport {

    boolean createEventReport(EventReport eventReport);

    EventReport readEventReport(int id);

    List<EventReport> readERUser(int u_id);

    List<EventReport> readERMaintenance();

    List<EventReport> readERSupport(int s_id);

    List<EventReport> readAllER();

    boolean updateERUser(int id, String status);

    boolean updateERMaintenance(int id);

    boolean updateERSupport(int id, String solution);

    boolean updateERChief(int id, int s_id);

    boolean deleteEventReport(int id);

}
