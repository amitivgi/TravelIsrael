package com.example.proj.PagesPackage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.proj.R;

public class WaterPageActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnWater, btnWithoutWater;
    private String typePlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waterpage);

        initUI();
        initListeners();
    }

    private void initUI() {
        typePlace = getIntent().getStringExtra("typePlace");

        btnWater = (Button) findViewById(R.id.btwater);
        btnWithoutWater = (Button) findViewById(R.id.btwithoutwater);
    }

    private void initListeners() {
        btnWater.setOnClickListener(this);
        btnWithoutWater.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) { //change the main activity to all the button
        Intent intent = new Intent(WaterPageActivity.this, AllTravelActivity.class);
        if (btnWater.getId() == v.getId()) {
            intent.putExtra("water", true);
            intent.putExtra("typePlace", typePlace);
            startActivity(intent);
        } else if (btnWithoutWater.getId() == v.getId()) {
            intent.putExtra("water", false);
            intent.putExtra("typePlace", typePlace);
            startActivity(intent);
        }
    }

}
