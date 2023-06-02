package com.example.bubtps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.graphics.Bitmap;
import android.graphics.Color;
import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
public class Checkout extends AppCompatActivity {
    private ImageView pic ;
    private Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);



        String s = "19202103239";
        pic = findViewById(R.id.imageViewcheckout);
        QRGEncoder qrgEncoder = new QRGEncoder(s, null, QRGContents.Type.TEXT, 1000);
        pic.setImageBitmap(qrgEncoder.getBitmap());

        qrgEncoder.setColorBlack(Color.WHITE);
        qrgEncoder.setColorWhite(Color.BLACK);
        bitmap = qrgEncoder.getBitmap();
        pic.setImageBitmap(bitmap);
    }
}