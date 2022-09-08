package com.example.cccpre_enrollmentapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class update_dialog extends AppCompatActivity {

    ListView myListview;
   List<User> studentList;

   DatabaseReference studentDbRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_dialog);


    }

    private void showUpdateDiaolog(final String fullname, String number){
        final AlertDialog.Builder mDialog=new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();


    }
}