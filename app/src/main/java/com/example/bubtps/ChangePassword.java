package com.example.bubtps;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Objects;


public class ChangePassword extends AppCompatActivity {
    HashMap<String, String> hashMap=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle bundle = getIntent().getExtras();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        if (bundle != null && bundle.containsKey("PI")) {
            hashMap= (HashMap<String, String>) bundle.getSerializable("PI");
        }
        EditText cpss = findViewById(R.id.CurrentPass),newpass = findViewById(R.id.Newpass),cnfpass = findViewById(R.id.ConfirmPass);
        Button save = findViewById(R.id.buttonSave);
        save.setOnClickListener(new View.OnClickListener(){
            public  void onClick(View v){
                if(cpss.getText()!=null && newpass.getText()!=null && cnfpass.getText()!=null){
                    assert hashMap != null;
                    if(cpss.getText().toString().equals(hashMap.get("Password")) ){
                        if( cnfpass.getText().toString().equals(newpass.getText().toString())){
                            Loading loading = new Loading(ChangePassword.this);
                            loading.startLoading();
                            DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Profile");
                            ref.child(Objects.requireNonNull(hashMap.get("ID"))).child("Password").setValue(newpass.getText().toString());
                            hashMap.put("Password",newpass.getText().toString());
                            loading.endLoading();
                            Toast.makeText(ChangePassword.this,"Successfully Changed",Toast.LENGTH_SHORT).show();
                            sendUpdatedValue();
                        }else {
                            Toast.makeText(ChangePassword.this,"Password Not Matched",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(ChangePassword.this,"Current Password Incorrect",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(ChangePassword.this,"Filed Empty",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void sendUpdatedValue() {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable("PI",hashMap);
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
        finish();
    }
}