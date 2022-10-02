package com.example.cccpre_enrollmentapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.content.Intent;
import android.view.View;

public class registrar_curriculum extends AppCompatActivity {
    public ImageButton menu_page;
    public ImageButton bscs,beed,bsoa,bsba,ABREED;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_curriculum);

        menu_page=findViewById(R.id.menuicon);
        menu_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(registrar_curriculum.this,tabmenu.class);
                startActivity(intent);
            }
        });

        menu_page=findViewById(R.id.menuicon);
        menu_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(registrar_curriculum.this,registrar_curriculum_form.class);
                startActivity(intent);
            }
        });

        bscs=findViewById(R.id.stbscs);
        bscs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(registrar_curriculum.this,registrar_curriculum_form.class);
                startActivity(intent);
            }
        });

        beed=findViewById(R.id.stbscs);
        beed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(registrar_curriculum.this,registrar_curriculum_form.class);
                startActivity(intent);
            }
        });

        bsoa=findViewById(R.id.stbsoa);
        bsoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(registrar_curriculum.this,registrar_curriculum_form.class);
                startActivity(intent);
            }
        });

        ABREED=findViewById(R.id.ABREED);
        ABREED.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(registrar_curriculum.this,registrar_curriculum_form.class);
                startActivity(intent);
            }
        });




    }
}