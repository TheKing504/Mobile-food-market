package vega.solutions.mobilefoodmarket2;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import vega.solutions.mobilefoodmarket2.object.Farm;
import vega.solutions.mobilefoodmarket2.utils.FarmItemsRecyclerAdapter;

public class FarmActivity extends AppCompatActivity {

    public static final String TAG = "FarmActivity";

    TextView farmName;
    TextView farmDescription;
    TextView farmAddress;
    TextView farmTelephone;
    TextView farmEmail;
    TextView farmDistance;
    ImageView farmImage;

    RecyclerView rv;
    FarmItemsRecyclerAdapter farmItemsRecyclerAdapter;

    int id;
    Farm farm;

    MobileMarketApp app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm);
        initComponents();

        app = MobileMarketApp.getInstance();

        if (!app.areFarmsLoaded()) {
            finish();
        }

        id = getIntent().getIntExtra("ID", -1);

        if (id == -1) {
            finish();
        }


        farm = app.getFarm(id);

        Glide.with(this).load(farm.getPhoto()).centerCrop().into(farmImage);

        setBasicFarmData(farm.getName(), farm.getDescription(), farm.getAddress(), farm.getPhone(), farm.getEmail(), "TODO");

        initList(farm.getItems());

    }

    private void setBasicFarmData(String name, String description, String address, String telephone, String email, String distance) {

        farmName.setText(name);
        farmDescription.setText(description);

        String sourceStringAddress = "<b>Naslov:</b> " + address;
        farmAddress.setText(Html.fromHtml(sourceStringAddress));

        String sourceStringTelephone = "<b>Telefon:</b> " + telephone;
        farmTelephone.setText(Html.fromHtml(sourceStringTelephone));

        String sourceStringEmail = "<b>Email:</b> " + email;
        farmEmail.setText(Html.fromHtml(sourceStringEmail));

        String sourceStringDistance = "<b>Oddaljenost:</b> " + distance;
        farmDistance.setText(Html.fromHtml(sourceStringDistance));
    }

    private void initList(List items) {
        rv.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);

        farmItemsRecyclerAdapter = new FarmItemsRecyclerAdapter(items);
        rv.setAdapter(farmItemsRecyclerAdapter);
    }

    private void initComponents() {
        farmName = findViewById(R.id.farm_name);
        farmDescription = findViewById(R.id.farm_description);
        farmAddress = findViewById(R.id.farm_address);
        farmTelephone = findViewById(R.id.farm_telephone);
        farmEmail = findViewById(R.id. farm_email);
        farmDistance = findViewById(R.id.farm_distance);
        farmImage = findViewById(R.id.farm_img);

        rv = findViewById(R.id.rv_items);
    }
}