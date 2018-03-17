package com.projectx.gluco.MainActivities;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.projectx.gluco.Fragments.AboutFragment;
import com.projectx.gluco.Fragments.ActivityFragment;
import com.projectx.gluco.Fragments.BottomSheet;
import com.projectx.gluco.Fragments.HomeFragment;
import com.projectx.gluco.Fragments.LogFragment;
import com.projectx.gluco.R;

import java.lang.reflect.Field;

public class ConsoleActivity extends AppCompatActivity implements View.OnClickListener {



        //View Intialisations
        private BottomNavigationView bottom_navigation;
    private ConstraintLayout ConstraintLayout;
        private AboutFragment aboutFragment;
        private ActivityFragment activityFragment;
        private HomeFragment homeFragment;
        private LogFragment logFragment;
        private BottomNavigationItemView plus;
        private BottomNavigationItemView homeicon;
        private BottomNavigationItemView logicon;
        private BottomNavigationItemView activityicon;
        private BottomNavigationItemView abouticon;
        private ImageView pressure_read;
        private ImageView hba1c_read;
        private ImageView blood_read;

    //When back button is pressed.
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            doExit();

        }
        return super.onKeyDown(keyCode, event);

    }



    //onCreate Function
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_console);


        //View Declarations
        bottom_navigation = findViewById(R.id.bottom_navigation);
        ConstraintLayout = findViewById(R.id.ConstraintLayout);
        plus = findViewById(R.id.nav_plus);
        blood_read = findViewById(R.id.blood_read);
        pressure_read = findViewById(R.id.pressure_read);
        hba1c_read = findViewById(R.id.hba1c_read);


        //Fragment Objects
        homeFragment = new HomeFragment();
        logFragment = new LogFragment();
        aboutFragment = new AboutFragment();
        activityFragment = new ActivityFragment();


        //Bottomnavigationbaricons
        homeicon = findViewById(R.id.nav_home);
        plus = findViewById(R.id.nav_plus);
        logicon = findViewById(R.id.nav_log);
        activityicon = findViewById(R.id.nav_activity);
        abouticon = findViewById(R.id.nav_about);
        DefaultFragment(homeFragment); //Sets Home as Default Fragment
        disableShiftMode(bottom_navigation);//Disable shift mode function


        //OnClickListeners
        plus.setOnClickListener(this);
       /* pressure_read.setOnClickListener(this);
        hba1c_read.setOnClickListener(this);*/
        bottom_navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId())
                {
                    case R.id.nav_home :
                        setFragment(homeFragment, "HOMEFRAGMENT");
                        return true;

                    case R.id.nav_log :
                        setFragment(logFragment);
                        return true;
                    case R.id.nav_plus :


                    case R.id.nav_activity :
                        setFragment(activityFragment);
                        return true;

                    case R.id.nav_about :
                        setFragment(aboutFragment);
                        return true;

                    default :
                        return false;
                }
            }
        });
    }




    //Popup prompt
    private void doExit() {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                ConsoleActivity.this);

        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity();//To destroy all the activities
            }
        });

        alertDialog.setNegativeButton("No", null);

        alertDialog.setMessage("Do you want to exit?");
        alertDialog.setTitle("Gluco");
        alertDialog.show();
    }





    //To Disable Shifting mode in the bottom navigation view
    @SuppressLint("RestrictedApi")
    private void disableShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                item.setShiftingMode(false);
                // set once again checked value, so view will be updated
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            Log.e("BNVHelper", "Unable to get shift mode field", e);
        } catch (IllegalAccessException e) {
            Log.e("BNVHelper", "Unable to change value of shift mode", e);
        }
    }





    //Set Fragment on Frame layout
    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.ConstraintLayout, fragment);
        fragmentTransaction.commit();
    }

    private void setFragment(Fragment fragment, String fragmentTag) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.ConstraintLayout, fragment, fragmentTag);
        fragmentTransaction.commit();
    }


    private void DefaultFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.ConstraintLayout, fragment, "HOMEFRAGMENT");
        fragmentTransaction.commit();

    }



    //OnClickListener
    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.nav_plus :
                //Calling Fragment
                BottomSheet bottomSheetDialog = new BottomSheet();
                bottomSheetDialog.show(getSupportFragmentManager(),bottomSheetDialog.getTag());
                break;

            default:
                    break;
        }
    }
}
