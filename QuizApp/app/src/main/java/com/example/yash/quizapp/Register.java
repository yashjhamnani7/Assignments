package com.example.yash.quizapp;


import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    EditText name1,mobile,editEmail,edPassword;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        db=openOrCreateDatabase("login",MODE_PRIVATE,null);

        //create a database login
        //for the 1st time
        //then it opens
        //if the table is already create 2nd time is called so we add a clause
        db.execSQL("CREATE TABLE if not exists users(name varchar(30),emailid varchar(45),password varchar(15),mobileno varchar(10))");



        editEmail=(EditText)findViewById(R.id.edmail);
        name1=(EditText)findViewById(R.id.edname);
        mobile=(EditText)findViewById(R.id.edphone);
        edPassword=(EditText)findViewById(R.id.edpassword);
        //TextView tvregister=(TextView)findViewById(R.id.tvregister);
        Button btnreg=(Button)findViewById(R.id.btnreg);
        Button btncancel=(Button)findViewById(R.id.btnlogout);
        Button back=(Button)findViewById(R.id.btnback);
        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String name=name1.getText().toString();
                String emailid=editEmail.getText().toString();
                String password=edPassword.getText().toString();
                String mobileno=mobile.getText().toString();

                //inssert the data
                try//it is optional
                {
                    db.execSQL("insert into users values('"+name+"','"+emailid+"','"+password+"','"+mobileno+"')");
                    Toast.makeText(Register.this, "YOU HAVE BEEN SUCCESSFULLLY REGISTERED", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(Register.this,MainActivity.class);
                    startActivity(i);
                    finish();
                }

                catch (Exception e)
                {
                    Toast.makeText(Register.this, "ERROR :"+e, Toast.LENGTH_SHORT).show();

                }



            }
        });
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)

            {
                edPassword.setText("");
                editEmail.setText("");
                mobile.setText("");
                name1.setText("");
                name1.requestFocus();
            }

        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Register.this,MainActivity.class);
                startActivity(i);
                finish();

            }
        });

    }
}

