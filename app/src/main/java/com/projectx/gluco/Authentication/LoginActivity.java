package com.projectx.gluco.Authentication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.projectx.gluco.R;
import com.projectx.gluco.Userdetails.BasicinfoActivity;

public class LoginActivity extends AppCompatActivity {

    private TextView uSignup,Fpass;
    private EditText uEmail,uPass;
    private Button uSignin;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthstateListener;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        uSignup = findViewById(R.id.uSignup);
        uSignin = findViewById(R.id.uSignin);
        uEmail = findViewById(R.id.uEmail);
        uPass = findViewById(R.id.uPass);
        uEmail = findViewById(R.id.uEmail);
        mAuth = FirebaseAuth.getInstance();
        mAuthstateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null) {
                    //startActivity(new Intent(LoginActivity.this, ConsoleActivity.class));
                }
            }
        };
        Signin();
        textAction();
    }

        public void Signin()
        {
            uSignin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String uFemail = uEmail.getText().toString().trim();
                    String uFpass = uPass.getText().toString().trim();

                        mAuth.signInWithEmailAndPassword(uFemail, uFpass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            public final String TAG = null;

                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful())
                                {
                                    startActivity(new Intent(LoginActivity.this, BasicinfoActivity.class));
                                    Toast.makeText(LoginActivity.this, "SignIn Successful", Toast.LENGTH_SHORT).show();
                                } else
                                    {
                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    Toast.makeText(LoginActivity.this, "SignIn Failed", Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
                }
            });
        }


   private void textAction()
    {
        uSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this,RegisterMailActivity.class);
                startActivity(i);
            }
        });
    }
}
