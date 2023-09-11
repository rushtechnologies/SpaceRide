package application.android.com.rushtechnologies.spaceride.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Parcelable;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperActivityToast;
import com.github.johnpersano.supertoasts.library.utils.PaletteUtils;

import application.android.com.rushtechnologies.spaceride.Activities.Home.HomeDeveloperActivity;
import application.android.com.rushtechnologies.spaceride.Activities.Home.HomeEditorActivity;
import application.android.com.rushtechnologies.spaceride.Activities.Home.HomeEngineerActivity;
import application.android.com.rushtechnologies.spaceride.Activities.Home.HomeManagerActivity;
import application.android.com.rushtechnologies.spaceride.Activities.Home.HomeOperatorActivity;
import application.android.com.rushtechnologies.spaceride.Activities.Home.HomeUserActivity;
import application.android.com.rushtechnologies.spaceride.App.Validations.Validations;
import application.android.com.rushtechnologies.spaceride.DAO.AccessUser;
import application.android.com.rushtechnologies.spaceride.ImpDAO.ImpUser;
import application.android.com.rushtechnologies.spaceride.Model.User;
import application.android.com.rushtechnologies.spaceride.R;

public class LoginActivity extends AppCompatActivity {
    private EditText name, passwd;
    private Button loginButton, signin, forgottenPasswd;
    private Switch rememberMe;
    private User user = new User();
    private SharedPreferences sharedPreferences;
    private final Validations validations = new Validations();
    private AccessUser accessUser;
    private int[] login = new int[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bindElements();
        listeners();
    }

    private void bindElements() {
        name = findViewById(R.id.name);
        passwd = findViewById(R.id.passwd);
        loginButton = findViewById(R.id.login);
        signin = findViewById(R.id.signin);
        rememberMe = findViewById(R.id.rememberMe);
        forgottenPasswd = findViewById(R.id.forgottenPasswd);
        sharedPreferences = getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE);
        accessUser = new ImpUser();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    private void listeners() {
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent(6);
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validations.isValidEntry(name.getText().toString()) && validations.isValidPasswd(passwd.getText().toString())) {
                    loginUser(name.getText().toString(), passwd.getText().toString());
                    if (login[1] == 0) {
                        toast(4);
                    } else {
                        if (login[1] == 1) {
                            toast(3);
                            readUser(login[0]);
                            saveSharedPreferences();
                            intent(user.getU_type());
                        } else {
                            if (login[1] == 2) {
                                toast(5);
                            }
                        }
                    }
                } else {
                    if (!validations.isValidEntry(name.getText().toString())) {
                        toast(1);
                    } else {
                        if (!validations.isValidPasswd(passwd.getText().toString())) {
                            toast(2);
                        }
                    }
                }
            }
        });
        forgottenPasswd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ForgottenPasswdActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("user", user.getU_id());
                startActivity(intent);
            }
        });
    }

    private void saveSharedPreferences() {
        if (rememberMe.isChecked()) {
            SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
            sharedPreferencesEditor.putInt("Id", user.getU_id());
            sharedPreferencesEditor.putString("Name", user.getU_name());
            sharedPreferencesEditor.putInt("Type", user.getU_type());
            if (sharedPreferencesEditor.commit()) {
                sharedPreferencesEditor.apply();
            } else {
                toast(6);
            }
        } else {
            sharedPreferences.edit().clear().apply();
        }
    }

    private void intent(int intentCase) {
        if (intentCase == 0) {
            Intent intent = new Intent(this, HomeUserActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.putExtra("user", user.getU_id());
            startActivity(intent);
        } else {
            if (intentCase == 1) {
                Intent intent = new Intent(this, HomeEditorActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.putExtra("user", user.getU_id());
                startActivity(intent);
            } else {
                if (intentCase == 2) {
                    Intent intent = new Intent(this, HomeEngineerActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("user", user.getU_id());
                    startActivity(intent);
                } else {
                    if (intentCase == 3) {
                        Intent intent = new Intent(this, HomeDeveloperActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.putExtra("user", user.getU_id());
                        startActivity(intent);
                    } else {
                        if (intentCase == 4) {
                            Intent intent = new Intent(this, HomeManagerActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            intent.putExtra("user", user.getU_id());
                            startActivity(intent);
                        } else{
                            if (intentCase == 5) {
                                Intent intent = new Intent(this, HomeOperatorActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                intent.putExtra("user", user.getU_id());
                                startActivity(intent);
                            } else {
                                if (intentCase == 6) {
                                    Intent intent = new Intent(this, SigninActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void toast(int messageCase) {
        String text = "";
        EditText editText = null;
        switch (messageCase) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
        }
        if (messageCase == 1) {
            text = "Ingrese su nombre correctamente";
            editText = name;
        } else {
            if (messageCase == 2) {
                text = "Ingrese su contraseña correctamente";
                editText = passwd;
            } else {
                if (messageCase == 3) {
                    text = "¡Bienvenido a SpaceRide " + name.getText().toString() + "!";
                } else {
                    if (messageCase == 4) {
                        text = "Usuario no encontrado";
                        editText = name;
                    } else {
                        if (messageCase == 5) {
                            text = "Contraseña incorrecta";
                            editText = passwd;
                        } else {
                            if (messageCase == 6) {
                                text = "Error al guardar las preferencias";
                            }
                        }
                    }
                }
            }
        }
        final EditText finalEditText = editText;
        SuperActivityToast.create(this, new Style(), Style.TYPE_BUTTON)
                .setButtonText("¡OK!")
                .setButtonTextColor(PaletteUtils.getSolidColor(PaletteUtils.MATERIAL_DEEP_ORANGE))
                .setButtonTypefaceStyle(Typeface.BOLD)
                .setButtonDividerColor(PaletteUtils.getSolidColor(PaletteUtils.MATERIAL_DEEP_ORANGE))
                .setOnButtonClickListener("My Music", null, new SuperActivityToast.OnButtonClickListener() {
                    @Override
                    public void onClick(View view, Parcelable token) {
                        focus(finalEditText);
                    }
                })
                .setText(text)
                .setDuration(Style.DURATION_LONG)
                .setFrame(Style.FRAME_LOLLIPOP)
                .setColor(this.getResources().getColor(R.color.colorPrimaryDark))
                .setTextColor(PaletteUtils.getSolidColor(PaletteUtils.MATERIAL_DEEP_ORANGE))
                .setAnimations(Style.ANIMATIONS_FLY).show();
    }

    private void focus(EditText editText) {
        if (editText != null) {
            editText.requestFocus();
        }
    }

    public void loginUser(final String name, final String passwd) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    login = accessUser.loginUser(name, passwd);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Error at loginuser: " + e.getMessage());
                }
            }
        });
    }

    public void readUser(final int id) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    user = accessUser.readUser(id);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Error at readUser: " + e.getMessage());
                }
            }
        });
    }

}
