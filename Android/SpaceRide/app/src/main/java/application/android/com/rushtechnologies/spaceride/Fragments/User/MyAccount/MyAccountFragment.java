package application.android.com.rushtechnologies.spaceride.Fragments.User.MyAccount;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import application.android.com.rushtechnologies.spaceride.Activities.ForgottenPasswdActivity;
import application.android.com.rushtechnologies.spaceride.App.Validations.Validations;
import application.android.com.rushtechnologies.spaceride.DAO.AccessSecurity;
import application.android.com.rushtechnologies.spaceride.DAO.AccessUser;
import application.android.com.rushtechnologies.spaceride.ImpDAO.ImpSecurity;
import application.android.com.rushtechnologies.spaceride.ImpDAO.ImpUser;
import application.android.com.rushtechnologies.spaceride.Model.User;
import application.android.com.rushtechnologies.spaceride.R;

public class MyAccountFragment extends Fragment {
    private EditText name, email, passwd;
    private Button  forgottenPasswd, update;
    private final Validations validations = new Validations();
    private User user;
    private boolean updated;
    private String encryptedNewPasswd = "";
    private OnMyAccountFragmentInteractionListener callback;

    public MyAccountFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_account, container, false);
        getUser(callback.getUser());
        bindElements(view);
        initView();
        listeners();
        return view;
    }

    private void getUser (User user) {
        this.user = user;
    }

    private void bindElements(View view) {
        name = view.findViewById(R.id.name);
        email = view.findViewById(R.id.email);
        passwd = view.findViewById(R.id.passwd);
        forgottenPasswd = view.findViewById(R.id.forgottenPasswd);
        update = view.findViewById(R.id.update);
    }

    private void initView() {
        name.setText(user.getU_name());
        email.setText(user.getU_email());
    }

    private void listeners() {
        forgottenPasswd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ForgottenPasswdActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("user", user.getU_id());
                startActivity(intent);
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validations.isValidEntry(name.getText().toString()) && validations.isValidEmail(email.getText().toString()) && validations.isValidPasswd(passwd.getText().toString())) {
                    encryptSR(passwd.getText().toString());
                    if (validations.entriesMatch(user.getU_passwd(), encryptedNewPasswd)){
                        User updatedUser = new User(user.getU_id(), name.getText().toString(), user.getU_passwd(), email.getText().toString(), user.getU_type(), user.getU_wins(), user.getU_loses());
                        updateUserData(updatedUser);
                        if (updated) {
                            callback.toast("Información actualizada");
                            getFragmentManager().beginTransaction().detach(MyAccountFragment.this).attach(MyAccountFragment.this).commit();
                            saveSharedPreferences(updatedUser);
                        } else {
                            callback.toast("Error al actualizar la información");
                        }
                    } else {
                        callback.toast("Contraseña incorrecta");
                        focus(passwd);
                    }
                } else {
                    if (!validations.isValidEntry(name.getText().toString())) {
                        callback.toast("Ingrese su nombre correctamente");
                        focus(name);
                    } else {
                        if (!validations.isValidEmail(email.getText().toString())) {
                            callback.toast("Ingrese su correo correctamente");
                            focus(email);
                        } else {
                            if (!validations.isValidPasswd(passwd.getText().toString())) {
                                callback.toast("Ingrese su contraseña correctamente");
                                focus(passwd);
                            }
                        }
                    }
                }
            }
        });
    }

    private void updateUserData(final User updatedUser) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    final AccessUser ACCESS_USER = new ImpUser();
                    updated = ACCESS_USER.updateUserData(updatedUser);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Error at createUser: " + e.getMessage());
                }
            }
        });
    }

    private void encryptSR(final String toEncrypt) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    final AccessSecurity ACCESS_SECURITY = new ImpSecurity();
                    encryptedNewPasswd = ACCESS_SECURITY.encrypt(toEncrypt);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Error at createUser: " + e.getMessage());
                }
            }
        });
    }

    private void saveSharedPreferences(User updatedUser) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE);
        int id = sharedPreferences.getInt("Id", 0);
        if (id != 0) {
            SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
            sharedPreferencesEditor.putString("Name", updatedUser.getU_name());
            if (sharedPreferencesEditor.commit()) {
                sharedPreferencesEditor.apply();
            } else {
                callback.toast("Error al actualizar las preferencias");
            }
        }
    }

    public interface OnMyAccountFragmentInteractionListener {
        User getUser();
        void toast(String message);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            callback = (OnMyAccountFragmentInteractionListener) context;
        } catch(Exception ex) {
            System.out.println("Error at MyAccountFragment: "+ex.getMessage());
            throw new ClassCastException(context.toString() + "should implement OnFragmentInteractionListener");
        }
    }

    private void focus(EditText editText) {
        if (editText != null) {
            editText.requestFocus();
        }
    }

}
