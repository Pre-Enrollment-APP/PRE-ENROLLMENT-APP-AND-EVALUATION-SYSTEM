package com.example.cccpre_enrollmentapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.support.v4.os.IResultReceiver;
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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class Pre_Enrollment extends AppCompatActivity {
    private ImageButton menu_page;
    private Button ok;
    private TextView name, course, address, gmail, birthday;
    private int totalunits = 0;
    private long invoiceNo = 0;
    int total = 0, one, two, three, four, five, six, seven, eight, nine, ten;
    private Spinner semester, section, mop;
    private EditText des1, des2, des3, des4, des5, des6, des7, des8, des9, des10, schoolyear;
    private EditText units1, units2, units3, units4, units5, units6, units7, units8, units9, units10;
    ArrayAdapter<String> sectionlist;
    ArrayAdapter<String> moplist;
    String[] sections, MOP;
    SimpleDateFormat datePattternformat = new SimpleDateFormat("yyyy-yyyy");
    private String userID;
    private DatabaseReference databaseRef;
    private FirebaseUser user;
    private EditText SC1, SC2, SC3, SC4, SC5, SC6, SC7, SC8, SC9, SC10;
    private EditText s1, s2, s3, s4, s5, s6, s7, s8, s9, s10;
    //logo image print
    Bitmap bmp, scaledbmp;

    //print pdf
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference print = database.getReference("record");
    studentinfo dataObj = new studentinfo();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pre_enrollment);
        name = findViewById(R.id.studentName);
        course = findViewById(R.id.course);
        mop = findViewById(R.id.mop);
        semester = findViewById(R.id.semester);
        section = findViewById(R.id.section);
        schoolyear = findViewById(R.id.schoolyear);
        gmail = findViewById(R.id.gmail);
        address = findViewById(R.id.address);
        birthday = findViewById(R.id.birthday);

        user = FirebaseAuth.getInstance().getCurrentUser();
        databaseRef = FirebaseDatabase.getInstance().getReference("User");
        userID = user.getUid();

        SC1 = findViewById(R.id.SC1);
        SC2 = findViewById(R.id.SC2);
        SC3 = findViewById(R.id.SC3);
        SC4 = findViewById(R.id.SC4);
        SC5 = findViewById(R.id.SC5);
        SC6 = findViewById(R.id.SC6);
        SC7 = findViewById(R.id.SC7);
        SC8 = findViewById(R.id.SC8);
        SC9 = findViewById(R.id.SC9);
        SC10 = findViewById(R.id.SC10);

        des1 = findViewById(R.id.Des1);
        des2 = findViewById(R.id.Des2);
        des3 = findViewById(R.id.Des3);
        des4 = findViewById(R.id.Des4);
        des5 = findViewById(R.id.Des5);
        des6 = findViewById(R.id.Des6);
        des7 = findViewById(R.id.Des7);
        des8 = findViewById(R.id.Des8);
        des9 = findViewById(R.id.Des9);
        des10 = findViewById(R.id.Des10);

        units1 = findViewById(R.id.units1);
        units2 = findViewById(R.id.units2);
        units3 = findViewById(R.id.units3);
        units4 = findViewById(R.id.units4);
        units5 = findViewById(R.id.units5);
        units6 = findViewById(R.id.units6);
        units7 = findViewById(R.id.units7);
        units8 = findViewById(R.id.units8);
        units9 = findViewById(R.id.units9);
        units10 = findViewById(R.id.units10);


        s1 = findViewById(R.id.sched1);
        s2 = findViewById(R.id.sched2);
        s3 = findViewById(R.id.sched3);
        s4 = findViewById(R.id.sched4);
        s5 = findViewById(R.id.sched5);
        s6 = findViewById(R.id.sched6);
        s7 = findViewById(R.id.sched7);
        s8 = findViewById(R.id.sched8);
        s9 = findViewById(R.id.sched9);
        s10 = findViewById(R.id.sched10);


//////////// /////////////////////////S E C T I O N DROPDOWN///////////////////////////////////////////////////

        sections = new String[]{"--select--", "A", "B", "C",};
        sectionlist = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, sections);
        section.setAdapter(sectionlist);

        section.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                String Course = course.getText().toString();
                String YearSem = semester.getSelectedItem().toString();

                switch (item) {
                    case "A":

                        if (Course.equals("Bachelor of Science in Computer Science")) {
                            if (YearSem.equals("1st yr, 1st sem")) {
                                bscs1y1sSched_A();

                            }
                            if (YearSem.equals("1st yr, 2nd sem")) {
                                bscs1y2sSched_A();

                            }
                            if (YearSem.equals("2st yr, 1nd sem")) {
                                bscs2y1sSched_A();

                            }


                        }else {
                            Toast.makeText(Pre_Enrollment.this, "Choose your *Year and Semester* first", Toast.LENGTH_SHORT).show();
                        }
                        break;

                    case "B":{

                        if (Course.equals("Bachelor of Science in Computer Science")) {
                            if (YearSem.equals("1st yr, 1st sem")) {
                                bscs1y1sSched_B();

                            }
                            if (YearSem.equals("1st yr, 2nd sem")) {
                                bscs1y2sSched_B();

                            }
                            if (YearSem.equals("2st yr, 1nd sem")) {
                                bscs2y1sSched_B();

                            }

                        }

                    }
                    case "C":{
                        if (Course.equals("Bachelor of Science in Computer Science")) {
                            if (YearSem.equals("1st yr, 1st sem")) {
                                bscs1y1sSched_C();

                            }
                            if (YearSem.equals("1st yr, 2nd sem")) {
                                bscs1y2sSched_C();

                            }
                            if (YearSem.equals("2st yr, 1nd sem")) {
                                bscs2y1sSched_C();

                            }

                        }else {
                            Toast.makeText(Pre_Enrollment.this, "Choose your *Year and Semester* first", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    }
                    case "--select--":{
                        s1.getText().clear();
                        s2.getText().clear();
                        s3.getText().clear();
                        s4.getText().clear();
                        s5.getText().clear();
                        s6.getText().clear();
                        s7.getText().clear();
                        s8.getText().clear();
                        s9.getText().clear();
                        s10.getText().clear();

                    }



                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.ccc);
        scaledbmp = Bitmap.createScaledBitmap(bmp, 45, 40, true);

//////////// /////////////////////////MODE OF PAYMENT DROPDOWN /////////////////////////////////////////

        MOP = new String[]{"-select-", "A - Cash", "- Installment"};
        moplist = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, MOP);
        mop.setAdapter(moplist);

        print.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                invoiceNo = snapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//////////// /////////////////////////SEMESTER DROPDOWN///////////////////////////////////////////////////

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.semester, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        semester.setAdapter(adapter);
        semester.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                switch (item) {
                    case "1st yr, 1st sem":
                        databaseRef.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                String Course = snapshot.child("Course").getValue().toString();

                                if (Course.equals("Bachelor of Science in Computer Science")) {
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

                                if (Course.equals("Bachelor of Science in Computer Science")) {
                                    BSCS1y2sem();

                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                        break;

                }


            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


///////////////////////////////DISPLAY THE COURSE, NAME///////////////////////////////////////////////////////
        databaseRef.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String Name = snapshot.child("Name").getValue().toString();
                String Course = snapshot.child("Course").getValue().toString();
                String Email = snapshot.child("Email").getValue().toString(); // i hide the email to xml
                name.setText(Name);
                course.setText(Course);
                gmail.setText(Email);


            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        /////////// / ////////////////////DOWNLOAD THE FORM////////////////////////////////////

        ok = findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dataObj.name = String.valueOf(name.getText());
                dataObj.mop = String.valueOf(mop.getSelectedItem());
                dataObj.course = String.valueOf(course.getText());
                dataObj.gmail = String.valueOf(gmail.getText());
                dataObj.schoolyear = String.valueOf(schoolyear.getText());
                dataObj.yearAndsem = String.valueOf(semester.getSelectedItem());
                dataObj.section = String.valueOf(section.getSelectedItem());
                dataObj.des5 = String.valueOf(des10.getText());
                dataObj.address = String.valueOf(address.getText());
                dataObj.des1 = String.valueOf(des1.getText());
                dataObj.des2 = String.valueOf(des2.getText());
                dataObj.des3 = String.valueOf(des3.getText());
                dataObj.des4 = String.valueOf(des4.getText());
                dataObj.des5 = String.valueOf(des5.getText());
                dataObj.des5 = String.valueOf(des6.getText());
                dataObj.des5 = String.valueOf(des7.getText());
                dataObj.des5 = String.valueOf(des8.getText());
                dataObj.des5 = String.valueOf(des9.getText());
                dataObj.s1=String.valueOf(s1.getText());
                dataObj.s2=String.valueOf(s2.getText());
                dataObj.s3=String.valueOf(s3.getText());
                dataObj.s4=String.valueOf(s4.getText());
                dataObj.s5=String.valueOf(s5.getText());
                dataObj.s6=String.valueOf(s6.getText());
                dataObj.s7=String.valueOf(s7.getText());
                dataObj.s8=String.valueOf(s8.getText());
                dataObj.s9=String.valueOf(s9.getText());
                dataObj.s10=String.valueOf(s10.getText());

                dataObj.date = new Date().getTime();
                dataObj.birthday = String.valueOf(birthday.getText());

                print.child(String.valueOf(invoiceNo + 1)).setValue(dataObj);

                if (dataObj.schoolyear.isEmpty()) {
                    schoolyear.setError("School year is required!");
                    schoolyear.requestFocus();
                    return;

                }
                AlertDialog dlg=new AlertDialog.Builder(Pre_Enrollment.this)
                        .setTitle("Your Form is ready to Print")
                        .setMessage("File Location: Filemanager>Android>data>com.example.ccc...llmentapplication")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create();
                dlg.show();


                printPDF();

            }
        });

    }

/////////////////////////////////////* SUBJECT EACH COURSE*////////////////////////////////

    private void BSCS1y1sem() {
        DatabaseReference databasecs = FirebaseDatabase.getInstance().getReference("course_curriculum/bscs/first_year");

        databasecs.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //Introduction to Computing
                String sub1 = snapshot.child("first_sem/subject1/code").getValue().toString();
                String Des1 = snapshot.child("first_sem/subject1/descriptive_title").getValue().toString();
                String Unit = snapshot.child("first_sem/subject1/units").getValue().toString();
                SC1.setText(sub1);
                des1.setText(Des1);
                units1.setText(Unit);

                String sub2 = snapshot.child("first_sem/subject2/code").getValue().toString();
                String Des2 = snapshot.child("first_sem/subject2/descriptive_title").getValue().toString();
                String Unit2 = snapshot.child("first_sem/subject2/units").getValue().toString();
                SC2.setText(sub2);
                des2.setText(Des2);
                units2.setText(Unit2);

                String sub3 = snapshot.child("first_sem/subject3/code").getValue().toString();
                String Des3 = snapshot.child("first_sem/subject3/descriptive_title").getValue().toString();
                String Unit3 = snapshot.child("first_sem/subject3/units").getValue().toString();
                SC3.setText(sub3);
                des3.setText(Des3);
                units3.setText(Unit3);

                String sub4 = snapshot.child("first_sem/subject4/code").getValue().toString();
                String Des4 = snapshot.child("first_sem/subject4/descriptive_title").getValue().toString();
                String Unit4 = snapshot.child("first_sem/subject4/units").getValue().toString();
                SC4.setText(sub4);
                des4.setText(Des4);
                units4.setText(Unit4);

                String sub5 = snapshot.child("first_sem/subject5/code").getValue().toString();
                String Des5 = snapshot.child("first_sem/subject5/descriptive_title").getValue().toString();
                String Unit5 = snapshot.child("first_sem/subject5/units").getValue().toString();
                SC5.setText(sub5);
                des5.setText(Des5);
                units5.setText(Unit5);

                String sub6 = snapshot.child("first_sem/subject6/code").getValue().toString();
                String Des6 = snapshot.child("first_sem/subject6/descriptive_title").getValue().toString();
                String Unit6 = snapshot.child("first_sem/subject6/units").getValue().toString();
                SC6.setText(sub6);
                des6.setText(Des6);
                units6.setText(Unit6);

                String sub7 = snapshot.child("first_sem/subject7/code").getValue().toString();
                String Des7 = snapshot.child("first_sem/subject7/descriptive_title").getValue().toString();
                String Unit7 = snapshot.child("first_sem/subject7/units").getValue().toString();
                SC7.setText(sub7);
                des7.setText(Des7);
                units7.setText(Unit7);

                String sub8 = snapshot.child("first_sem/subject8/code").getValue().toString();
                String Des8 = snapshot.child("first_sem/subject8/descriptive_title").getValue().toString();
                String Unit8 = snapshot.child("first_sem/subject8/units").getValue().toString();
                SC8.setText(sub8);
                des8.setText(Des8);
                units8.setText(Unit8);

                String sub9 = snapshot.child("first_sem/subject9/code").getValue().toString();
                String Des9 = snapshot.child("first_sem/subject9/descriptive_title").getValue().toString();
                String Unit9 = snapshot.child("first_sem/subject9/units").getValue().toString();
                SC9.setText(sub9);
                des9.setText(Des9);
                units9.setText(Unit9);

                String sub10 = snapshot.child("first_sem/subject10/code").getValue().toString();
                String Des10 = snapshot.child("first_sem/subject10/descriptive_title").getValue().toString();
                String Unit10 = snapshot.child("first_sem/subject10/units").getValue().toString();
                SC10.setText(sub10);
                des10.setText(Des10);
                units10.setText(Unit10);


            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void BSCS1y2sem() {

        DatabaseReference databasecs = FirebaseDatabase.getInstance().getReference("course_curriculum");

        databasecs.child("bscs").child("first_year").child("second_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //Introduction to Computing
                String sub1 = snapshot.child("subject_1/code").getValue().toString();
                String Des1 = snapshot.child("subject_1/descriptive_title").getValue().toString();
                String Unit = snapshot.child("subject_1/units").getValue().toString();
                SC1.setText(sub1);
                des1.setText(Des1);
                units1.setText(Unit);

                String sub2 = snapshot.child("subject_2/code").getValue().toString();
                String Des2 = snapshot.child("subject_2/descriptive_title").getValue().toString();
                String Unit2 = snapshot.child("subject_2/units").getValue().toString();
                SC2.setText(sub2);
                des2.setText(Des2);
                units2.setText(Unit2);

                String sub3 = snapshot.child("subject_3/code").getValue().toString();
                String Des3 = snapshot.child("subject_3/descriptive_title").getValue().toString();
                String Unit3 = snapshot.child("subject_3/units").getValue().toString();
                SC3.setText(sub3);
                des3.setText(Des3);
                units3.setText(Unit3);

                String sub4 = snapshot.child("subject_4/code").getValue().toString();
                String Des4 = snapshot.child("subject_4/descriptive_title").getValue().toString();
                String Unit4 = snapshot.child("subject_4/units").getValue().toString();
                SC4.setText(sub4);
                des4.setText(Des4);
                units4.setText(Unit4);

                String sub5 = snapshot.child("subject_5/code").getValue().toString();
                String Des5 = snapshot.child("subject_5/descriptive_title").getValue().toString();
                String Unit5 = snapshot.child("subject_5/units").getValue().toString();
                SC5.setText(sub5);
                des5.setText(Des5);
                units5.setText(Unit5);

                String sub6 = snapshot.child("subject_6/code").getValue().toString();
                String Des6 = snapshot.child("subject_6/descriptive_title").getValue().toString();
                String Unit6 = snapshot.child("subject_6/units").getValue().toString();
                SC6.setText(sub6);
                des6.setText(Des6);
                units6.setText(Unit6);

                String sub7 = snapshot.child("subject_7/code").getValue().toString();
                String Des7 = snapshot.child("subject_7/descriptive_title").getValue().toString();
                String Unit7 = snapshot.child("subject_7/units").getValue().toString();
                SC7.setText(sub7);
                des7.setText(Des7);
                units7.setText(Unit7);

                String sub8 = snapshot.child("subject_8/code").getValue().toString();
                String Des8 = snapshot.child("subject_8/descriptive_title").getValue().toString();
                String Unit8 = snapshot.child("subject_8/units").getValue().toString();
                SC8.setText(sub8);
                des8.setText(Des8);
                units8.setText(Unit8);

                String sub9 = snapshot.child("subject_9/code").getValue().toString();
                String Des9 = snapshot.child("subject_9/descriptive_title").getValue().toString();
                String Unit9 = snapshot.child("subject_9/units").getValue().toString();
                SC9.setText(sub9);
                des9.setText(Des9);
                units9.setText(Unit9);

                String sub10 = snapshot.child("subject_10/code").getValue().toString();
                String Des10 = snapshot.child("subject_10/descriptive_title").getValue().toString();
                String Unit10 = snapshot.child("subject_10/units").getValue().toString();
                SC10.setText(sub10);
                des10.setText(Des10);
                units10.setText(Unit10);


            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void printPDF() {
        PdfDocument myPdfDocument = new PdfDocument();
        Paint paint = new Paint();
        PdfDocument.PageInfo myPageInfo1 = new PdfDocument.PageInfo.Builder(596, 842, 1).create();
        PdfDocument.Page myPage1 = myPdfDocument.startPage(myPageInfo1);
        Canvas canvas = myPage1.getCanvas();
        //
        File file = new File(this.getExternalFilesDir("/"), "Form.pdf" );
        //
        canvas.drawBitmap(scaledbmp, (myPageInfo1.getPageWidth() / 2) - 20, 10, paint);


        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(14.0f);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        canvas.drawText("Cainta Catholic College", myPageInfo1.getPageWidth() / 2, 65, paint);
        paint.setTextSize(11.0f);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));



        canvas.drawText("Cainta,Rizal", myPageInfo1.getPageWidth() / 2, 80, paint);

        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        canvas.drawText("COLLEGE DEPARTMENT", myPageInfo1.getPageWidth() / 2, 100, paint);
        canvas.drawText("INFORMATION SHEET", myPageInfo1.getPageWidth() / 2, 111, paint);

        paint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText("OR#:", myPageInfo1.getPageWidth() - 80, 85, paint);
        canvas.drawText("SECTION : " + section.getSelectedItem(), myPageInfo1.getPageWidth() - 135, 191, paint);
        canvas.drawText("UNITS", myPageInfo1.getPageWidth() - 185, 260, paint);
        canvas.drawText("SCHEDULE", myPageInfo1.getPageWidth() - 65, 260, paint);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
        canvas.drawText("" + units1.getText(), myPageInfo1.getPageWidth() - 195, 288, paint);
        canvas.drawText("" + units2.getText(), myPageInfo1.getPageWidth() - 195, 316, paint);
        canvas.drawText("" + units3.getText(), myPageInfo1.getPageWidth() - 195, 344, paint);
        canvas.drawText("" + units4.getText(), myPageInfo1.getPageWidth() - 195, 372, paint);
        canvas.drawText("" + units5.getText(), myPageInfo1.getPageWidth() - 195, 400, paint);
        canvas.drawText("" + units6.getText(), myPageInfo1.getPageWidth() - 195, 428, paint);
        canvas.drawText("" + units7.getText(), myPageInfo1.getPageWidth() - 195, 456, paint);
        canvas.drawText("" + units8.getText(), myPageInfo1.getPageWidth() - 195, 484, paint);
        canvas.drawText("" + units9.getText(), myPageInfo1.getPageWidth() - 195, 512, paint);
        canvas.drawText("" + units10.getText(), myPageInfo1.getPageWidth() - 195, 540, paint);
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText("" + s1.getText(), myPageInfo1.getPageWidth() - 105, 288, paint);
        canvas.drawText("" + s2.getText(), myPageInfo1.getPageWidth() - 105, 316, paint);
        canvas.drawText("" + s3.getText(), myPageInfo1.getPageWidth() - 105, 344, paint);
        canvas.drawText("" + s4.getText(), myPageInfo1.getPageWidth() - 105, 372, paint);
        canvas.drawText("" + s5.getText(), myPageInfo1.getPageWidth() - 105, 400, paint);
        canvas.drawText("" + s6.getText(), myPageInfo1.getPageWidth() - 105, 428, paint);
        canvas.drawText("" + s7.getText(), myPageInfo1.getPageWidth() - 105, 456, paint);
        canvas.drawText("" + s8.getText(), myPageInfo1.getPageWidth() - 105, 484, paint);
        canvas.drawText("" + s9.getText(), myPageInfo1.getPageWidth() - 105, 512, paint);
        canvas.drawText("" + s10.getText(), myPageInfo1.getPageWidth() - 105, 540, paint);

        paint.setTextAlign(Paint.Align.LEFT);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText("NAME: " + name.getText(), 40, 140, paint);
        canvas.drawText("EMAIL: " + gmail.getText(), 40, 157, paint);
        canvas.drawText("COURSE: " + course.getText(), 40, 174, paint);
        canvas.drawText("YEAR & SEM: " + semester.getSelectedItem(), 40, 191, paint);
        //
        canvas.drawText("MODE OF PAYMENT: Plan " + mop.getSelectedItem(), 40, 208, paint);
        canvas.drawText("CODE", 40, 260, paint);
        canvas.drawText("DESCRIPTION: ", 175, 260, paint);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
        canvas.drawText("" + SC1.getText(), 40, 288, paint);
        canvas.drawText("" + SC2.getText(), 40, 316, paint);
        canvas.drawText("" + SC3.getText(), 40, 344, paint);
        canvas.drawText("" + SC4.getText(), 40, 372, paint);
        canvas.drawText("" + SC5.getText(), 40, 400, paint);
        canvas.drawText("" + SC6.getText(), 40, 428, paint);
        canvas.drawText("" + SC7.getText(), 40, 456, paint);
        canvas.drawText("" + SC8.getText(), 40, 484, paint);
        canvas.drawText("" + SC9.getText(), 40, 512, paint);
        canvas.drawText("" + SC10.getText(), 40, 540, paint);

        canvas.drawText("" + des1.getText(), 140, 288, paint);
        canvas.drawText("" + des2.getText(), 140, 316, paint);
        canvas.drawText("" + des3.getText(), 140, 344, paint);
        canvas.drawText("" + des4.getText(), 140, 372, paint);
        canvas.drawText("" + des5.getText(), 140, 400, paint);
        canvas.drawText("" + des6.getText(), 140, 428, paint);
        canvas.drawText("" + des7.getText(), 140, 456, paint);
        canvas.drawText("" + des8.getText(), 140, 484, paint);
        canvas.drawText("" + des9.getText(), 140, 512, paint);
        canvas.drawText("" + des10.getText(), 140, 540, paint);
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        canvas.drawText("NOTE:", 40, 600, paint);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
        canvas.drawLine(80, 600, myPageInfo1.getPageWidth() - 35, 600, paint);
        canvas.drawLine(40, 630, myPageInfo1.getPageWidth() - 35, 630, paint);

        canvas.drawLine(40, 655, myPageInfo1.getPageWidth() - 35, 655, paint);

        canvas.drawLine(40, 705, 200, 705, paint);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        canvas.drawText("Adviser", 100, 720, paint);
        paint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawLine(myPageInfo1.getPageWidth() - 210, 705, myPageInfo1.getPageWidth() - 40, 705, paint);
        canvas.drawText("Student Signature", myPageInfo1.getPageWidth() - 80, 720, paint);


        one = Integer.parseInt(units1.getText().toString());
        two = Integer.parseInt(units2.getText().toString());
        three = Integer.parseInt(units3.getText().toString());
        four = Integer.parseInt(units4.getText().toString());
        five = Integer.parseInt(units5.getText().toString());
        six = Integer.parseInt(units6.getText().toString());
        seven = Integer.parseInt(units7.getText().toString());
        eight = Integer.parseInt(units8.getText().toString());
        nine = Integer.parseInt(units9.getText().toString());
        ten = Integer.parseInt(units10.getText().toString());

        paint.setTextAlign(Paint.Align.RIGHT);
        total = one + two + three + four + five + six + seven + eight + nine + ten;
        canvas.drawText("" + total, myPageInfo1.getPageWidth() - 195, 562, paint);


        myPdfDocument.finishPage(myPage1);


        try {
            myPdfDocument.writeTo(new FileOutputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        myPdfDocument.close();


    }


    //////////////////////////////////// BSCS SCHEDULE SECTION //////////////////////////////////////////////////////

              ///////////////////////////////////1st year 1st sem/////////////////////////////////////////
    private void bscs1y1sSched_A() {

        DatabaseReference database = FirebaseDatabase.getInstance().getReference("schedule");

        database.child("bscs").child("first_year").child("first_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //sectionA
                String sub1 = snapshot.child("subject1/a").getValue().toString();
                String sub2 = snapshot.child("subject2/a").getValue().toString();
                String sub3 = snapshot.child("subject3/a").getValue().toString();
                String sub4 = snapshot.child("subject4/a").getValue().toString();
                String sub5 = snapshot.child("subject5/a").getValue().toString();
                String sub6 = snapshot.child("subject6/a").getValue().toString();
                String sub7 = snapshot.child("subject7/a").getValue().toString();
                String sub8 = snapshot.child("subject8/a").getValue().toString();
                String sub9 = snapshot.child("subject9/a").getValue().toString();
                String sub10 = snapshot.child("subject10/a").getValue().toString();


                    s1.setText(sub1);
                    s2.setText(sub2);
                    s3.setText(sub3);
                    s4.setText(sub4);
                    s5.setText(sub5);
                    s6.setText(sub6);
                    s7.setText(sub7);
                    s8.setText(sub8);
                    s9.setText(sub9);
                    s10.setText(sub10);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
    private void bscs1y1sSched_B(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("schedule");

        database.child("bscs").child("first_year").child("first_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //sectionA
                String sub1 = snapshot.child("subject1/b").getValue().toString();
                String sub2 = snapshot.child("subject2/b").getValue().toString();
                String sub3 = snapshot.child("subject3/b").getValue().toString();
                String sub4 = snapshot.child("subject4/b").getValue().toString();
                String sub5 = snapshot.child("subject5/b").getValue().toString();
                String sub6 = snapshot.child("subject6/b").getValue().toString();
                String sub7 = snapshot.child("subject7/b").getValue().toString();
                String sub8 = snapshot.child("subject8/b").getValue().toString();
                String sub9 = snapshot.child("subject9/b").getValue().toString();
                String sub10 = snapshot.child("subject10/b").getValue().toString();


                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
    private void bscs1y1sSched_C(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("schedule");

        database.child("bscs").child("first_year").child("first_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //sectionA
                String sub1 = snapshot.child("subject1/c").getValue().toString();
                String sub2 = snapshot.child("subject2/c").getValue().toString();
                String sub3 = snapshot.child("subject3/c").getValue().toString();
                String sub4 = snapshot.child("subject4/c").getValue().toString();
                String sub5 = snapshot.child("subject5/c").getValue().toString();
                String sub6 = snapshot.child("subject6/c").getValue().toString();
                String sub7 = snapshot.child("subject7/c").getValue().toString();
                String sub8 = snapshot.child("subject8/c").getValue().toString();
                String sub9 = snapshot.child("subject9/c").getValue().toString();
                String sub10 = snapshot.child("subject10/c").getValue().toString();


                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            }
             ///////////////////////////////////1nd year 2st sem/////////////////////////////////////////
    private void bscs1y2sSched_A() {

                 DatabaseReference database = FirebaseDatabase.getInstance().getReference("schedule");

                 database.child("bscs").child("first_year").child("second_sem").addListenerForSingleValueEvent(new ValueEventListener() {
                     @Override
                     public void onDataChange(@NonNull DataSnapshot snapshot) {

                         //sectionA
                         String sub1 = snapshot.child("subject1/a").getValue().toString();
                         String sub2 = snapshot.child("subject2/a").getValue().toString();
                         String sub3 = snapshot.child("subject3/a").getValue().toString();
                         String sub4 = snapshot.child("subject4/a").getValue().toString();
                         String sub5 = snapshot.child("subject5/a").getValue().toString();
                         String sub6 = snapshot.child("subject6/a").getValue().toString();
                         String sub7 = snapshot.child("subject7/a").getValue().toString();
                         String sub8 = snapshot.child("subject8/a").getValue().toString();
                         String sub9 = snapshot.child("subject9/a").getValue().toString();
                         String sub10 = snapshot.child("subject10/a").getValue().toString();


                         s1.setText(sub1);
                         s2.setText(sub2);
                         s3.setText(sub3);
                         s4.setText(sub4);
                         s5.setText(sub5);
                         s6.setText(sub6);
                         s7.setText(sub7);
                         s8.setText(sub8);
                         s9.setText(sub9);
                         s10.setText(sub10);


                     }

                     @Override
                     public void onCancelled(@NonNull DatabaseError error) {

                     }
                 });


             }
    private void bscs1y2sSched_B(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("schedule");

        database.child("bscs").child("first_year").child("second_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //sectionA
                String sub1 = snapshot.child("subject1/b").getValue().toString();
                String sub2 = snapshot.child("subject2/b").getValue().toString();
                String sub3 = snapshot.child("subject3/b").getValue().toString();
                String sub4 = snapshot.child("subject4/b").getValue().toString();
                String sub5 = snapshot.child("subject5/b").getValue().toString();
                String sub6 = snapshot.child("subject6/b").getValue().toString();
                String sub7 = snapshot.child("subject7/b").getValue().toString();
                String sub8 = snapshot.child("subject8/b").getValue().toString();
                String sub9 = snapshot.child("subject9/b").getValue().toString();
                String sub10 = snapshot.child("subject10/b").getValue().toString();


                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
    private void bscs1y2sSched_C(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("schedule");

        database.child("bscs").child("first_year").child("second_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //sectionA
                String sub1 = snapshot.child("subject1/c").getValue().toString();
                String sub2 = snapshot.child("subject2/c").getValue().toString();
                String sub3 = snapshot.child("subject3/c").getValue().toString();
                String sub4 = snapshot.child("subject4/c").getValue().toString();
                String sub5 = snapshot.child("subject5/c").getValue().toString();
                String sub6 = snapshot.child("subject6/c").getValue().toString();
                String sub7 = snapshot.child("subject7/c").getValue().toString();
                String sub8 = snapshot.child("subject8/c").getValue().toString();
                String sub9 = snapshot.child("subject9/c").getValue().toString();
                String sub10 = snapshot.child("subject10/c").getValue().toString();


                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    ///////////////////////////////////2st year 1st sem/////////////////////////////////////////
    private void bscs2y1sSched_A() {

        DatabaseReference database = FirebaseDatabase.getInstance().getReference("schedule");

        database.child("bscs").child("second_year").child("first_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //sectionA
                String sub1 = snapshot.child("subject1/a").getValue().toString();
                String sub2 = snapshot.child("subject2/a").getValue().toString();
                String sub3 = snapshot.child("subject3/a").getValue().toString();
                String sub4 = snapshot.child("subject4/a").getValue().toString();
                String sub5 = snapshot.child("subject5/a").getValue().toString();
                String sub6 = snapshot.child("subject6/a").getValue().toString();
                String sub7 = snapshot.child("subject7/a").getValue().toString();
                String sub8 = snapshot.child("subject8/a").getValue().toString();
                String sub9 = snapshot.child("subject9/a").getValue().toString();
                String sub10 = snapshot.child("subject10/a").getValue().toString();


                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
    private void bscs2y1sSched_B(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("schedule");

        database.child("bscs").child("second_year").child("first_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //sectionA
                String sub1 = snapshot.child("subject1/b").getValue().toString();
                String sub2 = snapshot.child("subject2/b").getValue().toString();
                String sub3 = snapshot.child("subject3/b").getValue().toString();
                String sub4 = snapshot.child("subject4/b").getValue().toString();
                String sub5 = snapshot.child("subject5/b").getValue().toString();
                String sub6 = snapshot.child("subject6/b").getValue().toString();
                String sub7 = snapshot.child("subject7/b").getValue().toString();
                String sub8 = snapshot.child("subject8/b").getValue().toString();
                String sub9 = snapshot.child("subject9/b").getValue().toString();
                String sub10 = snapshot.child("subject10/b").getValue().toString();


                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
    private void bscs2y1sSched_C(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("schedule");

        database.child("bscs").child("second_year").child("first_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //sectionA
                String sub1 = snapshot.child("subject1/c").getValue().toString();
                String sub2 = snapshot.child("subject2/c").getValue().toString();
                String sub3 = snapshot.child("subject3/c").getValue().toString();
                String sub4 = snapshot.child("subject4/c").getValue().toString();
                String sub5 = snapshot.child("subject5/c").getValue().toString();
                String sub6 = snapshot.child("subject6/c").getValue().toString();
                String sub7 = snapshot.child("subject7/c").getValue().toString();
                String sub8 = snapshot.child("subject8/c").getValue().toString();
                String sub9 = snapshot.child("subject9/c").getValue().toString();
                String sub10 = snapshot.child("subject10/c").getValue().toString();


                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }



}
