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

public class viewing_grades3 extends AppCompatActivity{
    String[] sem ={"1st Semester", "2nd Semester"};
    ArrayAdapter<String> semesterlist;
    private ImageButton back;
    private Button firstyear, secondyear,thirdyear,fourthyear;
    private TextView course;
    private String userID;
    private DatabaseReference databaseRef;
    private FirebaseUser user;
    private  Spinner semester;
    private String Course=null;
    private TextView des1, des2, des3, des4, des5, des6, des7, des8, des9, des10, schoolyear;
    private TextView units1, units2, units3, units4, units5, units6, units7, units8, units9, units10;
    private TextView g1, g2, g3, g4, g5, g6, g7, g8, g9, g10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewing_grades3);
        user = FirebaseAuth.getInstance().getCurrentUser();
        semester=findViewById(R.id.semestergrades);

        firstyear=findViewById(R.id.firstyear);
        secondyear=findViewById(R.id.secondyear);
        thirdyear=findViewById(R.id.thirdyear);
        fourthyear=findViewById(R.id.fourthyear);

        back=findViewById(R.id.backs);


        des1 = findViewById(R.id.d1);
        des2 = findViewById(R.id.d2);
        des3 = findViewById(R.id.d3);
        des4 = findViewById(R.id.d4);
        des5 = findViewById(R.id.d5);
        des6 = findViewById(R.id.d6);
        des7 = findViewById(R.id.d7);
        des8 = findViewById(R.id.d8);
        des9 = findViewById(R.id.d9);
        des10 = findViewById(R.id.d10);

        units1 = findViewById(R.id.u1);
        units2 = findViewById(R.id.u2);
        units3 = findViewById(R.id.u3);
        units4 = findViewById(R.id.u4);
        units5 = findViewById(R.id.u5);
        units6 = findViewById(R.id.u6);
        units7 = findViewById(R.id.u7);
        units8 = findViewById(R.id.u8);
        units9 = findViewById(R.id.u9);
        units10 = findViewById(R.id.u10);


        g1 = findViewById(R.id.g1);
        g2 = findViewById(R.id.g2);
        g3 = findViewById(R.id.g3);
        g4 = findViewById(R.id.g4);
        g5 = findViewById(R.id.g5);
        g6 = findViewById(R.id.g6);
        g7 = findViewById(R.id.g7);
        g8 = findViewById(R.id.g8);
        g9 = findViewById(R.id.g9);
        g10 = findViewById(R.id.g10);

        ///// spinner //////
        semesterlist=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, sem);
        semester.setAdapter(semesterlist);

        /////database //////
        user = FirebaseAuth.getInstance().getCurrentUser();
        databaseRef = FirebaseDatabase.getInstance().getReference("User");
        userID = user.getUid();

        databaseRef.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Course = snapshot.child("Course").getValue().toString();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        semester.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                switch (item) {
                    case "1st Semester":
                        databaseRef.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                String Course = snapshot.child("Course").getValue().toString();

                                if (Course.equals("Bachelor of Science in Computer Science")) {
                                    BSCS1y1sem();

                                }else if (Course.equals("Bachelor of Elementary Education")){
                                    BEED1y1sem();
                                }else if (Course.equals("Bachelor of Secondary Education in English")){
                                    BSE_E1y1sem();
                                }else if (Course.equals("Bachelor of Secondary Education in Science")){
                                    BSE_S1y1sem();
                                }
                                else if (Course.equals("Bachelor of Science in Office Administration")){
                                    BSOA1y1sem();
                                }  else if (Course.equals("Bachelor of Science in Business Administration")){
                                    BSBA1y1sem();
                                }else if (Course.equals("Bachelor of Secondary Education in Math")){
                                    BSE_M1y1sem();
                                }else if (Course.equals("Bachelor of Arts in Religious Education")){
                                    ABREED1y1sem();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                        break;
                    case "2nd Semester":
                        databaseRef.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                String Course = snapshot.child("Course").getValue().toString();

                                if (Course.equals("Bachelor of Science in Computer Science")) {
                                    BSCS1y2sem();

                                }else if (Course.equals("Bachelor of Elementary Education")){
                                    BEED1y2sem();
                                }else if (Course.equals("Bachelor of Secondary Education in English")){
                                    BSE_E1y2sem();
                                }else if (Course.equals("Bachelor of Secondary Education in Science")){
                                    BSE_S1y2sem();
                                }
                                else if (Course.equals("Bachelor of Science in Office Administration")){
                                    BSOA1y2sem();
                                }  else if (Course.equals("Bachelor of Science in Business Administration")){
                                    BSBA1y2sem();
                                }else if (Course.equals("Bachelor of Secondary Education in Math")){
                                    BSE_M1y2sem();
                                }else if (Course.equals("Bachelor of Arts in Religious Education")){
                                    ABREED1y2sem();
                                }

                            }


                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                        break;

                    default:
                        Toast.makeText(viewing_grades3.this, "Sorry something wrong happened", Toast.LENGTH_SHORT).show();

                }


            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        ////// //back to homepage///////

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(viewing_grades3.this, Home_Page.class));
            }
        });

        ArrayAdapter adapter =new ArrayAdapter(this, android.R.layout.simple_spinner_item,sem);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        semester.setAdapter(adapter);


        ////// //Year buttons///////

        secondyear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(viewing_grades3.this,viewing_grades2.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

            }
        });
        firstyear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(viewing_grades3.this,ViewingGrades.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        fourthyear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(viewing_grades3.this,viewing_grades4.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });


    }



    public void  BSCS1y1sem(){
        /////database //////
        databaseRef.child(userID).child("grades").child("third_year").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("first_sem/grade01_31").getValue().toString();
                String Des1 = snapshot.child("first_sem/des1_31").getValue().toString();
                String Unit = snapshot.child("first_sem/unit1_31").getValue().toString();
                g1.setText(sub1);
                des1.setText(Des1);
                units1.setText(Unit);

                String sub2 = snapshot.child("first_sem/grade02_31").getValue().toString();
                String Des2 = snapshot.child("first_sem/des2_31").getValue().toString();
                String Unit2 = snapshot.child("first_sem/unit2_31").getValue().toString();
                g2.setText(sub2);
                des2.setText(Des2);
                units2.setText(Unit2);

                String sub3 = snapshot.child("first_sem/grade03_31").getValue().toString();
                String Des3 = snapshot.child("first_sem/des3_31").getValue().toString();
                String Unit3 = snapshot.child("first_sem/unit3_31").getValue().toString();
                g3.setText(sub3);
                des3.setText(Des3);
                units3.setText(Unit3);

                String sub4 = snapshot.child("first_sem/grade04_31").getValue().toString();
                String Des4 = snapshot.child("first_sem/des4_31").getValue().toString();
                String Unit4 = snapshot.child("first_sem/unit4_31").getValue().toString();
                g4.setText(sub4);
                des4.setText(Des4);
                units4.setText(Unit4);

                String sub5 = snapshot.child("first_sem/grade05_31").getValue().toString();
                String Des5 = snapshot.child("first_sem/des5_31").getValue().toString();
                String Unit5 = snapshot.child("first_sem/unit5_31").getValue().toString();
                g5.setText(sub5);
                des5.setText(Des5);
                units5.setText(Unit5);

                String sub6 = snapshot.child("first_sem/grade06_31").getValue().toString();
                String Des6 = snapshot.child("first_sem/des6_31").getValue().toString();
                String Unit6 = snapshot.child("first_sem/unit6_31").getValue().toString();
                g6.setText(sub6);
                des6.setText(Des6);
                units6.setText(Unit6);

                String sub7 = snapshot.child("first_sem/grade07_31").getValue().toString();
                String Des7 = snapshot.child("first_sem/des7_31").getValue().toString();
                String Unit7 = snapshot.child("first_sem/unit7_31").getValue().toString();
                g7.setText(sub7);
                des7.setText(Des7);
                units7.setText(Unit7);

                String sub8 = snapshot.child("first_sem/grade08_31").getValue().toString();
                String Des8 = snapshot.child("first_sem/des8_31").getValue().toString();
                String Unit8 = snapshot.child("first_sem/unit8_31").getValue().toString();
                g8.setText(sub8);
                des8.setText(Des8);
                units8.setText(Unit8);

                String sub9 = snapshot.child("first_sem/grade09_31").getValue().toString();
                String Des9 = snapshot.child("first_sem/des9_31").getValue().toString();
                String Unit9 = snapshot.child("first_sem/unit9_31").getValue().toString();
                g9.setText(sub9);
                des9.setText(Des9);
                units9.setText(Unit9);

                String sub10 = snapshot.child("first_sem/grade10_31").getValue().toString();
                String Des10 = snapshot.child("first_sem/des10_31").getValue().toString();
                String Unit10 = snapshot.child("first_sem/unit10_31").getValue().toString();
                g10.setText(sub10);
                des10.setText(Des10);
                units10.setText(Unit10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    public void  BSCS1y2sem(){

        databaseRef.child(userID).child("grades").child("third_year").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //Introduction to Computing
                String sub1 = snapshot.child("second_sem/grade01_32").getValue().toString();
                String Des1 = snapshot.child("second_sem/des1_32").getValue().toString();
                String Unit = snapshot.child("second_sem/unit1_32").getValue().toString();
                g1.setText(sub1);
                des1.setText(Des1);
                units1.setText(Unit);

                String sub2 = snapshot.child("second_sem/grade02_32").getValue().toString();
                String Des2 = snapshot.child("second_sem/des2_32").getValue().toString();
                String Unit2 = snapshot.child("second_sem/unit2_32").getValue().toString();
                g2.setText(sub2);
                des2.setText(Des2);
                units2.setText(Unit2);

                String sub3 = snapshot.child("second_sem/grade03_32").getValue().toString();
                String Des3 = snapshot.child("second_sem/des3_32").getValue().toString();
                String Unit3 = snapshot.child("second_sem/unit3_32").getValue().toString();
                g3.setText(sub3);
                des3.setText(Des3);
                units3.setText(Unit3);

                String sub4 = snapshot.child("second_sem/grade04_32").getValue().toString();
                String Des4 = snapshot.child("second_sem/des4_32").getValue().toString();
                String Unit4 = snapshot.child("second_sem/unit4_32").getValue().toString();
                g4.setText(sub4);
                des4.setText(Des4);
                units4.setText(Unit4);

                String sub5= snapshot.child("second_sem/grade05_32").getValue().toString();
                String Des5 = snapshot.child("second_sem/des5_32").getValue().toString();
                String Unit5 = snapshot.child("second_sem/unit5_32").getValue().toString();
                g5.setText(sub5);
                des5.setText(Des5);
                units5.setText(Unit5);

                String sub6 = snapshot.child("second_sem/grade06_32").getValue().toString();
                String Des6 = snapshot.child("second_sem/des6_32").getValue().toString();
                String Unit6 = snapshot.child("second_sem/unit6_32").getValue().toString();
                g6.setText(sub6);
                des6.setText(Des6);
                units6.setText(Unit6);

                String sub7 = snapshot.child("second_sem/grade07_32").getValue().toString();
                String Des7 = snapshot.child("second_sem/des7_32").getValue().toString();
                String Unit7 = snapshot.child("second_sem/unit7_32").getValue().toString();
                g7.setText(sub7);
                des7.setText(Des7);
                units7.setText(Unit7);

                String sub8 = snapshot.child("second_sem/grade08_32").getValue().toString();
                String Des8= snapshot.child("second_sem/des8_32").getValue().toString();
                String Unit8 = snapshot.child("second_sem/unit8_32").getValue().toString();
                g8.setText(sub8);
                des8.setText(Des8);
                units8.setText(Unit8);

                String sub9 = snapshot.child("second_sem/grade09_32").getValue().toString();
                String Des9 = snapshot.child("second_sem/des9_32").getValue().toString();
                String Unit9 = snapshot.child("second_sem/unit9_32").getValue().toString();
                g9.setText(sub9);
                des9.setText(Des9);
                units9.setText(Unit9);

                String sub10 = snapshot.child("second_sem/grade10_32").getValue().toString();
                String Des10 = snapshot.child("second_sem/des10_32").getValue().toString();
                String Unit10 = snapshot.child("second_sem/unit10_32").getValue().toString();
                g10.setText(sub10);
                des10.setText(Des10);
                units10.setText(Unit10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void  BEED1y1sem(){
        //

        databaseRef.child(userID).child("grades").child("third_year").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("first_sem/grade01_31").getValue().toString();
                String Des1 = snapshot.child("first_sem/des1_31").getValue().toString();
                String Unit = snapshot.child("first_sem/unit1_31").getValue().toString();
                g1.setText(sub1);
                des1.setText(Des1);
                units1.setText(Unit);

                String sub2 = snapshot.child("first_sem/grade02_31").getValue().toString();
                String Des2 = snapshot.child("first_sem/des2_31").getValue().toString();
                String Unit2 = snapshot.child("first_sem/unit2_31").getValue().toString();
                g2.setText(sub2);
                des2.setText(Des2);
                units2.setText(Unit2);

                String sub3 = snapshot.child("first_sem/grade03_31").getValue().toString();
                String Des3 = snapshot.child("first_sem/des3_31").getValue().toString();
                String Unit3 = snapshot.child("first_sem/unit3_31").getValue().toString();
                g3.setText(sub3);
                des3.setText(Des3);
                units3.setText(Unit3);

                String sub4 = snapshot.child("first_sem/grade04_31").getValue().toString();
                String Des4 = snapshot.child("first_sem/des4_31").getValue().toString();
                String Unit4 = snapshot.child("first_sem/unit4_31").getValue().toString();
                g4.setText(sub4);
                des4.setText(Des4);
                units4.setText(Unit4);

                String sub5 = snapshot.child("first_sem/grade05_31").getValue().toString();
                String Des5 = snapshot.child("first_sem/des5_31").getValue().toString();
                String Unit5 = snapshot.child("first_sem/unit5_31").getValue().toString();
                g5.setText(sub5);
                des5.setText(Des5);
                units5.setText(Unit5);

                String sub6 = snapshot.child("first_sem/grade06_31").getValue().toString();
                String Des6 = snapshot.child("first_sem/des6_31").getValue().toString();
                String Unit6 = snapshot.child("first_sem/unit6_31").getValue().toString();
                g6.setText(sub6);
                des6.setText(Des6);
                units6.setText(Unit6);

                String sub7 = snapshot.child("first_sem/grade07_31").getValue().toString();
                String Des7 = snapshot.child("first_sem/des7_31").getValue().toString();
                String Unit7 = snapshot.child("first_sem/unit7_31").getValue().toString();
                g7.setText(sub7);
                des7.setText(Des7);
                units7.setText(Unit7);

                String sub8 = snapshot.child("first_sem/grade08_31").getValue().toString();
                String Des8 = snapshot.child("first_sem/des8_31").getValue().toString();
                String Unit8 = snapshot.child("first_sem/unit8_31").getValue().toString();
                g8.setText(sub8);
                des8.setText(Des8);
                units8.setText(Unit8);

                String sub9 = snapshot.child("first_sem/grade09_31").getValue().toString();
                String Des9 = snapshot.child("first_sem/des9_31").getValue().toString();
                String Unit9 = snapshot.child("first_sem/unit9_31").getValue().toString();
                g9.setText(sub9);
                des9.setText(Des9);
                units9.setText(Unit9);

                String sub10 = snapshot.child("first_sem/grade10_31").getValue().toString();
                String Des10 = snapshot.child("first_sem/des10_31").getValue().toString();
                String Unit10 = snapshot.child("first_sem/unit10_31").getValue().toString();
                g10.setText(sub10);
                des10.setText(Des10);
                units10.setText(Unit10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    public void  BEED1y2sem(){
        //
        databaseRef.child(userID).child("grades").child("third_year").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("second_sem/grade01_32").getValue().toString();
                String Des1 = snapshot.child("second_sem/des1_32").getValue().toString();
                String Unit = snapshot.child("second_sem/unit1_32").getValue().toString();
                g1.setText(sub1);
                des1.setText(Des1);
                units1.setText(Unit);

                String sub2 = snapshot.child("second_sem/grade02_32").getValue().toString();
                String Des2 = snapshot.child("second_sem/des2_32").getValue().toString();
                String Unit2 = snapshot.child("second_sem/unit2_32").getValue().toString();
                g2.setText(sub2);
                des2.setText(Des2);
                units2.setText(Unit2);

                String sub3 = snapshot.child("second_sem/grade03_32").getValue().toString();
                String Des3 = snapshot.child("second_sem/des3_32").getValue().toString();
                String Unit3 = snapshot.child("second_sem/unit3_32").getValue().toString();
                g3.setText(sub3);
                des3.setText(Des3);
                units3.setText(Unit3);

                String sub4 = snapshot.child("second_sem/grade04_32").getValue().toString();
                String Des4 = snapshot.child("second_sem/des4_32").getValue().toString();
                String Unit4 = snapshot.child("second_sem/unit4_32").getValue().toString();
                g4.setText(sub4);
                des4.setText(Des4);
                units4.setText(Unit4);

                String sub5= snapshot.child("second_sem/grade05_32").getValue().toString();
                String Des5 = snapshot.child("second_sem/des5_32").getValue().toString();
                String Unit5 = snapshot.child("second_sem/unit5_32").getValue().toString();
                g5.setText(sub5);
                des5.setText(Des5);
                units5.setText(Unit5);

                String sub6 = snapshot.child("second_sem/grade06_32").getValue().toString();
                String Des6 = snapshot.child("second_sem/des6_32").getValue().toString();
                String Unit6 = snapshot.child("second_sem/unit6_32").getValue().toString();
                g6.setText(sub6);
                des6.setText(Des6);
                units6.setText(Unit6);

                String sub7 = snapshot.child("second_sem/grade07_32").getValue().toString();
                String Des7 = snapshot.child("second_sem/des7_32").getValue().toString();
                String Unit7 = snapshot.child("second_sem/unit7_32").getValue().toString();
                g7.setText(sub7);
                des7.setText(Des7);
                units7.setText(Unit7);

                String sub8 = snapshot.child("second_sem/grade08_32").getValue().toString();
                String Des8= snapshot.child("second_sem/des8_32").getValue().toString();
                String Unit8 = snapshot.child("second_sem/unit8_32").getValue().toString();
                g8.setText(sub8);
                des8.setText(Des8);
                units8.setText(Unit8);

                String sub9 = snapshot.child("second_sem/grade09_32").getValue().toString();
                String Des9 = snapshot.child("second_sem/des9_32").getValue().toString();
                String Unit9 = snapshot.child("second_sem/unit9_32").getValue().toString();
                g9.setText(sub9);
                des9.setText(Des9);
                units9.setText(Unit9);

                String sub10 = snapshot.child("second_sem/grade10_32").getValue().toString();
                String Des10 = snapshot.child("second_sem/des10_32").getValue().toString();
                String Unit10 = snapshot.child("second_sem/unit10_32").getValue().toString();
                g10.setText(sub10);
                des10.setText(Des10);
                units10.setText(Unit10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    public void  BSBA1y1sem(){
        //

        databaseRef.child(userID).child("grades").child("third_year").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                String sub1 = snapshot.child("first_sem/grade01_31").getValue().toString();
                String Des1 = snapshot.child("first_sem/des1_31").getValue().toString();
                String Unit = snapshot.child("first_sem/unit1_31").getValue().toString();
                g1.setText(sub1);
                des1.setText(Des1);
                units1.setText(Unit);

                String sub2 = snapshot.child("first_sem/grade02_31").getValue().toString();
                String Des2 = snapshot.child("first_sem/des2_31").getValue().toString();
                String Unit2 = snapshot.child("first_sem/unit2_31").getValue().toString();
                g2.setText(sub2);
                des2.setText(Des2);
                units2.setText(Unit2);

                String sub3 = snapshot.child("first_sem/grade03_31").getValue().toString();
                String Des3 = snapshot.child("first_sem/des3_31").getValue().toString();
                String Unit3 = snapshot.child("first_sem/unit3_31").getValue().toString();
                g3.setText(sub3);
                des3.setText(Des3);
                units3.setText(Unit3);

                String sub4 = snapshot.child("first_sem/grade04_31").getValue().toString();
                String Des4 = snapshot.child("first_sem/des4_31").getValue().toString();
                String Unit4 = snapshot.child("first_sem/unit4_31").getValue().toString();
                g4.setText(sub4);
                des4.setText(Des4);
                units4.setText(Unit4);

                String sub5 = snapshot.child("first_sem/grade05_31").getValue().toString();
                String Des5 = snapshot.child("first_sem/des5_31").getValue().toString();
                String Unit5 = snapshot.child("first_sem/unit5_31").getValue().toString();
                g5.setText(sub5);
                des5.setText(Des5);
                units5.setText(Unit5);

                String sub6 = snapshot.child("first_sem/grade06_31").getValue().toString();
                String Des6 = snapshot.child("first_sem/des6_31").getValue().toString();
                String Unit6 = snapshot.child("first_sem/unit6_31").getValue().toString();
                g6.setText(sub6);
                des6.setText(Des6);
                units6.setText(Unit6);

                String sub7 = snapshot.child("first_sem/grade07_31").getValue().toString();
                String Des7 = snapshot.child("first_sem/des7_31").getValue().toString();
                String Unit7 = snapshot.child("first_sem/unit7_31").getValue().toString();
                g7.setText(sub7);
                des7.setText(Des7);
                units7.setText(Unit7);

                String sub8 = snapshot.child("first_sem/grade08_31").getValue().toString();
                String Des8 = snapshot.child("first_sem/des8_31").getValue().toString();
                String Unit8 = snapshot.child("first_sem/unit8_31").getValue().toString();
                g8.setText(sub8);
                des8.setText(Des8);
                units8.setText(Unit8);

                String sub9 = snapshot.child("first_sem/grade09_31").getValue().toString();
                String Des9 = snapshot.child("first_sem/des9_31").getValue().toString();
                String Unit9 = snapshot.child("first_sem/unit9_31").getValue().toString();
                g9.setText(sub9);
                des9.setText(Des9);
                units9.setText(Unit9);

                String sub10 = snapshot.child("first_sem/grade10_31").getValue().toString();
                String Des10 = snapshot.child("first_sem/des10_31").getValue().toString();
                String Unit10 = snapshot.child("first_sem/unit10_31").getValue().toString();
                g10.setText(sub10);
                des10.setText(Des10);
                units10.setText(Unit10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void  BSBA1y2sem(){
        //

        databaseRef.child(userID).child("grades").child("third_year").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("second_sem/grade01_32").getValue().toString();
                String Des1 = snapshot.child("second_sem/des1_32").getValue().toString();
                String Unit = snapshot.child("second_sem/unit1_32").getValue().toString();
                g1.setText(sub1);
                des1.setText(Des1);
                units1.setText(Unit);

                String sub2 = snapshot.child("second_sem/grade02_32").getValue().toString();
                String Des2 = snapshot.child("second_sem/des2_32").getValue().toString();
                String Unit2 = snapshot.child("second_sem/unit2_32").getValue().toString();
                g2.setText(sub2);
                des2.setText(Des2);
                units2.setText(Unit2);

                String sub3 = snapshot.child("second_sem/grade03_32").getValue().toString();
                String Des3 = snapshot.child("second_sem/des3_32").getValue().toString();
                String Unit3 = snapshot.child("second_sem/unit3_32").getValue().toString();
                g3.setText(sub3);
                des3.setText(Des3);
                units3.setText(Unit3);

                String sub4 = snapshot.child("second_sem/grade04_32").getValue().toString();
                String Des4 = snapshot.child("second_sem/des4_32").getValue().toString();
                String Unit4 = snapshot.child("second_sem/unit4_32").getValue().toString();
                g4.setText(sub4);
                des4.setText(Des4);
                units4.setText(Unit4);

                String sub5= snapshot.child("second_sem/grade05_32").getValue().toString();
                String Des5 = snapshot.child("second_sem/des5_32").getValue().toString();
                String Unit5 = snapshot.child("second_sem/unit5_32").getValue().toString();
                g5.setText(sub5);
                des5.setText(Des5);
                units5.setText(Unit5);

                String sub6 = snapshot.child("second_sem/grade06_32").getValue().toString();
                String Des6 = snapshot.child("second_sem/des6_32").getValue().toString();
                String Unit6 = snapshot.child("second_sem/unit6_32").getValue().toString();
                g6.setText(sub6);
                des6.setText(Des6);
                units6.setText(Unit6);

                String sub7 = snapshot.child("second_sem/grade07_32").getValue().toString();
                String Des7 = snapshot.child("second_sem/des7_32").getValue().toString();
                String Unit7 = snapshot.child("second_sem/unit7_32").getValue().toString();
                g7.setText(sub7);
                des7.setText(Des7);
                units7.setText(Unit7);

                String sub8 = snapshot.child("second_sem/grade08_32").getValue().toString();
                String Des8= snapshot.child("second_sem/des8_32").getValue().toString();
                String Unit8 = snapshot.child("second_sem/unit8_32").getValue().toString();
                g8.setText(sub8);
                des8.setText(Des8);
                units8.setText(Unit8);

                String sub9 = snapshot.child("second_sem/grade09_32").getValue().toString();
                String Des9 = snapshot.child("second_sem/des9_32").getValue().toString();
                String Unit9 = snapshot.child("second_sem/unit9_32").getValue().toString();
                g9.setText(sub9);
                des9.setText(Des9);
                units9.setText(Unit9);

                String sub10 = snapshot.child("second_sem/grade10_32").getValue().toString();
                String Des10 = snapshot.child("second_sem/des10_32").getValue().toString();
                String Unit10 = snapshot.child("second_sem/unit10_32").getValue().toString();
                g10.setText(sub10);
                des10.setText(Des10);
                units10.setText(Unit10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void  BSOA1y1sem(){
        //
        databaseRef.child(userID).child("grades").child("third_year").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                String sub1 = snapshot.child("first_sem/grade01_31").getValue().toString();
                String Des1 = snapshot.child("first_sem/des1_31").getValue().toString();
                String Unit = snapshot.child("first_sem/unit1_31").getValue().toString();
                g1.setText(sub1);
                des1.setText(Des1);
                units1.setText(Unit);

                String sub2 = snapshot.child("first_sem/grade02_31").getValue().toString();
                String Des2 = snapshot.child("first_sem/des2_31").getValue().toString();
                String Unit2 = snapshot.child("first_sem/unit2_31").getValue().toString();
                g2.setText(sub2);
                des2.setText(Des2);
                units2.setText(Unit2);

                String sub3 = snapshot.child("first_sem/grade03_31").getValue().toString();
                String Des3 = snapshot.child("first_sem/des3_31").getValue().toString();
                String Unit3 = snapshot.child("first_sem/unit3_31").getValue().toString();
                g3.setText(sub3);
                des3.setText(Des3);
                units3.setText(Unit3);

                String sub4 = snapshot.child("first_sem/grade04_31").getValue().toString();
                String Des4 = snapshot.child("first_sem/des4_31").getValue().toString();
                String Unit4 = snapshot.child("first_sem/unit4_31").getValue().toString();
                g4.setText(sub4);
                des4.setText(Des4);
                units4.setText(Unit4);

                String sub5 = snapshot.child("first_sem/grade05_31").getValue().toString();
                String Des5 = snapshot.child("first_sem/des5_31").getValue().toString();
                String Unit5 = snapshot.child("first_sem/unit5_31").getValue().toString();
                g5.setText(sub5);
                des5.setText(Des5);
                units5.setText(Unit5);

                String sub6 = snapshot.child("first_sem/grade06_31").getValue().toString();
                String Des6 = snapshot.child("first_sem/des6_31").getValue().toString();
                String Unit6 = snapshot.child("first_sem/unit6_31").getValue().toString();
                g6.setText(sub6);
                des6.setText(Des6);
                units6.setText(Unit6);

                String sub7 = snapshot.child("first_sem/grade07_31").getValue().toString();
                String Des7 = snapshot.child("first_sem/des7_31").getValue().toString();
                String Unit7 = snapshot.child("first_sem/unit7_31").getValue().toString();
                g7.setText(sub7);
                des7.setText(Des7);
                units7.setText(Unit7);

                String sub8 = snapshot.child("first_sem/grade08_31").getValue().toString();
                String Des8 = snapshot.child("first_sem/des8_31").getValue().toString();
                String Unit8 = snapshot.child("first_sem/unit8_31").getValue().toString();
                g8.setText(sub8);
                des8.setText(Des8);
                units8.setText(Unit8);

                String sub9 = snapshot.child("first_sem/grade09_31").getValue().toString();
                String Des9 = snapshot.child("first_sem/des9_31").getValue().toString();
                String Unit9 = snapshot.child("first_sem/unit9_31").getValue().toString();
                g9.setText(sub9);
                des9.setText(Des9);
                units9.setText(Unit9);

                String sub10 = snapshot.child("first_sem/grade10_31").getValue().toString();
                String Des10 = snapshot.child("first_sem/des10_31").getValue().toString();
                String Unit10 = snapshot.child("first_sem/unit10_31").getValue().toString();
                g10.setText(sub10);
                des10.setText(Des10);
                units10.setText(Unit10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void  BSOA1y2sem(){
        //
        databaseRef.child(userID).child("grades").child("third_year").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("second_sem/grade01_32").getValue().toString();
                String Des1 = snapshot.child("second_sem/des1_32").getValue().toString();
                String Unit = snapshot.child("second_sem/unit1_32").getValue().toString();
                g1.setText(sub1);
                des1.setText(Des1);
                units1.setText(Unit);

                String sub2 = snapshot.child("second_sem/grade02_32").getValue().toString();
                String Des2 = snapshot.child("second_sem/des2_32").getValue().toString();
                String Unit2 = snapshot.child("second_sem/unit2_32").getValue().toString();
                g2.setText(sub2);
                des2.setText(Des2);
                units2.setText(Unit2);

                String sub3 = snapshot.child("second_sem/grade03_32").getValue().toString();
                String Des3 = snapshot.child("second_sem/des3_32").getValue().toString();
                String Unit3 = snapshot.child("second_sem/unit3_32").getValue().toString();
                g3.setText(sub3);
                des3.setText(Des3);
                units3.setText(Unit3);

                String sub4 = snapshot.child("second_sem/grade04_32").getValue().toString();
                String Des4 = snapshot.child("second_sem/des4_32").getValue().toString();
                String Unit4 = snapshot.child("second_sem/unit4_32").getValue().toString();
                g4.setText(sub4);
                des4.setText(Des4);
                units4.setText(Unit4);

                String sub5= snapshot.child("second_sem/grade05_32").getValue().toString();
                String Des5 = snapshot.child("second_sem/des5_32").getValue().toString();
                String Unit5 = snapshot.child("second_sem/unit5_32").getValue().toString();
                g5.setText(sub5);
                des5.setText(Des5);
                units5.setText(Unit5);

                String sub6 = snapshot.child("second_sem/grade06_32").getValue().toString();
                String Des6 = snapshot.child("second_sem/des6_32").getValue().toString();
                String Unit6 = snapshot.child("second_sem/unit6_32").getValue().toString();
                g6.setText(sub6);
                des6.setText(Des6);
                units6.setText(Unit6);

                String sub7 = snapshot.child("second_sem/grade07_32").getValue().toString();
                String Des7 = snapshot.child("second_sem/des7_32").getValue().toString();
                String Unit7 = snapshot.child("second_sem/unit7_32").getValue().toString();
                g7.setText(sub7);
                des7.setText(Des7);
                units7.setText(Unit7);

                String sub8 = snapshot.child("second_sem/grade08_32").getValue().toString();
                String Des8= snapshot.child("second_sem/des8_32").getValue().toString();
                String Unit8 = snapshot.child("second_sem/unit8_32").getValue().toString();
                g8.setText(sub8);
                des8.setText(Des8);
                units8.setText(Unit8);

                String sub9 = snapshot.child("second_sem/grade09_32").getValue().toString();
                String Des9 = snapshot.child("second_sem/des9_32").getValue().toString();
                String Unit9 = snapshot.child("second_sem/unit9_32").getValue().toString();
                g9.setText(sub9);
                des9.setText(Des9);
                units9.setText(Unit9);

                String sub10 = snapshot.child("second_sem/grade10_32").getValue().toString();
                String Des10 = snapshot.child("second_sem/des10_32").getValue().toString();
                String Unit10 = snapshot.child("second_sem/unit10_32").getValue().toString();
                g10.setText(sub10);
                des10.setText(Des10);
                units10.setText(Unit10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void  ABREED1y1sem(){
        //
        databaseRef.child(userID).child("grades").child("third_year").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("first_sem/grade01_31").getValue().toString();
                String Des1 = snapshot.child("first_sem/des1_31").getValue().toString();
                String Unit = snapshot.child("first_sem/unit1_31").getValue().toString();
                g1.setText(sub1);
                des1.setText(Des1);
                units1.setText(Unit);

                String sub2 = snapshot.child("first_sem/grade02_31").getValue().toString();
                String Des2 = snapshot.child("first_sem/des2_31").getValue().toString();
                String Unit2 = snapshot.child("first_sem/unit2_31").getValue().toString();
                g2.setText(sub2);
                des2.setText(Des2);
                units2.setText(Unit2);

                String sub3 = snapshot.child("first_sem/grade03_31").getValue().toString();
                String Des3 = snapshot.child("first_sem/des3_31").getValue().toString();
                String Unit3 = snapshot.child("first_sem/unit3_31").getValue().toString();
                g3.setText(sub3);
                des3.setText(Des3);
                units3.setText(Unit3);

                String sub4 = snapshot.child("first_sem/grade04_31").getValue().toString();
                String Des4 = snapshot.child("first_sem/des4_31").getValue().toString();
                String Unit4 = snapshot.child("first_sem/unit4_31").getValue().toString();
                g4.setText(sub4);
                des4.setText(Des4);
                units4.setText(Unit4);

                String sub5 = snapshot.child("first_sem/grade05_31").getValue().toString();
                String Des5 = snapshot.child("first_sem/des5_31").getValue().toString();
                String Unit5 = snapshot.child("first_sem/unit5_31").getValue().toString();
                g5.setText(sub5);
                des5.setText(Des5);
                units5.setText(Unit5);

                String sub6 = snapshot.child("first_sem/grade06_31").getValue().toString();
                String Des6 = snapshot.child("first_sem/des6_31").getValue().toString();
                String Unit6 = snapshot.child("first_sem/unit6_31").getValue().toString();
                g6.setText(sub6);
                des6.setText(Des6);
                units6.setText(Unit6);

                String sub7 = snapshot.child("first_sem/grade07_31").getValue().toString();
                String Des7 = snapshot.child("first_sem/des7_31").getValue().toString();
                String Unit7 = snapshot.child("first_sem/unit7_31").getValue().toString();
                g7.setText(sub7);
                des7.setText(Des7);
                units7.setText(Unit7);

                String sub8 = snapshot.child("first_sem/grade08_31").getValue().toString();
                String Des8 = snapshot.child("first_sem/des8_31").getValue().toString();
                String Unit8 = snapshot.child("first_sem/unit8_31").getValue().toString();
                g8.setText(sub8);
                des8.setText(Des8);
                units8.setText(Unit8);

                String sub9 = snapshot.child("first_sem/grade09_31").getValue().toString();
                String Des9 = snapshot.child("first_sem/des9_31").getValue().toString();
                String Unit9 = snapshot.child("first_sem/unit9_31").getValue().toString();
                g9.setText(sub9);
                des9.setText(Des9);
                units9.setText(Unit9);

                String sub10 = snapshot.child("first_sem/grade10_31").getValue().toString();
                String Des10 = snapshot.child("first_sem/des10_31").getValue().toString();
                String Unit10 = snapshot.child("first_sem/unit10_31").getValue().toString();
                g10.setText(sub10);
                des10.setText(Des10);
                units10.setText(Unit10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void  ABREED1y2sem(){
        //
        databaseRef.child(userID).child("grades").child("third_year").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("second_sem/grade01_32").getValue().toString();
                String Des1 = snapshot.child("second_sem/des1_32").getValue().toString();
                String Unit = snapshot.child("second_sem/unit1_32").getValue().toString();
                g1.setText(sub1);
                des1.setText(Des1);
                units1.setText(Unit);

                String sub2 = snapshot.child("second_sem/grade02_32").getValue().toString();
                String Des2 = snapshot.child("second_sem/des2_32").getValue().toString();
                String Unit2 = snapshot.child("second_sem/unit2_32").getValue().toString();
                g2.setText(sub2);
                des2.setText(Des2);
                units2.setText(Unit2);

                String sub3 = snapshot.child("second_sem/grade03_32").getValue().toString();
                String Des3 = snapshot.child("second_sem/des3_32").getValue().toString();
                String Unit3 = snapshot.child("second_sem/unit3_32").getValue().toString();
                g3.setText(sub3);
                des3.setText(Des3);
                units3.setText(Unit3);

                String sub4 = snapshot.child("second_sem/grade04_32").getValue().toString();
                String Des4 = snapshot.child("second_sem/des4_32").getValue().toString();
                String Unit4 = snapshot.child("second_sem/unit4_32").getValue().toString();
                g4.setText(sub4);
                des4.setText(Des4);
                units4.setText(Unit4);

                String sub5= snapshot.child("second_sem/grade05_32").getValue().toString();
                String Des5 = snapshot.child("second_sem/des5_32").getValue().toString();
                String Unit5 = snapshot.child("second_sem/unit5_32").getValue().toString();
                g5.setText(sub5);
                des5.setText(Des5);
                units5.setText(Unit5);

                String sub6 = snapshot.child("second_sem/grade06_32").getValue().toString();
                String Des6 = snapshot.child("second_sem/des6_32").getValue().toString();
                String Unit6 = snapshot.child("second_sem/unit6_32").getValue().toString();
                g6.setText(sub6);
                des6.setText(Des6);
                units6.setText(Unit6);

                String sub7 = snapshot.child("second_sem/grade07_32").getValue().toString();
                String Des7 = snapshot.child("second_sem/des7_32").getValue().toString();
                String Unit7 = snapshot.child("second_sem/unit7_32").getValue().toString();
                g7.setText(sub7);
                des7.setText(Des7);
                units7.setText(Unit7);

                String sub8 = snapshot.child("second_sem/grade08_32").getValue().toString();
                String Des8= snapshot.child("second_sem/des8_32").getValue().toString();
                String Unit8 = snapshot.child("second_sem/unit8_32").getValue().toString();
                g8.setText(sub8);
                des8.setText(Des8);
                units8.setText(Unit8);

                String sub9 = snapshot.child("second_sem/grade09_32").getValue().toString();
                String Des9 = snapshot.child("second_sem/des9_32").getValue().toString();
                String Unit9 = snapshot.child("second_sem/unit9_32").getValue().toString();
                g9.setText(sub9);
                des9.setText(Des9);
                units9.setText(Unit9);

                String sub10 = snapshot.child("second_sem/grade10_32").getValue().toString();
                String Des10 = snapshot.child("second_sem/des10_32").getValue().toString();
                String Unit10 = snapshot.child("second_sem/unit10_32").getValue().toString();
                g10.setText(sub10);
                des10.setText(Des10);
                units10.setText(Unit10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void  BSE_M1y1sem(){
        //
        databaseRef.child(userID).child("grades").child("third_year").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                String sub1 = snapshot.child("first_sem/grade01_31").getValue().toString();
                String Des1 = snapshot.child("first_sem/des1_31").getValue().toString();
                String Unit = snapshot.child("first_sem/unit1_31").getValue().toString();
                g1.setText(sub1);
                des1.setText(Des1);
                units1.setText(Unit);

                String sub2 = snapshot.child("first_sem/grade02_31").getValue().toString();
                String Des2 = snapshot.child("first_sem/des2_31").getValue().toString();
                String Unit2 = snapshot.child("first_sem/unit2_31").getValue().toString();
                g2.setText(sub2);
                des2.setText(Des2);
                units2.setText(Unit2);

                String sub3 = snapshot.child("first_sem/grade03_31").getValue().toString();
                String Des3 = snapshot.child("first_sem/des3_31").getValue().toString();
                String Unit3 = snapshot.child("first_sem/unit3_31").getValue().toString();
                g3.setText(sub3);
                des3.setText(Des3);
                units3.setText(Unit3);

                String sub4 = snapshot.child("first_sem/grade04_31").getValue().toString();
                String Des4 = snapshot.child("first_sem/des4_31").getValue().toString();
                String Unit4 = snapshot.child("first_sem/unit4_31").getValue().toString();
                g4.setText(sub4);
                des4.setText(Des4);
                units4.setText(Unit4);

                String sub5 = snapshot.child("first_sem/grade05_31").getValue().toString();
                String Des5 = snapshot.child("first_sem/des5_31").getValue().toString();
                String Unit5 = snapshot.child("first_sem/unit5_31").getValue().toString();
                g5.setText(sub5);
                des5.setText(Des5);
                units5.setText(Unit5);

                String sub6 = snapshot.child("first_sem/grade06_31").getValue().toString();
                String Des6 = snapshot.child("first_sem/des6_31").getValue().toString();
                String Unit6 = snapshot.child("first_sem/unit6_31").getValue().toString();
                g6.setText(sub6);
                des6.setText(Des6);
                units6.setText(Unit6);

                String sub7 = snapshot.child("first_sem/grade07_31").getValue().toString();
                String Des7 = snapshot.child("first_sem/des7_31").getValue().toString();
                String Unit7 = snapshot.child("first_sem/unit7_31").getValue().toString();
                g7.setText(sub7);
                des7.setText(Des7);
                units7.setText(Unit7);

                String sub8 = snapshot.child("first_sem/grade08_31").getValue().toString();
                String Des8 = snapshot.child("first_sem/des8_31").getValue().toString();
                String Unit8 = snapshot.child("first_sem/unit8_31").getValue().toString();
                g8.setText(sub8);
                des8.setText(Des8);
                units8.setText(Unit8);

                String sub9 = snapshot.child("first_sem/grade09_31").getValue().toString();
                String Des9 = snapshot.child("first_sem/des9_31").getValue().toString();
                String Unit9 = snapshot.child("first_sem/unit9_31").getValue().toString();
                g9.setText(sub9);
                des9.setText(Des9);
                units9.setText(Unit9);

                String sub10 = snapshot.child("first_sem/grade10_31").getValue().toString();
                String Des10 = snapshot.child("first_sem/des10_31").getValue().toString();
                String Unit10 = snapshot.child("first_sem/unit10_31").getValue().toString();
                g10.setText(sub10);
                des10.setText(Des10);
                units10.setText(Unit10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void  BSE_M1y2sem(){
        //
        databaseRef.child(userID).child("grades").child("third_year").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("second_sem/grade01_32").getValue().toString();
                String Des1 = snapshot.child("second_sem/des1_32").getValue().toString();
                String Unit = snapshot.child("second_sem/unit1_32").getValue().toString();
                g1.setText(sub1);
                des1.setText(Des1);
                units1.setText(Unit);

                String sub2 = snapshot.child("second_sem/grade02_32").getValue().toString();
                String Des2 = snapshot.child("second_sem/des2_32").getValue().toString();
                String Unit2 = snapshot.child("second_sem/unit2_32").getValue().toString();
                g2.setText(sub2);
                des2.setText(Des2);
                units2.setText(Unit2);

                String sub3 = snapshot.child("second_sem/grade03_32").getValue().toString();
                String Des3 = snapshot.child("second_sem/des3_32").getValue().toString();
                String Unit3 = snapshot.child("second_sem/unit3_32").getValue().toString();
                g3.setText(sub3);
                des3.setText(Des3);
                units3.setText(Unit3);

                String sub4 = snapshot.child("second_sem/grade04_32").getValue().toString();
                String Des4 = snapshot.child("second_sem/des4_32").getValue().toString();
                String Unit4 = snapshot.child("second_sem/unit4_32").getValue().toString();
                g4.setText(sub4);
                des4.setText(Des4);
                units4.setText(Unit4);

                String sub5= snapshot.child("second_sem/grade05_32").getValue().toString();
                String Des5 = snapshot.child("second_sem/des5_32").getValue().toString();
                String Unit5 = snapshot.child("second_sem/unit5_32").getValue().toString();
                g5.setText(sub5);
                des5.setText(Des5);
                units5.setText(Unit5);

                String sub6 = snapshot.child("second_sem/grade06_32").getValue().toString();
                String Des6 = snapshot.child("second_sem/des6_32").getValue().toString();
                String Unit6 = snapshot.child("second_sem/unit6_32").getValue().toString();
                g6.setText(sub6);
                des6.setText(Des6);
                units6.setText(Unit6);

                String sub7 = snapshot.child("second_sem/grade07_32").getValue().toString();
                String Des7 = snapshot.child("second_sem/des7_32").getValue().toString();
                String Unit7 = snapshot.child("second_sem/unit7_32").getValue().toString();
                g7.setText(sub7);
                des7.setText(Des7);
                units7.setText(Unit7);

                String sub8 = snapshot.child("second_sem/grade08_32").getValue().toString();
                String Des8= snapshot.child("second_sem/des8_32").getValue().toString();
                String Unit8 = snapshot.child("second_sem/unit8_32").getValue().toString();
                g8.setText(sub8);
                des8.setText(Des8);
                units8.setText(Unit8);

                String sub9 = snapshot.child("second_sem/grade09_32").getValue().toString();
                String Des9 = snapshot.child("second_sem/des9_32").getValue().toString();
                String Unit9 = snapshot.child("second_sem/unit9_32").getValue().toString();
                g9.setText(sub9);
                des9.setText(Des9);
                units9.setText(Unit9);

                String sub10 = snapshot.child("second_sem/grade10_32").getValue().toString();
                String Des10 = snapshot.child("second_sem/des10_32").getValue().toString();
                String Unit10 = snapshot.child("second_sem/unit10_32").getValue().toString();
                g10.setText(sub10);
                des10.setText(Des10);
                units10.setText(Unit10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void  BSE_E1y1sem(){
        //
        databaseRef.child(userID).child("grades").child("third_year").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                String sub1 = snapshot.child("first_sem/grade01_31").getValue().toString();
                String Des1 = snapshot.child("first_sem/des1_31").getValue().toString();
                String Unit = snapshot.child("first_sem/unit1_31").getValue().toString();
                g1.setText(sub1);
                des1.setText(Des1);
                units1.setText(Unit);

                String sub2 = snapshot.child("first_sem/grade02_31").getValue().toString();
                String Des2 = snapshot.child("first_sem/des2_31").getValue().toString();
                String Unit2 = snapshot.child("first_sem/unit2_31").getValue().toString();
                g2.setText(sub2);
                des2.setText(Des2);
                units2.setText(Unit2);

                String sub3 = snapshot.child("first_sem/grade03_31").getValue().toString();
                String Des3 = snapshot.child("first_sem/des3_31").getValue().toString();
                String Unit3 = snapshot.child("first_sem/unit3_31").getValue().toString();
                g3.setText(sub3);
                des3.setText(Des3);
                units3.setText(Unit3);

                String sub4 = snapshot.child("first_sem/grade04_31").getValue().toString();
                String Des4 = snapshot.child("first_sem/des4_31").getValue().toString();
                String Unit4 = snapshot.child("first_sem/unit4_31").getValue().toString();
                g4.setText(sub4);
                des4.setText(Des4);
                units4.setText(Unit4);

                String sub5 = snapshot.child("first_sem/grade05_31").getValue().toString();
                String Des5 = snapshot.child("first_sem/des5_31").getValue().toString();
                String Unit5 = snapshot.child("first_sem/unit5_31").getValue().toString();
                g5.setText(sub5);
                des5.setText(Des5);
                units5.setText(Unit5);

                String sub6 = snapshot.child("first_sem/grade06_31").getValue().toString();
                String Des6 = snapshot.child("first_sem/des6_31").getValue().toString();
                String Unit6 = snapshot.child("first_sem/unit6_31").getValue().toString();
                g6.setText(sub6);
                des6.setText(Des6);
                units6.setText(Unit6);

                String sub7 = snapshot.child("first_sem/grade07_31").getValue().toString();
                String Des7 = snapshot.child("first_sem/des7_31").getValue().toString();
                String Unit7 = snapshot.child("first_sem/unit7_31").getValue().toString();
                g7.setText(sub7);
                des7.setText(Des7);
                units7.setText(Unit7);

                String sub8 = snapshot.child("first_sem/grade08_31").getValue().toString();
                String Des8 = snapshot.child("first_sem/des8_31").getValue().toString();
                String Unit8 = snapshot.child("first_sem/unit8_31").getValue().toString();
                g8.setText(sub8);
                des8.setText(Des8);
                units8.setText(Unit8);

                String sub9 = snapshot.child("first_sem/grade09_31").getValue().toString();
                String Des9 = snapshot.child("first_sem/des9_31").getValue().toString();
                String Unit9 = snapshot.child("first_sem/unit9_31").getValue().toString();
                g9.setText(sub9);
                des9.setText(Des9);
                units9.setText(Unit9);

                String sub10 = snapshot.child("first_sem/grade10_31").getValue().toString();
                String Des10 = snapshot.child("first_sem/des10_31").getValue().toString();
                String Unit10 = snapshot.child("first_sem/unit10_31").getValue().toString();
                g10.setText(sub10);
                des10.setText(Des10);
                units10.setText(Unit10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void  BSE_E1y2sem(){
        //
        databaseRef.child(userID).child("grades").child("third_year").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("second_sem/grade01_32").getValue().toString();
                String Des1 = snapshot.child("second_sem/des1_32").getValue().toString();
                String Unit = snapshot.child("second_sem/unit1_32").getValue().toString();
                g1.setText(sub1);
                des1.setText(Des1);
                units1.setText(Unit);

                String sub2 = snapshot.child("second_sem/grade02_32").getValue().toString();
                String Des2 = snapshot.child("second_sem/des2_32").getValue().toString();
                String Unit2 = snapshot.child("second_sem/unit2_32").getValue().toString();
                g2.setText(sub2);
                des2.setText(Des2);
                units2.setText(Unit2);

                String sub3 = snapshot.child("second_sem/grade03_32").getValue().toString();
                String Des3 = snapshot.child("second_sem/des3_32").getValue().toString();
                String Unit3 = snapshot.child("second_sem/unit3_32").getValue().toString();
                g3.setText(sub3);
                des3.setText(Des3);
                units3.setText(Unit3);

                String sub4 = snapshot.child("second_sem/grade04_32").getValue().toString();
                String Des4 = snapshot.child("second_sem/des4_32").getValue().toString();
                String Unit4 = snapshot.child("second_sem/unit4_32").getValue().toString();
                g4.setText(sub4);
                des4.setText(Des4);
                units4.setText(Unit4);

                String sub5= snapshot.child("second_sem/grade05_32").getValue().toString();
                String Des5 = snapshot.child("second_sem/des5_32").getValue().toString();
                String Unit5 = snapshot.child("second_sem/unit5_32").getValue().toString();
                g5.setText(sub5);
                des5.setText(Des5);
                units5.setText(Unit5);

                String sub6 = snapshot.child("second_sem/grade06_32").getValue().toString();
                String Des6 = snapshot.child("second_sem/des6_32").getValue().toString();
                String Unit6 = snapshot.child("second_sem/unit6_32").getValue().toString();
                g6.setText(sub6);
                des6.setText(Des6);
                units6.setText(Unit6);

                String sub7 = snapshot.child("second_sem/grade07_32").getValue().toString();
                String Des7 = snapshot.child("second_sem/des7_32").getValue().toString();
                String Unit7 = snapshot.child("second_sem/unit7_32").getValue().toString();
                g7.setText(sub7);
                des7.setText(Des7);
                units7.setText(Unit7);

                String sub8 = snapshot.child("second_sem/grade08_32").getValue().toString();
                String Des8= snapshot.child("second_sem/des8_32").getValue().toString();
                String Unit8 = snapshot.child("second_sem/unit8_32").getValue().toString();
                g8.setText(sub8);
                des8.setText(Des8);
                units8.setText(Unit8);

                String sub9 = snapshot.child("second_sem/grade09_32").getValue().toString();
                String Des9 = snapshot.child("second_sem/des9_32").getValue().toString();
                String Unit9 = snapshot.child("second_sem/unit9_32").getValue().toString();
                g9.setText(sub9);
                des9.setText(Des9);
                units9.setText(Unit9);

                String sub10 = snapshot.child("second_sem/grade10_32").getValue().toString();
                String Des10 = snapshot.child("second_sem/des10_32").getValue().toString();
                String Unit10 = snapshot.child("second_sem/unit10_32").getValue().toString();
                g10.setText(sub10);
                des10.setText(Des10);
                units10.setText(Unit10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void  BSE_S1y1sem(){
        //
        databaseRef.child(userID).child("grades").child("third_year").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                String sub1 = snapshot.child("first_sem/grade01_31").getValue().toString();
                String Des1 = snapshot.child("first_sem/des1_31").getValue().toString();
                String Unit = snapshot.child("first_sem/unit1_31").getValue().toString();
                g1.setText(sub1);
                des1.setText(Des1);
                units1.setText(Unit);

                String sub2 = snapshot.child("first_sem/grade02_31").getValue().toString();
                String Des2 = snapshot.child("first_sem/des2_31").getValue().toString();
                String Unit2 = snapshot.child("first_sem/unit2_31").getValue().toString();
                g2.setText(sub2);
                des2.setText(Des2);
                units2.setText(Unit2);

                String sub3 = snapshot.child("first_sem/grade03_31").getValue().toString();
                String Des3 = snapshot.child("first_sem/des3_31").getValue().toString();
                String Unit3 = snapshot.child("first_sem/unit3_31").getValue().toString();
                g3.setText(sub3);
                des3.setText(Des3);
                units3.setText(Unit3);

                String sub4 = snapshot.child("first_sem/grade04_31").getValue().toString();
                String Des4 = snapshot.child("first_sem/des4_31").getValue().toString();
                String Unit4 = snapshot.child("first_sem/unit4_31").getValue().toString();
                g4.setText(sub4);
                des4.setText(Des4);
                units4.setText(Unit4);

                String sub5 = snapshot.child("first_sem/grade05_31").getValue().toString();
                String Des5 = snapshot.child("first_sem/des5_31").getValue().toString();
                String Unit5 = snapshot.child("first_sem/unit5_31").getValue().toString();
                g5.setText(sub5);
                des5.setText(Des5);
                units5.setText(Unit5);

                String sub6 = snapshot.child("first_sem/grade06_31").getValue().toString();
                String Des6 = snapshot.child("first_sem/des6_31").getValue().toString();
                String Unit6 = snapshot.child("first_sem/unit6_31").getValue().toString();
                g6.setText(sub6);
                des6.setText(Des6);
                units6.setText(Unit6);

                String sub7 = snapshot.child("first_sem/grade07_31").getValue().toString();
                String Des7 = snapshot.child("first_sem/des7_31").getValue().toString();
                String Unit7 = snapshot.child("first_sem/unit7_31").getValue().toString();
                g7.setText(sub7);
                des7.setText(Des7);
                units7.setText(Unit7);

                String sub8 = snapshot.child("first_sem/grade08_31").getValue().toString();
                String Des8 = snapshot.child("first_sem/des8_31").getValue().toString();
                String Unit8 = snapshot.child("first_sem/unit8_31").getValue().toString();
                g8.setText(sub8);
                des8.setText(Des8);
                units8.setText(Unit8);

                String sub9 = snapshot.child("first_sem/grade09_31").getValue().toString();
                String Des9 = snapshot.child("first_sem/des9_31").getValue().toString();
                String Unit9 = snapshot.child("first_sem/unit9_31").getValue().toString();
                g9.setText(sub9);
                des9.setText(Des9);
                units9.setText(Unit9);

                String sub10 = snapshot.child("first_sem/grade10_31").getValue().toString();
                String Des10 = snapshot.child("first_sem/des10_31").getValue().toString();
                String Unit10 = snapshot.child("first_sem/unit10_31").getValue().toString();
                g10.setText(sub10);
                des10.setText(Des10);
                units10.setText(Unit10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void  BSE_S1y2sem(){
        //
        databaseRef.child(userID).child("grades").child("third_year").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //Introduction to Computing
                String sub1 = snapshot.child("first_sem/grade01").getValue().toString();
                String Des1 = snapshot.child("first_sem/des1").getValue().toString();
                String Unit = snapshot.child("first_sem/unit1").getValue().toString();
                g1.setText(sub1);
                des1.setText(Des1);
                units1.setText(Unit);

                String sub2 = snapshot.child("first_sem/grade02").getValue().toString();
                String Des2 = snapshot.child("first_sem/des2").getValue().toString();
                String Unit2 = snapshot.child("first_sem/unit2").getValue().toString();
                g2.setText(sub2);
                des2.setText(Des2);
                units2.setText(Unit2);

                String sub3 = snapshot.child("second_sem/grade03_12").getValue().toString();
                String Des3 = snapshot.child("second_sem/des3_12").getValue().toString();
                String Unit3 = snapshot.child("second_sem/unit3_12").getValue().toString();
                g3.setText(sub3);
                des3.setText(Des3);
                units3.setText(Unit3);

                String sub4 = snapshot.child("second_sem/grade04_12").getValue().toString();
                String Des4 = snapshot.child("second_sem/des4_12").getValue().toString();
                String Unit4 = snapshot.child("second_sem/unit4_12").getValue().toString();
                g4.setText(sub4);
                des4.setText(Des4);
                units4.setText(Unit4);

                String sub5 = snapshot.child("second_sem/grade05_12").getValue().toString();
                String Des5 = snapshot.child("second_sem/des5_12").getValue().toString();
                String Unit5 = snapshot.child("second_sem/unit5_12").getValue().toString();
                g5.setText(sub5);
                des5.setText(Des5);
                units5.setText(Unit5);

                String sub6 = snapshot.child("second_sem/grade06_12").getValue().toString();
                String Des6 = snapshot.child("second_sem/des6_12").getValue().toString();
                String Unit6 = snapshot.child("second_sem/unit6_12").getValue().toString();
                g6.setText(sub6);
                des6.setText(Des6);
                units6.setText(Unit6);

                String sub7 = snapshot.child("second_sem/grade07_12").getValue().toString();
                String Des7 = snapshot.child("second_sem/des7_12").getValue().toString();
                String Unit7 = snapshot.child("second_sem/unit7_12").getValue().toString();
                g7.setText(sub7);
                des7.setText(Des7);
                units7.setText(Unit7);

                String sub8 = snapshot.child("second_sem/grade08_12").getValue().toString();
                String Des8 = snapshot.child("second_sem/des8_12").getValue().toString();
                String Unit8 = snapshot.child("second_sem/unit8_12").getValue().toString();
                g8.setText(sub8);
                des8.setText(Des8);
                units8.setText(Unit8);

                String sub9 = snapshot.child("second_sem/grade09_12").getValue().toString();
                String Des9 = snapshot.child("second_sem/des9_12").getValue().toString();
                String Unit9 = snapshot.child("second_sem/unit9_12").getValue().toString();
                g9.setText(sub9);
                des9.setText(Des9);
                units9.setText(Unit9);

                String sub10 = snapshot.child("second_sem/grade10_12").getValue().toString();
                String Des10 = snapshot.child("second_sem/des10_12").getValue().toString();
                String Unit10 = snapshot.child("second_sem/unit10_12").getValue().toString();
                g10.setText(sub10);
                des10.setText(Des10);
                units10.setText(Unit10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }



}

