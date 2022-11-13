package com.example.cccpre_enrollmentapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Home_Page extends AppCompatActivity {
    private  ImageButton pre_enrollment;
    private ImageButton Evaluation;
    private ImageButton menu_page;
    private TextView status;

    private String userID;
    private DatabaseReference databaseRef;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_homepage);
        pre_enrollment=findViewById(R.id.preenroll);
        Evaluation = findViewById(R.id.evaluate);
        status=findViewById(R.id.status);
        databaseRef = FirebaseDatabase.getInstance().getReference("pre_enrollment");


        databaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String Status=snapshot.child("status").getValue().toString();
                status.setText(Status);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }

        });

        pre_enrollment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Status_preenroll();
            }
        });

        Evaluation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Home_Page.this,evaluationform.class);
                startActivity(intent);
            }
        });

        menu_page=findViewById(R.id.menuicon);
        menu_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Home_Page.this,tabmenu.class);
                startActivity(intent);
            }
        });




    }
    private void Status_preenroll(){

        if(status.getText().equals("CLOSE")){
            Toast.makeText(this, "The Pre-Enrollment is Closed for this semester", Toast.LENGTH_SHORT).show();

        } else{
            Intent intent=new Intent(Home_Page.this,Pre_Enrollment.class);
            startActivity(intent);

        }
    }
}