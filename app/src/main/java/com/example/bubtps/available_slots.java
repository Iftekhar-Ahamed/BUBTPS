package com.example.bubtps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class available_slots extends AppCompatActivity {
    private Button b1,b2;
    Intent home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_slots);
        b1 = findViewById(R.id.button_status1);
        b2 = findViewById(R.id.button_status2);
        b1.setOnClickListener(new View.OnClickListener(){
            public  void onClick(View v){
                home = new Intent(available_slots.this, checkin.class);
                startActivity(home);
            }
        });
        b2.setOnClickListener(new View.OnClickListener(){
            public  void onClick(View v){
                home = new Intent(available_slots.this, checkin.class);
                startActivity(home);
            }
        });
    }
}