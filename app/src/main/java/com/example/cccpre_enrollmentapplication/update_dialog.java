package com.example.cccpre_enrollmentapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class update_dialog extends AppCompatActivity {

    ListView myListview;
   List<User> studentList;

   DatabaseReference studentDbRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_registrar);

        myListview= findViewById(R.id.myListView);
        studentList=new ArrayList<>();

        studentDbRef= FirebaseDatabase.getInstance().getReference("Users");
        studentDbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                studentList.clear();
                for( DataSnapshot studentDatasnap:snapshot.getChildren()){
                                    }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        })
;



    }

    private void showUpdateDiaolog(final String fullname, String number){
        final AlertDialog.Builder mDialog=new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();


    }
}