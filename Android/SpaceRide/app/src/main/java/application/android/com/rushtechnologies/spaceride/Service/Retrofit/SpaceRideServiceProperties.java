package application.android.com.rushtechnologies.spaceride.Service.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SpaceRideServiceProperties {
    public static final String IP = "192.168.30.101";
    public static final String BASE_URL = "http://" + IP + "/applications/";
    public static final String SERVICE_NAME = "Android_Migration.php";
    public static Retrofit retrofit = null;
    public static final String ENCRYPT_CASE = "encryptSR";
    public static final String ENCRYPT_ID_CASE = "encryptidSR";
    public static final String DECRYPT_CASE = "decryptSR";
    public static final String CREATE_USER_CASE = "createUser";
    public static final String LOGIN_CASE = "loginUser";
    public static final String READ_USER_CASE = "readUser";
    public static final String UPDATE_USER_DATA_CASE = "updateUserData";
    public static final String UPDATE_USER_PSD_CASE = "updateUserPsd";
    public static final String CREATE_FAQ_CASE = "createFaq";
    public static final String READ_FAQ_CASE = "readFaq";
    public static final String READ_ALL_FAQ_CASE = "readAllFaq";
    public static final String UPDATE_FAQ_ANSWER_CASE = "updateFaq_respuesta";
    public static final String UPDATE_FAQ_APPROVAL_CASE = "updateFaq_approval";
    public static final String DELETE_FAQ_CASE = "deleteFaq";
    public static final String READ_AREA_CASE = "readArea";
    public static final String CREATE_EVENTREPORT_CASE = "createEventReport";
    public static final String READ_EVENTREPORT_CASE = "readEventReport";
    public static final String READ_EVENTREPORT_USER_CASE = "readERUser";
    public static final String READ_EVENTREPORT_MAINTENANCE_CASE = "readERMaintenance";
    public static final String READ_EVENTREPORT_SUPPORT_CASE = "readERSupport";
    public static final String READ_ALL_EVENTREPORT_CASE = "readAllER";
    public static final String UPDATE_EVENTREPORT_USER_CASE = "updateERUser";
    public static final String UPDATE_EVENTREPORT_MAINTENANCE_CASE = "updateERMaintenance";
    public static final String UPDATE_EVENTREPORT_SUPPORT_CASE = "updateERSupport";
    public static final String UPDATE_EVENTREPORT_CHIEF_CASE = "updateERChief";
    public static final String DELETE_EVENTREPORT_CASE = "deleteEventReport";
    public static final String CREATE_MAINTENANCEREPORT_CASE = "createMaintenanceReport";
    public static final String READ_MAINTENANCEREPORT_CASE = "readMaintenanceReport";
    public static final String READ_MAINTENANCEREPORT_DEVELOPER_CASE = "readDeveloperMR";
    public static final String READ_ALL_MAINTENANCEREPORT_CASE = "readAllMR";
    public static final String READ_ALL_MAINTENANCEREPORT_LIST_CASE = "readAllMRList";
    public static final String UPDATE_MAINTENANCEREPORT_DEVELOPER_CASE = "updateMRDeveloper";
    public static final String UPDATE_MAINTENANCEREPORT_EVENT_CASE = "updateMREvent";
    public static final String UPDATE_MAINTENANCEREPORT_CHIEF_CASE = "updateMRChief";
    public static final String DELETE_MAINTENANCEREPORT_CASE = "deleteMaintenanceReport";
    public static final String CASE = "caso";
    public static final String PARAM_1 = "param1";
    public static final String PARAM_2 = "param2";
    public static final String PARAM_3 = "param3";
    public static final String PARAM_4 = "param4";
    public static final String PARAM_5 = "param5";
    public static final String PARAM_6 = "param6";
    public static final String PARAM_7 = "param7";

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
