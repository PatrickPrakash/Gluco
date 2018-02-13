package com.projectx.gluco;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.projectx.gluco.Fragments.AboutFragment;
import com.projectx.gluco.Fragments.ActivityFragment;
import com.projectx.gluco.Fragments.HomeFragment;
import com.projectx.gluco.Fragments.LogFragment;

import java.lang.reflect.Field;

public class ConsoleActivity extends AppCompatActivity {

        //View Intialisations
        private BottomNavigationView bottom_navigation;
        private FrameLayout frameLayout;
        private AboutFragment aboutFragment;
        private ActivityFragment activityFragment;
        private HomeFragment homeFragment;
        private LogFragment logFragment;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_console);

        //View Declarations
        bottom_navigation = findViewById(R.id.bottom_navigation);
        frameLayout = findViewById(R.id.framelayout);

        //Fragment Objects
        homeFragment = new HomeFragment();
        logFragment = new LogFragment();
        aboutFragment = new AboutFragment();
        activityFragment = new ActivityFragment();
        DefaultFragment(homeFragment); //Sets Home as Default Fragment
        disableShiftMode(bottom_navigation);//Disable shift mode function

        bottom_navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId())
                {
                    case R.id.nav_home :
                        setFragment(homeFragment);
                        return true;

                    case R.id.nav_log :
                        setFragment(logFragment);
                        return true;

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
    private void setFragment(Fragment fragment)
    {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.framelayout,fragment);
        fragmentTransaction.commit();
    }
    private void DefaultFragment(Fragment fragment)
    {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.framelayout,fragment);
        fragmentTransaction.commit();
    }
}
