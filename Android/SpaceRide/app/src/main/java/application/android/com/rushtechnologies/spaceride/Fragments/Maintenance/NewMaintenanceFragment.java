package application.android.com.rushtechnologies.spaceride.Fragments.Maintenance;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.sql.Timestamp;
import java.util.Date;

import application.android.com.rushtechnologies.spaceride.App.Validations.Validations;
import application.android.com.rushtechnologies.spaceride.DAO.AccessMaintenanceReport;
import application.android.com.rushtechnologies.spaceride.ImpDAO.ImpMaintenanceReport;
import application.android.com.rushtechnologies.spaceride.Model.MaintenanceReport;
import application.android.com.rushtechnologies.spaceride.Model.User;
import application.android.com.rushtechnologies.spaceride.R;

public class NewMaintenanceFragment extends Fragment {
    private EditText description;
    private Button report;
    private final Validations VALIDATIONS = new Validations();
    private User user;
    private boolean created;
    private OnNewMaintenanceFragmentInteractionListener callback;

    public NewMaintenanceFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_maintenance, container, false);
        setUser(callback.getUser());
        bindElements(view);
        listeners();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            callback = (OnNewMaintenanceFragmentInteractionListener) context;
        } catch (Exception ex) {
            System.out.println("Error at NewMaintenanceFragment: " + ex.getMessage());
            throw new ClassCastException(context.toString() + "should implement OnFragmentInteractionListener");
        }
    }

    private void bindElements(View view) {
        description = view.findViewById(R.id.description);
        report = view.findViewById(R.id.report);
    }

    private void listeners() {
        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (VALIDATIONS.isValidEntry(description.getText().toString())) {
                    MaintenanceReport maintenanceReport = new MaintenanceReport(0, "Mantenimiento", description.getText().toString(), "Pendiente", "", user.getU_id(), new Timestamp(new Date().getTime()));
                    createMaintenanceReport(maintenanceReport);
                    if (created) {
                        callback.toast("Reporte de mantenimiento registrado");
                        restoreView();
                    } else {
                        callback.toast("Error al registar reporte de mantenimiento");
                    }
                } else {
                    if (!VALIDATIONS.isValidEntry(description.getText().toString())) {
                        callback.toast("Ingrese la descripción correctamente");
                        focus(description);
                    }
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

    private void setUser(User user) {
        this.user = user;
    }

    public interface OnNewMaintenanceFragmentInteractionListener {
        User getUser();

        void toast(String message);
    }

    private void focus(EditText editText) {
        if (editText != null) {
            editText.requestFocus();
        }
    }

    private void restoreView() {
        description.setText("");
    }

}
