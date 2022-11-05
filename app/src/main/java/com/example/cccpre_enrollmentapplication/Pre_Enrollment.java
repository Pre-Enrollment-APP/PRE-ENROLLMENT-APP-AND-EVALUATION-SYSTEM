package com.example.cccpre_enrollmentapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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


public class Pre_Enrollment extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private ImageButton menu_page;
    private Button ok;
    private TextView name,course,address,gmail,birthday;
    private int totalunits=0;
    private long invoiceNo=0;
    int  total=0,one,two,three,four,five,six,seven,eight,nine,ten;
    private Spinner semester,section,mop;
    private EditText des1,des2,des3,des4,des5,des6,des7,des8,des9,des10 , schoolyear;
    private EditText units1,units2,units3,units4,units5,units6,units7,units8,units9,units10;
    ArrayAdapter<String> sectionlist;
    ArrayAdapter<String> moplist;
    String[] sections,MOP;
    SimpleDateFormat datePattternformat= new SimpleDateFormat("yyyy-yyyy");
    private String userID;
    private DatabaseReference databaseRef;
    private FirebaseUser user;
    private EditText SC1,SC2,SC3,SC4,SC5,SC6,SC7,SC8,SC9,SC10;
    //logo image print
    Bitmap bmp,scaledbmp;

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
        mop=findViewById(R.id.mop);
        semester = findViewById(R.id.semester);
        section=findViewById(R.id.section);
        schoolyear= findViewById(R.id.schoolyear);
        gmail=findViewById(R.id.gmail);
        address=findViewById(R.id.address);
        birthday=findViewById(R.id.birthday);

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

        sections=new String[]{"--select--","A","B","C","D","E"};
        sectionlist=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,sections);
        section.setAdapter(sectionlist);

        bmp= BitmapFactory.decodeResource(getResources(),R.drawable.ccc);
        scaledbmp =Bitmap.createScaledBitmap(bmp,45,40,true);

          MOP=new String[]{"-select-","A - Cash","- Installment"};
        moplist=new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,MOP);
        mop.setAdapter(moplist);

        print.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                invoiceNo=snapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.semester, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        semester.setAdapter(adapter);
        semester.setOnItemSelectedListener(this);



        databaseRef.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String Name = snapshot.child("Name").getValue().toString();
                String Course = snapshot.child("Course").getValue().toString();
                String Email=snapshot.child("Email").getValue().toString();
                name.setText(Name);
                course.setText(Course);
                gmail.setText(Email);


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
                dataObj.mop=String.valueOf(mop.getSelectedItem());
                dataObj.course=String.valueOf(course.getText());
                dataObj.gmail=String.valueOf(gmail.getText());
                dataObj.schoolyear=String.valueOf(schoolyear.getText());
                dataObj.yearAndsem=String.valueOf(semester.getSelectedItem());
                dataObj.section=String.valueOf(section.getSelectedItem());
                dataObj.des5=String.valueOf(des10.getText());
                dataObj.address=String.valueOf(address.getText());
                dataObj.des1=String.valueOf(des1.getText());
                dataObj.des2=String.valueOf(des2.getText());
                dataObj.des3=String.valueOf(des3.getText());
                dataObj.des4=String.valueOf(des4.getText());
                dataObj.des5=String.valueOf(des5.getText());
                dataObj.des5=String.valueOf(des6.getText());
                dataObj.des5=String.valueOf(des7.getText());
                dataObj.des5=String.valueOf(des8.getText());
                dataObj.des5=String.valueOf(des9.getText());





                dataObj.date=new Date().getTime();
                dataObj.birthday=String.valueOf(birthday.getText());

                print.child(String.valueOf(invoiceNo+1)).setValue(dataObj);

                if (dataObj.schoolyear.isEmpty()) {
                    schoolyear.setError("School year is required!");
                    schoolyear.requestFocus();
                    return;

                }

                printPDF();
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
    //this is null

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
    private  void printPDF(){
        PdfDocument myPdfDocument=new PdfDocument();
        Paint paint=new Paint();
        PdfDocument.PageInfo myPageInfo1= new PdfDocument.PageInfo.Builder(596,842,1).create();
        PdfDocument.Page myPage1=myPdfDocument.startPage(myPageInfo1);
        Canvas canvas=myPage1.getCanvas();
        //
        File file =new File(this.getExternalFilesDir("/"),"Form.pdf");
        //
        canvas.drawBitmap(scaledbmp,(myPageInfo1.getPageWidth()/2)-20,10, paint);




        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(14.0f);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.BOLD));
        canvas.drawText("Cainta Catholic College",myPageInfo1.getPageWidth()/2,65,paint);
        paint.setTextSize(11.0f);
        canvas.drawText("DESCRIPTION: ",myPageInfo1.getPageWidth()/2,260,paint);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.NORMAL));
        canvas.drawText(""+des1.getText(),myPageInfo1.getPageWidth()/2,288,paint);
        canvas.drawText(""+des2.getText(),myPageInfo1.getPageWidth()/2,316,paint);
        canvas.drawText(""+des3.getText(),myPageInfo1.getPageWidth()/2,344,paint);
        canvas.drawText(""+des4.getText(),myPageInfo1.getPageWidth()/2,372,paint);
        canvas.drawText(""+des5.getText(),myPageInfo1.getPageWidth()/2,400,paint);
        canvas.drawText(""+des6.getText(),myPageInfo1.getPageWidth()/2,428,paint);
        canvas.drawText(""+des7.getText(),myPageInfo1.getPageWidth()/2,456,paint);
        canvas.drawText(""+des8.getText(),myPageInfo1.getPageWidth()/2,484,paint);
        canvas.drawText(""+des9.getText(),myPageInfo1.getPageWidth()/2,512,paint);
        canvas.drawText(""+des10.getText(),myPageInfo1.getPageWidth()/2,540,paint);


        canvas.drawText("Cainta,Rizal",myPageInfo1.getPageWidth()/2,80,paint);

        paint.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.BOLD));
        canvas.drawText("COLLEGE DEPARTMENT",myPageInfo1.getPageWidth()/2,100,paint);
        canvas.drawText("INFORMATION SHEET",myPageInfo1.getPageWidth()/2,111,paint);

        paint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText("OR#:",myPageInfo1.getPageWidth()-80,85,paint);
        canvas.drawText("SECTION : "+section.getSelectedItem(),myPageInfo1.getPageWidth()-135,191,paint);
        canvas.drawText("UNITS",myPageInfo1.getPageWidth()-35,260,paint);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.NORMAL));
        canvas.drawText(""+units1.getText(),myPageInfo1.getPageWidth()-40,288,paint);
        canvas.drawText(""+units2.getText(),myPageInfo1.getPageWidth()-40,316,paint);
        canvas.drawText(""+units3.getText(),myPageInfo1.getPageWidth()-40,344,paint);
        canvas.drawText(""+units4.getText(),myPageInfo1.getPageWidth()-40,372,paint);
        canvas.drawText(""+units5.getText(),myPageInfo1.getPageWidth()-40,400,paint);
        canvas.drawText(""+units6.getText(),myPageInfo1.getPageWidth()-40,428,paint);
        canvas.drawText(""+units7.getText(),myPageInfo1.getPageWidth()-40,456,paint);
        canvas.drawText(""+units8.getText(),myPageInfo1.getPageWidth()-40,484,paint);
        canvas.drawText(""+units9.getText(),myPageInfo1.getPageWidth()-40,512,paint);
        canvas.drawText(""+units10.getText(),myPageInfo1.getPageWidth()-40,540,paint);


        paint.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.BOLD));
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText("NAME: "+name.getText(),40,140,paint);
        canvas.drawText("EMAIL: "+gmail.getText(),40,157,paint);
        canvas.drawText("COURSE: "+course.getText(),40,174,paint);
        canvas.drawText("YEAR & SEM: "+semester.getSelectedItem(),40,191,paint);
        //
        canvas.drawText("MODE OF PAYMENT: Plan "+mop.getSelectedItem(),40,208,paint);
        canvas.drawText("CODE",40,260,paint);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.NORMAL));
        canvas.drawText(""+SC1.getText(),40,288,paint);
        canvas.drawText(""+SC2.getText(),40,316,paint);
        canvas.drawText(""+SC3.getText(),40,344,paint);
        canvas.drawText(""+SC4.getText(),40,372,paint);
        canvas.drawText(""+SC5.getText(),40,400,paint);
        canvas.drawText(""+SC6.getText(),40,428,paint);
        canvas.drawText(""+SC7.getText(),40,456,paint);
        canvas.drawText(""+SC8.getText(),40,484,paint);
        canvas.drawText(""+SC9.getText(),40,512,paint);
        canvas.drawText(""+SC10.getText(),40,540,paint);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.BOLD));
        canvas.drawText("NOTE:",40,600,paint);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.NORMAL));
        canvas.drawLine(80,600,myPageInfo1.getPageWidth()-35,600,paint);
        canvas.drawLine(40,630,myPageInfo1.getPageWidth()-35,630,paint);

        canvas.drawLine(40,655,myPageInfo1.getPageWidth()-35,655,paint);

        canvas.drawLine(40,705,200,705,paint);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.BOLD));
        canvas.drawText("Adviser",100,720,paint);

        one=Integer.parseInt(units1.getText().toString());
        two=Integer.parseInt(units2.getText().toString());
        three=Integer.parseInt(units3.getText().toString());
        four=Integer.parseInt(units4.getText().toString());
        five=Integer.parseInt(units5.getText().toString());
        six=Integer.parseInt(units6.getText().toString());
        seven=Integer.parseInt(units7.getText().toString());
        eight=Integer.parseInt(units8.getText().toString());
        nine=Integer.parseInt(units9.getText().toString());
        ten=Integer.parseInt(units10.getText().toString());

        paint.setTextAlign(Paint.Align.RIGHT);
        total=one+two+three+four+five+six+seven+eight+nine+ten;
        canvas.drawText(""+total,myPageInfo1.getPageWidth()-40,562,paint);



























        myPdfDocument.finishPage(myPage1);




        try{
            myPdfDocument.writeTo(new FileOutputStream(file));
        }catch (IOException e){
            e.printStackTrace();
        }
        myPdfDocument.close();



    }

}

