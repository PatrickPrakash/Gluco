package com.projectx.gluco.Authentication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.projectx.gluco.R;

public class ForgotPassActivity extends AppCompatActivity {
    private Button ubtn;
    private TextInputEditText uemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);
        ubtn = findViewById(R.id.ubtn);
        uemail = findViewById(R.id.uemail);

        ubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailaddress;
                emailaddress = uemail.getText().toString();
                FirebaseAuth auth = FirebaseAuth.getInstance();
                auth.sendPasswordResetEmail(emailaddress)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Log.d("Forgot Password", "Email sent.");
                                    Toast.makeText(ForgotPassActivity.this, "Email Sent", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(ForgotPassActivity.this, "Email not Sent", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }


}
