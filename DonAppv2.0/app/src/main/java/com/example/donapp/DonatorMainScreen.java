package com.example.donapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import DonatorBottomNavigationFragments.HomeFragment;
import DonatorBottomNavigationFragments.NotificationFragment;
import DonatorBottomNavigationFragments.SettingsFragment;

public class DonatorMainScreen extends AppCompatActivity {

    public  static Integer ID;
    public static String AccountUserDonator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        setCurrentAccountUsername();
        setCurrentID();
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);


        //WHEN THIS STATEMENT IS NOT PRESENT, A BLANK WHITE SCREEN WILL BE SHOWN BECAUSE NONE OF THE FRAGMENTS ARE CLICKED RIGHT NOW...
        //BY DEFAULT WE SELECT HOME FRAGMENT ON OPENING THE APP...
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
    }
    // Revtrive ID from login page
    private Integer setCurrentID() {
        Bundle B = getIntent().getExtras();
        ID = B.getInt("ID_AccountDonator");
        return ID;
    }

    // RETRIVE INFORMATION ACCOUNT NAME FROM THE LOGIN PAGE
    public String setCurrentAccountUsername(){
        Bundle B = getIntent().getExtras();
        AccountUserDonator = B.getString("DonatorAccountName");
        return AccountUserDonator;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment  = null;

                    //ASSIGNING THE GIVEN THE FRAGMENT TO SELECTED FRAGMENT WHEN ANY OF THE THREE FRAGMENTS ARE CLICKED...
                    switch (item.getItemId()){
                        case R.id.nav_home:
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.nav_notifications:
                            selectedFragment = new NotificationFragment();
                            break;
                        case R.id.nav_settings:
                            selectedFragment = new SettingsFragment();
                            break;
                    }


                    //THIS METHOD DOES THE TASK OF WHAT TO SHOW WHEN ANY OF THE FRAGMENTS IS CLICKED...
                    //HERE "fragment container" is the id of the MainActivity xml which is place where to show the changes...
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };
}

