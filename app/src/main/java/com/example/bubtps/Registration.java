package com.example.bubtps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
class user{
    String name,intake,pass,section,dept,year;
    user(String n,String in,String sec,String de,String d){
        name = n;
        intake = in;
        pass = "1234";
        section=sec;
        dept=de;
        year = d;
    }
}
public class Registration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Button submit = findViewById(R.id.buttonRsubmit);
        TextView id = findViewById(R.id.Rid);


        submit.setOnClickListener(new View.OnClickListener(){
            public  void onClick(View v){
                Process(id);
            }
        });
    }
    public void Process(TextView uid){
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("Profile");
        Loading loading = new Loading(Registration.this);
        loading.startLoading();
        final Boolean[] fl = {false};
        mDatabase.child(uid.getText().toString()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                loading.endLoading();
                if (!task.isSuccessful()) {
                    Toast.makeText(Registration.this,"Faild to reach DataBase",Toast.LENGTH_SHORT).show();
                }else if(task.getResult().getValue()==null){
                    submitprofileinfo();
                    Toast.makeText(Registration.this,"Successfully Added",Toast.LENGTH_SHORT).show();
                    TextView name = findViewById(R.id.Rname),id = findViewById(R.id.Rid),intake = findViewById(R.id.Rintake),section = findViewById(R.id.Rsection),dept = findViewById(R.id.RDept);
                    name.setText(null);
                    id.setText(null);
                    intake.setText(null);
                    section.setText(null);
                    dept.setText(null);

                }
                else {
                    Toast.makeText(Registration.this,"Already Exits",Toast.LENGTH_SHORT).show();
                    //ok;
                }
            }
        });
    }
    public void submitprofileinfo(){
        Loading loading = new Loading(Registration.this);
        loading.startLoading();



        TextView name = findViewById(R.id.Rname),id = findViewById(R.id.Rid),intake = findViewById(R.id.Rintake),section = findViewById(R.id.Rsection),dept = findViewById(R.id.RDept),y=findViewById(R.id.editTextRDate);
        user ob = new user(name.getText().toString(),intake.getText().toString(),section.getText().toString(),dept.getText().toString(),y.getText().toString());



        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Profile");
        ref.child(id.getText().toString()).child("Name").setValue(ob.name);
        ref.child(id.getText().toString()).child("Intake").setValue(ob.intake);
        ref.child(id.getText().toString()).child("Password").setValue(ob.pass);
        ref.child(id.getText().toString()).child("Section").setValue(ob.section);
        ref.child(id.getText().toString()).child("Year").setValue(ob.year);
        ref.child(id.getText().toString()).child("Department").setValue(ob.dept);
        loading.endLoading();
    }
}