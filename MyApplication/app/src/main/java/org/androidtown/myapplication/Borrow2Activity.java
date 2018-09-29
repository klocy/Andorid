package org.androidtown.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Borrow2Activity extends AppCompatActivity {

    public  static final int Return_Time = 1;
    final Context context = this;
    int end_hour;
    int end_minute;
    int time[]={0,0,0,0};
    TextView endT,endD;
    String h , m, year,month,date;
    ImageView lock;
    boolean lock_flag = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrow2);

        //----------툴바------------
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true); //커스터마이징 하기 위해 필요
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼, 디폴트로 true만 해도 백버튼이 생김
        //actionBar.setHomeAsUpIndicator(R.drawable.ic_back); //뒤로가기 버튼을 본인이 만든 아이콘으로 하기 위해 필요
        //------------------------

        ImageButton btn4 =(ImageButton) findViewById(R.id.btn_return);//reutrn botton
        ImageButton btn5 = (ImageButton) findViewById(R.id.btn_pause); //pause botton
        ImageButton btn6 = (ImageButton) findViewById(R.id.btn_extend); //extend botton

        lock = findViewById(R.id.lock);
        lock.setVisibility(View.INVISIBLE);

        //서버에서 예약시간 가져오기 *API
        SharedPreferences pref = getSharedPreferences("Temp", Activity.MODE_PRIVATE);
        end_hour = pref.getInt("hour", 0);
        end_minute = pref.getInt("minute", 0);

        //서버에서 종료날짜 가져오기 *API
        year="2018";
        month="8";
        date="13";

        h = String.valueOf(end_hour);
        m = String.valueOf(end_minute);
        if (end_hour < 10) h = 0 + h;
        if (end_minute < 10) m = 0 + m;

        endT = findViewById(R.id.end);
        endT.setText(year+"년 "+month+"월 "+date+"일\n"+h + " : " + m);




        btn4.setOnClickListener(new View.OnClickListener() { //자전거 반납
            @Override
            public void onClick(View v) { //반납

                AlertDialog.Builder toMain = new AlertDialog.Builder(context);
                toMain.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        //서버에 자전거 반납요청 *API

                        LayoutInflater inflater = getLayoutInflater();
                        View layout = inflater.inflate(R.layout.toast, (ViewGroup)findViewById(R.id.toast_custom));
                        TextView tv_title=(TextView)layout.findViewById(R.id.textView1);
                        tv_title.setText("사용해주셔서 감사합니다.");
                        Toast toast = new Toast(getApplicationContext());
                        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                        toast.setDuration(Toast.LENGTH_LONG);
                        toast.setView(layout);
                        toast.show();

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();

                    }
                });

                toMain.setPositiveButton("No",null);
                toMain.setTitle("자전거 반납");
                toMain.setMessage("자전거를 반납하시겠습니까?");
                toMain.show();

            }
        });

        btn5.setOnClickListener(new View.OnClickListener() { //일시정지
            @Override
            public void onClick(View v) { //일시정지


                if(lock_flag){

                    AlertDialog.Builder toMain = new AlertDialog.Builder(context);
                    toMain.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //서버에 자전거 일시정지 요청 *API

                            //일시정지에 관한 코드 넣어야함 *수정
                            lock.setVisibility(View.INVISIBLE);
                            lock_flag=false;

                        }
                    });
                    toMain.setPositiveButton("No",null);
                    toMain.setTitle("자전거 잠금");
                    toMain.setMessage("자전거를 잠금을 해제합니다.\n계속하시겠습니까?");
                    toMain.show();

                }else{

                AlertDialog.Builder toMain = new AlertDialog.Builder(context);
                toMain.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //서버에 자전거 잠금요청 *API

                        lock.setVisibility(View.VISIBLE);
                        lock_flag=true;

                    }
                });
                toMain.setPositiveButton("No",null);
                toMain.setTitle("자전거 잠금");
                toMain.setMessage("자전거를 잠금을 활성화합니다.\n계속하시겠습니까?");
                toMain.show();
                }
            }
        });


        btn6.setOnClickListener(new View.OnClickListener() { //연장
            @Override
            public void onClick(View v) { //연장
                Intent intent = new Intent(getApplicationContext(), ExtendActivity.class);
                startActivityForResult(intent, Return_Time);

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode==Return_Time){
            if(resultCode==RESULT_OK){
                int etime[] = data.getIntArrayExtra("extend_time");
                end_hour = etime[2];
                end_minute = etime[3];
                h = String.valueOf(end_hour);
                m = String.valueOf(end_minute);

             if (end_hour < 10) h = 0 + h;
             if (end_minute < 10) m = 0 + m;

            endT.setText(year+"년 "+month+"월 "+date+"일\n"+h + " : " + m);

        }}
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
