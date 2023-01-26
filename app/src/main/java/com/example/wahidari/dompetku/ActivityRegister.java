package com.example.wahidari.dompetku;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ActivityRegister extends AppCompatActivity {

    EditText Etnama, Etemail, Etalamat, Etpassword;
    String nama, email, alamat, password;
    Button btn_registrasi;
    TextView tv_login, login_text_1;
    ProgressDialog progressDialog;
    LinearLayout ly_1, ly_2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ly_1=findViewById(R.id.ly_1);
        ly_2=findViewById(R.id.ly_2);

        btn_registrasi = findViewById(R.id.btn_regis);
        Etnama = findViewById(R.id.edt_name);
        Etemail = findViewById(R.id.edt_email);
        Etalamat = findViewById(R.id.edt_alamat);
        Etpassword = findViewById(R.id.edt_pass);
        tv_login=findViewById(R.id.tv_registrasi);
        progressDialog = new ProgressDialog(this);

        ly_1.setTranslationX(800);



        ly_1.animate().translationX(0).alpha(1).setDuration(500).setStartDelay(100).start();




        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ActivityRegister.this, ActivityLogin.class);
                startActivity(i);
            }
        });
        btn_registrasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.setMessage("Loading ...");
                progressDialog.setCancelable(false);
                progressDialog.show();

                nama = Etnama.getText().toString();
                email = Etemail.getText().toString();
                alamat = Etalamat.getText().toString();
                password = Etpassword.getText().toString();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        validasiData();
                    }
                }, 1000);

            }
        });
    }
    void validasiData(){
        if(nama.equals("") || email.equals("")|| alamat.equals("") || password.equals("")){
            progressDialog.dismiss();
            Toast.makeText(ActivityRegister.this,"Periksa kembali data Anda !", Toast.LENGTH_SHORT).show();
        }else  {
            kirimData();
        }
    }
    void kirimData(){

        AndroidNetworking.post("https://tekajeapunya.com/kelompok_5/api_uangku/register_user.php")
                .addBodyParameter("nama",""+nama)
                .addBodyParameter("email",""+email)
                .addBodyParameter("alamat",""+alamat)
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
                            Toast.makeText(ActivityRegister.this, ""+pesan, Toast.LENGTH_SHORT).show();
                            Log.d("status",""+status);
                            if(status){
                                new AlertDialog.Builder(ActivityRegister.this)
                                        .setMessage("Berhasil Menambahkan Data !")
                                        .setCancelable(false)
                                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Intent i = new Intent(ActivityRegister.this, ActivityLogin.class);
                                                startActivity(i);
                                            }
                                        })
                                        .show();
                            }
                            else{
                                new AlertDialog.Builder(ActivityRegister.this)
                                        .setMessage("Gagal Menambahkan Data !")
                                        .setPositiveButton("Kembali", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Intent i = new Intent(ActivityRegister.this, ActivityRegister.class);
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

