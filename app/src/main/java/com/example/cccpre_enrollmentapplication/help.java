package com.example.cccpre_enrollmentapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
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


public class help extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ImageButton pre_enrollment;
    private ImageButton Evaluation;
    private FirebaseAuth auth;
    private DatabaseReference databaseRef;
    //drawer menu
    private ImageView profilepic;
    private TextView status,fullnametab,studentno,coursetab;
    private FirebaseUser user;
    private DatabaseReference userRef;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    private ProgressBar progressbar;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);


        auth = FirebaseAuth.getInstance();

        //tab menu
        user = auth.getCurrentUser();
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        databaseRef = FirebaseDatabase.getInstance().getReference("pre_enrollment");
        //toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //Navigation Drawer Menu
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setCheckedItem(R.id.nav_help);
        profilepic = navigationView.getHeaderView(0).findViewById(R.id.profilepic);
        fullnametab = navigationView.getHeaderView(0).findViewById(R.id.fullname);
        studentno = navigationView.getHeaderView(0).findViewById(R.id.studentnumber);
        coursetab = navigationView.getHeaderView(0).findViewById(R.id.course);
        progressbar = navigationView.getHeaderView(0).findViewById(R.id.progressbar);
        userRef = FirebaseDatabase.getInstance().getReference("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference reference = storage.getReference().child("Displaypic/");
        Uri uri = user.getPhotoUrl();
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String Name = snapshot.child("Name").getValue().toString();
                    fullnametab.setText(Name);

                    String Course = snapshot.child("Course").getValue().toString();
                    coursetab.setText(Course);

                    String StudentNumber = snapshot.child("Student_number").getValue().toString();
                    studentno.setText(StudentNumber);


                }

                if (uri != null) {
                    Picasso.with(help.this).load(uri).into(profilepic);
                } else {
                    profilepic.setImageResource(R.drawable.user);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        navigationView.setNavigationItemSelectedListener(this);
    }



    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) { switch (item.getItemId()){
        case R.id.nav_home:
            Intent intent2=new Intent(help.this,Home_Page.class);
            startActivity(intent2);
            break;
        case R.id.nav_profile:
            Intent intent=new Intent(help.this,profile.class);
            startActivity(intent);
            break;
        case R.id.nav_about:
            Intent intent1=new Intent(help.this,About.class);
            startActivity(intent1);
            break;
        case R.id.nav_help:
            break;
        case R.id.contact_us:
            Intent intent3=new Intent(help.this,contact_us.class);
            startActivity(intent3);
            break;
        case R.id.nav_logout:
            FirebaseAuth.getInstance().signOut();
            Intent intent4=new Intent(help.this, login.class);
            intent4.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent4.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent4.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent4);
            break;



    }
        //drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}