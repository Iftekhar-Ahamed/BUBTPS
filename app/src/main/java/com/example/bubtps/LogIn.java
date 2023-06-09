package com.example.bubtps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class LogIn extends AppCompatActivity {
    private Button login;
    private TextView uid,pass;
    Intent home;
    private DatabaseReference mDatabase;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        login = findViewById(R.id.btn_login);
        Loading loading = new Loading(LogIn.this);
        login.setOnClickListener(new View.OnClickListener(){
            public  void onClick(View v){

                uid = findViewById(R.id.login_username);
                pass = findViewById(R.id.editTextTextPassword);
                mDatabase = FirebaseDatabase.getInstance().getReference();
                loading.startLoading();

                mDatabase.child("Profile").child(uid.getText().toString()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        loading.endLoading();
                        if (!task.isSuccessful()) {
                            Toast.makeText(LogIn.this,"Faild to reach DataBase",Toast.LENGTH_SHORT).show();
                        }else if(task.getResult().getValue()==null){
                            Toast.makeText(LogIn.this,"Wrong ID",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            ToolsCode tools =new ToolsCode();
                            HashMap<String,String> mp = tools.converPersonalInfo(task.getResult().getValue().toString());
                            mp.put("ID",uid.getText().toString());
                            String ss =mp.get("Password");
                            assert ss != null;
                            if(ss.equals(pass.getText().toString())){
                                Toast.makeText(LogIn.this,"Sucessfully Loged In",Toast.LENGTH_SHORT).show();

                                if(mp.size()==2){
                                    home = new Intent(LogIn.this,admin_home.class);
                                    Bundle b = new Bundle();
                                    b.putSerializable("PI",mp);
                                    home.putExtras(b);
                                    startActivity(home);
                                }else {
                                    home = new Intent(LogIn.this,Home.class);
                                    Bundle b = new Bundle();
                                    b.putSerializable("PI",mp);
                                    home.putExtras(b);
                                    startActivity(home);
                                }

                            }else {
                                Toast.makeText(LogIn.this,"Wrong Password",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });


            }
        });
    }


}