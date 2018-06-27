package org.androidtown.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class WalletActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
    }

    public void onButton1Clicked(View v) {
        Toast.makeText(getApplicationContext(), "지갑 계정을 생성 완료했습니다.", Toast.LENGTH_LONG).show();
    }
}
