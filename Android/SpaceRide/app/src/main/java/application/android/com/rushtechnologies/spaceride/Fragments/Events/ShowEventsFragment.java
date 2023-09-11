package application.android.com.rushtechnologies.spaceride.Fragments.Events;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import application.android.com.rushtechnologies.spaceride.App.Adapters.RecyclerView.ShowReportRecyclerViewAdapter;
import application.android.com.rushtechnologies.spaceride.DAO.AccessEventReport;
import application.android.com.rushtechnologies.spaceride.ImpDAO.ImpEventReport;
import application.android.com.rushtechnologies.spaceride.Model.EventReport;
import application.android.com.rushtechnologies.spaceride.Model.User;
import application.android.com.rushtechnologies.spaceride.R;

public class ShowEventsFragment extends Fragment {
    private List<EventReport> eventReports;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private OnShowEventsFragemntListener callback;
    private User user;

    public ShowEventsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_events, container, false);
        setUser(callback.getUser());
        bindElements(view);
        setView();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            callback = (OnShowEventsFragemntListener) context;
        } catch (Exception ex) {
            System.out.println("Error at ShowEventsFragment: " + ex.getMessage());
            throw new ClassCastException(context.toString() + "should implement OnFragmentInteractionListener");
        }
    }

    private void bindElements(View view) {
        getEventReports(user.getU_type());
        recyclerView = view.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this.getContext());
        adapter = new ShowReportRecyclerViewAdapter(R.layout.recycler_view_item_report_grid, false, eventReports, null, new ShowReportRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int id, int i) {

            }
        });
    }

    private void setView() {
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void getEventReports(int type) {
        if (type == 2) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        eventReports = new ArrayList<>();
                        AccessEventReport accessEventReport = new ImpEventReport();
                        eventReports = accessEventReport.readERSupport(user.getU_id());
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Error at readAllFaq: " + e.getMessage());
                    }
                }
            });
        }
        if (type == 4 || type == 5) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        eventReports = new ArrayList<>();
                        AccessEventReport accessEventReport = new ImpEventReport();
                        eventReports = accessEventReport.readAllER();
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Error at readAllFaq: " + e.getMessage());
                    }
                }
            });
        }
    }

    public interface OnShowEventsFragemntListener {
        User getUser();

        void toast(String message);
    }

    private void setUser(User user) {
        this.user = user;
    }

}
