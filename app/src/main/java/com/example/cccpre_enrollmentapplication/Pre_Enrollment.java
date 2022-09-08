package com.example.cccpre_enrollmentapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Pre_Enrollment extends AppCompatActivity {
    private ImageButton menu_page;
    private Button next_page;
    private Spinner semester;
    private FirebaseUser user;

    //String [] course_array = {"BSCS", "BSBA", "BSOA", "ABREED", "BEED", "BSED"};
    // String [] major_array = {"A", "B", "C", "D"};
    // String [] sem_array = {"1st Semester", "2nd Semester"};
    //String [] section_array = {"1A", "1B", "2A", "2B", "3A", "3B", "4A", "4B"};
    // String [] mop_array = {"Down Payment", "Full Payment"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pre_enrollment);

        user= FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference databaseRef= FirebaseDatabase.getInstance().getReference("Course&Curriculum");





    }
}