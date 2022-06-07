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
public ImageButton menu_page;
public Button next_page;

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
        next_page=(Button)findViewById(R.id.nextpage);

        menu_page.setOnClickListener(view -> {
            Intent intent=new Intent(Pre_Enrollment.this,tabmenu.class);
            startActivity(intent);
        });

        next_page.setOnClickListener(view -> {
            Intent intent=new Intent(Pre_Enrollment.this,Pre_enrollment_next.class);
            startActivity(intent);
        });
        // COURSE SPINNER
        Spinner spin = (Spinner) findViewById(R.id.course);
                spin.setOnClickListener((View.OnClickListener) this);

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,course_array);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);
        // COURSE SPINNER


        // MAJOR SPINNER
        Spinner spin2 = (Spinner) findViewById(R.id.major);
        spin2.setOnClickListener((View.OnClickListener) this);

        ArrayAdapter ab = new ArrayAdapter(this,android.R.layout.simple_spinner_item,major_array);
        ab.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(ab);
        // MAJOR SPINNER

        // SEMESTER SPINNER
        Spinner spin3 = (Spinner)  findViewById(R.id.semester);
        spin3.setOnClickListener((View.OnClickListener) this);

        ArrayAdapter ac = new ArrayAdapter(this,android.R.layout.simple_spinner_item,sem_array);
        ac.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(ac);
        // SEMESTER SPINNER

        // SECTION SPINNER
        Spinner spin4 = (Spinner) findViewById(R.id.section);
        spin4.setOnClickListener((View.OnClickListener) this);

        ArrayAdapter ad = new ArrayAdapter(this,android.R.layout.simple_spinner_item,section_array);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(ad);
        // SECTION SPINNER

        // MODE OF PAYMENT SPINNER
        Spinner spin5 = (Spinner) findViewById(R.id.mop);
        spin5.setOnClickListener((View.OnClickListener) this);

        ArrayAdapter ae = new ArrayAdapter(this,android.R.layout.simple_spinner_item,mop_array);
        ae.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(ae);
        // MODE OF PAYMENT SPINNER



    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}