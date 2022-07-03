package com.example.cccpre_enrollmentapplication;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.content.Intent;
import android.widget.Spinner;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class mis_grade extends AppCompatActivity{
    public ImageButton menu_page;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mis_grade);
        menu_page = findViewById(R.id.menuicon);
        menu_page.setOnClickListener(view -> {
            Intent intent = new Intent(mis_grade.this, tabmenu.class);
            startActivity(intent);
        });
}
    }