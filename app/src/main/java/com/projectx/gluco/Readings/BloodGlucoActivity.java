package com.projectx.gluco.Readings;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.projectx.gluco.DataModels.Blood_gluco;
import com.projectx.gluco.MainActivities.ConsoleActivity;
import com.projectx.gluco.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class BloodGlucoActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = "Blood_gluco";
    TextInputEditText gluco_add_con, gluco_add_date, gluco_add_notes, gluco_add_time;
    FloatingActionButton floatingActionButton;
    String gluco_con, gluco_date, gluco_notes, gluco_newdate, gluco_period_string, gluco_time, gluco_key;
    Calendar myCalendar = Calendar.getInstance();
    TimePickerDialog mTimePicker;
    Spinner gluco_period;
    DatabaseReference mDataref;
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {

            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bloodgluco);
        gluco_add_con = findViewById(R.id.gluco_add_con);
        gluco_add_date = findViewById(R.id.gluco_add_date);
        gluco_add_notes = findViewById(R.id.gluco_add_notes);
        gluco_add_time = findViewById(R.id.gluco_add_time);
        floatingActionButton = findViewById(R.id.floating_action_button);
        gluco_add_con = findViewById(R.id.gluco_add_con);
        gluco_add_date = findViewById(R.id.gluco_add_date);
        gluco_add_notes = findViewById(R.id.gluco_add_notes);
        gluco_period = findViewById(R.id.gluco_period);
        floatingActionButton.setOnClickListener(this);
        gluco_add_time.setOnClickListener(this);
        gluco_add_date.setOnClickListener(this);

        ArrayAdapter<CharSequence> period_adapter = ArrayAdapter.createFromResource(this, R.array.period_spinner, R.layout.spinner_layout);
        period_adapter.setDropDownViewResource(R.layout.spinner_layout);
        gluco_period.setAdapter(period_adapter);

    }

    private void updateLabel() {
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        gluco_add_date.setText(sdf.format(myCalendar.getTime()));
        gluco_newdate = sdf.format(myCalendar.getTime());
        gluco_newdate = gluco_newdate.replace("/", "-");
    }

    private void firebase_database() {
        try {
            mDataref = FirebaseDatabase.getInstance().getReference();
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser(); //To get uid
            String uid = user.getUid();
            Blood_gluco blood_gluco = new Blood_gluco(gluco_con, gluco_date, gluco_time, gluco_period_string, gluco_notes);
            gluco_key = mDataref.push().getKey();
            blood_gluco.setKey(gluco_key);
            mDataref.child("User_Readings").child(uid).child("Blood_Glucose").child(gluco_key).setValue(blood_gluco);
        } catch (Exception e) {
            Toast.makeText(this, "Error code:1002,Database Connection Failed", Toast.LENGTH_SHORT).show();
        }
    }


    private void updateTime() {
        int hour = myCalendar.get(Calendar.HOUR_OF_DAY);
        int minute = myCalendar.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(BloodGlucoActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                gluco_add_time.setText(selectedHour + ":" + selectedMinute);
            }
        }, hour, minute, true);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.floating_action_button:
                gluco_con = gluco_add_con.getText().toString();
                gluco_date = gluco_newdate;
                gluco_notes = gluco_add_notes.getText().toString();
                gluco_period_string = gluco_period.getSelectedItem().toString();
                gluco_time = gluco_add_time.getText().toString();
                firebase_database();
                startActivity(new Intent(BloodGlucoActivity.this, ConsoleActivity.class));
                break;

            case R.id.gluco_add_date:
                new DatePickerDialog(BloodGlucoActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                break;

            case R.id.gluco_add_time:
                int hour = myCalendar.get(Calendar.HOUR_OF_DAY);
                int minute = myCalendar.get(Calendar.MINUTE);
                updateTime();
                break;


            default:
                break;

        }
    }
}



