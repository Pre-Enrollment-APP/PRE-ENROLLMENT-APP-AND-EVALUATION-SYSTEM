package com.example.cccpre_enrollmentapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Pre_Enrollment extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private ImageButton menu_page;
    private Button next_page;
    private TextView name ,course;
    private Spinner semester,year;
    private FirebaseUser user;
    private String userID;
    private  DatabaseReference databaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pre_enrollment);
        name = findViewById(R.id.studentName);
        course = findViewById(R.id.course);
        semester = findViewById(R.id.semester);
        year = findViewById(R.id.year);


        user = FirebaseAuth.getInstance().getCurrentUser();
        databaseRef = FirebaseDatabase.getInstance().getReference("User");
        userID = user.getUid();
        course = findViewById(R.id.course);


        //year spinner

       /* ArrayAdapter<CharSequence> adapt = ArrayAdapter.createFromResource(this, R.array.year_level, android.R.layout.simple_spinner_item);
        adapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        year.setAdapter(adapt);
        year.setOnItemSelectedListener(this);*/


        //semester spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.semester, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        semester.setAdapter(adapter);
        semester.setOnItemSelectedListener(this);
        databaseRef.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String Name = snapshot.child("Name").getValue().toString();
                name.setText(Name);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


        String item = parent.getItemAtPosition(position).toString();


        if (course.equals("Bachelor of Science in Computer Science")) {
                if (item.equals("1st Semester")) {

                    Toast.makeText(this, "yes", Toast.LENGTH_SHORT).show();


                }
            }else {
            Toast.makeText(this, "no", Toast.LENGTH_SHORT).show();
        }
        }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}