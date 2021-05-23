package vega.solutions.mobilefoodmarket2;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import vega.solutions.mobilefoodmarket2.object.Farm;
import vega.solutions.mobilefoodmarket2.object.Item;
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

        ArrayList<Item> itemsSeparated = new ArrayList<>();

        for (Item item : farm.getItems()) {
            if (!containsCategory(itemsSeparated, item.getCategory())) {
                itemsSeparated.add(new Item(-2, item.getCategory(), 0.0f, null, null));
                for (Item item1 : farm.getItems()) {
                    if (item1.getCategory().equals(item.getCategory())) {
                        itemsSeparated.add(item1);
                    }
                }

            }
        }

        initList(itemsSeparated);
        setStars(farm.getRating());

    }

    private boolean containsCategory(ArrayList<Item> items, String category) {
        for (Item item : items) {
            if (item.getCategory() == null) {
                continue;
            }
            if (item.getCategory().equals(category)) {
                return true;
            }
        }
        return false;
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

    private void setStars(int mark) {
        if (mark > 5 || mark < 1) {
            return;
        }

        ImageView s1 = findViewById(R.id.ic_star1);
        ImageView s2 = findViewById(R.id.ic_star2);
        ImageView s3 = findViewById(R.id.ic_star3);
        ImageView s4 = findViewById(R.id.ic_star4);
        ImageView s5 = findViewById(R.id.ic_star5);

        ImageView[] stars = new ImageView[5];
        stars[0] = s1;
        stars[1] = s2;
        stars[2] = s3;
        stars[3] = s4;
        stars[4] = s5;

        for (int i = 0; i < mark; i++) {
            stars[i].setColorFilter(ContextCompat.getColor(getApplicationContext(),
                    R.color.yellow));
        }
    }

    private void initList(List items) {
        rv.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        rv.setNestedScrollingEnabled(false);

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