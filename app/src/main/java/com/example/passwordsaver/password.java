package com.example.passwordsaver;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class password extends AppCompatActivity {
    private EditText pass1;
    private EditText pass2;
    private Button cnfmbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        pass1=findViewById(R.id.password1);
        pass2=findViewById(R.id.password2);
        cnfmbtn=findViewById(R.id.confirmbutton);
        cnfmbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String passw1=pass1.getText().toString();
                String passw2=pass2.getText().toString();
                if(passw1.equals("")||passw2.equals(""))
                {
                    Toast.makeText(password.this,"password field is empty",Toast.LENGTH_SHORT).show();
                    return;

                }
                if(passw1.equals(passw2))
                {
                      SharedPreferences sharedPreferences=getSharedPreferences("PREFS",0);
                      SharedPreferences.Editor editor=sharedPreferences.edit();
                      editor.putString("password",passw1);
                      editor.apply();
                      Toast.makeText(password.this,"Password created successfully",Toast.LENGTH_SHORT).show();
                      Intent intent=new Intent(password.this,MainActivity.class);
                      startActivity(intent);
                      finish();
                }
                else{
                    Toast.makeText(password.this,"Password doesn't Matches",Toast.LENGTH_SHORT).show();
                    return;
                }

            }
        });
    }
}
