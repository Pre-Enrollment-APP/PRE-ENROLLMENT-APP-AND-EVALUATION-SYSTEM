package com.example.cccpre_enrollmentapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class tabmenu extends AppCompatActivity {
    public Button profile;
    public Button about, logout;
    TextView name,course,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabmenu);
        profile=findViewById(R.id.studentprofile);
        about=findViewById(R.id.about);
        logout=findViewById(R.id.logout);


        //

        name=findViewById(R.id.fullname);
        course=findViewById(R.id.course);
        email=findViewById(R.id.email);


        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(tabmenu.this,profile.class);
                startActivity(intent);
            }
        });


        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(tabmenu.this,About.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(tabmenu.this, login.class));

            }
        });



    }
}