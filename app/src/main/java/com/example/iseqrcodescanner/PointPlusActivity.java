package com.example.iseqrcodescanner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class PointPlusActivity extends AppCompatActivity {
    TextView tx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point_plus);

        tx = findViewById(R.id.id_barcode);

        tx.setText(getIntent().getStringExtra("idBarcode"));
    }
}
