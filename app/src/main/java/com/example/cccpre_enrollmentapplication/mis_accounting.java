package com.example.cccpre_enrollmentapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;


public class mis_accounting extends AppCompatActivity{

    public Spinner spinner;
    public DatabaseReference databaseReference;
    public TextView textView;
    public String year;
    public Year Year;
    public String[] yrlevel_array = {"Year Level","1st Year", "2nd Year", "3rd Year", "4th Year"};
    public ImageButton menu_page;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mis_accounting);
        menu_page = findViewById(R.id.menuicon);
        menu_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mis_accounting.this,tabmenu.class);
                startActivity(intent);
            }
        });


        menu_page = findViewById(R.id.menuicon);
        menu_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mis_accounting.this,tabmenu.class);
                startActivity(intent);
            }
        });
    }
}