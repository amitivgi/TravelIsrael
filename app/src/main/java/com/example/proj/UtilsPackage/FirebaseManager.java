package com.example.proj.UtilsPackage;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.proj.AdaptersPackage.AllTravelAdapter;
import com.example.proj.ModelsPackage.TravelModel;
import com.example.proj.PagesPackage.MainActivity;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FirebaseManager {

    private FirebaseFirestore firestore;
    private AllTravelAdapter allTravelAdapter;

    public void initFirestore() {
        firestore = FirebaseFirestore.getInstance();
    }

    public void addDocumentFirestore(Activity activity, String location, String description, String equipmentList,
                                     String imageUrl, double lat, double lng, String locationName,
                                     boolean water, String weatherUrl) {
        Map<String, Object> travel = new HashMap<>();
        travel.put("description", description);
        travel.put("equipmentList", equipmentList);
        travel.put("imageUrl", imageUrl);
        travel.put("lat", lat);
        travel.put("lng", lng);
        travel.put("locationName", locationName);
        travel.put("water", water);
        travel.put("weatherUrl", weatherUrl);

        firestore.collection(location)
                .add(travel)
                .addOnSuccessListener(aVoid -> {
                    Intent intentAddInternetToMain = new Intent(activity, MainActivity.class);
                    activity.startActivity(intentAddInternetToMain);

                    activity.finish();
                })
                .addOnFailureListener(e -> Toast.makeText(activity, "Error: " + e, Toast.LENGTH_SHORT).show());
    }

    public void getDataFirestore(Activity activity, ArrayList<TravelModel> travels, RecyclerView rv,
                                 String typePlace, boolean water) {
        firestore.collection(typePlace)
                .whereEqualTo("water", water)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        travels.clear();

                        for (DocumentSnapshot doc : task.getResult()) {
                            TravelModel travelModel = doc.toObject(TravelModel.class);
                            assert travelModel != null;
                            travelModel.setId(doc.getId());
                            travels.add(travelModel);
                        }

                        allTravelAdapter = new AllTravelAdapter(travels, activity);
                        rv.setAdapter(allTravelAdapter);
                    } else {
                        Toast.makeText(activity, "שגיאה בקבלת המידע", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
