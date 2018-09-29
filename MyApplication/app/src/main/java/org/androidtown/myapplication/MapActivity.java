package org.androidtown.myapplication;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
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

import java.util.List;
import java.util.Locale;
import java.util.Map;


//3
public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    LocationManager locationManager;
    double latitude;   //현재 자신의 위도
    double longitude;  //현재 자신의 경도
    private static final int MY_PERMISSION_REQUEST_LOCATION = 1;
    String userLocation;
    //get city name
    public String hereLocation(double lat, double lon) {
        String curCity = "";
        Geocoder geocoder = new Geocoder(MapActivity.this, Locale.getDefault());

        try {
            List<Address> addressList = geocoder.getFromLocation(lat, lon, 10);
            // if(addressList.size() > 0){
            curCity = addressList.get(0).getAddressLine(0);
            //}
        } catch (Exception e) {
            e.printStackTrace();
        }
        return curCity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        if (Build.VERSION.SDK_INT >= 23) {
            //권한이 없는 경우
            if (ContextCompat.checkSelfPermission(MapActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(MapActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(MapActivity.this,
                        Manifest.permission.ACCESS_COARSE_LOCATION)) {
                    ActivityCompat.requestPermissions(MapActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSION_REQUEST_LOCATION);

                } else {
                    ActivityCompat.requestPermissions(MapActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSION_REQUEST_LOCATION);

                }
                // ActivityCompat.requestPermissions(ConfirmActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
            //권한이 있는 경우
            else {

                try {
                    locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
                    Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    Thread.sleep(3000);

                    latitude = location.getLatitude();
                    longitude = location.getLongitude();

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(MapActivity.this, "---", Toast.LENGTH_SHORT).show();
                }
            }
        }


    }

    //권한 요청 후 응답 콜백
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSION_REQUEST_LOCATION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(MapActivity.this,
                            Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
                        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        try {
                            hereLocation(location.getLatitude(), location.getLongitude());
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(MapActivity.this, "not found", Toast.LENGTH_SHORT).show();
                        }
                    }

                } else {
                    Toast.makeText(this, "Mo permission granted", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


    @Override
    public void onMapReady(GoogleMap map) {
        MarkerOptions markerOptions;
        String myAddress = hereLocation(latitude, longitude);
        Double[] lati = {37.5122711, 37.5451012, 37.5536067, 37.5463644, latitude};
        Double[] lon = {126.9231514, 126.969793, 126.9696195, 126.9648311, longitude};
        String[] title = {"대여소1", "대여소2", "대여소3", "대여소4", "내위치"};
        String[] addr = {"1호선 대방역", "4호선 숙대입구역", "1,4,공항철도 서울역", "숙명여자대학교", myAddress};

        for (int i = 0; i < title.length; i++) {
            markerOptions = new MarkerOptions();
            LatLng name = new LatLng(lati[i], lon[i]);
            markerOptions.position(name);
            markerOptions.title(title[i]);
            markerOptions.snippet(addr[i]);
            map.addMarker(markerOptions);
            map.moveCamera(CameraUpdateFactory.newLatLng(name));
        }

        map.animateCamera(CameraUpdateFactory.zoomTo(16));

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

        userLocation ="숙대입구 대여소"; //여기다가 선택된 대여소 위치 넣기 *수정

        intent.putExtra("location",userLocation);
        startActivity(intent);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
