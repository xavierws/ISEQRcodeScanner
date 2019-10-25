package com.example.iseqrcodescanner;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn = findViewById(R.id.button_login);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);

        //server_url="https://playground.icon.ise-its.com";

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*Intent i = new Intent(LoginActivity.this, MainActivity.class);
                i.putExtra("userLevel", "GAME");

                i.putExtra("userLevel", response);
                requestQueue.stop();
                startActivity(i);
                finish();*/

                String urlLogin= ParamGenerator.Companion.masukUrl(username.getText().toString(), password.getText().toString()); //tambah get text ambil dari edittext
                reqServer(urlLogin);
            }
        });


    }

    public void reqServer(String server_url){
        final RequestQueue requestQueue= Volley.newRequestQueue(LoginActivity.this);
        JsonObjectRequest request= new JsonObjectRequest(Request.Method.POST, server_url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {/*
                        loginHelper= new LoginHelper(new LoginHelper.LoginHelperListener() {
                            @Override
                            public boolean onLogin(@Nullable String tokenDariFile) {
                                return false;
                            }
                        });*/
                        Toast.makeText(LoginActivity.this, response.toString(), Toast.LENGTH_LONG).show();
                        try{
                            if(!response.equals(_Alias.Companion.respon("gak"))){
                                String peran= response.getString(_Alias.Companion.getLain()[7]);
                                String token = response.getString(_Alias.Companion.getLain()[0]);

//                                FileTool.Companion.simpanln(_Alias.Companion.getLoginDir(), token, false);
//                                FileTool.Companion.simpanln(_Alias.Companion.getLoginDir(), peran, true);
                                LoginHelper.Companion.simpanTokenPeran(token, peran);
                                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                                i.putExtra("userLevel", peran);

//                                Toast.makeText(LoginActivity.this, peran, Toast.LENGTH_LONG).show();
                                requestQueue.stop();
                                //startActivity(i);
                                //finish();
                            }
                        }catch (Exception e){

                        }
                        //Toast.makeText(LoginActivity.this, response, Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this, "sorry couldn't connect to server e= " +error.getMessage(), Toast.LENGTH_LONG).show();
                error.printStackTrace();
                requestQueue.stop();
            }
        });
        requestQueue.add(request);
    }
}
