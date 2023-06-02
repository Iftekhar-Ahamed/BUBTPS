package com.example.bubtps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LogIn extends AppCompatActivity {
    private Button login;
    private TextView uid,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        login = findViewById(R.id.btn_login);
        login.setOnClickListener(new View.OnClickListener(){
            public  void onClick(View v){
                uid = findViewById(R.id.login_username);
                pass = findViewById(R.id.editTextTextPassword);
                if(uid.getText().toString().equals("19202103239")&&pass.getText().toString().equals("1234")){
                    Toast.makeText(LogIn.this,"LOG IN SUCCESSFUL",Toast.LENGTH_SHORT).show();
                    Intent home = new Intent(LogIn.this, Home.class);
                    startActivity(home);
                }else {
                    Toast.makeText(LogIn.this,"TRY AGAIN",Toast.LENGTH_SHORT).show();
                }
                //Toast.makeText(LogIn.this,uid.getText().toString()+" "+pass.getText().toString(),Toast.LENGTH_SHORT).show();
                //Toast.makeText(LogIn.this,"OK",Toast.LENGTH_SHORT).show();
            }
        });
    }
}