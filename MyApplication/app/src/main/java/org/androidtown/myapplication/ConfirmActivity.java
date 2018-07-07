package org.androidtown.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

//3-1
public class ConfirmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        Intent intent = getIntent();

        int start_h = intent.getIntExtra("start_hour",0);
        int start_m = intent.getIntExtra("start_min",0);
        int end_h = intent.getIntExtra("end_hour",0);
        int end_m = intent.getIntExtra("end_min",0);
    }

    public void onButton1Clicked(View v) {
        Toast.makeText(getApplicationContext(), "예약 번호를 복사했습니다.", Toast.LENGTH_LONG).show();
    }
}
