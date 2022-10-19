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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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
        String Email=emailEditText.getText().toString().trim();
        if (Email.isEmpty()) {
            emailEditText.setError("Email is required");
            emailEditText.requestFocus();

        }
       if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
           emailEditText.setError("please provide valid email");
           emailEditText.requestFocus();
           return;
       }
       progressBar.setVisibility(View.VISIBLE);
       auth.sendPasswordResetEmail(Email).addOnSuccessListener(new OnSuccessListener<Void>() {
           @Override
           public void onSuccess(Void unused) {
               Toast.makeText(resetpassword.this, "Reset link sent to your email", Toast.LENGTH_SHORT).show();
               progressBar.setVisibility(View.GONE);
           }
       }).addOnFailureListener(new OnFailureListener() {
           @Override
           public void onFailure(@NonNull Exception e) {
               Toast.makeText(resetpassword.this, "something wrong"+e.getMessage(), Toast.LENGTH_SHORT).show();
               progressBar.setVisibility(View.GONE);
           }
       });
    }


}

