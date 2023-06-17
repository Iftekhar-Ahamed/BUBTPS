package com.example.bubtps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Home extends AppCompatActivity {
    private Button profile,checkout,status;
    Dialog checkindialog;
    Intent home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        profile = findViewById(R.id.btn_profile);
        checkout = findViewById(R.id.btn_checkout);
        status = findViewById(R.id.btn_status);
        checkindialog = new Dialog(this);

        profile.setOnClickListener(new View.OnClickListener(){
            public  void onClick(View v){
                home = new Intent(Home.this, Profile.class);
                startActivity(home);
            }
        });


        checkout.setOnClickListener(new View.OnClickListener(){
            public  void onClick(View v){
                Intent home = new Intent(Home.this, Checkout.class);
                startActivity(home);
            }
        });
        status.setOnClickListener(new View.OnClickListener(){
            public  void onClick(View v){
                Intent home = new Intent(Home.this, Status.class);
                startActivity(home);
            }
        });
    }
    public void showDialog(View v){
        TextView close;
        AppCompatButton bike,car;
        checkindialog.setContentView(R.layout.activity_bike_car);
        close=(TextView) checkindialog.findViewById(R.id.button_cancel);
        bike=checkindialog.findViewById(R.id.button_bike);
        car=checkindialog.findViewById(R.id.button_car);

        close.setOnClickListener(new View.OnClickListener(){
            public  void onClick(View vi){
                checkindialog.dismiss();
            }
        });
        bike.setOnClickListener(new View.OnClickListener(){
            public  void onClick(View vi){
                home = new Intent(Home.this, checkin.class);
                startActivity(home);
            }
        });
        car.setOnClickListener(new View.OnClickListener(){
            public  void onClick(View vi){
                home = new Intent(Home.this, checkin.class);
                startActivity(home);
            }
        });
        checkindialog.show();
    }
}