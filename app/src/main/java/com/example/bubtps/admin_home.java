package com.example.bubtps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class admin_home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle data = getIntent().getExtras();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        Button status = findViewById(R.id.button_adminstatus),scan=findViewById(R.id.button_scan),reg=findViewById(R.id.button_register);

        scan.setOnClickListener(new View.OnClickListener(){
            public  void onClick(View v){
                Intent status = new Intent(admin_home.this, scanQrcode.class);
                startActivity(status);
            }
        });
        reg.setOnClickListener(new View.OnClickListener(){
            public  void onClick(View v){
                Intent status = new Intent(admin_home.this, Registration.class);
                startActivity(status);
            }
        });

        status.setOnClickListener(new View.OnClickListener(){
            public  void onClick(View v){
                Intent status = new Intent(admin_home.this, Status.class);
                startActivity(status);
            }
        });
    }
}