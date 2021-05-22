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

    MobileMarketApp app;
    MobileMarketAPI mobileMarketApi;
    ArrayList<Farm> farms;

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();

        app = MobileMarketApp.getInstance();
        mobileMarketApi = new MobileMarketAPI(new HttpClient());

        if (app.getFarms() == null) {

            mobileMarketApi.getFarms(new APICallback<ArrayList<Farm>>() {

                @Override
                public void onCallback(ArrayList<Farm> apiResponse) {
                    farms = apiResponse;
                    app.setFarms(farms);
                    // TODO SET UP UI;
                }

                @Override
                public void onFailure(@NotNull Throwable t) {
                    // TODO SET UP ERROR UI;
                }
            });

        } else {
            farms = app.getFarms();
            // TODO SET UP UI;
        }

        // default fragment
        setFragment(new HomeFragment());

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

    private void setFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }

    private void initComponents() {

        findViewById(R.id.kmetija_test).setOnClickListener(view -> {
            Intent intent = new Intent(this, FarmActivity.class);
            intent.putExtra("ID", 1);
            startActivity(intent);
        });

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