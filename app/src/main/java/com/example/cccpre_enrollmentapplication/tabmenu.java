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

public class tabmenu extends AppCompatActivity {
    private Button profile;
    private Button about, logout;
    private TextView studentNumber;
    private FirebaseUser user;
    private FirebaseAuth mAuth;
    private String  userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabmenu);
        profile=findViewById(R.id.studentprofile);
        about=findViewById(R.id.about);
        logout=findViewById(R.id.logout);


        //



        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(tabmenu.this,profile.class);
                startActivity(intent);
            }
        });


        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(tabmenu.this,About.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent=new Intent(tabmenu.this, login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

            }
        });

        mAuth=FirebaseAuth.getInstance();

        TextView fullName=(TextView) findViewById(R.id.fullname);
        TextView Course =(TextView) findViewById(R.id.course);
        studentNumber=(TextView)findViewById(R.id.studentnumber);

        user=FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference databaseRef= FirebaseDatabase.getInstance().getReference("User");
        userID=user.getUid();
        databaseRef.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String name =snapshot.child("Name").getValue().toString();
                String course =snapshot.child("Course").getValue().toString();
                String studentnumber =snapshot.child("Student_number").getValue().toString();

                fullName.setText(name);
                Course.setText(course);
                studentNumber.setText(studentnumber)
                ;



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(tabmenu.this, "Something wrong happened!", Toast.LENGTH_SHORT).show();

            }
        });


    }
}