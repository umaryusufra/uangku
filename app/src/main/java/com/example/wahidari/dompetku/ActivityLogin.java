package com.example.wahidari.dompetku;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONObject;

public class ActivityLogin extends AppCompatActivity {


    EditText Etemail, Etpass;
    String email, password;
    Button btn_masuk;
    TextView tv_registrasi, login_text_1, blm_akun;
    ProgressDialog progressDialog;
    ImageView img2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //edit text
        Etemail = findViewById(R.id.email);
        Etpass = findViewById(R.id.pass);

        //textview
        tv_registrasi=findViewById(R.id.tv_logout);
        login_text_1=findViewById(R.id.login_text_1);
        blm_akun=findViewById(R.id.blm_akun);

        //imageview
        img2=findViewById(R.id.img_2);

        //button
        btn_masuk=findViewById(R.id.btn_regis);


        //edittext
        Etemail.setTranslationX(900);
        Etpass.setTranslationX(900);
        img2.setTranslationX(900);

         login_text_1.setTranslationX(900);







        //delay
        Etemail.animate().translationX(0).alpha(1).setDuration(500).setStartDelay(100).start();
        Etpass.animate().translationX(0).alpha(1).setDuration(500).setStartDelay(100).start();
        img2.animate().translationX(0).alpha(1).setDuration(500).setStartDelay(100).start();

        login_text_1.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(100).start();


        progressDialog = new ProgressDialog(this);

        tv_registrasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ActivityRegister.class);
                startActivity(i);

            }
        });
        btn_masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.setMessage("Loading ...");
                progressDialog.setCancelable(false);
                progressDialog.show( );

                email = Etemail.getText().toString();
                password = Etpass.getText().toString();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        validasiData();
                    }
                }, 1000);

            }
        });

    }

    void validasiData() {
        if(email.equals("") || password.equals("")){
            progressDialog.dismiss();
            Toast.makeText(ActivityLogin.this,"Periksa kembali data Anda !", Toast.LENGTH_SHORT).show();
        }else  {
            kirimData();
        }
    }
    void kirimData(){



        AndroidNetworking.post("https://tekajeapunya.com/kelompok_5/api_uangku/login_user.php")
                .addBodyParameter("email",""+email)
                .addBodyParameter("password",""+password)
                .setPriority(Priority.MEDIUM)
                .setTag("Tambah Data")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.dismiss();
                        Log.d("cekTambah",""+response);
                        try {
                            Boolean status = response.getBoolean("status");
                            String pesan   = response.getString("result");
                            Toast.makeText(ActivityLogin.this, ""+pesan, Toast.LENGTH_SHORT).show();

                            Log.d("status",""+status);
                            if(status){

                                new AlertDialog.Builder(ActivityLogin.this)
                                        .setMessage("Welcome To UANGKU !")
                                        .setCancelable(false)
                                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Intent i = new Intent(ActivityLogin.this, MainActivity.class);
                                                startActivity(i);
                                            }
                                        })
                                        .show();
                            }
                            else{
                                new AlertDialog.Builder(ActivityLogin.this)
                                        .setMessage("Periksa Kembali Email dan Password !")
                                        .setPositiveButton("Kembali", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Intent i = new Intent(ActivityLogin.this, ActivityLogin.class);
                                                startActivity(i);
                                            }
                                        })
                                        .setCancelable(false)
                                        .show();
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(com.androidnetworking.error.ANError anError) {
                        Log.d("ErrorTambahData",""+anError.getErrorBody());
                    }

                });
    }
}