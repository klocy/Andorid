package org.androidtown.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;


//3
public class MapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);


        //-------------------툴바--------------------
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true); //커스터마이징 하기 위해 필요
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼, 디폴트로 true만 해도 백버튼이 생김
        //actionBar.setHomeAsUpIndicator(R.drawable.ic_back); //뒤로가기 버튼을 본인이 만든 아이콘으로 하기 위해 필요
        //------------------------------------------




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
