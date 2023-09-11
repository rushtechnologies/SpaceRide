package application.android.com.rushtechnologies.spaceride.ImpDAO;

import java.util.ArrayList;
import java.util.List;

import application.android.com.rushtechnologies.spaceride.DAO.AccessFaq;
import application.android.com.rushtechnologies.spaceride.Model.Faq;
import application.android.com.rushtechnologies.spaceride.Service.Retrofit.SpaceRideService;
import application.android.com.rushtechnologies.spaceride.Service.Retrofit.SpaceRideServiceModel;
import application.android.com.rushtechnologies.spaceride.Service.Retrofit.SpaceRideServiceProperties;
import retrofit2.Call;
import retrofit2.Response;

public class ImpFaq implements AccessFaq {
    private final SpaceRideService SERVICE = SpaceRideServiceProperties.getRetrofit().create(SpaceRideService.class);

    public ImpFaq() {
    }

    @Override
    public boolean createFaq(Faq faq) {
        boolean created = false;
        Call<List<SpaceRideServiceModel>> call = SERVICE.createFaq(SpaceRideServiceProperties.CREATE_FAQ_CASE, faq.getFaq_pregunta(), faq.getFaq_respuesta(), faq.getFaq_tema(), faq.getFaq_prioridad(), (faq.isFaq_aprobacion() ? 1 : 0), faq.getFaq_u_id(), faq.getFaq_a_id());
        try {
            Response<List<SpaceRideServiceModel>> response = call.execute();
            List<SpaceRideServiceModel> spaceRideServiceModels = response.body();
            SpaceRideServiceModel spaceRideServiceModel = spaceRideServiceModels.get(0);
            created = (Integer.parseInt(spaceRideServiceModel.getVariable1()) != 0);
            System.out.println(created);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error at createFaq: " + e.getMessage());
        }
        return created;
    }

    @Override
    public Faq readFaq(int faq_id) {
        Faq faq = null;
        Call<List<SpaceRideServiceModel>> call = SERVICE.readFaq(SpaceRideServiceProperties.READ_FAQ_CASE, faq_id);
        try {
            Response<List<SpaceRideServiceModel>> response = call.execute();
            List<SpaceRideServiceModel> spaceRideServiceModels = response.body();
            SpaceRideServiceModel spaceRideServiceModel = spaceRideServiceModels.get(0);
            faq = new Faq(Integer.parseInt(spaceRideServiceModel.getVariable1()), spaceRideServiceModel.getVariable2(), spaceRideServiceModel.getVariable3(), spaceRideServiceModel.getVariable4(), Integer.parseInt(spaceRideServiceModel.getVariable5()), ( Integer.parseInt(spaceRideServiceModel.getVariable6()) != 0 ), Integer.parseInt(spaceRideServiceModel.getVariable7()), Integer.parseInt(spaceRideServiceModel.getVariable8()));
            System.out.println(faq);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error at readFaq: " + e.getMessage());
        }
        return faq;
    }

    @Override
    public List<Faq> readAllFaq() {
        List<Faq> faqs = null;
        Call<List<SpaceRideServiceModel>> call = SERVICE.readAllFaq(SpaceRideServiceProperties.READ_ALL_FAQ_CASE);
        try {
            Response<List<SpaceRideServiceModel>> response = call.execute();
            List<SpaceRideServiceModel> spaceRideServiceModels = response.body();
            faqs = new ArrayList<>();
            for (SpaceRideServiceModel spaceRideServiceModel : spaceRideServiceModels) {
                Faq faq = new Faq(Integer.parseInt(spaceRideServiceModel.getVariable1()), spaceRideServiceModel.getVariable2(), spaceRideServiceModel.getVariable3(), spaceRideServiceModel.getVariable4(), Integer.parseInt(spaceRideServiceModel.getVariable5()), ( Integer.parseInt(spaceRideServiceModel.getVariable6()) != 0 ), Integer.parseInt(spaceRideServiceModel.getVariable7()), Integer.parseInt(spaceRideServiceModel.getVariable8()));
                faqs.add(faq);
                System.out.println(faq.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error at readAllFaq: " + e.getMessage());
        }
        return faqs;
    }

    @Override
    public boolean updateFaq_respuesta(Faq faq) {
        boolean updated = false;
        Call<List<SpaceRideServiceModel>> call = SERVICE.updateFaq_respuesta(SpaceRideServiceProperties.UPDATE_FAQ_ANSWER_CASE, faq.getFaq_id(), faq.getFaq_respuesta(), faq.getFaq_a_id());
        try {
            Response<List<SpaceRideServiceModel>> response = call.execute();
            List<SpaceRideServiceModel> spaceRideServiceModels = response.body();
            SpaceRideServiceModel spaceRideServiceModel = spaceRideServiceModels.get(0);
            updated = (Integer.parseInt(spaceRideServiceModel.getVariable1()) != 0);if (Integer.parseInt(spaceRideServiceModel.getVariable1()) == 1) {
                updated = true;
            } else {
                updated = false;
            }
            System.out.println(updated);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error at updateFaq_respuesta: " + e.getMessage());
        }
        return updated;
    }

    @Override
    public boolean updateFaq_approval(int faq_id, boolean approval) {
        boolean updated = false;
        Call<List<SpaceRideServiceModel>> call = SERVICE.updateFaq_approval(SpaceRideServiceProperties.UPDATE_FAQ_APPROVAL_CASE, faq_id, (approval ? 1 : 0));
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
    public boolean deleteFaq(int faq_id) {
        boolean deleted = false;
        Call<List<SpaceRideServiceModel>> call = SERVICE.deleteFaq(SpaceRideServiceProperties.DELETE_FAQ_CASE, faq_id);
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
