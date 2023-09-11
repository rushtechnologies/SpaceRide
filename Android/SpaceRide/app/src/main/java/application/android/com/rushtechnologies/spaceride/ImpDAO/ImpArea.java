package application.android.com.rushtechnologies.spaceride.ImpDAO;

import android.content.Context;

import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperActivityToast;
import com.github.johnpersano.supertoasts.library.utils.PaletteUtils;

import java.util.List;

import application.android.com.rushtechnologies.spaceride.DAO.AccessArea;
import application.android.com.rushtechnologies.spaceride.Model.Area;
import application.android.com.rushtechnologies.spaceride.R;
import application.android.com.rushtechnologies.spaceride.Service.Retrofit.SpaceRideService;
import application.android.com.rushtechnologies.spaceride.Service.Retrofit.SpaceRideServiceModel;
import application.android.com.rushtechnologies.spaceride.Service.Retrofit.SpaceRideServiceProperties;
import retrofit2.Call;
import retrofit2.Response;

public class ImpArea implements AccessArea {
    private final SpaceRideService SERVICE = SpaceRideServiceProperties.getRetrofit().create(SpaceRideService.class);

    public ImpArea() {
    }

    @Override
    public Area readArea(int u_id) {
        Area area = null;
        Call<List<SpaceRideServiceModel>> call = SERVICE.readArea(SpaceRideServiceProperties.READ_AREA_CASE, u_id);
        try {
            Response<List<SpaceRideServiceModel>> response = call.execute();
            List<SpaceRideServiceModel> spaceRideServiceModels = response.body();
            SpaceRideServiceModel spaceRideServiceModel = spaceRideServiceModels.get(0);
            area = new Area(Integer.parseInt(spaceRideServiceModel.getVariable1()), Integer.parseInt(spaceRideServiceModel.getVariable2()), Integer.parseInt(spaceRideServiceModel.getVariable3()), Integer.parseInt(spaceRideServiceModel.getVariable4()), Integer.parseInt(spaceRideServiceModel.getVariable5()), Integer.parseInt(spaceRideServiceModel.getVariable6()), Integer.parseInt(spaceRideServiceModel.getVariable7()), Integer.parseInt(spaceRideServiceModel.getVariable8()), Integer.parseInt(spaceRideServiceModel.getVariable9()), Integer.parseInt(spaceRideServiceModel.getVariable10()), Integer.parseInt(spaceRideServiceModel.getVariable11()), Integer.parseInt(spaceRideServiceModel.getVariable12()), Integer.parseInt(spaceRideServiceModel.getVariable13()), Integer.parseInt(spaceRideServiceModel.getVariable14()), Integer.parseInt(spaceRideServiceModel.getVariable15()), Integer.parseInt(spaceRideServiceModel.getVariable16()));
            System.out.println(area);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error at readArea: " + e.getMessage());
        }
        return area;
    }

}
