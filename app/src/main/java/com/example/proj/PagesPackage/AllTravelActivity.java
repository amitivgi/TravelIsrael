package com.example.proj.PagesPackage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.proj.AdaptersPackage.AllTravelAdapter;
import com.example.proj.R;
import com.example.proj.ModelsPackage.TravelModel;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Objects;

public class AllTravelActivity extends AppCompatActivity {

    private RecyclerView rv;
    private ArrayList<TravelModel> travels = new ArrayList<>();
    private AllTravelAdapter allPostAdapter;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String typePlace;
    private boolean water;

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
    }

    private void retrieveData() {
        db.collection(typePlace)
                .whereEqualTo("water", water)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        travels.clear();

                        for (DocumentSnapshot doc : Objects.requireNonNull(task.getResult())) {
                            TravelModel note = doc.toObject(TravelModel.class);
                            assert note != null;
                            note.setId(doc.getId());
                            travels.add(note);
                        }

                        allPostAdapter = new AllTravelAdapter(travels, AllTravelActivity.this);
                        allPostAdapter.setData(travels);
                        rv.setAdapter(allPostAdapter);
                    } else {
                        Toast.makeText(AllTravelActivity.this, "שגיאה בקבלת המידע", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
