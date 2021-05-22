package vega.solutions.mobilefoodmarket2;

import android.os.Bundle;

import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import vega.solutions.mobilefoodmarket2.fragments.HomeFragment;
import vega.solutions.mobilefoodmarket2.fragments.SearchFragment;
import vega.solutions.mobilefoodmarket2.fragments.SettingsFragment;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();

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
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
    }
}