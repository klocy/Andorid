package org.androidtown.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
    }

    public void onButton1Clicked(View v) {
        Toast.makeText(getApplicationContext(), "위치 확인", Toast.LENGTH_LONG).show();
    }
}
