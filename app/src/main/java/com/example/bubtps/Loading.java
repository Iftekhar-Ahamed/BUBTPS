package com.example.bubtps;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

public class Loading {
    Activity activity;
    AlertDialog alertDialog;
    Loading(Activity myactivity){
        activity = myactivity;

    }
    void startLoading(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.loading,null));
        alertDialog = builder.create();
        alertDialog.show();
    }
    void endLoading(){
        alertDialog.dismiss();
    }
}
