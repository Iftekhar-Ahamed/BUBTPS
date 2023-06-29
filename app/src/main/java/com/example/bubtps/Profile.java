package com.example.bubtps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

public class Profile extends AppCompatActivity {
    private static final int REQUEST_CODE = 1;
    HashMap<String, String> hashMap;
    Bundle bundle=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        bundle = getIntent().getExtras();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

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
        Button chagepass = findViewById(R.id.changePassword);
        chagepass.setOnClickListener(new View.OnClickListener(){
            public  void onClick(View v){
                Intent ob = new Intent(Profile.this,ChangePassword.class);
                ob.putExtras(bundle);
                startActivityForResult(ob,REQUEST_CODE);
            }
        });

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            bundle = data.getExtras();
            if (bundle != null && bundle.containsKey("PI")) {
                hashMap= (HashMap<String, String>) bundle.getSerializable("PI");
            }
        }
    }
    public void onBackPressed() {
        Intent intent = new Intent();
        Bundle bundle1 = new Bundle();
        bundle1.putSerializable("PI",hashMap);
        intent.putExtras(bundle1);
        setResult(RESULT_OK, intent);
        finish();
    }

}