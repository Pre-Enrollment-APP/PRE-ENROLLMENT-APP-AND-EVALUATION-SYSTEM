package com.example.cccpre_enrollmentapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.content.Intent;
import android.view.View;


public class mis_courses extends AppCompatActivity {
    public ImageButton menu_page,bscs,beed,bsoa,ABREED,bsba,bsed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mis_courses);

        menu_page=findViewById(R.id.menuicon);
        menu_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mis_courses.this,tabmenu.class);
                startActivity(intent);
            }
        });

        bscs=findViewById(R.id.stbscs);
        bscs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mis_courses.this,mis_student_list.class);
                startActivity(intent);
            }
        });

        beed=findViewById(R.id.stbeed);
        beed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mis_courses.this,mis_student_list.class);
                startActivity(intent);
            }
        });

        bsed=findViewById(R.id.stbsed);
        bsed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mis_courses.this,mis_student_list.class);
                startActivity(intent);
            }
        });

        bsba=findViewById(R.id.stbsba);
        bsba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mis_courses.this,mis_student_list.class);
                startActivity(intent);
            }
        });


        ABREED=findViewById(R.id.ABREED);
        ABREED.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mis_courses.this,mis_student_list.class);
                startActivity(intent);
            }
        });





    }
}