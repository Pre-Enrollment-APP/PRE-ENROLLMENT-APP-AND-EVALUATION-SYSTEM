package com.example.cccpre_enrollmentapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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
    private ImageButton back;

    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpassword);

        emailEditText= findViewById(R.id.enteremail);
        resetPassword=findViewById(R.id.send);
        back=findViewById(R.id.back);
        progressBar=findViewById(R.id.progressbar);
        auth=FirebaseAuth.getInstance();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(resetpassword.this, login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

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
               showAlertDialog();
           }
       }).addOnFailureListener(new OnFailureListener() {
           @Override
           public void onFailure(@NonNull Exception e) {
               Toast.makeText(resetpassword.this, "something wrong"+e.getMessage(), Toast.LENGTH_SHORT).show();
               progressBar.setVisibility(View.GONE);
           }
       });
    }
    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(resetpassword.this);
        builder.setTitle("Reset Password");
        builder.setMessage("Please reset your password now.");

        builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_APP_EMAIL);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                AlertDialog alert = builder.create();
                alert.dismiss();
                progressBar.setVisibility(View.GONE);
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}

