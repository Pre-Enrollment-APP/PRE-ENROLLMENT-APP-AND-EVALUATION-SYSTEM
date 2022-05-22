package com.example.cccpre_enrollmentapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.view.View;

public class registrarpage extends AppCompatActivity {
public ImageButton student_list;
public ImageButton curriculum;
public ImageButton menu_page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrarpage);

        student_list=findViewById(R.id.stlist);
        student_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(registrarpage.this,registrar_student_list.class);
                startActivity(intent);
            }
        });


        curriculum=findViewById(R.id.curr);
        curriculum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(registrarpage.this,registrar_curriculum.class);
                startActivity(intent);
            }
        });

        menu_page=findViewById(R.id.menuicon);
        menu_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(registrarpage.this,tabmenu.class);
                startActivity(intent);
            }
        });

    }
}