package com.example.cccpre_enrollmentapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
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
import com.google.firebase.database.collection.LLRBNode;

import java.util.Calendar;

public class registerUser extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private FirebaseAuth mAuth;
    private EditText name, pass, studentnumber, emailadd, add, emergencynumber, emergencyname, fname, mname, num;
    private ProgressBar progressbar;
    private Button registeruser, date, tr;
    private ImageButton Back;
    private FirebaseUser user;
    String gradesRetrieve = null;


    private String units1 = "", units2 = null, units3 = null, units4 = null, units5 = null, units6 = null, units7 = null, units8 = null, units9 = null, units10 = null; //1y 1sem
    private String units1_12 = null, units2_12 = null, units3_12 = null, units4_12 = null, units5_12 = null, units6_12 = null, units7_12 = null, units8_12 = null, units9_12 = null, units10_12 = null; //1st 2sem
    private String units1_21 = null, units2_21 = null, units3_21 = null, units4_21 = null, units5_21 = null, units6_21 = null, units7_21 = null, units8_21 = null, units9_21 = null, units10_21 = null; //2y 1sem
    private String units1_22 = null, units2_22 = null, units3_22 = null, units4_22 = null, units5_22 = null, units6_22 = null, units7_22 = null, units8_22 = null, units9_22 = null, units10_22 = null; //2y 2sem
    private String units1_31 = null, units2_31 = null, units3_31 = null, units4_31 = null, units5_31 = null, units6_31 = null, units7_31 = null, units8_31 = null, units9_31 = null, units10_31 = null; //3y 1sem
    private String units1_32 = null, units2_32 = null, units3_32 = null, units4_32 = null, units5_32 = null, units6_32 = null, units7_32 = null, units8_32 = null, units9_32 = null, units10_32 = null; //3y 2sem
    private String units1_41 = null, units2_41 = null, units3_41 = null, units4_41 = null, units5_41 = null, units6_41 = null, units7_41 = null, units8_41 = null, units9_41 = null, units10_41 = null; //4y 1sem
    private String units1_42 = null, units2_42 = null, units3_42 = null, units4_42 = null, units5_42 = null, units6_42 = null, units7_42 = null, units8_42 = null, units9_42 = null, units10_42 = null; //4y 2sem

    private String des1 = null, des2 = null, des3 = null, des4 = null, des5 = null, des6 = null, des7 = null, des8 = null, des9 = null, des10 = null; //1y 1sem
    private String des1_2 = null, des2_2 = null, des3_2 = null, des4_2 = null, des5_2 = null, des6_2 = null, des7_2 = null, des8_2 = null, des9_2 = null, des10_2 = null; //1y 2sem
    private String des1_21 = null, des2_21 = null, des3_21 = null, des4_21 = null, des5_21 = null, des6_21 = null, des7_21 = null, des8_21 = null, des9_21 = null, des10_21 = null; //2y 1sem
    private String des1_22 = null, des2_22 = null, des3_22 = null, des4_22 = null, des5_22 = null, des6_22 = null, des7_22 = null, des8_22 = null, des9_22, des10_22 = null; //2y 2sem
    private String des1_31 = null, des2_31 = null, des3_31 = null, des4_31 = null, des5_31 = null, des6_31 = null, des7_31 = null, des8_31 = null, des9_31 = null, des10_31 = null; //3y 1sem
    private String des1_32 = null, des2_32 = null, des3_32 = null, des4_32 = null, des5_32 = null, des6_32 = null, des7_32 = null, des8_32 = null, des9_32 = null, des10_32 = null; //3y 2sem
    private String des1_41 = null, des2_41 = null, des3_41 = null, des4_41 = null, des5_41 = null, des6_41 = null, des7_41 = null, des8_41 = null, des9_41 = null, des10_41 = null; //4y 1sem
    private String des1_42 = null, des2_42 = null, des3_42 = null, des4_42 = null, des5_42 = null, des6_42 = null, des7_42 = null, des8_42 = null, des9_42 = null, des10_42 = null; //4y 2sem


    private String SC1 = null, SC2 = null, SC3 = null, SC4 = null, SC5 = null, SC6 = null, SC7 = null, SC8 = null, SC9 = null, SC10 = null; //1y 1sem
    private String SC1_2 = null, SC2_2 = null, SC3_2 = null, SC4_2 = null, SC5_2 = null, SC6_2 = null, SC7_2 = null, SC8_2 = null, SC9_2 = null, SC10_2 = null; //1y 2sem
    private String SC1_21 = null, SC2_21 = null, SC3_21 = null, SC4_21 = null, SC5_21 = null, SC6_21 = null, SC7_21 = null, SC8_21 = null, SC9_21 = null, SC10_21 = null; //2y 1sem
    private String SC1_22 = null, SC2_22 = null, SC3_22 = null, SC4_22 = null, SC5_22 = null, SC6_22 = null, SC7_22 = null, SC8_22 = null, SC9_22 = null, SC10_22 = null; //2y 2sem
    private String SC1_31 = null, SC2_31 = null, SC3_31 = null, SC4_31 = null, SC5_31 = null, SC6_31 = null, SC7_31 = null, SC8_31 = null, SC9_31 = null, SC10_31 = null; //3y 1sem
    private String SC1_32 = null, SC2_32 = null, SC3_32 = null, SC4_32 = null, SC5_32 = null, SC6_32 = null, SC7_32 = null, SC8_32 = null, SC9_32 = null, SC10_32 = null; //3y 2sem
    private String SC1_41 = null, SC2_41 = null, SC3_41 = null, SC4_41 = null, SC5_41 = null, SC6_41 = null, SC7_41 = null, SC8_41 = null, SC9_41 = null, SC10_41 = null; //4y 1sem
    private String SC1_42 = null, SC2_42 = null, SC3_42 = null, SC4_42 = null, SC5_42 = null, SC6_42 = null, SC7_42 = null, SC8_42 = null, SC9_42 = null, SC10_42 = null; //4y 2sem

    private String g1 = "0.0", g2 = "0.0", g3 = "0.0", g4 ="0.0", g5 = "0.0", g6 ="0.0", g7 = "0.0", g8 = "0.0", g9 = "0.0", g10 = "0.0"; // 1y 1sem
    private String g1_2 ="0.0", g2_2 = "0.0", g3_2 = "0.0", g4_2 = "0.0", g5_2 ="0.0", g6_2 = "0.0", g7_2 = "0.0", g8_2 = "0.0", g9_2 = "0.0", g10_2 = "0.0";  // 1y 2sem
    private String g1_21 ="0.0", g2_21 = "0.0", g3_21 = "0.0", g4_21 = "0.0", g5_21 = "0.0", g6_21 = "0.0", g7_21 = "0.0", g8_21 = "0.0", g9_21 = "0.0", g10_21 = "0.0"; // 2y 1sem
    private String g1_22 = "0.0", g2_22 = "0.0", g3_22 = "0.0", g4_22 = "0.0", g5_22 = "0.0", g6_22 = "0.0", g7_22 = "0.0", g8_22 = "0.0", g9_22 = "0.0", g10_22 = "0.0"; // 2y 2sem
    private String g1_31 = "0.0", g2_31 = "0.0", g3_31 = "0.0", g4_31 ="0.0", g5_31 = "0.0", g6_31 = "0.0", g7_31 = "0.0", g8_31 = "0.0", g9_31 = "0.0", g10_31 = "0.0"; // 3y 1sem
    private String g1_32 ="0.0", g2_32 = "0.0", g3_32 = "0.0", g4_32 ="0.0", g5_32 ="0.0", g6_32 = "0.0", g7_32 = "0.0", g8_32 = "0.0", g9_32 = "0.0", g10_32 = "0.0"; // 3y 2sem
    private String g1_41 = "0.0", g2_41 = "0.0", g3_41 = "0.0", g4_41 ="0.0", g5_41 = "0.0", g6_41 = "0.0", g7_41 = "0.0", g8_41 = "0.0", g9_41 = "0.0", g10_41 = "0.0"; // 4y 1sem
    private String g1_42 = "0.0", g2_42 = "0.0", g3_42 = "0.0", g4_42 ="0.0", g5_42 = "0.0", g6_42 = "0.0", g7_42 = "0.0", g8_42 = "0.0", g9_42 = "0.0", g10_42 = "0.0"; // 4y 2sem


    private static final String TAG = "registerUser";
    private Button DateButton;
    private DatePickerDialog datePickerDialog;
    private Spinner course;
    private ImageView profile_pic;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference grade1 = FirebaseDatabase.getInstance().getReference("User");
    DatabaseReference course_curriculum = FirebaseDatabase.getInstance().getReference("course_curriculum");
    Student_Grades dataObj = new Student_Grades();
        Student_Grades1_2 dataObj1 = new Student_Grades1_2();
    Student_Grades2_1 dataObj21 = new Student_Grades2_1();
    Student_Grades2_2 dataObj22 = new Student_Grades2_2();
    Student_Grades3_1 dataObj31 = new Student_Grades3_1();
    Student_Grades3_2 dataObj32 = new Student_Grades3_2();
    Student_Grades4_2 dataObj42 = new Student_Grades4_2();
    Student_Grades4_1 dataObj41 = new Student_Grades4_1();
    RetrieveGrades retrieveGrades = new RetrieveGrades();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);


        //user profile id

        Toast.makeText(this, "You can register now", Toast.LENGTH_SHORT).show();
        mAuth = FirebaseAuth.getInstance();
        name = findViewById(R.id.fullname);
        name.setSingleLine(true);
        pass = findViewById(R.id.password);
        emailadd = findViewById(R.id.email);
        date=findViewById(R.id.date);
        emailadd.setSingleLine(true);
        add = findViewById(R.id.address);
        add.setSingleLine(true);
        num = findViewById(R.id.number);
        date = findViewById(R.id.date);
        fname = findViewById(R.id.fathername);
       fname.setSingleLine(true);
        emergencynumber = findViewById(R.id.emergencycontactnumber);
        mname = findViewById(R.id.mothername);
       mname.setSingleLine(true);
        emergencyname = findViewById(R.id.emergencyname);
        studentnumber = findViewById(R.id.studentnumber);


        registeruser = findViewById(R.id.registeruser);
        registeruser.setOnClickListener(this);

        Back = findViewById(R.id.back);
        Back.setOnClickListener(this);

        initDatePicker();
        DateButton = findViewById(R.id.date);
        DateButton.setText(getTodaysDate());

        progressbar = findViewById(R.id.progressbar);


        course = findViewById(R.id.course);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.courses, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        course.setAdapter(adapter);
        course.setOnItemSelectedListener(this);

        profile_pic = findViewById(R.id.profile);
        user = FirebaseAuth.getInstance().getCurrentUser();


        profile_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(registerUser.this, upload_photo.class);
                startActivity(intent);

            }
        });


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
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
        String Contact_Number = num.getText().toString().trim();
        String Birthday = date.getText().toString().trim();
        String Address = add.getText().toString().trim();
        String Mother = mname.getText().toString().trim();
        String Father = fname.getText().toString().trim();
        String Student_number = studentnumber.getText().toString().trim();
        String Emergency_name = emergencyname.getText().toString().trim();
        String Emergency_number = emergencynumber.getText().toString().trim();
        String Gender = "";
        String verify="not verify";


        if (Name.isEmpty()) {
            name.setError("Fullname is required!");
            name.requestFocus();
            return;

        }
        if (Password.isEmpty()) {
            pass.setError("Password is required!");
            pass.requestFocus();
            return;

        }
        if (Email.isEmpty()) {
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
        if (Student_number.length() != 8) {
            studentnumber.setError("Invalid Student ");
            studentnumber.requestFocus();
            return;


        }

        if (Emergency_number.length() != 11) {
            emergencynumber.setError("Invalid Number, It should be 11 digits");
            emergencynumber.requestFocus();
            return;


        }
        if (Contact_Number.length() != 11) {
            num.setError("Invalid Number, It should be 11 digits ");
            num.requestFocus();
            return;

        }

        if (Course.equals("Bachelor of Science in Computer Science")) {
            Bscsgradeview();
            savegrades();

        } else if (Course.equals("Bachelor of Science in Business Administration")) {
            bsbagradeview();
            savegrades();
            ;
        } else if (Course.equals("Bachelor of Science in Office Administration")) {
            BSOAgradeview();
            savegrades();
        } else if (Course.equals("Bachelor of Elementary Education")) {
            beedgradeview();
            savegrades();
        } else if (Course.equals("Bachelor of Arts in Religious Education")) {
            abreedgradeview();
            savegrades();
        } else if (Course.equals("Bachelor of Elementary Education")) {
            //bsedgradeview();
            savegrades();
        }
        if (Course.equals("--Select your Course--")) {

            TextView errorText = (TextView)course.getSelectedView();
            errorText.setError("");
            errorText.setTextColor(Color.RED);
            errorText.requestFocus();
            return;
        }


        progressbar.setVisibility(View.VISIBLE);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(Email, Password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            User user = new User(Name, Email, Student_number, Course, Address, Birthday, Contact_Number, Mother, Emergency_number, Father, Emergency_name,verify);
                            FirebaseDatabase.getInstance().getReference("User")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                savegrades();
                                                if (task.isSuccessful()) {
                                                    //1st year

                                                    grade1.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("grades").child("first_year").child("first_sem").setValue(dataObj);
                                                    grade1.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("grades").child("first_year").child("second_sem").setValue(dataObj1);
                                                    //2nd year
                                                    grade1.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("grades").child("second_year").child("first_sem").setValue(dataObj21);
                                                    grade1.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("grades").child("second_year").child("second_sem").setValue(dataObj22);

                                                    //3rd year
                                                    grade1.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("grades").child("third_year").child("first_sem").setValue(dataObj31);
                                                    grade1.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("grades").child("third_year").child("second_sem").setValue(dataObj32);
                                                    //4th year
                                                    grade1.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("grades").child("fourth_year").child("first_sem").setValue(dataObj41);
                                                    grade1.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("grades").child("fourth_year").child("second_sem").setValue(dataObj42);

                                                }else {
                                                    progressbar.setVisibility(View.GONE);
                                                    Toast.makeText(registerUser.this, "Failed to register! try again.", Toast.LENGTH_LONG).show();

                                                };

                                                Toast.makeText(registerUser.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(registerUser.this, login.class);
                                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                                startActivity(intent);
                                                finish();
                                            } else {
                                                Toast.makeText(registerUser.this, "Failed to register! try again.", Toast.LENGTH_LONG).show();
                                            }
                                            progressbar.setVisibility(View.GONE);

                                        }
                                    });

                        } else {
                            try {
                                throw task.getException();
                            } catch (FirebaseAuthWeakPasswordException e) {
                                pass.setError("Your password is too week. User a mix of alphabets,numbers and special character");
                                pass.requestFocus();
                            } catch (FirebaseAuthInvalidCredentialsException e) {
                                emailadd.setError("Your email is invalid or already in user. Kindly re-enter.");
                                emailadd.requestFocus();
                            } catch (FirebaseAuthUserCollisionException e) {
                                emailadd.setError("User is already registered with this email.");
                                emailadd.requestFocus();
                            } catch (Exception e) {
                                Log.e(TAG, e.getMessage());
                                Toast.makeText(registerUser.this, e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                            progressbar.setVisibility(View.GONE);
                        }
                    }
                });

        initDatePicker();
        DateButton = findViewById(R.id.date);


    }


    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);

        return makeDateString(day, month, year);
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dataSetListerner = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = makeDateString(day, month, year);
                DateButton.setText(date);
            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH);
        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dataSetListerner, year, month, day);

    }

    private String makeDateString(int day, int month, int year) {
        return getMonthFormat(month) + " " + day + " " + " " + year;
    }

    private String getMonthFormat(int month) {
        if (month == 1)
            return "Jan";
        if (month == 2)
            return "Feb";
        if (month == 3)
            return "Mar";
        if (month == 4)
            return "Apr";
        if (month == 5)
            return "May";
        if (month == 6)
            return "Jun";
        if (month == 7)
            return "Jul";
        if (month == 8)
            return "Aug";
        if (month == 9)
            return "Sep";
        if (month == 10)
            return "Oct";
        if (month == 11)
            return "Nov";
        if (month == 12)
            return "Dec";

        //default
        return "JAN";

    }

    public void opendatePicker(View view) {
        datePickerDialog.show();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    ///////////////////////*GRADES SECTION*////////////////////////

    private void savegrades() {
        retrieveGrades.retrieveGrades = String.valueOf(gradesRetrieve);

        //1st year 2nd sem

        dataObj.sc1 = String.valueOf(SC1);
        dataObj.sc2 = String.valueOf(SC2);
        dataObj.sc3 = String.valueOf(SC3);
        dataObj.sc4 = String.valueOf(SC4);
        dataObj.sc5 = String.valueOf(SC5);
        dataObj.sc6 = String.valueOf(SC6);
        dataObj.sc7 = String.valueOf(SC7);
        dataObj.sc8 = String.valueOf(SC8);
        dataObj.sc9 = String.valueOf(SC9);
        dataObj.sc10 = String.valueOf(SC10);

        dataObj.grade01 = String.valueOf(g1);
        dataObj.grade02 = String.valueOf(g2);
        dataObj.grade03 = String.valueOf(g3);
        dataObj.grade04 = String.valueOf(g4);
        dataObj.grade05 = String.valueOf(g5);
        dataObj.grade06 = String.valueOf(g6);
        dataObj.grade07 = String.valueOf(g7);
        dataObj.grade08 = String.valueOf(g8);
        dataObj.grade09 = String.valueOf(g9);
        dataObj.grade10 = String.valueOf(g10);

        dataObj.unit1= String.valueOf(units1);
        dataObj.unit2= String.valueOf(units2);
        dataObj.unit3= String.valueOf(units3);
        dataObj.unit4= String.valueOf(units4);
        dataObj.unit5= String.valueOf(units5);
        dataObj.unit6= String.valueOf(units6);
        dataObj.unit7= String.valueOf(units7);
        dataObj.unit8= String.valueOf(units8);
        dataObj.unit9= String.valueOf(units9);
        dataObj.unit10= String.valueOf(units10);




        //1st year 2nd sem

        dataObj1.sc1_12 = String.valueOf(SC1_2);
        dataObj1.sc2_12 = String.valueOf(SC2_2);
        dataObj1.sc3_12 = String.valueOf(SC3_2);
        dataObj1.sc4_12 = String.valueOf(SC4_2);
        dataObj1.sc5_12 = String.valueOf(SC5_2);
        dataObj1.sc6_12 = String.valueOf(SC6_2);
        dataObj1.sc7_12 = String.valueOf(SC7_2);
        dataObj1.sc8_12 = String.valueOf(SC8_2);
        dataObj1.sc9_12 = String.valueOf(SC9_2);
        dataObj1.sc10_12 = String.valueOf(SC10_2);

        dataObj1.grade01_12 = String.valueOf(g1_2);
        dataObj1.grade02_12 = String.valueOf(g2_2);
        dataObj1.grade03_12 = String.valueOf(g3_2);
        dataObj1.grade04_12 = String.valueOf(g4_2);
        dataObj1.grade05_12 = String.valueOf(g5_2);
        dataObj1.grade06_12 = String.valueOf(g6_2);
        dataObj1.grade07_12 = String.valueOf(g7_2);
        dataObj1.grade08_12 = String.valueOf(g8_2);
        dataObj1.grade09_12 = String.valueOf(g9_2);
        dataObj1.grade10_12 = String.valueOf(g10_2);

        dataObj1.unit1_12= String.valueOf(units1_12);
        dataObj1.unit2_12= String.valueOf(units2_12);
        dataObj1.unit3_12= String.valueOf(units3_12);
        dataObj1.unit4_12= String.valueOf(units4_12);
        dataObj1.unit5_12= String.valueOf(units5_12);
        dataObj1.unit6_12= String.valueOf(units6_12);
        dataObj1.unit7_12= String.valueOf(units7_12);
        dataObj1.unit8_12= String.valueOf(units8_12);
        dataObj1.unit9_12= String.valueOf(units9_12);
        dataObj1.unit10_12= String.valueOf(units10_12);


        //2nd year 1sem

        dataObj21.sc1_21 = String.valueOf(SC1_21);
        dataObj21.sc2_21 = String.valueOf(SC2_21);
        dataObj21.sc3_21 = String.valueOf(SC3_21);
        dataObj21.sc4_21 = String.valueOf(SC4_21);
        dataObj21.sc5_21 = String.valueOf(SC5_21);
        dataObj21.sc6_21 = String.valueOf(SC6_21);
        dataObj21.sc7_21 = String.valueOf(SC7_21);
        dataObj21.sc8_21 = String.valueOf(SC8_21);
        dataObj21.sc9_21 = String.valueOf(SC9_21);
        dataObj21.sc10_21 = String.valueOf(SC10_21);

        dataObj21.grade01_21 = String.valueOf(g1_21);
        dataObj21.grade02_21 = String.valueOf(g2_21);
        dataObj21.grade03_21 = String.valueOf(g3_21);
        dataObj21.grade04_21 = String.valueOf(g4_21);
        dataObj21.grade05_21 = String.valueOf(g5_21);
        dataObj21.grade06_21 = String.valueOf(g6_21);
        dataObj21.grade07_21 = String.valueOf(g7_21);
        dataObj21.grade08_21 = String.valueOf(g8_21);
        dataObj21.grade09_21 = String.valueOf(g9_21);
        dataObj21.grade10_21 = String.valueOf(g10_21);

        //2nd year 2ndsem
        dataObj22.sc1_22 = String.valueOf(SC1_22);
        dataObj22.sc2_22 = String.valueOf(SC2_22);
        dataObj22.sc3_22 = String.valueOf(SC3_22);
        dataObj22.sc4_22 = String.valueOf(SC4_22);
        dataObj22.sc5_22 = String.valueOf(SC5_22);
        dataObj22.sc6_22 = String.valueOf(SC6_22);
        dataObj22.sc7_22 = String.valueOf(SC7_22);
        dataObj22.sc8_22 = String.valueOf(SC8_22);
        dataObj22.sc9_22 = String.valueOf(SC9_22);
        dataObj22.sc10_22 = String.valueOf(SC10_22);


        dataObj22.grade01_22 = String.valueOf(g1_22);
        dataObj22.grade02_22 = String.valueOf(g2_22);
        dataObj22.grade03_22 = String.valueOf(g3_22);
        dataObj22.grade04_22 = String.valueOf(g4_22);
        dataObj22.grade05_22 = String.valueOf(g5_22);
        dataObj22.grade06_22 = String.valueOf(g6_22);
        dataObj22.grade07_22 = String.valueOf(g7_22);
        dataObj22.grade08_22 = String.valueOf(g8_22);
        dataObj22.grade09_22 = String.valueOf(g9_22);
        dataObj22.grade10_22 = String.valueOf(g10_22);

        //3rd year 1st sem


        dataObj31.sc1_31 = String.valueOf(SC1_31);
        dataObj31.sc2_31 = String.valueOf(SC2_31);
        dataObj31.sc3_31 = String.valueOf(SC3_31);
        dataObj31.sc4_31 = String.valueOf(SC4_31);
        dataObj31.sc5_31 = String.valueOf(SC5_31);
        dataObj31.sc6_31 = String.valueOf(SC6_31);
        dataObj31.sc7_31 = String.valueOf(SC7_31);
        dataObj31.sc8_31 = String.valueOf(SC8_31);
        dataObj31.sc9_31 = String.valueOf(SC9_31);
        dataObj31.sc10_31 = String.valueOf(SC10_31);


        dataObj31.grade01_31 = String.valueOf(g1_31);
        dataObj31.grade02_31 = String.valueOf(g2_31);
        dataObj31.grade03_31 =String.valueOf(g3_31);
        dataObj31.grade04_31 = String.valueOf(g4_31);
        dataObj31.grade05_31 = String.valueOf(g5_31);
        dataObj31.grade06_31 = String.valueOf(g6_31);
        dataObj31.grade07_31 = String.valueOf(g7_31);
        dataObj31.grade08_31 = String.valueOf(g8_31);
        dataObj31.grade09_31 = String.valueOf(g9_31);
        dataObj31.grade10_31 = String.valueOf(g10_31);

        //3rd year 2sem


        dataObj32.sc1_32 = String.valueOf(SC1_32);
        dataObj32.sc2_32 = String.valueOf(SC2_32);
        dataObj32.sc3_32 = String.valueOf(SC3_32);
        dataObj32.sc4_32 = String.valueOf(SC4_32);
        dataObj32.sc5_32 = String.valueOf(SC5_32);
        dataObj32.sc6_32 = String.valueOf(SC6_32);
        dataObj32.sc7_32 = String.valueOf(SC7_32);
        dataObj32.sc8_32 = String.valueOf(SC8_32);
        dataObj32.sc9_32 = String.valueOf(SC9_32);
        dataObj32.sc10_32 = String.valueOf(SC10_32);


        dataObj32.grade01_32 = String.valueOf(g1_32);
        dataObj32.grade02_32 = String.valueOf(g2_32);
        dataObj32.grade03_32 = String.valueOf(g3_32);
        dataObj32.grade04_32 = String.valueOf(g4_32);
        dataObj32.grade05_32 = String.valueOf(g5_32);
        dataObj32.grade06_32 = String.valueOf(g6_32);
        dataObj32.grade07_32 = String.valueOf(g7_32);
        dataObj32.grade08_32 = String.valueOf(g8_32);
        dataObj32.grade09_32 = String.valueOf(g9_32);
        dataObj32.grade10_32 = String.valueOf(g10_32);

        //3rd year 1st sem


        dataObj41.sc1_41 = String.valueOf(SC1_41);
        dataObj41.sc2_41 = String.valueOf(SC2_41);
        dataObj41.sc3_41 = String.valueOf(SC3_41);
        dataObj41.sc4_41 = String.valueOf(SC4_41);
        dataObj41.sc5_41 = String.valueOf(SC5_41);
        dataObj41.sc6_41 = String.valueOf(SC6_41);
        //      dataObj41.sc7_41=String.valueOf(SC7_41);
        dataObj41.sc8_41 = String.valueOf(SC8_41);
        dataObj41.sc9_41 = String.valueOf(SC9_41);
        dataObj41.sc10_41 = String.valueOf(SC10_41);


        dataObj41.grade01_41 = String.valueOf(g1_41);
        dataObj41.grade02_41 = String.valueOf(g2_41);
        dataObj41.grade03_41 = String.valueOf(g3_41);
        dataObj41.grade04_41 = String.valueOf(g4_41);
        dataObj41.grade05_41 = String.valueOf(g5_41);
        dataObj41.grade06_41 = String.valueOf(g6_41);
        dataObj41.grade07_41 = String.valueOf(g7_41);
        dataObj41.grade08_41 = String.valueOf(g8_41);
        dataObj41.grade09_41 = String.valueOf(g9_41);
        dataObj41.grade10_41 = String.valueOf(g10_41);

        //3rd year 2sem


        dataObj42.sc1_42 = String.valueOf(SC1_42);
        dataObj42.sc2_42 = String.valueOf(SC2_42);
        dataObj42.sc3_42 = String.valueOf(SC3_42);
        dataObj42.sc4_42 = String.valueOf(SC4_42);
        dataObj42.sc5_42 = String.valueOf(SC5_42);
        dataObj42.sc6_42 = String.valueOf(SC6_42);
        //  dataObj42.sc7_42=String.valueOf(SC7_42.getText());
        dataObj42.sc8_42 = String.valueOf(SC8_42);
        dataObj42.sc9_42 = String.valueOf(SC9_42);
        dataObj42.sc10_42 = String.valueOf(SC10_42);


        dataObj42.grade01_42 = String.valueOf(g1_42);
        dataObj42.grade02_42 = String.valueOf(g2_42);
        dataObj42.grade03_42 = String.valueOf(g3_42);
        dataObj42.grade04_42 = String.valueOf(g4_42);
        dataObj42.grade05_42 = String.valueOf(g5_42);
        dataObj42.grade06_42 = String.valueOf(g6_42);
        dataObj42.grade07_42 = String.valueOf(g7_42);
        dataObj42.grade08_42 = String.valueOf(g8_42);
        dataObj42.grade09_42 = String.valueOf(g9_42);
        dataObj42.grade10_42 = String.valueOf(g10_42);

        //1st year 2nd sem

        dataObj.des1 = String.valueOf(des1);
        dataObj.des2 = String.valueOf(des2);
        dataObj.des3 = String.valueOf(des3);
        dataObj.des4 = String.valueOf(des4);
        dataObj.des5 = String.valueOf(des5);
        dataObj.des6 = String.valueOf(des6);
        dataObj.des7 = String.valueOf(des7);
        dataObj.des8 = String.valueOf(des8);
        dataObj.des9 = String.valueOf(des9);
        dataObj.des10 = String.valueOf(des10);

        dataObj.unit1 = String.valueOf(units1);
        dataObj.unit2 = String.valueOf(units2);
        dataObj.unit3 = String.valueOf(units3);
        dataObj.unit4 = String.valueOf(units4);
        dataObj.unit5 = String.valueOf(units5);
        dataObj.unit6 = String.valueOf(units6);
        dataObj.unit7 = String.valueOf(units7);
        dataObj.unit8 = String.valueOf(units8);
        dataObj.unit9 = String.valueOf(units9);
        dataObj.unit10 = String.valueOf(units10);

        //1st year 2nd sem

        dataObj1.des1_12 = String.valueOf(des1_2);
        dataObj1.des2_12 = String.valueOf(des2_2);
        dataObj1.des3_12 = String.valueOf(des3_2);
        dataObj1.des4_12 = String.valueOf(des4_2);
        dataObj1.des5_12 = String.valueOf(des5_2);
        dataObj1.des6_12 = String.valueOf(des6_2);
        dataObj1.des7_12 = String.valueOf(des7_2);
        dataObj1.des8_12 = String.valueOf(des8_2);
        dataObj1.des9_12 = String.valueOf(des9_2);
        dataObj1.des10_12 = String.valueOf(des10_2);

        dataObj1.unit1_12 = String.valueOf(units1_12);
        dataObj1.unit2_12 = String.valueOf(units2_12);
        dataObj1.unit3_12 = String.valueOf(units3_12);
        dataObj1.unit4_12 = String.valueOf(units4_12);
        dataObj1.unit5_12 = String.valueOf(units5_12);
        dataObj1.unit6_12 = String.valueOf(units6_12);
        dataObj1.unit7_12 = String.valueOf(units7_12);
        dataObj1.unit8_12 = String.valueOf(units8_12);
        dataObj1.unit9_12 = String.valueOf(units9_12);
        dataObj1.unit10_12 = String.valueOf(units10_12);

        //2nd year 1sem

        dataObj21.des1_21 = String.valueOf(des1_21);
        dataObj21.des2_21 = String.valueOf(des2_21);
        dataObj21.des3_21 = String.valueOf(des3_21);
        dataObj21.des4_21 = String.valueOf(des4_21);
        dataObj21.des5_21 = String.valueOf(des5_21);
        dataObj21.des6_21 = String.valueOf(des6_21);
        dataObj21.des7_21 = String.valueOf(des7_21);
        dataObj21.des8_21 = String.valueOf(des8_21);
        dataObj21.des9_21 = String.valueOf(des9_21);
        dataObj21.des10_21 = String.valueOf(des10_21);

        dataObj21.unit1_21 = String.valueOf(units1_21);
        dataObj21.unit2_21 = String.valueOf(units2_21);
        dataObj21.unit3_21 = String.valueOf(units3_21);
        dataObj21.unit4_21 = String.valueOf(units4_21);
        dataObj21.unit5_21 = String.valueOf(units5_21);
        dataObj21.unit6_21 = String.valueOf(units6_21);
        dataObj21.unit7_21 = String.valueOf(units7_21);
        dataObj21.unit8_21 = String.valueOf(units8_21);
        dataObj21.unit9_21 = String.valueOf(units9_21);
        dataObj21.unit10_21 = String.valueOf(units10_21);

        //2nd year 2ndsem
        dataObj22.des1_22 = String.valueOf(des1_22);
        dataObj22.des2_22 = String.valueOf(des2_22);
        dataObj22.des3_22 = String.valueOf(des3_22);
        dataObj22.des4_22 = String.valueOf(des4_22);
        dataObj22.des5_22 = String.valueOf(des5_22);
        dataObj22.des6_22 = String.valueOf(des6_22);
        dataObj22.des7_22 = String.valueOf(des7_22);
        dataObj22.des8_22 = String.valueOf(des8_22);
        dataObj22.des9_22 = String.valueOf(des9_22);
        dataObj22.des10_22 = String.valueOf(des10_22);


        dataObj22.unit1_22 = String.valueOf(units1_22);
        dataObj22.unit2_22 = String.valueOf(units2_22);
        dataObj22.unit3_22 = String.valueOf(units3_22);
        dataObj22.unit4_22 = String.valueOf(units4_22);
        dataObj22.unit5_22 = String.valueOf(units5_22);
        dataObj22.unit6_22 = String.valueOf(units6_22);
        dataObj22.unit7_22 = String.valueOf(units7_22);
        dataObj22.unit8_22 = String.valueOf(units8_22);
        dataObj22.unit9_22 = String.valueOf(units9_22);
        dataObj22.unit10_22 = String.valueOf(units10_22);

        //3nd year 2ndsem
        dataObj32.des1_32 = String.valueOf(des1_32);
        dataObj32.des2_32 = String.valueOf(des2_32);
        dataObj32.des3_32 = String.valueOf(des3_32);
        dataObj32.des4_32 = String.valueOf(des4_32);
        dataObj32.des5_32 = String.valueOf(des5_32);
        dataObj32.des6_32 = String.valueOf(des6_32);
        dataObj32.des7_32 = String.valueOf(des7_32);
        dataObj32.des8_32 = String.valueOf(des8_32);
        dataObj32.des9_32 = String.valueOf(des9_32);
        dataObj32.des10_32 = String.valueOf(des10_32);


        dataObj32.unit1_32 = String.valueOf(units1_32);
        dataObj32.unit2_32 = String.valueOf(units2_32);
        dataObj32.unit3_32 = String.valueOf(units3_32);
        dataObj32.unit4_32 = String.valueOf(units4_32);
        dataObj32.unit5_32 = String.valueOf(units5_32);
        dataObj32.unit6_32 = String.valueOf(units6_32);
        dataObj32.unit7_32 = String.valueOf(units7_32);
        dataObj32.unit8_32 = String.valueOf(units8_32);
        dataObj32.unit9_32 = String.valueOf(units9_32);
        dataObj32.unit10_32 = String.valueOf(units10_32);

        //3rd year 1sem

        dataObj31.des1_31 = String.valueOf(des1_31);
        dataObj31.des2_31 = String.valueOf(des2_31);
        dataObj31.des3_31 = String.valueOf(des3_31);
        dataObj31.des4_31 = String.valueOf(des4_31);
        dataObj31.des5_31 = String.valueOf(des5_31);
        dataObj31.des6_31 = String.valueOf(des6_31);
        dataObj31.des7_31 = String.valueOf(des7_31);
        dataObj31.des8_31 = String.valueOf(des8_31);
        dataObj31.des9_31 = String.valueOf(des9_31);
        dataObj31.des10_31 = String.valueOf(des10_31);

        dataObj31.unit1_31 = String.valueOf(units1_31);
        dataObj31.unit2_31 = String.valueOf(units2_31);
        dataObj31.unit3_31 = String.valueOf(units3_31);
        dataObj31.unit4_31 = String.valueOf(units4_31);
        dataObj31.unit5_31 = String.valueOf(units5_31);
        dataObj31.unit6_31 = String.valueOf(units6_31);
        dataObj31.unit7_31 = String.valueOf(units7_31);
        dataObj31.unit8_31 = String.valueOf(units8_31);
        dataObj31.unit9_31 = String.valueOf(units9_31);
        dataObj31.unit10_31 = String.valueOf(units10_31);

        //4th year 1sem

        dataObj41.des1_41 = String.valueOf(des1_41);
        dataObj41.des2_41 = String.valueOf(des2_41);
        dataObj41.des3_41 = String.valueOf(des3_41);
        dataObj41.des4_41 = String.valueOf(des4_41);
        dataObj41.des5_41 = String.valueOf(des5_41);
        dataObj41.des6_41 = String.valueOf(des6_41);
        dataObj41.des7_41 = String.valueOf(des7_41);
        dataObj41.des8_41 = String.valueOf(des8_41);
        dataObj41.des9_41 = String.valueOf(des9_41);
        dataObj41.des10_41 = String.valueOf(des10_41);

        dataObj41.unit1_41 = String.valueOf(units1_41);
        dataObj41.unit2_41 = String.valueOf(units2_41);
        dataObj41.unit3_41 = String.valueOf(units3_41);
        dataObj41.unit4_41 = String.valueOf(units4_41);
        dataObj41.unit5_41 = String.valueOf(units5_41);
        dataObj41.unit6_41 = String.valueOf(units6_41);
        dataObj41.unit7_41 = String.valueOf(units7_41);
        dataObj41.unit8_41 = String.valueOf(units8_41);
        dataObj41.unit9_41 = String.valueOf(units9_41);
        dataObj41.unit10_41 = String.valueOf(units10_41);

        //4th year 2ndsem

        dataObj42.des1_42 = String.valueOf(des1_42);
        dataObj42.des2_42 = String.valueOf(des2_42);
        dataObj42.des3_42 = String.valueOf(des3_42);
        dataObj42.des4_42 = String.valueOf(des4_42);
        dataObj42.des5_42 = String.valueOf(des5_42);
        dataObj42.des6_42 = String.valueOf(des6_42);
        dataObj42.des7_42 = String.valueOf(des7_42);
        dataObj42.des8_42 = String.valueOf(des8_42);
        dataObj42.des9_42 = String.valueOf(des9_42);
        dataObj42.des10_42 = String.valueOf(des10_42);

        dataObj42.unit1_42 = String.valueOf(units1_42);
        dataObj42.unit2_42 = String.valueOf(units2_42);
        dataObj42.unit3_42 = String.valueOf(units3_42);
        dataObj42.unit4_42 = String.valueOf(units4_42);
        dataObj42.unit5_42 = String.valueOf(units5_42);
        dataObj42.unit6_42 = String.valueOf(units6_42);
        dataObj42.unit7_42 = String.valueOf(units7_42);
        dataObj42.unit8_42 = String.valueOf(units8_42);
        dataObj42.unit9_42 = String.valueOf(units9_42);
        dataObj42.unit10_42 = String.valueOf(units10_42);


    }

    ///////// //retrieve the course and curriculum for grades/////////////////
    private void Bscsgradeview() {
        course_curriculum.child("bscs").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                SC1 = snapshot.child("first_year").child("first_sem").child("subject01").child("descriptive_title").getValue().toString();
                SC2 = snapshot.child("first_year").child("first_sem").child("subject02").child("descriptive_title").getValue().toString();
                SC3 = snapshot.child("first_year").child("first_sem").child("subject03").child("descriptive_title").getValue().toString();
                SC4 = snapshot.child("first_year").child("first_sem").child("subject04").child("descriptive_title").getValue().toString();
                SC5 = snapshot.child("first_year").child("first_sem").child("subject05").child("descriptive_title").getValue().toString();
                SC6 = snapshot.child("first_year").child("first_sem").child("subject06").child("descriptive_title").getValue().toString();
                SC7 = snapshot.child("first_year").child("first_sem").child("subject07").child("descriptive_title").getValue().toString();
                SC8 = snapshot.child("first_year").child("first_sem").child("subject08").child("descriptive_title").getValue().toString();
                SC9 = snapshot.child("first_year").child("first_sem").child("subject09").child("descriptive_title").getValue().toString();
                SC10 = snapshot.child("first_year").child("first_sem").child("subject10").child("descriptive_title").getValue().toString();

                SC1_2 = snapshot.child("first_year").child("second_sem").child("subject01").child("code").getValue().toString();
                SC2_2 = snapshot.child("first_year").child("second_sem").child("subject02").child("code").getValue().toString();
                SC3_2 = snapshot.child("first_year").child("second_sem").child("subject03").child("code").getValue().toString();
                SC4_2 = snapshot.child("first_year").child("second_sem").child("subject04").child("code").getValue().toString();
                SC5_2 = snapshot.child("first_year").child("second_sem").child("subject05").child("code").getValue().toString();
                SC6_2 = snapshot.child("first_year").child("second_sem").child("subject06").child("code").getValue().toString();
                SC7_2 = snapshot.child("first_year").child("second_sem").child("subject07").child("code").getValue().toString();
                SC8_2 = snapshot.child("first_year").child("second_sem").child("subject08").child("code").getValue().toString();
                SC9_2 = snapshot.child("first_year").child("second_sem").child("subject09").child("code").getValue().toString();
                SC10_2 = snapshot.child("first_year").child("second_sem").child("subject10").child("code").getValue().toString();


                SC1_21 = snapshot.child("second_year").child("first_sem").child("subject01").child("code").getValue().toString();
                SC2_21 = snapshot.child("second_year").child("first_sem").child("subject03").child("code").getValue().toString();
                SC3_21 = snapshot.child("second_year").child("first_sem").child("subject04").child("code").getValue().toString();
                SC4_21 = snapshot.child("second_year").child("first_sem").child("subject05").child("descriptive_title").getValue().toString();
                SC5_21 = snapshot.child("second_year").child("first_sem").child("subject06").child("descriptive_title").getValue().toString();
                SC6_21 = snapshot.child("second_year").child("first_sem").child("subject07").child("descriptive_title").getValue().toString();
                SC7_21 = snapshot.child("second_year").child("first_sem").child("subject08").child("descriptive_title").getValue().toString();
                SC8_21 = snapshot.child("second_year").child("first_sem").child("subject09").child("descriptive_title").getValue().toString();
                SC10_21 = snapshot.child("second_year").child("first_sem").child("subject10").child("descriptive_title").getValue().toString();


                SC1_22 = snapshot.child("third_year").child("second_sem").child("subject01").child("descriptive_title").getValue().toString();
                SC2_22 = snapshot.child("third_year").child("second_sem").child("subject02").child("descriptive_title").getValue().toString();
                SC3_22 = snapshot.child("third_year").child("second_sem").child("subject03").child("descriptive_title").getValue().toString();
                SC4_22 = snapshot.child("third_year").child("second_sem").child("subject04").child("descriptive_title").getValue().toString();
                SC5_22 = snapshot.child("third_year").child("second_sem").child("subject05").child("descriptive_title").getValue().toString();
                SC6_22 = snapshot.child("third_year").child("second_sem").child("subject06").child("descriptive_title").getValue().toString();
                SC7_22 = snapshot.child("third_year").child("second_sem").child("subject07").child("descriptive_title").getValue().toString();
                SC8_22 = snapshot.child("third_year").child("second_sem").child("subject08").child("descriptive_title").getValue().toString();
                SC9_22 = snapshot.child("third_year").child("second_sem").child("subject09").child("descriptive_title").getValue().toString();
                SC10_22 = snapshot.child("third_year").child("second_sem").child("subject10").child("descriptive_title").getValue().toString();


                SC1_31 = snapshot.child("third_year").child("first_sem").child("subject01").child("code").getValue().toString();
                SC2_31 = snapshot.child("third_year").child("first_sem").child("subject02").child("code").getValue().toString();
                SC3_31 = snapshot.child("third_year").child("first_sem").child("subject03").child("code").getValue().toString();
                SC4_31 = snapshot.child("third_year").child("first_sem").child("subject04").child("code").getValue().toString();
                SC5_31 = snapshot.child("third_year").child("first_sem").child("subject05").child("code").getValue().toString();
                SC6_31 = snapshot.child("third_year").child("first_sem").child("subject06").child("code").getValue().toString();
                SC7_31 = snapshot.child("third_year").child("first_sem").child("subject07").child("code").getValue().toString();
                SC8_31 = snapshot.child("third_year").child("first_sem").child("subject08").child("code").getValue().toString();
                SC9_31 = snapshot.child("third_year").child("first_sem").child("subject09").child("code").getValue().toString();
                SC10_31 = snapshot.child("third_year").child("first_sem").child("subject10").child("code").getValue().toString();

                SC1_32 = snapshot.child("third_year").child("second_sem").child("subject01").child("code").getValue().toString();
                SC2_32 = snapshot.child("third_year").child("second_sem").child("subject02").child("code").getValue().toString();
                SC3_32 = snapshot.child("third_year").child("second_sem").child("subject03").child("code").getValue().toString();
                SC4_32 = snapshot.child("third_year").child("second_sem").child("subject04").child("code").getValue().toString();
                SC5_32 = snapshot.child("third_year").child("second_sem").child("subject05").child("code").getValue().toString();
                SC6_32 = snapshot.child("third_year").child("second_sem").child("subject06").child("code").getValue().toString();
                SC7_32 = snapshot.child("third_year").child("second_sem").child("subject07").child("code").getValue().toString();
                SC8_32 = snapshot.child("third_year").child("second_sem").child("subject08").child("code").getValue().toString();
                SC9_32 = snapshot.child("third_year").child("second_sem").child("subject09").child("code").getValue().toString();
                SC10_32 = snapshot.child("third_year").child("second_sem").child("subject010").child("code").getValue().toString();


                SC1_41 = snapshot.child("fourth_year").child("first_sem").child("subject01").child("code").getValue().toString();
                SC2_41 = snapshot.child("fourth_year").child("first_sem").child("subject02").child("code").getValue().toString();
                SC3_41 = snapshot.child("fourth_year").child("first_sem").child("subject03").child("code").getValue().toString();
                SC4_41 = snapshot.child("fourth_year").child("first_sem").child("subject04").child("code").getValue().toString();
                SC5_41 = snapshot.child("fourth_year").child("first_sem").child("subject05").child("code").getValue().toString();
                SC6_41 = snapshot.child("fourth_year").child("first_sem").child("subject06").child("code").getValue().toString();
                // SC7_41 = snapshot.child("fourth_year").child("first_sem").child("subject07").child("code").getValue().toString();
                SC8_41 = snapshot.child("fourth_year").child("first_sem").child("subject08").child("code").getValue().toString();
                SC9_41 = snapshot.child("fourth_year").child("first_sem").child("subject09").child("code").getValue().toString();
                SC10_41 = snapshot.child("fourth_year").child("first_sem").child("subject010").child("code").getValue().toString();


                SC1_42 = snapshot.child("fourth_year").child("second_sem").child("subject01").child("code").getValue().toString();
                SC3_42 = snapshot.child("fourth_year").child("second_sem").child("subject02").child("code").getValue().toString();
                SC4_42 = snapshot.child("fourth_year").child("second_sem").child("subject03").child("code").getValue().toString();
                SC5_42 = snapshot.child("fourth_year").child("second_sem").child("subject04").child("code").getValue().toString();
                SC6_42 = snapshot.child("fourth_year").child("second_sem").child("subject05").child("code").getValue().toString();
                // SC7_42 = snapshot.child("fourth_year").child("second_sem").child("subject06").child("code").getValue().toString();
                SC8_42 = snapshot.child("fourth_year").child("second_sem").child("subject07").child("code").getValue().toString();
                SC9_42 = snapshot.child("fourth_year").child("second_sem").child("subject08").child("code").getValue().toString();
                SC10_42 = snapshot.child("fourth_year").child("second_sem").child("subject0").child("code").getValue().toString();
                SC2_42 = snapshot.child("fourth_year").child("second_sem").child("subject10").child("code").getValue().toString();


                des1 = snapshot.child("first_year").child("first_sem").child("subject01").child("descriptive_title").getValue().toString().trim();
                des2 = snapshot.child("first_year").child("first_sem").child("subject02").child("descriptive_title").getValue().toString();
                des3 = snapshot.child("first_year").child("first_sem").child("subject03").child("descriptive_title").getValue().toString();
                des4 = snapshot.child("first_year").child("first_sem").child("subject04").child("descriptive_title").getValue().toString();
                des5 = snapshot.child("first_year").child("first_sem").child("subject05").child("descriptive_title").getValue().toString();
                des7 = snapshot.child("first_year").child("first_sem").child("subject06").child("descriptive_title").getValue().toString();
                des8 = snapshot.child("first_year").child("first_sem").child("subject07").child("descriptive_title").getValue().toString();
                des9 = snapshot.child("first_year").child("first_sem").child("subject08").child("descriptive_title").getValue().toString();
                des6 = snapshot.child("first_year").child("first_sem").child("subject09").child("descriptive_title").getValue().toString();
                des10 = snapshot.child("first_year").child("first_sem").child("subject10").child("descriptive_title").getValue().toString();

                des1_2 = snapshot.child("first_year").child("second_sem").child("subject01").child("descriptive_title").getValue().toString();
                des2_2 = snapshot.child("first_year").child("second_sem").child("subject02").child("descriptive_title").getValue().toString();
                des3_2 = snapshot.child("first_year").child("second_sem").child("subject03").child("descriptive_title").getValue().toString();
                des4_2 = snapshot.child("first_year").child("second_sem").child("subject04").child("descriptive_title").getValue().toString();
                des5_2 = snapshot.child("first_year").child("second_sem").child("subject05").child("descriptive_title").getValue().toString();
                des6_2 = snapshot.child("first_year").child("second_sem").child("subject06").child("descriptive_title").getValue().toString();
                des7_2 = snapshot.child("first_year").child("second_sem").child("subject07").child("descriptive_title").getValue().toString();
                des8_2 = snapshot.child("first_year").child("second_sem").child("subject08").child("descriptive_title").getValue().toString();
                des9_2 = snapshot.child("first_year").child("second_sem").child("subject09").child("descriptive_title").getValue().toString();
                des10_2 = snapshot.child("first_year").child("second_sem").child("subject10").child("descriptive_title").getValue().toString();

                des1_21 = snapshot.child("second_year").child("first_sem").child("subject01").child("descriptive_title").getValue().toString();
                des2_21 = snapshot.child("second_year").child("first_sem").child("subject02").child("descriptive_title").getValue().toString();
                des3_21 = snapshot.child("second_year").child("first_sem").child("subject03").child("descriptive_title").getValue().toString();
                des4_21 = snapshot.child("second_year").child("first_sem").child("subject04").child("descriptive_title").getValue().toString();
                des5_21 = snapshot.child("second_year").child("first_sem").child("subject05").child("descriptive_title").getValue().toString();
                des6_21 = snapshot.child("second_year").child("first_sem").child("subject06").child("descriptive_title").getValue().toString();
                des7_21 = snapshot.child("second_year").child("first_sem").child("subject07").child("descriptive_title").getValue().toString();
                des8_21 = snapshot.child("second_year").child("first_sem").child("subject08").child("descriptive_title").getValue().toString();
                des9_21 = snapshot.child("second_year").child("first_sem").child("subject09").child("descriptive_title").getValue().toString();
                des10_21 = snapshot.child("second_year").child("first_sem").child("subject10").child("descriptive_title").getValue().toString();

                des1_22 = snapshot.child("third_year").child("second_sem").child("subject01").child("descriptive_title").getValue().toString();
                des2_22 = snapshot.child("third_year").child("second_sem").child("subject02").child("descriptive_title").getValue().toString();
                des3_22 = snapshot.child("third_year").child("second_sem").child("subject03").child("descriptive_title").getValue().toString();
                des4_22 = snapshot.child("third_year").child("second_sem").child("subject04").child("descriptive_title").getValue().toString();
                des5_22 = snapshot.child("third_year").child("second_sem").child("subject05").child("descriptive_title").getValue().toString();
                des6_22 = snapshot.child("third_year").child("second_sem").child("subject06").child("descriptive_title").getValue().toString();
                des7_22 = snapshot.child("third_year").child("second_sem").child("subject07").child("descriptive_title").getValue().toString();
                des8_22 = snapshot.child("third_year").child("second_sem").child("subject08").child("descriptive_title").getValue().toString();
                des9_22 = snapshot.child("third_year").child("second_sem").child("subject09").child("descriptive_title").getValue().toString();
                des10_22 = snapshot.child("third_year").child("second_sem").child("subject10").child("descriptive_title").getValue().toString();

                des1_31 = snapshot.child("third_year").child("first_sem").child("subject01").child("descriptive_title").getValue().toString();
                des2_31 = snapshot.child("third_year").child("first_sem").child("subject02").child("descriptive_title").getValue().toString();
                des3_31 = snapshot.child("third_year").child("first_sem").child("subject03").child("descriptive_title").getValue().toString();
                des4_31 = snapshot.child("third_year").child("first_sem").child("subject04").child("descriptive_title").getValue().toString();
                des5_31 = snapshot.child("third_year").child("first_sem").child("subject05").child("descriptive_title").getValue().toString();
                des6_31 = snapshot.child("third_year").child("first_sem").child("subject06").child("descriptive_title").getValue().toString();
                des7_31 = snapshot.child("third_year").child("first_sem").child("subject07").child("descriptive_title").getValue().toString();
                des8_31 = snapshot.child("third_year").child("first_sem").child("subject08").child("descriptive_title").getValue().toString();
                des9_31 = snapshot.child("third_year").child("first_sem").child("subject09").child("descriptive_title").getValue().toString();
                des10_31 = snapshot.child("third_year").child("first_sem").child("subject10").child("descriptive_title").getValue().toString();

                des1_32 = snapshot.child("third_year").child("second_sem").child("subject01").child("descriptive_title").getValue().toString();
                des2_32 = snapshot.child("third_year").child("second_sem").child("subject02").child("descriptive_title").getValue().toString();
                des3_32 = snapshot.child("third_year").child("second_sem").child("subject03").child("descriptive_title").getValue().toString();
                des4_32 = snapshot.child("third_year").child("second_sem").child("subject04").child("descriptive_title").getValue().toString();
                des5_32 = snapshot.child("third_year").child("second_sem").child("subject05").child("descriptive_title").getValue().toString();
                des6_32 = snapshot.child("third_year").child("second_sem").child("subject06").child("descriptive_title").getValue().toString();
                des1_32 = snapshot.child("third_year").child("second_sem").child("subject07").child("descriptive_title").getValue().toString();
                des1_32 = snapshot.child("third_year").child("second_sem").child("subject08").child("descriptive_title").getValue().toString();
                des1_32 = snapshot.child("third_year").child("second_sem").child("subject09").child("descriptive_title").getValue().toString();
                des1_32 = snapshot.child("third_year").child("second_sem").child("subject10").child("descriptive_title").getValue().toString();

                des1_41 = snapshot.child("fourth_year").child("first_sem").child("subject01").child("descriptive_title").getValue().toString();
                des2_41 = snapshot.child("fourth_year").child("first_sem").child("subject02").child("descriptive_title").getValue().toString();
                des3_41 = snapshot.child("fourth_year").child("first_sem").child("subject03").child("descriptive_title").getValue().toString();
                des4_41 = snapshot.child("fourth_year").child("first_sem").child("subject04").child("descriptive_title").getValue().toString();
                des5_41 = snapshot.child("fourth_year").child("first_sem").child("subject05").child("descriptive_title").getValue().toString();
                des6_41 = snapshot.child("fourth_year").child("first_sem").child("subject06").child("descriptive_title").getValue().toString();
                des7_41 = snapshot.child("fourth_year").child("first_sem").child("subject07").child("descriptive_title").getValue().toString();
                des8_41 = snapshot.child("fourth_year").child("first_sem").child("subject08").child("descriptive_title").getValue().toString();
                des9_41 = snapshot.child("fourth_year").child("first_sem").child("subject09").child("descriptive_title").getValue().toString();
                des10_41 = snapshot.child("fourth_year").child("first_sem").child("subject10").child("descriptive_title").getValue().toString();


                des1_42 = snapshot.child("fourth_year").child("second_sem").child("subject01").child("descriptive_title").getValue().toString();
                des2_42 = snapshot.child("fourth_year").child("second_sem").child("subject02").child("descriptive_title").getValue().toString();
                des3_42 = snapshot.child("fourth_year").child("second_sem").child("subject03").child("descriptive_title").getValue().toString();
                des4_42 = snapshot.child("fourth_year").child("second_sem").child("subject04").child("descriptive_title").getValue().toString();
                des5_42 = snapshot.child("fourth_year").child("second_sem").child("subject05").child("descriptive_title").getValue().toString();
                des6_42 = snapshot.child("fourth_year").child("second_sem").child("subject06").child("descriptive_title").getValue().toString();
                des7_42 = snapshot.child("fourth_year").child("second_sem").child("subject07").child("descriptive_title").getValue().toString();
                des8_42 = snapshot.child("fourth_year").child("second_sem").child("subject08").child("descriptive_title").getValue().toString();
                des9_42 = snapshot.child("fourth_year").child("second_sem").child("subject09").child("descriptive_title").getValue().toString();
                des10_42 = snapshot.child("fourth_year").child("second_sem").child("subject10").child("descriptive_title").getValue().toString();

                units1 = snapshot.child("first_year").child("first_sem").child("subject01").child("units").getValue().toString();
                units2 = snapshot.child("first_year").child("first_sem").child("subject02").child("units").getValue().toString();
                units3 = snapshot.child("first_year").child("first_sem").child("subject03").child("units").getValue().toString();
                units4 = snapshot.child("first_year").child("first_sem").child("subject04").child("units").getValue().toString();
                units5 = snapshot.child("first_year").child("first_sem").child("subject05").child("units").getValue().toString();
                units6 = snapshot.child("first_year").child("first_sem").child("subject06").child("units").getValue().toString();
                units7 = snapshot.child("first_year").child("first_sem").child("subject07").child("units").getValue().toString();
                units8 = snapshot.child("first_year").child("first_sem").child("subject08").child("units").getValue().toString();
                units9 = snapshot.child("first_year").child("first_sem").child("subject09").child("units").getValue().toString();
                units10 = snapshot.child("first_year").child("first_sem").child("subject10").child("units").getValue().toString();

                units1_12 = snapshot.child("first_year").child("second_sem").child("subject01").child("units").getValue().toString();
                units2_12 = snapshot.child("first_year").child("second_sem").child("subject02").child("units").getValue().toString();
                units3_12 = snapshot.child("first_year").child("second_sem").child("subject03").child("units").getValue().toString();
                units4_12 = snapshot.child("first_year").child("second_sem").child("subject04").child("units").getValue().toString();
                units5_12 = snapshot.child("first_year").child("second_sem").child("subject05").child("units").getValue().toString();
                units6_12 = snapshot.child("first_year").child("second_sem").child("subject06").child("units").getValue().toString();
                units7_12 = snapshot.child("first_year").child("second_sem").child("subject07").child("units").getValue().toString();
                units8_12 = snapshot.child("first_year").child("second_sem").child("subject08").child("units").getValue().toString();
                units9_12 = snapshot.child("first_year").child("second_sem").child("subject09").child("units").getValue().toString();
                units10_12 = snapshot.child("first_year").child("second_sem").child("subject10").child("units").getValue().toString();

                units1_21 = snapshot.child("second_year").child("first_sem").child("subject01").child("units").getValue().toString();
                units2_21 = snapshot.child("second_year").child("first_sem").child("subject02").child("units").getValue().toString();
                units3_21 = snapshot.child("second_year").child("first_sem").child("subject03").child("units").getValue().toString();
                units4_21 = snapshot.child("second_year").child("first_sem").child("subject04").child("units").getValue().toString();
                units5_21 = snapshot.child("second_year").child("first_sem").child("subject05").child("units").getValue().toString();
                units6_21 = snapshot.child("second_year").child("first_sem").child("subject06").child("units").getValue().toString();
                units7_21 = snapshot.child("second_year").child("first_sem").child("subject07").child("units").getValue().toString();
                units8_21 = snapshot.child("second_year").child("first_sem").child("subject08").child("units").getValue().toString();
                units9_21 = snapshot.child("second_year").child("first_sem").child("subject09").child("units").getValue().toString();
                units10_21 = snapshot.child("second_year").child("first_sem").child("subject10").child("units").getValue().toString();

                units1_22 = snapshot.child("second_year").child("second_sem").child("subject01").child("units").getValue().toString();
                units2_22 = snapshot.child("second_year").child("second_sem").child("subject02").child("units").getValue().toString();
                units3_22 = snapshot.child("second_year").child("second_sem").child("subject03").child("units").getValue().toString();
                units4_22 = snapshot.child("second_year").child("second_sem").child("subject04").child("units").getValue().toString();
                units5_22 = snapshot.child("second_year").child("second_sem").child("subject05").child("units").getValue().toString();
                units6_22 = snapshot.child("second_year").child("second_sem").child("subject06").child("units").getValue().toString();
                units7_22 = snapshot.child("second_year").child("second_sem").child("subject07").child("units").getValue().toString();
                units8_22 = snapshot.child("second_year").child("second_sem").child("subject08").child("units").getValue().toString();
                units9_22 = snapshot.child("second_year").child("second_sem").child("subject09").child("units").getValue().toString();
                units10_22 = snapshot.child("second_year").child("second_sem").child("subject10").child("units").getValue().toString();

                units1_31 = snapshot.child("third_year").child("first_sem").child("subject01").child("units").getValue().toString();
                units2_31 = snapshot.child("third_year").child("first_sem").child("subject02").child("units").getValue().toString();
                units3_31 = snapshot.child("third_year").child("first_sem").child("subject03").child("units").getValue().toString();
                units4_31 = snapshot.child("third_year").child("first_sem").child("subject04").child("units").getValue().toString();
                units5_31 = snapshot.child("third_year").child("first_sem").child("subject05").child("units").getValue().toString();
                units6_31 = snapshot.child("third_year").child("first_sem").child("subject06").child("units").getValue().toString();
                units7_31 = snapshot.child("third_year").child("first_sem").child("subject07").child("units").getValue().toString();
                units8_31 = snapshot.child("third_year").child("first_sem").child("subject08").child("units").getValue().toString();
                units9_31 = snapshot.child("third_year").child("first_sem").child("subject09").child("units").getValue().toString();
                units10_31 = snapshot.child("third_year").child("first_sem").child("subject10").child("units").getValue().toString();


                units1_32 = snapshot.child("third_year").child("second_sem").child("subject01").child("units").getValue().toString();
                units2_32 = snapshot.child("third_year").child("second_sem").child("subject02").child("units").getValue().toString();
                units3_32 = snapshot.child("third_year").child("second_sem").child("subject03").child("units").getValue().toString();
                units4_32 = snapshot.child("third_year").child("second_sem").child("subject04").child("units").getValue().toString();
                units5_32 = snapshot.child("third_year").child("second_sem").child("subject05").child("units").getValue().toString();
                units6_32 = snapshot.child("third_year").child("second_sem").child("subject06").child("units").getValue().toString();
                units7_32 = snapshot.child("third_year").child("second_sem").child("subject07").child("units").getValue().toString();
                units8_32 = snapshot.child("third_year").child("second_sem").child("subject08").child("units").getValue().toString();
                units9_32 = snapshot.child("third_year").child("second_sem").child("subject09").child("units").getValue().toString();
                units10_32 = snapshot.child("third_year").child("second_sem").child("subjec10").child("units").getValue().toString();

                units1_41 = snapshot.child("fourth_year").child("first_sem").child("subject01").child("units").getValue().toString();
                units2_41 = snapshot.child("fourth_year").child("first_sem").child("subject02").child("units").getValue().toString();
                units3_41 = snapshot.child("fourth_year").child("first_sem").child("subject03").child("units").getValue().toString();
                units4_41 = snapshot.child("fourth_year").child("first_sem").child("subject04").child("units").getValue().toString();
                units5_41 = snapshot.child("fourth_year").child("first_sem").child("subject05").child("units").getValue().toString();
                units6_41 = snapshot.child("fourth_year").child("first_sem").child("subject06").child("units").getValue().toString();
                units7_41 = snapshot.child("fourth_year").child("first_sem").child("subject07").child("units").getValue().toString();
                units8_41 = snapshot.child("fourth_year").child("first_sem").child("subject08").child("units").getValue().toString();
                units9_41 = snapshot.child("fourth_year").child("first_sem").child("subject09").child("units").getValue().toString();
                units10_41 = snapshot.child("fourth_year").child("first_sem").child("subject10").child("units").getValue().toString();


                units1_42 = snapshot.child("fourth_year").child("second_sem").child("subject01").child("units").getValue().toString();
                units2_42 = snapshot.child("fourth_year").child("second_sem").child("subject02").child("units").getValue().toString();
                units3_42 = snapshot.child("fourth_year").child("second_sem").child("subject03").child("units").getValue().toString();
                units4_42 = snapshot.child("fourth_year").child("second_sem").child("subject04").child("units").getValue().toString();
                units5_42 = snapshot.child("fourth_year").child("second_sem").child("subject05").child("units").getValue().toString();
                units6_42 = snapshot.child("fourth_year").child("second_sem").child("subject06").child("units").getValue().toString();
                units7_42 = snapshot.child("fourth_year").child("second_sem").child("subject07").child("units").getValue().toString();
                units8_42 = snapshot.child("fourth_year").child("second_sem").child("subject08").child("units").getValue().toString();
                units9_42 = snapshot.child("fourth_year").child("second_sem").child("subject09").child("units").getValue().toString();
                units10_42 = snapshot.child("fourth_year").child("second_sem").child("subject10").child("units").getValue().toString();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void bsbagradeview() {

        course_curriculum.child("bsba").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                SC1 = snapshot.child("first_year").child("first_sem").child("subject01").child("descriptive_title").getValue().toString();
                SC2 = snapshot.child("first_year").child("first_sem").child("subject02").child("descriptive_title").getValue().toString();
                SC3 = snapshot.child("first_year").child("first_sem").child("subject03").child("descriptive_title").getValue().toString();
                SC4 = snapshot.child("first_year").child("first_sem").child("subject04").child("descriptive_title").getValue().toString();
                SC5 = snapshot.child("first_year").child("first_sem").child("subject05").child("descriptive_title").getValue().toString();
                SC6 = snapshot.child("first_year").child("first_sem").child("subject06").child("descriptive_title").getValue().toString();
                SC7 = snapshot.child("first_year").child("first_sem").child("subject07").child("descriptive_title").getValue().toString();
                SC8 = snapshot.child("first_year").child("first_sem").child("subject08").child("descriptive_title").getValue().toString();
                SC9 = snapshot.child("first_year").child("first_sem").child("subject09").child("descriptive_title").getValue().toString();
                SC10 = snapshot.child("first_year").child("first_sem").child("subject10").child("descriptive_title").getValue().toString();

                SC1_2 = snapshot.child("first_year").child("second_sem").child("subject01").child("code").getValue().toString();
                SC2_2 = snapshot.child("first_year").child("second_sem").child("subject02").child("code").getValue().toString();
                SC3_2 = snapshot.child("first_year").child("second_sem").child("subject03").child("code").getValue().toString();
                SC4_2 = snapshot.child("first_year").child("second_sem").child("subject04").child("code").getValue().toString();
                SC5_2 = snapshot.child("first_year").child("second_sem").child("subject05").child("code").getValue().toString();
                SC6_2 = snapshot.child("first_year").child("second_sem").child("subject06").child("code").getValue().toString();
                SC7_2 = snapshot.child("first_year").child("second_sem").child("subject07").child("code").getValue().toString();
                SC8_2 = snapshot.child("first_year").child("second_sem").child("subject08").child("code").getValue().toString();
                SC9_2 = snapshot.child("first_year").child("second_sem").child("subject09").child("code").getValue().toString();
                SC10_2 = snapshot.child("first_year").child("second_sem").child("subject10").child("code").getValue().toString();


                SC1_21 = snapshot.child("second_year").child("first_sem").child("subject01").child("code").getValue().toString();
                SC2_21 = snapshot.child("second_year").child("first_sem").child("subject03").child("code").getValue().toString();
                SC3_21 = snapshot.child("second_year").child("first_sem").child("subject04").child("code").getValue().toString();
                SC4_21 = snapshot.child("second_year").child("first_sem").child("subject05").child("descriptive_title").getValue().toString();
                SC5_21 = snapshot.child("second_year").child("first_sem").child("subject06").child("descriptive_title").getValue().toString();
                SC6_21 = snapshot.child("second_year").child("first_sem").child("subject07").child("descriptive_title").getValue().toString();
                SC7_21 = snapshot.child("second_year").child("first_sem").child("subject08").child("descriptive_title").getValue().toString();
                SC8_21 = snapshot.child("second_year").child("first_sem").child("subject09").child("descriptive_title").getValue().toString();
                SC10_21 = snapshot.child("second_year").child("first_sem").child("subject10").child("descriptive_title").getValue().toString();


                SC1_22 = snapshot.child("third_year").child("second_sem").child("subject01").child("descriptive_title").getValue().toString();
                SC2_22 = snapshot.child("third_year").child("second_sem").child("subject02").child("descriptive_title").getValue().toString();
                SC3_22 = snapshot.child("third_year").child("second_sem").child("subject03").child("descriptive_title").getValue().toString();
                SC4_22 = snapshot.child("third_year").child("second_sem").child("subject04").child("descriptive_title").getValue().toString();
                SC5_22 = snapshot.child("third_year").child("second_sem").child("subject05").child("descriptive_title").getValue().toString();
                SC6_22 = snapshot.child("third_year").child("second_sem").child("subject06").child("descriptive_title").getValue().toString();
                SC7_22 = snapshot.child("third_year").child("second_sem").child("subject07").child("descriptive_title").getValue().toString();
                SC8_22 = snapshot.child("third_year").child("second_sem").child("subject08").child("descriptive_title").getValue().toString();
                SC9_22 = snapshot.child("third_year").child("second_sem").child("subject09").child("descriptive_title").getValue().toString();
                SC10_22 = snapshot.child("third_year").child("second_sem").child("subject10").child("descriptive_title").getValue().toString();


                SC1_31 = snapshot.child("third_year").child("first_sem").child("subject01").child("code").getValue().toString();
                SC2_31 = snapshot.child("third_year").child("first_sem").child("subject02").child("code").getValue().toString();
                SC3_31 = snapshot.child("third_year").child("first_sem").child("subject03").child("code").getValue().toString();
                SC4_31 = snapshot.child("third_year").child("first_sem").child("subject04").child("code").getValue().toString();
                SC5_31 = snapshot.child("third_year").child("first_sem").child("subject05").child("code").getValue().toString();
                SC6_31 = snapshot.child("third_year").child("first_sem").child("subject06").child("code").getValue().toString();
                SC7_31 = snapshot.child("third_year").child("first_sem").child("subject07").child("code").getValue().toString();
                SC8_31 = snapshot.child("third_year").child("first_sem").child("subject08").child("code").getValue().toString();
                SC9_31 = snapshot.child("third_year").child("first_sem").child("subject09").child("code").getValue().toString();
                SC10_31 = snapshot.child("third_year").child("first_sem").child("subject10").child("code").getValue().toString();

                SC1_32 = snapshot.child("third_year").child("second_sem").child("subject01").child("code").getValue().toString();
                SC2_32 = snapshot.child("third_year").child("second_sem").child("subject02").child("code").getValue().toString();
                SC3_32 = snapshot.child("third_year").child("second_sem").child("subject03").child("code").getValue().toString();
                SC4_32 = snapshot.child("third_year").child("second_sem").child("subject04").child("code").getValue().toString();
                SC5_32 = snapshot.child("third_year").child("second_sem").child("subject05").child("code").getValue().toString();
                SC6_32 = snapshot.child("third_year").child("second_sem").child("subject06").child("code").getValue().toString();
                SC7_32 = snapshot.child("third_year").child("second_sem").child("subject07").child("code").getValue().toString();
                SC8_32 = snapshot.child("third_year").child("second_sem").child("subject08").child("code").getValue().toString();
                SC9_32 = snapshot.child("third_year").child("second_sem").child("subject09").child("code").getValue().toString();
                SC10_32 = snapshot.child("third_year").child("second_sem").child("subject010").child("code").getValue().toString();


                SC1_41 = snapshot.child("fourth_year").child("first_sem").child("subject01").child("code").getValue().toString();
                SC2_41 = snapshot.child("fourth_year").child("first_sem").child("subject02").child("code").getValue().toString();
                SC3_41 = snapshot.child("fourth_year").child("first_sem").child("subject03").child("code").getValue().toString();
                SC4_41 = snapshot.child("fourth_year").child("first_sem").child("subject04").child("code").getValue().toString();
                SC5_41 = snapshot.child("fourth_year").child("first_sem").child("subject05").child("code").getValue().toString();
                SC6_41 = snapshot.child("fourth_year").child("first_sem").child("subject06").child("code").getValue().toString();
                // SC7_41 = snapshot.child("fourth_year").child("first_sem").child("subject07").child("code").getValue().toString();
                SC8_41 = snapshot.child("fourth_year").child("first_sem").child("subject08").child("code").getValue().toString();
                SC9_41 = snapshot.child("fourth_year").child("first_sem").child("subject09").child("code").getValue().toString();
                SC10_41 = snapshot.child("fourth_year").child("first_sem").child("subject010").child("code").getValue().toString();


                SC1_42 = snapshot.child("fourth_year").child("second_sem").child("subject01").child("code").getValue().toString();
                SC3_42 = snapshot.child("fourth_year").child("second_sem").child("subject02").child("code").getValue().toString();
                SC4_42 = snapshot.child("fourth_year").child("second_sem").child("subject03").child("code").getValue().toString();
                SC5_42 = snapshot.child("fourth_year").child("second_sem").child("subject04").child("code").getValue().toString();
                SC6_42 = snapshot.child("fourth_year").child("second_sem").child("subject05").child("code").getValue().toString();
                // SC7_42 = snapshot.child("fourth_year").child("second_sem").child("subject06").child("code").getValue().toString();
                SC8_42 = snapshot.child("fourth_year").child("second_sem").child("subject07").child("code").getValue().toString();
                SC9_42 = snapshot.child("fourth_year").child("second_sem").child("subject08").child("code").getValue().toString();
                SC10_42 = snapshot.child("fourth_year").child("second_sem").child("subject0").child("code").getValue().toString();
                SC2_42 = snapshot.child("fourth_year").child("second_sem").child("subject10").child("code").getValue().toString();


                des1 = snapshot.child("first_year").child("first_sem").child("subject01").child("descriptive_title").getValue().toString().trim();
                des2 = snapshot.child("first_year").child("first_sem").child("subject02").child("descriptive_title").getValue().toString();
                des3 = snapshot.child("first_year").child("first_sem").child("subject03").child("descriptive_title").getValue().toString();
                des4 = snapshot.child("first_year").child("first_sem").child("subject04").child("descriptive_title").getValue().toString();
                des5 = snapshot.child("first_year").child("first_sem").child("subject05").child("descriptive_title").getValue().toString();
                des7 = snapshot.child("first_year").child("first_sem").child("subject06").child("descriptive_title").getValue().toString();
                des8 = snapshot.child("first_year").child("first_sem").child("subject07").child("descriptive_title").getValue().toString();
                des9 = snapshot.child("first_year").child("first_sem").child("subject08").child("descriptive_title").getValue().toString();
                des6 = snapshot.child("first_year").child("first_sem").child("subject09").child("descriptive_title").getValue().toString();
                des10 = snapshot.child("first_year").child("first_sem").child("subject10").child("descriptive_title").getValue().toString();

                des1_2 = snapshot.child("first_year").child("second_sem").child("subject01").child("descriptive_title").getValue().toString();
                des2_2 = snapshot.child("first_year").child("second_sem").child("subject02").child("descriptive_title").getValue().toString();
                des3_2 = snapshot.child("first_year").child("second_sem").child("subject03").child("descriptive_title").getValue().toString();
                des4_2 = snapshot.child("first_year").child("second_sem").child("subject04").child("descriptive_title").getValue().toString();
                des5_2 = snapshot.child("first_year").child("second_sem").child("subject05").child("descriptive_title").getValue().toString();
                des6_2 = snapshot.child("first_year").child("second_sem").child("subject06").child("descriptive_title").getValue().toString();
                des7_2 = snapshot.child("first_year").child("second_sem").child("subject07").child("descriptive_title").getValue().toString();
                des8_2 = snapshot.child("first_year").child("second_sem").child("subject08").child("descriptive_title").getValue().toString();
                des9_2 = snapshot.child("first_year").child("second_sem").child("subject09").child("descriptive_title").getValue().toString();
                des10_2 = snapshot.child("first_year").child("second_sem").child("subject10").child("descriptive_title").getValue().toString();

                des1_21 = snapshot.child("second_year").child("first_sem").child("subject01").child("descriptive_title").getValue().toString();
                des2_21 = snapshot.child("second_year").child("first_sem").child("subject02").child("descriptive_title").getValue().toString();
                des3_21 = snapshot.child("second_year").child("first_sem").child("subject03").child("descriptive_title").getValue().toString();
                des4_21 = snapshot.child("second_year").child("first_sem").child("subject04").child("descriptive_title").getValue().toString();
                des5_21 = snapshot.child("second_year").child("first_sem").child("subject05").child("descriptive_title").getValue().toString();
                des6_21 = snapshot.child("second_year").child("first_sem").child("subject06").child("descriptive_title").getValue().toString();
                des7_21 = snapshot.child("second_year").child("first_sem").child("subject07").child("descriptive_title").getValue().toString();
                des8_21 = snapshot.child("second_year").child("first_sem").child("subject08").child("descriptive_title").getValue().toString();
                des9_21 = snapshot.child("second_year").child("first_sem").child("subject09").child("descriptive_title").getValue().toString();
                des10_21 = snapshot.child("second_year").child("first_sem").child("subject10").child("descriptive_title").getValue().toString();

                des1_22 = snapshot.child("third_year").child("second_sem").child("subject01").child("descriptive_title").getValue().toString();
                des2_22 = snapshot.child("third_year").child("second_sem").child("subject02").child("descriptive_title").getValue().toString();
                des3_22 = snapshot.child("third_year").child("second_sem").child("subject03").child("descriptive_title").getValue().toString();
                des4_22 = snapshot.child("third_year").child("second_sem").child("subject04").child("descriptive_title").getValue().toString();
                des5_22 = snapshot.child("third_year").child("second_sem").child("subject05").child("descriptive_title").getValue().toString();
                des6_22 = snapshot.child("third_year").child("second_sem").child("subject06").child("descriptive_title").getValue().toString();
                des7_22 = snapshot.child("third_year").child("second_sem").child("subject07").child("descriptive_title").getValue().toString();
                des8_22 = snapshot.child("third_year").child("second_sem").child("subject08").child("descriptive_title").getValue().toString();
                des9_22 = snapshot.child("third_year").child("second_sem").child("subject09").child("descriptive_title").getValue().toString();
                des10_22 = snapshot.child("third_year").child("second_sem").child("subject10").child("descriptive_title").getValue().toString();

                des1_31 = snapshot.child("third_year").child("first_sem").child("subject01").child("descriptive_title").getValue().toString();
                des2_31 = snapshot.child("third_year").child("first_sem").child("subject02").child("descriptive_title").getValue().toString();
                des3_31 = snapshot.child("third_year").child("first_sem").child("subject03").child("descriptive_title").getValue().toString();
                des4_31 = snapshot.child("third_year").child("first_sem").child("subject04").child("descriptive_title").getValue().toString();
                des5_31 = snapshot.child("third_year").child("first_sem").child("subject05").child("descriptive_title").getValue().toString();
                des6_31 = snapshot.child("third_year").child("first_sem").child("subject06").child("descriptive_title").getValue().toString();
                des7_31 = snapshot.child("third_year").child("first_sem").child("subject07").child("descriptive_title").getValue().toString();
                des8_31 = snapshot.child("third_year").child("first_sem").child("subject08").child("descriptive_title").getValue().toString();
                des9_31 = snapshot.child("third_year").child("first_sem").child("subject09").child("descriptive_title").getValue().toString();
                des10_31 = snapshot.child("third_year").child("first_sem").child("subject10").child("descriptive_title").getValue().toString();

                des1_32 = snapshot.child("third_year").child("second_sem").child("subject01").child("descriptive_title").getValue().toString();
                des2_32 = snapshot.child("third_year").child("second_sem").child("subject02").child("descriptive_title").getValue().toString();
                des3_32 = snapshot.child("third_year").child("second_sem").child("subject03").child("descriptive_title").getValue().toString();
                des4_32 = snapshot.child("third_year").child("second_sem").child("subject04").child("descriptive_title").getValue().toString();
                des5_32 = snapshot.child("third_year").child("second_sem").child("subject05").child("descriptive_title").getValue().toString();
                des6_32 = snapshot.child("third_year").child("second_sem").child("subject06").child("descriptive_title").getValue().toString();
                des1_32 = snapshot.child("third_year").child("second_sem").child("subject07").child("descriptive_title").getValue().toString();
                des1_32 = snapshot.child("third_year").child("second_sem").child("subject08").child("descriptive_title").getValue().toString();
                des1_32 = snapshot.child("third_year").child("second_sem").child("subject09").child("descriptive_title").getValue().toString();
                des1_32 = snapshot.child("third_year").child("second_sem").child("subject10").child("descriptive_title").getValue().toString();

                des1_41 = snapshot.child("fourth_year").child("first_sem").child("subject01").child("descriptive_title").getValue().toString();
                des2_41 = snapshot.child("fourth_year").child("first_sem").child("subject02").child("descriptive_title").getValue().toString();
                des3_41 = snapshot.child("fourth_year").child("first_sem").child("subject03").child("descriptive_title").getValue().toString();
                des4_41 = snapshot.child("fourth_year").child("first_sem").child("subject04").child("descriptive_title").getValue().toString();
                des5_41 = snapshot.child("fourth_year").child("first_sem").child("subject05").child("descriptive_title").getValue().toString();
                des6_41 = snapshot.child("fourth_year").child("first_sem").child("subject06").child("descriptive_title").getValue().toString();
                des7_41 = snapshot.child("fourth_year").child("first_sem").child("subject07").child("descriptive_title").getValue().toString();
                des8_41 = snapshot.child("fourth_year").child("first_sem").child("subject08").child("descriptive_title").getValue().toString();
                des9_41 = snapshot.child("fourth_year").child("first_sem").child("subject09").child("descriptive_title").getValue().toString();
                des10_41 = snapshot.child("fourth_year").child("first_sem").child("subject10").child("descriptive_title").getValue().toString();


                des1_42 = snapshot.child("fourth_year").child("second_sem").child("subject01").child("descriptive_title").getValue().toString();
                des2_42 = snapshot.child("fourth_year").child("second_sem").child("subject02").child("descriptive_title").getValue().toString();
                des3_42 = snapshot.child("fourth_year").child("second_sem").child("subject03").child("descriptive_title").getValue().toString();
                des4_42 = snapshot.child("fourth_year").child("second_sem").child("subject04").child("descriptive_title").getValue().toString();
                des5_42 = snapshot.child("fourth_year").child("second_sem").child("subject05").child("descriptive_title").getValue().toString();
                des6_42 = snapshot.child("fourth_year").child("second_sem").child("subject06").child("descriptive_title").getValue().toString();
                des7_42 = snapshot.child("fourth_year").child("second_sem").child("subject07").child("descriptive_title").getValue().toString();
                des8_42 = snapshot.child("fourth_year").child("second_sem").child("subject08").child("descriptive_title").getValue().toString();
                des9_42 = snapshot.child("fourth_year").child("second_sem").child("subject09").child("descriptive_title").getValue().toString();
                des10_42 = snapshot.child("fourth_year").child("second_sem").child("subject10").child("descriptive_title").getValue().toString();

                units1 = snapshot.child("first_year").child("first_sem").child("subject01").child("units").getValue().toString();
                units2 = snapshot.child("first_year").child("first_sem").child("subject02").child("units").getValue().toString();
                units3 = snapshot.child("first_year").child("first_sem").child("subject03").child("units").getValue().toString();
                units4 = snapshot.child("first_year").child("first_sem").child("subject04").child("units").getValue().toString();
                units5 = snapshot.child("first_year").child("first_sem").child("subject05").child("units").getValue().toString();
                units6 = snapshot.child("first_year").child("first_sem").child("subject06").child("units").getValue().toString();
                units7 = snapshot.child("first_year").child("first_sem").child("subject07").child("units").getValue().toString();
                units8 = snapshot.child("first_year").child("first_sem").child("subject08").child("units").getValue().toString();
                units9 = snapshot.child("first_year").child("first_sem").child("subject09").child("units").getValue().toString();
                units10 = snapshot.child("first_year").child("first_sem").child("subject10").child("units").getValue().toString();

                units1_12 = snapshot.child("first_year").child("second_sem").child("subject01").child("units").getValue().toString();
                units2_12 = snapshot.child("first_year").child("second_sem").child("subject02").child("units").getValue().toString();
                units3_12 = snapshot.child("first_year").child("second_sem").child("subject03").child("units").getValue().toString();
                units4_12 = snapshot.child("first_year").child("second_sem").child("subject04").child("units").getValue().toString();
                units5_12 = snapshot.child("first_year").child("second_sem").child("subject05").child("units").getValue().toString();
                units6_12 = snapshot.child("first_year").child("second_sem").child("subject06").child("units").getValue().toString();
                units7_12 = snapshot.child("first_year").child("second_sem").child("subject07").child("units").getValue().toString();
                units8_12 = snapshot.child("first_year").child("second_sem").child("subject08").child("units").getValue().toString();
                units9_12 = snapshot.child("first_year").child("second_sem").child("subject09").child("units").getValue().toString();
                units10_12 = snapshot.child("first_year").child("second_sem").child("subject10").child("units").getValue().toString();

                units1_21 = snapshot.child("second_year").child("first_sem").child("subject01").child("units").getValue().toString();
                units2_21 = snapshot.child("second_year").child("first_sem").child("subject02").child("units").getValue().toString();
                units3_21 = snapshot.child("second_year").child("first_sem").child("subject03").child("units").getValue().toString();
                units4_21 = snapshot.child("second_year").child("first_sem").child("subject04").child("units").getValue().toString();
                units5_21 = snapshot.child("second_year").child("first_sem").child("subject05").child("units").getValue().toString();
                units6_21 = snapshot.child("second_year").child("first_sem").child("subject06").child("units").getValue().toString();
                units7_21 = snapshot.child("second_year").child("first_sem").child("subject07").child("units").getValue().toString();
                units8_21 = snapshot.child("second_year").child("first_sem").child("subject08").child("units").getValue().toString();
                units9_21 = snapshot.child("second_year").child("first_sem").child("subject09").child("units").getValue().toString();
                units10_21 = snapshot.child("second_year").child("first_sem").child("subject10").child("units").getValue().toString();

                units1_22 = snapshot.child("second_year").child("second_sem").child("subject01").child("units").getValue().toString();
                units2_22 = snapshot.child("second_year").child("second_sem").child("subject02").child("units").getValue().toString();
                units3_22 = snapshot.child("second_year").child("second_sem").child("subject03").child("units").getValue().toString();
                units4_22 = snapshot.child("second_year").child("second_sem").child("subject04").child("units").getValue().toString();
                units5_22 = snapshot.child("second_year").child("second_sem").child("subject05").child("units").getValue().toString();
                units6_22 = snapshot.child("second_year").child("second_sem").child("subject06").child("units").getValue().toString();
                units7_22 = snapshot.child("second_year").child("second_sem").child("subject07").child("units").getValue().toString();
                units8_22 = snapshot.child("second_year").child("second_sem").child("subject08").child("units").getValue().toString();
                units9_22 = snapshot.child("second_year").child("second_sem").child("subject09").child("units").getValue().toString();
                units10_22 = snapshot.child("second_year").child("second_sem").child("subject10").child("units").getValue().toString();

                units1_31 = snapshot.child("third_year").child("first_sem").child("subject01").child("units").getValue().toString();
                units2_31 = snapshot.child("third_year").child("first_sem").child("subject02").child("units").getValue().toString();
                units3_31 = snapshot.child("third_year").child("first_sem").child("subject03").child("units").getValue().toString();
                units4_31 = snapshot.child("third_year").child("first_sem").child("subject04").child("units").getValue().toString();
                units5_31 = snapshot.child("third_year").child("first_sem").child("subject05").child("units").getValue().toString();
                units6_31 = snapshot.child("third_year").child("first_sem").child("subject06").child("units").getValue().toString();
                units7_31 = snapshot.child("third_year").child("first_sem").child("subject07").child("units").getValue().toString();
                units8_31 = snapshot.child("third_year").child("first_sem").child("subject08").child("units").getValue().toString();
                units9_31 = snapshot.child("third_year").child("first_sem").child("subject09").child("units").getValue().toString();
                units10_31 = snapshot.child("third_year").child("first_sem").child("subject10").child("units").getValue().toString();


                units1_32 = snapshot.child("third_year").child("second_sem").child("subject01").child("units").getValue().toString();
                units2_32 = snapshot.child("third_year").child("second_sem").child("subject02").child("units").getValue().toString();
                units3_32 = snapshot.child("third_year").child("second_sem").child("subject03").child("units").getValue().toString();
                units4_32 = snapshot.child("third_year").child("second_sem").child("subject04").child("units").getValue().toString();
                units5_32 = snapshot.child("third_year").child("second_sem").child("subject05").child("units").getValue().toString();
                units6_32 = snapshot.child("third_year").child("second_sem").child("subject06").child("units").getValue().toString();
                units7_32 = snapshot.child("third_year").child("second_sem").child("subject07").child("units").getValue().toString();
                units8_32 = snapshot.child("third_year").child("second_sem").child("subject08").child("units").getValue().toString();
                units9_32 = snapshot.child("third_year").child("second_sem").child("subject09").child("units").getValue().toString();
                units10_32 = snapshot.child("third_year").child("second_sem").child("subjec10").child("units").getValue().toString();

                units1_41 = snapshot.child("fourth_year").child("first_sem").child("subject01").child("units").getValue().toString();
                units2_41 = snapshot.child("fourth_year").child("first_sem").child("subject02").child("units").getValue().toString();
                units3_41 = snapshot.child("fourth_year").child("first_sem").child("subject03").child("units").getValue().toString();
                units4_41 = snapshot.child("fourth_year").child("first_sem").child("subject04").child("units").getValue().toString();
                units5_41 = snapshot.child("fourth_year").child("first_sem").child("subject05").child("units").getValue().toString();
                units6_41 = snapshot.child("fourth_year").child("first_sem").child("subject06").child("units").getValue().toString();
                units7_41 = snapshot.child("fourth_year").child("first_sem").child("subject07").child("units").getValue().toString();
                units8_41 = snapshot.child("fourth_year").child("first_sem").child("subject08").child("units").getValue().toString();
                units9_41 = snapshot.child("fourth_year").child("first_sem").child("subject09").child("units").getValue().toString();
                units10_41 = snapshot.child("fourth_year").child("first_sem").child("subject10").child("units").getValue().toString();


                units1_42 = snapshot.child("fourth_year").child("second_sem").child("subject01").child("units").getValue().toString();
                units2_42 = snapshot.child("fourth_year").child("second_sem").child("subject02").child("units").getValue().toString();
                units3_42 = snapshot.child("fourth_year").child("second_sem").child("subject03").child("units").getValue().toString();
                units4_42 = snapshot.child("fourth_year").child("second_sem").child("subject04").child("units").getValue().toString();
                units5_42 = snapshot.child("fourth_year").child("second_sem").child("subject05").child("units").getValue().toString();
                units6_42 = snapshot.child("fourth_year").child("second_sem").child("subject06").child("units").getValue().toString();
                units7_42 = snapshot.child("fourth_year").child("second_sem").child("subject07").child("units").getValue().toString();
                units8_42 = snapshot.child("fourth_year").child("second_sem").child("subject08").child("units").getValue().toString();
                units9_42 = snapshot.child("fourth_year").child("second_sem").child("subject09").child("units").getValue().toString();
                units10_42 = snapshot.child("fourth_year").child("second_sem").child("subject10").child("units").getValue().toString();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void BSOAgradeview() {

        course_curriculum.child("bsoa").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                SC1 = snapshot.child("first_year").child("first_sem").child("subject01").child("descriptive_title").getValue().toString();
                SC2 = snapshot.child("first_year").child("first_sem").child("subject02").child("descriptive_title").getValue().toString();
                SC3 = snapshot.child("first_year").child("first_sem").child("subject03").child("descriptive_title").getValue().toString();
                SC4 = snapshot.child("first_year").child("first_sem").child("subject04").child("descriptive_title").getValue().toString();
                SC5 = snapshot.child("first_year").child("first_sem").child("subject05").child("descriptive_title").getValue().toString();
                SC6 = snapshot.child("first_year").child("first_sem").child("subject06").child("descriptive_title").getValue().toString();
                SC7 = snapshot.child("first_year").child("first_sem").child("subject07").child("descriptive_title").getValue().toString();
                SC8 = snapshot.child("first_year").child("first_sem").child("subject08").child("descriptive_title").getValue().toString();
                SC9 = snapshot.child("first_year").child("first_sem").child("subject09").child("descriptive_title").getValue().toString();
                SC10 = snapshot.child("first_year").child("first_sem").child("subject10").child("descriptive_title").getValue().toString();

                SC1_2 = snapshot.child("first_year").child("second_sem").child("subject01").child("code").getValue().toString();
                SC2_2 = snapshot.child("first_year").child("second_sem").child("subject02").child("code").getValue().toString();
                SC3_2 = snapshot.child("first_year").child("second_sem").child("subject03").child("code").getValue().toString();
                SC4_2 = snapshot.child("first_year").child("second_sem").child("subject04").child("code").getValue().toString();
                SC5_2 = snapshot.child("first_year").child("second_sem").child("subject05").child("code").getValue().toString();
                SC6_2 = snapshot.child("first_year").child("second_sem").child("subject06").child("code").getValue().toString();
                SC7_2 = snapshot.child("first_year").child("second_sem").child("subject07").child("code").getValue().toString();
                SC8_2 = snapshot.child("first_year").child("second_sem").child("subject08").child("code").getValue().toString();
                SC9_2 = snapshot.child("first_year").child("second_sem").child("subject09").child("code").getValue().toString();
                SC10_2 = snapshot.child("first_year").child("second_sem").child("subject10").child("code").getValue().toString();


                SC1_21 = snapshot.child("second_year").child("first_sem").child("subject01").child("code").getValue().toString();
                SC2_21 = snapshot.child("second_year").child("first_sem").child("subject03").child("code").getValue().toString();
                SC3_21 = snapshot.child("second_year").child("first_sem").child("subject04").child("code").getValue().toString();
                SC4_21 = snapshot.child("second_year").child("first_sem").child("subject05").child("descriptive_title").getValue().toString();
                SC5_21 = snapshot.child("second_year").child("first_sem").child("subject06").child("descriptive_title").getValue().toString();
                SC6_21 = snapshot.child("second_year").child("first_sem").child("subject07").child("descriptive_title").getValue().toString();
                SC7_21 = snapshot.child("second_year").child("first_sem").child("subject08").child("descriptive_title").getValue().toString();
                SC8_21 = snapshot.child("second_year").child("first_sem").child("subject09").child("descriptive_title").getValue().toString();
                SC10_21 = snapshot.child("second_year").child("first_sem").child("subject10").child("descriptive_title").getValue().toString();


                SC1_22 = snapshot.child("third_year").child("second_sem").child("subject01").child("descriptive_title").getValue().toString();
                SC2_22 = snapshot.child("third_year").child("second_sem").child("subject02").child("descriptive_title").getValue().toString();
                SC3_22 = snapshot.child("third_year").child("second_sem").child("subject03").child("descriptive_title").getValue().toString();
                SC4_22 = snapshot.child("third_year").child("second_sem").child("subject04").child("descriptive_title").getValue().toString();
                SC5_22 = snapshot.child("third_year").child("second_sem").child("subject05").child("descriptive_title").getValue().toString();
                SC6_22 = snapshot.child("third_year").child("second_sem").child("subject06").child("descriptive_title").getValue().toString();
                SC7_22 = snapshot.child("third_year").child("second_sem").child("subject07").child("descriptive_title").getValue().toString();
                SC8_22 = snapshot.child("third_year").child("second_sem").child("subject08").child("descriptive_title").getValue().toString();
                SC9_22 = snapshot.child("third_year").child("second_sem").child("subject09").child("descriptive_title").getValue().toString();
                SC10_22 = snapshot.child("third_year").child("second_sem").child("subject10").child("descriptive_title").getValue().toString();


                SC1_31 = snapshot.child("third_year").child("first_sem").child("subject01").child("code").getValue().toString();
                SC2_31 = snapshot.child("third_year").child("first_sem").child("subject02").child("code").getValue().toString();
                SC3_31 = snapshot.child("third_year").child("first_sem").child("subject03").child("code").getValue().toString();
                SC4_31 = snapshot.child("third_year").child("first_sem").child("subject04").child("code").getValue().toString();
                SC5_31 = snapshot.child("third_year").child("first_sem").child("subject05").child("code").getValue().toString();
                SC6_31 = snapshot.child("third_year").child("first_sem").child("subject06").child("code").getValue().toString();
                SC7_31 = snapshot.child("third_year").child("first_sem").child("subject07").child("code").getValue().toString();
                SC8_31 = snapshot.child("third_year").child("first_sem").child("subject08").child("code").getValue().toString();
                SC9_31 = snapshot.child("third_year").child("first_sem").child("subject09").child("code").getValue().toString();
                SC10_31 = snapshot.child("third_year").child("first_sem").child("subject10").child("code").getValue().toString();

                SC1_32 = snapshot.child("third_year").child("second_sem").child("subject01").child("code").getValue().toString();
                SC2_32 = snapshot.child("third_year").child("second_sem").child("subject02").child("code").getValue().toString();
                SC3_32 = snapshot.child("third_year").child("second_sem").child("subject03").child("code").getValue().toString();
                SC4_32 = snapshot.child("third_year").child("second_sem").child("subject04").child("code").getValue().toString();
                SC5_32 = snapshot.child("third_year").child("second_sem").child("subject05").child("code").getValue().toString();
                SC6_32 = snapshot.child("third_year").child("second_sem").child("subject06").child("code").getValue().toString();
                SC7_32 = snapshot.child("third_year").child("second_sem").child("subject07").child("code").getValue().toString();
                SC8_32 = snapshot.child("third_year").child("second_sem").child("subject08").child("code").getValue().toString();
                SC9_32 = snapshot.child("third_year").child("second_sem").child("subject09").child("code").getValue().toString();
                SC10_32 = snapshot.child("third_year").child("second_sem").child("subject010").child("code").getValue().toString();


                SC1_41 = snapshot.child("fourth_year").child("first_sem").child("subject01").child("code").getValue().toString();
                SC2_41 = snapshot.child("fourth_year").child("first_sem").child("subject02").child("code").getValue().toString();
                SC3_41 = snapshot.child("fourth_year").child("first_sem").child("subject03").child("code").getValue().toString();
                SC4_41 = snapshot.child("fourth_year").child("first_sem").child("subject04").child("code").getValue().toString();
                SC5_41 = snapshot.child("fourth_year").child("first_sem").child("subject05").child("code").getValue().toString();
                SC6_41 = snapshot.child("fourth_year").child("first_sem").child("subject06").child("code").getValue().toString();
                // SC7_41 = snapshot.child("fourth_year").child("first_sem").child("subject07").child("code").getValue().toString();
                SC8_41 = snapshot.child("fourth_year").child("first_sem").child("subject08").child("code").getValue().toString();
                SC9_41 = snapshot.child("fourth_year").child("first_sem").child("subject09").child("code").getValue().toString();
                SC10_41 = snapshot.child("fourth_year").child("first_sem").child("subject010").child("code").getValue().toString();


                SC1_42 = snapshot.child("fourth_year").child("second_sem").child("subject01").child("code").getValue().toString();
                SC3_42 = snapshot.child("fourth_year").child("second_sem").child("subject02").child("code").getValue().toString();
                SC4_42 = snapshot.child("fourth_year").child("second_sem").child("subject03").child("code").getValue().toString();
                SC5_42 = snapshot.child("fourth_year").child("second_sem").child("subject04").child("code").getValue().toString();
                SC6_42 = snapshot.child("fourth_year").child("second_sem").child("subject05").child("code").getValue().toString();
                // SC7_42 = snapshot.child("fourth_year").child("second_sem").child("subject06").child("code").getValue().toString();
                SC8_42 = snapshot.child("fourth_year").child("second_sem").child("subject07").child("code").getValue().toString();
                SC9_42 = snapshot.child("fourth_year").child("second_sem").child("subject08").child("code").getValue().toString();
                SC10_42 = snapshot.child("fourth_year").child("second_sem").child("subject0").child("code").getValue().toString();
                SC2_42 = snapshot.child("fourth_year").child("second_sem").child("subject10").child("code").getValue().toString();


                des1 = snapshot.child("first_year").child("first_sem").child("subject01").child("descriptive_title").getValue().toString().trim();
                des2 = snapshot.child("first_year").child("first_sem").child("subject02").child("descriptive_title").getValue().toString();
                des3 = snapshot.child("first_year").child("first_sem").child("subject03").child("descriptive_title").getValue().toString();
                des4 = snapshot.child("first_year").child("first_sem").child("subject04").child("descriptive_title").getValue().toString();
                des5 = snapshot.child("first_year").child("first_sem").child("subject05").child("descriptive_title").getValue().toString();
                des7 = snapshot.child("first_year").child("first_sem").child("subject06").child("descriptive_title").getValue().toString();
                des8 = snapshot.child("first_year").child("first_sem").child("subject07").child("descriptive_title").getValue().toString();
                des9 = snapshot.child("first_year").child("first_sem").child("subject08").child("descriptive_title").getValue().toString();
                des6 = snapshot.child("first_year").child("first_sem").child("subject09").child("descriptive_title").getValue().toString();
                des10 = snapshot.child("first_year").child("first_sem").child("subject10").child("descriptive_title").getValue().toString();

                des1_2 = snapshot.child("first_year").child("second_sem").child("subject01").child("descriptive_title").getValue().toString();
                des2_2 = snapshot.child("first_year").child("second_sem").child("subject02").child("descriptive_title").getValue().toString();
                des3_2 = snapshot.child("first_year").child("second_sem").child("subject03").child("descriptive_title").getValue().toString();
                des4_2 = snapshot.child("first_year").child("second_sem").child("subject04").child("descriptive_title").getValue().toString();
                des5_2 = snapshot.child("first_year").child("second_sem").child("subject05").child("descriptive_title").getValue().toString();
                des6_2 = snapshot.child("first_year").child("second_sem").child("subject06").child("descriptive_title").getValue().toString();
                des7_2 = snapshot.child("first_year").child("second_sem").child("subject07").child("descriptive_title").getValue().toString();
                des8_2 = snapshot.child("first_year").child("second_sem").child("subject08").child("descriptive_title").getValue().toString();
                des9_2 = snapshot.child("first_year").child("second_sem").child("subject09").child("descriptive_title").getValue().toString();
                des10_2 = snapshot.child("first_year").child("second_sem").child("subject10").child("descriptive_title").getValue().toString();

                des1_21 = snapshot.child("second_year").child("first_sem").child("subject01").child("descriptive_title").getValue().toString();
                des2_21 = snapshot.child("second_year").child("first_sem").child("subject02").child("descriptive_title").getValue().toString();
                des3_21 = snapshot.child("second_year").child("first_sem").child("subject03").child("descriptive_title").getValue().toString();
                des4_21 = snapshot.child("second_year").child("first_sem").child("subject04").child("descriptive_title").getValue().toString();
                des5_21 = snapshot.child("second_year").child("first_sem").child("subject05").child("descriptive_title").getValue().toString();
                des6_21 = snapshot.child("second_year").child("first_sem").child("subject06").child("descriptive_title").getValue().toString();
                des7_21 = snapshot.child("second_year").child("first_sem").child("subject07").child("descriptive_title").getValue().toString();
                des8_21 = snapshot.child("second_year").child("first_sem").child("subject08").child("descriptive_title").getValue().toString();
                des9_21 = snapshot.child("second_year").child("first_sem").child("subject09").child("descriptive_title").getValue().toString();
                des10_21 = snapshot.child("second_year").child("first_sem").child("subject10").child("descriptive_title").getValue().toString();

                des1_22 = snapshot.child("third_year").child("second_sem").child("subject01").child("descriptive_title").getValue().toString();
                des2_22 = snapshot.child("third_year").child("second_sem").child("subject02").child("descriptive_title").getValue().toString();
                des3_22 = snapshot.child("third_year").child("second_sem").child("subject03").child("descriptive_title").getValue().toString();
                des4_22 = snapshot.child("third_year").child("second_sem").child("subject04").child("descriptive_title").getValue().toString();
                des5_22 = snapshot.child("third_year").child("second_sem").child("subject05").child("descriptive_title").getValue().toString();
                des6_22 = snapshot.child("third_year").child("second_sem").child("subject06").child("descriptive_title").getValue().toString();
                des7_22 = snapshot.child("third_year").child("second_sem").child("subject07").child("descriptive_title").getValue().toString();
                des8_22 = snapshot.child("third_year").child("second_sem").child("subject08").child("descriptive_title").getValue().toString();
                des9_22 = snapshot.child("third_year").child("second_sem").child("subject09").child("descriptive_title").getValue().toString();
                des10_22 = snapshot.child("third_year").child("second_sem").child("subject10").child("descriptive_title").getValue().toString();

                des1_31 = snapshot.child("third_year").child("first_sem").child("subject01").child("descriptive_title").getValue().toString();
                des2_31 = snapshot.child("third_year").child("first_sem").child("subject02").child("descriptive_title").getValue().toString();
                des3_31 = snapshot.child("third_year").child("first_sem").child("subject03").child("descriptive_title").getValue().toString();
                des4_31 = snapshot.child("third_year").child("first_sem").child("subject04").child("descriptive_title").getValue().toString();
                des5_31 = snapshot.child("third_year").child("first_sem").child("subject05").child("descriptive_title").getValue().toString();
                des6_31 = snapshot.child("third_year").child("first_sem").child("subject06").child("descriptive_title").getValue().toString();
                des7_31 = snapshot.child("third_year").child("first_sem").child("subject07").child("descriptive_title").getValue().toString();
                des8_31 = snapshot.child("third_year").child("first_sem").child("subject08").child("descriptive_title").getValue().toString();
                des9_31 = snapshot.child("third_year").child("first_sem").child("subject09").child("descriptive_title").getValue().toString();
                des10_31 = snapshot.child("third_year").child("first_sem").child("subject10").child("descriptive_title").getValue().toString();

                des1_32 = snapshot.child("third_year").child("second_sem").child("subject01").child("descriptive_title").getValue().toString();
                des2_32 = snapshot.child("third_year").child("second_sem").child("subject02").child("descriptive_title").getValue().toString();
                des3_32 = snapshot.child("third_year").child("second_sem").child("subject03").child("descriptive_title").getValue().toString();
                des4_32 = snapshot.child("third_year").child("second_sem").child("subject04").child("descriptive_title").getValue().toString();
                des5_32 = snapshot.child("third_year").child("second_sem").child("subject05").child("descriptive_title").getValue().toString();
                des6_32 = snapshot.child("third_year").child("second_sem").child("subject06").child("descriptive_title").getValue().toString();
                des1_32 = snapshot.child("third_year").child("second_sem").child("subject07").child("descriptive_title").getValue().toString();
                des1_32 = snapshot.child("third_year").child("second_sem").child("subject08").child("descriptive_title").getValue().toString();
                des1_32 = snapshot.child("third_year").child("second_sem").child("subject09").child("descriptive_title").getValue().toString();
                des1_32 = snapshot.child("third_year").child("second_sem").child("subject10").child("descriptive_title").getValue().toString();

                des1_41 = snapshot.child("fourth_year").child("first_sem").child("subject01").child("descriptive_title").getValue().toString();
                des2_41 = snapshot.child("fourth_year").child("first_sem").child("subject02").child("descriptive_title").getValue().toString();
                des3_41 = snapshot.child("fourth_year").child("first_sem").child("subject03").child("descriptive_title").getValue().toString();
                des4_41 = snapshot.child("fourth_year").child("first_sem").child("subject04").child("descriptive_title").getValue().toString();
                des5_41 = snapshot.child("fourth_year").child("first_sem").child("subject05").child("descriptive_title").getValue().toString();
                des6_41 = snapshot.child("fourth_year").child("first_sem").child("subject06").child("descriptive_title").getValue().toString();
                des7_41 = snapshot.child("fourth_year").child("first_sem").child("subject07").child("descriptive_title").getValue().toString();
                des8_41 = snapshot.child("fourth_year").child("first_sem").child("subject08").child("descriptive_title").getValue().toString();
                des9_41 = snapshot.child("fourth_year").child("first_sem").child("subject09").child("descriptive_title").getValue().toString();
                des10_41 = snapshot.child("fourth_year").child("first_sem").child("subject10").child("descriptive_title").getValue().toString();


                des1_42 = snapshot.child("fourth_year").child("second_sem").child("subject01").child("descriptive_title").getValue().toString();
                des2_42 = snapshot.child("fourth_year").child("second_sem").child("subject02").child("descriptive_title").getValue().toString();
                des3_42 = snapshot.child("fourth_year").child("second_sem").child("subject03").child("descriptive_title").getValue().toString();
                des4_42 = snapshot.child("fourth_year").child("second_sem").child("subject04").child("descriptive_title").getValue().toString();
                des5_42 = snapshot.child("fourth_year").child("second_sem").child("subject05").child("descriptive_title").getValue().toString();
                des6_42 = snapshot.child("fourth_year").child("second_sem").child("subject06").child("descriptive_title").getValue().toString();
                des7_42 = snapshot.child("fourth_year").child("second_sem").child("subject07").child("descriptive_title").getValue().toString();
                des8_42 = snapshot.child("fourth_year").child("second_sem").child("subject08").child("descriptive_title").getValue().toString();
                des9_42 = snapshot.child("fourth_year").child("second_sem").child("subject09").child("descriptive_title").getValue().toString();
                des10_42 = snapshot.child("fourth_year").child("second_sem").child("subject10").child("descriptive_title").getValue().toString();

                units1 = snapshot.child("first_year").child("first_sem").child("subject01").child("units").getValue().toString();
                units2 = snapshot.child("first_year").child("first_sem").child("subject02").child("units").getValue().toString();
                units3 = snapshot.child("first_year").child("first_sem").child("subject03").child("units").getValue().toString();
                units4 = snapshot.child("first_year").child("first_sem").child("subject04").child("units").getValue().toString();
                units5 = snapshot.child("first_year").child("first_sem").child("subject05").child("units").getValue().toString();
                units6 = snapshot.child("first_year").child("first_sem").child("subject06").child("units").getValue().toString();
                units7 = snapshot.child("first_year").child("first_sem").child("subject07").child("units").getValue().toString();
                units8 = snapshot.child("first_year").child("first_sem").child("subject08").child("units").getValue().toString();
                units9 = snapshot.child("first_year").child("first_sem").child("subject09").child("units").getValue().toString();
                units10 = snapshot.child("first_year").child("first_sem").child("subject10").child("units").getValue().toString();

                units1_12 = snapshot.child("first_year").child("second_sem").child("subject01").child("units").getValue().toString();
                units2_12 = snapshot.child("first_year").child("second_sem").child("subject02").child("units").getValue().toString();
                units3_12 = snapshot.child("first_year").child("second_sem").child("subject03").child("units").getValue().toString();
                units4_12 = snapshot.child("first_year").child("second_sem").child("subject04").child("units").getValue().toString();
                units5_12 = snapshot.child("first_year").child("second_sem").child("subject05").child("units").getValue().toString();
                units6_12 = snapshot.child("first_year").child("second_sem").child("subject06").child("units").getValue().toString();
                units7_12 = snapshot.child("first_year").child("second_sem").child("subject07").child("units").getValue().toString();
                units8_12 = snapshot.child("first_year").child("second_sem").child("subject08").child("units").getValue().toString();
                units9_12 = snapshot.child("first_year").child("second_sem").child("subject09").child("units").getValue().toString();
                units10_12 = snapshot.child("first_year").child("second_sem").child("subject10").child("units").getValue().toString();

                units1_21 = snapshot.child("second_year").child("first_sem").child("subject01").child("units").getValue().toString();
                units2_21 = snapshot.child("second_year").child("first_sem").child("subject02").child("units").getValue().toString();
                units3_21 = snapshot.child("second_year").child("first_sem").child("subject03").child("units").getValue().toString();
                units4_21 = snapshot.child("second_year").child("first_sem").child("subject04").child("units").getValue().toString();
                units5_21 = snapshot.child("second_year").child("first_sem").child("subject05").child("units").getValue().toString();
                units6_21 = snapshot.child("second_year").child("first_sem").child("subject06").child("units").getValue().toString();
                units7_21 = snapshot.child("second_year").child("first_sem").child("subject07").child("units").getValue().toString();
                units8_21 = snapshot.child("second_year").child("first_sem").child("subject08").child("units").getValue().toString();
                units9_21 = snapshot.child("second_year").child("first_sem").child("subject09").child("units").getValue().toString();
                units10_21 = snapshot.child("second_year").child("first_sem").child("subject10").child("units").getValue().toString();

                units1_22 = snapshot.child("second_year").child("second_sem").child("subject01").child("units").getValue().toString();
                units2_22 = snapshot.child("second_year").child("second_sem").child("subject02").child("units").getValue().toString();
                units3_22 = snapshot.child("second_year").child("second_sem").child("subject03").child("units").getValue().toString();
                units4_22 = snapshot.child("second_year").child("second_sem").child("subject04").child("units").getValue().toString();
                units5_22 = snapshot.child("second_year").child("second_sem").child("subject05").child("units").getValue().toString();
                units6_22 = snapshot.child("second_year").child("second_sem").child("subject06").child("units").getValue().toString();
                units7_22 = snapshot.child("second_year").child("second_sem").child("subject07").child("units").getValue().toString();
                units8_22 = snapshot.child("second_year").child("second_sem").child("subject08").child("units").getValue().toString();
                units9_22 = snapshot.child("second_year").child("second_sem").child("subject09").child("units").getValue().toString();
                units10_22 = snapshot.child("second_year").child("second_sem").child("subject10").child("units").getValue().toString();

                units1_31 = snapshot.child("third_year").child("first_sem").child("subject01").child("units").getValue().toString();
                units2_31 = snapshot.child("third_year").child("first_sem").child("subject02").child("units").getValue().toString();
                units3_31 = snapshot.child("third_year").child("first_sem").child("subject03").child("units").getValue().toString();
                units4_31 = snapshot.child("third_year").child("first_sem").child("subject04").child("units").getValue().toString();
                units5_31 = snapshot.child("third_year").child("first_sem").child("subject05").child("units").getValue().toString();
                units6_31 = snapshot.child("third_year").child("first_sem").child("subject06").child("units").getValue().toString();
                units7_31 = snapshot.child("third_year").child("first_sem").child("subject07").child("units").getValue().toString();
                units8_31 = snapshot.child("third_year").child("first_sem").child("subject08").child("units").getValue().toString();
                units9_31 = snapshot.child("third_year").child("first_sem").child("subject09").child("units").getValue().toString();
                units10_31 = snapshot.child("third_year").child("first_sem").child("subject10").child("units").getValue().toString();


                units1_32 = snapshot.child("third_year").child("second_sem").child("subject01").child("units").getValue().toString();
                units2_32 = snapshot.child("third_year").child("second_sem").child("subject02").child("units").getValue().toString();
                units3_32 = snapshot.child("third_year").child("second_sem").child("subject03").child("units").getValue().toString();
                units4_32 = snapshot.child("third_year").child("second_sem").child("subject04").child("units").getValue().toString();
                units5_32 = snapshot.child("third_year").child("second_sem").child("subject05").child("units").getValue().toString();
                units6_32 = snapshot.child("third_year").child("second_sem").child("subject06").child("units").getValue().toString();
                units7_32 = snapshot.child("third_year").child("second_sem").child("subject07").child("units").getValue().toString();
                units8_32 = snapshot.child("third_year").child("second_sem").child("subject08").child("units").getValue().toString();
                units9_32 = snapshot.child("third_year").child("second_sem").child("subject09").child("units").getValue().toString();
                units10_32 = snapshot.child("third_year").child("second_sem").child("subjec10").child("units").getValue().toString();

                units1_41 = snapshot.child("fourth_year").child("first_sem").child("subject01").child("units").getValue().toString();
                units2_41 = snapshot.child("fourth_year").child("first_sem").child("subject02").child("units").getValue().toString();
                units3_41 = snapshot.child("fourth_year").child("first_sem").child("subject03").child("units").getValue().toString();
                units4_41 = snapshot.child("fourth_year").child("first_sem").child("subject04").child("units").getValue().toString();
                units5_41 = snapshot.child("fourth_year").child("first_sem").child("subject05").child("units").getValue().toString();
                units6_41 = snapshot.child("fourth_year").child("first_sem").child("subject06").child("units").getValue().toString();
                units7_41 = snapshot.child("fourth_year").child("first_sem").child("subject07").child("units").getValue().toString();
                units8_41 = snapshot.child("fourth_year").child("first_sem").child("subject08").child("units").getValue().toString();
                units9_41 = snapshot.child("fourth_year").child("first_sem").child("subject09").child("units").getValue().toString();
                units10_41 = snapshot.child("fourth_year").child("first_sem").child("subject10").child("units").getValue().toString();


                units1_42 = snapshot.child("fourth_year").child("second_sem").child("subject01").child("units").getValue().toString();
                units2_42 = snapshot.child("fourth_year").child("second_sem").child("subject02").child("units").getValue().toString();
                units3_42 = snapshot.child("fourth_year").child("second_sem").child("subject03").child("units").getValue().toString();
                units4_42 = snapshot.child("fourth_year").child("second_sem").child("subject04").child("units").getValue().toString();
                units5_42 = snapshot.child("fourth_year").child("second_sem").child("subject05").child("units").getValue().toString();
                units6_42 = snapshot.child("fourth_year").child("second_sem").child("subject06").child("units").getValue().toString();
                units7_42 = snapshot.child("fourth_year").child("second_sem").child("subject07").child("units").getValue().toString();
                units8_42 = snapshot.child("fourth_year").child("second_sem").child("subject08").child("units").getValue().toString();
                units9_42 = snapshot.child("fourth_year").child("second_sem").child("subject09").child("units").getValue().toString();
                units10_42 = snapshot.child("fourth_year").child("second_sem").child("subject10").child("units").getValue().toString();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

   public void bsed_mgradeview() {
        course_curriculum.child("bsed").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                SC1 = snapshot.child("first_year").child("first_sem").child("subject01").child("descriptive_title").getValue().toString();
                SC2 = snapshot.child("first_year").child("first_sem").child("subject02").child("descriptive_title").getValue().toString();
                SC3 = snapshot.child("first_year").child("first_sem").child("subject03").child("descriptive_title").getValue().toString();
                SC4 = snapshot.child("first_year").child("first_sem").child("subject04").child("descriptive_title").getValue().toString();
                SC5 = snapshot.child("first_year").child("first_sem").child("subject05").child("descriptive_title").getValue().toString();
                SC6 = snapshot.child("first_year").child("first_sem").child("subject06").child("descriptive_title").getValue().toString();
                SC7 = snapshot.child("first_year").child("first_sem").child("subject07").child("descriptive_title").getValue().toString();
                SC8 = snapshot.child("first_year").child("first_sem").child("subject08").child("descriptive_title").getValue().toString();
                SC9 = snapshot.child("first_year").child("first_sem").child("subject09").child("descriptive_title").getValue().toString();
                SC10 = snapshot.child("first_year").child("first_sem").child("subject10").child("descriptive_title").getValue().toString();

                SC1_2 = snapshot.child("first_year").child("second_sem").child("subject01").child("code").getValue().toString();
                SC2_2 = snapshot.child("first_year").child("second_sem").child("subject02").child("code").getValue().toString();
                SC3_2 = snapshot.child("first_year").child("second_sem").child("subject03").child("code").getValue().toString();
                SC4_2 = snapshot.child("first_year").child("second_sem").child("subject04").child("code").getValue().toString();
                SC5_2 = snapshot.child("first_year").child("second_sem").child("subject05").child("code").getValue().toString();
                SC6_2 = snapshot.child("first_year").child("second_sem").child("subject06").child("code").getValue().toString();
                SC7_2 = snapshot.child("first_year").child("second_sem").child("subject07").child("code").getValue().toString();
                SC8_2 = snapshot.child("first_year").child("second_sem").child("subject08").child("code").getValue().toString();
                SC9_2 = snapshot.child("first_year").child("second_sem").child("subject09").child("code").getValue().toString();
                SC10_2 = snapshot.child("first_year").child("second_sem").child("subject10").child("code").getValue().toString();


                SC1_21 = snapshot.child("second_year").child("first_sem").child("subject01").child("code").getValue().toString();
                SC2_21 = snapshot.child("second_year").child("first_sem").child("subject03").child("code").getValue().toString();
                SC3_21 = snapshot.child("second_year").child("first_sem").child("subject04").child("code").getValue().toString();
                SC4_21 = snapshot.child("second_year").child("first_sem").child("subject05").child("descriptive_title").getValue().toString();
                SC5_21 = snapshot.child("second_year").child("first_sem").child("subject06").child("descriptive_title").getValue().toString();
                SC6_21 = snapshot.child("second_year").child("first_sem").child("subject07").child("descriptive_title").getValue().toString();
                SC7_21 = snapshot.child("second_year").child("first_sem").child("subject08").child("descriptive_title").getValue().toString();
                SC8_21 = snapshot.child("second_year").child("first_sem").child("subject09").child("descriptive_title").getValue().toString();
                SC10_21 = snapshot.child("second_year").child("first_sem").child("subject10").child("descriptive_title").getValue().toString();


                SC1_22 = snapshot.child("third_year").child("second_sem").child("subject01").child("descriptive_title").getValue().toString();
                SC2_22 = snapshot.child("third_year").child("second_sem").child("subject02").child("descriptive_title").getValue().toString();
                SC3_22 = snapshot.child("third_year").child("second_sem").child("subject03").child("descriptive_title").getValue().toString();
                SC4_22 = snapshot.child("third_year").child("second_sem").child("subject04").child("descriptive_title").getValue().toString();
                SC5_22 = snapshot.child("third_year").child("second_sem").child("subject05").child("descriptive_title").getValue().toString();
                SC6_22 = snapshot.child("third_year").child("second_sem").child("subject06").child("descriptive_title").getValue().toString();
                SC7_22 = snapshot.child("third_year").child("second_sem").child("subject07").child("descriptive_title").getValue().toString();
                SC8_22 = snapshot.child("third_year").child("second_sem").child("subject08").child("descriptive_title").getValue().toString();
                SC9_22 = snapshot.child("third_year").child("second_sem").child("subject09").child("descriptive_title").getValue().toString();
                SC10_22 = snapshot.child("third_year").child("second_sem").child("subject10").child("descriptive_title").getValue().toString();


                SC1_31 = snapshot.child("third_year").child("first_sem").child("subject01").child("code").getValue().toString();
                SC2_31 = snapshot.child("third_year").child("first_sem").child("subject02").child("code").getValue().toString();
                SC3_31 = snapshot.child("third_year").child("first_sem").child("subject03").child("code").getValue().toString();
                SC4_31 = snapshot.child("third_year").child("first_sem").child("subject04").child("code").getValue().toString();
                SC5_31 = snapshot.child("third_year").child("first_sem").child("subject05").child("code").getValue().toString();
                SC6_31 = snapshot.child("third_year").child("first_sem").child("subject06").child("code").getValue().toString();
                SC7_31 = snapshot.child("third_year").child("first_sem").child("subject07").child("code").getValue().toString();
                SC8_31 = snapshot.child("third_year").child("first_sem").child("subject08").child("code").getValue().toString();
                SC9_31 = snapshot.child("third_year").child("first_sem").child("subject09").child("code").getValue().toString();
                SC10_31 = snapshot.child("third_year").child("first_sem").child("subject10").child("code").getValue().toString();

                SC1_32 = snapshot.child("third_year").child("second_sem").child("subject01").child("code").getValue().toString();
                SC2_32 = snapshot.child("third_year").child("second_sem").child("subject02").child("code").getValue().toString();
                SC3_32 = snapshot.child("third_year").child("second_sem").child("subject03").child("code").getValue().toString();
                SC4_32 = snapshot.child("third_year").child("second_sem").child("subject04").child("code").getValue().toString();
                SC5_32 = snapshot.child("third_year").child("second_sem").child("subject05").child("code").getValue().toString();
                SC6_32 = snapshot.child("third_year").child("second_sem").child("subject06").child("code").getValue().toString();
                SC7_32 = snapshot.child("third_year").child("second_sem").child("subject07").child("code").getValue().toString();
                SC8_32 = snapshot.child("third_year").child("second_sem").child("subject08").child("code").getValue().toString();
                SC9_32 = snapshot.child("third_year").child("second_sem").child("subject09").child("code").getValue().toString();
                SC10_32 = snapshot.child("third_year").child("second_sem").child("subject010").child("code").getValue().toString();


                SC1_41 = snapshot.child("fourth_year").child("first_sem").child("subject01").child("code").getValue().toString();
                SC2_41 = snapshot.child("fourth_year").child("first_sem").child("subject02").child("code").getValue().toString();
                SC3_41 = snapshot.child("fourth_year").child("first_sem").child("subject03").child("code").getValue().toString();
                SC4_41 = snapshot.child("fourth_year").child("first_sem").child("subject04").child("code").getValue().toString();
                SC5_41 = snapshot.child("fourth_year").child("first_sem").child("subject05").child("code").getValue().toString();
                SC6_41 = snapshot.child("fourth_year").child("first_sem").child("subject06").child("code").getValue().toString();
                // SC7_41 = snapshot.child("fourth_year").child("first_sem").child("subject07").child("code").getValue().toString();
                SC8_41 = snapshot.child("fourth_year").child("first_sem").child("subject08").child("code").getValue().toString();
                SC9_41 = snapshot.child("fourth_year").child("first_sem").child("subject09").child("code").getValue().toString();
                SC10_41 = snapshot.child("fourth_year").child("first_sem").child("subject010").child("code").getValue().toString();


                SC1_42 = snapshot.child("fourth_year").child("second_sem").child("subject01").child("code").getValue().toString();
                SC3_42 = snapshot.child("fourth_year").child("second_sem").child("subject02").child("code").getValue().toString();
                SC4_42 = snapshot.child("fourth_year").child("second_sem").child("subject03").child("code").getValue().toString();
                SC5_42 = snapshot.child("fourth_year").child("second_sem").child("subject04").child("code").getValue().toString();
                SC6_42 = snapshot.child("fourth_year").child("second_sem").child("subject05").child("code").getValue().toString();
                // SC7_42 = snapshot.child("fourth_year").child("second_sem").child("subject06").child("code").getValue().toString();
                SC8_42 = snapshot.child("fourth_year").child("second_sem").child("subject07").child("code").getValue().toString();
                SC9_42 = snapshot.child("fourth_year").child("second_sem").child("subject08").child("code").getValue().toString();
                SC10_42 = snapshot.child("fourth_year").child("second_sem").child("subject0").child("code").getValue().toString();
                SC2_42 = snapshot.child("fourth_year").child("second_sem").child("subject10").child("code").getValue().toString();


                des1 = snapshot.child("first_year").child("first_sem").child("subject01").child("descriptive_title").getValue().toString().trim();
                des2 = snapshot.child("first_year").child("first_sem").child("subject02").child("descriptive_title").getValue().toString();
                des3 = snapshot.child("first_year").child("first_sem").child("subject03").child("descriptive_title").getValue().toString();
                des4 = snapshot.child("first_year").child("first_sem").child("subject04").child("descriptive_title").getValue().toString();
                des5 = snapshot.child("first_year").child("first_sem").child("subject05").child("descriptive_title").getValue().toString();
                des7 = snapshot.child("first_year").child("first_sem").child("subject06").child("descriptive_title").getValue().toString();
                des8 = snapshot.child("first_year").child("first_sem").child("subject07").child("descriptive_title").getValue().toString();
                des9 = snapshot.child("first_year").child("first_sem").child("subject08").child("descriptive_title").getValue().toString();
                des6 = snapshot.child("first_year").child("first_sem").child("subject09").child("descriptive_title").getValue().toString();
                des10 = snapshot.child("first_year").child("first_sem").child("subject10").child("descriptive_title").getValue().toString();

                des1_2 = snapshot.child("first_year").child("second_sem").child("subject01").child("descriptive_title").getValue().toString();
                des2_2 = snapshot.child("first_year").child("second_sem").child("subject02").child("descriptive_title").getValue().toString();
                des3_2 = snapshot.child("first_year").child("second_sem").child("subject03").child("descriptive_title").getValue().toString();
                des4_2 = snapshot.child("first_year").child("second_sem").child("subject04").child("descriptive_title").getValue().toString();
                des5_2 = snapshot.child("first_year").child("second_sem").child("subject05").child("descriptive_title").getValue().toString();
                des6_2 = snapshot.child("first_year").child("second_sem").child("subject06").child("descriptive_title").getValue().toString();
                des7_2 = snapshot.child("first_year").child("second_sem").child("subject07").child("descriptive_title").getValue().toString();
                des8_2 = snapshot.child("first_year").child("second_sem").child("subject08").child("descriptive_title").getValue().toString();
                des9_2 = snapshot.child("first_year").child("second_sem").child("subject09").child("descriptive_title").getValue().toString();
                des10_2 = snapshot.child("first_year").child("second_sem").child("subject10").child("descriptive_title").getValue().toString();

                des1_21 = snapshot.child("second_year").child("first_sem").child("subject01").child("descriptive_title").getValue().toString();
                des2_21 = snapshot.child("second_year").child("first_sem").child("subject02").child("descriptive_title").getValue().toString();
                des3_21 = snapshot.child("second_year").child("first_sem").child("subject03").child("descriptive_title").getValue().toString();
                des4_21 = snapshot.child("second_year").child("first_sem").child("subject04").child("descriptive_title").getValue().toString();
                des5_21 = snapshot.child("second_year").child("first_sem").child("subject05").child("descriptive_title").getValue().toString();
                des6_21 = snapshot.child("second_year").child("first_sem").child("subject06").child("descriptive_title").getValue().toString();
                des7_21 = snapshot.child("second_year").child("first_sem").child("subject07").child("descriptive_title").getValue().toString();
                des8_21 = snapshot.child("second_year").child("first_sem").child("subject08").child("descriptive_title").getValue().toString();
                des9_21 = snapshot.child("second_year").child("first_sem").child("subject09").child("descriptive_title").getValue().toString();
                des10_21 = snapshot.child("second_year").child("first_sem").child("subject10").child("descriptive_title").getValue().toString();

                des1_22 = snapshot.child("third_year").child("second_sem").child("subject01").child("descriptive_title").getValue().toString();
                des2_22 = snapshot.child("third_year").child("second_sem").child("subject02").child("descriptive_title").getValue().toString();
                des3_22 = snapshot.child("third_year").child("second_sem").child("subject03").child("descriptive_title").getValue().toString();
                des4_22 = snapshot.child("third_year").child("second_sem").child("subject04").child("descriptive_title").getValue().toString();
                des5_22 = snapshot.child("third_year").child("second_sem").child("subject05").child("descriptive_title").getValue().toString();
                des6_22 = snapshot.child("third_year").child("second_sem").child("subject06").child("descriptive_title").getValue().toString();
                des7_22 = snapshot.child("third_year").child("second_sem").child("subject07").child("descriptive_title").getValue().toString();
                des8_22 = snapshot.child("third_year").child("second_sem").child("subject08").child("descriptive_title").getValue().toString();
                des9_22 = snapshot.child("third_year").child("second_sem").child("subject09").child("descriptive_title").getValue().toString();
                des10_22 = snapshot.child("third_year").child("second_sem").child("subject10").child("descriptive_title").getValue().toString();

                des1_31 = snapshot.child("third_year").child("first_sem").child("subject01").child("descriptive_title").getValue().toString();
                des2_31 = snapshot.child("third_year").child("first_sem").child("subject02").child("descriptive_title").getValue().toString();
                des3_31 = snapshot.child("third_year").child("first_sem").child("subject03").child("descriptive_title").getValue().toString();
                des4_31 = snapshot.child("third_year").child("first_sem").child("subject04").child("descriptive_title").getValue().toString();
                des5_31 = snapshot.child("third_year").child("first_sem").child("subject05").child("descriptive_title").getValue().toString();
                des6_31 = snapshot.child("third_year").child("first_sem").child("subject06").child("descriptive_title").getValue().toString();
                des7_31 = snapshot.child("third_year").child("first_sem").child("subject07").child("descriptive_title").getValue().toString();
                des8_31 = snapshot.child("third_year").child("first_sem").child("subject08").child("descriptive_title").getValue().toString();
                des9_31 = snapshot.child("third_year").child("first_sem").child("subject09").child("descriptive_title").getValue().toString();
                des10_31 = snapshot.child("third_year").child("first_sem").child("subject10").child("descriptive_title").getValue().toString();

                des1_32 = snapshot.child("third_year").child("second_sem").child("subject01").child("descriptive_title").getValue().toString();
                des2_32 = snapshot.child("third_year").child("second_sem").child("subject02").child("descriptive_title").getValue().toString();
                des3_32 = snapshot.child("third_year").child("second_sem").child("subject03").child("descriptive_title").getValue().toString();
                des4_32 = snapshot.child("third_year").child("second_sem").child("subject04").child("descriptive_title").getValue().toString();
                des5_32 = snapshot.child("third_year").child("second_sem").child("subject05").child("descriptive_title").getValue().toString();
                des6_32 = snapshot.child("third_year").child("second_sem").child("subject06").child("descriptive_title").getValue().toString();
                des1_32 = snapshot.child("third_year").child("second_sem").child("subject07").child("descriptive_title").getValue().toString();
                des1_32 = snapshot.child("third_year").child("second_sem").child("subject08").child("descriptive_title").getValue().toString();
                des1_32 = snapshot.child("third_year").child("second_sem").child("subject09").child("descriptive_title").getValue().toString();
                des1_32 = snapshot.child("third_year").child("second_sem").child("subject10").child("descriptive_title").getValue().toString();

                des1_41 = snapshot.child("fourth_year").child("first_sem").child("subject01").child("descriptive_title").getValue().toString();
                des2_41 = snapshot.child("fourth_year").child("first_sem").child("subject02").child("descriptive_title").getValue().toString();
                des3_41 = snapshot.child("fourth_year").child("first_sem").child("subject03").child("descriptive_title").getValue().toString();
                des4_41 = snapshot.child("fourth_year").child("first_sem").child("subject04").child("descriptive_title").getValue().toString();
                des5_41 = snapshot.child("fourth_year").child("first_sem").child("subject05").child("descriptive_title").getValue().toString();
                des6_41 = snapshot.child("fourth_year").child("first_sem").child("subject06").child("descriptive_title").getValue().toString();
                des7_41 = snapshot.child("fourth_year").child("first_sem").child("subject07").child("descriptive_title").getValue().toString();
                des8_41 = snapshot.child("fourth_year").child("first_sem").child("subject08").child("descriptive_title").getValue().toString();
                des9_41 = snapshot.child("fourth_year").child("first_sem").child("subject09").child("descriptive_title").getValue().toString();
                des10_41 = snapshot.child("fourth_year").child("first_sem").child("subject10").child("descriptive_title").getValue().toString();


                des1_42 = snapshot.child("fourth_year").child("second_sem").child("subject01").child("descriptive_title").getValue().toString();
                des2_42 = snapshot.child("fourth_year").child("second_sem").child("subject02").child("descriptive_title").getValue().toString();
                des3_42 = snapshot.child("fourth_year").child("second_sem").child("subject03").child("descriptive_title").getValue().toString();
                des4_42 = snapshot.child("fourth_year").child("second_sem").child("subject04").child("descriptive_title").getValue().toString();
                des5_42 = snapshot.child("fourth_year").child("second_sem").child("subject05").child("descriptive_title").getValue().toString();
                des6_42 = snapshot.child("fourth_year").child("second_sem").child("subject06").child("descriptive_title").getValue().toString();
                des7_42 = snapshot.child("fourth_year").child("second_sem").child("subject07").child("descriptive_title").getValue().toString();
                des8_42 = snapshot.child("fourth_year").child("second_sem").child("subject08").child("descriptive_title").getValue().toString();
                des9_42 = snapshot.child("fourth_year").child("second_sem").child("subject09").child("descriptive_title").getValue().toString();
                des10_42 = snapshot.child("fourth_year").child("second_sem").child("subject10").child("descriptive_title").getValue().toString();

                units1 = snapshot.child("first_year").child("first_sem").child("subject01").child("units").getValue().toString();
                units2 = snapshot.child("first_year").child("first_sem").child("subject02").child("units").getValue().toString();
                units3 = snapshot.child("first_year").child("first_sem").child("subject03").child("units").getValue().toString();
                units4 = snapshot.child("first_year").child("first_sem").child("subject04").child("units").getValue().toString();
                units5 = snapshot.child("first_year").child("first_sem").child("subject05").child("units").getValue().toString();
                units6 = snapshot.child("first_year").child("first_sem").child("subject06").child("units").getValue().toString();
                units7 = snapshot.child("first_year").child("first_sem").child("subject07").child("units").getValue().toString();
                units8 = snapshot.child("first_year").child("first_sem").child("subject08").child("units").getValue().toString();
                units9 = snapshot.child("first_year").child("first_sem").child("subject09").child("units").getValue().toString();
                units10 = snapshot.child("first_year").child("first_sem").child("subject10").child("units").getValue().toString();

                units1_12 = snapshot.child("first_year").child("second_sem").child("subject01").child("units").getValue().toString();
                units2_12 = snapshot.child("first_year").child("second_sem").child("subject02").child("units").getValue().toString();
                units3_12 = snapshot.child("first_year").child("second_sem").child("subject03").child("units").getValue().toString();
                units4_12 = snapshot.child("first_year").child("second_sem").child("subject04").child("units").getValue().toString();
                units5_12 = snapshot.child("first_year").child("second_sem").child("subject05").child("units").getValue().toString();
                units6_12 = snapshot.child("first_year").child("second_sem").child("subject06").child("units").getValue().toString();
                units7_12 = snapshot.child("first_year").child("second_sem").child("subject07").child("units").getValue().toString();
                units8_12 = snapshot.child("first_year").child("second_sem").child("subject08").child("units").getValue().toString();
                units9_12 = snapshot.child("first_year").child("second_sem").child("subject09").child("units").getValue().toString();
                units10_12 = snapshot.child("first_year").child("second_sem").child("subject10").child("units").getValue().toString();

                units1_21 = snapshot.child("second_year").child("first_sem").child("subject01").child("units").getValue().toString();
                units2_21 = snapshot.child("second_year").child("first_sem").child("subject02").child("units").getValue().toString();
                units3_21 = snapshot.child("second_year").child("first_sem").child("subject03").child("units").getValue().toString();
                units4_21 = snapshot.child("second_year").child("first_sem").child("subject04").child("units").getValue().toString();
                units5_21 = snapshot.child("second_year").child("first_sem").child("subject05").child("units").getValue().toString();
                units6_21 = snapshot.child("second_year").child("first_sem").child("subject06").child("units").getValue().toString();
                units7_21 = snapshot.child("second_year").child("first_sem").child("subject07").child("units").getValue().toString();
                units8_21 = snapshot.child("second_year").child("first_sem").child("subject08").child("units").getValue().toString();
                units9_21 = snapshot.child("second_year").child("first_sem").child("subject09").child("units").getValue().toString();
                units10_21 = snapshot.child("second_year").child("first_sem").child("subject10").child("units").getValue().toString();

                units1_22 = snapshot.child("second_year").child("second_sem").child("subject01").child("units").getValue().toString();
                units2_22 = snapshot.child("second_year").child("second_sem").child("subject02").child("units").getValue().toString();
                units3_22 = snapshot.child("second_year").child("second_sem").child("subject03").child("units").getValue().toString();
                units4_22 = snapshot.child("second_year").child("second_sem").child("subject04").child("units").getValue().toString();
                units5_22 = snapshot.child("second_year").child("second_sem").child("subject05").child("units").getValue().toString();
                units6_22 = snapshot.child("second_year").child("second_sem").child("subject06").child("units").getValue().toString();
                units7_22 = snapshot.child("second_year").child("second_sem").child("subject07").child("units").getValue().toString();
                units8_22 = snapshot.child("second_year").child("second_sem").child("subject08").child("units").getValue().toString();
                units9_22 = snapshot.child("second_year").child("second_sem").child("subject09").child("units").getValue().toString();
                units10_22 = snapshot.child("second_year").child("second_sem").child("subject10").child("units").getValue().toString();

                units1_31 = snapshot.child("third_year").child("first_sem").child("subject01").child("units").getValue().toString();
                units2_31 = snapshot.child("third_year").child("first_sem").child("subject02").child("units").getValue().toString();
                units3_31 = snapshot.child("third_year").child("first_sem").child("subject03").child("units").getValue().toString();
                units4_31 = snapshot.child("third_year").child("first_sem").child("subject04").child("units").getValue().toString();
                units5_31 = snapshot.child("third_year").child("first_sem").child("subject05").child("units").getValue().toString();
                units6_31 = snapshot.child("third_year").child("first_sem").child("subject06").child("units").getValue().toString();
                units7_31 = snapshot.child("third_year").child("first_sem").child("subject07").child("units").getValue().toString();
                units8_31 = snapshot.child("third_year").child("first_sem").child("subject08").child("units").getValue().toString();
                units9_31 = snapshot.child("third_year").child("first_sem").child("subject09").child("units").getValue().toString();
                units10_31 = snapshot.child("third_year").child("first_sem").child("subject10").child("units").getValue().toString();


                units1_32 = snapshot.child("third_year").child("second_sem").child("subject01").child("units").getValue().toString();
                units2_32 = snapshot.child("third_year").child("second_sem").child("subject02").child("units").getValue().toString();
                units3_32 = snapshot.child("third_year").child("second_sem").child("subject03").child("units").getValue().toString();
                units4_32 = snapshot.child("third_year").child("second_sem").child("subject04").child("units").getValue().toString();
                units5_32 = snapshot.child("third_year").child("second_sem").child("subject05").child("units").getValue().toString();
                units6_32 = snapshot.child("third_year").child("second_sem").child("subject06").child("units").getValue().toString();
                units7_32 = snapshot.child("third_year").child("second_sem").child("subject07").child("units").getValue().toString();
                units8_32 = snapshot.child("third_year").child("second_sem").child("subject08").child("units").getValue().toString();
                units9_32 = snapshot.child("third_year").child("second_sem").child("subject09").child("units").getValue().toString();
                units10_32 = snapshot.child("third_year").child("second_sem").child("subjec10").child("units").getValue().toString();

                units1_41 = snapshot.child("fourth_year").child("first_sem").child("subject01").child("units").getValue().toString();
                units2_41 = snapshot.child("fourth_year").child("first_sem").child("subject02").child("units").getValue().toString();
                units3_41 = snapshot.child("fourth_year").child("first_sem").child("subject03").child("units").getValue().toString();
                units4_41 = snapshot.child("fourth_year").child("first_sem").child("subject04").child("units").getValue().toString();
                units5_41 = snapshot.child("fourth_year").child("first_sem").child("subject05").child("units").getValue().toString();
                units6_41 = snapshot.child("fourth_year").child("first_sem").child("subject06").child("units").getValue().toString();
                units7_41 = snapshot.child("fourth_year").child("first_sem").child("subject07").child("units").getValue().toString();
                units8_41 = snapshot.child("fourth_year").child("first_sem").child("subject08").child("units").getValue().toString();
                units9_41 = snapshot.child("fourth_year").child("first_sem").child("subject09").child("units").getValue().toString();
                units10_41 = snapshot.child("fourth_year").child("first_sem").child("subject10").child("units").getValue().toString();


                units1_42 = snapshot.child("fourth_year").child("second_sem").child("subject01").child("units").getValue().toString();
                units2_42 = snapshot.child("fourth_year").child("second_sem").child("subject02").child("units").getValue().toString();
                units3_42 = snapshot.child("fourth_year").child("second_sem").child("subject03").child("units").getValue().toString();
                units4_42 = snapshot.child("fourth_year").child("second_sem").child("subject04").child("units").getValue().toString();
                units5_42 = snapshot.child("fourth_year").child("second_sem").child("subject05").child("units").getValue().toString();
                units6_42 = snapshot.child("fourth_year").child("second_sem").child("subject06").child("units").getValue().toString();
                units7_42 = snapshot.child("fourth_year").child("second_sem").child("subject07").child("units").getValue().toString();
                units8_42 = snapshot.child("fourth_year").child("second_sem").child("subject08").child("units").getValue().toString();
                units9_42 = snapshot.child("fourth_year").child("second_sem").child("subject09").child("units").getValue().toString();
                units10_42 = snapshot.child("fourth_year").child("second_sem").child("subject10").child("units").getValue().toString();

            }

                @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void beedgradeview() {
        course_curriculum.child("beed").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                SC1 = snapshot.child("first_year").child("first_sem").child("subject01").child("descriptive_title").getValue().toString();
                SC2 = snapshot.child("first_year").child("first_sem").child("subject02").child("descriptive_title").getValue().toString();
                SC3 = snapshot.child("first_year").child("first_sem").child("subject03").child("descriptive_title").getValue().toString();
                SC4 = snapshot.child("first_year").child("first_sem").child("subject04").child("descriptive_title").getValue().toString();
                SC5 = snapshot.child("first_year").child("first_sem").child("subject05").child("descriptive_title").getValue().toString();
                SC6 = snapshot.child("first_year").child("first_sem").child("subject06").child("descriptive_title").getValue().toString();
                SC7 = snapshot.child("first_year").child("first_sem").child("subject07").child("descriptive_title").getValue().toString();
                SC8 = snapshot.child("first_year").child("first_sem").child("subject08").child("descriptive_title").getValue().toString();
                SC9 = snapshot.child("first_year").child("first_sem").child("subject09").child("descriptive_title").getValue().toString();
                SC10 = snapshot.child("first_year").child("first_sem").child("subject10").child("descriptive_title").getValue().toString();

                SC1_2 = snapshot.child("first_year").child("second_sem").child("subject01").child("code").getValue().toString();
                SC2_2 = snapshot.child("first_year").child("second_sem").child("subject02").child("code").getValue().toString();
                SC3_2 = snapshot.child("first_year").child("second_sem").child("subject03").child("code").getValue().toString();
                SC4_2 = snapshot.child("first_year").child("second_sem").child("subject04").child("code").getValue().toString();
                SC5_2 = snapshot.child("first_year").child("second_sem").child("subject05").child("code").getValue().toString();
                SC6_2 = snapshot.child("first_year").child("second_sem").child("subject06").child("code").getValue().toString();
                SC7_2 = snapshot.child("first_year").child("second_sem").child("subject07").child("code").getValue().toString();
                SC8_2 = snapshot.child("first_year").child("second_sem").child("subject08").child("code").getValue().toString();
                SC9_2 = snapshot.child("first_year").child("second_sem").child("subject09").child("code").getValue().toString();
                SC10_2 = snapshot.child("first_year").child("second_sem").child("subject10").child("code").getValue().toString();


                SC1_21 = snapshot.child("second_year").child("first_sem").child("subject01").child("code").getValue().toString();
                SC2_21 = snapshot.child("second_year").child("first_sem").child("subject03").child("code").getValue().toString();
                SC3_21 = snapshot.child("second_year").child("first_sem").child("subject04").child("code").getValue().toString();
                SC4_21 = snapshot.child("second_year").child("first_sem").child("subject05").child("descriptive_title").getValue().toString();
                SC5_21 = snapshot.child("second_year").child("first_sem").child("subject06").child("descriptive_title").getValue().toString();
                SC6_21 = snapshot.child("second_year").child("first_sem").child("subject07").child("descriptive_title").getValue().toString();
                SC7_21 = snapshot.child("second_year").child("first_sem").child("subject08").child("descriptive_title").getValue().toString();
                SC8_21 = snapshot.child("second_year").child("first_sem").child("subject09").child("descriptive_title").getValue().toString();
                SC10_21 = snapshot.child("second_year").child("first_sem").child("subject10").child("descriptive_title").getValue().toString();


                SC1_22 = snapshot.child("third_year").child("second_sem").child("subject01").child("descriptive_title").getValue().toString();
                SC2_22 = snapshot.child("third_year").child("second_sem").child("subject02").child("descriptive_title").getValue().toString();
                SC3_22 = snapshot.child("third_year").child("second_sem").child("subject03").child("descriptive_title").getValue().toString();
                SC4_22 = snapshot.child("third_year").child("second_sem").child("subject04").child("descriptive_title").getValue().toString();
                SC5_22 = snapshot.child("third_year").child("second_sem").child("subject05").child("descriptive_title").getValue().toString();
                SC6_22 = snapshot.child("third_year").child("second_sem").child("subject06").child("descriptive_title").getValue().toString();
                SC7_22 = snapshot.child("third_year").child("second_sem").child("subject07").child("descriptive_title").getValue().toString();
                SC8_22 = snapshot.child("third_year").child("second_sem").child("subject08").child("descriptive_title").getValue().toString();
                SC9_22 = snapshot.child("third_year").child("second_sem").child("subject09").child("descriptive_title").getValue().toString();
                SC10_22 = snapshot.child("third_year").child("second_sem").child("subject10").child("descriptive_title").getValue().toString();


                SC1_31 = snapshot.child("third_year").child("first_sem").child("subject01").child("code").getValue().toString();
                SC2_31 = snapshot.child("third_year").child("first_sem").child("subject02").child("code").getValue().toString();
                SC3_31 = snapshot.child("third_year").child("first_sem").child("subject03").child("code").getValue().toString();
                SC4_31 = snapshot.child("third_year").child("first_sem").child("subject04").child("code").getValue().toString();
                SC5_31 = snapshot.child("third_year").child("first_sem").child("subject05").child("code").getValue().toString();
                SC6_31 = snapshot.child("third_year").child("first_sem").child("subject06").child("code").getValue().toString();
                SC7_31 = snapshot.child("third_year").child("first_sem").child("subject07").child("code").getValue().toString();
                SC8_31 = snapshot.child("third_year").child("first_sem").child("subject08").child("code").getValue().toString();
                SC9_31 = snapshot.child("third_year").child("first_sem").child("subject09").child("code").getValue().toString();
                SC10_31 = snapshot.child("third_year").child("first_sem").child("subject10").child("code").getValue().toString();

                SC1_32 = snapshot.child("third_year").child("second_sem").child("subject01").child("code").getValue().toString();
                SC2_32 = snapshot.child("third_year").child("second_sem").child("subject02").child("code").getValue().toString();
                SC3_32 = snapshot.child("third_year").child("second_sem").child("subject03").child("code").getValue().toString();
                SC4_32 = snapshot.child("third_year").child("second_sem").child("subject04").child("code").getValue().toString();
                SC5_32 = snapshot.child("third_year").child("second_sem").child("subject05").child("code").getValue().toString();
                SC6_32 = snapshot.child("third_year").child("second_sem").child("subject06").child("code").getValue().toString();
                SC7_32 = snapshot.child("third_year").child("second_sem").child("subject07").child("code").getValue().toString();
                SC8_32 = snapshot.child("third_year").child("second_sem").child("subject08").child("code").getValue().toString();
                SC9_32 = snapshot.child("third_year").child("second_sem").child("subject09").child("code").getValue().toString();
                SC10_32 = snapshot.child("third_year").child("second_sem").child("subject010").child("code").getValue().toString();


                SC1_41 = snapshot.child("fourth_year").child("first_sem").child("subject01").child("code").getValue().toString();
                SC2_41 = snapshot.child("fourth_year").child("first_sem").child("subject02").child("code").getValue().toString();
                SC3_41 = snapshot.child("fourth_year").child("first_sem").child("subject03").child("code").getValue().toString();
                SC4_41 = snapshot.child("fourth_year").child("first_sem").child("subject04").child("code").getValue().toString();
                SC5_41 = snapshot.child("fourth_year").child("first_sem").child("subject05").child("code").getValue().toString();
                SC6_41 = snapshot.child("fourth_year").child("first_sem").child("subject06").child("code").getValue().toString();
                // SC7_41 = snapshot.child("fourth_year").child("first_sem").child("subject07").child("code").getValue().toString();
                SC8_41 = snapshot.child("fourth_year").child("first_sem").child("subject08").child("code").getValue().toString();
                SC9_41 = snapshot.child("fourth_year").child("first_sem").child("subject09").child("code").getValue().toString();
                SC10_41 = snapshot.child("fourth_year").child("first_sem").child("subject010").child("code").getValue().toString();


                SC1_42 = snapshot.child("fourth_year").child("second_sem").child("subject01").child("code").getValue().toString();
                SC3_42 = snapshot.child("fourth_year").child("second_sem").child("subject02").child("code").getValue().toString();
                SC4_42 = snapshot.child("fourth_year").child("second_sem").child("subject03").child("code").getValue().toString();
                SC5_42 = snapshot.child("fourth_year").child("second_sem").child("subject04").child("code").getValue().toString();
                SC6_42 = snapshot.child("fourth_year").child("second_sem").child("subject05").child("code").getValue().toString();
                // SC7_42 = snapshot.child("fourth_year").child("second_sem").child("subject06").child("code").getValue().toString();
                SC8_42 = snapshot.child("fourth_year").child("second_sem").child("subject07").child("code").getValue().toString();
                SC9_42 = snapshot.child("fourth_year").child("second_sem").child("subject08").child("code").getValue().toString();
                SC10_42 = snapshot.child("fourth_year").child("second_sem").child("subject0").child("code").getValue().toString();
                SC2_42 = snapshot.child("fourth_year").child("second_sem").child("subject10").child("code").getValue().toString();


                des1 = snapshot.child("first_year").child("first_sem").child("subject01").child("descriptive_title").getValue().toString().trim();
                des2 = snapshot.child("first_year").child("first_sem").child("subject02").child("descriptive_title").getValue().toString();
                des3 = snapshot.child("first_year").child("first_sem").child("subject03").child("descriptive_title").getValue().toString();
                des4 = snapshot.child("first_year").child("first_sem").child("subject04").child("descriptive_title").getValue().toString();
                des5 = snapshot.child("first_year").child("first_sem").child("subject05").child("descriptive_title").getValue().toString();
                des7 = snapshot.child("first_year").child("first_sem").child("subject06").child("descriptive_title").getValue().toString();
                des8 = snapshot.child("first_year").child("first_sem").child("subject07").child("descriptive_title").getValue().toString();
                des9 = snapshot.child("first_year").child("first_sem").child("subject08").child("descriptive_title").getValue().toString();
                des6 = snapshot.child("first_year").child("first_sem").child("subject09").child("descriptive_title").getValue().toString();
                des10 = snapshot.child("first_year").child("first_sem").child("subject10").child("descriptive_title").getValue().toString();

                des1_2 = snapshot.child("first_year").child("second_sem").child("subject01").child("descriptive_title").getValue().toString();
                des2_2 = snapshot.child("first_year").child("second_sem").child("subject02").child("descriptive_title").getValue().toString();
                des3_2 = snapshot.child("first_year").child("second_sem").child("subject03").child("descriptive_title").getValue().toString();
                des4_2 = snapshot.child("first_year").child("second_sem").child("subject04").child("descriptive_title").getValue().toString();
                des5_2 = snapshot.child("first_year").child("second_sem").child("subject05").child("descriptive_title").getValue().toString();
                des6_2 = snapshot.child("first_year").child("second_sem").child("subject06").child("descriptive_title").getValue().toString();
                des7_2 = snapshot.child("first_year").child("second_sem").child("subject07").child("descriptive_title").getValue().toString();
                des8_2 = snapshot.child("first_year").child("second_sem").child("subject08").child("descriptive_title").getValue().toString();
                des9_2 = snapshot.child("first_year").child("second_sem").child("subject09").child("descriptive_title").getValue().toString();
                des10_2 = snapshot.child("first_year").child("second_sem").child("subject10").child("descriptive_title").getValue().toString();

                des1_21 = snapshot.child("second_year").child("first_sem").child("subject01").child("descriptive_title").getValue().toString();
                des2_21 = snapshot.child("second_year").child("first_sem").child("subject02").child("descriptive_title").getValue().toString();
                des3_21 = snapshot.child("second_year").child("first_sem").child("subject03").child("descriptive_title").getValue().toString();
                des4_21 = snapshot.child("second_year").child("first_sem").child("subject04").child("descriptive_title").getValue().toString();
                des5_21 = snapshot.child("second_year").child("first_sem").child("subject05").child("descriptive_title").getValue().toString();
                des6_21 = snapshot.child("second_year").child("first_sem").child("subject06").child("descriptive_title").getValue().toString();
                des7_21 = snapshot.child("second_year").child("first_sem").child("subject07").child("descriptive_title").getValue().toString();
                des8_21 = snapshot.child("second_year").child("first_sem").child("subject08").child("descriptive_title").getValue().toString();
                des9_21 = snapshot.child("second_year").child("first_sem").child("subject09").child("descriptive_title").getValue().toString();
                des10_21 = snapshot.child("second_year").child("first_sem").child("subject10").child("descriptive_title").getValue().toString();

                des1_22 = snapshot.child("third_year").child("second_sem").child("subject01").child("descriptive_title").getValue().toString();
                des2_22 = snapshot.child("third_year").child("second_sem").child("subject02").child("descriptive_title").getValue().toString();
                des3_22 = snapshot.child("third_year").child("second_sem").child("subject03").child("descriptive_title").getValue().toString();
                des4_22 = snapshot.child("third_year").child("second_sem").child("subject04").child("descriptive_title").getValue().toString();
                des5_22 = snapshot.child("third_year").child("second_sem").child("subject05").child("descriptive_title").getValue().toString();
                des6_22 = snapshot.child("third_year").child("second_sem").child("subject06").child("descriptive_title").getValue().toString();
                des7_22 = snapshot.child("third_year").child("second_sem").child("subject07").child("descriptive_title").getValue().toString();
                des8_22 = snapshot.child("third_year").child("second_sem").child("subject08").child("descriptive_title").getValue().toString();
                des9_22 = snapshot.child("third_year").child("second_sem").child("subject09").child("descriptive_title").getValue().toString();
                des10_22 = snapshot.child("third_year").child("second_sem").child("subject10").child("descriptive_title").getValue().toString();

                des1_31 = snapshot.child("third_year").child("first_sem").child("subject01").child("descriptive_title").getValue().toString();
                des2_31 = snapshot.child("third_year").child("first_sem").child("subject02").child("descriptive_title").getValue().toString();
                des3_31 = snapshot.child("third_year").child("first_sem").child("subject03").child("descriptive_title").getValue().toString();
                des4_31 = snapshot.child("third_year").child("first_sem").child("subject04").child("descriptive_title").getValue().toString();
                des5_31 = snapshot.child("third_year").child("first_sem").child("subject05").child("descriptive_title").getValue().toString();
                des6_31 = snapshot.child("third_year").child("first_sem").child("subject06").child("descriptive_title").getValue().toString();
                des7_31 = snapshot.child("third_year").child("first_sem").child("subject07").child("descriptive_title").getValue().toString();
                des8_31 = snapshot.child("third_year").child("first_sem").child("subject08").child("descriptive_title").getValue().toString();
                des9_31 = snapshot.child("third_year").child("first_sem").child("subject09").child("descriptive_title").getValue().toString();
                des10_31 = snapshot.child("third_year").child("first_sem").child("subject10").child("descriptive_title").getValue().toString();

                des1_32 = snapshot.child("third_year").child("second_sem").child("subject01").child("descriptive_title").getValue().toString();
                des2_32 = snapshot.child("third_year").child("second_sem").child("subject02").child("descriptive_title").getValue().toString();
                des3_32 = snapshot.child("third_year").child("second_sem").child("subject03").child("descriptive_title").getValue().toString();
                des4_32 = snapshot.child("third_year").child("second_sem").child("subject04").child("descriptive_title").getValue().toString();
                des5_32 = snapshot.child("third_year").child("second_sem").child("subject05").child("descriptive_title").getValue().toString();
                des6_32 = snapshot.child("third_year").child("second_sem").child("subject06").child("descriptive_title").getValue().toString();
                des1_32 = snapshot.child("third_year").child("second_sem").child("subject07").child("descriptive_title").getValue().toString();
                des1_32 = snapshot.child("third_year").child("second_sem").child("subject08").child("descriptive_title").getValue().toString();
                des1_32 = snapshot.child("third_year").child("second_sem").child("subject09").child("descriptive_title").getValue().toString();
                des1_32 = snapshot.child("third_year").child("second_sem").child("subject10").child("descriptive_title").getValue().toString();

                des1_41 = snapshot.child("fourth_year").child("first_sem").child("subject01").child("descriptive_title").getValue().toString();
                des2_41 = snapshot.child("fourth_year").child("first_sem").child("subject02").child("descriptive_title").getValue().toString();
                des3_41 = snapshot.child("fourth_year").child("first_sem").child("subject03").child("descriptive_title").getValue().toString();
                des4_41 = snapshot.child("fourth_year").child("first_sem").child("subject04").child("descriptive_title").getValue().toString();
                des5_41 = snapshot.child("fourth_year").child("first_sem").child("subject05").child("descriptive_title").getValue().toString();
                des6_41 = snapshot.child("fourth_year").child("first_sem").child("subject06").child("descriptive_title").getValue().toString();
                des7_41 = snapshot.child("fourth_year").child("first_sem").child("subject07").child("descriptive_title").getValue().toString();
                des8_41 = snapshot.child("fourth_year").child("first_sem").child("subject08").child("descriptive_title").getValue().toString();
                des9_41 = snapshot.child("fourth_year").child("first_sem").child("subject09").child("descriptive_title").getValue().toString();
                des10_41 = snapshot.child("fourth_year").child("first_sem").child("subject10").child("descriptive_title").getValue().toString();


                des1_42 = snapshot.child("fourth_year").child("second_sem").child("subject01").child("descriptive_title").getValue().toString();
                des2_42 = snapshot.child("fourth_year").child("second_sem").child("subject02").child("descriptive_title").getValue().toString();
                des3_42 = snapshot.child("fourth_year").child("second_sem").child("subject03").child("descriptive_title").getValue().toString();
                des4_42 = snapshot.child("fourth_year").child("second_sem").child("subject04").child("descriptive_title").getValue().toString();
                des5_42 = snapshot.child("fourth_year").child("second_sem").child("subject05").child("descriptive_title").getValue().toString();
                des6_42 = snapshot.child("fourth_year").child("second_sem").child("subject06").child("descriptive_title").getValue().toString();
                des7_42 = snapshot.child("fourth_year").child("second_sem").child("subject07").child("descriptive_title").getValue().toString();
                des8_42 = snapshot.child("fourth_year").child("second_sem").child("subject08").child("descriptive_title").getValue().toString();
                des9_42 = snapshot.child("fourth_year").child("second_sem").child("subject09").child("descriptive_title").getValue().toString();
                des10_42 = snapshot.child("fourth_year").child("second_sem").child("subject10").child("descriptive_title").getValue().toString();

                units1 = snapshot.child("first_year").child("first_sem").child("subject01").child("units").getValue().toString();
                units2 = snapshot.child("first_year").child("first_sem").child("subject02").child("units").getValue().toString();
                units3 = snapshot.child("first_year").child("first_sem").child("subject03").child("units").getValue().toString();
                units4 = snapshot.child("first_year").child("first_sem").child("subject04").child("units").getValue().toString();
                units5 = snapshot.child("first_year").child("first_sem").child("subject05").child("units").getValue().toString();
                units6 = snapshot.child("first_year").child("first_sem").child("subject06").child("units").getValue().toString();
                units7 = snapshot.child("first_year").child("first_sem").child("subject07").child("units").getValue().toString();
                units8 = snapshot.child("first_year").child("first_sem").child("subject08").child("units").getValue().toString();
                units9 = snapshot.child("first_year").child("first_sem").child("subject09").child("units").getValue().toString();
                units10 = snapshot.child("first_year").child("first_sem").child("subject10").child("units").getValue().toString();

                units1_12 = snapshot.child("first_year").child("second_sem").child("subject01").child("units").getValue().toString();
                units2_12 = snapshot.child("first_year").child("second_sem").child("subject02").child("units").getValue().toString();
                units3_12 = snapshot.child("first_year").child("second_sem").child("subject03").child("units").getValue().toString();
                units4_12 = snapshot.child("first_year").child("second_sem").child("subject04").child("units").getValue().toString();
                units5_12 = snapshot.child("first_year").child("second_sem").child("subject05").child("units").getValue().toString();
                units6_12 = snapshot.child("first_year").child("second_sem").child("subject06").child("units").getValue().toString();
                units7_12 = snapshot.child("first_year").child("second_sem").child("subject07").child("units").getValue().toString();
                units8_12 = snapshot.child("first_year").child("second_sem").child("subject08").child("units").getValue().toString();
                units9_12 = snapshot.child("first_year").child("second_sem").child("subject09").child("units").getValue().toString();
                units10_12 = snapshot.child("first_year").child("second_sem").child("subject10").child("units").getValue().toString();

                units1_21 = snapshot.child("second_year").child("first_sem").child("subject01").child("units").getValue().toString();
                units2_21 = snapshot.child("second_year").child("first_sem").child("subject02").child("units").getValue().toString();
                units3_21 = snapshot.child("second_year").child("first_sem").child("subject03").child("units").getValue().toString();
                units4_21 = snapshot.child("second_year").child("first_sem").child("subject04").child("units").getValue().toString();
                units5_21 = snapshot.child("second_year").child("first_sem").child("subject05").child("units").getValue().toString();
                units6_21 = snapshot.child("second_year").child("first_sem").child("subject06").child("units").getValue().toString();
                units7_21 = snapshot.child("second_year").child("first_sem").child("subject07").child("units").getValue().toString();
                units8_21 = snapshot.child("second_year").child("first_sem").child("subject08").child("units").getValue().toString();
                units9_21 = snapshot.child("second_year").child("first_sem").child("subject09").child("units").getValue().toString();
                units10_21 = snapshot.child("second_year").child("first_sem").child("subject10").child("units").getValue().toString();

                units1_22 = snapshot.child("second_year").child("second_sem").child("subject01").child("units").getValue().toString();
                units2_22 = snapshot.child("second_year").child("second_sem").child("subject02").child("units").getValue().toString();
                units3_22 = snapshot.child("second_year").child("second_sem").child("subject03").child("units").getValue().toString();
                units4_22 = snapshot.child("second_year").child("second_sem").child("subject04").child("units").getValue().toString();
                units5_22 = snapshot.child("second_year").child("second_sem").child("subject05").child("units").getValue().toString();
                units6_22 = snapshot.child("second_year").child("second_sem").child("subject06").child("units").getValue().toString();
                units7_22 = snapshot.child("second_year").child("second_sem").child("subject07").child("units").getValue().toString();
                units8_22 = snapshot.child("second_year").child("second_sem").child("subject08").child("units").getValue().toString();
                units9_22 = snapshot.child("second_year").child("second_sem").child("subject09").child("units").getValue().toString();
                units10_22 = snapshot.child("second_year").child("second_sem").child("subject10").child("units").getValue().toString();

                units1_31 = snapshot.child("third_year").child("first_sem").child("subject01").child("units").getValue().toString();
                units2_31 = snapshot.child("third_year").child("first_sem").child("subject02").child("units").getValue().toString();
                units3_31 = snapshot.child("third_year").child("first_sem").child("subject03").child("units").getValue().toString();
                units4_31 = snapshot.child("third_year").child("first_sem").child("subject04").child("units").getValue().toString();
                units5_31 = snapshot.child("third_year").child("first_sem").child("subject05").child("units").getValue().toString();
                units6_31 = snapshot.child("third_year").child("first_sem").child("subject06").child("units").getValue().toString();
                units7_31 = snapshot.child("third_year").child("first_sem").child("subject07").child("units").getValue().toString();
                units8_31 = snapshot.child("third_year").child("first_sem").child("subject08").child("units").getValue().toString();
                units9_31 = snapshot.child("third_year").child("first_sem").child("subject09").child("units").getValue().toString();
                units10_31 = snapshot.child("third_year").child("first_sem").child("subject10").child("units").getValue().toString();


                units1_32 = snapshot.child("third_year").child("second_sem").child("subject01").child("units").getValue().toString();
                units2_32 = snapshot.child("third_year").child("second_sem").child("subject02").child("units").getValue().toString();
                units3_32 = snapshot.child("third_year").child("second_sem").child("subject03").child("units").getValue().toString();
                units4_32 = snapshot.child("third_year").child("second_sem").child("subject04").child("units").getValue().toString();
                units5_32 = snapshot.child("third_year").child("second_sem").child("subject05").child("units").getValue().toString();
                units6_32 = snapshot.child("third_year").child("second_sem").child("subject06").child("units").getValue().toString();
                units7_32 = snapshot.child("third_year").child("second_sem").child("subject07").child("units").getValue().toString();
                units8_32 = snapshot.child("third_year").child("second_sem").child("subject08").child("units").getValue().toString();
                units9_32 = snapshot.child("third_year").child("second_sem").child("subject09").child("units").getValue().toString();
                units10_32 = snapshot.child("third_year").child("second_sem").child("subjec10").child("units").getValue().toString();

                units1_41 = snapshot.child("fourth_year").child("first_sem").child("subject01").child("units").getValue().toString();
                units2_41 = snapshot.child("fourth_year").child("first_sem").child("subject02").child("units").getValue().toString();
                units3_41 = snapshot.child("fourth_year").child("first_sem").child("subject03").child("units").getValue().toString();
                units4_41 = snapshot.child("fourth_year").child("first_sem").child("subject04").child("units").getValue().toString();
                units5_41 = snapshot.child("fourth_year").child("first_sem").child("subject05").child("units").getValue().toString();
                units6_41 = snapshot.child("fourth_year").child("first_sem").child("subject06").child("units").getValue().toString();
                units7_41 = snapshot.child("fourth_year").child("first_sem").child("subject07").child("units").getValue().toString();
                units8_41 = snapshot.child("fourth_year").child("first_sem").child("subject08").child("units").getValue().toString();
                units9_41 = snapshot.child("fourth_year").child("first_sem").child("subject09").child("units").getValue().toString();
                units10_41 = snapshot.child("fourth_year").child("first_sem").child("subject10").child("units").getValue().toString();


                units1_42 = snapshot.child("fourth_year").child("second_sem").child("subject01").child("units").getValue().toString();
                units2_42 = snapshot.child("fourth_year").child("second_sem").child("subject02").child("units").getValue().toString();
                units3_42 = snapshot.child("fourth_year").child("second_sem").child("subject03").child("units").getValue().toString();
                units4_42 = snapshot.child("fourth_year").child("second_sem").child("subject04").child("units").getValue().toString();
                units5_42 = snapshot.child("fourth_year").child("second_sem").child("subject05").child("units").getValue().toString();
                units6_42 = snapshot.child("fourth_year").child("second_sem").child("subject06").child("units").getValue().toString();
                units7_42 = snapshot.child("fourth_year").child("second_sem").child("subject07").child("units").getValue().toString();
                units8_42 = snapshot.child("fourth_year").child("second_sem").child("subject08").child("units").getValue().toString();
                units9_42 = snapshot.child("fourth_year").child("second_sem").child("subject09").child("units").getValue().toString();
                units10_42 = snapshot.child("fourth_year").child("second_sem").child("subject10").child("units").getValue().toString();

            }

                @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void abreedgradeview() {
        course_curriculum.child("beed").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                SC1 = snapshot.child("first_year").child("first_sem").child("subject01").child("descriptive_title").getValue().toString();
                SC2 = snapshot.child("first_year").child("first_sem").child("subject02").child("descriptive_title").getValue().toString();
                SC3 = snapshot.child("first_year").child("first_sem").child("subject03").child("descriptive_title").getValue().toString();
                SC4 = snapshot.child("first_year").child("first_sem").child("subject04").child("descriptive_title").getValue().toString();
                SC5 = snapshot.child("first_year").child("first_sem").child("subject05").child("descriptive_title").getValue().toString();
                SC6 = snapshot.child("first_year").child("first_sem").child("subject06").child("descriptive_title").getValue().toString();
                SC7 = snapshot.child("first_year").child("first_sem").child("subject07").child("descriptive_title").getValue().toString();
                SC8 = snapshot.child("first_year").child("first_sem").child("subject08").child("descriptive_title").getValue().toString();
                SC9 = snapshot.child("first_year").child("first_sem").child("subject09").child("descriptive_title").getValue().toString();
                SC10 = snapshot.child("first_year").child("first_sem").child("subject10").child("descriptive_title").getValue().toString();

                SC1_2 = snapshot.child("first_year").child("second_sem").child("subject01").child("code").getValue().toString();
                SC2_2 = snapshot.child("first_year").child("second_sem").child("subject02").child("code").getValue().toString();
                SC3_2 = snapshot.child("first_year").child("second_sem").child("subject03").child("code").getValue().toString();
                SC4_2 = snapshot.child("first_year").child("second_sem").child("subject04").child("code").getValue().toString();
                SC5_2 = snapshot.child("first_year").child("second_sem").child("subject05").child("code").getValue().toString();
                SC6_2 = snapshot.child("first_year").child("second_sem").child("subject06").child("code").getValue().toString();
                SC7_2 = snapshot.child("first_year").child("second_sem").child("subject07").child("code").getValue().toString();
                SC8_2 = snapshot.child("first_year").child("second_sem").child("subject08").child("code").getValue().toString();
                SC9_2 = snapshot.child("first_year").child("second_sem").child("subject09").child("code").getValue().toString();
                SC10_2 = snapshot.child("first_year").child("second_sem").child("subject10").child("code").getValue().toString();


                SC1_21 = snapshot.child("second_year").child("first_sem").child("subject01").child("code").getValue().toString();
                SC2_21 = snapshot.child("second_year").child("first_sem").child("subject03").child("code").getValue().toString();
                SC3_21 = snapshot.child("second_year").child("first_sem").child("subject04").child("code").getValue().toString();
                SC4_21 = snapshot.child("second_year").child("first_sem").child("subject05").child("descriptive_title").getValue().toString();
                SC5_21 = snapshot.child("second_year").child("first_sem").child("subject06").child("descriptive_title").getValue().toString();
                SC6_21 = snapshot.child("second_year").child("first_sem").child("subject07").child("descriptive_title").getValue().toString();
                SC7_21 = snapshot.child("second_year").child("first_sem").child("subject08").child("descriptive_title").getValue().toString();
                SC8_21 = snapshot.child("second_year").child("first_sem").child("subject09").child("descriptive_title").getValue().toString();
                SC10_21 = snapshot.child("second_year").child("first_sem").child("subject10").child("descriptive_title").getValue().toString();


                SC1_22 = snapshot.child("third_year").child("second_sem").child("subject01").child("descriptive_title").getValue().toString();
                SC2_22 = snapshot.child("third_year").child("second_sem").child("subject02").child("descriptive_title").getValue().toString();
                SC3_22 = snapshot.child("third_year").child("second_sem").child("subject03").child("descriptive_title").getValue().toString();
                SC4_22 = snapshot.child("third_year").child("second_sem").child("subject04").child("descriptive_title").getValue().toString();
                SC5_22 = snapshot.child("third_year").child("second_sem").child("subject05").child("descriptive_title").getValue().toString();
                SC6_22 = snapshot.child("third_year").child("second_sem").child("subject06").child("descriptive_title").getValue().toString();
                SC7_22 = snapshot.child("third_year").child("second_sem").child("subject07").child("descriptive_title").getValue().toString();
                SC8_22 = snapshot.child("third_year").child("second_sem").child("subject08").child("descriptive_title").getValue().toString();
                SC9_22 = snapshot.child("third_year").child("second_sem").child("subject09").child("descriptive_title").getValue().toString();
                SC10_22 = snapshot.child("third_year").child("second_sem").child("subject10").child("descriptive_title").getValue().toString();


                SC1_31 = snapshot.child("third_year").child("first_sem").child("subject01").child("code").getValue().toString();
                SC2_31 = snapshot.child("third_year").child("first_sem").child("subject02").child("code").getValue().toString();
                SC3_31 = snapshot.child("third_year").child("first_sem").child("subject03").child("code").getValue().toString();
                SC4_31 = snapshot.child("third_year").child("first_sem").child("subject04").child("code").getValue().toString();
                SC5_31 = snapshot.child("third_year").child("first_sem").child("subject05").child("code").getValue().toString();
                SC6_31 = snapshot.child("third_year").child("first_sem").child("subject06").child("code").getValue().toString();
                SC7_31 = snapshot.child("third_year").child("first_sem").child("subject07").child("code").getValue().toString();
                SC8_31 = snapshot.child("third_year").child("first_sem").child("subject08").child("code").getValue().toString();
                SC9_31 = snapshot.child("third_year").child("first_sem").child("subject09").child("code").getValue().toString();
                SC10_31 = snapshot.child("third_year").child("first_sem").child("subject10").child("code").getValue().toString();

                SC1_32 = snapshot.child("third_year").child("second_sem").child("subject01").child("code").getValue().toString();
                SC2_32 = snapshot.child("third_year").child("second_sem").child("subject02").child("code").getValue().toString();
                SC3_32 = snapshot.child("third_year").child("second_sem").child("subject03").child("code").getValue().toString();
                SC4_32 = snapshot.child("third_year").child("second_sem").child("subject04").child("code").getValue().toString();
                SC5_32 = snapshot.child("third_year").child("second_sem").child("subject05").child("code").getValue().toString();
                SC6_32 = snapshot.child("third_year").child("second_sem").child("subject06").child("code").getValue().toString();
                SC7_32 = snapshot.child("third_year").child("second_sem").child("subject07").child("code").getValue().toString();
                SC8_32 = snapshot.child("third_year").child("second_sem").child("subject08").child("code").getValue().toString();
                SC9_32 = snapshot.child("third_year").child("second_sem").child("subject09").child("code").getValue().toString();
                SC10_32 = snapshot.child("third_year").child("second_sem").child("subject010").child("code").getValue().toString();


                SC1_41 = snapshot.child("fourth_year").child("first_sem").child("subject01").child("code").getValue().toString();
                SC2_41 = snapshot.child("fourth_year").child("first_sem").child("subject02").child("code").getValue().toString();
                SC3_41 = snapshot.child("fourth_year").child("first_sem").child("subject03").child("code").getValue().toString();
                SC4_41 = snapshot.child("fourth_year").child("first_sem").child("subject04").child("code").getValue().toString();
                SC5_41 = snapshot.child("fourth_year").child("first_sem").child("subject05").child("code").getValue().toString();
                SC6_41 = snapshot.child("fourth_year").child("first_sem").child("subject06").child("code").getValue().toString();
                // SC7_41 = snapshot.child("fourth_year").child("first_sem").child("subject07").child("code").getValue().toString();
                SC8_41 = snapshot.child("fourth_year").child("first_sem").child("subject08").child("code").getValue().toString();
                SC9_41 = snapshot.child("fourth_year").child("first_sem").child("subject09").child("code").getValue().toString();
                SC10_41 = snapshot.child("fourth_year").child("first_sem").child("subject010").child("code").getValue().toString();


                SC1_42 = snapshot.child("fourth_year").child("second_sem").child("subject01").child("code").getValue().toString();
                SC3_42 = snapshot.child("fourth_year").child("second_sem").child("subject02").child("code").getValue().toString();
                SC4_42 = snapshot.child("fourth_year").child("second_sem").child("subject03").child("code").getValue().toString();
                SC5_42 = snapshot.child("fourth_year").child("second_sem").child("subject04").child("code").getValue().toString();
                SC6_42 = snapshot.child("fourth_year").child("second_sem").child("subject05").child("code").getValue().toString();
                // SC7_42 = snapshot.child("fourth_year").child("second_sem").child("subject06").child("code").getValue().toString();
                SC8_42 = snapshot.child("fourth_year").child("second_sem").child("subject07").child("code").getValue().toString();
                SC9_42 = snapshot.child("fourth_year").child("second_sem").child("subject08").child("code").getValue().toString();
                SC10_42 = snapshot.child("fourth_year").child("second_sem").child("subject0").child("code").getValue().toString();
                SC2_42 = snapshot.child("fourth_year").child("second_sem").child("subject10").child("code").getValue().toString();


                des1 = snapshot.child("first_year").child("first_sem").child("subject01").child("descriptive_title").getValue().toString().trim();
                des2 = snapshot.child("first_year").child("first_sem").child("subject02").child("descriptive_title").getValue().toString();
                des3 = snapshot.child("first_year").child("first_sem").child("subject03").child("descriptive_title").getValue().toString();
                des4 = snapshot.child("first_year").child("first_sem").child("subject04").child("descriptive_title").getValue().toString();
                des5 = snapshot.child("first_year").child("first_sem").child("subject05").child("descriptive_title").getValue().toString();
                des7 = snapshot.child("first_year").child("first_sem").child("subject06").child("descriptive_title").getValue().toString();
                des8 = snapshot.child("first_year").child("first_sem").child("subject07").child("descriptive_title").getValue().toString();
                des9 = snapshot.child("first_year").child("first_sem").child("subject08").child("descriptive_title").getValue().toString();
                des6 = snapshot.child("first_year").child("first_sem").child("subject09").child("descriptive_title").getValue().toString();
                des10 = snapshot.child("first_year").child("first_sem").child("subject10").child("descriptive_title").getValue().toString();

                des1_2 = snapshot.child("first_year").child("second_sem").child("subject01").child("descriptive_title").getValue().toString();
                des2_2 = snapshot.child("first_year").child("second_sem").child("subject02").child("descriptive_title").getValue().toString();
                des3_2 = snapshot.child("first_year").child("second_sem").child("subject03").child("descriptive_title").getValue().toString();
                des4_2 = snapshot.child("first_year").child("second_sem").child("subject04").child("descriptive_title").getValue().toString();
                des5_2 = snapshot.child("first_year").child("second_sem").child("subject05").child("descriptive_title").getValue().toString();
                des6_2 = snapshot.child("first_year").child("second_sem").child("subject06").child("descriptive_title").getValue().toString();
                des7_2 = snapshot.child("first_year").child("second_sem").child("subject07").child("descriptive_title").getValue().toString();
                des8_2 = snapshot.child("first_year").child("second_sem").child("subject08").child("descriptive_title").getValue().toString();
                des9_2 = snapshot.child("first_year").child("second_sem").child("subject09").child("descriptive_title").getValue().toString();
                des10_2 = snapshot.child("first_year").child("second_sem").child("subject10").child("descriptive_title").getValue().toString();

                des1_21 = snapshot.child("second_year").child("first_sem").child("subject01").child("descriptive_title").getValue().toString();
                des2_21 = snapshot.child("second_year").child("first_sem").child("subject02").child("descriptive_title").getValue().toString();
                des3_21 = snapshot.child("second_year").child("first_sem").child("subject03").child("descriptive_title").getValue().toString();
                des4_21 = snapshot.child("second_year").child("first_sem").child("subject04").child("descriptive_title").getValue().toString();
                des5_21 = snapshot.child("second_year").child("first_sem").child("subject05").child("descriptive_title").getValue().toString();
                des6_21 = snapshot.child("second_year").child("first_sem").child("subject06").child("descriptive_title").getValue().toString();
                des7_21 = snapshot.child("second_year").child("first_sem").child("subject07").child("descriptive_title").getValue().toString();
                des8_21 = snapshot.child("second_year").child("first_sem").child("subject08").child("descriptive_title").getValue().toString();
                des9_21 = snapshot.child("second_year").child("first_sem").child("subject09").child("descriptive_title").getValue().toString();
                des10_21 = snapshot.child("second_year").child("first_sem").child("subject10").child("descriptive_title").getValue().toString();

                des1_22 = snapshot.child("third_year").child("second_sem").child("subject01").child("descriptive_title").getValue().toString();
                des2_22 = snapshot.child("third_year").child("second_sem").child("subject02").child("descriptive_title").getValue().toString();
                des3_22 = snapshot.child("third_year").child("second_sem").child("subject03").child("descriptive_title").getValue().toString();
                des4_22 = snapshot.child("third_year").child("second_sem").child("subject04").child("descriptive_title").getValue().toString();
                des5_22 = snapshot.child("third_year").child("second_sem").child("subject05").child("descriptive_title").getValue().toString();
                des6_22 = snapshot.child("third_year").child("second_sem").child("subject06").child("descriptive_title").getValue().toString();
                des7_22 = snapshot.child("third_year").child("second_sem").child("subject07").child("descriptive_title").getValue().toString();
                des8_22 = snapshot.child("third_year").child("second_sem").child("subject08").child("descriptive_title").getValue().toString();
                des9_22 = snapshot.child("third_year").child("second_sem").child("subject09").child("descriptive_title").getValue().toString();
                des10_22 = snapshot.child("third_year").child("second_sem").child("subject10").child("descriptive_title").getValue().toString();

                des1_31 = snapshot.child("third_year").child("first_sem").child("subject01").child("descriptive_title").getValue().toString();
                des2_31 = snapshot.child("third_year").child("first_sem").child("subject02").child("descriptive_title").getValue().toString();
                des3_31 = snapshot.child("third_year").child("first_sem").child("subject03").child("descriptive_title").getValue().toString();
                des4_31 = snapshot.child("third_year").child("first_sem").child("subject04").child("descriptive_title").getValue().toString();
                des5_31 = snapshot.child("third_year").child("first_sem").child("subject05").child("descriptive_title").getValue().toString();
                des6_31 = snapshot.child("third_year").child("first_sem").child("subject06").child("descriptive_title").getValue().toString();
                des7_31 = snapshot.child("third_year").child("first_sem").child("subject07").child("descriptive_title").getValue().toString();
                des8_31 = snapshot.child("third_year").child("first_sem").child("subject08").child("descriptive_title").getValue().toString();
                des9_31 = snapshot.child("third_year").child("first_sem").child("subject09").child("descriptive_title").getValue().toString();
                des10_31 = snapshot.child("third_year").child("first_sem").child("subject10").child("descriptive_title").getValue().toString();

                des1_32 = snapshot.child("third_year").child("second_sem").child("subject01").child("descriptive_title").getValue().toString();
                des2_32 = snapshot.child("third_year").child("second_sem").child("subject02").child("descriptive_title").getValue().toString();
                des3_32 = snapshot.child("third_year").child("second_sem").child("subject03").child("descriptive_title").getValue().toString();
                des4_32 = snapshot.child("third_year").child("second_sem").child("subject04").child("descriptive_title").getValue().toString();
                des5_32 = snapshot.child("third_year").child("second_sem").child("subject05").child("descriptive_title").getValue().toString();
                des6_32 = snapshot.child("third_year").child("second_sem").child("subject06").child("descriptive_title").getValue().toString();
                des1_32 = snapshot.child("third_year").child("second_sem").child("subject07").child("descriptive_title").getValue().toString();
                des1_32 = snapshot.child("third_year").child("second_sem").child("subject08").child("descriptive_title").getValue().toString();
                des1_32 = snapshot.child("third_year").child("second_sem").child("subject09").child("descriptive_title").getValue().toString();
                des1_32 = snapshot.child("third_year").child("second_sem").child("subject10").child("descriptive_title").getValue().toString();

                des1_41 = snapshot.child("fourth_year").child("first_sem").child("subject01").child("descriptive_title").getValue().toString();
                des2_41 = snapshot.child("fourth_year").child("first_sem").child("subject02").child("descriptive_title").getValue().toString();
                des3_41 = snapshot.child("fourth_year").child("first_sem").child("subject03").child("descriptive_title").getValue().toString();
                des4_41 = snapshot.child("fourth_year").child("first_sem").child("subject04").child("descriptive_title").getValue().toString();
                des5_41 = snapshot.child("fourth_year").child("first_sem").child("subject05").child("descriptive_title").getValue().toString();
                des6_41 = snapshot.child("fourth_year").child("first_sem").child("subject06").child("descriptive_title").getValue().toString();
                des7_41 = snapshot.child("fourth_year").child("first_sem").child("subject07").child("descriptive_title").getValue().toString();
                des8_41 = snapshot.child("fourth_year").child("first_sem").child("subject08").child("descriptive_title").getValue().toString();
                des9_41 = snapshot.child("fourth_year").child("first_sem").child("subject09").child("descriptive_title").getValue().toString();
                des10_41 = snapshot.child("fourth_year").child("first_sem").child("subject10").child("descriptive_title").getValue().toString();


                des1_42 = snapshot.child("fourth_year").child("second_sem").child("subject01").child("descriptive_title").getValue().toString();
                des2_42 = snapshot.child("fourth_year").child("second_sem").child("subject02").child("descriptive_title").getValue().toString();
                des3_42 = snapshot.child("fourth_year").child("second_sem").child("subject03").child("descriptive_title").getValue().toString();
                des4_42 = snapshot.child("fourth_year").child("second_sem").child("subject04").child("descriptive_title").getValue().toString();
                des5_42 = snapshot.child("fourth_year").child("second_sem").child("subject05").child("descriptive_title").getValue().toString();
                des6_42 = snapshot.child("fourth_year").child("second_sem").child("subject06").child("descriptive_title").getValue().toString();
                des7_42 = snapshot.child("fourth_year").child("second_sem").child("subject07").child("descriptive_title").getValue().toString();
                des8_42 = snapshot.child("fourth_year").child("second_sem").child("subject08").child("descriptive_title").getValue().toString();
                des9_42 = snapshot.child("fourth_year").child("second_sem").child("subject09").child("descriptive_title").getValue().toString();
                des10_42 = snapshot.child("fourth_year").child("second_sem").child("subject10").child("descriptive_title").getValue().toString();

                units1 = snapshot.child("first_year").child("first_sem").child("subject01").child("units").getValue().toString();
                units2 = snapshot.child("first_year").child("first_sem").child("subject02").child("units").getValue().toString();
                units3 = snapshot.child("first_year").child("first_sem").child("subject03").child("units").getValue().toString();
                units4 = snapshot.child("first_year").child("first_sem").child("subject04").child("units").getValue().toString();
                units5 = snapshot.child("first_year").child("first_sem").child("subject05").child("units").getValue().toString();
                units6 = snapshot.child("first_year").child("first_sem").child("subject06").child("units").getValue().toString();
                units7 = snapshot.child("first_year").child("first_sem").child("subject07").child("units").getValue().toString();
                units8 = snapshot.child("first_year").child("first_sem").child("subject08").child("units").getValue().toString();
                units9 = snapshot.child("first_year").child("first_sem").child("subject09").child("units").getValue().toString();
                units10 = snapshot.child("first_year").child("first_sem").child("subject10").child("units").getValue().toString();

                units1_12 = snapshot.child("first_year").child("second_sem").child("subject01").child("units").getValue().toString();
                units2_12 = snapshot.child("first_year").child("second_sem").child("subject02").child("units").getValue().toString();
                units3_12 = snapshot.child("first_year").child("second_sem").child("subject03").child("units").getValue().toString();
                units4_12 = snapshot.child("first_year").child("second_sem").child("subject04").child("units").getValue().toString();
                units5_12 = snapshot.child("first_year").child("second_sem").child("subject05").child("units").getValue().toString();
                units6_12 = snapshot.child("first_year").child("second_sem").child("subject06").child("units").getValue().toString();
                units7_12 = snapshot.child("first_year").child("second_sem").child("subject07").child("units").getValue().toString();
                units8_12 = snapshot.child("first_year").child("second_sem").child("subject08").child("units").getValue().toString();
                units9_12 = snapshot.child("first_year").child("second_sem").child("subject09").child("units").getValue().toString();
                units10_12 = snapshot.child("first_year").child("second_sem").child("subject10").child("units").getValue().toString();

                units1_21 = snapshot.child("second_year").child("first_sem").child("subject01").child("units").getValue().toString();
                units2_21 = snapshot.child("second_year").child("first_sem").child("subject02").child("units").getValue().toString();
                units3_21 = snapshot.child("second_year").child("first_sem").child("subject03").child("units").getValue().toString();
                units4_21 = snapshot.child("second_year").child("first_sem").child("subject04").child("units").getValue().toString();
                units5_21 = snapshot.child("second_year").child("first_sem").child("subject05").child("units").getValue().toString();
                units6_21 = snapshot.child("second_year").child("first_sem").child("subject06").child("units").getValue().toString();
                units7_21 = snapshot.child("second_year").child("first_sem").child("subject07").child("units").getValue().toString();
                units8_21 = snapshot.child("second_year").child("first_sem").child("subject08").child("units").getValue().toString();
                units9_21 = snapshot.child("second_year").child("first_sem").child("subject09").child("units").getValue().toString();
                units10_21 = snapshot.child("second_year").child("first_sem").child("subject10").child("units").getValue().toString();

                units1_22 = snapshot.child("second_year").child("second_sem").child("subject01").child("units").getValue().toString();
                units2_22 = snapshot.child("second_year").child("second_sem").child("subject02").child("units").getValue().toString();
                units3_22 = snapshot.child("second_year").child("second_sem").child("subject03").child("units").getValue().toString();
                units4_22 = snapshot.child("second_year").child("second_sem").child("subject04").child("units").getValue().toString();
                units5_22 = snapshot.child("second_year").child("second_sem").child("subject05").child("units").getValue().toString();
                units6_22 = snapshot.child("second_year").child("second_sem").child("subject06").child("units").getValue().toString();
                units7_22 = snapshot.child("second_year").child("second_sem").child("subject07").child("units").getValue().toString();
                units8_22 = snapshot.child("second_year").child("second_sem").child("subject08").child("units").getValue().toString();
                units9_22 = snapshot.child("second_year").child("second_sem").child("subject09").child("units").getValue().toString();
                units10_22 = snapshot.child("second_year").child("second_sem").child("subject10").child("units").getValue().toString();

                units1_31 = snapshot.child("third_year").child("first_sem").child("subject01").child("units").getValue().toString();
                units2_31 = snapshot.child("third_year").child("first_sem").child("subject02").child("units").getValue().toString();
                units3_31 = snapshot.child("third_year").child("first_sem").child("subject03").child("units").getValue().toString();
                units4_31 = snapshot.child("third_year").child("first_sem").child("subject04").child("units").getValue().toString();
                units5_31 = snapshot.child("third_year").child("first_sem").child("subject05").child("units").getValue().toString();
                units6_31 = snapshot.child("third_year").child("first_sem").child("subject06").child("units").getValue().toString();
                units7_31 = snapshot.child("third_year").child("first_sem").child("subject07").child("units").getValue().toString();
                units8_31 = snapshot.child("third_year").child("first_sem").child("subject08").child("units").getValue().toString();
                units9_31 = snapshot.child("third_year").child("first_sem").child("subject09").child("units").getValue().toString();
                units10_31 = snapshot.child("third_year").child("first_sem").child("subject10").child("units").getValue().toString();


                units1_32 = snapshot.child("third_year").child("second_sem").child("subject01").child("units").getValue().toString();
                units2_32 = snapshot.child("third_year").child("second_sem").child("subject02").child("units").getValue().toString();
                units3_32 = snapshot.child("third_year").child("second_sem").child("subject03").child("units").getValue().toString();
                units4_32 = snapshot.child("third_year").child("second_sem").child("subject04").child("units").getValue().toString();
                units5_32 = snapshot.child("third_year").child("second_sem").child("subject05").child("units").getValue().toString();
                units6_32 = snapshot.child("third_year").child("second_sem").child("subject06").child("units").getValue().toString();
                units7_32 = snapshot.child("third_year").child("second_sem").child("subject07").child("units").getValue().toString();
                units8_32 = snapshot.child("third_year").child("second_sem").child("subject08").child("units").getValue().toString();
                units9_32 = snapshot.child("third_year").child("second_sem").child("subject09").child("units").getValue().toString();
                units10_32 = snapshot.child("third_year").child("second_sem").child("subjec10").child("units").getValue().toString();

                units1_41 = snapshot.child("fourth_year").child("first_sem").child("subject01").child("units").getValue().toString();
                units2_41 = snapshot.child("fourth_year").child("first_sem").child("subject02").child("units").getValue().toString();
                units3_41 = snapshot.child("fourth_year").child("first_sem").child("subject03").child("units").getValue().toString();
                units4_41 = snapshot.child("fourth_year").child("first_sem").child("subject04").child("units").getValue().toString();
                units5_41 = snapshot.child("fourth_year").child("first_sem").child("subject05").child("units").getValue().toString();
                units6_41 = snapshot.child("fourth_year").child("first_sem").child("subject06").child("units").getValue().toString();
                units7_41 = snapshot.child("fourth_year").child("first_sem").child("subject07").child("units").getValue().toString();
                units8_41 = snapshot.child("fourth_year").child("first_sem").child("subject08").child("units").getValue().toString();
                units9_41 = snapshot.child("fourth_year").child("first_sem").child("subject09").child("units").getValue().toString();
                units10_41 = snapshot.child("fourth_year").child("first_sem").child("subject10").child("units").getValue().toString();


                units1_42 = snapshot.child("fourth_year").child("second_sem").child("subject01").child("units").getValue().toString();
                units2_42 = snapshot.child("fourth_year").child("second_sem").child("subject02").child("units").getValue().toString();
                units3_42 = snapshot.child("fourth_year").child("second_sem").child("subject03").child("units").getValue().toString();
                units4_42 = snapshot.child("fourth_year").child("second_sem").child("subject04").child("units").getValue().toString();
                units5_42 = snapshot.child("fourth_year").child("second_sem").child("subject05").child("units").getValue().toString();
                units6_42 = snapshot.child("fourth_year").child("second_sem").child("subject06").child("units").getValue().toString();
                units7_42 = snapshot.child("fourth_year").child("second_sem").child("subject07").child("units").getValue().toString();
                units8_42 = snapshot.child("fourth_year").child("second_sem").child("subject08").child("units").getValue().toString();
                units9_42 = snapshot.child("fourth_year").child("second_sem").child("subject09").child("units").getValue().toString();
                units10_42 = snapshot.child("fourth_year").child("second_sem").child("subject10").child("units").getValue().toString();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}




