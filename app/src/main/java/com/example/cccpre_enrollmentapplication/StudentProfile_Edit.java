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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;


public class StudentProfile_Edit extends AppCompatActivity {
    private TextView name, studentnumber, course;
    private EditText add,fnumber,mnumber,fname,mname,num,bday,emailadd;
    private FirebaseUser user;
    private String userID;
    public String _Name,_Email, Number,_Course, _Address, _Birthday,_Contact_Number,_Mother,_Mother_number,_Father,_Father_number;
    private FirebaseAuth mAuth;


    private Button SAVEPROFILE;
    private Spinner coursespinner;
    private Button DateButton;
    private DatePickerDialog datePickerDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_profile_edit);

        name=findViewById(R.id.SP_fullname);
        studentnumber=findViewById(R.id.Snumber);
        emailadd=findViewById(R.id.SP_email);
        course=findViewById(R.id.course);
        add=findViewById(R.id.SP_address);
        fnumber=findViewById(R.id.SP_fathernumber);
        mnumber=findViewById(R.id.SP_mothernumber);
        fname=findViewById(R.id.SP_fathername);
        mname=findViewById(R.id.SP_mothername);
        bday=findViewById(R.id.SP_bday);
        SAVEPROFILE=findViewById(R.id.editbutton);
         num=findViewById(R.id.SP_number);


        user=FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference databaseRef= FirebaseDatabase.getInstance().getReference("User");
        userID=user.getUid();




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

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(StudentProfile_Edit .this, "Something wrong happened!", Toast.LENGTH_SHORT).show();
            }
        }));

        SAVEPROFILE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Email = emailadd.getText().toString().trim();
                String Course = coursespinner.getSelectedItem().toString().trim();
                String Name = name.getText().toString().trim();
                String Contact_Number= num.getText().toString().trim();
                String Birthday=bday.getText().toString().trim();
                String Address= add.getText().toString().trim();
                String Mother =mname.getText().toString().trim();
                String Father =fname.getText().toString().trim();
                String Student_number=studentnumber.getText().toString().trim();
                String Father_number=fnumber.getText().toString().trim();
                String Mother_number=mnumber.getText().toString().trim();

                //databaseRef.child("User").child(userID).addValueEventListener();




            }
        });


    }
}