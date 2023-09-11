package application.android.com.rushtechnologies.spaceride.Activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Parcelable;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

public class SigninActivity extends AppCompatActivity {
    private EditText name, passwd, email, rpasswd;
    private Button login, signin;
    private User user;
    private boolean create;
    private final Validations VALIDATIONS = new Validations();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        bindUI();
        listeners();
    }

    private void bindUI() {
        name = findViewById(R.id.name);
        passwd = findViewById(R.id.passwd);
        rpasswd = findViewById(R.id.rpasswd);
        email = findViewById(R.id.email);
        login = findViewById(R.id.login);
        signin = findViewById(R.id.signin);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    private void listeners() {
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (VALIDATIONS.isValidEntry(name.getText().toString()) && VALIDATIONS.isValidEmail(email.getText().toString()) && VALIDATIONS.isValidPasswd(passwd.getText().toString()) && VALIDATIONS.entriesMatch(passwd.getText().toString(), rpasswd.getText().toString())) {
                    user = new User(0, name.getText().toString(), passwd.getText().toString(), email.getText().toString(), getUserType(), 0, 0);
                    createUser(user);
                    if (create) {
                        toast(5);
                        intent(user.getU_type());
                    } else {
                        toast(6);
                    }
                } else {
                    if (!VALIDATIONS.isValidEntry(name.getText().toString())) {
                        toast(1);
                    } else {
                        if (!VALIDATIONS.isValidEmail(email.getText().toString())) {
                            toast(2);
                        } else {
                            if (!VALIDATIONS.isValidPasswd(passwd.getText().toString())) {
                                toast(3);
                            } else {
                                if (!VALIDATIONS.entriesMatch(passwd.getText().toString(), rpasswd.getText().toString())) {
                                    toast(4);
                                }
                            }
                        }
                    }
                }
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent(6);
            }
        });
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
                        } else {
                            if (intentCase == 5) {
                                Intent intent = new Intent(this, HomeOperatorActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                intent.putExtra("user", user.getU_id());
                                startActivity(intent);
                            } else {
                                if (intentCase == 6) {
                                    Intent intent = new Intent(this, LoginActivity.class);
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

    private void createUser(final User user) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    final AccessUser ACCESS_USER = new ImpUser();
                    create = ACCESS_USER.createUser(user);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Error at createUser: " + e.getMessage());
                }
            }
        });
    }

    private int getUserType() {
        int type = 0;
        switch (email.getText().toString()) {
            case "brenbetto17190@hotmail.com":
                type = 1;
                break;
            case "chavaramos.ipn@gmail.com":
                type = 2;
                break;
            case "rhayyim@hotmail.com":
                type = 3;
                break;
            case "huerta2502@hotmail.com":
                type = 4;
                break;
            case "huerta2502@gmail.com":
                type = 5;
                break;
            default:
                type = 0;
                break;
        }
        return type;
    }

    private void toast(int messageCase) {
        String text = "";
        EditText editText = null;
        if (messageCase == 1) {
            text = "Ingrese su nombre correctamente";
            editText = name;
        } else {
            if (messageCase == 2) {
                text = "Ingrese su correo correctamente";
                editText = email;
            } else {
                if (messageCase == 3) {
                    text = "Ingrese su contraseña correctamente";
                    editText = passwd;
                } else {
                    if (messageCase == 4) {
                        text = "Las contraseñas no coinciden";
                        editText = rpasswd;
                    } else {
                        if (messageCase == 5) {
                            text = "¡Bienvenido a SpaceRide " + name.getText().toString() + "!";
                        } else {
                            if (messageCase == 6) {
                                text = "Error al crear usuario";
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

}
