package com.example.yash.quizapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editEmail,edPassword;
    SQLiteDatabase db;
    SharedPreferences sp;

    Editor e;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db=openOrCreateDatabase("login",MODE_PRIVATE,null);

        //to access the table
        sp=getSharedPreferences("loginsp",MODE_PRIVATE);
        e=sp.edit();


        editEmail=(EditText)findViewById(R.id.edlogin);
        edPassword=(EditText)findViewById(R.id.edpassword);
        TextView tvregister=(TextView)findViewById(R.id.tvreg);
        Button btnsign=(Button)findViewById(R.id.btnlogin);
        Button btncancel=(Button)findViewById(R.id.btnlogout);
        btnsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailid=editEmail.getText().toString();
                String password=edPassword.getText().toString();
                //we assume that email id entered is unique by everyone
                try
                {
                    Cursor cursor=db.rawQuery("select * from users where emailid='"+emailid+"' and password='"+password+"'",null);
                    if(cursor.getCount()>0)
                    {
                        e.putString("emailid",emailid);
                        e.commit();
                        Intent i=new Intent(MainActivity.this,Home.class);
                        startActivity(i);

                        //we use the shared preference so to passs to home activity

                        finish();
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "INVALID EMAIL ID/PASSWORD", Toast.LENGTH_SHORT).show();
                        editEmail.setText("");
                        //to open the database
                        edPassword.setText("");
                        editEmail.requestFocus();
                    }
                }
                catch (Exception e)
                {
                    Toast.makeText(MainActivity.this, "ERROR  :"+e, Toast.LENGTH_SHORT).show();
                }
            }
        });
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                editEmail.setText("");
                edPassword.setText("");
                editEmail.requestFocus();

            }

        });
        tvregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,Register.class);
                startActivity(i);
                finish();
            }
        });


    }
}



