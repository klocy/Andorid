package org.androidtown.myapplication;

import android.Manifest;
import android.app.Fragment;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.location.SettingInjectorService;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

//3-1

public class ConfirmActivity extends AppCompatActivity implements OnMapReadyCallback {

    TextView confrimeT, date, bn;
    ImageButton change, cancel, ok;
    Calendar cal = Calendar.getInstance();

    final Context context = this;

    protected String bookingCode;
    private MapView mapView = null;

    LocationManager locationManager;
    double latitude;   //현재 자신의 위도
    double longitude;  //현재 자신의 경도

    private static final int MY_PERMISSION_REQUEST_LOCATION = 1;

    //get city name
    public String hereLocation(double lat, double lon){
        String curCity = "";
        Geocoder geocoder = new Geocoder(ConfirmActivity.this, Locale.getDefault());
        //GeocoderRequest geocoderRequest = new GeocoderRequestBuilder().


        try{
            List<Address> addressList = geocoder.getFromLocation(lat, lon,10);
            // if(addressList.size() > 0){
            curCity = addressList.get(0).getAddressLine(0);
            //}
        }catch(Exception e){
            e.printStackTrace();
        }
        // Toast.makeText(ConfirmActivity.this,"주소:"+curCity,Toast.LENGTH_SHORT).show();
        return curCity;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        //----------툴바------------

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true); //커스터마이징 하기 위해 필요
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼, 디폴트로 true만 해도 백버튼이 생김
        //actionBar.setHomeAsUpIndicator(R.drawable.ic_back); //뒤로가기 버튼을 본인이 만든 아이콘으로 하기 위해 필요
        //------------------------

        Intent intent = getIntent();
        int time[] = intent.getIntArrayExtra("time"); //서버에서 시간가져오는것으로변경
        int start_h = intent.getIntExtra("start_hour", 0);
        int start_m = intent.getIntExtra("start_min", 0);
        int end_h = intent.getIntExtra("end_hour", 0);
        int end_m = intent.getIntExtra("end_min", 0);


        //TextView
        date = findViewById(R.id.date);
        confrimeT = findViewById(R.id.confrimeTime);
        bn = findViewById(R.id.bookingNum);

        //Button
        change = findViewById(R.id.change_booking);
        cancel = findViewById(R.id.cancel_booking);
        //ok = findViewById(R.id.ok_booking);

        //지도

        bookingCode = Create_BookingCode(time); //예약번호생성
        bn.setText(bookingCode);

        confrimeT.setText(time[0] + " : " + time[1] + " ~ " + time[2] + " : " + time[3]); //수정하기 00
        date.setText(cal.get(Calendar.YEAR) + "년 " + cal.get(Calendar.MONTH) + "월 " + cal.get(Calendar.DATE) + "일");

        /*
        ok.setOnClickListener(new View.OnClickListener() { //자전거 반납

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        */

        change.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                AlertDialog.Builder toMap = new AlertDialog.Builder(context);
                toMap.setTitle("예약 변경");
                toMap.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //예약내역삭제요청
                        Intent main = new Intent(getApplicationContext(),TimeActivity.class); //MapActivity로변경하기
                        startActivity(main);
                        finish();
                    }
                });
                toMap.setNegativeButton("No",null);
                toMap.setMessage("기존 예약내역이 삭제됩니다.\n예약을 변경하시겠습니까?");
                toMap.show();

            }
        });

        cancel.setOnClickListener(new View.OnClickListener(){
            public void  onClick(View v){
                AlertDialog.Builder toMain = new AlertDialog.Builder(context);
                toMain.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //예약내역 삭제요청
                        Intent main = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(main);
                        finish();
                    }
                });
                toMain.setNegativeButton("No",null);
                toMain.setTitle("예약 삭제");
                toMain.setMessage("예약이 삭제됩니다.\n삭제하시겠습니까?");
                toMain.show();


            }
        });

        //GPS가 켜져있는지 체크
        /*
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            //GPS설정화면으로 이동
            Intent intent1 = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            intent1.addCategory(Intent.CATEGORY_DEFAULT);
            startActivity(intent1);
            finish();
        }
*/
        //권한 요청하기
        if (Build.VERSION.SDK_INT >= 23) {
            //권한이 없는 경우
            if (ContextCompat.checkSelfPermission(ConfirmActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(ConfirmActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(ConfirmActivity.this,
                        Manifest.permission.ACCESS_COARSE_LOCATION)) {
                    ActivityCompat.requestPermissions(ConfirmActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSION_REQUEST_LOCATION);

                } else {
                    ActivityCompat.requestPermissions(ConfirmActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSION_REQUEST_LOCATION);

                }
                // ActivityCompat.requestPermissions(ConfirmActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
            //권한이 있는 경우
            else {

                try {
                    locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
                    Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    Thread.sleep(3000);
                    if(location!= null){
                        latitude = location.getLatitude();
                        longitude = location.getLongitude();
                        String temp =  hereLocation(location.getLatitude(), location.getLongitude());
                        Toast.makeText(ConfirmActivity.this, Double.toString(latitude), Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(ConfirmActivity.this, "도3희", Toast.LENGTH_SHORT).show();
                    }
                    // double latitude = location.getLongitude();
                    // String temp =  hereLocation(location.getLatitude(), location.getLongitude());
                    //Toast.makeText(ConfirmActivity.this, "도희", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(ConfirmActivity.this, "---", Toast.LENGTH_SHORT).show();
                }
            }
        }


    }

    //권한 요청 후 응답 콜백
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        switch(requestCode){
            case MY_PERMISSION_REQUEST_LOCATION:{
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if(ContextCompat.checkSelfPermission(ConfirmActivity.this,
                            Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
                        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        try {
                            hereLocation(location.getLatitude(), location.getLongitude());
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(ConfirmActivity.this, "not found", Toast.LENGTH_SHORT).show();
                        }
                    }

                }else{
                    Toast.makeText(this, "Mo permission granted", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }





    //Button btn = (Button)findViewById(R.id.homeButton);
        /*btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });*/



    public void onMapReady(GoogleMap map){


        /*
        LatLng sydney = new LatLng(37.555744, 126.970431);
        map.addMarker(new MarkerOptions().position(sydney).title("Sydney"));
        map.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);
        map.animateCamera(zoom);
*/
        //marker표시
        /*마커 하나
        LatLng seoul = new LatLng(37.555744, 126.970431);
        MarkerOptions marker = new MarkerOptions();
        marker.position(seoul)
                .title("서울역")
                .snippet("Seoul Station");
        map.addMarker(marker);
        map.moveCamera(CameraUpdateFactory.newLatLng(seoul));
        map.animateCamera(CameraUpdateFactory.zoomTo(15));
        */
        //마커 여러개
        MarkerOptions markerOptions;
        String myAddress = hereLocation(latitude, longitude);
        Double[] lati = {37.5122711, 37.5451012, 37.5536067, 37.5463644,latitude};
        Double[] lon = {126.9231514, 126.969793, 126.9696195, 126.9648311,longitude};
        String[] title = {"대여소1", "대여소2", "대여소3", "대여소4","내위치"};
        String[] addr = {"1호선 대방역", "4호선 숙대입구역", "1,4,공항철도 서울역", "숙명여자대학교",myAddress};

        for(int i=0; i<title.length; i++){
            markerOptions = new MarkerOptions();
            LatLng name = new LatLng(lati[i], lon[i]);
            markerOptions.position(name);
            markerOptions.title(title[i]);
            markerOptions.snippet(addr[i]);
            map.addMarker(markerOptions);
            map.moveCamera(CameraUpdateFactory.newLatLng(name));
        }

        map.animateCamera(CameraUpdateFactory.zoomTo(16));
    }


    protected String Create_BookingCode(int num[]) {
        String bn = "klocy";

        //코드만들기
        bn = bn + num[2] + num[3];

        //서버에저장하는것으로 변경
        SharedPreferences reserveTIme = getSharedPreferences("bookingCode", MODE_PRIVATE);
        SharedPreferences.Editor edi = reserveTIme.edit();
        edi.putString("code", bn);
        edi.commit();
        return bn;
    }


    public void copy(View v) {

        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("Copy_Code", bookingCode); //클립보드에 ID라는 이름표로 id 값을 복사하여 저장
        clipboardManager.setPrimaryClip(clipData);


        AlertDialog.Builder toMain = new AlertDialog.Builder(this);

        toMain.setNegativeButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent main = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(main);
                finish();
            }
        });
        toMain.setPositiveButton("NO", null);
        toMain.setMessage("예약 번호가 복사되었습니다.\n메인화면으로 이동하시겠습니까?");
        toMain.show();

        //Toast.makeText(getApplicationContext(), "예약 번호를 복사했습니다.", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
