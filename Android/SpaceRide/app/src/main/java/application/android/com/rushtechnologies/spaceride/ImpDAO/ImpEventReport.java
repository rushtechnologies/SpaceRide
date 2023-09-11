package application.android.com.rushtechnologies.spaceride.ImpDAO;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import application.android.com.rushtechnologies.spaceride.DAO.AccessEventReport;
import application.android.com.rushtechnologies.spaceride.Model.EventReport;

import application.android.com.rushtechnologies.spaceride.Service.Retrofit.SpaceRideService;
import application.android.com.rushtechnologies.spaceride.Service.Retrofit.SpaceRideServiceModel;
import application.android.com.rushtechnologies.spaceride.Service.Retrofit.SpaceRideServiceProperties;
import retrofit2.Call;
import retrofit2.Response;

public class ImpEventReport implements AccessEventReport {
    private final SpaceRideService SERVICE = SpaceRideServiceProperties.getRetrofit().create(SpaceRideService.class);

    public ImpEventReport() {
    }

    @Override
    public boolean createEventReport(EventReport eventReport) {
        boolean created = false;
        Call<List<SpaceRideServiceModel>> call = SERVICE.createEventReport(SpaceRideServiceProperties.CREATE_EVENTREPORT_CASE, eventReport.getER_U_Id(), eventReport.getER_Transcript(), eventReport.getER_Description(), eventReport.getER_Category(), eventReport.getER_S_Id());
        try {
            Response<List<SpaceRideServiceModel>> response = call.execute();
            List<SpaceRideServiceModel> spaceRideServiceModels = response.body();
            SpaceRideServiceModel spaceRideServiceModel = spaceRideServiceModels.get(0);
            created = (Integer.parseInt(spaceRideServiceModel.getVariable1()) != 0);
            System.out.println(created);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error at createEventReport: " + e.getMessage());
        }
        return created;
    }

    @Override
    public EventReport readEventReport(int id) {
        EventReport eventReport = null;
        Call<List<SpaceRideServiceModel>> call = SERVICE.readEventReport(SpaceRideServiceProperties.READ_EVENTREPORT_CASE, id);
        try {
            Response<List<SpaceRideServiceModel>> response = call.execute();
            List<SpaceRideServiceModel> spaceRideServiceModels = response.body();
            SpaceRideServiceModel spaceRideServiceModel = spaceRideServiceModels.get(0);
            eventReport = new EventReport(Integer.parseInt(spaceRideServiceModel.getVariable1()), Integer.parseInt(spaceRideServiceModel.getVariable2()), spaceRideServiceModel.getVariable3(), spaceRideServiceModel.getVariable4(), spaceRideServiceModel.getVariable5(), spaceRideServiceModel.getVariable6(), spaceRideServiceModel.getVariable7(), Integer.parseInt(spaceRideServiceModel.getVariable8()), Timestamp.valueOf(spaceRideServiceModel.getVariable9()));
            System.out.println(eventReport);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error at readEventReport: " + e.getMessage());
        }
        return eventReport;
    }

    @Override
    public List<EventReport> readERUser(int u_id) {
        List<EventReport> eventReports = null;
        Call<List<SpaceRideServiceModel>> call = SERVICE.readERUser(SpaceRideServiceProperties.READ_EVENTREPORT_USER_CASE, u_id);
        try {
            Response<List<SpaceRideServiceModel>> response = call.execute();
            List<SpaceRideServiceModel> spaceRideServiceModels = response.body();
            eventReports = new ArrayList<>();
            for (SpaceRideServiceModel spaceRideServiceModel : spaceRideServiceModels) {
                EventReport eventReport = new EventReport(Integer.parseInt(spaceRideServiceModel.getVariable1()), Integer.parseInt(spaceRideServiceModel.getVariable2()), spaceRideServiceModel.getVariable3(), spaceRideServiceModel.getVariable4(), spaceRideServiceModel.getVariable5(), spaceRideServiceModel.getVariable6(), spaceRideServiceModel.getVariable7(), Integer.parseInt(spaceRideServiceModel.getVariable8()), Timestamp.valueOf(spaceRideServiceModel.getVariable9()));
                eventReports.add(eventReport);
                System.out.println(eventReport.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error at readERUser: " + e.getMessage());
        }
        return eventReports;
    }

    @Override
    public List<EventReport> readERMaintenance() {
        List<EventReport> eventReports = null;
        Call<List<SpaceRideServiceModel>> call = SERVICE.readERMaintenance(SpaceRideServiceProperties.READ_EVENTREPORT_MAINTENANCE_CASE);
        try {
            Response<List<SpaceRideServiceModel>> response = call.execute();
            List<SpaceRideServiceModel> spaceRideServiceModels = response.body();
            eventReports = new ArrayList<>();
            for (SpaceRideServiceModel spaceRideServiceModel : spaceRideServiceModels) {
                EventReport eventReport = new EventReport(Integer.parseInt(spaceRideServiceModel.getVariable1()), Integer.parseInt(spaceRideServiceModel.getVariable2()), spaceRideServiceModel.getVariable3(), spaceRideServiceModel.getVariable4(), spaceRideServiceModel.getVariable5(), spaceRideServiceModel.getVariable6(), spaceRideServiceModel.getVariable7(), Integer.parseInt(spaceRideServiceModel.getVariable8()), Timestamp.valueOf(spaceRideServiceModel.getVariable9()));
                eventReports.add(eventReport);
                System.out.println(eventReport.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error at readERMaintenance: " + e.getMessage());
        }
        return eventReports;
    }

    @Override
    public List<EventReport> readERSupport(int s_id) {
        List<EventReport> eventReports = null;
        Call<List<SpaceRideServiceModel>> call = SERVICE.readERSupport(SpaceRideServiceProperties.READ_EVENTREPORT_SUPPORT_CASE, s_id);
        try {
            Response<List<SpaceRideServiceModel>> response = call.execute();
            List<SpaceRideServiceModel> spaceRideServiceModels = response.body();
            eventReports = new ArrayList<>();
            for (SpaceRideServiceModel spaceRideServiceModel : spaceRideServiceModels) {
                EventReport eventReport = new EventReport(Integer.parseInt(spaceRideServiceModel.getVariable1()), Integer.parseInt(spaceRideServiceModel.getVariable2()), spaceRideServiceModel.getVariable3(), spaceRideServiceModel.getVariable4(), spaceRideServiceModel.getVariable5(), spaceRideServiceModel.getVariable6(), spaceRideServiceModel.getVariable7(), Integer.parseInt(spaceRideServiceModel.getVariable8()), Timestamp.valueOf(spaceRideServiceModel.getVariable9()));
                eventReports.add(eventReport);
                System.out.println(eventReport.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error at readERSupport: " + e.getMessage());
        }
        return eventReports;
    }

    @Override
    public List<EventReport> readAllER() {
        List<EventReport> eventReports = null;
        Call<List<SpaceRideServiceModel>> call = SERVICE.readAllER(SpaceRideServiceProperties.READ_ALL_EVENTREPORT_CASE);
        try {
            Response<List<SpaceRideServiceModel>> response = call.execute();
            List<SpaceRideServiceModel> spaceRideServiceModels = response.body();
            eventReports = new ArrayList<>();
            for (SpaceRideServiceModel spaceRideServiceModel : spaceRideServiceModels) {
                EventReport eventReport = new EventReport(Integer.parseInt(spaceRideServiceModel.getVariable1()), Integer.parseInt(spaceRideServiceModel.getVariable2()), spaceRideServiceModel.getVariable3(), spaceRideServiceModel.getVariable4(), spaceRideServiceModel.getVariable5(), spaceRideServiceModel.getVariable6(), spaceRideServiceModel.getVariable7(), Integer.parseInt(spaceRideServiceModel.getVariable8()), Timestamp.valueOf(spaceRideServiceModel.getVariable9()));
                eventReports.add(eventReport);
                System.out.println(eventReport.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error at readAllER: " + e.getMessage());
        }
        return eventReports;
    }

    @Override
    public boolean updateERUser(int id, String status) {
        boolean updated = false;
        Call<List<SpaceRideServiceModel>> call = SERVICE.updateERUser(SpaceRideServiceProperties.UPDATE_EVENTREPORT_USER_CASE, id, status);
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
    public boolean updateERMaintenance(int id) {
        boolean updated = false;
        Call<List<SpaceRideServiceModel>> call = SERVICE.updateERMaintenance(SpaceRideServiceProperties.UPDATE_EVENTREPORT_MAINTENANCE_CASE, id);
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
    public boolean updateERSupport(int id, String solution) {
        boolean updated = false;
        Call<List<SpaceRideServiceModel>> call = SERVICE.updateERSupport(SpaceRideServiceProperties.UPDATE_EVENTREPORT_SUPPORT_CASE, id, solution);
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
    public boolean updateERChief(int id, int s_id) {
        boolean updated = false;
        Call<List<SpaceRideServiceModel>> call = SERVICE.updateERChief(SpaceRideServiceProperties.UPDATE_EVENTREPORT_CHIEF_CASE, id, s_id);
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
    public boolean deleteEventReport(int id) {
        boolean deleted = false;
        Call<List<SpaceRideServiceModel>> call = SERVICE.deleteEventReport(SpaceRideServiceProperties.DELETE_EVENTREPORT_CASE, id);
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
