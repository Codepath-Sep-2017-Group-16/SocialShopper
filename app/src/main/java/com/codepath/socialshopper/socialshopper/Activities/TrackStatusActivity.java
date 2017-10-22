package com.codepath.socialshopper.socialshopper.Activities;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.codepath.socialshopper.socialshopper.Manifest;
import com.codepath.socialshopper.socialshopper.R;
import com.codepath.socialshopper.socialshopper.Receivers.ShopperUpdates;
import com.codepath.socialshopper.socialshopper.Utils.DatabaseUtils;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;


import static com.google.android.gms.location.LocationServices.getFusedLocationProviderClient;


public class TrackStatusActivity extends AppCompatActivity implements GoogleMap.OnMapLongClickListener, GoogleMap.OnMarkerDragListener, DatabaseUtils.OnLocationFetchListener {

    private SupportMapFragment mapFragment;
    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_status);


    }

    public void requestForShoppersLocation(View view) {

        // Send a notification to server saying that requestor would like to know shoppers location.
        // Server sends share location request, if the requestor

        mapFragment = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map));
        if (mapFragment != null) {
            mapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap map) {

                    loadMap(map);
                }
            });
        } else {
            Toast.makeText(this, "Error - Map Fragment was null!!", Toast.LENGTH_SHORT).show();
        }
    }


    protected void loadMap(GoogleMap googleMap) {
        map = googleMap;
        if (map != null) {
            // Map is ready
            Toast.makeText(this, "Map Fragment was loaded properly!", Toast.LENGTH_SHORT).show();

            DatabaseUtils.getShoppersLocation(this, getIntent().getStringExtra("list_id"));

        } else {
            Toast.makeText(this, "Error - Map was null!!", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onMapLongClick(LatLng latLng) {

    }

    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {

    }

    @Override
    public void OnLocationFetchListener(com.codepath.socialshopper.socialshopper.Models.Location location) {
        LatLng myLocation = new LatLng(location.getLatitude(), location.getLongitude());
        map.addMarker(new MarkerOptions().position(myLocation).title("Shopper"));
        map.moveCamera(CameraUpdateFactory.newLatLng(myLocation));
        map.animateCamera(CameraUpdateFactory.zoomTo(14));
    }
}
