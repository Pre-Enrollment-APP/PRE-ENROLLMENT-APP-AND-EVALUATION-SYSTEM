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
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class registerUser extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private FirebaseAuth mAuth;
    private EditText name, pass,studentnumber, emailadd,add,fnumber,mnumber,fname,mname,num;
    private ProgressBar progressbar;
    private Button registeruser,date;
    private ImageButton Back;
    private static final String TAG="registerUser";
    private Button DateButton;
    private DatePickerDialog datePickerDialog;
    private Spinner course;
    private ImageView profile_pic;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        Toast.makeText(this, "You can register now", Toast.LENGTH_SHORT).show();
        mAuth = FirebaseAuth.getInstance();
        name = findViewById(R.id.fullname);
        pass = findViewById(R.id.password);
        emailadd = findViewById(R.id.email);
        add=findViewById(R.id.address);
        num=findViewById(R.id.number);
        date=findViewById(R.id.date);
        fname=findViewById(R.id.fathername);
        fnumber=findViewById(R.id.fathernumber);
        mname=findViewById(R.id.mothername);
        mnumber=findViewById(R.id.mothernumber);
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
        String Father_number=fnumber.getText().toString().trim();
        String Mother_number=mnumber.getText().toString().trim();
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
        if (Father_number.isEmpty()) {
            num.setError("Contact # is required!");
            num.requestFocus();
            return;

        }
        if (Mother_number.isEmpty()) {
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
        if(Mother_number.length()!=11){
            studentnumber.setError("Invalid Number, It should be 11 digits ");


        }
        if(Father_number.length()!=11){
            studentnumber.setError("Invalid Number, It should be 11 digits");


        }
        if(Contact_Number.length()!=11){
            studentnumber.setError("Invalid Number, It should be 11 digits ");


        }
        progressbar.setVisibility(View.VISIBLE);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(Email, Password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            User user = new User(Name, Email, Student_number, Course, Address, Birthday, Contact_Number, Mother, Mother_number, Father, Father_number);
                            FirebaseDatabase.getInstance().getReference("User")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
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
}
