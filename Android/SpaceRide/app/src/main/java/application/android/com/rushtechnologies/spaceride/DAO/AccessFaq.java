package application.android.com.rushtechnologies.spaceride.DAO;

import java.util.List;

import application.android.com.rushtechnologies.spaceride.Model.Faq;

public interface AccessFaq {

    boolean createFaq(Faq faq);

    Faq readFaq(int faq_id);

    List<Faq> readAllFaq();

    boolean updateFaq_respuesta(Faq faq);

    boolean updateFaq_approval(int faq_id, boolean approval);

    boolean deleteFaq(int faq_id);

}
