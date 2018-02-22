package com.projectx.gluco.Readings;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
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
import com.projectx.gluco.DataModels.Pressure_gluco;
import com.projectx.gluco.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class BloodPressureActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "Pressure_Activity";
    Calendar myCalendar = Calendar.getInstance();
    TimePickerDialog mTimePicker;
    DatabaseReference databaseReference;
    private EditText pressure_add_min, pressure_add_max, pressure_add_time, pressure_add_date, pressure_add_notes;
    private Spinner pressure_period;
    private FloatingActionButton floatingActionButton;
    private String pressure_newdate, pressure_max, pressure_min, pressure_time, pressure_date, pressure_notes, pressure_period_string;
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
        setContentView(R.layout.activity_blood_pressure);
        pressure_add_max = findViewById(R.id.pressure_add_max);
        pressure_add_min = findViewById(R.id.pressure_add_min);
        pressure_add_time = findViewById(R.id.pressure_add_time);
        pressure_period = findViewById(R.id.pressure_period);
        pressure_add_notes = findViewById(R.id.pressure_add_notes);
        pressure_add_date = findViewById(R.id.pressure_add_date);
        pressure_add_time = findViewById(R.id.pressure_add_time);
        floatingActionButton = findViewById(R.id.floating_action_button);
        pressure_period = findViewById(R.id.pressure_period);
        floatingActionButton.setOnClickListener(this);
        pressure_add_time.setOnClickListener(this);
        pressure_add_date.setOnClickListener(this);


        ArrayAdapter<CharSequence> period_adapter = ArrayAdapter.createFromResource(this, R.array.period_spinner, R.layout.spinner_layout);
        period_adapter.setDropDownViewResource(R.layout.spinner_layout);
        pressure_period.setAdapter(period_adapter);
    }

    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        pressure_add_date.setText(sdf.format(myCalendar.getTime()));
        pressure_newdate = sdf.format(myCalendar.getTime());
        pressure_newdate = pressure_newdate.replace("/", "-");
    }

    private void updateTime() {
        int hour = myCalendar.get(Calendar.HOUR_OF_DAY);
        int minute = myCalendar.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(BloodPressureActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                pressure_add_time.setText(selectedHour + ":" + selectedMinute);
            }
        }, hour, minute, true);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();

    }

    private void firebase_database() {
        try {
            databaseReference = FirebaseDatabase.getInstance().getReference();
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser(); //To get uid
            String uid = null;
            if (user != null) {
                uid = user.getUid();

                Pressure_gluco pressure_gluco = new Pressure_gluco(pressure_max, pressure_min, pressure_date, pressure_notes, pressure_time, pressure_period_string);
                databaseReference.child("User_Readings").child(uid).child("Pressure_Readings").child(pressure_date).child(pressure_time).setValue(pressure_gluco);
            } else {
                Toast.makeText(this, "Error code:1001", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Error code:1002,Database Connection Failed", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.floating_action_button:
                pressure_max = pressure_add_max.getText().toString();
                pressure_min = pressure_add_min.getText().toString();
                pressure_date = pressure_newdate;
                pressure_notes = pressure_add_notes.getText().toString();
                pressure_period_string = pressure_period.getSelectedItem().toString();
                pressure_time = pressure_add_time.getText().toString();
                firebase_database();
                break;

            case R.id.pressure_add_date:
                new DatePickerDialog(BloodPressureActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                break;

            case R.id.pressure_add_time:
                updateTime();
                break;

            default:
                break;

        }
    }
}
