package com.example.cccpre_enrollmentapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.content.Intent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;


public class profile extends AppCompatActivity {
    public TextView name, studentnumber, course;
    public EditText add,fnumber,mnumber,fname,mname,num,bday,emailadd;
    private FirebaseUser user;
    private String userID;
    private FirebaseAuth mAuth;
    private ImageButton editButton;

    private ProgressBar progressbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_profile);

        name=findViewById(R.id.SP_fullname);
        studentnumber=findViewById(R.id.Snumber);
        emailadd=findViewById(R.id.SP_email);
        course=findViewById(R.id.course);
        add=findViewById(R.id.SP_address);
        fnumber=findViewById(R.id.SP_fathernumber);
        mnumber=findViewById(R.id.SP_mothernumber);
        fname=findViewById(R.id.SP_fathername);
        mname=findViewById(R.id.SP_mothername);
        num=findViewById(R.id.SP_number);
        bday=findViewById(R.id.SP_bday);
        progressbar= findViewById(R.id.progressbar);

        editButton=findViewById(R.id.editbutton);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(profile.this,StudentProfile_Edit.class);
                startActivity(intent);
            }
        });




        mAuth=FirebaseAuth.getInstance();
        user=FirebaseAuth.getInstance().getCurrentUser();
        userID=user.getUid();
        DatabaseReference databaseRef= FirebaseDatabase.getInstance().getReference("User");

        progressbar.setVisibility(View.VISIBLE);
        databaseRef.child(userID).addListenerForSingleValueEvent((new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String Name =snapshot.child("Name").getValue().toString();
                String StudentNumber=snapshot.child("Student_number").getValue().toString();
                String Course=snapshot.child("Course").getValue().toString();

                String Number=snapshot.child("Contact_Number").getValue().toString();
                String Email =snapshot.child("Email").getValue().toString();
                String Address=snapshot.child("Address").getValue().toString();
                String Birthday=snapshot.child("Birthday").getValue().toString();
                String MotherName=snapshot.child("Mother").getValue().toString();
                String FatherName=snapshot.child("Father").getValue().toString();
                String Fathernumber=snapshot.child("Father_number").getValue().toString();
                String Mothernumber=snapshot.child("Mother_number").getValue().toString();

                name.setText(Name);
                course.setText(Course);
                studentnumber.setText(StudentNumber);

                num.setText(Number);
                emailadd.setText(Email);
                add.setText(Address);
                bday.setText(Birthday);
                mname.setText(MotherName);
                fname.setText(FatherName);
                fnumber.setText(Fathernumber);
                mnumber.setText(Mothernumber);

                progressbar.setVisibility(View.GONE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(profile.this, "Something wrong happened!", Toast.LENGTH_SHORT).show();
            }
        }));


    }
}