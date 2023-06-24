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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle data = getIntent().getExtras();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        profile = findViewById(R.id.btn_profile);
        checkout = findViewById(R.id.btn_checkout);
        status = findViewById(R.id.btn_status);
        checkindialog = new Dialog(this);

        profile.setOnClickListener(new View.OnClickListener(){
            public  void onClick(View v){
                Intent profile= new Intent(Home.this, Profile.class);
                profile.putExtras(data);
                startActivity(profile);
            }
        });


        checkout.setOnClickListener(new View.OnClickListener(){
            public  void onClick(View v){
                Intent checkout = new Intent(Home.this, Checkout.class);
                startActivity(checkout);
            }
        });
        status.setOnClickListener(new View.OnClickListener(){
            public  void onClick(View v){
                Intent status = new Intent(Home.this, Status.class);
                startActivity(status);
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
                Intent availableSlot = new Intent(Home.this, available_slots.class);
                startActivity(availableSlot);
            }
        });
        car.setOnClickListener(new View.OnClickListener(){
            public  void onClick(View vi){
                Intent car = new Intent(Home.this, available_slots.class);
                startActivity(car);
            }
        });
        checkindialog.show();
    }
}