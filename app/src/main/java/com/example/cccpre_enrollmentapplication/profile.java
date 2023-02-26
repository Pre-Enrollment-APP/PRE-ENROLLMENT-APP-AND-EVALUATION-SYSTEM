package com.example.cccpre_enrollmentapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
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

import java.util.Calendar;


public class profile extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public TextView name, studentnumber, course;
    public EditText add,enumber,ename,fname,mname,num,bday,emailadd;
    private FirebaseUser user;
    private String userID;
    private ImageButton editButton, menu_page;
    private ImageView profile;
    private SwipeRefreshLayout swipeCotainer;
    private FirebaseAuth authProfile;
    private StorageReference storageReference;
    private FirebaseUser firebaseUser;
    private Uri uriImage;


    //drawer menu

    private ImageView profilepic;
    private TextView status,fullnametab,studentno,coursetab;
    private DatabaseReference userRef;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    private FirebaseAuth auth;


    private ProgressBar progressbar,progressbar1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_profile);

        swipeToRefresh();
        name = findViewById(R.id.SP_fullname);
        studentnumber = findViewById(R.id.Snumber);
        emailadd = findViewById(R.id.SP_email);
        course = findViewById(R.id.course);
        add = findViewById(R.id.SP_address);
        enumber = findViewById(R.id.SP_Emergencynumber);
        ename = findViewById(R.id.SP_Emergencyname);
        fname = findViewById(R.id.SP_fathername);
        mname = findViewById(R.id.SP_mothername);
        num = findViewById(R.id.SP_number);
        bday = findViewById(R.id.SP_bday);
        progressbar = findViewById(R.id.progressbar);
        profile = findViewById(R.id.profile);
        authProfile = FirebaseAuth.getInstance();
        firebaseUser = authProfile.getCurrentUser();

        auth=FirebaseAuth.getInstance();

        //tab menu
        user=auth.getCurrentUser();
        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav_view);
        toolbar=findViewById(R.id.toolbar);

        //toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //Navigation Drawer Menu
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setCheckedItem(R.id.nav_profile);
        profilepic= navigationView.getHeaderView(0).findViewById(R.id.profilepic);
        fullnametab= navigationView.getHeaderView(0).findViewById(R.id.fullname);
        studentno= navigationView.getHeaderView(0).findViewById(R.id.studentnumber);
        coursetab= navigationView.getHeaderView(0).findViewById(R.id.course);


        userRef= FirebaseDatabase.getInstance().getReference("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid());

        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String Name=snapshot.child("Name").getValue().toString();
                    fullnametab.setText(Name);

                    String Course=snapshot.child("Course").getValue().toString();
                    coursetab.setText(Course);

                    String StudentNumber=snapshot.child("Student_number").getValue().toString();
                    studentno.setText(StudentNumber);

                    Uri uri = firebaseUser.getPhotoUrl();

                    if(uri != null){
                        Picasso.with(profile.this).load(uri).into(profilepic);

                    }else{
                        profilepic.setImageResource(R.drawable.user);
                    }
                    progressbar.setVisibility(View.GONE);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        navigationView.setNavigationItemSelectedListener(this);

        editButton = findViewById(R.id.editbutton);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(profile.this, StudentProfile_Edit.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });


        user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("User");

        progressbar.setVisibility(View.VISIBLE);

        databaseRef.child(userID).addListenerForSingleValueEvent((new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String Name = snapshot.child("Name").getValue().toString();
                String StudentNumber = snapshot.child("Student_number").getValue().toString();
                String Course = snapshot.child("Course").getValue().toString();

                String Number = snapshot.child("Contact_Number").getValue().toString();
                String Email = snapshot.child("Email").getValue().toString();
                String Address = snapshot.child("Address").getValue().toString();
                String Birthday = snapshot.child("Birthday").getValue().toString();
                String MotherName = snapshot.child("Mother").getValue().toString();
                String FatherName = snapshot.child("Father").getValue().toString();
                String Emergencynumber = snapshot.child("Emergency_number").getValue().toString();
                String Emergencyname = snapshot.child("Emergency_name").getValue().toString();

                name.setText(Name);
                course.setText(Course);
                studentnumber.setText(StudentNumber);

                num.setText(Number);
                emailadd.setText(Email);
                add.setText(Address);
                bday.setText(Birthday);
                mname.setText(MotherName);
                fname.setText(FatherName);
                enumber.setText(Emergencynumber);
                ename.setText(Emergencyname);

               Uri uri = firebaseUser.getPhotoUrl();

                if(uri != null){
                    Picasso.with(profile.this).load(uri).into(profile);

                }else{
                    profile.setImageResource(R.drawable.user);
                }
                progressbar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(profile.this, "Something wrong happened!", Toast.LENGTH_SHORT).show();
            }
        }));

    }

    @SuppressLint("ResourceAsColor")
    private void swipeToRefresh(){
        swipeCotainer=findViewById(R.id.swipe);
        swipeCotainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                startActivity(getIntent());
                finish();
            overridePendingTransition(0,0);
            swipeCotainer.setRefreshing(false);}
        });
        swipeCotainer.setColorSchemeColors(android.R.color.holo_blue_dark, android.R.color.holo_blue_light, android.R.color.holo_blue_bright, android.R.color.holo_blue_dark);
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_home:
                Intent intent=new Intent(profile.this,Home_Page.class);
                startActivity(intent);
                break;
            case R.id.nav_profile:
                break;
            case R.id.nav_contactus:
                Intent intent3=new Intent(profile.this,contact_us.class);
                startActivity(intent3);
                break;
            case R.id.nav_about:
                Intent intent1=new Intent(profile.this,About.class);
                startActivity(intent1);
                break;
            case R.id.nav_help:
                Intent intent5=new Intent(profile.this,help.class);
                startActivity(intent5);
                break;
            case R.id.nav_logout:
                FirebaseAuth.getInstance().signOut();
                Intent intent2=new Intent(profile.this, login.class);
                intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent2);
                break;

        }
       // drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


}