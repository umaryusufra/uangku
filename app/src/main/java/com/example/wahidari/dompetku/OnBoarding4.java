package com.example.wahidari.dompetku;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class OnBoarding4 extends AppCompatActivity {
    Button btn_mulai4;

    ImageView back4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding4);

        btn_mulai4=findViewById(R.id.btn_mulai4);

        back4=findViewById(R.id.back4);
        btn_mulai4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ActivityLogin.class);
                startActivity(i);
            }
        });

        back4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(OnBoarding4.this, OnBoarding3.class);
                startActivity(i);
            }
        });



    }




}