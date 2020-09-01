package com.example.meenutarun.cse227_2020;
/* To get key which we added in manifest. these are the following steps to follow:
1. https://console.cloud.google.com
2. Goto MyProject-->NewProject--> Give the name to project(As i given BAsicMApP1)-->Create
3. home--> Goto API and Services--> Libraries --> Search Map SDK for Android--> Click on Enable
4. home-->Goto API and Services --> Credentials -->Create Credentials-->API key--> key created and
now copy this and add in manifest

*/

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;
//this activity to show marker and camera on our map corresponding current location longitude and
// latitude
// this activity to show marker and camera on our map corresponding changed location longitude and
// latitude(add the key for this)
// above both mentioned work is in initMap method

// this activity to show marker and camera on our map corresponding searched location name
// above mentioned work is in doSearch method

/* we are using fragment in xml which we are using to display Google Map and want to get its object.
then we will inflate this fragement in our java
 */
public class P1MarkerSearchMap extends AppCompatActivity {
    SupportMapFragment supportMapFragment;
    FusedLocationProviderClient myclient;
    Location lastLocation;
    double dlatitude, dlongitude;
    boolean flag = false;
    GoogleMap gmap;
    EditText etsearch;
//    PlaceAutocompleteFragment autocompleteFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p1_marker_search_map);
        //etsearch = findViewById(R.id.etsearch);
        //inflate this fragement in our java
        supportMapFragment = (SupportMapFragment) getSupportFragmentManager().
                findFragmentById(R.id.map);
        myclient = LocationServices.getFusedLocationProviderClient(this);
        checkLocationPermission();
    }
        //same as we done in P1MainActivity
        private void checkLocationPermission() {
            if (ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED)
            {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
            else {
            flag = true;
            Toast.makeText(this, "Location Permission Granted", Toast.LENGTH_SHORT).show();
            myclient.getLastLocation().addOnSuccessListener
                    (new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if (location != null) {
                                lastLocation = location;
                                dlatitude = lastLocation.getLatitude();
                                dlongitude = lastLocation.getLongitude();
                                initMap();
                            }
                        }
                    });
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        if(requestCode ==1)
        {
            if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED)
            {
                checkLocationPermission();
            }
            else
            {
                Toast.makeText(this,"Location Permission Denied",Toast.LENGTH_SHORT).show();
            }
        }
    }
    void initMap() {
        Toast.makeText(this, "Initialize Map", Toast.LENGTH_SHORT).show();
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @SuppressLint("MissingPermission")
            @Override
            public void onMapReady(GoogleMap googleMap) {
                gmap = googleMap;
                if (flag) {
                    LatLng mylatlng = new LatLng(dlatitude, dlongitude);
                    //To show camera on our map on current place which we get from current Latitude
                    // and longitude
                    gmap.moveCamera(CameraUpdateFactory.newLatLng(mylatlng));

             //in my case to workon this:SetMyLocationEnabled(true): i need to add to write
                    // following if statement.
                    if (ActivityCompat.checkSelfPermission(P1MarkerSearchMap.this,
                            Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                            && ActivityCompat.checkSelfPermission
                            (P1MarkerSearchMap.this, Manifest.permission.ACCESS_COARSE_LOCATION) !=
                            PackageManager.PERMISSION_GRANTED)
                    {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    gmap.setMyLocationEnabled(true);;

                //To show marker on our map on current place which we get from current Latitude and longitude
                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(mylatlng);
                    markerOptions.title("You are here");

                    gmap.addMarker(markerOptions);

                    //same as above mentioned but here as we are changing the location of our app
                    //then  camera and marker also change its position on our map
                    gmap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
                        @Override
                        public void onMyLocationChange(Location location)
                        {
                            gmap.clear();
                            LatLng mylatlng = new LatLng(location.getLatitude(),location.getLongitude());
                            gmap.moveCamera(CameraUpdateFactory.newLatLng(mylatlng));

                            MarkerOptions markerOptions = new MarkerOptions();
                            markerOptions.position(mylatlng);
                            markerOptions.title("Updated Location");
                            gmap.addMarker(markerOptions);
                        }
                    });

                }

            }
        });
    }


// home work for students
    //when i enter some city like delhi in edit text,this method(doSearch)
// it will search the llongitude and latitude corresponding to that city
    // and display marker on that position
    public void doSearch(View view) {


    }
}

