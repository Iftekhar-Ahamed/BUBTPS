package com.example.bubtps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Objects;

public class Status extends AppCompatActivity {
    private HashMap<String,String> s1,s2,s3,s4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);


        DatabaseReference mDatabaseSlot1 = FirebaseDatabase.getInstance().getReference().child("Parking Area").child("1");
        DatabaseReference mDatabaseSlot2 = FirebaseDatabase.getInstance().getReference().child("Parking Area").child("2");
        DatabaseReference mDatabaseSlot3 = FirebaseDatabase.getInstance().getReference().child("Parking Area").child("3");
        DatabaseReference mDatabaseSlot4 = FirebaseDatabase.getInstance().getReference().child("Parking Area").child("4");

        TextView t1 = findViewById(R.id.textViewp1);
        ValueEventListener valueEventListenerSlot1 = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = Objects.requireNonNull(dataSnapshot.getValue()).toString();
                ToolsCode tools =new ToolsCode();
                s1 = tools.converPersonalInfo(value);
                String displaytxt = s1.get("Name")+'\n'+s1.get("Capacity")+"/"+s1.get("Intial");
                t1.setText(displaytxt);
                // Update UI with the new value
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Status.this,"Faild to reach DataBase",Toast.LENGTH_SHORT).show();
            }
        };
        mDatabaseSlot1.addValueEventListener(valueEventListenerSlot1);


        TextView t2 = findViewById(R.id.textViewp2);
        ValueEventListener valueEventListenerSlot2 = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = Objects.requireNonNull(dataSnapshot.getValue()).toString();
                ToolsCode tools =new ToolsCode();
                s2 = tools.converPersonalInfo(value);
                String displaytxt = s2.get("Name")+'\n'+s2.get("Capacity")+"/"+s2.get("Intial");
                t2.setText(displaytxt);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle errors
                Toast.makeText(Status.this,"Faild to reach DataBase",Toast.LENGTH_SHORT).show();
            }
        };

        mDatabaseSlot2.addValueEventListener(valueEventListenerSlot2);


        TextView t3 = findViewById(R.id.textViewp3);
        ValueEventListener valueEventListenerSlot3 = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = Objects.requireNonNull(dataSnapshot.getValue()).toString();
                ToolsCode tools =new ToolsCode();
                s3 = tools.converPersonalInfo(value);
                String displaytxt = s3.get("Name")+'\n'+s3.get("Capacity")+"/"+s3.get("Intial");
                t3.setText(displaytxt);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle errors
                Toast.makeText(Status.this,"Faild to reach DataBase",Toast.LENGTH_SHORT).show();
            }
        };
        mDatabaseSlot3.addValueEventListener(valueEventListenerSlot3);

        TextView t4 = findViewById(R.id.textViewp4);
        ValueEventListener valueEventListenerSlot4 = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = Objects.requireNonNull(dataSnapshot.getValue()).toString();
                ToolsCode tools =new ToolsCode();
                s4 = tools.converPersonalInfo(value);
                String displaytxt = s4.get("Name")+'\n'+s4.get("Capacity")+"/"+s4.get("Intial");
                t4.setText(displaytxt);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle errors
                Toast.makeText(Status.this,"Faild to reach DataBase",Toast.LENGTH_SHORT).show();
            }
        };
        mDatabaseSlot4.addValueEventListener(valueEventListenerSlot4);
    }
}