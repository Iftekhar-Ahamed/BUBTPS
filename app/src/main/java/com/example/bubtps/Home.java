package com.example.bubtps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Locale;
import java.util.Objects;

public class Home extends AppCompatActivity {
    Boolean flag = false;
    Dialog checkindialog;
    HashMap<String, String> hashMap;
    String[] checkoutid;

    String id,uniqeid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle data = getIntent().getExtras();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        if (data != null && data.containsKey("PI")) {
            hashMap= (HashMap<String, String>) data.getSerializable("PI");
            id=hashMap.get("ID");
        }
        LocalDate d = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            d = LocalDate.now();
        }
        assert d != null;
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("Data").child(d.toString()).child(id).child("Status");

        ValueEventListener valueEventListenerSlot = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot.getValue()== null){
                    flag = false;
                }else {
                    String value = Objects.requireNonNull(dataSnapshot.getValue()).toString();
                    if(value.equals("Nill")){
                        flag = false;
                    }else {
                        checkoutid = new String[]{id, value};
                        flag = true;
                    }
                }
                // Update UI with the new value
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Home.this,"Faild to reach DataBase",Toast.LENGTH_SHORT).show();
            }
        };
        mDatabase.addValueEventListener(valueEventListenerSlot);



        Button profile = findViewById(R.id.btn_profile);
        Button checkout = findViewById(R.id.btn_checkout);
        Button status = findViewById(R.id.btn_status);
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
                if(!flag){
                    Toast.makeText(Home.this,"You Have No Parking",Toast.LENGTH_SHORT).show();
                }else {
                    Intent checkout = new Intent(Home.this, Checkout.class);
                    Bundle t = new Bundle();
                    t.putString("cid",checkoutid[1].toString());
                    checkout.putExtras(t);
                    startActivity(checkout);
                }

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
        if(flag){
            Toast.makeText(Home.this,"Already Parked",Toast.LENGTH_SHORT).show();
        }else {
            TextView close;
            AppCompatButton bike, car;
            checkindialog.setContentView(R.layout.activity_bike_car);
            close = (TextView) checkindialog.findViewById(R.id.button_cancel);
            bike = checkindialog.findViewById(R.id.button_bike);
            car = checkindialog.findViewById(R.id.button_car);

            close.setOnClickListener(new View.OnClickListener() {
                public void onClick(View vi) {
                    checkindialog.dismiss();
                }
            });
            bike.setOnClickListener(new View.OnClickListener() {
                public void onClick(View vi) {
                    Intent bike = new Intent(Home.this, available_slots.class);
                    String code = id + " bike";
                    bike.putExtra("code", code);
                    startActivity(bike);
                }
            });
            car.setOnClickListener(new View.OnClickListener() {
                public void onClick(View vi) {
                    String code = id + " car";
                    Intent car = new Intent(Home.this, available_slots.class);
                    car.putExtra("code", code);
                    startActivity(car);
                }
            });
            checkindialog.show();
        }
    }
}