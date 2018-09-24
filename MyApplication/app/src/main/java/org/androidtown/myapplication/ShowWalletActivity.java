package org.androidtown.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class ShowWalletActivity extends AppCompatActivity {

    private String TAG = ShowWalletActivity.class.getSimpleName();
    final Context context = this;
    TextView walletAddress, walletBalance;
    double balance;
    String address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showwallet);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true); //커스터마이징 하기 위해 필요
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼, 디폴트로 true만 해도 백버튼이 생김
        //actionBar.setHomeAsUpIndicator(R.drawable.ic_back); //뒤로가기 버튼을 본인이 만든 아이콘으로 하기 위해 필요
        //------------------------------툴바----------------

        balance = 135.24; //서버에서 잔액가져오기
        address = "A-ba-c-s11"; //서버에서 주소가져오기

        Log.e(TAG, "onCreate in ShowWalletActivity, balance = " + String.valueOf(balance));
        Log.e(TAG, "onCreate in ShowWalletActivity, address = " + address);

        walletAddress = findViewById(R.id.walletAddress);
        walletBalance = findViewById(R.id.walletBalance);


        walletAddress.setText(address);
        walletBalance.setText(String.valueOf(balance)+" eth");

        ImageButton btn1 = (ImageButton) findViewById(R.id.button_charge);
        ImageButton btn2 = (ImageButton) findViewById(R.id.button_borrowhistory);
        ImageButton btn3 = (ImageButton) findViewById(R.id.button_delete);

        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.e(TAG, "setOnClickListener in ShowWalletActivity, call ChargeActivity " );

                Intent intent = new Intent(getApplicationContext(), ChargeActivity.class);
                startActivity(intent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Log.e(TAG, "setOnClickListener in ShowWalletActivity, call BorrowHistoryActivity " );

                Intent intent = new Intent(getApplicationContext(), BorrowHistoryActivity.class);
                startActivity(intent);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Log.e(TAG, "setOnClickListener in ShowWalletActivity, ask for deleting user's account" );

                switch (v.getId()) {
                    case R.id.button_delete:
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                        // 제목셋팅
                        alertDialogBuilder.setTitle("계정 삭제");

                        // AlertDialog 셋팅
                        alertDialogBuilder
                                .setMessage("계정을 삭제하시겠습니까?")
                                .setCancelable(false)
                                .setPositiveButton("예",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(
                                                    DialogInterface dialog, int id) {

                                                Log.e(TAG, "dialog's setPositiveButton in ShowWalletActivity, delete user's account" );

                                                ShowWalletActivity.this.finish();
                                            }
                                        })
                                .setNegativeButton("아니오",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(
                                                    DialogInterface dialog, int id) { // 다이얼로그를 취소

                                                Log.e(TAG, "dialog's setNegativeButton in ShowWalletActivity, don't delete user's account" );

                                                dialog.cancel();
                                            }
                                        });

                        // 다이얼로그 생성
                        AlertDialog alertDialog = alertDialogBuilder.create();

                        // 다이얼로그 보여주기
                        alertDialog.show();
                        break;

                    default:
                        break;
                }
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
