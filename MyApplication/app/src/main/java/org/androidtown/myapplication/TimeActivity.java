package org.androidtown.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.w3c.dom.Text;

import static java.lang.StrictMath.abs;

//3-1
public class TimeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);

        //시작시간 버튼
        ImageButton shour_up = (ImageButton) findViewById(R.id.hourUp);
        ImageButton shour_down = (ImageButton) findViewById(R.id.hourDown);
        ImageButton sminute_up = (ImageButton) findViewById(R.id.minuteUp);
        ImageButton sminute_down = (ImageButton) findViewById(R.id.minuteDown);

        //종료시간 버튼
        ImageButton ehour_up = (ImageButton) findViewById(R.id.hourUp2);
        ImageButton ehour_down = (ImageButton) findViewById(R.id.hourDown2);
        ImageButton eminute_up = (ImageButton) findViewById(R.id.minuteUp2);
        ImageButton eminute_down = (ImageButton) findViewById(R.id.minuteDown2);

        ImageButton btn_ok = (ImageButton) findViewById(R.id.time_ok);

        //시작시간
        final TextView shour = (TextView) findViewById(R.id.start_hour);
        final TextView sminute = (TextView) findViewById(R.id.start_minute);

        //종료시간
        final TextView ehour = (TextView) findViewById(R.id.end_hour);
        final TextView eminute = (TextView) findViewById(R.id.end_minute);

        shour_up.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                setTime(shour,1);
            }
        });

        shour_down.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                setTime(shour,-1);
            }
        });


        sminute_up.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                setTime(sminute,5);
            }
        });


        sminute_down.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                setTime(sminute,-5);
            }
        });

        ehour_up.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                setTime(ehour,1);
            }
        });


        ehour_down.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                setTime(ehour,-1);
            }
        });


        eminute_up.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                setTime(eminute,5);
            }
        });

        eminute_down.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                setTime(eminute,-5);
            }
        });

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), ConfirmActivity.class);
                startActivity(intent);
            }
        });
    }



    protected  void setTime(TextView text, int num){
        int time = Integer.valueOf((String)text.getText());
        int x=60;

        if(num==1||num==-1) x= 24;

        time = (time+num) % x;

        if(time<0) time+=x;

        text.setText(String.valueOf(time));

    }

    /*public void onButton1Clicked(View v) {
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
    }*/
}
