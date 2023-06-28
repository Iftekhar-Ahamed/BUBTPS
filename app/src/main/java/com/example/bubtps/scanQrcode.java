package com.example.bubtps;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class scanQrcode extends AppCompatActivity {
    private static final int CAMERA_PERMISSION_REQUEST_CODE = 1001;
    private Boolean done = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qrcode);
        Button scanButton = findViewById(R.id.scan_button);
        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(scanQrcode.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    startQRCodeScan();
                } else {
                    ActivityCompat.requestPermissions(scanQrcode.this, new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_REQUEST_CODE);
                }
            }
        });
    }

    private void startQRCodeScan() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setPrompt("Scan a QR code");
        integrator.setOrientationLocked(true);
        integrator.setCaptureActivity(CaptureActivityPortrait.class);
        integrator.initiateScan();
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null && result.getContents() != null) {
            String qrCodeData = result.getContents();
            //Toast.makeText(this, "QR Code: " + qrCodeData, Toast.LENGTH_SHORT).show();
            String[] t =qrCodeData.toString().split("\\$");
            if(t[0].equals("1")){
                pushData(t[1]);
            }else {
                popdata(t[1]);
            }

        }
    }

    public void pushData(String s){

        String[] data = s.split(" ");
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalTime t = LocalTime.now();
            s+=" "+t.toString();
            DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference().child("Parking Area").child(data[2]).child("Capacity");
            Loading loading = new Loading(scanQrcode.this);
            loading.startLoading();
            String finalS = s;
            databaseRef.runTransaction(new Transaction.Handler() {
                Integer ok=0;
                @NonNull
                @Override
                public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                    // Retrieve the current value from the database
                    Integer currentValue = mutableData.getValue(Integer.class);

                    if (currentValue == null) {
                        ok=-1;
                        mutableData.setValue(0);
                    }else if(currentValue==0){
                        ok=0;
                        mutableData.setValue(0);
                    }
                    else {
                        ok=1;
                        // Increment the value by 1
                        mutableData.setValue(currentValue - 1);
                    }

                    // Indicate that the transaction is successful

                    return Transaction.success(mutableData);
                }

                @Override
                public void onComplete(DatabaseError databaseError, boolean committed, DataSnapshot dataSnapshot) {
                    loading.endLoading();
                    if (databaseError != null) {
                        // Handle the error
                        Toast.makeText(scanQrcode.this,"Database failed",Toast.LENGTH_SHORT).show();
                    } else if(ok==1) {

                        insertData(finalS,data[0]);

                    }else {
                        Toast.makeText(scanQrcode.this,"No Slot available.",Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
        
    }
    public void insertData(String data,String id){
        LocalDate d;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            d = LocalDate.now();
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Data").child(d.toString()).child(id);
            data = data.replace(":","-").replace(".","-");

            String finalData = data;
            ref.child("Status").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (!task.isSuccessful()) {
                        Toast.makeText(scanQrcode.this,"Faild to reach DataBase",Toast.LENGTH_SHORT).show();
                    }else if(task.getResult().getValue()==null || task.getResult().getValue().toString().equals("Nill")){
                        ref.child("Status").setValue(finalData);
                        ref.child(finalData).setValue("ok");
                        Toast.makeText(scanQrcode.this,"Successfully Done",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(scanQrcode.this,"Already Parked",Toast.LENGTH_SHORT).show();
                    }
                }
            });


        }
    }
    public  void popdata(String data){
        LocalDate d;
        String[] t= data.split(" ");
        String id = t[0];

        LocalTime time;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O && android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            d = LocalDate.now();
            time=LocalTime.now();
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Data").child(d.toString()).child(id);
            data = data.replace(":","-").replace(".","-");
            String finalData = data;
            ref.child("Status").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (!task.isSuccessful()) {
                        Toast.makeText(scanQrcode.this,"Faild to reach DataBase",Toast.LENGTH_SHORT).show();
                    }else if(task.getResult().getValue()==null || task.getResult().getValue().toString().equals("Nill")){
                        Toast.makeText(scanQrcode.this,"Already CheckOut",Toast.LENGTH_SHORT).show();
                    }else {
                        ref.child("Status").setValue("Nill");
                        ref.child(finalData).setValue(time.toString());
                        Toast.makeText(scanQrcode.this,"Successfully CheckOut",Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startQRCodeScan();
            } else {
                Toast.makeText(this, "Camera permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}