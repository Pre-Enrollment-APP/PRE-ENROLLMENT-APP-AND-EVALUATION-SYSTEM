package com.example.cccpre_enrollmentapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.content.Intent;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;


public class mis_accounting extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public ImageButton menu_page;
    String [] course_array = {"BSCS", "BSOA", "ABREED", "BEED", "BSED", "BSBA"};
    String [] yrlvl_array = {"1st Year", "2nd Year", "3rd Year", "4th Year"};
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mis_accounting);

        menu_page=findViewById(R.id.menuicon);
        menu_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mis_accounting.this,tabmenu.class);
                startActivity(intent);
            }
        });


        menu_page=findViewById(R.id.menuicon);
        menu_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mis_accounting.this,tabmenu.class);
                startActivity(intent);
            }
        });

        // COURSE SPINNER
        Spinner spin = (Spinner) findViewById(R.id.course_spinner);
        spin.setOnClickListener((View.OnClickListener) this);

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,course_array);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);
        // COURSE SPINNER

        // YR LEVEL SPINNER
        Spinner spin2 = (Spinner) findViewById(R.id.styrlevel);
        spin2.setOnClickListener((View.OnClickListener) this);

        ArrayAdapter ab = new ArrayAdapter(this,android.R.layout.simple_spinner_item,yrlvl_array);
        ab.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(ab);
        // YR LEVEL SPINNER



    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(getApplicationContext(),course_array[i] , Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(),yrlvl_array[i] , Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}