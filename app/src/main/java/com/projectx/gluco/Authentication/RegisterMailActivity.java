package com.projectx.gluco.Authentication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.projectx.gluco.ConsoleActivity;
import com.projectx.gluco.R;

import static android.widget.Toast.makeText;

public class RegisterMailActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private EditText uEmail;
    private EditText uPass;
    private EditText uCPass;
    private Button uSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_signup);
        uEmail = (EditText) findViewById(R.id.uEmail);
        uPass = (EditText) findViewById(R.id.uPass);
        uCPass = (EditText) findViewById(R.id.uCPass);
        uSignup = (Button) findViewById(R.id.uSignin);
        mAuth = FirebaseAuth.getInstance();
        final Intent i = new Intent(RegisterMailActivity.this,ConsoleActivity.class);
        emailSignup();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if (firebaseAuth.getCurrentUser() != null) {
                    //startActivity(i);
                }
            }
        };
    }

    //Traditional Email Sign up method
    private void emailSignup()
    {
        uSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uFemail = uEmail.getText().toString().trim();
                String uFpass = uPass.getText().toString().trim();
                String uFcpass = uCPass.getText().toString().trim();
                if (!TextUtils.isEmpty(uFcpass) && !TextUtils.isEmpty(uFpass)){

                    mAuth.createUserWithEmailAndPassword(uFemail,uFpass).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(RegisterMailActivity.this,"Authentication Successful",Toast.LENGTH_SHORT).show();
                            }else
                            {
                                Toast.makeText(RegisterMailActivity.this,"Authentication Failed",Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }//Ending of if
            }
        }); //Ending of OnclickListener

    }
    @Override
    protected void onStart() {
        super.onStart();
        //Check if user is signed in
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    @Override
    protected void onPause() {
        super.onPause();

    }
}










