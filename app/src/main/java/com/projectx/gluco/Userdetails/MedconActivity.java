package com.projectx.gluco.Userdetails;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.projectx.gluco.MainActivities.ConsoleActivity;
import com.projectx.gluco.R;

public class MedconActivity extends AppCompatActivity {

    private static final String TAG = "Med Activity";
    ToggleButton diapre, diatype1, diatype2;
    Button back_btn, next_btn;
    String med_state;
    CompoundButton.OnCheckedChangeListener changeChecker = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                if (buttonView == diapre) {
                    diatype1.setChecked(false);
                    diatype2.setChecked(false);
                    med_state = "PreDiabetes";
                }
                if (buttonView == diatype1) {
                    diapre.setChecked(false);
                    diatype2.setChecked(false);
                    med_state = "Diabetes Type1";
                }
                if (buttonView == diatype2) {
                    diapre.setChecked(false);
                    diatype1.setChecked(false);
                    med_state = "Diabetes Type2";
                }
            }
        }
    };
    private DatabaseReference mDataref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medcon);

        diapre = findViewById(R.id.diapre);
        diatype1 = findViewById(R.id.diatype1);
        diatype2 = findViewById(R.id.diatype2);
        back_btn = findViewById(R.id.back_btn);
        next_btn = findViewById(R.id.next_btn);

        //SetonCheckedChangeListeners
        diapre.setOnCheckedChangeListener(changeChecker);
        diatype1.setOnCheckedChangeListener(changeChecker);
        diatype2.setOnCheckedChangeListener(changeChecker);

        //OnClickListener

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MedconActivity.this, BasicinfoActivity.class));
            }
        });

        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDataref = FirebaseDatabase.getInstance().getReference();
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser(); //To get uid
                String uid = user.getUid();
                mDataref.child("users").child(uid).child("medcon").setValue(med_state);
              /*  Log.v(TAG,"The value"+med_state);*/
                startActivity(new Intent(MedconActivity.this, ConsoleActivity.class));
            }
        });
    }


}
