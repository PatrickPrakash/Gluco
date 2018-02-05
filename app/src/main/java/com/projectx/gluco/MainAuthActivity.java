package com.projectx.gluco;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainAuthActivity extends AppCompatActivity {

    private Button g_signin;
    private Button e_signin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_auth);
        g_signin = (Button) findViewById(R.id.google_btn);
        e_signin = (Button) findViewById(R.id.email_btn);
        btnaction();
    }
        private void btnaction()
            {
                e_signin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent emailsign = new Intent(MainAuthActivity.this,LoginActivity.class);
                        startActivity(emailsign);
                    }
                });

            }

}
