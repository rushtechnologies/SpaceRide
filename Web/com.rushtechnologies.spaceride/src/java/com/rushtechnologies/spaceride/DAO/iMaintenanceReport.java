package com.rushtechnologies.spaceride.DAO;

import com.rushtechnologies.spaceride.Models.MaintenanceReport;
import java.util.List;

/**
 *
 * @author CARLOSHG
 */
public interface iMaintenanceReport {

    public boolean createMaintenanceReport(MaintenanceReport maintenanceReport);
    
    public MaintenanceReport readMaintenanceReport(int id);
    
    public List<MaintenanceReport> readAllMR();
    
    public List<MaintenanceReport> readDeveloperMR(int s_id);

    public boolean updateMRChief(int s_id, int id);

    public boolean updateMRDeveloper(String solution, int id);
    
    public boolean deleteMaintenanceReport(int id);
    
}
