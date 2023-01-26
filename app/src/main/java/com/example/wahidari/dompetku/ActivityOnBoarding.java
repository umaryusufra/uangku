package com.example.wahidari.dompetku;

import static com.example.wahidari.dompetku.R.id.selanjutnya1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActivityOnBoarding extends AppCompatActivity {


    Button btn_mulai;
    TextView selanjutnya1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boardingg);

        btn_mulai=findViewById(R.id.btn_mulai);
        selanjutnya1=findViewById(R.id.selanjutnya1);
        btn_mulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ActivityLogin.class);
                startActivity(i);
            }
        });
        selanjutnya1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ActivityOnBoarding.this, OnBoarding2.class);

                startActivity(i);
            }

        });




    }




    }
