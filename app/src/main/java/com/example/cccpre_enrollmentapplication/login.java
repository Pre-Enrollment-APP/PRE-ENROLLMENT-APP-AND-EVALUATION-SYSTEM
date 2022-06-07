package com.example.cccpre_enrollmentapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.view.View;

import java.util.logging.Logger;

public class login extends AppCompatActivity {
public Button Login;
public Button CAA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logine_page);
        Login=(Button) findViewById(R.id.login);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(login.this,Home_Page.class);
                startActivity(intent);
            }
        });
        //create account

        CAA=findViewById(R.id.createaccount);

        CAA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(login.this,registerUser.class);
                startActivity(intent);
            }
        });

    }
}