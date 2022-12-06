package com.example.cccpre_enrollmentapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.Calendar;

public class registerUser extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private FirebaseAuth mAuth;
    private EditText name, pass,studentnumber, emailadd,add,emergencynumber,emergencyname,fname,mname,num;
    private ProgressBar progressbar;
    private Button registeruser,date, tr;
    private ImageButton Back;
    private String userID;
    private FirebaseUser user;
    private TextView SC1, SC2, SC3, SC4, SC5, SC6, SC7, SC8, SC9, SC10;
    private  TextView g1, g2, g3, g4, g5, g6, g7, g8, g9, g10;
    private  TextView des1, des2, des3, des4, des5, des6, des7, des8, des9, des10;
    private TextView units1, units2, units3, units4, units5, units6, units7, units8, units9, units10;
    private static final String TAG="registerUser";
    private Button DateButton;
    private DatePickerDialog datePickerDialog;
    private Spinner course;
    private ImageView profile_pic;
    private  FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference print = FirebaseDatabase.getInstance().getReference("User");
    DatabaseReference course_curriculum =FirebaseDatabase.getInstance().getReference( "course_curriculum");
    Student_Grades dataObj = new Student_Grades();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        //GRADES TABLE ID
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

        units1 = findViewById(R.id.unit1);
        units2 = findViewById(R.id.unit2);
        units3 = findViewById(R.id.unit3);
        units4 = findViewById(R.id.unit4);
        units5 = findViewById(R.id.unit5);
        units6 = findViewById(R.id.unit6);
        units7 = findViewById(R.id.unit7);
        units8 = findViewById(R.id.unit8);
        units9 = findViewById(R.id.unit9);
        units10 = findViewById(R.id.unit10);


        g1 = findViewById(R.id.sched1);
        g2 = findViewById(R.id.sched2);
        g3 = findViewById(R.id.sched3);
        g4 = findViewById(R.id.sched4);
        g5 = findViewById(R.id.sched5);
        g6 = findViewById(R.id.sched6);
        g7 = findViewById(R.id.sched7);
        g8 = findViewById(R.id.sched8);
        g9 = findViewById(R.id.sched9);
        g10 = findViewById(R.id.sched10);
        g1.setText("");
        g2.setText("");
        g3.setText("");
        g4 .setText("");
        g5 .setText("");
        g6.setText("");
        g8.setText("");
        g9.setText("");
        g10.setText("");


        user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();


        //user profile id

        Toast.makeText(this, "You can register now", Toast.LENGTH_SHORT).show();
        mAuth = FirebaseAuth.getInstance();
        name = findViewById(R.id.fullname);
        pass = findViewById(R.id.password);
        emailadd = findViewById(R.id.email);
        add=findViewById(R.id.address);
        num=findViewById(R.id.number);
        date=findViewById(R.id.date);
        fname=findViewById(R.id.fathername);
        emergencynumber=findViewById(R.id.emergencycontactnumber);
        mname=findViewById(R.id.mothername);
        emergencyname=findViewById(R.id.emergencyname);
        studentnumber=findViewById(R.id.studentnumber);


        registeruser = findViewById(R.id.registeruser);
        registeruser.setOnClickListener(this);

        Back = findViewById(R.id.back);
        Back.setOnClickListener(this);

        initDatePicker();
        DateButton= findViewById(R.id.date);
        DateButton.setText(getTodaysDate());

        progressbar= findViewById(R.id.progressbar);


        course = findViewById(R.id.course);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.courses, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        course.setAdapter(adapter);
        course.setOnItemSelectedListener(this);

        profile_pic=findViewById(R.id.profile);
        user = FirebaseAuth.getInstance().getCurrentUser();

        ///////// //retrieve the course and curriculum for grades/////////////////

        course_curriculum.child("bscs").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String code1= snapshot.child("first_year").child("first_sem").child("subject1").child("code").getValue().toString();
                String code2= snapshot.child("first_year").child("first_sem").child("subject2").child("code").getValue().toString();
                String code3= snapshot.child("first_year").child("first_sem").child("subject3").child("code").getValue().toString();
                String code4= snapshot.child("first_year").child("first_sem").child("subject4").child("code").getValue().toString();
                String code5= snapshot.child("first_year").child("first_sem").child("subject5").child("code").getValue().toString();
                String code6= snapshot.child("first_year").child("first_sem").child("subject6").child("code").getValue().toString();
                String code7= snapshot.child("first_year").child("first_sem").child("subject7").child("code").getValue().toString();
                String code8= snapshot.child("first_year").child("first_sem").child("subject8").child("code").getValue().toString();
                String code9= snapshot.child("first_year").child("first_sem").child("subject9").child("code").getValue().toString();
                String code10= snapshot.child("first_year").child("first_sem").child("subject10").child("code").getValue().toString();
                SC1.setText(code1);
                SC2.setText(code2);
                SC3.setText(code3);
                SC4.setText(code4);
                SC5.setText(code5);
                SC6.setText(code6);
                SC7.setText(code7);
                SC8.setText(code8);
                SC9.setText(code9);
                SC10.setText(code10);
              /*  String code1_2sem= snapshot.child("first_year").child("first_sem").child("subject2").child("code").getValue().toString().trim();
                String code2_2sem= snapshot.child("first_year").child("first_sem").child("subject2").child("code").getValue().toString();
                String code3_2sem= snapshot.child("first_year").child("first_sem").child("subject3").child("code").getValue().toString();
                String code4_2sem= snapshot.child("first_year").child("first_sem").child("subject4").child("code").getValue().toString();
                String code5_2sem= snapshot.child("first_year").child("first_sem").child("subject5").child("code").getValue().toString();
                String code6_2sem= snapshot.child("first_year").child("first_sem").child("subject6").child("code").getValue().toString();
                String code7_2sem= snapshot.child("first_year").child("first_sem").child("subject7").child("code").getValue().toString();
                String code8_2sem= snapshot.child("first_year").child("first_sem").child("subject8").child("code").getValue().toString();
                String code9_2sem= snapshot.child("first_year").child("first_sem").child("subject9").child("code").getValue().toString();
                String code10_2sem= snapshot.child("first_year").child("first_sem").child("subject10").child("code").getValue().toString();
                SC1.setText(code1_2sem);
                SC2.setText(code2_2sem);
                SC3.setText(code3_2sem);
                SC4.setText(code4_2sem);
                SC5.setText(code5_2sem);
                SC6.setText(code6_2sem);
                SC7.setText(code7_2sem);
                SC8.setText(code8_2sem);
                SC9.setText(code9_2sem);
                SC10.setText(code10_2sem);*/
                String D1= snapshot.child("first_year").child("first_sem").child("subject2").child("descriptive_title").getValue().toString().trim();
                String D2= snapshot.child("first_year").child("first_sem").child("subject2").child("descriptive_title").getValue().toString();
                String D3= snapshot.child("first_year").child("first_sem").child("subject3").child("descriptive_title").getValue().toString();
                String D4= snapshot.child("first_year").child("first_sem").child("subject4").child("descriptive_title").getValue().toString();
                String D5= snapshot.child("first_year").child("first_sem").child("subject5").child("descriptive_title").getValue().toString();
                String D6= snapshot.child("first_year").child("first_sem").child("subject6").child("descriptive_title").getValue().toString();
                String D7= snapshot.child("first_year").child("first_sem").child("subject7").child("descriptive_title").getValue().toString();
                String D8= snapshot.child("first_year").child("first_sem").child("subject8").child("descriptive_title").getValue().toString();
                String D9= snapshot.child("first_year").child("first_sem").child("subject9").child("descriptive_title").getValue().toString();
                String D10= snapshot.child("first_year").child("first_sem").child("subject10").child("descriptive_title").getValue().toString();
                des1.setText(code1);
                des2.setText(code2);
                des3.setText(code3);
                des4.setText(code4);
                des5.setText(code5);
                des6.setText(code6);
                des7.setText(code7);
                des8.setText(code8);
                des9.setText(code9);
                des10.setText(code10);






            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        profile_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(registerUser.this,upload_photo.class);
                startActivity(intent);

            }
        });
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back:
                startActivity(new Intent(this, login.class));
                break;
            case R.id.registeruser:
                registeruser();
                break;
        }

    }



    private void registeruser() {

        String Email = emailadd.getText().toString().trim();
        String Password = pass.getText().toString().trim();
        String Course = course.getSelectedItem().toString().trim();
        String Name = name.getText().toString().trim();
        String Contact_Number= num.getText().toString().trim();
        String Birthday=date.getText().toString().trim();
        String Address= add.getText().toString().trim();
        String Mother =mname.getText().toString().trim();
        String Father =fname.getText().toString().trim();
        String Student_number=studentnumber.getText().toString().trim();
        String Emergency_name=emergencyname.getText().toString().trim();
        String Emergency_number=emergencynumber.getText().toString().trim();
        String Gender="";


        if (Name.isEmpty()) {
            name.setError("Fullname is required!");
            name.requestFocus();
            return;

        } if (Password.isEmpty()) {
            pass.setError("Password is required!");
            pass.requestFocus();
            return;

        } if (Email.isEmpty()) {
            emailadd.setError("Email is required!");
            emailadd.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            emailadd.setError("please provide valid email");
            emailadd.requestFocus();
            return;
        }
        if (Password.length() < 6) {
            pass.setError("min password length should be 6 characters");
            pass.requestFocus();
            return;
        }
        if (Address.isEmpty()) {
            add.setError("Address is required!");
            add.requestFocus();
            return;

        }
        if (Birthday.isEmpty()) {
            date.setError("Birthday is required!");
            date.requestFocus();
            return;

        }
        if (Contact_Number.isEmpty()) {
            num.setError("Contact # is required!");
            num.requestFocus();
            return;

        }
        if (Emergency_number.isEmpty()) {
            emergencynumber.setError("Contact # is required!");
            emergencynumber.requestFocus();
            return;

        }
        if (Emergency_name.isEmpty()) {
            num.setError("Contact # is required!");
            num.requestFocus();
            return;

        }

        if (Mother.isEmpty()) {
            mname.setError("Contact # is required!");
            mname.requestFocus();
            return;

        }
        if (Father.isEmpty()) {
            fname.setError("Contact # is required!");
            fname.requestFocus();
            return;

        }
        if(Student_number.length()!=8){
            studentnumber.setError("Invalid Student ");


        }

        if(Emergency_number.length()!=11){
            emergencynumber.setError("Invalid Number, It should be 11 digits");


        }
        if(Contact_Number.length()!=11){
            num.setError("Invalid Number, It should be 11 digits ");

        }

        dataObj.des1 = String.valueOf(des1.getText());
        dataObj.des2 = String.valueOf(des2.getText());
        dataObj.des3 = String.valueOf(des3.getText());
        dataObj.des4 = String.valueOf(des4.getText());
        dataObj.des5 = String.valueOf(des5.getText());
        dataObj.des6 = String.valueOf(des6.getText());
        dataObj.des7 = String.valueOf(des7.getText());
        dataObj.des8 = String.valueOf(des8.getText());
        dataObj.des9= String.valueOf(des9.getText());
        dataObj.des10 = String.valueOf(des10.getText());

        dataObj.unit1=String.valueOf(units1.getText());
        dataObj.unit2=String.valueOf(units2.getText());
        dataObj.unit3=String.valueOf(units3.getText());
        dataObj.unit4=String.valueOf(units4.getText());
        dataObj.unit5=String.valueOf(units5.getText());
        dataObj.unit6=String.valueOf(units6.getText());
        dataObj.unit7=String.valueOf(units7.getText());
        dataObj.unit8=String.valueOf(units8.getText());
        dataObj.unit9=String.valueOf(units9.getText());
        dataObj.unit10=String.valueOf(units10.getText());

        dataObj.sc1=String.valueOf(SC1.getText());
        dataObj.sc2=String.valueOf(SC2.getText());
        dataObj.sc3=String.valueOf(SC3.getText());
        dataObj.sc4=String.valueOf(SC4.getText());
        dataObj.sc5=String.valueOf(SC5.getText());
        dataObj.sc6=String.valueOf(SC6.getText());
        dataObj.sc7=String.valueOf(SC7.getText());
        dataObj.sc8=String.valueOf(SC8.getText());
        dataObj.sc9=String.valueOf(SC9.getText());
        dataObj.sc10=String.valueOf(SC10.getText());
        dataObj.sc1=String.valueOf(SC1.getText());
        dataObj.sc2=String.valueOf(SC2.getText());
        dataObj.sc3=String.valueOf(SC3.getText());
        dataObj.sc4=String.valueOf(SC4.getText());
        dataObj.sc5=String.valueOf(SC5.getText());
        dataObj.sc6=String.valueOf(SC6.getText());
        dataObj.sc7=String.valueOf(SC7.getText());
        dataObj.sc8=String.valueOf(SC8.getText());
        dataObj.sc9=String.valueOf(SC9.getText());
        dataObj.sc10=String.valueOf(SC10.getText());
        dataObj.grade1=String.valueOf(g1.getText());
        dataObj.grade2=String.valueOf(g2.getText());
        dataObj.grade3=String.valueOf(g3.getText());
        dataObj.grade4=String.valueOf(g4.getText());
        dataObj.grade5=String.valueOf(g5.getText());
        dataObj.grade6=String.valueOf(g6.getText());
        dataObj.grade7=String.valueOf(g7.getText());
        dataObj.grade8=String.valueOf(g8.getText());
        dataObj.grade9=String.valueOf(g9.getText());
        dataObj.grade10=String.valueOf(g10.getText());






        progressbar.setVisibility(View.VISIBLE);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(Email, Password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {

                            User user = new User(Name, Email, Student_number, Course, Address, Birthday, Contact_Number, Mother, Emergency_number, Father, Emergency_name);
                            FirebaseDatabase.getInstance().getReference("User")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                print.child(userID).child("grades").setValue(dataObj);
                                                Toast.makeText(registerUser.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                                                Intent intent= new Intent(registerUser.this,login.class);
                                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK );
                                                startActivity(intent);
                                                finish();
                                            } else {
                                                Toast.makeText(registerUser.this, "Failed to register! try again.", Toast.LENGTH_LONG).show();
                                            }
                                            progressbar.setVisibility(View.GONE);

                                        }
                                    });

                        }else {
                            try {
                                throw task.getException();
                            }catch (FirebaseAuthWeakPasswordException e){
                                pass.setError("Your password is too week. User a mix of alphabets,numbers and special character");
                                pass.requestFocus();
                            }catch (FirebaseAuthInvalidCredentialsException e){
                                emailadd.setError("Your email is invalid or already in user. Kindly re-enter.");
                                emailadd.requestFocus();
                            }catch (FirebaseAuthUserCollisionException e){
                                pass.setError("User is already registered with this email.");
                                pass.requestFocus();
                            }catch (Exception e){
                                Log.e(TAG, e.getMessage());
                                Toast.makeText(registerUser.this,e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                            progressbar.setVisibility(View.GONE);
                        }
                    }
                });

        initDatePicker();
        DateButton=findViewById(R.id.date);

    }


    private String getTodaysDate() {
        Calendar cal= Calendar.getInstance();
        int year=cal.get(Calendar.YEAR);
        int month=cal.get(Calendar.MONTH);
        month=month+1;
        int day=cal.get(Calendar.DAY_OF_MONTH);

        return makeDateString(day,month,year);
    }

    private  void initDatePicker(){
        DatePickerDialog.OnDateSetListener dataSetListerner= new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month=month+1;
                String date=makeDateString(day,month,year);
                DateButton.setText(date);
            }
        };
        Calendar cal= Calendar.getInstance();
        int year=cal.get(Calendar.YEAR);
        int day=cal.get(Calendar.DAY_OF_MONTH);
        int month=cal.get(Calendar.MONTH);
        int style= AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog =new DatePickerDialog(this,style, dataSetListerner,year, month ,day);

    }

    private String makeDateString(int day, int month, int year) {
        return getMonthFormat(month)+ " "+ day+" "+" "+year;
    }

    private String getMonthFormat(int month) {
        if(month==1)
            return" AN";
        if(month==2)
            return"FEB";
        if(month==3)
            return"MAR";
        if(month==4)
            return "APR";
        if(month==5)
            return"MAY";
        if(month==6)
            return" JUN";
        if(month==7)
            return"JUL";
        if(month==8)
            return"AUG";
        if(month==9)
            return"SEP";
        if(month==10)
            return"OCT";
        if(month==11)
            return"NOV";
        if(month==12)
            return"DEC";

        //default
        return "JAN";

    }

    public void opendatePicker(View view) {
        datePickerDialog.show();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text =adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    ///////////////////////*GRADES SECTION*////////////////////////

    private  void Bscsgrades(){



    }


}
