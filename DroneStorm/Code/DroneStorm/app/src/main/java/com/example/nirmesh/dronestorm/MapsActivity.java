package com.example.nirmesh.dronestorm;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    TextView tv1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        tv1 = (TextView) findViewById(R.id.tv1);

        Typeface mytypeFace = Typeface.createFromAsset(getAssets(), "Ailerons-Typeface.otf");
        tv1.setTypeface(mytypeFace);
    }

    public void zoomIn(View v) {
        if (v.getId() == R.id.BzoomIn) {
            mMap.animateCamera(CameraUpdateFactory.zoomIn());
        }

    }

    public void zoomOut(View v) {
        if (v.getId() == R.id.BzoomOut) {
            mMap.animateCamera(CameraUpdateFactory.zoomOut());
        }
    }


    public void onSearch(View v) {


        List<Address> addressList = null;
        EditText drone_tf = (EditText) findViewById(R.id.editText);
        String location = drone_tf.getText().toString();

        if (location != null || !location.equals("")) {
            Geocoder geocoder = new Geocoder(this);
            try {
                addressList = geocoder.getFromLocationName(location, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Address address = addressList.get(0);
            LatLng latlng = new LatLng(address.getLatitude(), address.getLongitude());


            mMap.clear();
            mMap.addMarker(new MarkerOptions().position(latlng).title("Marker"));

            mMap.animateCamera(CameraUpdateFactory.newLatLng(latlng));
        }
        if (location == "") {
            Toast.makeText(this,"Enter a location", Toast.LENGTH_LONG).show();
        }

    }
    public void thirdPage(View v)
    {
        Intent i = new Intent(this, Dummy.class);
        startActivity(i);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

     /*   // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);*/
        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
        /*mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/
    }
}

