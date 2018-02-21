package com.projectx.gluco.Userdetails;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.projectx.gluco.DataModels.User_info;
import com.projectx.gluco.R;

public class BasicinfoActivity extends AppCompatActivity {

    private static final String TAG = "Gluco Activity";
    private EditText iname, iage;
    private Button next_btn;
    private Spinner igender;
    private String uname, uage, ugender;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mDataref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basicinfo);

        //Declarations
        iname = findViewById(R.id.iname);
        iage = findViewById(R.id.iage);
        next_btn = findViewById(R.id.next_btn);

        //Spinner intialization
        igender = findViewById(R.id.igender);
        ArrayAdapter<CharSequence> gender_adapter = ArrayAdapter.createFromResource(this, R.array.gender_spinner, R.layout.spinner_layout);
        gender_adapter.setDropDownViewResource(R.layout.spinner_layout);
        igender.setAdapter(gender_adapter);

        //On click listener
        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Getting values
                uname = iname.getText().toString();
                uage = iage.getText().toString();
                ugender = igender.getSelectedItem().toString();
                setinfo(uname, uage, ugender); //Setinfo function for Inserting data
                /*Log.v(TAG,"This value"+uname);
                Log.v(TAG,"This value"+uage);
                Log.v(TAG,"This value"+ugender);*/

                startActivity(new Intent(BasicinfoActivity.this, MedconActivity.class));
            }
        });

    }

    private void setinfo(String username, String age, String gender) {
        mDataref = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser(); //To get uid
        String uid = user.getUid();
        User_info uinfo = new User_info(username, age, gender);
        mDataref.child("users").child(uid).setValue(uinfo);
    }


}
