package org.androidtown.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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

import java.util.Calendar;

import static java.lang.StrictMath.abs;
import static java.lang.StrictMath.min;

//3-1
public class TimeActivity extends AppCompatActivity {

    Calendar cal= Calendar.getInstance();
    int hour = cal.get(Calendar.HOUR_OF_DAY);//현재 시
    int minute = cal.get(Calendar.MINUTE);//현재 분
    int year = cal.get(Calendar.YEAR);
    int month  = cal.get(Calendar.MONTH)+1;
    int date = cal.get(Calendar.DATE);

    String h= String.valueOf(hour),m= String.valueOf(minute);
    String userlocation;

    int time[] = {hour,minute,hour,minute}; //시작시, 시작분, 종료시, 종료분
    int day[]={year,month,date,year,month,date};

    TextView sYear, sMonth, sDay, eYear,eMonth,eDay, location;

    final int start = 1, end =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);

        //--------------툴바------------
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true); //커스터마이징 하기 위해 필요
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼, 디폴트로 true만 해도 백버튼이 생김
        //actionBar.setHomeAsUpIndicator(R.drawable.ic_back); //뒤로가기 버튼을 본인이 만든 아이콘으로 하기 위해 필요
        //------------------------------



        //시작날짜 버튼
        ImageButton syear_up = findViewById(R.id.yearUp);
        ImageButton syear_down = findViewById(R.id.yearDown);
        ImageButton smonth_up = findViewById(R.id.monthUp);
        ImageButton smonth_down = findViewById(R.id.monthDown);
        ImageButton sdate_up = findViewById(R.id.dayUp);
        ImageButton sdate_down  = findViewById(R.id.dayDown);

        //시작시간 버튼
        ImageButton shour_up = (ImageButton) findViewById(R.id.hourUp);
        ImageButton shour_down = (ImageButton) findViewById(R.id.hourDown);
        ImageButton sminute_up = (ImageButton) findViewById(R.id.minuteUp);
        ImageButton sminute_down = (ImageButton) findViewById(R.id.minuteDown);

        //종료 날짜 버튼
        ImageButton eyear_up = findViewById(R.id.yearUp2);
        ImageButton eyear_down = findViewById(R.id.yearDown2);
        ImageButton emonth_up = findViewById(R.id.monthUp2);
        ImageButton emonth_down = findViewById(R.id.monthDown2);
        ImageButton edate_up = findViewById(R.id.dayUp2);
        ImageButton edate_down  = findViewById(R.id.dayDown2);

        //종료시간 버튼
        ImageButton ehour_up = (ImageButton) findViewById(R.id.hourUp2);
        ImageButton ehour_down = (ImageButton) findViewById(R.id.hourDown2);
        ImageButton eminute_up = (ImageButton) findViewById(R.id.minuteUp2);
        ImageButton eminute_down = (ImageButton) findViewById(R.id.minuteDown2);


        ImageButton btn_ok = (ImageButton) findViewById(R.id.time_ok);


        if(hour<10) h= 0+h;
        if(minute<10) m = 0+h;

        //시작시간
        final TextView shour = (TextView) findViewById(R.id.start_hour);
        final TextView sminute = (TextView) findViewById(R.id.start_minute);

        shour.setText(h);
        sminute.setText(m);

        //종료시간
        final TextView ehour = (TextView) findViewById(R.id.end_hour);
        final TextView eminute = (TextView) findViewById(R.id.end_minute);


        ehour.setText(h);


        time[2] = setTime(ehour, 1);

        //나중에 수정하기---
        boolean flag=false;
        if(time[2]==0) flag=true;
        //------------------

        eminute.setText(m);


        shour_up.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                time[0]= setTime(shour,1);
            }
        });
        shour_down.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                time[0]= setTime(shour,-1);
            }
        });


        sminute_up.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                time[1]=setTime(sminute,5);
            }
        });
        sminute_down.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                time[1]= setTime(sminute,-5);
            }
        });

        ehour_up.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                time[2]= setTime(ehour,1);
            }
        });
        ehour_down.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
               time[2]= setTime(ehour,-1);
            }
        });

        eminute_up.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
               time[3]= setTime(eminute,5);
            }
        });
        eminute_down.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
               time[3]= setTime(eminute,-5);
            }
        });

        //-----------날짜 설정 --------------
       sYear= findViewById(R.id.start_year);
       sMonth= findViewById(R.id.start_month);
       sDay= findViewById(R.id.start_day);
       eYear= findViewById(R.id.end_year);
       eMonth= findViewById(R.id.end_month);
       eDay= findViewById(R.id.end_day);


        sYear.setText(String.valueOf(year));
        sMonth.setText(String.valueOf(month));
        sDay.setText(String.valueOf(date));
        eYear.setText(String.valueOf(year));
        eMonth.setText(String.valueOf(month));


        if(flag) date++;
        eDay.setText(String.valueOf(date));


        //시작 년 증감
        syear_up.setOnClickListener(new View.OnClickListener(){
            public  void onClick(View v){
             day[0]= setYear(sYear,1);
            }
        });
        syear_down.setOnClickListener(new View.OnClickListener(){
            public  void onClick(View v){
                day[0]= setYear(sYear,-1);
            }
        });

        //종료 년 증감
        eyear_up.setOnClickListener(new View.OnClickListener(){
            public  void onClick(View v){
                day[3]= setYear(eYear,1);
            }
        });
        eyear_down.setOnClickListener(new View.OnClickListener(){
            public  void onClick(View v){
                day[3]= setYear(eYear,-1);
            }
        });

        //시작 월 증감
        smonth_up.setOnClickListener(new View.OnClickListener(){
            public  void onClick(View v){
                day[1]= setMonth(sMonth,1);
            }
        });
        smonth_down.setOnClickListener(new View.OnClickListener(){
            public  void onClick(View v){
                day[1]= setMonth(sMonth,-1);
            }
        });

        //종료 월 증감
        emonth_up.setOnClickListener(new View.OnClickListener(){
            public  void onClick(View v){
                day[4]= setMonth(eMonth,1);
            }
        });
        emonth_down.setOnClickListener(new View.OnClickListener(){
            public  void onClick(View v){
                day[4]= setMonth(eMonth,-1);
            }
        });

        //시작 일 증감
        sdate_up.setOnClickListener(new View.OnClickListener(){
            public  void onClick(View v){
                day[2]= setDate(sDay,1,start);
            }
        });
        sdate_down.setOnClickListener(new View.OnClickListener(){
            public  void onClick(View v){
                day[2]= setDate(sDay,-1,start);
            }
        });

        //종료 일 증감
        edate_up.setOnClickListener(new View.OnClickListener(){
            public  void onClick(View v){
                day[5]= setDate(eDay,1,end);
            }
        });
        edate_down.setOnClickListener(new View.OnClickListener(){
            public  void onClick(View v){
                day[5]= setDate(eDay,-1,end);
            }
        });

        //대여소 위치설정
        location = findViewById(R.id.borrow_loaction);
        Intent mapIntent = getIntent();
        userlocation = mapIntent.getStringExtra("location");
        location.setText(userlocation);


        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            if(errorCheck(time))  {

                //서버에 시간저장하는것으로변경

                //나중에 삭제
                Intent intent = new Intent(getApplicationContext(), ConfirmActivity.class);
                intent.putExtra("time",time);
                SharedPreferences reserveTIme = getSharedPreferences("Temp", MODE_PRIVATE);
                SharedPreferences.Editor edi = reserveTIme.edit();
                edi.putInt("hour",time[2]);
                edi.putInt("minute",time[3]);
                edi.commit();
                //----------

                startActivity(intent);
                finish();
            }

            }
        });
    }



    public  int setTime(TextView text, int num){
        int time = Integer.valueOf((String)text.getText());
        int x=60;
        String t;

        if(num==1||num==-1) x= 24;

        time = (time+num) % x;

        if(time<0) time+=x;

        t = String.valueOf(time);

        if(time <10) t = "0"+t;

        text.setText(t);

        return time;
    }

    public int setMonth(TextView text, int num){
        int mon = Integer.valueOf((String)text.getText());
        mon = (mon)%12;
        mon=mon+num;
        if(mon<1) mon+=12;


        text.setText(String.valueOf(mon));

        return mon;
    }

    public int setYear(TextView text, int num){
        int yea = Integer.valueOf((String)text.getText());
        yea= (yea+num)%10000;

        text.setText(String.valueOf(yea));
        return yea;
    }

    public int setDate(TextView text, int num, int t){
        int dat = Integer.valueOf((String)text.getText());
        int x = 31;
        int mon = day[1];
        if(t!=start) mon=day[4];

        if(mon==4|mon==6|mon==9|mon==11) x--;
        if(mon==2) x=28;

        dat=dat%x;
        dat=dat+num;

        if(dat<1) dat=dat+x;

        text.setText(String.valueOf(dat));
        return dat;
    }



    public  boolean errorCheck(int num[]) {
        double eth = 100000; //eth 잔고, 서버에서 가져오기
        double price = 0.01; // 단위 : eth / 5분
        double totalP = 0; //총요금
        int stime = num[0] * 60 + num[1];
        int etime = num[2] * 60 + num[3];
        int usingTime = etime - stime;
        boolean check = false;


        totalP = (usingTime / 5.0) * price;


        if (usingTime <= 0) {
            Toast.makeText(getApplicationContext(), "잘못된 예약시간입니다.", Toast.LENGTH_LONG).show();

        } else if (totalP > eth) {
            //잔액부족팝업, 충전화면 전환여부
            AlertDialog.Builder bid = new AlertDialog.Builder(this);
            bid.setTitle("잔액 부족");
            bid.setMessage("사용자 계정에 이더가 부족합니다.\n마이닝 하시겠습니까?");
            bid.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = new Intent(getApplicationContext(),ChargeActivity.class);
                    startActivity(intent);
                }
            });
            bid.setNegativeButton("No", null);
            bid.show();

        } else check = true;


        return check;
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
