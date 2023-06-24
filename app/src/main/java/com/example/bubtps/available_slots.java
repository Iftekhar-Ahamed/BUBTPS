package com.example.bubtps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Objects;

public class available_slots extends AppCompatActivity {
    private Button b1,b2;
    private String type,id,slot1,slot2;
    private Integer capacity;
    private  HashMap<String,String>s1,s2;
    Intent home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle data = getIntent().getExtras();
        String[] t = data.getString("code").split(" ");
        type=t[1];id = t[0];
        parkingSlot(t[1]);

        super.onCreate(savedInstanceState);

        DatabaseReference mDatabaseSlot1 = FirebaseDatabase.getInstance().getReference().child("Parking Area").child(slot1);
        DatabaseReference mDatabaseSlot2 = FirebaseDatabase.getInstance().getReference().child("Parking Area").child(slot2);

        setContentView(R.layout.activity_available_slots);
        b1 = findViewById(R.id.button_status1);
        b2 = findViewById(R.id.button_status2);

        //mDatabase.child(slot2).
        ValueEventListener valueEventListenerSlot1 = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = Objects.requireNonNull(dataSnapshot.getValue()).toString();
                ToolsCode tools =new ToolsCode();
                s1 = tools.converPersonalInfo(value);
                String displaytxt = s1.get("Name")+'\n'+s1.get("Capacity")+"/"+s1.get("Intial");
                b1.setText(displaytxt);
                // Update UI with the new value
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(available_slots.this,"Faild to reach DataBase",Toast.LENGTH_SHORT).show();
            }
        };
        mDatabaseSlot1.addValueEventListener(valueEventListenerSlot1);
        ValueEventListener valueEventListenerSlot2 = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = Objects.requireNonNull(dataSnapshot.getValue()).toString();
                ToolsCode tools =new ToolsCode();
                s2 = tools.converPersonalInfo(value);
                String displaytxt = s2.get("Name")+'\n'+s2.get("Capacity")+"/"+s2.get("Intial");
                b2.setText(displaytxt);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle errors
                Toast.makeText(available_slots.this,"Faild to reach DataBase",Toast.LENGTH_SHORT).show();
            }
        };
        mDatabaseSlot2.addValueEventListener(valueEventListenerSlot2);



        b1.setOnClickListener(new View.OnClickListener(){
            public  void onClick(View v){
                if(Objects.equals(s1.get("Capacity"), "0")){
                    Toast.makeText(available_slots.this,"No space left "+s1.get("Name"),Toast.LENGTH_SHORT).show();
                }else {
                    home = new Intent(available_slots.this, checkin.class);
                    String code = id + " " +type+" "+slot1;
                    home.putExtra("code",code);
                    startActivity(home);
                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener(){
            public  void onClick(View v){
                if(Objects.equals(s2.get("Capacity"), "0")){
                    Toast.makeText(available_slots.this,"No space left "+s2.get("Name"),Toast.LENGTH_SHORT).show();
                }else {
                    home = new Intent(available_slots.this, checkin.class);
                    String code = id + " " +type+" "+slot2;
                    home.putExtra("code",code);
                    startActivity(home);
                }
            }
        });
    }
    public void parkingSlot(String s){

        if(s.equals("bike")){
            slot1="2";slot2="4";
        }else {
            slot1="1";slot2="3";
        }
    }
}