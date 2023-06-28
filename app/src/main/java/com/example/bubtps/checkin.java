package com.example.bubtps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.widget.Toast;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class checkin extends AppCompatActivity {
    private ImageView pic;
    private Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle data = getIntent().getExtras();
        String s = "1$"+data.getString("code");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkin);

        pic = findViewById(R.id.imageViewCheckin);
        QRGEncoder qrgEncoder = new QRGEncoder(s, null, QRGContents.Type.TEXT, 1500);
        //pic.setImageBitmap(qrgEncoder.getBitmap());

        qrgEncoder.setColorBlack(Color.WHITE);
        qrgEncoder.setColorWhite(Color.BLACK);
        bitmap = qrgEncoder.getBitmap();
        pic.setImageBitmap(bitmap);

        Toast.makeText(checkin.this,s,Toast.LENGTH_SHORT).show();

    }
}