package com.example.gpstest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements LocationListener {
    private TextView longtitude;
    private TextView latitude;
    private LocationManager locationManager;
    private long save_time=0;
    private Location saveLocation;
    private boolean is_start=false;
    private Button checkbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        longtitude = (TextView) findViewById(R.id.longitude);
        latitude = (TextView) findViewById(R.id.latitude);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.INTERNET},
                    0);
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, this);


        Button startbutton =(Button) findViewById(R.id.startbutton);
        startbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveLocation=(Location) locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            }
        });
        checkbutton=(Button) findViewById(R.id.checkbutton);
        checkbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Distance moved :" + saveLocation.distanceTo(locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)), Toast.LENGTH_SHORT).show();;
            }
        });

        Button map=(Button) findViewById(R.id.checkmap);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Map.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
         long LongTitude=(long) location.getLongitude();
        long LaTitude=(long) location.getLatitude();

        this.longtitude.setText(String.valueOf(LongTitude));
        this.latitude.setText(String.valueOf(LaTitude));

    }

    @Override
    public void onPause(){
        super.onPause();
        is_start=false;
        locationManager.removeUpdates(this);
    }
}