package com.example.proj.PagesPackage;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proj.ModelsPackage.TravelModel;
import com.example.proj.R;
import com.example.proj.UtilsPackage.FirebaseManager;

import java.util.ArrayList;

public class AllTravelActivity extends AppCompatActivity {

    private RecyclerView rv;
    private final ArrayList<TravelModel> travels = new ArrayList<>();
    private String typePlace;
    private boolean water;
    private FirebaseManager firebaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_travel);

        initUI();
        retrieveData();
    }

    private void initUI() {
        rv = findViewById(R.id.rv);

        rv.setLayoutManager(new LinearLayoutManager(this));

        typePlace = getIntent().getStringExtra("typePlace");
        water = getIntent().getBooleanExtra("water", true);

        firebaseManager = new FirebaseManager();
        firebaseManager.initFirestore();
    }

    private void retrieveData() {
        firebaseManager.getDataFirestore(this, travels, rv, typePlace, water);
    }

}
