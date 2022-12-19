package com.example.cccpre_enrollmentapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class ViewingGrades extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    String[] sem ={"1st Semester", "2nd Semester"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewing_grades);
        Spinner semester=findViewById(R.id.semester);
        semester.setOnItemSelectedListener(this);

        ArrayAdapter adapter =new ArrayAdapter(this, android.R.layout.simple_spinner_item,sem);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        semester.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}