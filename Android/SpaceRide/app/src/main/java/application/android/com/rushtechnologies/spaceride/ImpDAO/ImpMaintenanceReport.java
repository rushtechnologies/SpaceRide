package application.android.com.rushtechnologies.spaceride.ImpDAO;

import android.content.Context;

import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperActivityToast;
import com.github.johnpersano.supertoasts.library.utils.PaletteUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import application.android.com.rushtechnologies.spaceride.DAO.AccessMaintenanceReport;
import application.android.com.rushtechnologies.spaceride.Model.MaintenanceReport;
import application.android.com.rushtechnologies.spaceride.R;
import application.android.com.rushtechnologies.spaceride.Service.Retrofit.SpaceRideService;
import application.android.com.rushtechnologies.spaceride.Service.Retrofit.SpaceRideServiceModel;
import application.android.com.rushtechnologies.spaceride.Service.Retrofit.SpaceRideServiceProperties;
import retrofit2.Call;
import retrofit2.Response;

public class ImpMaintenanceReport implements AccessMaintenanceReport {
    private final SpaceRideService SERVICE = SpaceRideServiceProperties.getRetrofit().create(SpaceRideService.class);

    public ImpMaintenanceReport() {
    }

    @Override
    public boolean createMaintenanceReport(MaintenanceReport maintenanceReport) {
        boolean created = false;
        Call<List<SpaceRideServiceModel>> call = SERVICE.createMaintenanceReport(SpaceRideServiceProperties.CREATE_MAINTENANCEREPORT_CASE, maintenanceReport.getMR_Module(), maintenanceReport.getMR_Description(), maintenanceReport.getMR_S_Id());
        try {
            Response<List<SpaceRideServiceModel>> response = call.execute();
            List<SpaceRideServiceModel> spaceRideServiceModels = response.body();
            SpaceRideServiceModel spaceRideServiceModel = spaceRideServiceModels.get(0);
            created = (Integer.parseInt(spaceRideServiceModel.getVariable1()) != 0);
            System.out.println(created);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error at createMaintenanceReport: " + e.getMessage());
        }
        return created;
    }

    @Override
    public MaintenanceReport readMaintenanceReport(int id) {
        MaintenanceReport maintenanceReport = null;
        Call<List<SpaceRideServiceModel>> call = SERVICE.readMaintenanceReport(SpaceRideServiceProperties.READ_MAINTENANCEREPORT_CASE, id);
        try {
            Response<List<SpaceRideServiceModel>> response = call.execute();
            List<SpaceRideServiceModel> spaceRideServiceModels = response.body();
            SpaceRideServiceModel spaceRideServiceModel = spaceRideServiceModels.get(0);
            maintenanceReport = new MaintenanceReport(Integer.parseInt(spaceRideServiceModel.getVariable1()), spaceRideServiceModel.getVariable2(), spaceRideServiceModel.getVariable3(), spaceRideServiceModel.getVariable4(), spaceRideServiceModel.getVariable5(), Integer.parseInt(spaceRideServiceModel.getVariable6()), Timestamp.valueOf(spaceRideServiceModel.getVariable7()));
            System.out.println(maintenanceReport);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error at readMaintenanceReport: " + e.getMessage());
        }
        return maintenanceReport;
    }

    @Override
    public List<MaintenanceReport> readDeveloperMR(int s_id) {
        List<MaintenanceReport> maintenanceReports = null;
        Call<List<SpaceRideServiceModel>> call = SERVICE.readDeveloperMR(SpaceRideServiceProperties.READ_MAINTENANCEREPORT_DEVELOPER_CASE, s_id);
        try {
            Response<List<SpaceRideServiceModel>> response = call.execute();
            List<SpaceRideServiceModel> spaceRideServiceModels = response.body();
            maintenanceReports = new ArrayList<>();
            for (SpaceRideServiceModel spaceRideServiceModel : spaceRideServiceModels) {
                MaintenanceReport maintenanceReport = new MaintenanceReport(Integer.parseInt(spaceRideServiceModel.getVariable1()), spaceRideServiceModel.getVariable2(), spaceRideServiceModel.getVariable3(), spaceRideServiceModel.getVariable4(), spaceRideServiceModel.getVariable5(), Integer.parseInt(spaceRideServiceModel.getVariable6()), Timestamp.valueOf(spaceRideServiceModel.getVariable7()));
                maintenanceReports.add(maintenanceReport);
                System.out.println(maintenanceReport.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error at readDeveloperMR: " + e.getMessage());
        }
        return maintenanceReports;
    }

    @Override
    public List<MaintenanceReport> readAllMR() {
        List<MaintenanceReport> maintenanceReports = null;
        Call<List<SpaceRideServiceModel>> call = SERVICE.readAllMR(SpaceRideServiceProperties.READ_ALL_MAINTENANCEREPORT_CASE);
        try {
            Response<List<SpaceRideServiceModel>> response = call.execute();
            List<SpaceRideServiceModel> spaceRideServiceModels = response.body();
            maintenanceReports = new ArrayList<>();
            for (SpaceRideServiceModel spaceRideServiceModel : spaceRideServiceModels) {
                MaintenanceReport maintenanceReport = new MaintenanceReport(Integer.parseInt(spaceRideServiceModel.getVariable1()), spaceRideServiceModel.getVariable2(), spaceRideServiceModel.getVariable3(), spaceRideServiceModel.getVariable4(), spaceRideServiceModel.getVariable5(), Integer.parseInt(spaceRideServiceModel.getVariable6()), Timestamp.valueOf(spaceRideServiceModel.getVariable7()));
                maintenanceReports.add(maintenanceReport);
                System.out.println(maintenanceReport.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error at readAllMR: " + e.getMessage());
        }
        return maintenanceReports;
    }

    @Override
    public List<MaintenanceReport> readAllMRList() {
        List<MaintenanceReport> maintenanceReports = null;
        Call<List<SpaceRideServiceModel>> call = SERVICE.readAllMRList(SpaceRideServiceProperties.READ_ALL_MAINTENANCEREPORT_LIST_CASE);
        try {
            Response<List<SpaceRideServiceModel>> response = call.execute();
            List<SpaceRideServiceModel> spaceRideServiceModels = response.body();
            maintenanceReports = new ArrayList<>();
            for (SpaceRideServiceModel spaceRideServiceModel : spaceRideServiceModels) {
                MaintenanceReport maintenanceReport = new MaintenanceReport(Integer.parseInt(spaceRideServiceModel.getVariable1()), spaceRideServiceModel.getVariable2(), spaceRideServiceModel.getVariable3(), spaceRideServiceModel.getVariable4(), spaceRideServiceModel.getVariable5(), Integer.parseInt(spaceRideServiceModel.getVariable6()), Timestamp.valueOf(spaceRideServiceModel.getVariable7()));
                maintenanceReports.add(maintenanceReport);
                System.out.println(maintenanceReport.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error at readAllMRList: " + e.getMessage());
        }
        return maintenanceReports;
    }

    @Override
    public boolean updateMRChief(int id, int s_id) {
        boolean updated = false;
        Call<List<SpaceRideServiceModel>> call = SERVICE.updateMRChief(SpaceRideServiceProperties.UPDATE_MAINTENANCEREPORT_CHIEF_CASE, id, s_id);
        try {
            Response<List<SpaceRideServiceModel>> response = call.execute();
            List<SpaceRideServiceModel> spaceRideServiceModels = response.body();
            SpaceRideServiceModel spaceRideServiceModel = spaceRideServiceModels.get(0);
            updated = (Integer.parseInt(spaceRideServiceModel.getVariable1()) != 0);
            System.out.println(updated);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error at deleteFaq: " + e.getMessage());
        }
        return updated;
    }

    @Override
    public boolean updateMRDeveloper(int id, String solution) {
        boolean updated = false;
        Call<List<SpaceRideServiceModel>> call = SERVICE.updateMRDeveloper(SpaceRideServiceProperties.UPDATE_MAINTENANCEREPORT_DEVELOPER_CASE, id, solution);
        try {
            Response<List<SpaceRideServiceModel>> response = call.execute();
            List<SpaceRideServiceModel> spaceRideServiceModels = response.body();
            SpaceRideServiceModel spaceRideServiceModel = spaceRideServiceModels.get(0);
            updated = (Integer.parseInt(spaceRideServiceModel.getVariable1()) != 0);
            System.out.println(updated);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error at deleteFaq: " + e.getMessage());
        }
        return updated;
    }

    @Override
    public boolean updateMREvent(int id, String status) {
        boolean updated = false;
        Call<List<SpaceRideServiceModel>> call = SERVICE.updateMREvent(SpaceRideServiceProperties.UPDATE_MAINTENANCEREPORT_EVENT_CASE, id, status);
        try {
            Response<List<SpaceRideServiceModel>> response = call.execute();
            List<SpaceRideServiceModel> spaceRideServiceModels = response.body();
            SpaceRideServiceModel spaceRideServiceModel = spaceRideServiceModels.get(0);
            updated = (Integer.parseInt(spaceRideServiceModel.getVariable1()) != 0);
            System.out.println(updated);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error at updateMREvent: " + e.getMessage());
        }
        return updated;
    }

    @Override
    public boolean deleteMaintenanceReport(int id) {
        boolean deleted = false;
        Call<List<SpaceRideServiceModel>> call = SERVICE.deleteMaintenanceReport(SpaceRideServiceProperties.DELETE_MAINTENANCEREPORT_CASE, id);
        try {
            Response<List<SpaceRideServiceModel>> response = call.execute();
            List<SpaceRideServiceModel> spaceRideServiceModels = response.body();
            SpaceRideServiceModel spaceRideServiceModel = spaceRideServiceModels.get(0);
            deleted = (Integer.parseInt(spaceRideServiceModel.getVariable1()) != 0);
            System.out.println(deleted);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error at deleteFaq: " + e.getMessage());
        }
        return deleted;
    }

}
