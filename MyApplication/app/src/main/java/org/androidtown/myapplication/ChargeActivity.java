package org.androidtown.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class ChargeActivity extends AppCompatActivity {

    private TextView balance;
    private EditText input;
    private  String TAG = ChargeActivity.class.getSimpleName();
    private double userBalance, chargeEth=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charge);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true); //커스터마이징 하기 위해 필요
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼, 디폴트로 true만 해도 백버튼이 생김
        //actionBar.setHomeAsUpIndicator(R.drawable.ic_back); //뒤로가기 버튼을 본인이 만든 아이콘으로 하기 위해 필요
        //------------------------툴바

        balance=findViewById( R.id.walletBalance2);
        input = findViewById(R.id.charge);

        ImageButton btn1 = (ImageButton) findViewById(R.id.button_finish);

        userBalance =135.24; //서버에서 가져오기

        balance.setText(String.valueOf(userBalance));

        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                if(input.getText().equals(null)){ //입력값이 null이 아니라면
                chargeEth = Double.valueOf(String.valueOf(input.getText()));
                }

                Log.e(TAG, "setOnClickListener in ChargeActivity, chargeEth = "+Double.valueOf(chargeEth) +"eth");

                //서버에 충전요청

                //수정해야할듯?
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
