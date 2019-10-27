package com.example.iseqrcodescanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import cob.cob3_1_2.api.app.LoginHelper;
import cob.cob3_1_2.api.app.ParamGenerator;
import cob.cob3_1_2.api.pref._Alias;

public class CekPointStartupActivity extends AppCompatActivity {
    TextView tx;
    TextView tx2;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cek_point_startup);

        tx=findViewById(R.id.tx_point_value_startup);
        tx2= findViewById(R.id.uname);
        progressBar= findViewById(R.id.progress_bar);

        String ID= ParamGenerator.Companion.ambilIdLokal();
        String url = ParamGenerator.Companion.cekUrl(ID);
        String username = ParamGenerator.Companion.ambilUnameLokal();

        requestServer(url, username);

    }

    public void requestServer(String url, final String username){
        RequestQueue requestQueue = Volley.newRequestQueue(CekPointStartupActivity.this);
        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        tx.setVisibility(View.VISIBLE);
                        tx.setText(response);
                        progressBar.setVisibility(View.GONE);

                        tx2.setText(username);

                        //Toast.makeText(LoginActivity.this, response, Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CekPointStartupActivity.this, "sorry couldn't connect to server", Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        });
        requestQueue.add(request);
    }
}
