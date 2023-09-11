package com.rushtechnologies.spaceride.DAO;

import com.rushtechnologies.spaceride.Models.Faq;
import java.util.List;

/**
 *
 * @author CARLOSHG
 */
public interface iFaq {

    public boolean createFaq(Faq faq);

    public Faq readFaq(int faq_id);

    public List<Faq> readAllFaq(String faq_tema);
    
    public List<Faq> readAllFaq();

    public List<String> readAllFaq_tema();

    public List<Faq> readAllFaqAdmin();

    public boolean updateFaq_respuesta(int faq_id, String faq_respuesta, int faq_a_id);

    public boolean deleteFaq(int faq_id);
}
