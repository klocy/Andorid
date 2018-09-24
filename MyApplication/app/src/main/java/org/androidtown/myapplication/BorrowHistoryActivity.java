package org.androidtown.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;

public class BorrowHistoryActivity extends AppCompatActivity {
    private TextView[] history;
    private View[] divider;
    private boolean t[];
    private String borrow[];
    private String TAG = BorrowHistoryActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrowhistory);

        //----------------툴바------------------
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true); //커스터마이징 하기 위해 필요
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼, 디폴트로 true만 해도 백버튼이 생김
        //actionBar.setHomeAsUpIndicator(R.drawable.ic_back); //뒤로가기 버튼을 본인이 만든 아이콘으로 하기 위해 필요
        //--------------------------------------

        history = new TextView[10];
        t = new boolean[10];
        borrow = new String[10];
        divider = new View[10];

        ImageButton btn1 = (ImageButton) findViewById(R.id.okButton);

        int id_history = R.id.history;
        int id_divider = R.id.his_div;
        //Log.e(TAG,"onCreate in BorrowHistoryActivity, x = "+ Integer.valueOf(x));
        //나중에서버에서 가져오는걸로변경
        borrow[0]="2018년 8월 7일 17:30 ~ 2018년 8월 7일 18:30\n대여소 : 용산역";
        borrow[1]="2018년 7월 5일 18:00 ~ 2018년 7월 5일 23:30\n대여소 : 송도 센트럴파크";
        t[0]=true;
        t[1]=true;

        for(int i = 2;i<10;i++){ //나중에 i초기값 0으로변경하기
            borrow[i] = " ";//서버에서 기록가져오기
            t[i]=false; //예약내역이 있으면 true
        }

        for(int i =0;i<10;i++){
            history[i]=findViewById(id_history);
            divider[i]=findViewById(id_divider);
            id_divider++;
            id_history++;
            history[i].setText(borrow[i]);
            if(!t[i]) divider[i].setVisibility(View.INVISIBLE);
        }





        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onBackPressed();
            }
        });
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
