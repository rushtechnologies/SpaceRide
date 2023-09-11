package application.android.com.rushtechnologies.spaceride.ImpDAO;

import android.content.Context;

import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperActivityToast;
import com.github.johnpersano.supertoasts.library.utils.PaletteUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import application.android.com.rushtechnologies.spaceride.DAO.AccessSecurity;
import application.android.com.rushtechnologies.spaceride.R;
import application.android.com.rushtechnologies.spaceride.Service.Retrofit.SpaceRideService;
import application.android.com.rushtechnologies.spaceride.Service.Retrofit.SpaceRideServiceModel;
import application.android.com.rushtechnologies.spaceride.Service.Retrofit.SpaceRideServiceProperties;
import retrofit2.Call;
import retrofit2.Response;

public class ImpSecurity implements AccessSecurity {
    public final SpaceRideService SERVICE = SpaceRideServiceProperties.getRetrofit().create(SpaceRideService.class);

    public ImpSecurity() {
    }

    @Override
    public String encrypt(String toEncrypt) {
        String encrypted = "";
        Call<List<SpaceRideServiceModel>> call = SERVICE.encryptSR(SpaceRideServiceProperties.ENCRYPT_CASE, toEncrypt);
        try {
            Response<List<SpaceRideServiceModel>> response = call.execute();
            List<SpaceRideServiceModel> spaceRideServiceModels = response.body();
            encrypted = spaceRideServiceModels.get(0).getVariable1();
            System.out.println(encrypted);
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("Error at encrypt: " + e.getMessage());
        }
        return encrypted;
    }

    @Override
    public String encrypt(int toEncrypt) {
        String encrypted = "";
        Call<List<SpaceRideServiceModel>> call = SERVICE.encryptIdSR(SpaceRideServiceProperties.ENCRYPT_ID_CASE, toEncrypt);
        try {
            Response<List<SpaceRideServiceModel>> response = call.execute();
            List<SpaceRideServiceModel> spaceRideServiceModels = response.body();
            encrypted = spaceRideServiceModels.get(0).getVariable1();
            System.out.println(encrypted);
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("Error at encrypt: " + e.getMessage());
        }
        return encrypted;
    }

    @Override
    public String decrypt(String encrypted) {
        String decrypted = "";
        Call<List<SpaceRideServiceModel>> call = SERVICE.decryptSR(SpaceRideServiceProperties.DECRYPT_CASE, encrypted);
        try {
            Response<List<SpaceRideServiceModel>> response = call.execute();
            List<SpaceRideServiceModel> spaceRideServiceModels = response.body();
            decrypted = spaceRideServiceModels.get(0).getVariable1();
            System.out.println(encrypted);
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("Error at decrypt: " + e.getMessage());
        }
        return decrypted;
    }
}
