package org.androidtown.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


//3
public class MapActivity extends AppCompatActivity implements OnMapReadyCallback{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap map){
        LatLng sydney = new LatLng(37.555744, 126.970431);
        map.addMarker(new MarkerOptions().position(sydney).title("Sydney"));
        map.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);
        map.animateCamera(zoom);

        //marker표시
        MarkerOptions marker = new MarkerOptions();
        marker.position(new LatLng(37.555744, 126.970431))
                .title("서울역")
                .snippet("Seoul Station");
        map.addMarker(marker).showInfoWindow();
    }

        /*
        mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback(){
            @Override
            public void onMapReady(GoogleMap googleMap){
                Log.d(TAG, "GoogleMap is ready");

                map = googleMap;
            }
        });
        try{
            MapsInitializer.initialize(this);
        }
        catch(Exception e){
            e.printStackTrace();
        }

        Button btn = (Button) findViewById(R.id.mapButton);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                requestMyLocation();
            }
        })
        */

    public void onButton1Clicked(View v) {
        Intent intent = new Intent(getApplicationContext(), TimeActivity.class);
        startActivity(intent);
    }
}
