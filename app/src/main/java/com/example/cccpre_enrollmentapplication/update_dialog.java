package com.example.cccpre_enrollmentapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class update_dialog extends AppCompatActivity {

    private Button profile;
    private TextView studentNumber;
    private ImageView profilepic;
    private FirebaseUser user;
    private FirebaseAuth mAuth;
    private String  userID;
    private FirebaseAuth authProfile;
    private StorageReference storageReference;
    private FirebaseUser firebaseUser;
    private Uri uriImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.header);
        profile=findViewById(R.id.studentprofile);
        authProfile = FirebaseAuth.getInstance();
        firebaseUser = authProfile.getCurrentUser();
        profilepic=findViewById(R.id.profilepic);
        storageReference = FirebaseStorage.getInstance().getReference("DisplayPic");
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
                studentNumber.setText(studentnumber);

                Uri uri=firebaseUser.getPhotoUrl();
                Picasso.with(update_dialog.this).load(uri).into(profilepic);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(update_dialog.this, "Something wrong happened!", Toast.LENGTH_SHORT).show();

            }
        });


    }




    }
