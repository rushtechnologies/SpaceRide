package application.android.com.rushtechnologies.spaceride.ImpDAO;

import android.content.Context;

import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperActivityToast;
import com.github.johnpersano.supertoasts.library.utils.PaletteUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import application.android.com.rushtechnologies.spaceride.DAO.AccessSecurity;
import application.android.com.rushtechnologies.spaceride.DAO.AccessUser;
import application.android.com.rushtechnologies.spaceride.Model.User;
import application.android.com.rushtechnologies.spaceride.R;
import application.android.com.rushtechnologies.spaceride.Service.Retrofit.SpaceRideService;
import application.android.com.rushtechnologies.spaceride.Service.Retrofit.SpaceRideServiceModel;
import application.android.com.rushtechnologies.spaceride.Service.Retrofit.SpaceRideServiceProperties;
import retrofit2.Call;
import retrofit2.Response;

public class ImpUser implements AccessUser {
    private final AccessSecurity ACCESS_SECURITY = new ImpSecurity();
    private final SpaceRideService SERVICE = SpaceRideServiceProperties.getRetrofit().create(SpaceRideService.class);

    public ImpUser() {
    }

    @Override
    public boolean createUser(User user) {
        boolean created = false;
        String passwd = ACCESS_SECURITY.encrypt(user.getU_passwd());
        Call<List<SpaceRideServiceModel>> call = SERVICE.createUser(SpaceRideServiceProperties.CREATE_USER_CASE, user.getU_name(), passwd, user.getU_email(), user.getU_type());
        try {
            Response<List<SpaceRideServiceModel>> response = call.execute();
            List<SpaceRideServiceModel> spaceRideServiceModels = response.body();
            SpaceRideServiceModel spaceRideServiceModel = spaceRideServiceModels.get(0);
            created = (Integer.parseInt(spaceRideServiceModel.getVariable1()) != 0);
            System.out.println(created);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error at createUser: " + e.getMessage());
        }
        return created;
    }

    @Override
    public int[] loginUser(String u_nombre, String u_contra) {
        int[] login = new int[2];
        String passwd = ACCESS_SECURITY.encrypt(u_contra);
        Call<List<SpaceRideServiceModel>> call = SERVICE.loginUser(SpaceRideServiceProperties.LOGIN_CASE, u_nombre, passwd);
        try {
            Response<List<SpaceRideServiceModel>> response = call.execute();
            List<SpaceRideServiceModel> spaceRideServiceModels = response.body();
            SpaceRideServiceModel spaceRideServiceModel = spaceRideServiceModels.get(0);
            login[0] = Integer.parseInt(spaceRideServiceModel.getVariable1());
            login[1] = Integer.parseInt(spaceRideServiceModel.getVariable2());
            System.out.println(login[0]);
            System.out.println(login[1]);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error at loginUser: " + e.getMessage());
        }
        return login;
    }

    @Override
    public User readUser(int u_id) {
        User user = null;
        Call<List<SpaceRideServiceModel>> call = SERVICE.readUser(SpaceRideServiceProperties.READ_USER_CASE, u_id);
        try {
            Response<List<SpaceRideServiceModel>> response = call.execute();
            List<SpaceRideServiceModel> spaceRideServiceModels = response.body();
            SpaceRideServiceModel spaceRideServiceModel = spaceRideServiceModels.get(0);
            user = new User(Integer.parseInt(spaceRideServiceModel.getVariable1()), spaceRideServiceModel.getVariable2(), spaceRideServiceModel.getVariable3(), spaceRideServiceModel.getVariable4(), Integer.parseInt(spaceRideServiceModel.getVariable5()), Integer.parseInt(spaceRideServiceModel.getVariable6()), Integer.parseInt(spaceRideServiceModel.getVariable7()));
            System.out.println(user);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error at readUser: " + e.getMessage());
        }
        return user;
    }

    @Override
    public boolean updateUserData(User user) {
        boolean updated = false;
        Call<List<SpaceRideServiceModel>> call = SERVICE.updateUserData(SpaceRideServiceProperties.UPDATE_USER_DATA_CASE, user.getU_id(), user.getU_name(), user.getU_email());
        try {
            Response<List<SpaceRideServiceModel>> response = call.execute();
            List<SpaceRideServiceModel> spaceRideServiceModels = response.body();
            SpaceRideServiceModel spaceRideServiceModel = spaceRideServiceModels.get(0);
            updated = (Integer.parseInt(spaceRideServiceModel.getVariable1()) != 0);
            System.out.println(updated);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error at updateUserData: " + e.getMessage());
        }
        return updated;
    }

    @Override
    public boolean updateUserPsd(int u_id, String u_contra) {
        boolean updated = false;
        String passwd = ACCESS_SECURITY.encrypt(u_contra);
        Call<List<SpaceRideServiceModel>> call = SERVICE.updateUserPsd(SpaceRideServiceProperties.UPDATE_USER_PSD_CASE, u_id, passwd);
        try {
            Response<List<SpaceRideServiceModel>> response = call.execute();
            List<SpaceRideServiceModel> spaceRideServiceModels = response.body();
            SpaceRideServiceModel spaceRideServiceModel = spaceRideServiceModels.get(0);
            updated = (Integer.parseInt(spaceRideServiceModel.getVariable1()) != 0);
            System.out.println(updated);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error at updateUserPsd: " + e.getMessage());
        }
        return updated;
    }

}
