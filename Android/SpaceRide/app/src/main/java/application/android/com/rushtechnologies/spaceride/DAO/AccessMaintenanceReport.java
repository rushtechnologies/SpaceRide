package application.android.com.rushtechnologies.spaceride.DAO;

import java.util.List;

import application.android.com.rushtechnologies.spaceride.Model.MaintenanceReport;

public interface AccessMaintenanceReport {

    boolean createMaintenanceReport(MaintenanceReport maintenanceReport);

    MaintenanceReport readMaintenanceReport(int id);

    List<MaintenanceReport> readDeveloperMR(int s_id);

    List<MaintenanceReport> readAllMR();

    List<MaintenanceReport> readAllMRList();

    boolean updateMRChief(int id, int s_id);

    boolean updateMRDeveloper(int id, String solution);

    boolean updateMREvent(int id, String status);

    boolean deleteMaintenanceReport(int id);

}
