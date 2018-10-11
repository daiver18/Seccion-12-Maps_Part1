package com.example.daiverandresdoria.seccion_12_maps.Fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.daiverandresdoria.seccion_12_maps.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapFragment extends Fragment implements OnMapReadyCallback,GoogleMap.OnMarkerDragListener,View.OnClickListener{

    private View rootView;
    private GoogleMap gMap;
    private MapView mapView;

    private Geocoder geocoder;
    private List<Address> addresses;

    private MarkerOptions marker;

    private FloatingActionButton fab;

    public MapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_map, container, false);
        fab = rootView.findViewById(R.id.fab);
        fab.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mapView = (MapView) rootView.findViewById(R.id.mapView);
        mapView.onCreate(null);
        mapView.onResume();
        mapView.getMapAsync(this);

    }

    @Override
    public void onResume() {
        super.onResume();
        this.checkedGPS();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;
        LatLng MyHome = new LatLng(8.765199899999999, -75.8628734);

        CameraUpdate camera = CameraUpdateFactory.zoomTo(15);

        marker = new MarkerOptions();
        marker.position(MyHome);
        marker.title("My Home");
        marker.snippet("caja de texto");
        marker.draggable(true);

        gMap.addMarker(marker);
        gMap.moveCamera(CameraUpdateFactory.newLatLng(MyHome));
        gMap.animateCamera(camera);

        gMap.setOnMarkerDragListener(this);

        geocoder = new Geocoder(getContext(), Locale.getDefault());
    }

    private void checkedGPS(){
        try {
            int gpsSettings = Settings.Secure.getInt(getActivity().getContentResolver(),Settings.Secure.LOCATION_MODE);
            if (gpsSettings == 0){
                showAlertDialog();
            }
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void showAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Alert").setMessage("you don't have GPS enable, please go setting and enable");
            builder.setPositiveButton("Go Settings", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(intent);
                }
            });
            builder.setNeutralButton("Cancel",null);
            builder.create().show();
    }

    @Override
    public void onMarkerDragStart(Marker marker) {
        marker.hideInfoWindow();
    }

    @Override
    public void onMarkerDrag(Marker marker) {
    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        double latitude = marker.getPosition().latitude;
        double longitude = marker.getPosition().longitude;

        try {
            addresses = geocoder.getFromLocation(latitude,longitude,1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String address = addresses.get(0).getAddressLine(0);
        String City = addresses.get(0).getLocality();
        String state = addresses.get(0).getAdminArea();
        String Country = addresses.get(0).getCountryName();
        String PostalCode = addresses.get(0).getPostalCode();

        marker.setSnippet(address);
        marker.showInfoWindow();
        
    }

    @Override
    public void onClick(View v) {
        this.checkedGPS();
    }
}
