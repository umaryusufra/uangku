package com.example.wahidari.dompetku;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class OnBoarding3 extends AppCompatActivity {
    Button btn_mulai3;
    TextView selanjutnya3;
    ImageView back3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding3);

        btn_mulai3=findViewById(R.id.btn_mulai3);
        selanjutnya3=findViewById(R.id.selanjutnya3);
        back3=findViewById(R.id.back3);
        btn_mulai3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ActivityLogin.class);
                startActivity(i);
            }
        });
        selanjutnya3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(OnBoarding3.this, OnBoarding4.class);
                startActivity(i);
            }
        });
        back3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(OnBoarding3.this, OnBoarding2.class);
                startActivity(i);
            }
        });



    }




}