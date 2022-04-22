package com.example.cccpre_enrollmentapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.view.View;

import java.util.logging.Logger;

public class login extends AppCompatActivity {
public Button button;
public Button Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logine_page);
        Login=(Button) findViewById(R.id.login);
        button=(Button) findViewById(R.id.register);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(login.this,Home_Page.class);
                startActivity(intent);
            }
        });
        //create account
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(login.this,SignUp.class);
                        startActivity(intent);
            }
        });


    }
}