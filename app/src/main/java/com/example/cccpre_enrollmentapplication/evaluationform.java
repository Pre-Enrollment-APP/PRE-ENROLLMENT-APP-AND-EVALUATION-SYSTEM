package com.example.cccpre_enrollmentapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class evaluationform extends AppCompatActivity{

    public ImageButton menu_page;
    public Spinner spinner;
    public DatabaseReference databaseReference;
    public TextView textView;
    public String yearitem;
    public Year Year;
    public Button button;
    public String[] yrlevel_array = {"Year Level","1st Year", "2nd Year", "3rd Year", "4th Year"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_evaluation_form);

        textView = findViewById(R.id.yearlevel);
        button = findViewById(R.id.more);

        }
    }