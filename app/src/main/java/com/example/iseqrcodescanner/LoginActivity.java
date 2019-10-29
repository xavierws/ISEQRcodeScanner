package com.example.iseqrcodescanner;

import android.Manifest;
import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;

import cob.cob3_1_2.api.adapter.JsonUtil;
import cob.cob3_1_2.api.app.LoginHelper;
import cob.cob3_1_2.api.app.ParamGenerator;
import cob.cob3_1_2.api.pref._Alias;
import cob.cob3_1_2.api.util.FileTool;

public class LoginActivity extends AppCompatActivity {
    Button btn;
    EditText username, password;
    String server_url;
    LoginHelper loginHelper;
/*
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 0:
                checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE, 1);
                break;
            case 1:
                checkPermission(Manifest.permission.CAMERA, 2);
                break;
        }

    }
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn = findViewById(R.id.button_login);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);

        /*if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){

        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }*/
//        checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, 0);

        String permission[]= new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA
        };
        ActivityCompat.requestPermissions(this, permission, 1);


        if(ParamGenerator.Companion.cekTokenLokal()){
            String peran = ParamGenerator.Companion.ambilPeranLokal();
            pindahHalaman(peran);
        }else {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String usernameStr = username.getText().toString();
                    String urlLogin = ParamGenerator.Companion.masukUrl(usernameStr, password.getText().toString()); //tambah get text ambil dari edittext
                    reqServer(urlLogin, usernameStr);
                }
            });
        }
    }

    public void reqServer(String server_url, final String username){

        //Toast.makeText(LoginActivity.this, server_url, Toast.LENGTH_LONG).show();
        Log.e("TES_A", "URL= " +server_url);
        final RequestQueue requestQueue= Volley.newRequestQueue(LoginActivity.this);
        JsonObjectRequest request= new JsonObjectRequest(Request.Method.POST, server_url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(LoginActivity.this, response.toString(), Toast.LENGTH_LONG).show();
                        try{
                            if(!response.equals(_Alias.Companion.respon("gak"))){
                                String peran= response.getString(_Alias.Companion.getLain()[7]);
                                String id= response.getString(_Alias.Companion.getLain()[8]);
                                String token = response.getString(_Alias.Companion.getLain()[0]);

//                                FileTool.Companion.simpanln(_Alias.Companion.getLoginDir(), token, false);
//                                FileTool.Companion.simpanln(_Alias.Companion.getLoginDir(), peran, true);
                                LoginHelper.Companion.simpanTokenPeran(token, id, username, peran);
                                /*Intent i = new Intent(LoginActivity.this, MainActivity.class);
                                i.putExtra("userLevel", peran);
                                Toast.makeText(LoginActivity.this, peran, Toast.LENGTH_LONG).show();
                                startActivity(i);
                                finish();*/

                                pindahHalaman(peran);
                            }
                        }catch (Exception e){
                            Toast.makeText(LoginActivity.this, "error", Toast.LENGTH_LONG).show();
                        }
                        //Toast.makeText(LoginActivity.this, response, Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this, "sorry couldn't connect to server e=" + error.getMessage(), Toast.LENGTH_LONG).show();
                error.printStackTrace();
                requestQueue.stop();
            }
        });
        requestQueue.add(request);
    }

    public void pindahHalaman(String peran){
        Intent i = new Intent(LoginActivity.this, MainActivity.class);
        i.putExtra("userLevel", peran);
        Toast.makeText(LoginActivity.this, "You're logged in as " +peran, Toast.LENGTH_LONG).show();
        startActivity(i);
        finish();
    }

    public void checkPermission(String permission, int reqCode){
        if(ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED){

        } else {
            ActivityCompat.requestPermissions(this, new String[]{permission}, reqCode);
        }
    }
}
