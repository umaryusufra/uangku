package com.example.wahidari.dompetku;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class OnBoarding2 extends AppCompatActivity {
    Button btn_mulai2;
    TextView selanjutnya2;
    ImageView back2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding2);

        btn_mulai2=findViewById(R.id.btn_mulai2);
        selanjutnya2=findViewById(R.id.selanjutnya2);
        back2=findViewById(R.id.back2);
        btn_mulai2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ActivityLogin.class);
                startActivity(i);
            }
        });
        selanjutnya2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(OnBoarding2.this, OnBoarding3.class);
                startActivity(i);
            }
        });
        back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(OnBoarding2.this, ActivityOnBoarding.class);
                startActivity(i);
            }
        });



    }




}