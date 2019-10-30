package com.example.iseqrcodescanner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cob.cob3_1_2.api.app.ParamGenerator;
import cob.cob3_1_2.api.pref._Alias;

public class PointMinusActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner spinner;
    List<String> itemStr = new ArrayList<>();
    List<String> itemIdStr= new ArrayList<>();
    String IDTerpilih= "";
    String IDTerpilihLalu= "";
    Button btnTukar;
    TextView txID;
    TextView txPoint;
    int skorKurangKali= 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point_minus);

        txID= findViewById(R.id.tx_id);
        txPoint= findViewById(R.id.point);
        spinner=findViewById(R.id.spinner);
        btnTukar=findViewById(R.id.btn_tukarkan);

        spinner.setOnItemSelectedListener(this);

        String url = ParamGenerator.Companion.liatUrl(_Alias.Companion.getNamaTabel()[1]);
        Log.e("TEST_BLA", "url= " +url);
        Log.e("TEST_BLA", "url= " +url);
        Log.e("TEST_BLA", "url= " +url);
        Log.e("TEST_BLA", "url= " +url);
        requestServer(url);

        final String idUser=getIntent().getStringExtra("idMinus");
        String point=getIntent().getStringExtra("value_point");
        txID.setText(idUser);
        txPoint.setText(point);

        try{
            btnTukar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url = ParamGenerator.Companion.tukarUrl(idUser, IDTerpilih);
                    reqServerTukar(url);
                }
            });
        }catch (Exception e){
            Toast.makeText(PointMinusActivity.this, "there's no data retrieve", Toast.LENGTH_LONG).show();
        }



    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        IDTerpilih= itemIdStr.get(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void requestServer(String url){
        RequestQueue requestQueue= Volley.newRequestQueue(PointMinusActivity.this);
        JsonArrayRequest request= new JsonArrayRequest(Request.Method.POST, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                       // Toast.makeText(PointMinusActivity.this, "respon bro = " +response, Toast.LENGTH_LONG).show();
                        try{
//                            res = new JSONArray(response.substring(1, response.length() - 1));
/*
                            for(int i = 0; i < res.length(); i ++){
                                JSONObject obj = res.getJSONObject(i);

                                String id = obj.getString("id");
                            }
                            Log.e("TEST_BLA", response.toString());
                            Log.e("TEST_BLA", response.toString());
                            Log.e("TEST_BLA", response.toString());
                           // JSONArray js= new JSONArray(response);
                            Log.e("TEST_BLA", response.toString());

 */
                            for(int i= 0; i< response.length(); i++){
                                itemStr.add(jadikanString(Integer.toString(i+1), response.getJSONObject(i)));
                                itemIdStr.add(response.getJSONObject(i).getString("id"));
                            }
                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(PointMinusActivity.this, android.R.layout.simple_spinner_item, itemStr);
                            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinner.setAdapter(dataAdapter);
                        }catch (Exception e){
                            Toast.makeText(PointMinusActivity.this, "error", Toast.LENGTH_LONG).show();

                            Log.e("TEST_BLA", "ERROR requ json e =" +e.getMessage());
                            Log.e("TEST_BLA", "ERROR requ json e =" +e.getMessage());
                            Log.e("TEST_BLA", "ERROR requ json e =" +e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(PointMinusActivity.this, "Sorry can't connect to server", Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(request);
    }

    public String jadikanString(String no, JSONObject data){
        try{
            return no +". " +data.getString("nama") +": " +data.getString("harga");
        }catch (Exception e){
            return null;
        }
    }

    public void reqServerTukar(String url){
        RequestQueue requestQueue= Volley.newRequestQueue(PointMinusActivity.this);
        StringRequest request= new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        // Toast.makeText(PointMinusActivity.this, "respon bro = " +response, Toast.LENGTH_LONG).show();
                        if(response.equals("SKOR_KURANG")){
                            String strRespon= "Sorry, your score is not enough";
                            if(!IDTerpilih.equals(IDTerpilihLalu))
                                skorKurangKali= 0;
                            if(skorKurangKali++ >= 3)
                                strRespon= "Jangan ngeyel ya...";

                            Toast.makeText(PointMinusActivity.this, strRespon, Toast.LENGTH_LONG).show();
                            IDTerpilihLalu= IDTerpilih;
                        } else if(!response.equals(_Alias.Companion.respon("gak"))){
                            Toast.makeText(PointMinusActivity.this, "success", Toast.LENGTH_LONG).show();
                            finish();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(PointMinusActivity.this, "Sorry can't connect to server", Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(request);
    }
}
