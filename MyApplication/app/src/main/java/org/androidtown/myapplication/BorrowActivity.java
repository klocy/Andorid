package org.androidtown.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

//4
public class BorrowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrow);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true); //커스터마이징 하기 위해 필요
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼, 디폴트로 true만 해도 백버튼이 생김
        //actionBar.setHomeAsUpIndicator(R.drawable.ic_back); //뒤로가기 버튼을 본인이 만든 아이콘으로 하기 위해 필요

        ImageButton btn = (ImageButton) findViewById(R.id.borrow);

        btn.setOnClickListener(new View.OnClickListener(){ //자전거 반납
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), Borrow2Activity.class);
                startActivity(intent);
            }
        });


    }


}
