package com.example.meenutarun.cse227_2020;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
//this activity find current location Latitude and Longitude and the Physical address corresponding longitude and latitude

public class P1MainActivity extends AppCompatActivity {

    FusedLocationProviderClient myclient;
    TextView tv, tv1,tv2;
    double dlatitude,dlongitude;
    Location lastLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p1_map_main);
        tv = (TextView) findViewById(R.id.tv);
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);

        myclient = LocationServices.getFusedLocationProviderClient(this);
    }


    public void dothis(View view)
    {
        checkLOcationPermission();
    }

    private void checkLOcationPermission()
    {
        // request for permission if not granted and get the result on onRequestPermissionsResult overridden method
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
        }
        else
        {
            Toast.makeText(this,"Location Permission Granted",Toast.LENGTH_SHORT).show();

  //The getLastLocation() method doesn't actually make a location request.
  // It simply returns the location most recently obtained by the FusedLocationProviderClient class.
            myclient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if(location != null)
                    {
                        lastLocation = location;
                        dlatitude  = lastLocation.getLatitude();
                        dlongitude = lastLocation.getLongitude();

                        tv.setText(""+dlatitude+"\n"+dlongitude);

 // Geocoder class is used for Geocoding as well as Reverse Geocoding.
 // Geocoding refers to transforming street address or any address into latitude and longitude.
 // and in our app we are using ReverseGeoCoding as From latitude and longitude we are finding Physical address

                        Geocoder geocoder = new Geocoder(P1MainActivity.this, Locale.getDefault());

                        try {

                            List<Address> locationList = geocoder.getFromLocation(dlatitude,dlongitude,1);
                            if(locationList.size() >0)
                            {
                                Address address =  locationList.get(0);

                                tv2.setText(""+address);
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode ==1)
        {
            if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED)
            {
                checkLOcationPermission();
            }
            else
            {
                Toast.makeText(this,"Location Permission Denied",Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void openMap(View view)
    {

        startActivity(new Intent(this,P1MarkerSearchMap.class));
    }
}

