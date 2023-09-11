package application.android.com.rushtechnologies.spaceride.Fragments.Events;


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

public class UpdateEventFragment extends Fragment {
    private List<EventReport> eventReports;
    private EventReport eventReport;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private final Validations VALIDATIONS = new Validations();
    private User user;
    private boolean updated, created;
    private OnUpdateEventFragmentInteractionListener callback;

    public UpdateEventFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_update_event, container, false);
        setUser(callback.getUser());
        bindElements(view);
        setView();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            callback = (OnUpdateEventFragmentInteractionListener) context;
        } catch (Exception ex) {
            System.out.println("Error at UpdateFaqsFragment: " + ex.getMessage());
            throw new ClassCastException(context.toString() + "should implement OnFragmentInteractionListener");
        }
    }

    private void bindElements(View view) {
        getEventReports();
        recyclerView = view.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this.getContext());
        adapter = new UpdateReportRecyclerViewAdapter(R.layout.recycler_view_item_report_update, false, eventReports, null, new UpdateReportRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int id, String solution, int i) {
                if(solution.equalsIgnoreCase("Mantenimiento")){
                    updateERMaintenance(id);
                    readEventReport(id);
                    createMaintenanceReport(new MaintenanceReport(0, "Soporte", eventReport.getER_Description(), "Pendiente", "", 1, new Timestamp(new Date().getTime())));
                    if (updated && created) {
                        callback.toast("Reporte de evento registrado en mantenimiento");
                        getFragmentManager().beginTransaction().detach(UpdateEventFragment.this).attach(UpdateEventFragment.this).commit();
                    } else {
                        callback.toast("Error al actualizar reporte de evento y regsitrar reporte de mantenimiento");
                    }
                } else {
                    if (VALIDATIONS.isValidEntry(solution)) {
                        updateER(id, solution);
                        if (updated) {
                            callback.toast("Reporte de evento actualizado");
                            getFragmentManager().beginTransaction().detach(UpdateEventFragment.this).attach(UpdateEventFragment.this).commit();
                        } else {
                            callback.toast("Error al actualizar reporte de evento");
                        }
                    } else {
                        if (!VALIDATIONS.isValidEntry(solution)) {
                            callback.toast("Ingrese la solución correctamente");
                        }
                    }
                }
            }
        });
    }

    private void setView() {
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void getEventReports() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    eventReports = new ArrayList<>();
                    AccessEventReport accessEventReport = new ImpEventReport();
                    eventReports = accessEventReport.readERSupport(user.getU_id());
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Error at readERSupport: " + e.getMessage());
                }
            }
        });
    }

    private void readEventReport(final int id) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    final AccessEventReport ACCESS_EVENT = new ImpEventReport();
                    eventReport = ACCESS_EVENT.readEventReport(id);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Error at readEventReport: " + e.getMessage());
                }
            }
        });
    }

    private void updateER(final int id, final String solution) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    final AccessEventReport ACCESS_EVENT = new ImpEventReport();
                    EventReport eventReport = ACCESS_EVENT.readEventReport(id);
                    final AccessMaintenanceReport ACCESS_MAINTENANCE = new ImpMaintenanceReport();
                    List<MaintenanceReport> maintenanceReports = ACCESS_MAINTENANCE.readAllMR();
                    for (MaintenanceReport maintenanceReport : maintenanceReports) {
                        if(eventReport.getER_Description().equals(maintenanceReport.getMR_Description())) {
                            updated = ACCESS_MAINTENANCE.updateMREvent(maintenanceReport.getMR_Id(), "Validado");
                        }
                    }
                    updated = ACCESS_EVENT.updateERSupport(id, solution);
                    updated = ACCESS_EVENT.updateERUser(id, "Validado");
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Error at updateERSupport: " + e.getMessage());
                }
            }
        });
    }

    private void createMaintenanceReport(final MaintenanceReport maintenanceReport) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    final AccessMaintenanceReport ACCESS_MAINTENANCE = new ImpMaintenanceReport();
                    created = ACCESS_MAINTENANCE.createMaintenanceReport(maintenanceReport);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Error at createMaintenanceReport: " + e.getMessage());
                }
            }
        });
    }

    private void updateERMaintenance(final int id) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    final AccessEventReport ACCESS_EVENT = new ImpEventReport();
                    updated = ACCESS_EVENT.updateERMaintenance(id);

                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Error at updateERMaintenance: " + e.getMessage());
                }
            }
        });
    }

    public interface OnUpdateEventFragmentInteractionListener {
        User getUser();

        void toast(String message);
    }

    private void setUser(User user) {
        this.user = user;
    }

}
