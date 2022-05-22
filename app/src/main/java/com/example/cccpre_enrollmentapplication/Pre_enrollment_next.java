package com.example.cccpre_enrollmentapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

public class Pre_enrollment_next extends AppCompatActivity {
public ImageButton menu_page;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_enrollment_next);

        menu_page=findViewById(R.id.menuicon);

        menu_page.setOnClickListener(view -> {
            Intent intent=new Intent(Pre_enrollment_next.this,tabmenu.class);
            startActivity(intent);
        });
    }
}