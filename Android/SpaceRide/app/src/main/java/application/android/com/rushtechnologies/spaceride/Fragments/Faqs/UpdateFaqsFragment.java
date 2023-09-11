package application.android.com.rushtechnologies.spaceride.Fragments.Faqs;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import application.android.com.rushtechnologies.spaceride.App.Adapters.RecyclerView.UpdateFaqsRecyclerViewAdapter;
import application.android.com.rushtechnologies.spaceride.App.Validations.Validations;
import application.android.com.rushtechnologies.spaceride.DAO.AccessFaq;
import application.android.com.rushtechnologies.spaceride.ImpDAO.ImpFaq;
import application.android.com.rushtechnologies.spaceride.Model.Faq;
import application.android.com.rushtechnologies.spaceride.Model.User;
import application.android.com.rushtechnologies.spaceride.R;

public class UpdateFaqsFragment extends Fragment {
    private List<Faq> faqs;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private final Validations VALIDATIONS = new Validations();
    private boolean updated;
    private User user;
    private OnUpdateFaqsFragmentInteractionListener callback;

    public UpdateFaqsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_update_faqs, container, false);
        setUser(callback.getUser());
        bindElements(view);
        setView();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            callback = (OnUpdateFaqsFragmentInteractionListener) context;
        } catch (Exception ex) {
            System.out.println("Error at UpdateFaqsFragment: " + ex.getMessage());
            throw new ClassCastException(context.toString() + "should implement OnFragmentInteractionListener");
        }
    }

    private void bindElements(View view) {
        getFaqs();
        recyclerView = view.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this.getContext());
        adapter = new UpdateFaqsRecyclerViewAdapter(faqs, R.layout.recycler_view_item_faqs_update, new UpdateFaqsRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(Faq faq, String answer, int position) {
                if (VALIDATIONS.isValidEntry(answer)) {
                    updateFaq_respuesta(faq, answer, user.getU_id());
                    if (updated) {
                        callback.toast("Pregunta frecuente actualizada");
                        getFragmentManager().beginTransaction().detach(UpdateFaqsFragment.this).attach(UpdateFaqsFragment.this).commit();
                    } else {
                        callback.toast("Error al actualizar pregunta frecuente");
                    }
                } else {
                    if (!VALIDATIONS.isValidEntry(answer)) {
                        callback.toast("Ingrese la respuesta correctamente");
                    }
                }
            }
        });
    }

    private void setView() {
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void getFaqs() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    faqs = new ArrayList<>();
                    final AccessFaq ACCESS_FAQ = new ImpFaq();
                    List<Faq> AllFaqs = ACCESS_FAQ.readAllFaq();
                    for (Faq faq : AllFaqs) {
                        if (!faq.isFaq_aprobacion()) {
                            faqs.add(faq);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Error at readAllFaq: " + e.getMessage());
                }
            }
        });
    }

    private void updateFaq_respuesta(final Faq faq, final String solution, final int s_id) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    final AccessFaq ACCESS_FAQ = new ImpFaq();
                    updated = ACCESS_FAQ.updateFaq_respuesta(new Faq(faq.getFaq_id(), faq.getFaq_pregunta(), solution, faq.getFaq_tema(), faq.getFaq_prioridad(), faq.isFaq_aprobacion(), faq.getFaq_u_id(), s_id));
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Error at updateFaq_respuesta: " + e.getMessage());
                }
            }
        });
    }

    public interface OnUpdateFaqsFragmentInteractionListener {
        User getUser();

        void toast(String message);
    }

    private void setUser(User user) {
        this.user = user;
    }

}
