package com.example.passwordsaver;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Enterpassword extends AppCompatActivity {
   private EditText menterpassword;
   private Button mbutton;
   private String mpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enterpassword);
        menterpassword=findViewById(R.id.enterpassword);
        mbutton=findViewById(R.id.submit);
        mbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences=getSharedPreferences("PREFS",0);
                mpassword=sharedPreferences.getString("password","");
                String text=menterpassword.getText().toString();
                if(mpassword.equals(text))
                {
                    Intent i=new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i);
                    finish();
                }
                else {
                    Toast.makeText(Enterpassword.this,"Wrong password",Toast.LENGTH_SHORT).show();
                }
             }
        });
    }
}
