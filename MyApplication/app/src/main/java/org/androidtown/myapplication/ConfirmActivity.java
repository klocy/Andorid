package org.androidtown.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

//3-1
public class ConfirmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true); //커스터마이징 하기 위해 필요
        actionBar.setDisplayShowTitleEnabled(false);

        Intent intent = getIntent();

        int start_h = intent.getIntExtra("start_hour",0);
        int start_m = intent.getIntExtra("start_min",0);
        int end_h = intent.getIntExtra("end_hour",0);
        int end_m = intent.getIntExtra("end_min",0);

        Button btn = (Button)findViewById(R.id.homeButton);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    public void onButton1Clicked(View v) {
        Toast.makeText(getApplicationContext(), "예약 번호를 복사했습니다.", Toast.LENGTH_LONG).show();
    }
}
