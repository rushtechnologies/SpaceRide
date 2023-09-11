package application.android.com.rushtechnologies.spaceride.Fragments.Maintenance;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import application.android.com.rushtechnologies.spaceride.App.Adapters.RecyclerView.UpdateReportRecyclerViewAdapter;
import application.android.com.rushtechnologies.spaceride.App.Validations.Validations;
import application.android.com.rushtechnologies.spaceride.DAO.AccessEventReport;
import application.android.com.rushtechnologies.spaceride.DAO.AccessMaintenanceReport;
import application.android.com.rushtechnologies.spaceride.ImpDAO.ImpEventReport;
import application.android.com.rushtechnologies.spaceride.ImpDAO.ImpMaintenanceReport;
import application.android.com.rushtechnologies.spaceride.Model.EventReport;
import application.android.com.rushtechnologies.spaceride.Model.MaintenanceReport;
import application.android.com.rushtechnologies.spaceride.Model.User;
import application.android.com.rushtechnologies.spaceride.R;

public class UpdateMaintenanceFragment extends Fragment {
    private List<MaintenanceReport> maintenanceReports;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private final Validations VALIDATIONS = new Validations();
    private boolean updated;
    private User user;
    private OnUpdateMaintenanceFragmentInteractionListener callback;

    public UpdateMaintenanceFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_update_maintenance, container, false);
        setUser(callback.getUser());
        bindElements(view);
        setView();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            callback = (OnUpdateMaintenanceFragmentInteractionListener) context;
        } catch (Exception ex) {
            System.out.println("Error at UpdateMaintenanceFragment: " + ex.getMessage());
            throw new ClassCastException(context.toString() + "should implement OnFragmentInteractionListener");
        }
    }

    private void bindElements(View view) {
        getMaintenanceReports();
        recyclerView = view.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this.getContext());
        adapter = new UpdateReportRecyclerViewAdapter(R.layout.recycler_view_item_report_update, true, null, maintenanceReports, new UpdateReportRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int id, String solution, int i) {
                if (VALIDATIONS.isValidEntry(solution)) {
                    updateMRDeveloper(id, solution);
                    if (updated) {
                        callback.toast("Reporte de mantenimiento actualizado");
                        getFragmentManager().beginTransaction().detach(UpdateMaintenanceFragment.this).attach(UpdateMaintenanceFragment.this).commit();
                    } else {
                        callback.toast("Error al actualizar reporte de mantenimiento");
                    }
                } else {
                    if (!VALIDATIONS.isValidEntry(solution)) {
                        callback.toast("Ingrese la solución correctamente");
                    }
                }
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
                    maintenanceReports = ACCESS_MAINTENANCE.readDeveloperMR(user.getU_id());
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Error at readAllFaq: " + e.getMessage());
                }
            }
        });
    }

    private void updateMRDeveloper(final int id, final String solution) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    final AccessMaintenanceReport ACCESS_MAINTENANCE = new ImpMaintenanceReport();
                    updated = ACCESS_MAINTENANCE.updateMRDeveloper(id, solution);
                    MaintenanceReport maintenanceReport = ACCESS_MAINTENANCE.readMaintenanceReport(id);
                    final AccessEventReport ACCESS_EVENT = new ImpEventReport();
                    List<EventReport> eventReports = ACCESS_EVENT.readAllER();
                    for (EventReport eventReport : eventReports) {
                        if(eventReport.getER_Description().equals(maintenanceReport.getMR_Description())) {
                            updated = ACCESS_EVENT.updateERUser(eventReport.getER_Id(), "Desarrollado");
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Error at updateMRDeveloper: " + e.getMessage());
                }
            }
        });
    }

    public interface OnUpdateMaintenanceFragmentInteractionListener {
        User getUser();

        void toast(String message);
    }

    private void setUser(User user) {
        this.user = user;
    }

}
