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




public class registrar_curriculum_form extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    public ImageButton menu_page;
    String [] yrlevel_array = {"1st Year", "2nd Year", "3rd Year", "4th Year"};
    String [] sem_array = {"1st Semester", "2nd Semester"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrar_curriculum_form);
        menu_page=findViewById(R.id.menuicon);
        menu_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(registrar_curriculum_form.this,tabmenu.class);
                startActivity(intent);
            }
        });
        // YR LEVEL SPINNER
        Spinner spin = (Spinner) findViewById(R.id.yrlevel_spinner);
        spin.setOnClickListener((View.OnClickListener) this);

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,yrlevel_array);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);
        // YR LEVEL SPINNER

        // SEMESTER SPINNER
        Spinner spin2 = (Spinner) findViewById(R.id.semester_spinner);
        spin2.setOnClickListener((View.OnClickListener) this);

        ArrayAdapter ab = new ArrayAdapter(this,android.R.layout.simple_spinner_item,sem_array);
        ab.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(ab);

        // SEMESTER SPINNER
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(getApplicationContext(),yrlevel_array[i] , Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(),sem_array[i] , Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}