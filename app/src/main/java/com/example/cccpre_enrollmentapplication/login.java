package com.example.cccpre_enrollmentapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.logging.Logger;

public class login extends AppCompatActivity implements View.OnClickListener  {
private EditText emailadd, pass;
private Button register,logIn;
private FirebaseAuth mAuth;
private  ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logine_page);

        register=(Button) findViewById(R.id.createaccount);
        register.setOnClickListener(this);
        logIn=(Button) findViewById(R.id.logIn);
        logIn.setOnClickListener(this);
        emailadd=(EditText) findViewById(R.id.email);
        progressBar=(ProgressBar) findViewById(R.id.progressbar);
        pass=(EditText) findViewById(R.id.password);
        mAuth=FirebaseAuth.getInstance();


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.createaccount:
                startActivity(new Intent(this,registerUser.class));
                break;
            case R.id.logIn:
                userLogin();
                break;
        }
    }
    private void userLogin(){
        String Email=emailadd.getText().toString().trim();
        String Password=pass.getText().toString().trim();

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
    mAuth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            if (task.isSuccessful()){
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if(user.isEmailVerified()){
                    startActivity(new Intent(login.this,Home_Page.class));
                }else
                    user.sendEmailVerification();
                Toast.makeText(login.this, "Check your email to verify your account", Toast.LENGTH_SHORT).show();
                }

            else
            {
                Toast.makeText(login.this,"Failed to login! Please check your credentials", Toast.LENGTH_LONG).show();
            }

        }

    });
    }
}