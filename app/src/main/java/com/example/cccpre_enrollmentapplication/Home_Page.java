package com.example.cccpre_enrollmentapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.view.View;

public class Home_Page extends AppCompatActivity {
public Button pre_enrollment;
public Button activity_about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        pre_enrollment=(Button) findViewById(R.id.preenroll);
        activity_about=(Button) findViewById(R.id.evaluate);


        pre_enrollment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Home_Page.this,Pre_Enrollment.class);
                startActivity(intent);
            }
        });

        activity_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent lol=new Intent(Home_Page.this,About.class);
                startActivity(lol);
            }
        });


    }
}