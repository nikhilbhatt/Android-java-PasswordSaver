package com.example.passwordsaver;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class updatepassword extends AppCompatActivity {
    private EditText mcurrentpassword;
    private EditText mnewpassword;
    private EditText mconfirmnewpassword;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatepassword);
        Toolbar toolbar=findViewById(R.id.toolbarupdate);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Update Password");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mcurrentpassword=findViewById(R.id.current);
        mnewpassword=findViewById(R.id.newpassword);
        mconfirmnewpassword=findViewById(R.id.confirmnewpassword);
        button=findViewById(R.id.updatebutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textpassword=mcurrentpassword.getText().toString();
                String textnewpassword=mnewpassword.getText().toString();
                String textconfirmpassword=mconfirmnewpassword.getText().toString();
                SharedPreferences sharedPreferences=getSharedPreferences("PREFS",0);
                String currentpassword=sharedPreferences.getString("password","");
                if(textpassword.equals(currentpassword))
                {
                    if(textnewpassword.equals(textconfirmpassword))
                    {
                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        editor.putString("password",textnewpassword);
                        editor.apply();
                        Toast.makeText(updatepassword.this,"Password updated",Toast.LENGTH_SHORT).show();
                        Intent intnt=new Intent(updatepassword.this,MainActivity.class);
                        startActivity(intnt);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(updatepassword.this,"Password doesn't matches",Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(updatepassword.this,"Currrent password is wrong",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
