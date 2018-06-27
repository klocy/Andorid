package org.androidtown.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


//3
public class MapActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
    }


    public void onButton1Clicked(View v) {
        Toast.makeText(getApplicationContext(), "위치 확인", Toast.LENGTH_LONG).show();
        Intent intent3 = new Intent(getApplicationContext(),TimeActivity.class);
    }
}
