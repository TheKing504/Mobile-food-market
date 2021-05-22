package vega.solutions.mobilefoodmarket2;

import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class FarmActivity extends AppCompatActivity {

    TextView farmName;
    TextView farmDescription;
    TextView farmAddress;
    TextView farmTelephone;
    TextView farmEmail;
    TextView farmDistance;

    RecyclerView items;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm);
        initComponents();

        setBasicFarmData("Kralj Kmetija", "Domače klobase", "Hrastje pri Grosupljem 16", "041 680 213", "domenkralj.sahist@gmail.com", "12 km");

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

    private void initComponents() {
        farmName = findViewById(R.id.farm_name);
        farmDescription = findViewById(R.id.farm_description);
        farmAddress = findViewById(R.id.farm_address);
        farmTelephone = findViewById(R.id.farm_telephone);
        farmEmail = findViewById(R.id. farm_email);
        farmDistance = findViewById(R.id.farm_distance);

        items = findViewById(R.id.rv_items);
    }
}