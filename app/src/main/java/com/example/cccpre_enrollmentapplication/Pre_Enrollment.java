package com.example.cccpre_enrollmentapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class Pre_Enrollment extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private ImageButton menu_page;
    private Button ok;
    private TextView name,course;
    private Spinner semester, schoolyear,section,mop;
    private EditText des1,des2,des3,des4,des5,des6,des7,des8,des9,des10;
    private EditText units1,units2,units3,units4,units5,units6,units7,units8,units9,units10;
    ArrayAdapter<String> sectionlist;
    String[] sections;
    private String userID;
    private DatabaseReference databaseRef;
    private FirebaseUser user;
    private EditText SC1,SC2,SC3,SC4,SC5,SC6,SC7,SC8,SC9,SC10;


    //print pdf
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference print=database.getReference("record");
    studentinfo dataObj=new studentinfo();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pre_enrollment);
        name = findViewById(R.id.studentName);
        course = findViewById(R.id.course);
        semester = findViewById(R.id.semester);
        section=findViewById(R.id.section);
        schoolyear= findViewById(R.id.schoolyear);

        user = FirebaseAuth.getInstance().getCurrentUser();
        databaseRef = FirebaseDatabase.getInstance().getReference("User");
        userID = user.getUid();

        SC1=findViewById(R.id.SC1);
        SC2=findViewById(R.id.SC2);
        SC3=findViewById(R.id.SC3);
        SC4=findViewById(R.id.SC4);
        SC5=findViewById(R.id.SC5);
        SC6=findViewById(R.id.SC6);
        SC7=findViewById(R.id.SC7);
        SC8=findViewById(R.id.SC8);
        SC9=findViewById(R.id.SC9);
        SC10=findViewById(R.id.SC10);

        des1=findViewById(R.id.Des1);
        des2=findViewById(R.id.Des2);
        des3=findViewById(R.id.Des3);
        des4=findViewById(R.id.Des4);
        des5=findViewById(R.id.Des5);
        des6=findViewById(R.id.Des6);
        des7=findViewById(R.id.Des7);
        des8=findViewById(R.id.Des8);
        des9=findViewById(R.id.Des9);
        des10=findViewById(R.id.Des10);

        units1=findViewById(R.id.units1);
        units2=findViewById(R.id.units2);
        units3=findViewById(R.id.units3);
        units4=findViewById(R.id.units4);
        units5=findViewById(R.id.units5);
        units6=findViewById(R.id.units6);
        units7=findViewById(R.id.units7);
        units8=findViewById(R.id.units8);
        units9=findViewById(R.id.units9);
        units10=findViewById(R.id.units10);

        sections=new String[]{"A","B","C","D","E"};
        sectionlist=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,sections);
        section.setAdapter(sectionlist);

        //year spinner

       /* ArrayAdapter<CharSequence> adapt = ArrayAdapter.createFromResource(this, R.array.year_level, android.R.layout.simple_spinner_item);
        adapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        year.setAdapter(adapt);
        year.setOnItemSelectedListener(this);*/
        //semester spinner
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.semester, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        semester.setAdapter(adapter);
        semester.setOnItemSelectedListener(this);



        databaseRef.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String Name = snapshot.child("Name").getValue().toString();
                String Course = snapshot.child("Course").getValue().toString();

                name.setText(Name);
                course.setText(Course);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        ok=findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dataObj.name=String.valueOf(name.getText());
                dataObj.course=String.valueOf(course.getText());






            }
        });



    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
switch (item) {
    case "1st yr, 1st sem":
    databaseRef.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            String Course = snapshot.child("Course").getValue().toString();

            if(Course.equals("Bachelor of Science in Computer Science")){
                BSCS1y1sem();

            }

        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    });
    break;
    case "1st yr, 2nd sem":
        databaseRef.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String Course = snapshot.child("Course").getValue().toString();



                if(Course.equals("Bachelor of Science in Computer Science")){
                    BSCS1y2sem();


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
}



    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void BSCS1y1sem(){
        DatabaseReference databasecs = FirebaseDatabase.getInstance().getReference("course_curriculum/bscs/first_year");

        databasecs.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //Introduction to Computing
                String sub1= snapshot.child("first_sem/subject1/code").getValue().toString();
                String Des1= snapshot.child("first_sem/subject1/descriptive_title").getValue().toString();
                String Unit= snapshot.child("first_sem/subject1/units").getValue().toString();
                SC1.setText(sub1);
                des1.setText(Des1);
                units1.setText(Unit);

                String sub2= snapshot.child("first_sem/subject2/code").getValue().toString();
                String Des2= snapshot.child("first_sem/subject2/descriptive_title").getValue().toString();
                String Unit2= snapshot.child("first_sem/subject2/units").getValue().toString();
                SC2.setText(sub2);
                des2.setText(Des2);
                units2.setText(Unit2);

                String sub3= snapshot.child("first_sem/subject3/code").getValue().toString();
                String Des3= snapshot.child("first_sem/subject3/descriptive_title").getValue().toString();
                String Unit3= snapshot.child("first_sem/subject3/units").getValue().toString();
                SC3.setText(sub3);
                des3.setText(Des3);
                units3.setText(Unit3);

                 String sub4= snapshot.child("first_sem/subject4/code").getValue().toString();
                String Des4= snapshot.child("first_sem/subject4/descriptive_title").getValue().toString();
                String Unit4= snapshot.child("first_sem/subject4/units").getValue().toString();
                SC4.setText(sub4);
                des4.setText(Des4);
                units4.setText(Unit4);

                String sub5= snapshot.child("first_sem/subject5/code").getValue().toString();
                String Des5= snapshot.child("first_sem/subject5/descriptive_title").getValue().toString();
                String Unit5= snapshot.child("first_sem/subject5/units").getValue().toString();
                SC5.setText(sub5);
                des5.setText(Des5);
                units5.setText(Unit5);

                String sub6= snapshot.child("first_sem/subject6/code").getValue().toString();
                String Des6= snapshot.child("first_sem/subject6/descriptive_title").getValue().toString();
                String Unit6= snapshot.child("first_sem/subject6/units").getValue().toString();
                SC6.setText(sub6);
                des6.setText(Des6);
                units6.setText(Unit6);

                String sub7= snapshot.child("first_sem/subject7/code").getValue().toString();
                String Des7= snapshot.child("first_sem/subject7/descriptive_title").getValue().toString();
                String Unit7= snapshot.child("first_sem/subject7/units").getValue().toString();
                SC7.setText(sub7);
                des7.setText(Des7);
                units7.setText(Unit7);

                 String sub8= snapshot.child("first_sem/subject8/code").getValue().toString();
                String Des8= snapshot.child("first_sem/subject8/descriptive_title").getValue().toString();
                String Unit8= snapshot.child("first_sem/subject8/units").getValue().toString();
                SC8.setText(sub8);
                des8.setText(Des8);
                units8.setText(Unit8);

                String sub9= snapshot.child("first_sem/subject9/code").getValue().toString();
                String Des9= snapshot.child("first_sem/subject9/descriptive_title").getValue().toString();
                String Unit9= snapshot.child("first_sem/subject9/units").getValue().toString();
                SC9.setText(sub9);
                des9.setText(Des9);
                units9.setText(Unit9);

                String sub10= snapshot.child("first_sem/subject10/code").getValue().toString();
                String Des10= snapshot.child("first_sem/subject10/descriptive_title").getValue().toString();
                String Unit10= snapshot.child("first_sem/subject10/units").getValue().toString();
                SC10.setText(sub10);
                des10.setText(Des10);
                units10.setText(Unit10);





            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void BSCS1y2sem(){

        DatabaseReference databasecs = FirebaseDatabase.getInstance().getReference("course_curriculum/bscs/first_year");

        databasecs.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //Introduction to Computing
                String sub1= snapshot.child("first_sem/subject1/code").getValue().toString();
                String Des1= snapshot.child("first_sem/subject1/descriptive_title").getValue().toString();
                String Unit= snapshot.child("first_sem/subject1/units").getValue().toString();
                SC1.setText(sub1);
                des1.setText(Des1);
                units1.setText(Unit);

                String sub2= snapshot.child("first_sem/subject2/code").getValue().toString();
                String Des2= snapshot.child("first_sem/subject2/descriptive_title").getValue().toString();
                String Unit2= snapshot.child("first_sem/subject2/units").getValue().toString();
                SC2.setText(sub2);
                des2.setText(Des2);
                units2.setText(Unit2);

                String sub3= snapshot.child("first_sem/subject3/code").getValue().toString();
                String Des3= snapshot.child("first_sem/subject3/descriptive_title").getValue().toString();
                String Unit3= snapshot.child("first_sem/subject3/units").getValue().toString();
                SC3.setText(sub3);
                des3.setText(Des3);
                units3.setText(Unit3);

                String sub4= snapshot.child("first_sem/subject4/code").getValue().toString();
                String Des4= snapshot.child("first_sem/subject4/descriptive_title").getValue().toString();
                String Unit4= snapshot.child("first_sem/subject4/units").getValue().toString();
                SC4.setText(sub4);
                des4.setText(Des4);
                units4.setText(Unit4);

                String sub5= snapshot.child("first_sem/subject5/code").getValue().toString();
                String Des5= snapshot.child("first_sem/subject5/descriptive_title").getValue().toString();
                String Unit5= snapshot.child("first_sem/subject5/units").getValue().toString();
                SC5.setText(sub5);
                des5.setText(Des5);
                units5.setText(Unit5);

                String sub6= snapshot.child("first_sem/subject6/code").getValue().toString();
                String Des6= snapshot.child("first_sem/subject6/descriptive_title").getValue().toString();
                String Unit6= snapshot.child("first_sem/subject6/units").getValue().toString();
                SC6.setText(sub6);
                des6.setText(Des6);
                units6.setText(Unit6);

                String sub7= snapshot.child("first_sem/subject7/code").getValue().toString();
                String Des7= snapshot.child("first_sem/subject7/descriptive_title").getValue().toString();
                String Unit7= snapshot.child("first_sem/subject7/units").getValue().toString();
                SC7.setText(sub7);
                des7.setText(Des7);
                units7.setText(Unit7);

                String sub8= snapshot.child("first_sem/subject8/code").getValue().toString();
                String Des8= snapshot.child("first_sem/subject8/descriptive_title").getValue().toString();
                String Unit8= snapshot.child("first_sem/subject8/units").getValue().toString();
                SC8.setText(sub8);
                des8.setText(Des8);
                units8.setText(Unit8);

                String sub9= snapshot.child("first_sem/subject9/code").getValue().toString();
                String Des9= snapshot.child("first_sem/subject9/descriptive_title").getValue().toString();
                String Unit9= snapshot.child("first_sem/subject9/units").getValue().toString();
                SC9.setText(sub9);
                des9.setText(Des9);
                units9.setText(Unit9);

                String sub10= snapshot.child("first_sem/subject10/code").getValue().toString();
                String Des10= snapshot.child("first_sem/subject10/descriptive_title").getValue().toString();
                String Unit10= snapshot.child("first_sem/subject10/units").getValue().toString();
                SC10.setText(sub10);
                des10.setText(Des10);
                units10.setText(Unit10);





            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}

