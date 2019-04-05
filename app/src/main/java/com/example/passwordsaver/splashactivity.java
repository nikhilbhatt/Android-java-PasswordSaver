package com.example.passwordsaver;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.HttpAuthHandler;

public class splashactivity extends AppCompatActivity {

    private String textPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashactivity);
        SharedPreferences sharedPreferences=getSharedPreferences("PREFS",0);
        textPassword=sharedPreferences.getString("password","");
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
               if(textPassword.isEmpty())
               {
                    Intent i=new Intent(splashactivity.this,password.class);
                    startActivity(i);
                    finish();
               }
               else
               {
                   Intent intent=new Intent(splashactivity.this,Enterpassword.class);
                   startActivity(intent);
                   finish();

               }
            }
        },2000);
    }
}
