package com.example.cccpre_enrollmentapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class About extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
public ImageButton menu_page;

    private ImageView profilepic;
    private TextView status,fullnametab,studentno,coursetab;
    private FirebaseUser user;
    private DatabaseReference userRef;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

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

        navigationView.setCheckedItem(R.id.nav_about);
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

                    Uri uri=user.getPhotoUrl();
                    Picasso.with(About.this).load(uri).into(profilepic);
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
                Intent intent1=new Intent(About.this,Home_Page.class);
                startActivity(intent1);
                break;
            case R.id.nav_profile:
                Intent intent=new Intent(About.this,profile.class);
                startActivity(intent);
                break;
            case R.id.nav_about:

                break;

        }
        //drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}