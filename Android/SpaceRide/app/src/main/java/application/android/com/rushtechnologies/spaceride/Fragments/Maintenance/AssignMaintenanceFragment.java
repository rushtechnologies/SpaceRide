package application.android.com.rushtechnologies.spaceride.Fragments.Maintenance;

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

import application.android.com.rushtechnologies.spaceride.App.Adapters.RecyclerView.AssignRecyclerViewAdapter;
import application.android.com.rushtechnologies.spaceride.DAO.AccessMaintenanceReport;
import application.android.com.rushtechnologies.spaceride.ImpDAO.ImpMaintenanceReport;
import application.android.com.rushtechnologies.spaceride.Model.MaintenanceReport;
import application.android.com.rushtechnologies.spaceride.Model.User;
import application.android.com.rushtechnologies.spaceride.R;

public class AssignMaintenanceFragment extends Fragment {
    private List<MaintenanceReport> maintenanceReports;
    private List<String> reports = new ArrayList<>();
    private List<Integer> ids = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private User user;
    private boolean updated;
    private OnAssignMaintenanceFragmentInteractionListener callback;

    public AssignMaintenanceFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_assign_maintenance, container, false);
        setUser(callback.getUser());
        bindElements(view);
        initView();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            callback = (OnAssignMaintenanceFragmentInteractionListener) context;
        } catch (Exception ex) {
            System.out.println("Error at AssignMaintenanceFragment: " + ex.getMessage());
            throw new ClassCastException(context.toString() + "should implement OnFragmentInteractionListener");
        }
    }

    private void bindElements(View view) {
        getReports();
        recyclerView = view.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this.getContext());
        adapter = new AssignRecyclerViewAdapter(true, reports, ids, R.layout.recycler_view_item_assign, this.getContext(), new AssignRecyclerViewAdapter.OnAssignFragmentItemClickListener() {
            @Override
            public void onItemClick(String report, int id, String name, boolean maintenance, int i) {
                int s_id = 5;
                updateMRChief(id, s_id);
                if (updated) {
                    callback.toast("Reporte de evento asignado a: " + name);
                    getFragmentManager().beginTransaction().detach(AssignMaintenanceFragment.this).attach(AssignMaintenanceFragment.this).commit();
                } else {
                    callback.toast("Error al asignar reporte de evento");
                }
            }
        });
    }

    private void initView() {
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void getReports() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    maintenanceReports = new ArrayList<>();
                    final AccessMaintenanceReport ACCESS_MAINTENANCE = new ImpMaintenanceReport();
                    maintenanceReports = ACCESS_MAINTENANCE.readDeveloperMR(user.getU_id());
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Error at readAllFaq: " + e.getMessage());
                }
            }
        });
        for (MaintenanceReport maintenanceReport : maintenanceReports) {
            reports.add(maintenanceReport.getMR_Description());
            ids.add(maintenanceReport.getMR_Id());
        }
    }

    private void updateMRChief(final int id, final int s_id) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    final AccessMaintenanceReport ACCESS_MAINTENANCE = new ImpMaintenanceReport();
                    updated = ACCESS_MAINTENANCE.updateMRChief(id, s_id);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Error at createMaintenanceReport: " + e.getMessage());
                }
            }
        });
    }

    public interface OnAssignMaintenanceFragmentInteractionListener {
        User getUser();

        void toast(String message);
    }

    private void setUser(User user) {
        this.user = user;
    }

}
