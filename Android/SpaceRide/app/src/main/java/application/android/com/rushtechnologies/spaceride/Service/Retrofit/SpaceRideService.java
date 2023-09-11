package application.android.com.rushtechnologies.spaceride.Service.Retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Retrpfit2 REST Api Service Client for Spaceride database interactions
 *
 * Access to SpaceRide database Stored Procesures
 *
 * Todos los derechos reservados Rush Technologies S.A. de C.V. © 2019
 *
 * Java version 8.211
 *
 * LICENSE: This source file is subject to version 3.01 of the PHP license
 * that is available through the world-wide-web at the following URI:
 * http://www.php.net/license/3_01.txt.  If you did not receive a copy of
 * the PHP License and are unable to obtain it through the web, please
 * send a note to license@php.net so we can mail you a copy immediately.
 *
 * @category   REST Api Service Client
 * @package    application.android.com.rushtechnologies.spaceride.Service
 * @author     Carlos Huerta García <huerta2502@gmail.com>
 * @copyright  2019 © Rush Technologies S.A. de C.V.
 * @license    http://www.php.net/license/3_01.txt  PHP License 3.01
 */

public interface SpaceRideService {

    @GET(SpaceRideServiceProperties.SERVICE_NAME)
    Call<List<SpaceRideServiceModel>> encryptSR(@Query(SpaceRideServiceProperties.CASE) String call, @Query(SpaceRideServiceProperties.PARAM_1) String toEncrypt);

    @GET(SpaceRideServiceProperties.SERVICE_NAME)
    Call<List<SpaceRideServiceModel>> encryptIdSR(@Query(SpaceRideServiceProperties.CASE) String call, @Query(SpaceRideServiceProperties.PARAM_1) int toEncrypt);

    @GET(SpaceRideServiceProperties.SERVICE_NAME)
    Call<List<SpaceRideServiceModel>> decryptSR(@Query(SpaceRideServiceProperties.CASE) String call, @Query(SpaceRideServiceProperties.PARAM_1) String encrypted);

    @GET(SpaceRideServiceProperties.SERVICE_NAME)
    Call<List<SpaceRideServiceModel>> createUser(@Query(SpaceRideServiceProperties.CASE) String call, @Query(SpaceRideServiceProperties.PARAM_1) String name, @Query(SpaceRideServiceProperties.PARAM_2) String passwd, @Query(SpaceRideServiceProperties.PARAM_3) String mail, @Query(SpaceRideServiceProperties.PARAM_4) int type);

    @GET(SpaceRideServiceProperties.SERVICE_NAME)
    Call<List<SpaceRideServiceModel>> loginUser(@Query(SpaceRideServiceProperties.CASE) String call, @Query(SpaceRideServiceProperties.PARAM_1) String name, @Query(SpaceRideServiceProperties.PARAM_2) String passwd);

    @GET(SpaceRideServiceProperties.SERVICE_NAME)
    Call<List<SpaceRideServiceModel>> readUser(@Query(SpaceRideServiceProperties.CASE) String call, @Query(SpaceRideServiceProperties.PARAM_1) int id);

    @GET(SpaceRideServiceProperties.SERVICE_NAME)
    Call<List<SpaceRideServiceModel>> updateUserData(@Query(SpaceRideServiceProperties.CASE) String call, @Query(SpaceRideServiceProperties.PARAM_1) int id, @Query(SpaceRideServiceProperties.PARAM_2) String name, @Query(SpaceRideServiceProperties.PARAM_3) String mail);

    @GET(SpaceRideServiceProperties.SERVICE_NAME)
    Call<List<SpaceRideServiceModel>> updateUserPsd(@Query(SpaceRideServiceProperties.CASE) String call, @Query(SpaceRideServiceProperties.PARAM_1) int id, @Query(SpaceRideServiceProperties.PARAM_2) String passwd);

    @GET(SpaceRideServiceProperties.SERVICE_NAME)
    Call<List<SpaceRideServiceModel>> createFaq(@Query(SpaceRideServiceProperties.CASE) String call, @Query(SpaceRideServiceProperties.PARAM_1) String question, @Query(SpaceRideServiceProperties.PARAM_2) String answer, @Query(SpaceRideServiceProperties.PARAM_3) String topic, @Query(SpaceRideServiceProperties.PARAM_4) int priority, @Query(SpaceRideServiceProperties.PARAM_5) int approval, @Query(SpaceRideServiceProperties.PARAM_6) int u_id, @Query(SpaceRideServiceProperties.PARAM_7) int s_id);

    @GET(SpaceRideServiceProperties.SERVICE_NAME)
    Call<List<SpaceRideServiceModel>> readFaq(@Query(SpaceRideServiceProperties.CASE) String call, @Query(SpaceRideServiceProperties.PARAM_1) int id);

    @GET(SpaceRideServiceProperties.SERVICE_NAME)
    Call<List<SpaceRideServiceModel>> readAllFaq(@Query(SpaceRideServiceProperties.CASE) String call);

    @GET(SpaceRideServiceProperties.SERVICE_NAME)
    Call<List<SpaceRideServiceModel>> deleteFaq(@Query(SpaceRideServiceProperties.CASE) String call, @Query(SpaceRideServiceProperties.PARAM_1) int id);

    @GET(SpaceRideServiceProperties.SERVICE_NAME)
    Call<List<SpaceRideServiceModel>> updateFaq_respuesta(@Query(SpaceRideServiceProperties.CASE) String call, @Query(SpaceRideServiceProperties.PARAM_1) int id, @Query(SpaceRideServiceProperties.PARAM_2) String answer, @Query(SpaceRideServiceProperties.PARAM_3) int s_id);

    @GET(SpaceRideServiceProperties.SERVICE_NAME)
    Call<List<SpaceRideServiceModel>> updateFaq_approval(@Query(SpaceRideServiceProperties.CASE) String call, @Query(SpaceRideServiceProperties.PARAM_1) int id, @Query(SpaceRideServiceProperties.PARAM_2) int approval);

    @GET(SpaceRideServiceProperties.SERVICE_NAME)
    Call<List<SpaceRideServiceModel>> readArea(@Query(SpaceRideServiceProperties.CASE) String call, @Query(SpaceRideServiceProperties.PARAM_1) int id);

    @GET(SpaceRideServiceProperties.SERVICE_NAME)
    Call<List<SpaceRideServiceModel>> createEventReport(@Query(SpaceRideServiceProperties.CASE) String call, @Query(SpaceRideServiceProperties.PARAM_1) int u_id, @Query(SpaceRideServiceProperties.PARAM_2) String transcript, @Query(SpaceRideServiceProperties.PARAM_3) String description, @Query(SpaceRideServiceProperties.PARAM_4) String category, @Query(SpaceRideServiceProperties.PARAM_5) int s_id);

    @GET(SpaceRideServiceProperties.SERVICE_NAME)
    Call<List<SpaceRideServiceModel>> readEventReport(@Query(SpaceRideServiceProperties.CASE) String call, @Query(SpaceRideServiceProperties.PARAM_1) int id);

    @GET(SpaceRideServiceProperties.SERVICE_NAME)
    Call<List<SpaceRideServiceModel>> readERUser(@Query(SpaceRideServiceProperties.CASE) String call, @Query(SpaceRideServiceProperties.PARAM_1) int u_id);

    @GET(SpaceRideServiceProperties.SERVICE_NAME)
    Call<List<SpaceRideServiceModel>> readERMaintenance(@Query(SpaceRideServiceProperties.CASE) String call);

    @GET(SpaceRideServiceProperties.SERVICE_NAME)
    Call<List<SpaceRideServiceModel>> readERSupport(@Query(SpaceRideServiceProperties.CASE) String call, @Query(SpaceRideServiceProperties.PARAM_1) int s_id);

    @GET(SpaceRideServiceProperties.SERVICE_NAME)
    Call<List<SpaceRideServiceModel>> readAllER(@Query(SpaceRideServiceProperties.CASE) String call);

    @GET(SpaceRideServiceProperties.SERVICE_NAME)
    Call<List<SpaceRideServiceModel>> updateERUser(@Query(SpaceRideServiceProperties.CASE) String call, @Query(SpaceRideServiceProperties.PARAM_1) int id, @Query(SpaceRideServiceProperties.PARAM_2) String status);

    @GET(SpaceRideServiceProperties.SERVICE_NAME)
    Call<List<SpaceRideServiceModel>> updateERMaintenance(@Query(SpaceRideServiceProperties.CASE) String call, @Query(SpaceRideServiceProperties.PARAM_1) int id);

    @GET(SpaceRideServiceProperties.SERVICE_NAME)
    Call<List<SpaceRideServiceModel>> updateERSupport(@Query(SpaceRideServiceProperties.CASE) String call, @Query(SpaceRideServiceProperties.PARAM_1) int id, @Query(SpaceRideServiceProperties.PARAM_2) String solution);

    @GET(SpaceRideServiceProperties.SERVICE_NAME)
    Call<List<SpaceRideServiceModel>> updateERChief(@Query(SpaceRideServiceProperties.CASE) String call, @Query(SpaceRideServiceProperties.PARAM_1) int id, @Query(SpaceRideServiceProperties.PARAM_2) int s_id);

    @GET(SpaceRideServiceProperties.SERVICE_NAME)
    Call<List<SpaceRideServiceModel>> deleteEventReport(@Query(SpaceRideServiceProperties.CASE) String call, @Query(SpaceRideServiceProperties.PARAM_1) int id);

    @GET(SpaceRideServiceProperties.SERVICE_NAME)
    Call<List<SpaceRideServiceModel>> createMaintenanceReport(@Query(SpaceRideServiceProperties.CASE) String call, @Query(SpaceRideServiceProperties.PARAM_1) String module, @Query(SpaceRideServiceProperties.PARAM_2) String description, @Query(SpaceRideServiceProperties.PARAM_3) int s_id);

    @GET(SpaceRideServiceProperties.SERVICE_NAME)
    Call<List<SpaceRideServiceModel>> readMaintenanceReport(@Query(SpaceRideServiceProperties.CASE) String call, @Query(SpaceRideServiceProperties.PARAM_1) int id);

    @GET(SpaceRideServiceProperties.SERVICE_NAME)
    Call<List<SpaceRideServiceModel>> readDeveloperMR(@Query(SpaceRideServiceProperties.CASE) String call, @Query(SpaceRideServiceProperties.PARAM_1) int id);

    @GET(SpaceRideServiceProperties.SERVICE_NAME)
    Call<List<SpaceRideServiceModel>> readAllMR(@Query(SpaceRideServiceProperties.CASE) String call);

    @GET(SpaceRideServiceProperties.SERVICE_NAME)
    Call<List<SpaceRideServiceModel>> readAllMRList(@Query(SpaceRideServiceProperties.CASE) String call);

    @GET(SpaceRideServiceProperties.SERVICE_NAME)
    Call<List<SpaceRideServiceModel>> updateMRChief(@Query(SpaceRideServiceProperties.CASE) String call, @Query(SpaceRideServiceProperties.PARAM_1) int id, @Query(SpaceRideServiceProperties.PARAM_2) int s_id);

    @GET(SpaceRideServiceProperties.SERVICE_NAME)
    Call<List<SpaceRideServiceModel>> updateMRDeveloper(@Query(SpaceRideServiceProperties.CASE) String call, @Query(SpaceRideServiceProperties.PARAM_1) int id, @Query(SpaceRideServiceProperties.PARAM_2) String solution);

    @GET(SpaceRideServiceProperties.SERVICE_NAME)
    Call<List<SpaceRideServiceModel>> updateMREvent(@Query(SpaceRideServiceProperties.CASE) String call, @Query(SpaceRideServiceProperties.PARAM_1) int id, @Query(SpaceRideServiceProperties.PARAM_2) String status);

    @GET(SpaceRideServiceProperties.SERVICE_NAME)
    Call<List<SpaceRideServiceModel>> deleteMaintenanceReport(@Query(SpaceRideServiceProperties.CASE) String call, @Query(SpaceRideServiceProperties.PARAM_1) int id);

}
