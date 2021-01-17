package com.example.proj.PagesPackage;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proj.BroadcastReceiverPackage.BroadcastReceiverBattery;
import com.example.proj.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnD, btnM, btnTz, btnAddTravel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
        initListeners();
    }

    @Override
    protected void onResume() {
        super.onResume();

        IntentFilter iFilter = new IntentFilter();
        iFilter.addAction(Intent.ACTION_BATTERY_LOW);
        iFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(new BroadcastReceiverBattery(), iFilter);
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//
//        unregisterReceiver(new BroadcastReceiverBattery());
//    }

    private void initUI() {
        btnD = findViewById(R.id.btnD);
        btnM = findViewById(R.id.btnM);
        btnTz = findViewById(R.id.btnTz);
        btnAddTravel = findViewById(R.id.btnAddTravel);
    }

    private void initListeners() {
        btnD.setOnClickListener(this);
        btnM.setOnClickListener(this);
        btnTz.setOnClickListener(this);
        btnAddTravel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) { //change the main activity to all the button
        Intent i = new Intent(getApplicationContext(), WaterPageActivity.class);
        if (btnD.getId() == v.getId()) {
            i.putExtra("typePlace", "travelsouth");
            startActivity(i);
        }
        if (btnM.getId() == v.getId()) {
            i.putExtra("typePlace", "travelcentral");
            startActivity(i);
        }
        if (btnTz.getId() == v.getId()) {
            i.putExtra("typePlace", "travelnorth");
            startActivity(i);
        }
        if (btnAddTravel.getId() == v.getId()) {
            Intent intent = new Intent(MainActivity.this, AddTravelActivity.class);
            startActivity(intent);
        }
    }

}
