package vega.solutions.mobilefoodmarket2;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;


import java.util.ArrayList;

import vega.solutions.mobilefoodmarket2.fragments.HomeFragment;
import vega.solutions.mobilefoodmarket2.fragments.SearchFragment;
import vega.solutions.mobilefoodmarket2.fragments.SettingsFragment;
import vega.solutions.mobilefoodmarket2.http.APICallback;
import vega.solutions.mobilefoodmarket2.http.HttpClient;
import vega.solutions.mobilefoodmarket2.object.Farm;
import vega.solutions.mobilefoodmarket2.http.MobileMarketAPI;

public class MainActivity extends AppCompatActivity {

    ArrayList<Farm> farms = new ArrayList<>();

    BottomNavigationView bottomNavigationView;

    Fragment home;
    Fragment search;
    Fragment settings;

    MobileMarketApp app;
    MobileMarketAPI mobileMarketApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();


        if (home == null)
            home = new HomeFragment();

        setFragment(home);



        // TODO - fragments should be reused
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home:

                        if (home == null)
                            home = new HomeFragment();

                        setFragment(home);
                        break;
                    case R.id.search:

                        if (search == null)
                            search = new SearchFragment();

                        setFragment(search);
                        break;
                    case R.id.settings:

                        if (settings == null)
                            settings = new SettingsFragment();

                        setFragment(settings);
                        break;
                }
                return true;
            }
        });

    }

    private void setFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }

    private void initComponents() {

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // TODO - fragments should be reused
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home:
                        setFragment(new HomeFragment());
                        break;
                    case R.id.search:
                        setFragment(new SearchFragment());
                        break;
                    case R.id.settings:
                        setFragment(new SettingsFragment());
                        break;
                }
                return true;
            }
        });

    }
}