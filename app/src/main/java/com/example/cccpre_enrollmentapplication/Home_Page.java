package com.example.cccpre_enrollmentapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.content.Intent;
import android.view.View;

public class Home_Page extends AppCompatActivity {
public Button pre_enrollment;
public Button Evaluation;
public ImageButton menu_page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_homepage);
        pre_enrollment=findViewById(R.id.preenroll);
        Evaluation = findViewById(R.id.evaluate);


        pre_enrollment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Home_Page.this,Pre_Enrollment.class);
                startActivity(intent);
            }
        });

        Evaluation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Home_Page.this,evaluationform.class);
                startActivity(intent);
            }
        });

        menu_page=findViewById(R.id.menuicon);
        menu_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Home_Page.this,tabmenu.class);
                startActivity(intent);
            }
        });


    }
}