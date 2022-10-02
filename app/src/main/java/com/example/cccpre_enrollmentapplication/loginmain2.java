package com.example.cccpre_enrollmentapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class loginmain2 extends AppCompatActivity {
public ImageButton Admin;
public ImageButton Student;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginmain2);

        Admin= findViewById(R.id.buttonadmin);
        Student= findViewById(R.id.buttonstudent);
        Admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(loginmain2.this,adminmenu2.class);
                startActivity(intent);
            }
        });

        Student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(loginmain2.this,login.class);
                startActivity(intent);

            }
        });
    }
}