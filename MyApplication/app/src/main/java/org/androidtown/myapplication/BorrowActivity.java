package org.androidtown.myapplication;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

//4
public class BorrowActivity extends AppCompatActivity {

    String bookingCode ;
    String inputCode;

    EditText input ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrow);

        final Context context = this;
        input= findViewById(R.id.inputCode);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true); //커스터마이징 하기 위해 필요
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼, 디폴트로 true만 해도 백버튼이 생김
        //actionBar.setHomeAsUpIndicator(R.drawable.ic_back); //뒤로가기 버튼을 본인이 만든 아이콘으로 하기 위해 필요

        ImageButton btn_borrow = (ImageButton) findViewById(R.id.borrow);
        ImageButton btn_paste = (ImageButton) findViewById(R.id.paste);

        //서버에서 가져오는걸로 변경하기
        SharedPreferences pref = getSharedPreferences("Temp", Activity.MODE_PRIVATE);
        bookingCode = pref.getString("code","");

        btn_borrow.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                inputCode=input.getText().toString();

                if(inputCode.equals(bookingCode)){
                Intent intent = new Intent(getApplicationContext(), Borrow2Activity.class);
                startActivity(intent);
              }else{
                  Toast.makeText(getApplicationContext(), "잘못된 예약코드입니다.", Toast.LENGTH_LONG).show();
              }
            }
        });

        btn_paste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);

                ClipData clip = clipboardManager.getPrimaryClip();
                ClipData.Item item = clip.getItemAt(0);//첫번째 데이터를 가져옴
                TextView pastetext = (TextView)findViewById(R.id.inputCode);

                pastetext.setText(item.getText());//텍스트뷰에 세팅해줌
            }
        });
    }


}
