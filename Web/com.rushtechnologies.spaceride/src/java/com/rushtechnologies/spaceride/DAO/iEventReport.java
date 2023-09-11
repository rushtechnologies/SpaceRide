package com.rushtechnologies.spaceride.DAO;

import com.rushtechnologies.spaceride.Models.EventReport;
import java.util.List;

/**
 *
 * @author CARLOSHG
 */
public interface iEventReport {

    public boolean createEventReport(EventReport eventReport);
    
    public EventReport readEventReport (int id);

    public List<EventReport> readERUser(int u_id);

    public List<EventReport> readERSupport(int s_id);

    public List<EventReport> readERMaintenance();

    public List<EventReport> readAllER();

    public boolean updateERSupport(String solution, int id);

    public boolean updateERChief(int s_id, int id);

    public boolean updateERMaintenance(int id);

    public boolean updateERUser(int id, String status);

    public boolean deleteEventReport(int id);
}
