package com.example.iseqrcodescanner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class PointPlusActivity extends AppCompatActivity {
    TextView txBerhasil, txPoint, txID;
    ImageView img;
    String jenis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point_plus);
        img = findViewById(R.id.img_berhasil);

        String idBar=getIntent().getStringExtra("idBarcode");

        txID = findViewById(R.id.id_value);
        txID.setText(idBar);

        txBerhasil = findViewById(R.id.tx_berhasil);
        txPoint = findViewById(R.id.tx_point);

        jenis = getIntent().getStringExtra("jenisCek");
        if(jenis.equals(Peran.CEK_POINT)){
            img.setVisibility(View.GONE);
            txPoint.setVisibility(View.GONE);

            txBerhasil.setText(getIntent().getStringExtra("value_point"));

        }


    }
}
