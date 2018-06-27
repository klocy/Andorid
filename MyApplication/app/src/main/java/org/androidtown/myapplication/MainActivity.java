package org.androidtown.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn1 =(Button) findViewById(R.id.button1);

        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent  intent = new Intent(getApplicationContext(), WalletActivity.class);
                startActivity(intent);
            }
        });
    }


    public void onButton2Clicked(View v) {
        // 자전거 예약 액티비티
    }

    public void onButton3Clicked(View v) {
        // 자전거 대여 액티비티
    }

}
