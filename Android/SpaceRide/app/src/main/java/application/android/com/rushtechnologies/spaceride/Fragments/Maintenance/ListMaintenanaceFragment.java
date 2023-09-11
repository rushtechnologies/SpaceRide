package application.android.com.rushtechnologies.spaceride.Fragments.Maintenance;


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

import application.android.com.rushtechnologies.spaceride.App.Adapters.RecyclerView.ShowReportRecyclerViewAdapter;
import application.android.com.rushtechnologies.spaceride.DAO.AccessMaintenanceReport;
import application.android.com.rushtechnologies.spaceride.ImpDAO.ImpMaintenanceReport;
import application.android.com.rushtechnologies.spaceride.Model.MaintenanceReport;
import application.android.com.rushtechnologies.spaceride.R;

public class ListMaintenanaceFragment extends Fragment {
    private List<MaintenanceReport> maintenanceReports;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    public ListMaintenanaceFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_maintenanace, container, false);
        bindElements(view);
        setView();
        return view;
    }

    private void bindElements(View view) {
        getMaintenanceReports();
        recyclerView = view.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this.getContext());
        adapter = new ShowReportRecyclerViewAdapter(R.layout.recycler_view_item_report_grid, true, null, maintenanceReports, new ShowReportRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int id, int i) {

            }
        });
    }

    private void setView() {
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void getMaintenanceReports() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    maintenanceReports = new ArrayList<>();
                    final AccessMaintenanceReport ACCESS_MAINTENANCE = new ImpMaintenanceReport();
                    maintenanceReports = ACCESS_MAINTENANCE.readAllMRList();
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Error at getMaintenanceReports: " + e.getMessage());
                }
            }
        });
    }

}
