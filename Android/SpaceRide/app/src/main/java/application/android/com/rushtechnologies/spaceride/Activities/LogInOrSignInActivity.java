package application.android.com.rushtechnologies.spaceride.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import application.android.com.rushtechnologies.spaceride.R;

public class LogInOrSignInActivity extends AppCompatActivity {
    private Button signin, login;
    private ImageButton signin_img, login_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_or_sign_in);
        initView();
    }

    public void initView() {
        signin = findViewById(R.id.signin);
        login = findViewById(R.id.login);
        signin_img = findViewById(R.id.signin_img);
        login_img = findViewById(R.id.login_img);
        listeners();
    }

    public void listeners() {
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent(0);
            }
        });
        signin_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent(0);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent(1);
            }
        });
        login_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent(1);
            }
        });
    }

    public void intent(int intent_case) {
        Intent intent;
        switch (intent_case) {
            case 0:
                intent = new Intent(this, SigninActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            case 1:
                intent = new Intent(this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
        }
    }
}
