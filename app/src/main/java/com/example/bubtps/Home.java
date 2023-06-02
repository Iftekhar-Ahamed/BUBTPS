package com.example.bubtps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity {
    private Button profile,checkin,checkout,status;
    Intent home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        profile = findViewById(R.id.btn_profile);
        checkin = findViewById(R.id.btn_checkin);
        checkout = findViewById(R.id.btn_checkout);
        status = findViewById(R.id.btn_status);

        profile.setOnClickListener(new View.OnClickListener(){
            public  void onClick(View v){
                home = new Intent(Home.this, Profile.class);
                startActivity(home);
            }
        });

        checkin.setOnClickListener(new View.OnClickListener(){
            public  void onClick(View v){
                home = new Intent(Home.this, checkin.class);
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
}