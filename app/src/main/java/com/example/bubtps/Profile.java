package com.example.bubtps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.HashMap;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle bundle = getIntent().getExtras();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        HashMap<String, String> hashMap;
        if (bundle != null && bundle.containsKey("PI")) {
            hashMap= (HashMap<String, String>) bundle.getSerializable("PI");
            TextView name = findViewById(R.id.textview_name) ;
            name.setText(hashMap.get("Name"));

            TextView dept = findViewById(R.id.textview_dept) ;
            dept.setText(hashMap.get("Department"));

            TextView intakeSec = findViewById(R.id.textview_Intake_section) ;
            intakeSec.setText("INTAKE-"+hashMap.get("Intake")+" | "+"SECTION-"+hashMap.get("Section"));

            TextView id = findViewById(R.id.textview_id);
            id.setText("ID-"+hashMap.get("ID"));
        }
    }
}