package com.example.cccpre_enrollmentapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class resetpassword extends AppCompatActivity {

    private EditText emailEditText;
    private Button resetPassword;
    private ProgressBar progressBar;

    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpassword);

        emailEditText= findViewById(R.id.enteremail);
        resetPassword=findViewById(R.id.send);

        progressBar=findViewById(R.id.progressbar);
        auth=FirebaseAuth.getInstance();

        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetpass();
            }
        });



    }

   private void resetpass(){
        String email=emailEditText.getText().toString().trim();
        if (email.isEmpty()) {
            emailEditText.setError("Email is required");
            emailEditText.requestFocus();

        }
       if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
           emailEditText.setError("please provide valid email");
           return;
       }
       progressBar.setVisibility(View.VISIBLE);
       auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
           @Override
           public void onComplete(@NonNull Task<Void> task) {
               if (task.isSuccessful()){
                   Toast.makeText(resetpassword.this, "Check your email to reset your password", Toast.LENGTH_SHORT).show();
               }
               else{
                   Toast.makeText(resetpassword.this, "Try again, something wrong happened", Toast.LENGTH_SHORT).show();
               }
           }
       });
    }


}

