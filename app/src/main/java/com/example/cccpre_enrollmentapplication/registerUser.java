package com.example.cccpre_enrollmentapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class registerUser extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    private EditText name, pass, emailadd,course;
    private ProgressBar progressbar;
    private Button registeruser;
    private ImageButton Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        mAuth = FirebaseAuth.getInstance();

        name = findViewById(R.id.fullname);
        pass = findViewById(R.id.password);
        emailadd = findViewById(R.id.email);
        course = findViewById(R.id.course);

        registeruser = findViewById(R.id.registeruser);
        registeruser.setOnClickListener(this);

        Back = findViewById(R.id.back);
        Back.setOnClickListener(this);

        progressbar= findViewById(R.id.progressbar);

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
        String Course = course.getText().toString().trim();
        String Name = name.getText().toString().trim();

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

        if (Course.isEmpty()) {
            course.setError("Username is required!");
            course.requestFocus();
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
        }

        progressbar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(Email, Password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user=new User (Name,Course,Email);
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                Toast.makeText(registerUser.this,"User has been registered successfully",Toast.LENGTH_LONG).show();
                                                progressbar.setVisibility(View.GONE);
                                                //redirect to login
                                            } else {
                                                Toast.makeText(registerUser.this,"Failed to register! try again.",Toast.LENGTH_LONG).show();
                                                progressbar.setVisibility(View.GONE);
                                            }

                                        }
                                    });

                        }else {
                            Toast.makeText(registerUser.this,"Failed to register! try again.",Toast.LENGTH_LONG);
                            progressbar.setVisibility(View.GONE);
                        }
                    }
                });
    }

}
