package com.example.proj.PagesPackage;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proj.R;
import com.example.proj.UtilsPackage.FirebaseManager;

public class AddTravelActivity extends AppCompatActivity implements View.OnClickListener {

    private String locationTravel = "travelnorth";
    private EditText description, equipmentList, imageUrl, lat, lng, locationName, water, weatherUrl;
    private Button btnDialogLocation, btnSaveTravel;
    private FirebaseManager firebaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_travel);

        initUI();
        initListeners();
    }

    private void initUI() {
        description = findViewById(R.id.description);
        equipmentList = findViewById(R.id.equipmentList);
        imageUrl = findViewById(R.id.imageUrl);
        lat = findViewById(R.id.lat);
        lng = findViewById(R.id.lng);
        locationName = findViewById(R.id.locationName);
        water = findViewById(R.id.water);
        weatherUrl = findViewById(R.id.weatherUrl);
        btnDialogLocation = findViewById(R.id.btnDialogLocation);
        btnSaveTravel = findViewById(R.id.btnSaveTravel);

        firebaseManager = new FirebaseManager();
        firebaseManager.initFirestore();
    }

    private void initListeners() {
        btnDialogLocation.setOnClickListener(this);
        btnSaveTravel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (btnDialogLocation.getId() == v.getId()) {
            alertDialogTravel();
        }
        if (btnSaveTravel.getId() == v.getId()) {
            firebaseManager.addDocumentFirestore(this, locationTravel, description.getText().toString(),
                    equipmentList.getText().toString(), imageUrl.getText().toString(),
                    Double.parseDouble(lat.getText().toString()), Double.parseDouble(lng.getText().toString()),
                    locationName.getText().toString(), Boolean.parseBoolean(water.getText().toString()),
                    weatherUrl.getText().toString());
        }
    }

    private void alertDialogTravel() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose your location");
        String[] locationTravel = {"North", "Central", "South"};
        builder.setItems(locationTravel, (dialog, which) -> {
            switch (which) {
                case 0:
                    this.locationTravel = "travelnorth";
                    break;
                case 1:
                    this.locationTravel = "travelcentral";
                    break;
                case 2:
                    this.locationTravel = "travelsouth";
                    break;
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
