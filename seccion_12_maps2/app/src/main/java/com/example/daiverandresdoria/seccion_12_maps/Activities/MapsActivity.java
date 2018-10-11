package com.example.daiverandresdoria.seccion_12_maps.Activities;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.example.daiverandresdoria.seccion_12_maps.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMinZoomPreference(10);
        mMap.setMaxZoomPreference(15);

        // Add a marker in Sydney and move the camera
        LatLng MyHome = new LatLng(8.765199899999999, -75.8628734);
        mMap.addMarker(new MarkerOptions().position(MyHome).title("My Home"));
        CameraPosition camera = new CameraPosition.Builder()
                .zoom(10)       //limite de 20 en zoom
                .target(MyHome)
                .bearing(90)    //rotacion de la camara
                .tilt(30)       // inclinacion de la camara, limite de 90
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(camera));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(MyHome));
    }
}
