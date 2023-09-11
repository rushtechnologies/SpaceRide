package application.android.com.rushtechnologies.spaceride.Fragments.Maintenance;


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
import application.android.com.rushtechnologies.spaceride.DAO.AccessMaintenanceReport;
import application.android.com.rushtechnologies.spaceride.ImpDAO.ImpMaintenanceReport;
import application.android.com.rushtechnologies.spaceride.Model.MaintenanceReport;
import application.android.com.rushtechnologies.spaceride.Model.User;
import application.android.com.rushtechnologies.spaceride.R;

public class DeleteMaintenanceFragment extends Fragment {
    private List<MaintenanceReport> maintenanceReports;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private boolean deleted;
    private User user;
    private OnDeleteMaintenanceFragmentInteractionListener callback;

    public DeleteMaintenanceFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_delete_maintenance, container, false);
        setUser(callback.getUser());
        bindElements(view);
        setView();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            callback = (OnDeleteMaintenanceFragmentInteractionListener) context;
        } catch (Exception ex) {
            System.out.println("Error at DeleteMaintenanceFragment: " + ex.getMessage());
            throw new ClassCastException(context.toString() + "should implement OnFragmentInteractionListener");
        }
    }

    private void bindElements(View view) {
        getMaintenanceReports(user.getU_type());
        recyclerView = view.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this.getContext());
        adapter = new DeleteRecyclerViewAdapter(R.layout.recycler_view_item_delete, 2, null, null, maintenanceReports, new DeleteRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int id, int i) {
                deleteMaintenanceReport(id);
                if(deleted) {
                    callback.toast("Reporte de mantenimiento borrado");
                } else {
                    callback.toast("Error al borrar reporte de mantenimiento");
                }
            }
        });
    }

    private void setView() {
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void getMaintenanceReports(int type) {
        if (type == 3) {
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
        if (type == 4) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        maintenanceReports = new ArrayList<>();
                        final AccessMaintenanceReport ACCESS_MAINTENANCE = new ImpMaintenanceReport();
                        maintenanceReports = ACCESS_MAINTENANCE.readAllMR();
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Error at readAllFaq: " + e.getMessage());
                    }
                }
            });
        }
    }

    private void deleteMaintenanceReport(final int id) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    final AccessMaintenanceReport ACCESS_MAINTENANCE = new ImpMaintenanceReport();
                    deleted = ACCESS_MAINTENANCE.deleteMaintenanceReport(id);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Error at createMaintenanceReport: " + e.getMessage());
                }
            }
        });
    }

    public void setUser(User user) {
        this.user = user;
    }

    public interface OnDeleteMaintenanceFragmentInteractionListener {
        User getUser();

        void toast(String message);
    }

}
