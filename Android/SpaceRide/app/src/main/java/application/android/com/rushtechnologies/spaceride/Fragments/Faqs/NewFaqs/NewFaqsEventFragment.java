package application.android.com.rushtechnologies.spaceride.Fragments.Faqs.NewFaqs;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import application.android.com.rushtechnologies.spaceride.App.Adapters.RecyclerView.NewFaqRecyclerViewAdapter;
import application.android.com.rushtechnologies.spaceride.App.Validations.Validations;
import application.android.com.rushtechnologies.spaceride.DAO.AccessEventReport;
import application.android.com.rushtechnologies.spaceride.DAO.AccessFaq;
import application.android.com.rushtechnologies.spaceride.Fragments.User.MyAccount.MyAccountFragment;
import application.android.com.rushtechnologies.spaceride.ImpDAO.ImpEventReport;
import application.android.com.rushtechnologies.spaceride.ImpDAO.ImpFaq;
import application.android.com.rushtechnologies.spaceride.Model.EventReport;
import application.android.com.rushtechnologies.spaceride.Model.Faq;
import application.android.com.rushtechnologies.spaceride.Model.User;
import application.android.com.rushtechnologies.spaceride.R;

public class NewFaqsEventFragment extends Fragment {
    private List<EventReport> eventReports;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private final Validations VALIDATIONS = new Validations();
    private User user;
    private boolean created;
    private OnNewFaqsEventFragmentInteractionListener callback;

    public NewFaqsEventFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_delete_event, container, false);
        setUser(callback.getUser());
        bindElements(view);
        setView();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            callback = (OnNewFaqsEventFragmentInteractionListener) context;
        } catch (Exception ex) {
            System.out.println("Error at NewFaqsEventFragment: " + ex.getMessage());
            throw new ClassCastException(context.toString() + "should implement OnFragmentInteractionListener");
        }
    }

    private void createFaq(final Faq faq) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    final AccessFaq ACCESS_FAQ = new ImpFaq();
                    created = ACCESS_FAQ.createFaq(faq);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Error at createEventReport: " + e.getMessage());
                }
            }
        });
    }

    private void setView() {
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void bindElements(View view) {
        getEventReports();
        recyclerView = view.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this.getContext());
        adapter = new NewFaqRecyclerViewAdapter(R.layout.recycler_view_item_new_faq, eventReports, new NewFaqRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int id, String question, int i) {
                if (VALIDATIONS.isValidEntry(question)) {
                    Faq faq = new Faq(0, question, eventReports.get(i).getER_Solution(), "Soporte", 1, false, eventReports.get(i).getER_U_Id(), user.getU_id());
                    createFaq(faq);
                    if (created) {
                        callback.toast("Pregunta frecuente registrada");
                        getFragmentManager().beginTransaction().detach(NewFaqsEventFragment.this).attach(NewFaqsEventFragment.this).commit();
                    } else {
                        callback.toast("Error al registar pregunta frecuente");
                    }
                } else {
                    if (!VALIDATIONS.isValidEntry(question)) {
                        callback.toast("Ingrese la pregunta correctamente");
                    }
                }
            }
        });
    }

    private void getEventReports() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    eventReports = new ArrayList<>();
                    final AccessEventReport ACCESS_EVENT = new ImpEventReport();
                    List<EventReport> reports = ACCESS_EVENT.readAllER();
                    for (EventReport eventReport: reports) {
                        if(eventReport.getER_Status().equals("Validado")) {
                            final AccessFaq ACCESS_FAQ = new ImpFaq();
                            List<Faq> faqs = ACCESS_FAQ.readAllFaq();
                            for (Faq faq: faqs) {
                                if(!faq.getFaq_respuesta().equals(eventReport.getER_Solution())) {
                                    if(!eventReports.contains(eventReport)) {
                                        eventReports.add(eventReport);
                                    }
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Error at readAllER and readAllFaq: " + e.getMessage());
                }
            }
        });
    }

    private void setUser(User user) {
        this.user = user;
    }

    public interface OnNewFaqsEventFragmentInteractionListener {
        User getUser();

        void toast(String message);
    }

}
