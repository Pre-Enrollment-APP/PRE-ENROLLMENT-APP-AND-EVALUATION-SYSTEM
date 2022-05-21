package com.example.cccpre_enrollmentapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.view.View;

public class Pre_Enrollment extends AppCompatActivity {
public ImageButton menu_page;
public Button nextpage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pre_enrollment);
        menu_page=findViewById(R.id.menuicon);
        menu_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Pre_Enrollment.this,tabmenu.class);
                startActivity(intent);
            }
        });


        nextpage=findViewById(R.id.nextpage);
        nextpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Pre_Enrollment.this,Pre_Enrollment_nextpage.class);
                startActivity(intent);
            }
        });
    }
}