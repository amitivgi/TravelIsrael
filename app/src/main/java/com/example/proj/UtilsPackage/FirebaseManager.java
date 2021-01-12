package com.example.proj.UtilsPackage;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class FirebaseManager {

    private FirebaseFirestore firestore;

    public void initFirestore() {
        firestore = FirebaseFirestore.getInstance();
    }

    public Task<DocumentReference> addDocumentFirestore(String location, String description, String equipmentList,
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

        return firestore.collection(location)
                .add(travel);
    }

    public Task<QuerySnapshot> getDataFirestore(String typePlace, boolean water) {
        return firestore.collection(typePlace)
                .whereEqualTo("water", water)
                .get();
    }

}
