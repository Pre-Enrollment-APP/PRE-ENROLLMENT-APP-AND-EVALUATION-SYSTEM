package com.example.cccpre_enrollmentapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.content.Intent;


public class Pre_Enrollment extends AppCompatActivity {
public ImageButton menu_page;
public Button next_page;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pre_enrollment);
        menu_page=findViewById(R.id.menuicon);
        next_page=(Button)findViewById(R.id.nextpage);

        menu_page.setOnClickListener(view -> {
            Intent intent=new Intent(Pre_Enrollment.this,tabmenu.class);
            startActivity(intent);
        });

        next_page.setOnClickListener(view -> {
            Intent intent=new Intent(Pre_Enrollment.this,Pre_enrollment_next.class);
            startActivity(intent);
        });

    }
}