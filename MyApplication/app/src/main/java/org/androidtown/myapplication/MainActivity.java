package org.androidtown.myapplication;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();

    private TextView borrow_detail;
    private String detail; //예약 상세정보
    LinearLayout lay;
    final  int layout_vis =1, layout_invis=0;
    protected  boolean account, booking, borrow ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //서버에서 확인하는것으로변경
        account=true;
        booking=false;
        borrow=true;

        Log.e(TAG, "onCreate in MainActivity, (account,booking,borrow) = ("+account+"," + booking+","+borrow+")");

        // 예약 현황 서버에서 받아와서 보여주기

        ImageButton btn1 = (ImageButton) findViewById(R.id.create);
        ImageButton btn2 = (ImageButton) findViewById(R.id.book);
        ImageButton btn3 = (ImageButton) findViewById(R.id.rental);

        lay = (LinearLayout) findViewById(R.id.layout); //예약정보를 담고있는 layout
        borrow_detail = findViewById(R.id.reservation_detail);

        if(true){ //예약을 했다면 layout을 보이게함
            setLayoutTrue(layout_vis);

            //서버에서 예약정보 받아오기
            detail= "2018년9월30일 17:30 ~ 2018년9월30일 18:30\n대여소 : 숙대입구역 대여소";

            borrow_detail.setText(detail);

        }

        btn1.setOnClickListener(new View.OnClickListener(){ //계정생성
            @Override
            public void onClick(View v){ //지갑생성

                account=!account;//핸드폰에 등록된 계정정보가 있는 경우 true, 추후변경하기

                Log.e(TAG, "setOnClickListener in MainActivity, account = " + account);

                if(account){ //지갑정보가 있는경우
                Intent  intent = new Intent(getApplicationContext(), ShowWalletActivity.class);
                startActivity(intent);
                }else { //지갑정보가 없는경우
                Intent intent = new Intent(getApplicationContext(),WalletActivity.class);
                startActivity(intent);
                }
        }});

        btn2.setOnClickListener(new View.OnClickListener(){ //예약
            @Override
            public void onClick(View v){ //자전거 예약

                booking=false; //서버에 요청해서 예약내역 확인하는것으로 변경

                Log.e(TAG, "setOnClickListener in MainActivity, booking = " + booking);

                if(booking){ //예약내역이 있는경우
                    Intent  intent = new Intent(getApplicationContext(), ConfirmActivity.class);
                    startActivity(intent);
                }else { //예약내역이 없는 경우
                    Intent intent = new Intent(getApplicationContext(),MapActivity.class);//날짜선택화면엑티비티로 변경하기
                    startActivity(intent);
                }
            }
        });

        btn3.setOnClickListener(new View.OnClickListener(){ //대여

            @Override
            public void onClick(View v){ //자전거 대여

               borrow = !borrow; //추후에 서버에서 대여 확인하는것으로 변경

                Log.e(TAG, "setOnClickListener in MainActivity, borrow = " + borrow);

                if(borrow) { //대여중인 경우
                    Intent intent = new Intent(getApplicationContext(), Borrow2Activity.class);
                    startActivity(intent);
                }else { //대여하지 않는경우
                    Intent intent = new Intent(getApplicationContext(), BorrowActivity.class);
                    startActivity(intent);
                }
            }
        });



        }

        public void setLayoutTrue(int flag){

        if(flag==1) lay.setVisibility(View.VISIBLE);
        else lay.setVisibility(View.INVISIBLE);

    }

}
