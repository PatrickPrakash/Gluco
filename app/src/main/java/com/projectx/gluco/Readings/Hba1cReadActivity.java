package com.projectx.gluco.Readings;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.projectx.gluco.DataModels.Hba1c_gluco;
import com.projectx.gluco.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Hba1cReadActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "Hba1c_Activity";
    Calendar myCalendar = Calendar.getInstance();
    TimePickerDialog mTimePicker;
    DatabaseReference databaseReference;
    private EditText hba1c_add_con, hba1c_add_date, hba1c_add_time, hba1c_add_notes;
    private Spinner hba1c_period;
    private FloatingActionButton floatingActionButton;
    private String hba1c_newdate, hba1c_con, hba1c_time, hba1c_date, hba1c_notes, hba1c_period_string;
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hba1c);
        hba1c_add_con = findViewById(R.id.hba1c_add_con);
        hba1c_add_date = findViewById(R.id.hba1c_add_date);
        hba1c_add_time = findViewById(R.id.hba1c_add_time);
        hba1c_period = findViewById(R.id.hba1c_period);
        hba1c_add_notes = findViewById(R.id.hba1c_add_notes);
        floatingActionButton = findViewById(R.id.floating_action_button);
        hba1c_period = findViewById(R.id.hba1c_period);
        floatingActionButton.setOnClickListener(this);
        hba1c_add_time.setOnClickListener(this);
        hba1c_add_date.setOnClickListener(this);


        ArrayAdapter<CharSequence> period_adapter = ArrayAdapter.createFromResource(this, R.array.period_spinner, R.layout.spinner_layout);
        period_adapter.setDropDownViewResource(R.layout.spinner_layout);
        hba1c_period.setAdapter(period_adapter);
    }

    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        hba1c_add_date.setText(sdf.format(myCalendar.getTime()));
        hba1c_newdate = sdf.format(myCalendar.getTime());
        hba1c_newdate = hba1c_newdate.replace("/", "-");
    }

    private void updateTime() {
        int hour = myCalendar.get(Calendar.HOUR_OF_DAY);
        int minute = myCalendar.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(Hba1cReadActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                hba1c_add_time.setText(selectedHour + ":" + selectedMinute);
            }
        }, hour, minute, true);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();

    }

    private void firebase_database() {
        try {
            databaseReference = FirebaseDatabase.getInstance().getReference();
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser(); //To get uid
            String uid = user.getUid();
            Hba1c_gluco hba1c_gluco = new Hba1c_gluco(hba1c_con, hba1c_date, hba1c_time, hba1c_notes, hba1c_period_string);
            databaseReference.child("User_Readings").child(uid).child("Hba1c_Readings").child(hba1c_date).child(hba1c_time).setValue(hba1c_gluco);
        } catch (Exception e) {
            Toast.makeText(this, "Error code:1002,Database Connection Failed", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.floating_action_button:
                hba1c_con = hba1c_add_con.getText().toString();
                hba1c_date = hba1c_newdate;
                hba1c_notes = hba1c_add_notes.getText().toString();
                hba1c_period_string = hba1c_period.getSelectedItem().toString();
                hba1c_time = hba1c_add_time.getText().toString();
                Log.v(TAG, "New date:" + hba1c_date);
                Log.v(TAG, "New value:" + hba1c_con);
                Log.v(TAG, "New value:" + hba1c_notes);
                Log.v(TAG, "New value:" + hba1c_period_string);
                Log.v(TAG, "New value:" + hba1c_time);
                firebase_database();
                break;

            case R.id.hba1c_add_date:
                new DatePickerDialog(Hba1cReadActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                break;

            case R.id.hba1c_add_time:
                updateTime();
                break;

            default:
                break;

        }
    }
}
