package application.android.com.rushtechnologies.spaceride.Fragments.Events;


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
import application.android.com.rushtechnologies.spaceride.DAO.AccessEventReport;
import application.android.com.rushtechnologies.spaceride.ImpDAO.ImpEventReport;
import application.android.com.rushtechnologies.spaceride.Model.EventReport;
import application.android.com.rushtechnologies.spaceride.Model.User;
import application.android.com.rushtechnologies.spaceride.R;

public class NewEventFragment extends Fragment {
    private EditText transcript, description, userId;
    private Button report;
    private final Validations VALIDATIONS = new Validations();
    private User user;
    private boolean created;
    private OnNewEventFragmentInteractionListener callback;

    public NewEventFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_event, container, false);
        setUser(callback.getUser());
        bindElements(view);
        listeners();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            callback = (OnNewEventFragmentInteractionListener) context;
        } catch (Exception ex) {
            System.out.println("Error at NewEventFragment: " + ex.getMessage());
            throw new ClassCastException(context.toString() + "should implement OnFragmentInteractionListener");
        }
    }

    private void bindElements(View view) {
        transcript = view.findViewById(R.id.transcript);
        description = view.findViewById(R.id.description);
        userId = view.findViewById(R.id.userID);
        report = view.findViewById(R.id.report);
    }

    private void listeners() {
        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (VALIDATIONS.isValidEntry(transcript.getText().toString()) && VALIDATIONS.isValidEntry(description.getText().toString()) && VALIDATIONS.isValidEntry(userId.getText().toString())) {
                    int u_id = 0;
                    try {
                        u_id = Integer.parseInt(userId.getText().toString());
                    } catch(NumberFormatException e) {
                        e.printStackTrace();
                        System.out.println("Error at createFaq: " + e.getMessage());
                        callback.toast("Ingrese el Id del usuario correctamente");
                        focus(userId);
                        return;
                    }
                    EventReport eventReport = new EventReport();
                    if(user.getU_type() == 5) {
                        eventReport = new EventReport(0, u_id, transcript.getText().toString(), description.getText().toString(), "Soporte", "Pendiente", "", 1, new Timestamp(new Date().getTime()));
                    } else {
                        eventReport = new EventReport(0, u_id, transcript.getText().toString(), description.getText().toString(), "Soporte", "Pendiente", "", user.getU_id(), new Timestamp(new Date().getTime()));
                    }
                    createEventReport(eventReport);
                    if (created) {
                        callback.toast("Reporte de evento registrado");
                        restoreView();
                    } else {
                        callback.toast("Error al registar reporte de evento");
                    }
                } else {
                    if (!VALIDATIONS.isValidEntry(transcript.getText().toString())) {
                        callback.toast("Ingrese la transcripción correctamente");
                        focus(transcript);
                    } else {
                        if (!VALIDATIONS.isValidEntry(description.getText().toString())) {
                            callback.toast("Ingrese la descripción correctamente");
                            focus(description);
                        } else {
                            if (!VALIDATIONS.isValidEntry(userId.getText().toString())) {
                                callback.toast("Ingrese el Id del usuario correctamente");
                                focus(userId);
                            }
                        }
                    }
                }
            }
        });
    }

    private void createEventReport(final EventReport eventReport) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    final AccessEventReport ACCESS_EVENT = new ImpEventReport();
                    created = ACCESS_EVENT.createEventReport(eventReport);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Error at createEventReport: " + e.getMessage());
                }
            }
        });
    }

    private void setUser(User user) {
        this.user = user;
    }

    public interface OnNewEventFragmentInteractionListener {
        User getUser();

        void toast(String message);
    }

    private void focus(EditText editText) {
        if (editText != null) {
            editText.requestFocus();
        }
    }

    private void restoreView() {
        transcript.setText("");
        description.setText("");
        userId.setText("");
    }

}
