package com.example.proj.PagesPackage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.proj.ModelsPackage.TravelModel;
import com.example.proj.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private MapView mMapView;
    private GoogleMap mGoogleMap;
    private TravelModel travelModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        initUI();
    }

    private void initUI() {
        travelModel = (TravelModel) getIntent().getExtras().getSerializable(getString(R.string.dataFirestore));

        mMapView = findViewById(R.id.mapApp);

        if (mMapView != null) {
            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        try {
            MapsInitializer.initialize(this);
            mGoogleMap = googleMap;
            mGoogleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
            mGoogleMap.getUiSettings().setZoomControlsEnabled(true);
            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(travelModel.getLat(), travelModel.getLng()), 8));
            mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(travelModel.getLat(), travelModel.getLng())).title(travelModel.getLocationName()));
        } catch (Exception e) {

        }
    }

}