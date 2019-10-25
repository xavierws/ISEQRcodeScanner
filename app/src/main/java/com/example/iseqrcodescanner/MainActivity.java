package com.example.iseqrcodescanner;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;


import org.json.JSONObject;

import java.io.IOException;

import cob.cob3_1_2.api.app.ParamGenerator;
import cob.cob3_1_2.api.pref._Alias;
import cob.cob3_1_2.api.util.FileTool;

//import static com.giviews.qrcodescanner.MainActivity.REQUEST_CODE;



public class MainActivity extends AppCompatActivity {
    SurfaceView cameraView;
    BarcodeDetector barcode;
    CameraSource cameraSource;
    SurfaceHolder surfaceHolder;
    BottomNavigationView navView;
    ImageView img;
    String user;
    Boolean udahDiscan=false;
    TextView tx;


    @Override
    protected void onResume() {
        super.onResume();
        udahDiscan=false;

        tx= findViewById(R.id.tx_startup);
        cameraView = (SurfaceView) findViewById(R.id.cameraView);
        surfaceHolder = cameraView.getHolder();

        barcode = new BarcodeDetector.Builder(this)
                .setBarcodeFormats(Barcode.QR_CODE)
                .build();


        if (!barcode.isOperational()) {
            Toast.makeText(getApplicationContext(), "Sorry couldn't setup the detector", Toast.LENGTH_LONG).show();
            this.finish();
        }

        cameraSource = new CameraSource.Builder(this, barcode)
                .setFacing(CameraSource.CAMERA_FACING_BACK)
                .setRequestedFps(24)
                .setAutoFocusEnabled(true)
                .setRequestedPreviewSize(1920,1080)
                .build();

        cameraView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                try {
                    cameraSource.start(cameraView.getHolder());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

            }
        });

        navView = findViewById(R.id.botNav);

        switch (getIntent().getStringExtra("userLevel")){
            case Peran.GAME:
                tx.setVisibility(View.GONE);
                navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.navigation_add:
                                scanBarcode(PointPlusActivity.class, "plusURL", barcode, cameraSource);
                                break;
                            case R.id.navigation_minus:
                                Toast.makeText(MainActivity.this, "sorry you can't access this", Toast.LENGTH_LONG).show();
                                break;
                            case R.id.navigation_check:
                                scanBarcode(PointPlusActivity.class, "cekURL", barcode, cameraSource);
                                break;
                        }
                        return true;
                    }
                });
                break;
            case Peran.PANITIA:
                tx.setVisibility(View.GONE);
                navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.navigation_add:
                                Toast.makeText(MainActivity.this, "sorry you can't access this", Toast.LENGTH_LONG).show();
                                break;
                            case R.id.navigation_minus:
                                scanBarcode(PointPlusActivity.class, "minusURL", barcode, cameraSource);
                                break;
                            case R.id.navigation_check:
                                scanBarcode(PointPlusActivity.class, "cekURL", barcode, cameraSource);
                                break;
                        }
                        return true;
                    }
                });
                break;
            case Peran.STARTUP:
                navView.setVisibility(View.GONE);
                scanBarcode(PointPlusActivity.class, "startupURL", barcode, cameraSource);
                break;
        }

        /*navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.navigation_add:
                        break;
                    case R.id.navigation_minus:
                        break;
                    case R.id.navigation_check:
                        scanBarcode(PointPlusActivity.class, "cekURL");
                        break;
                }
                return true;
            }
        });*/
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        if(getIntent().getStringExtra("userLevel").equals(Peran.STARTUP)){
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_main, menu);
            return true;
        }else {
            return false;
        }

    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.cek_point:
                Intent i = new Intent(this, PointPlusActivity.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void ambilBitmap(final Rect rect){
        cameraSource.takePicture(null, new CameraSource.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] bytes) {
                Bitmap bm = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                bm = prosesBitmap(bm, rect);
                img.setImageBitmap(bm);
            }
        });
    }

    public Bitmap prosesBitmap(Bitmap bm, Rect rect){
        DisplayMetrics dm = getResources().getDisplayMetrics();
        float density= dm.density;

//        Matrix matrix = new Matrix();
        int left = rect.left;
        int right = rect.right;
        int top = rect.top;
        int bottom = rect.bottom;

        //Bitmap newBm = Bitmap.createScaledBitmap(bm, right-left, bottom-top, true);
        Bitmap newBm = Bitmap.createBitmap(bm, left, top, right-left, bottom-top);
        newBm = Bitmap.createScaledBitmap(newBm, 300, 300, true);

        //Bitmap rotatedBitmap = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(), matrix, true);
        return newBm;
    }

    public void scanBarcode(final Class conDestination, final String jenisUrl, final BarcodeDetector barcode, final CameraSource cameraSource){
        /*cameraView = (SurfaceView) findViewById(R.id.cameraView);
        surfaceHolder = cameraView.getHolder();

        barcode = new BarcodeDetector.Builder(this)
                .setBarcodeFormats(Barcode.QR_CODE)
                .build();

        if (!barcode.isOperational()) {
            Toast.makeText(getApplicationContext(), "Sorry couldn't setup the detector", Toast.LENGTH_LONG).show();
            this.finish();
        }

        cameraSource = new CameraSource.Builder(this, barcode)
                .setFacing(CameraSource.CAMERA_FACING_BACK)
                .setRequestedFps(24)
                .setAutoFocusEnabled(true)
                .setRequestedPreviewSize(1920,1080)
                .build();

        cameraView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                try {
                    cameraSource.start(cameraView.getHolder());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            }
        });*/

       barcode.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> barcode = detections.getDetectedItems();


                if(barcode.size() == 1 && !udahDiscan){
                    udahDiscan=true;

                    //cameraSource.release();
                    //final Barcode barcode1= barcode.get(barcode.keyAt(0));

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Vibrator vibrator = (Vibrator)getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                            vibrator.vibrate(200);
//                            ambilBitmap(barcode1.getBoundingBox());

                            String valueID = barcode.valueAt(0).displayValue;
                            if (jenisUrl.equals("plusURL")) {
                                String url =ParamGenerator.Companion.scanUrl(valueID);
                                requestServer(url, valueID);
                            } else if (jenisUrl.equals("cekURL")) {
                                String url =ParamGenerator.Companion.cekUrl(valueID);
                                requestServer(url, valueID);
                            } else if (jenisUrl.equals("minusURL")){
                                Intent i = new Intent(MainActivity.this, PointMinusActivity.class);
                                i.putExtra("idMinus", valueID);
                                startActivity(i);
                            }
                        }
                    });
                }
            }
        });
    }

    public void requestServer(String url, final String valueID){
        RequestQueue requestQueue= Volley.newRequestQueue(MainActivity.this);
        StringRequest request= new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {/*
                        loginHelper= new LoginHelper(new LoginHelper.LoginHelperListener() {
                            @Override
                            public boolean onLogin(@Nullable String tokenDariFile) {
                                return false;
                            }
                        });*/
                        try{
                            if(response.equals(_Alias.Companion.respon("ya"))){
                                Intent i = new Intent(MainActivity.this, PointPlusActivity.class);
                                i.putExtra("idBarcode", valueID);
                                startActivity(i);
                            }
                        }catch (Exception e){

                        }
                        //Toast.makeText(LoginActivity.this, response, Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "sorry couldn't connect to server", Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        });
        requestQueue.add(request);
    }
}