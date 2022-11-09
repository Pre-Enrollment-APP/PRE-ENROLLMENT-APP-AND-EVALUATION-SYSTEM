package com.example.cccpre_enrollmentapplication;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelper {

    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceGrades;
    private List<Grades> grade= new ArrayList<>();
    private FirebaseUser user;
    private String userID;

    public interface DataStatus{
        void DataIsLoaded(List<Grades> grade, List<String>keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

    public FirebaseDatabaseHelper() {
        mDatabase=FirebaseDatabase.getInstance();
        mReferenceGrades=mDatabase.getReference("User/Grades/first_year/1st_sem");
        user= FirebaseAuth.getInstance().getCurrentUser();
        userID=user.getUid();

    }

    public void viewGrades(final DataStatus dataStatus){
        mReferenceGrades.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                grade.clear();
                List<String> keys =new ArrayList<>();
                for(DataSnapshot keyNode:snapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    Grades grades =keyNode.getValue(Grades.class);
                    grade.add(grades);
                }
                dataStatus.DataIsLoaded(grade,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
