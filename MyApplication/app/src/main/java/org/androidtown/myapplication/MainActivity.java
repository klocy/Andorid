package org.androidtown.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ImageButton btn1 = (ImageButton) findViewById(R.id.create);
        ImageButton btn2 = (ImageButton) findViewById(R.id.book);
        ImageButton btn3 = (ImageButton) findViewById(R.id.rental);

        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent  intent = new Intent(getApplicationContext(), WalletActivity.class);
                startActivity(intent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent  intent = new Intent(getApplicationContext(), TimeActivity.class);
                startActivity(intent);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent  intent = new Intent(getApplicationContext(), BorrowActivity.class);
                startActivity(intent);
            }
        });



    }


}
