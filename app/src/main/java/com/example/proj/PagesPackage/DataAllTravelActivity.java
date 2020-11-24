package com.example.proj.PagesPackage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.proj.R;
import com.example.proj.ModelsPackage.TravelModel;
import com.squareup.picasso.Picasso;

import java.util.Locale;

public class DataAllTravelActivity extends AppCompatActivity implements View.OnClickListener, TextToSpeech.OnInitListener {

    private TextView locationName, equipmentList, description;
    private ImageView imageUrl;
    private TravelModel travelModel;
    private Button btnBack, btnMap, btnSpeak;
    private TextToSpeech t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_all_travel);

        initUI();
        initListeners();
    }

    @Override
    public void onPause() {
        super.onPause();

        if (t1 != null) {
            t1.stop();
            t1.shutdown();
        }
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            t1.setLanguage(Locale.ENGLISH);
            t1.setPitch(1);
        }
    }

    private void initUI() {
        travelModel = (TravelModel) getIntent().getExtras().getSerializable(getString(R.string.dataFirestore));

        description = findViewById(R.id.description);
        locationName = findViewById(R.id.locationName);
        equipmentList = findViewById(R.id.equipmentList);
        imageUrl = findViewById(R.id.imageUrl);
        btnBack = findViewById(R.id.btnBack);
        btnMap = findViewById(R.id.btnMap);
        btnSpeak = findViewById(R.id.btnSpeak);

        locationName.setText(travelModel.getLocationName());
        equipmentList.setText(travelModel.getEquipmentList().replace(",", "\n"));
        description.setText(travelModel.getDescription().replace(",", "\n"));
        Picasso.get().load(travelModel.getImageUrl()).into(imageUrl);

        t1 = new TextToSpeech(this, this);
    }

    private void initListeners() {
        btnBack.setOnClickListener(this);
        btnMap.setOnClickListener(this);
        btnSpeak.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (btnBack.getId() == v.getId()) {
            onBackPressed();
        }
        if (btnMap.getId() == v.getId()) {
            Intent intent = new Intent(this, MapActivity.class);
            intent.putExtra(getString(R.string.dataFirestore), travelModel);
            startActivity(intent);
        }
        if (btnSpeak.getId() == v.getId()) {
            String toSpeak = travelModel.getLocationName() +
                    ".\n" + travelModel.getEquipmentList() +
                    ".\n" + travelModel.getDescription();
            t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null, null);
        }
    }

}
