package application.android.com.rushtechnologies.spaceride.Fragments.Events;


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

import application.android.com.rushtechnologies.spaceride.App.Adapters.RecyclerView.DeleteRecyclerViewAdapter;
import application.android.com.rushtechnologies.spaceride.DAO.AccessEventReport;
import application.android.com.rushtechnologies.spaceride.ImpDAO.ImpEventReport;
import application.android.com.rushtechnologies.spaceride.Model.EventReport;
import application.android.com.rushtechnologies.spaceride.Model.User;
import application.android.com.rushtechnologies.spaceride.R;

public class DeleteEventFragment extends Fragment {
    private List<EventReport> eventReports;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private boolean deleted;
    private User user;
    private OnDeleteEventFragmentInteractionListener callback;

    public DeleteEventFragment() {
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
            callback = (OnDeleteEventFragmentInteractionListener) context;
        } catch (Exception ex) {
            System.out.println("Error at DeleteEventFragment: " + ex.getMessage());
            throw new ClassCastException(context.toString() + "should implement OnFragmentInteractionListener");
        }
    }

    private void setView() {
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void bindElements(View view) {
        getEventReports(user.getU_type());
        recyclerView = view.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this.getContext());
        adapter = new DeleteRecyclerViewAdapter(R.layout.recycler_view_item_delete, 1, null, eventReports, null, new DeleteRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int id, int i) {
                deleteEventReport(id);
                if(deleted) {
                    callback.toast("Reporte de evento borrado");
                } else {
                    callback.toast("Error al borrar reporte de evento");
                }
            }
        });
    }

    private void getEventReports(int type) {
        if (type == 5) {
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

    private void deleteEventReport(final int id) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    final AccessEventReport ACCESS_EVENT = new ImpEventReport();
                    deleted = ACCESS_EVENT.deleteEventReport(id);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Error at deleteEventReport: " + e.getMessage());
                }
            }
        });
    }

    public void setUser(User user) {
        this.user = user;
    }

    public interface OnDeleteEventFragmentInteractionListener {
        User getUser();

        void toast(String message);
    }

}
