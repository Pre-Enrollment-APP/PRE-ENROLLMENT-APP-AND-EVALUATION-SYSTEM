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


public class Pre_Enrollment extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
private ImageButton menu_page;
private Button next_page;
private Spinner semester;

    String [] course_array = {"BSCS", "BSBA", "BSOA", "ABREED", "BEED", "BSED"};
    String [] major_array = {"A", "B", "C", "D"};
    String [] sem_array = {"1st Semester", "2nd Semester"};
    String [] section_array = {"1A", "1B", "2A", "2B", "3A", "3B", "4A", "4B"};
    String [] mop_array = {"Down Payment", "Full Payment"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pre_enrollment);
        menu_page=findViewById(R.id.menuicon);

        menu_page.setOnClickListener(view -> {
            Intent intent=new Intent(Pre_Enrollment.this,tabmenu.class);
            startActivity(intent);
        });

        semester.findViewById(R.id.semester);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.courses, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        semester.setAdapter(adapter);
        semester.setOnItemSelectedListener(this);


}

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}