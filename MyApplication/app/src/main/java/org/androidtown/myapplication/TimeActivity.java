package org.androidtown.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class TimeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);
    }

    public void onButton1Clicked(View v) {
        Toast.makeText(getApplicationContext(), "시간 입력", Toast.LENGTH_LONG).show();
    }
}
