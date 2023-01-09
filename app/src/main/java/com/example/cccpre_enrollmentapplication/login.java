package com.example.cccpre_enrollmentapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class login extends AppCompatActivity implements View.OnClickListener {
    private EditText emailadd, pass;
    private Button createaccount, logIn, forgetpassword;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private CheckBox checkbox_password;
    private String userID;
    private DatabaseReference databaseRef;
    private FirebaseUser user;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logine_page);


        user = FirebaseAuth.getInstance().getCurrentUser();
        databaseRef = FirebaseDatabase.getInstance().getReference("User");
        //  userID = user.getUid();
        forgetpassword = (Button) findViewById(R.id.forget);
        emailadd = (EditText) findViewById(R.id.email);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        pass = (EditText) findViewById(R.id.password);
        checkbox_password = findViewById(R.id.checkbox_pass);

        createaccount = (Button) findViewById(R.id.createaccount);
        createaccount.setOnClickListener(this);

        logIn = (Button) findViewById(R.id.logIn);
        logIn.setOnClickListener(this);
        emailadd.setSingleLine(true);
        pass.setSingleLine(true);
        forgetpassword = findViewById(R.id.forget);
        forgetpassword.setOnClickListener(this);


        mAuth = FirebaseAuth.getInstance();



        checkbox_password.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                } else {
                    pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.createaccount:
                startActivity(new Intent(this, registerUser.class));

                break;
            case R.id.logIn:
                userLogin();
                break;
            case R.id.forget:
                startActivity(new Intent(this, resetpassword.class));

        }


    }

    private void userLogin() {
        String Email = emailadd.getText().toString().trim();
        String Password = pass.getText().toString().trim();

        if (Email.isEmpty()) {
            emailadd.setError("Email is required!");
            emailadd.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            emailadd.setError("Please provide valid email");
            emailadd.requestFocus();
            return;
        }
        if (Password.isEmpty()) {
            pass.setError("Password is required!");
            pass.requestFocus();
            return;

        }
        if (Password.length() < 6) {
            pass.setError("Min password length should be 6 characters");
            pass.requestFocus();
            return;
        }


        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user.isEmailVerified()) {

                        startActivity(new Intent(login.this, Home_Page.class));

                    } else {
                        user.sendEmailVerification();
                        progressBar.setVisibility(View.GONE);
                        showAlertDialog();
                    }
                } else {
                    Toast.makeText(login.this, "Failed to login! Please check your credentials", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);

                }

            }
        });
    }

    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(login.this);
        builder.setTitle("Email Not Verified");
        builder.setMessage("Please verify your email now.");

        builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_APP_EMAIL);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }




}