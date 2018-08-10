package org.androidtown.myapplication;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class ExtendActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extend);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true); //커스터마이징 하기 위해 필요
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼, 디폴트로 true만 해도 백버튼이 생김
        //actionBar.setHomeAsUpIndicator(R.drawable.ic_back); //뒤로가기 버튼을 본인이 만든 아이콘으로 하기 위해 필요


        ImageButton btn = (ImageButton) findViewById(R.id.extend);

        ImageButton hourUp = (ImageButton) findViewById(R.id.hourUp_e);
        ImageButton hourDown = (ImageButton) findViewById(R.id.hourDown_e);
        ImageButton minuteUp = (ImageButton) findViewById(R.id.minuteUp_e);
        ImageButton minuteDown = (ImageButton) findViewById(R.id.minuteDown_e);

        final TextView eHour = (TextView) findViewById(R.id.extend_hour);
        final TextView eMinute = (TextView) findViewById(R.id.extend_minute);

        hourUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTime(eHour,1);
            }
        });

        hourDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTime(eHour,-1);
            }
        });

        minuteUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTime(eMinute,5);
            }
        });

        minuteDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTime(eMinute,-5);
            }
        });

        btn.setOnClickListener(new View.OnClickListener(){ //자전거 반납
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), Borrow2Activity.class);
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
