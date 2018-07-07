package org.androidtown.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;

//3-1
public class TimeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);
    }

    public void onButton1Clicked(View v) {
        //Toast.makeText(getApplicationContext(), "시간 입력", Toast.LENGTH_LONG).show();

        TimePicker tp1 = (TimePicker) findViewById(R.id.timepicker);
        TimePicker tp2 = (TimePicker) findViewById(R.id.timepicker2);

        int start_h, start_m;
        int end_h, end_m;

        start_h = tp1.getHour();
        start_m = tp1.getMinute();
        end_h = tp2.getHour();
        end_m = tp2.getMinute();

        Intent intent = new Intent(this, ConfirmActivity.class);

        intent.putExtra("start_hour",start_h);
        intent.putExtra("start_min",start_m);
        intent.putExtra("end_hour",end_h);
        intent.putExtra("end_min",end_m);

        startActivity(intent);
    }
}
