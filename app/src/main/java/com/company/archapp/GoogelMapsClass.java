package com.company.archapp;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class GoogelMapsClass extends AppCompatActivity implements OnMapReadyCallback {

    private double latitude;
    private double longitude;

    public void map(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * This shows how to create a simple activity with a map and a marker on the map.
     */


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_result);

            SupportMapFragment mapFragment =
                    (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        }

        /**
         * This is where we can add markers or lines, add listeners or move the camera. In this case,
         * we
         * just add a marker near Africa.
         */
        @Override
        public void onMapReady(GoogleMap map) {
            map.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker")); }
    }

