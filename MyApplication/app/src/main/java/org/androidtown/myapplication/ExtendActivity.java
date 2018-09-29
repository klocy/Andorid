package org.androidtown.myapplication;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class ExtendActivity extends AppCompatActivity {


    public  static final int Return_Time = 1;
    private String TAG = ExtendActivity.class.getSimpleName();

    Calendar cal= Calendar.getInstance();
    int hour = cal.get(Calendar.HOUR_OF_DAY);//현재 시
    int minute = cal.get(Calendar.MINUTE);//현재 분

    private  boolean test=true;


    int time[] = {hour,minute,hour,minute}; //시작시, 시작분, 종료시, 종료분


    ImageButton btn;
    RadioGroup rg;

    int extend_h=0, extend_m=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extend);

        //---------------------툴바--------------------------
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true); //커스터마이징 하기 위해 필요
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼, 디폴트로 true만 해도 백버튼이 생김
        //actionBar.setHomeAsUpIndicator(R.drawable.ic_back); //뒤로가기 버튼을 본인이 만든 아이콘으로 하기 위해 필요
        //---------------------툴바--------------------------

        rg = (RadioGroup) findViewById(R.id.rdgroup);
        btn = (ImageButton) findViewById(R.id.extend);


        SharedPreferences pref = getSharedPreferences("Temp", Activity.MODE_PRIVATE);
        time[0]= pref.getInt("hour",0);
        time[1]=pref.getInt("hour",0);
        time[2] = pref.getInt("hour",0);
        time[3] = pref.getInt("minute",0);

        Log.e(TAG,"onCreate in ExtendActivity (time[2],time[3]) = ("+time[2]+","+time[3]+")");

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.half :
                        extend_h = 0;
                        extend_m = 30;
                        break;
                    case R.id.one :
                        extend_h = 1;
                        extend_m = 0;
                        break;
                    case R.id.one_half :
                        extend_h = 1;
                        extend_m = 30;
                        break;
                    case R.id.two :
                        extend_h = 2;
                        extend_m = 0;
                        break;
                }

                Log.e(TAG,"setOnCheckedChangeListener in ExtendActivity (extend_h,extend_m) = ("+extend_h+","+extend_m+")");

            }
        });




        btn.setOnClickListener(new View.OnClickListener(){ //자전거 반납
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), Borrow2Activity.class);


                Log.e(TAG,"setOnClickListener in ExtendActivity (extend_h,extend_m) = ("+extend_h+","+extend_m+")");

                if(errorCheck(extend_h,extend_m))  {


                    time[2]+=extend_h;
                    time[3]+=extend_m;


                    if(time[3]>=60) {
                        time[3]-=60;
                        time[2]+=1;

                    }

                    if(time[2]>=24){
                        time[2]-=24;

                        //날짜 증가하는걸로 수정 *수정
                    }


                    Log.e(TAG,"setOnClickListener in ExtendActivity (time[2],time[3]) = ("+time[2]+","+time[3]+")");

                    //intent에 날짜도 추가해야할듯 *수정
                    intent.putExtra("extend_time",time);

                    //서버에 반납시간 연장 요청 *API
                    SharedPreferences reserveTIme = getSharedPreferences("reserveTime", MODE_PRIVATE);
                    SharedPreferences.Editor edi = reserveTIme.edit();
                    edi.putInt("hour",time[2]);
                    edi.putInt("minute",time[3]);
                    edi.commit();


                    setResult(RESULT_OK,intent);
                    finish();

                }else{
                    setResult(RESULT_CANCELED,intent);
                  //  finish();
                }
            }
        });

    }

    public  boolean errorCheck(int hour,int minute){
        double eth = 100000; //eth 잔고, db에서 가져오기 *API
        double price = 0.01; // 단위 : eth / 5분  *API
        double totalP = 0; //총요금
        boolean check = false;
        final double lackEth ;

        totalP = ((hour*60+minute)/5)*price;



        Log.e(TAG,"errorCheck in ExtendActivity test = " + test);

        if(test){
            eth=0;
        }
        test = !test;

        lackEth = totalP-eth;

        Log.e(TAG,"errorCheck in ExtendActivity uesr's balance (eth) = " + eth);
        Log.e(TAG,"errorCheck in ExtendActivity price for extending time (totalP) = " + totalP);

        if(totalP>eth){

            Log.e(TAG,"errorCheck in ExtendActivity lackEth = " + lackEth);

            //잔액부족팝업, 충전화면 전환여부
            AlertDialog.Builder bid = new AlertDialog.Builder(this);
            bid.setTitle("잔액 부족");
            bid.setMessage("사용자 계정에 이더가 부족합니다.\n마이닝 하시겠습니까?");
            bid.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {


                    //충전화면으로 전환하는 인텐트
                    Intent intent = new Intent(getApplicationContext(),ChargeActivity.class);
                    intent.putExtra("lack eth",lackEth);
                    startActivityForResult(intent, Return_Time);
                }
            });
            bid.setPositiveButton("No",null);
            bid.show();

        }else check =true;


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
