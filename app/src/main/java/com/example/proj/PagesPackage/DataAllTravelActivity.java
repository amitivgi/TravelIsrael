package com.example.proj.PagesPackage;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proj.R;
import com.example.proj.ModelsPackage.TravelModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Locale;

import static android.speech.tts.TextToSpeech.*;

public class DataAllTravelActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView locationName, equipmentList, description;
    private ImageView imageUrl;
    private TravelModel travelModel;
    private Button btnBack, btnMap, btnSpeak, btnChooseLanguage, btnStopSpeak, btnWeather;
    private TextToSpeech t1;
    private Locale localeLanguage = Locale.US;

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
    protected void onStop() {
        super.onStop();

        if (t1 != null) {
            t1.stop();
            t1.shutdown();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (t1 != null) {
            t1.stop();
            t1.shutdown();
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
        btnChooseLanguage = findViewById(R.id.btnChooseLanguage);
        btnStopSpeak = findViewById(R.id.btnStopSpeak);
        btnWeather = findViewById(R.id.btnWeather);

        locationName.setText(travelModel.getLocationName());
        equipmentList.setText(travelModel.getEquipmentList().replace(",", "\n"));
        description.setText(travelModel.getDescription().replace(",", "\n"));
        Picasso.get().load(travelModel.getImageUrl()).into(imageUrl);

        initTextToSpeak(localeLanguage);
    }

    private void initListeners() {
        btnBack.setOnClickListener(this);
        btnMap.setOnClickListener(this);
        btnSpeak.setOnClickListener(this);
        btnChooseLanguage.setOnClickListener(this);
        btnStopSpeak.setOnClickListener(this);
        btnWeather.setOnClickListener(this);
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
            ConvertTextToSpeech();
        }
        if (btnStopSpeak.getId() == v.getId()) {
            if (t1 != null) {
                t1.stop();
                t1.shutdown();
            }
        }
        if (btnChooseLanguage.getId() == v.getId()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Choose your language");
            String[] languages = {"English", "Francais"};
            builder.setItems(languages, (dialog, which) -> {
                switch (which) {
                    case 0:
                        localeLanguage = Locale.US;
                        initTextToSpeak(localeLanguage);
                        break;
                    case 1:
                        localeLanguage = Locale.FRANCE;
                        initTextToSpeak(localeLanguage);
                        break;
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        }
        if (btnWeather.getId() == v.getId()) {
            Uri uri = Uri.parse(travelModel.getWeatherUrl());
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }
    }

    private void initTextToSpeak(Locale locale) {
        t1 = new TextToSpeech(getApplicationContext(), status -> {
            if (status == TextToSpeech.SUCCESS) {
                int result = t1.setLanguage(locale);
                if (result == TextToSpeech.LANG_MISSING_DATA ||
                        result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Log.e("error", "This Language is not supported");
                }
            } else {
                Log.e("error", "Initialization Failed!");
            }
        });
    }

    private void ConvertTextToSpeech() {
        String text = travelModel.getDescription();
        if (text == null || "".equals(text)) {
            text = "Content not available";
            t1.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        } else
            t1.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

}

