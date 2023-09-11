package application.android.com.rushtechnologies.spaceride.Fragments.Faqs.NewFaqs;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import application.android.com.rushtechnologies.spaceride.App.Validations.Validations;
import application.android.com.rushtechnologies.spaceride.DAO.AccessFaq;
import application.android.com.rushtechnologies.spaceride.ImpDAO.ImpFaq;
import application.android.com.rushtechnologies.spaceride.Model.Faq;
import application.android.com.rushtechnologies.spaceride.Model.User;
import application.android.com.rushtechnologies.spaceride.R;

public class NewFaqsFragment extends Fragment {
    private EditText question;
    private Spinner topic;
    private Button ask;
    private final Validations VALIDATIONS = new Validations();
    private User user;
    private boolean created;
    private OnNewFaqsFragmentInteractionListener callback;

    public NewFaqsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_faqs, container, false);
        setUser(callback.getUser());
        bindElements(view);
        listeners();
        initView();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            callback = (OnNewFaqsFragmentInteractionListener) context;
        } catch (Exception ex) {
            System.out.println("Error at NewFaqsFragment: " + ex.getMessage());
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

    private void bindElements(View view) {
        question = view.findViewById(R.id.question);
        topic = view.findViewById(R.id.topic);
        ask = view.findViewById(R.id.ask);
    }

    private void listeners() {
        ask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (VALIDATIONS.isValidEntry(question.getText().toString()) && VALIDATIONS.isValidEntry(topic.getSelectedItem().toString())) {
                    Faq faq = new Faq(0, question.getText().toString(), "", topic.getSelectedItem().toString(), 1, false, user.getU_id(), user.getU_id());
                    createFaq(faq);
                    if (created) {
                        callback.toast("Pregunta frecuente registrada");
                        restoreView();
                    } else {
                        callback.toast("Error al registar pregunta frecuente");
                    }
                } else {
                    if (!VALIDATIONS.isValidEntry(question.getText().toString())) {
                        callback.toast("Ingrese la pregunta correctamente");
                        focus(question);
                    } else {
                        if (!VALIDATIONS.isValidEntry(topic.getSelectedItem().toString())) {
                            callback.toast("Seleccione el tema correctamente");
                            focus(topic);
                        }
                    }
                }
            }
        });
    }

    private void initView() {
        topic.setSelection(0, true);
    }

    private void focus(EditText editText) {
        if (editText != null) {
            editText.requestFocus();
        }
    }

    private void focus(Spinner spinner) {
        if (spinner != null) {
            spinner.requestFocus();
        }
    }

    private void restoreView() {
        question.setText("");
        initView();
    }

    private void setUser(User user) {
        this.user = user;
    }

    public interface OnNewFaqsFragmentInteractionListener {
        User getUser();

        void toast(String message);
    }

}
