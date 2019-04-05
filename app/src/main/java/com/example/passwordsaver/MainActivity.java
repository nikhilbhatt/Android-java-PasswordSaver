package com.example.passwordsaver;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    final Context context=this;
    private FloatingActionButton add;
    private SQLiteDatabase mdatabase;
    private adapterclass madpater;
    private int x;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        android.support.v7.widget.Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Password");
        final databasehelper dbhelper=new databasehelper(this);
        mdatabase=dbhelper.getWritableDatabase();
        final RecyclerView recyclerView=findViewById(R.id.recyclerrview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        AnimationDrawable animationDrawable=(AnimationDrawable) recyclerView.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();
        madpater=new adapterclass(this,getallitems());
        recyclerView.setAdapter(madpater);
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove( RecyclerView recyclerView,  RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder1) {
                return false;
            }
            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int i) {
                if(x==1)
                {
                    removeitem((long) viewHolder.itemView.getTag());
                    Toast.makeText(MainActivity.this,"deleted",Toast.LENGTH_SHORT).show();
                    x=0;
                }
                else
                    Toast.makeText(MainActivity.this,"Enable delete button first",Toast.LENGTH_SHORT).show();
                madpater.notifyDataSetChanged();
                //Toast.makeText(MainActivity.this,"i="+i,Toast.LENGTH_SHORT).show();
                //removeitem((long) viewHolder.itemView.getTag());
            }
        }).attachToRecyclerView(recyclerView);
        add=findViewById(R.id.addbutton);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adddialog();
            }
        });
    }
    public void adddialog()
    {
        LayoutInflater inflater=LayoutInflater.from(this);
        View v=inflater.inflate(R.layout.dialogbox,null);
        AlertDialog.Builder alertdialogbuilder=new AlertDialog.Builder(this);
        final EditText inputemail=v.findViewById(R.id.dialogemail);
        final EditText inputpassword=v.findViewById(R.id.dialogpassword);
        final EditText inputname=v.findViewById(R.id.dialogname);
        alertdialogbuilder.setView(v);
        alertdialogbuilder.
                setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).
                setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(inputname.getText().toString().trim().length()<1)
                        {
                            Toast.makeText(context,"Name Can't be empty",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if(inputemail.getText().toString().trim().length()<1)
                        {
                            Toast.makeText(context,"Email Field can't be empty",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if(inputpassword.getText().toString().trim().length()<1) {
                            Toast.makeText(context, "Password field can't be empty", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        String fname=inputname.getText().toString();
                        String useremail="Email:"+inputemail.getText().toString();
                        String userpassword="Password:"+inputpassword.getText().toString();
                        ContentValues contentValues=new ContentValues();
                        contentValues.put(emailcontract.emailpasswordentry.COLOUMN_1,useremail);
                        contentValues.put(emailcontract.emailpasswordentry.COLOUMN_2,userpassword);
                        contentValues.put(emailcontract.emailpasswordentry.COLOUMN_3,fname);
                        mdatabase.insert(emailcontract.emailpasswordentry.TABLE_NAME,null,contentValues);
                        madpater.swapcursor(getallitems());
                    }
                });
        AlertDialog alertDialog=alertdialogbuilder.create();
        alertDialog.show();
    }

    private void removeitem(long id)
    {
        mdatabase.delete(emailcontract.emailpasswordentry.TABLE_NAME ,
                emailcontract.emailpasswordentry._ID + "=" + id,null);
        madpater.swapcursor(getallitems());
    }
    private Cursor getallitems()
    {
        return mdatabase.query(
            emailcontract.emailpasswordentry.TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            emailcontract.emailpasswordentry.COLOUMN_3+ " ASC"
        );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.item1)
        {
            Intent intent=new Intent(MainActivity.this,helpactivity.class);
            startActivity(intent);
        }
        if(item.getItemId()==R.id.item2)
        {
            Intent i=new Intent(MainActivity.this,about.class);
            startActivity(i);
        }
        if(item.getItemId()==R.id.item3)
        {
            Intent inte=new Intent(MainActivity.this,updatepassword.class);
            startActivity(inte);
        }
        if(item.getItemId()==R.id.item4)
        {
            x=1;
            Toast.makeText(MainActivity.this,"Delete Enabled swipe left or Right to delete an item",Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.examplemenu,menu);
        return true;
    }
}
