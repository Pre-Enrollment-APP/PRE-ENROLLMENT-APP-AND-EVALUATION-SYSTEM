package com.example.cccpre_enrollmentapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.ContentResolver;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.HashMap;


public class StudentProfile_Edit extends AppCompatActivity {
    private TextView  course;
    private EditText name, studentnumber,add,ename,enumber,fname,mname,num,emailadd;
    private EditText bday;
    private FirebaseUser user;
    private String userID;
    public String _Name,_Email, Number,_Course, _Address, _Birthday,_Contact_Number,_Mother,_Mother_number,_Father,_Father_number;
    private FirebaseAuth mAuth;
    private ImageView  buttonUploadPicChoose;
    ProgressBar progressbar;
    private ImageButton back;

    private FirebaseAuth authProfile;
    private StorageReference storageReference;
    private FirebaseUser firebaseUser;
    private Uri uriImage;
    private static final int PICK_IMAGE_REQUEST = 1;


    private Button SAVEPROFILE;
    private Spinner coursespinner;
    private Button DateButton;
    private DatePickerDialog datePickerDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_profile_edit);

        name=findViewById(R.id.SP_fullname);
        studentnumber=findViewById(R.id.Snumber);
        emailadd=findViewById(R.id.SP_email);
        course=findViewById(R.id.course);
        add=findViewById(R.id.SP_address);
        enumber=findViewById(R.id.SP_Emergencynumber);
        ename=findViewById(R.id.SP_Emergencyname);
        fname=findViewById(R.id.SP_fathername);
        mname=findViewById(R.id.SP_mothername);
        bday=findViewById(R.id.SP_bday);
        SAVEPROFILE=findViewById(R.id.editbutton);
        num=findViewById(R.id.SP_number);
        progressbar= findViewById(R.id.progressbar);
        buttonUploadPicChoose=findViewById(R.id.profile);
        user=FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference databaseRef= FirebaseDatabase.getInstance().getReference("User");
        userID=user.getUid();
        back=findViewById(R.id.back);

        authProfile = FirebaseAuth.getInstance();
        firebaseUser = authProfile.getCurrentUser();

        storageReference = FirebaseStorage.getInstance().getReference("DisplayPic");

        Uri uri = firebaseUser.getPhotoUrl();
        Picasso.with(StudentProfile_Edit.this).load(uri).into( buttonUploadPicChoose);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(StudentProfile_Edit.this,profile.class);
                startActivity(intent);

            }
        });


        buttonUploadPicChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();

            }
        });


        databaseRef.child(userID).addListenerForSingleValueEvent((new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String Name =snapshot.child("Name").getValue().toString();
                String StudentNumber=snapshot.child("Student_number").getValue().toString();
                String Course=snapshot.child("Course").getValue().toString();

                String Number=snapshot.child("Contact_Number").getValue().toString();
                String Email =snapshot.child("Email").getValue().toString();
                String Address=snapshot.child("Address").getValue().toString();
                String Birthday=snapshot.child("Birthday").getValue().toString();
                String MotherName=snapshot.child("Mother").getValue().toString();
                String FatherName=snapshot.child("Father").getValue().toString();
                String Emergencynumber=snapshot.child("Emergency_number").getValue().toString();
                String Emergencyname=snapshot.child("Emergency_name").getValue().toString();


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
                progressbar.setVisibility(View.GONE);

                if(uri != null){
                    Picasso.with(StudentProfile_Edit.this).load(uri).into(buttonUploadPicChoose);
                }else{
                    buttonUploadPicChoose.setImageResource(R.drawable.user);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(StudentProfile_Edit .this, "Something wrong happened!", Toast.LENGTH_SHORT).show();
            }
        }));
        initDatePicker();



        SAVEPROFILE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                updateProfile(firebaseUser);

            }
        });



    }

    private void updateProfile(FirebaseUser firebaseUser) {
        String Email = emailadd.getText().toString().trim();
        String Course = course.getText().toString().trim();
        String Name = name.getText().toString().trim();
        String Contact_Number= num.getText().toString().trim();
        String Birthday=bday.getText().toString().trim();
        String Address= add.getText().toString().trim();
        String Mother =mname.getText().toString().trim();
        String Father =fname.getText().toString().trim();
        String Student_number=studentnumber.getText().toString().trim();
        String Emergency_number=enumber.getText().toString().trim();
        String Emergency_name=ename.getText().toString().trim();

        User user=new User (Name,Email, Student_number,Course, Address, Birthday,Contact_Number,Mother,Emergency_number,Father,Emergency_name);

        DatabaseReference referenceProfile =FirebaseDatabase.getInstance().getReference("User");

        userID=firebaseUser.getUid();
        progressbar.setVisibility(View.VISIBLE);

        referenceProfile.child(userID).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    UserProfileChangeRequest profileUpdate =new UserProfileChangeRequest.Builder().build();
                    firebaseUser.updateProfile(profileUpdate);
                    Uri uri=firebaseUser.getPhotoUrl();
                    Picasso.with(StudentProfile_Edit.this).load(uri).into(buttonUploadPicChoose);
                    Toast.makeText(StudentProfile_Edit.this, "Update Successfully", Toast.LENGTH_SHORT).show();

                    Intent intent= new Intent(StudentProfile_Edit.this, profile.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                            Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);



                }else {
                    try {

                        throw task.getException();
                    }catch(Exception e){
                        Toast.makeText(StudentProfile_Edit.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
                UploadPic();
                progressbar.setVisibility(View.GONE);
            }
        });
    }

    private  void initDatePicker(){
        DatePickerDialog.OnDateSetListener dataSetListerner= new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month=month+1;
                String date=makeDateString(day,month,year);
                bday.setText(date);
            }
        };
        Calendar cal= Calendar.getInstance();
        int year=cal.get(Calendar.YEAR);
        int day=cal.get(Calendar.DAY_OF_MONTH);
        int month=cal.get(Calendar.MONTH);
        int style= AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog =new DatePickerDialog(this,style, dataSetListerner,year, month ,day);

    }

    private String makeDateString(int day, int month, int year) {
        return getMonthFormat(month)+ " "+ day+" "+" "+year;
    }

    private String getMonthFormat(int month) {
        if(month==1)
            return" AN";
        if(month==2)
            return"FEB";
        if(month==3)
            return"MAR";
        if(month==4)
            return "APR";
        if(month==5)
            return"MAY";
        if(month==6)
            return" JUN";
        if(month==7)
            return"JUL";
        if(month==8)
            return"AUG";
        if(month==9)
            return"SEP";
        if(month==10)
            return"OCT";
        if(month==11)
            return"NOV";
        if(month==12)
            return"DEC";

        //default
        return "JAN";

    }

    public void opendatePicker(View view) {
        datePickerDialog.show();
    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }
    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    private void UploadPic() {
        if (uriImage != null) {
            //save the image with uid of the currently logged user

            StorageReference fileReference = storageReference.child(authProfile.getCurrentUser().getUid() + "."
                    + getFileExtension(uriImage));
            //Upload image to Storage
            fileReference.putFile(uriImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Uri downloadUri=uri;
                            firebaseUser=authProfile.getCurrentUser();
                            UserProfileChangeRequest profileUpdates=new UserProfileChangeRequest.Builder()
                                    .setPhotoUri(downloadUri).build();
                            firebaseUser.updateProfile(profileUpdates);
                        }
                    });
                }
            });
        }
    }
    private String getFileExtension(Uri uri) {
        ContentResolver cR=getContentResolver();
        MimeTypeMap mime= MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            uriImage = data.getData();
            buttonUploadPicChoose.setImageURI(uriImage);


        }
    }


}