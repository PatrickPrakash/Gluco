package com.projectx.gluco.Authentication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.projectx.gluco.R;

public class LoginActivity extends AppCompatActivity {

    private TextView createacc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        createacc = (TextView)  findViewById(R.id.createacc);
        textAction();
    }
   private void textAction()
    {
        createacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this,RegisterMailActivity.class);
                startActivity(i);
            }
        });
    }
}
