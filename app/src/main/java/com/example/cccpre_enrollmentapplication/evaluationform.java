package com.example.cccpre_enrollmentapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

public class evaluationform extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public ImageButton menu_page;
    public String[] yrlevel_array = {"1st Year", "2nd Year", "3rd Year", "4th Year"};
    public String[] sem_array2 = {"1st Semester", "2nd Semester"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_evaluation_form);

        menu_page = findViewById(R.id.menuicon);
        menu_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(evaluationform.this, tabmenu.class);
                startActivity(intent);
            }
        });
        // YEAR LEVEL SPINNER
        Spinner spin = (Spinner) findViewById(R.id.yrlvl_spinner);
        spin.setOnClickListener((View.OnClickListener) this);

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,yrlevel_array);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);
        // YEAR LEVEL SPINNER

        Spinner spin2 = (Spinner) findViewById(R.id.semester_spinner);
        spin2.setOnClickListener((View.OnClickListener) this);

        ArrayAdapter ab = new ArrayAdapter(this,android.R.layout.simple_spinner_item,sem_array2);
        ab.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(ab);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(getApplicationContext(),yrlevel_array[i] , Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(),sem_array2[i] , Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
