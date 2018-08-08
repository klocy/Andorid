package org.androidtown.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
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

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true); //커스터마이징 하기 위해 필요
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼, 디폴트로 true만 해도 백버튼이 생김
        //actionBar.setHomeAsUpIndicator(R.drawable.ic_back); //뒤로가기 버튼을 본인이 만든 아이콘으로 하기 위해 필요

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



    public  void setTime(TextView text, int num){
        int time = Integer.valueOf((String)text.getText());
        int x=60;

        if(num==1||num==-1) x= 24;

        time = (time+num) % x;

        if(time<0) time+=x;

        text.setText(String.valueOf(time));

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
