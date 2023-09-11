package application.android.com.rushtechnologies.spaceride.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import application.android.com.rushtechnologies.spaceride.Activities.Home.HomeDeveloperActivity;
import application.android.com.rushtechnologies.spaceride.Activities.Home.HomeEditorActivity;
import application.android.com.rushtechnologies.spaceride.Activities.Home.HomeEngineerActivity;
import application.android.com.rushtechnologies.spaceride.Activities.Home.HomeManagerActivity;
import application.android.com.rushtechnologies.spaceride.Activities.Home.HomeOperatorActivity;
import application.android.com.rushtechnologies.spaceride.Activities.Home.HomeUserActivity;
import application.android.com.rushtechnologies.spaceride.DAO.AccessUser;
import application.android.com.rushtechnologies.spaceride.ImpDAO.ImpUser;
import application.android.com.rushtechnologies.spaceride.Model.User;
import application.android.com.rushtechnologies.spaceride.R;

public class MainActivity extends AppCompatActivity {
    private final static int SPLASH_SCREEN_TIMEOUT = 1500;
    private User user = new User();
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String name = sharedPreferences.getString("Name", null);
                if (name != null) {
                    int id = sharedPreferences.getInt("Id", 0);
                    user.setU_id(id);
                    int type = sharedPreferences.getInt("Type", 0);
                    intent(type);
                } else {
                    intent(6);
                }
            }
        }, SPLASH_SCREEN_TIMEOUT);
    }

    public void intent(int intent_case) {
        if (intent_case == 0) {
            Intent intent = new Intent(this, HomeUserActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION);
            intent.putExtra("user", user.getU_id());
            startActivity(intent);
        } else {
            if (intent_case == 1) {
                Intent intent = new Intent(this, HomeEditorActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra("user", user.getU_id());
                startActivity(intent);
            } else {
                if (intent_case == 2) {
                    Intent intent = new Intent(this, HomeEngineerActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    intent.putExtra("user", user.getU_id());
                    startActivity(intent);
                } else {
                    if (intent_case == 3) {
                        Intent intent = new Intent(this, HomeDeveloperActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        intent.putExtra("user", user.getU_id());
                        startActivity(intent);
                    } else {
                        if (intent_case == 4) {
                            Intent intent = new Intent(this, HomeManagerActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            intent.putExtra("user", user.getU_id());
                            startActivity(intent);
                        } else{
                            if (intent_case == 5) {
                                Intent intent = new Intent(this, HomeOperatorActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                intent.putExtra("user", user.getU_id());
                                startActivity(intent);
                            } else {
                                if (intent_case == 6) {
                                    Intent intent = new Intent(this, LogInOrSignInActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                    startActivity(intent);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
