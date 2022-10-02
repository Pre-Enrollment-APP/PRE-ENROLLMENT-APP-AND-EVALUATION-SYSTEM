package com.example.cccpre_enrollmentapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.content.Intent;
import android.view.View;


public class registrar_student_list extends AppCompatActivity {
public ImageButton menu_page;
public Button edit1,edit2, edit3, edit4;
//needpangmoreEdit
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrar_student_list);

        menu_page=findViewById(R.id.menuicon);
        menu_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(registrar_student_list.this,tabmenu.class);
                startActivity(intent);
            }
        });

        edit1=findViewById(R.id.edit1);
        edit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(registrar_student_list.this,studentinfo.class);
                startActivity(intent);
            }
        });
        edit2=findViewById(R.id.edit1);
        edit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(registrar_student_list.this,studentinfo.class);
                startActivity(intent);
            }
        });

        edit3=findViewById(R.id.edit1);
        edit3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(registrar_student_list.this,studentinfo.class);
                startActivity(intent);
            }
        });






    }
}