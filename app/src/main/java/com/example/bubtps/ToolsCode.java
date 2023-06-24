package com.example.bubtps;

import java.util.HashMap;

public class ToolsCode {
    ToolsCode (){

    };
    public  HashMap<String,String> converPersonalInfo(String s){
        HashMap<String,String> mp=new HashMap<String,String>();
        s=s.replace("{","").replace("}","");
        String[] parts = s.split(",");
        for (String i: parts) {
            String[] val = i.split("=");
            if(val[0].charAt(0)==' '){
                val[0]=val[0].substring(1);
            }
            mp.put(val[0],val[1]);
        }
        return  mp;
    }
}
