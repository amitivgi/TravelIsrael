package com.example.proj.PagesPackage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.proj.BroadcastReceiverPackage.BroadcastReceiverBattery;
import com.example.proj.PagesPackage.WaterPageActivity;
import com.example.proj.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvTitle;
    private Button btnD, btnM, btnTz;

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

    private void initUI() {
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        btnD = (Button) findViewById(R.id.btnD);
        btnM = (Button) findViewById(R.id.btnM);
        btnTz = (Button) findViewById(R.id.btnTz);
    }

    private void initListeners() {
        btnD.setOnClickListener(this);
        btnM.setOnClickListener(this);
        btnTz.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) { //change the main activity to all the button
        Intent i = new Intent(getApplicationContext(), WaterPageActivity.class);
        if (btnD.getId() == v.getId()) {
            i.putExtra("typePlace", "travelsouth");
            startActivity(i);
        } else if (btnM.getId() == v.getId()) {
            i.putExtra("typePlace", "travelcentral");
            startActivity(i);
        } else if (btnTz.getId() == v.getId()) {
            i.putExtra("typePlace", "travelnorth");
            startActivity(i);
        }
    }

}
