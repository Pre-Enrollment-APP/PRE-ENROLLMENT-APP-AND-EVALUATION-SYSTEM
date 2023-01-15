package com.example.cccpre_enrollmentapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.support.v4.os.IResultReceiver;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class Pre_Enrollment extends AppCompatActivity {
    private ImageButton menu_page;
    private Button ok;
    private TextView name, course, address, gmail, birthday;
    private int totalunits = 0;
    private long invoiceNo = 0;
    int total = 0, one, two, three, four, five, six, seven, eight, nine, ten;
    private Spinner semester, section, mop;
    private EditText des1, des2, des3, des4, des5, des6, des7, des8, des9, des10, schoolyear;
    private EditText units1, units2, units3, units4, units5, units6, units7, units8, units9, units10;
    ArrayAdapter<String> sectionlist;
    ArrayAdapter<String> moplist;
    String[] sections, MOP;
    private  EditText downpayment;
    SimpleDateFormat datePattternformat = new SimpleDateFormat("yyyy-yyyy");
    private String userID;
    private DatabaseReference databaseRef;
    private FirebaseUser user;
    private ImageButton back;
    private EditText SC1, SC2, SC3, SC4, SC5, SC6, SC7, SC8, SC9, SC10;
    private EditText s1, s2, s3, s4, s5, s6, s7, s8, s9, s10;
    //logo image print
    Bitmap bmp, scaledbmp;

    //print pdf
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference print = database.getReference("User");
    studentinfo dataObj = new studentinfo();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pre_enrollment);
        name = findViewById(R.id.studentName);
        course = findViewById(R.id.course);
        downpayment = findViewById(R.id.downpayment);
        semester = findViewById(R.id.semester);
        section = findViewById(R.id.section);
        schoolyear = findViewById(R.id.schoolyear);
        gmail = findViewById(R.id.gmail);
        address = findViewById(R.id.address);
        birthday = findViewById(R.id.birthday);
        back=findViewById(R.id.back);

        user = FirebaseAuth.getInstance().getCurrentUser();
        databaseRef = FirebaseDatabase.getInstance().getReference("User");
        userID = user.getUid();

        SC1 = findViewById(R.id.SC1);
        SC2 = findViewById(R.id.SC2);
        SC3 = findViewById(R.id.SC3);
        SC4 = findViewById(R.id.SC4);
        SC5 = findViewById(R.id.SC5);
        SC6 = findViewById(R.id.SC6);
        SC7 = findViewById(R.id.SC7);
        SC8 = findViewById(R.id.SC8);
        SC9 = findViewById(R.id.SC9);
        SC10 = findViewById(R.id.SC10);

        des1 = findViewById(R.id.Des1);
        des2 = findViewById(R.id.Des2);
        des3 = findViewById(R.id.Des3);
        des4 = findViewById(R.id.Des4);
        des5 = findViewById(R.id.Des5);
        des6 = findViewById(R.id.Des6);
        des7 = findViewById(R.id.Des7);
        des8 = findViewById(R.id.Des8);
        des9 = findViewById(R.id.Des9);
        des10 = findViewById(R.id.Des10);

        units1 = findViewById(R.id.units1);
        units2 = findViewById(R.id.units2);
        units3 = findViewById(R.id.units3);
        units4 = findViewById(R.id.units4);
        units5 = findViewById(R.id.units5);
        units6 = findViewById(R.id.units6);
        units7 = findViewById(R.id.units7);
        units8 = findViewById(R.id.units8);
        units9 = findViewById(R.id.units9);
        units10 = findViewById(R.id.units10);


        s1 = findViewById(R.id.sched1);
        s2 = findViewById(R.id.sched2);
        s3 = findViewById(R.id.sched3);
        s4 = findViewById(R.id.sched4);
        s5 = findViewById(R.id.sched5);
        s6 = findViewById(R.id.sched6);
        s7 = findViewById(R.id.sched7);
        s8 = findViewById(R.id.sched8);
        s9 = findViewById(R.id.sched9);
        s10 = findViewById(R.id.sched10);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Pre_Enrollment.this, Home_Page.class));
            }
        });



//////////// /////////////////////////S E C T I O N DROPDOWN///////////////////////////////////////////////////

        sections = new String[]{"--Select--", "A", "B", "C",};
        sectionlist = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, sections);
        section.setAdapter(sectionlist);

        section.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                String Course = course.getText().toString();
                String YearSem = semester.getSelectedItem().toString();

                switch (item) {
                    case "A":

                        if (Course.equals("Bachelor of Science in Computer Science")) {
                            if (YearSem.equals("1st yr, 1st sem")) {
                                bscs1y1sSched_A();
                            }
                            else if (YearSem.equals("1st yr, 2nd sem")) {
                            bscs1y2sSched_A();

                        } else if (YearSem.equals("2nd yr, 1st sem")) {
                            bscs2y1sSched_A();

                        } else if (YearSem.equals("2nd yr, 2nd sem")) {
                            bscs2y2sSched_A();
                        } else if (YearSem.equals("3rd yr, 1st sem")) {
                            bscs3y1sSched_A();

                        } else if (YearSem.equals("3rd yr, 2nd sem")) {
                            bscs3y2sSched_A();
                        } else if (YearSem.equals("4th yr, 1st sem")) {
                            bscs4y1sSched_A();

                        } else if (YearSem.equals("4th yr, 2nd sem")) {
                            bscs4y2sSched_A();
                        }
                }

                        else if(Course.equals("Bachelor of Elementary Education")) {
                            if (YearSem.equals("1st yr, 1st sem")) {
                                beed1y1sSched_A();

                            } else if (YearSem.equals("1st yr, 2nd sem")) {
                                beed1y2sSched_A();

                            } else if (YearSem.equals("2nd yr, 1st sem")) {
                                beed2y1sSched_A();

                            } else if (YearSem.equals("2nd yr, 2nd sem")) {
                                beed2y2sSched_A();
                            } else if (YearSem.equals("3rd yr, 1st sem")) {
                                beed3y1sSched_A();

                            }else if (YearSem.equals("3rd yr, 2nd sem")) {
                                bscs3y2sSched_A();
                            } else if (YearSem.equals("4th yr, 1st sem")) {
                                bscs4y1sSched_A();

                            }else if (YearSem.equals("4th yr, 2nd sem")) {
                                bscs4y2sSched_A();
                            }


                        }
                        //bsoa
                        else if(Course.equals("Bachelor of Science in Office Administration")) {
                            if (YearSem.equals("1st yr, 1st sem")) {
                                bsoa1y1sSched_A();

                            } else if (YearSem.equals("1st yr, 2nd sem")) {
                                bsoa1y2sSched_A();

                            } else if (YearSem.equals("2nd yr, 1st sem")) {
                                bsoa2y1sSched_A();

                            } else if (YearSem.equals("2nd yr, 2nd sem")) {
                                bsoa2y2sSched_A();
                            } else if (YearSem.equals("3rd yr, 1st sem")) {
                                bsoa3y1sSched_A();

                            }else if (YearSem.equals("3rd yr, 2nd sem")) {
                                bsoa3y2sSched_A();
                            } else if (YearSem.equals("4th yr, 1st sem")) {
                                bsoa4y1sSched_A();

                            }else if (YearSem.equals("4th yr, 2nd sem")) {
                                bsoa4y2sSched_A();
                            }


                        }
                        else if (Course.equals("Bachelor of Science in Business Administration")){
                            if (YearSem.equals("1st yr, 1st sem")) {
                                bsba1y1sSched_A();

                            } else if (YearSem.equals("1st yr, 2nd sem")) {
                                bsba1y2sSched_A();

                            } else if (YearSem.equals("2nd yr, 1st sem")) {
                                bsba2y1sSched_A();

                            } else if (YearSem.equals("2nd yr, 2nd sem")) {
                                bsba2y2sSched_A();
                            } else if (YearSem.equals("3rd yr, 1st sem")) {
                                bsba3y1sSched_A();

                            }else if (YearSem.equals("3rd yr, 2nd sem")) {
                                bsba3y2sSched_A();
                            } else if (YearSem.equals("4th yr, 1st sem")) {
                                bsba4y1sSched_A();

                            }else if (YearSem.equals("4th yr, 2nd sem")) {
                                bsba4y2sSched_A();
                            }

                        }
                        else if(Course.equals("Bachelor of Secondary Education in English")) {
                            if (YearSem.equals("1st yr, 1st sem")) {
                                abreed1y1sSched_C();

                            } else if (YearSem.equals("1st yr, 2nd sem")) {
                                abreed1y2sSched_C();

                            } else if (YearSem.equals("2nd yr, 1st sem")) {
                                abreed2y1sSched_C();

                            } else if (YearSem.equals("2nd yr, 2nd sem")) {
                                abreed2y2sSched_C();
                            } else if (YearSem.equals("3rd yr, 1st sem")) {
                                abreed3y1sSched_C();

                            }else if (YearSem.equals("3rd yr, 2nd sem")) {
                                abreed3y2sSched_C();
                            } else if (YearSem.equals("4th yr, 1st sem")) {
                                abreed4y1sSched_C();

                            }else if (YearSem.equals("4th yr, 2nd sem")) {
                                abreed4y2sSched_C();
                            }

                        }
                        else if(Course.equals("Bachelor of Secondary Eduction in Math")) {
                            if (YearSem.equals("1st yr, 1st sem")) {
                                abreed1y1sSched_C();

                            } else if (YearSem.equals("1st yr, 2nd sem")) {
                                abreed1y2sSched_C();

                            } else if (YearSem.equals("2nd yr, 1st sem")) {
                                abreed2y1sSched_C();

                            } else if (YearSem.equals("2nd yr, 2nd sem")) {
                                abreed2y2sSched_C();
                            } else if (YearSem.equals("3rd yr, 1st sem")) {
                                abreed3y1sSched_C();

                            }else if (YearSem.equals("3rd yr, 2nd sem")) {
                                abreed3y2sSched_C();
                            } else if (YearSem.equals("4th yr, 1st sem")) {
                                abreed4y1sSched_C();

                            }else if (YearSem.equals("4th yr, 2nd sem")) {
                                abreed4y2sSched_C();
                            }

                        }
                        else if(Course.equals("Bachelor of Secondary Education in Science")) {
                            if (YearSem.equals("1st yr, 1st sem")) {
                                abreed1y1sSched_C();

                            } else if (YearSem.equals("1st yr, 2nd sem")) {
                                abreed1y2sSched_C();

                            } else if (YearSem.equals("2nd yr, 1st sem")) {
                                abreed2y1sSched_C();

                            } else if (YearSem.equals("2nd yr, 2nd sem")) {
                                abreed2y2sSched_C();
                            } else if (YearSem.equals("3rd yr, 1st sem")) {
                                abreed3y1sSched_C();

                            }else if (YearSem.equals("3rd yr, 2nd sem")) {
                                abreed3y2sSched_C();
                            } else if (YearSem.equals("4th yr, 1st sem")) {
                                abreed4y1sSched_C();

                            }else if (YearSem.equals("4th yr, 2nd sem")) {
                                abreed4y2sSched_C();
                            }

                        }
                        else {
                            Toast.makeText(Pre_Enrollment.this, "Choose your *Year and Semester* first", Toast.LENGTH_SHORT).show();
                        }
                        break;

                    case "B":{

                        if (Course.equals("Bachelor of Science in Computer Science")) {
                            if (YearSem.equals("1st yr, 1st sem")) {
                                bscs1y1sSched_B();

                            } else if (YearSem.equals("1st yr, 2nd sem")) {
                                bscs1y2sSched_B();

                            } else if (YearSem.equals("2nd yr, 1st sem")) {
                                bscs2y1sSched_B();

                            } else if (YearSem.equals("2nd yr, 2nd sem")) {
                                bscs2y2sSched_B();
                            } else if (YearSem.equals("3rd yr, 1st sem")) {
                                bscs3y1sSched_B();

                            }else if (YearSem.equals("3rd yr, 2nd sem")) {
                                bscs3y2sSched_B();
                            } else if (YearSem.equals("4th yr, 1st sem")) {
                                bscs4y1sSched_B();

                            }else if (YearSem.equals("4th yr, 2nd sem")) {
                                bscs4y2sSched_B();
                            }


                        }

                        else if(Course.equals("Bachelor of Elementary Education")) {
                            if (YearSem.equals("1st yr, 1st sem")) {
                                beed1y1sSched_B();

                            } else if (YearSem.equals("1st yr, 2nd sem")) {
                                beed1y2sSched_B();

                            } else if (YearSem.equals("2nd yr, 1st sem")) {
                                beed2y1sSched_B();

                            } else if (YearSem.equals("2nd yr, 2nd sem")) {
                                beed2y2sSched_B();
                            } else if (YearSem.equals("3rd yr, 1st sem")) {
                                beed3y1sSched_B();

                            }else if (YearSem.equals("3rd yr, 2nd sem")) {
                                beed3y2sSched_B();
                            } else if (YearSem.equals("4th yr, 1st sem")) {
                                beed4y1sSched_B();

                            }else if (YearSem.equals("4th yr, 2nd sem")) {
                                beed4y2sSched_B();
                            }

                        }
                        else if(Course.equals("Bachelor of Science in Office Administration")) {
                            if (YearSem.equals("1st yr, 1st sem")) {
                                bsoa1y1sSched_B();

                            } else if (YearSem.equals("1st yr, 2nd sem")) {
                                bsoa1y2sSched_B();

                            } else if (YearSem.equals("2nd yr, 1st sem")) {
                                bsoa2y1sSched_B();

                            } else if (YearSem.equals("2nd yr, 2nd sem")) {
                                bsoa2y2sSched_B();
                            } else if (YearSem.equals("3rd yr, 1st sem")) {
                                bsoa3y1sSched_B();

                            }else if (YearSem.equals("3rd yr, 2nd sem")) {
                                bsoa3y2sSched_B();
                            } else if (YearSem.equals("4th yr, 1st sem")) {
                                bsoa4y1sSched_C();

                            }else if (YearSem.equals("4th yr, 2nd sem")) {
                                bsoa4y2sSched_B();
                            }

                        }
                        else if(Course.equals("Bachelor of Science in Business Administration")) {
                            if (YearSem.equals("1st yr, 1st sem")) {
                                bsoa1y1sSched_B();

                            } else if (YearSem.equals("1st yr, 2nd sem")) {
                                bsba1y2sSched_C();

                            } else if (YearSem.equals("2nd yr, 1st sem")) {
                                bsba2y1sSched_C();

                            } else if (YearSem.equals("2nd yr, 2nd sem")) {
                                bsba2y2sSched_C();
                            } else if (YearSem.equals("3rd yr, 1st sem")) {
                                bsba3y1sSched_B();

                            }else if (YearSem.equals("3rd yr, 2nd sem")) {
                                bsba3y2sSched_B();

                            } else if (YearSem.equals("4th yr, 1st sem")) {
                                bsba4y1sSched_B();

                            }else if (YearSem.equals("4th yr, 2nd sem")) {
                                bsba4y2sSched_B();
                            }

                        }
                        else if(Course.equals("Bachelor of Arts in Religious Education")) {
                            if (YearSem.equals("1st yr, 1st sem")) {
                                abreed1y1sSched_C();

                            } else if (YearSem.equals("1st yr, 2nd sem")) {
                                abreed1y2sSched_C();

                            } else if (YearSem.equals("2nd yr, 1st sem")) {
                                abreed2y1sSched_C();

                            } else if (YearSem.equals("2nd yr, 2nd sem")) {
                                abreed2y2sSched_C();
                            } else if (YearSem.equals("3rd yr, 1st sem")) {
                                abreed3y1sSched_C();

                            }else if (YearSem.equals("3rd yr, 2nd sem")) {
                                abreed3y2sSched_C();
                            } else if (YearSem.equals("4th yr, 1st sem")) {
                                abreed4y1sSched_C();

                            }else if (YearSem.equals("4th yr, 2nd sem")) {
                                abreed4y2sSched_C();
                            }

                        }
                        else if(Course.equals("Bachelor of Secondary Education in English")) {
                            if (YearSem.equals("1st yr, 1st sem")) {
                                abreed1y1sSched_C();

                            } else if (YearSem.equals("1st yr, 2nd sem")) {
                                abreed1y2sSched_C();

                            } else if (YearSem.equals("2nd yr, 1st sem")) {
                                abreed2y1sSched_C();

                            } else if (YearSem.equals("2nd yr, 2nd sem")) {
                                abreed2y2sSched_C();
                            } else if (YearSem.equals("3rd yr, 1st sem")) {
                                abreed3y1sSched_C();

                            }else if (YearSem.equals("3rd yr, 2nd sem")) {
                                abreed3y2sSched_C();
                            } else if (YearSem.equals("4th yr, 1st sem")) {
                                abreed4y1sSched_C();

                            }else if (YearSem.equals("4th yr, 2nd sem")) {
                                abreed4y2sSched_C();
                            }

                        }
                        else if(Course.equals("Bachelor of Secondary Eduction in Math")) {
                            if (YearSem.equals("1st yr, 1st sem")) {
                                abreed1y1sSched_C();

                            } else if (YearSem.equals("1st yr, 2nd sem")) {
                                abreed1y2sSched_C();

                            } else if (YearSem.equals("2nd yr, 1st sem")) {
                                abreed2y1sSched_C();

                            } else if (YearSem.equals("2nd yr, 2nd sem")) {
                                abreed2y2sSched_C();
                            } else if (YearSem.equals("3rd yr, 1st sem")) {
                                abreed3y1sSched_C();

                            }else if (YearSem.equals("3rd yr, 2nd sem")) {
                                abreed3y2sSched_C();
                            } else if (YearSem.equals("4th yr, 1st sem")) {
                                abreed4y1sSched_C();

                            }else if (YearSem.equals("4th yr, 2nd sem")) {
                                abreed4y2sSched_C();
                            }

                        }
                        else if(Course.equals("Bachelor of Secondary Education in Science")) {
                            if (YearSem.equals("1st yr, 1st sem")) {
                                abreed1y1sSched_C();

                            } else if (YearSem.equals("1st yr, 2nd sem")) {
                                abreed1y2sSched_C();

                            } else if (YearSem.equals("2nd yr, 1st sem")) {
                                abreed2y1sSched_C();

                            } else if (YearSem.equals("2nd yr, 2nd sem")) {
                                abreed2y2sSched_C();
                            } else if (YearSem.equals("3rd yr, 1st sem")) {
                                abreed3y1sSched_C();

                            }else if (YearSem.equals("3rd yr, 2nd sem")) {
                                abreed3y2sSched_C();
                            } else if (YearSem.equals("4th yr, 1st sem")) {
                                abreed4y1sSched_C();

                            }else if (YearSem.equals("4th yr, 2nd sem")) {
                                abreed4y2sSched_C();
                            }

                        }
                        else {
                            Toast.makeText(Pre_Enrollment.this, "Choose your *Year and Semester* first", Toast.LENGTH_SHORT).show();
                        }
                        break;

                    }
                    case "C":{
                        if (Course.equals("Bachelor of Science in Computer Science")) {
                            if (YearSem.equals("1st yr, 1st sem")) {
                                bscs1y1sSched_C();

                            } else if (YearSem.equals("1st yr, 2nd sem")) {
                                bscs1y2sSched_C();

                            } else if (YearSem.equals("2nd yr, 1st sem")) {
                                bscs2y1sSched_C();

                            } else if (YearSem.equals("2nd yr, 2nd sem")) {
                                bscs2y2sSched_C();
                            } else if (YearSem.equals("3rd yr, 1st sem")) {
                                bscs3y1sSched_C();

                            }else if (YearSem.equals("3rd yr, 2nd sem")) {
                                bscs3y2sSched_C();
                            } else if (YearSem.equals("4th yr, 1st sem")) {
                                bscs4y1sSched_C();

                            }else if (YearSem.equals("4th yr, 2nd sem")) {
                                bscs4y2sSched_C();
                            }


                        }
                        else if(Course.equals("Bachelor of Elementary Education")) {
                            if (YearSem.equals("1st yr, 1st sem")) {
                                beed1y1sSched_C();

                            } else if (YearSem.equals("1st yr, 2nd sem")) {
                                beed1y2sSched_C();

                            } else if (YearSem.equals("2nd yr, 1st sem")) {
                                beed2y1sSched_C();

                            } else if (YearSem.equals("2nd yr, 2nd sem")) {
                                beed2y2sSched_C();
                            } else if (YearSem.equals("3rd yr, 1st sem")) {
                                beed3y1sSched_C();

                            }else if (YearSem.equals("3rd yr, 2nd sem")) {
                                beed3y2sSched_C();
                            } else if (YearSem.equals("4th yr, 1st sem")) {
                                beed4y1sSched_C();

                            }else if (YearSem.equals("4th yr, 2nd sem")) {
                                beed4y2sSched_C();
                            }

                        }
                        else if(Course.equals("Bachelor of Science in Office Administration")) {
                            if (YearSem.equals("1st yr, 1st sem")) {
                                bsoa1y1sSched_C();

                            } else if (YearSem.equals("1st yr, 2nd sem")) {
                                bsoa1y2sSched_C();

                            } else if (YearSem.equals("2nd yr, 1st sem")) {
                                bsoa2y1sSched_C();

                            } else if (YearSem.equals("2nd yr, 2nd sem")) {
                                bsoa2y2sSched_C();
                            } else if (YearSem.equals("3rd yr, 1st sem")) {
                                bsoa3y1sSched_C();

                            }else if (YearSem.equals("3rd yr, 2nd sem")) {
                                bsoa3y2sSched_C();
                            } else if (YearSem.equals("4th yr, 1st sem")) {
                                bsoa4y1sSched_C();

                            }else if (YearSem.equals("4th yr, 2nd sem")) {
                                bsoa4y2sSched_C();
                            }

                        }
                        else if(Course.equals("Bachelor of Science in Business Administration")) {
                            if (YearSem.equals("1st yr, 1st sem")) {
                                bsoa1y1sSched_C();

                            } else if (YearSem.equals("1st yr, 2nd sem")) {
                                bsba1y2sSched_C();

                            } else if (YearSem.equals("2nd yr, 1st sem")) {
                                bsba2y1sSched_C();

                            } else if (YearSem.equals("2nd yr, 2nd sem")) {
                                bsba2y2sSched_C();
                            } else if (YearSem.equals("3rd yr, 1st sem")) {
                                bsba3y1sSched_C();

                            }else if (YearSem.equals("3rd yr, 2nd sem")) {
                                bsba3y2sSched_C();
                            } else if (YearSem.equals("4th yr, 1st sem")) {
                                bsba4y1sSched_C();

                            }else if (YearSem.equals("4th yr, 2nd sem")) {
                                bsba4y2sSched_C();
                            }

                        }
                        else if(Course.equals("Bachelor of Arts in Religious Education")) {
                            if (YearSem.equals("1st yr, 1st sem")) {
                                abreed1y1sSched_C();

                            } else if (YearSem.equals("1st yr, 2nd sem")) {
                                abreed1y2sSched_C();

                            } else if (YearSem.equals("2nd yr, 1st sem")) {
                                abreed2y1sSched_C();

                            } else if (YearSem.equals("2nd yr, 2nd sem")) {
                                abreed2y2sSched_C();
                            } else if (YearSem.equals("3rd yr, 1st sem")) {
                                abreed3y1sSched_C();

                            }else if (YearSem.equals("3rd yr, 2nd sem")) {
                                abreed3y2sSched_C();
                            } else if (YearSem.equals("4th yr, 1st sem")) {
                                abreed4y1sSched_C();

                            }else if (YearSem.equals("4th yr, 2nd sem")) {
                                abreed4y2sSched_C();
                            }

                        }
                        else if(Course.equals("Bachelor of Secondary Education in English")) {
                            if (YearSem.equals("1st yr, 1st sem")) {
                                abreed1y1sSched_C();

                            } else if (YearSem.equals("1st yr, 2nd sem")) {
                                abreed1y2sSched_C();

                            } else if (YearSem.equals("2nd yr, 1st sem")) {
                                abreed2y1sSched_C();

                            } else if (YearSem.equals("2nd yr, 2nd sem")) {
                                abreed2y2sSched_C();
                            } else if (YearSem.equals("3rd yr, 1st sem")) {
                                abreed3y1sSched_C();

                            }else if (YearSem.equals("3rd yr, 2nd sem")) {
                                abreed3y2sSched_C();
                            } else if (YearSem.equals("4th yr, 1st sem")) {
                                abreed4y1sSched_C();

                            }else if (YearSem.equals("4th yr, 2nd sem")) {
                                abreed4y2sSched_C();
                            }

                        }
                        else if(Course.equals("Bachelor of Secondary Eduction in Math")) {
                            if (YearSem.equals("1st yr, 1st sem")) {
                                abreed1y1sSched_C();

                            } else if (YearSem.equals("1st yr, 2nd sem")) {
                                abreed1y2sSched_C();

                            } else if (YearSem.equals("2nd yr, 1st sem")) {
                                abreed2y1sSched_C();

                            } else if (YearSem.equals("2nd yr, 2nd sem")) {
                                abreed2y2sSched_C();
                            } else if (YearSem.equals("3rd yr, 1st sem")) {
                                abreed3y1sSched_C();

                            }else if (YearSem.equals("3rd yr, 2nd sem")) {
                                abreed3y2sSched_C();
                            } else if (YearSem.equals("4th yr, 1st sem")) {
                                abreed4y1sSched_C();

                            }else if (YearSem.equals("4th yr, 2nd sem")) {
                                abreed4y2sSched_C();
                            }

                        }
                        else if(Course.equals("Bachelor of Secondary Education in Science")) {
                            if (YearSem.equals("1st yr, 1st sem")) {
                                abreed1y1sSched_C();

                            } else if (YearSem.equals("1st yr, 2nd sem")) {
                                abreed1y2sSched_C();

                            } else if (YearSem.equals("2nd yr, 1st sem")) {
                                abreed2y1sSched_C();

                            } else if (YearSem.equals("2nd yr, 2nd sem")) {
                                abreed2y2sSched_C();
                            } else if (YearSem.equals("3rd yr, 1st sem")) {
                                abreed3y1sSched_C();

                            }else if (YearSem.equals("3rd yr, 2nd sem")) {
                                abreed3y2sSched_C();
                            } else if (YearSem.equals("4th yr, 1st sem")) {
                                abreed4y1sSched_C();

                            }else if (YearSem.equals("4th yr, 2nd sem")) {
                                abreed4y2sSched_C();
                            }

                        }
                        else {
                            Toast.makeText(Pre_Enrollment.this, "Choose your *Year and Semester* first", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    }
                    case "--select--":{
                        s1.getText().clear();
                        s2.getText().clear();
                        s3.getText().clear();
                        s4.getText().clear();
                        s5.getText().clear();
                        s6.getText().clear();
                        s7.getText().clear();
                        s8.getText().clear();
                        s9.getText().clear();
                        s10.getText().clear();

                    }



                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.ccc);
        scaledbmp = Bitmap.createScaledBitmap(bmp, 45, 40, true);

//////////// /////////////////////////MODE OF PAYMENT DROPDOWN /////////////////////////////////////////


        print.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                invoiceNo = snapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//////////// /////////////////////////SEMESTER DROPDOWN///////////////////////////////////////////////////

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.semester, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        semester.setAdapter(adapter);
        semester.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                switch (item) {
                    case "1st yr, 1st sem":
                        databaseRef.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                String Course = snapshot.child("Course").getValue().toString();

                                if (Course.equals("Bachelor of Science in Computer Science")) {
                                    BSCS1y1sem();

                                }else if (Course.equals("Bachelor of Elementary Education")){
                                    BEED1y1sem();
                                }else if (Course.equals("Bachelor of Secondary Education in English")){
                                    // BEED1y2sem();
                                }else if (Course.equals("Bachelor of Secondary Education in Science")){
                                    // BEED1y2sem();
                                }
                                else if (Course.equals("Bachelor of Science in Office Administration")){
                                    // BEED1y2sem();
                                }  else if (Course.equals("Bachelor of Science in Business Administration")){
                                    // BEED1y2sem();
                                }else if (Course.equals("Bachelor of Science in Business Administration")){
                                    // BEED1y2sem();
                                }else if (Course.equals("Bachelor of Arts in Religious Education")){
                                    // BEED1y2sem();
                                }


                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                        break;
                    case "1st yr, 2nd sem":
                        databaseRef.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                String Course = snapshot.child("Course").getValue().toString();

                                if (Course.equals("Bachelor of Science in Computer Science")) {
                                    BSCS1y2sem();

                                }else if (Course.equals("Bachelor of Science in Computer Science")) {
                                    BEED1y2sem();

                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                        break;
                    case "2nd yr, 1st sem":
                        databaseRef.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                String Course = snapshot.child("Course").getValue().toString();

                                if (Course.equals("Bachelor of Science in Computer Science")) {
                                    BSCS2y1sem();

                                }else if (Course.equals("Bachelor of Science in Computer Science")) {
                                    BEED2y1sem();

                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                        break;
                    case "2nd yr, 2nd sem":
                        databaseRef.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                String Course = snapshot.child("Course").getValue().toString();

                                if (Course.equals("Bachelor of Science in Computer Science")) {
                                    BSCS2y2sem();

                                }else if (Course.equals("Bachelor of Science in Computer Science")) {
                                    BEED2y2sem();

                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                        break;
                    case "3rd yr, 1st sem":
                        databaseRef.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                String Course = snapshot.child("Course").getValue().toString();

                                if (Course.equals("Bachelor of Science in Computer Science")) {
                                    BSCS3y1sem();

                                }else if (Course.equals("Bachelor of Science in Computer Science")) {
                                    BEED3y1sem();

                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                        break;

                    case "3rd yr, 2nd sem":
                        databaseRef.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                String Course = snapshot.child("Course").getValue().toString();

                                if (Course.equals("Bachelor of Science in Computer Science")) {
                                    BSCS3y2sem();

                                }else if (Course.equals("Bachelor of Science in Computer Science")) {
                                    BEED3y2sem();

                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                        break;
                    case "4th yr, 1st sem":
                        databaseRef.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                String Course = snapshot.child("Course").getValue().toString();

                                if (Course.equals("Bachelor of Science in Computer Science")) {
                                    BSCS4y1sem();

                                }else if (Course.equals("Bachelor of Science in Computer Science")) {
                                    BEED4y1sem();

                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                        break;

                    case "4th yr, 2nd sem":
                        databaseRef.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                String Course = snapshot.child("Course").getValue().toString();

                                if (Course.equals("Bachelor of Science in Computer Science")) {
                                    BSCS4y2sem();

                                }else if (Course.equals("Bachelor of Science in Computer Science")) {
                                    BEED4y2sem();

                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                        break;




                }


            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


///////////////////////////////DISPLAY THE COURSE, NAME///////////////////////////////////////////////////////
        databaseRef.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String Name = snapshot.child("Name").getValue().toString();
                String Course = snapshot.child("Course").getValue().toString();
                String Email = snapshot.child("Email").getValue().toString(); // i hide the email to xml
                name.setText(Name);
                course.setText(Course);
                gmail.setText(Email);


            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        /////////// / ////////////////////DOWNLOAD THE FORM////////////////////////////////////

        ok = findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                dataObj.name = String.valueOf(name.getText());
                dataObj.mop = String.valueOf(downpayment.getText());
                dataObj.course = String.valueOf(course.getText());
                dataObj.gmail = String.valueOf(gmail.getText());
                dataObj.schoolyear = String.valueOf(schoolyear.getText());
                dataObj.yearAndsem = String.valueOf(semester.getSelectedItem());
                dataObj.section = String.valueOf(section.getSelectedItem());
                dataObj.address = String.valueOf(address.getText());
                dataObj.des1 = String.valueOf(des1.getText());
                dataObj.des2 = String.valueOf(des2.getText());
                dataObj.des3 = String.valueOf(des3.getText());
                dataObj.des4 = String.valueOf(des4.getText());
                dataObj.des5 = String.valueOf(des5.getText());
                dataObj.des6 = String.valueOf(des6.getText());
                dataObj.des7 = String.valueOf(des7.getText());
                dataObj.des8 = String.valueOf(des8.getText());
                dataObj.des9= String.valueOf(des9.getText());
                dataObj.des10 = String.valueOf(des10.getText());
                dataObj.s1=String.valueOf(s1.getText());
                dataObj.s2=String.valueOf(s2.getText());
                dataObj.s3=String.valueOf(s3.getText());
                dataObj.s4=String.valueOf(s4.getText());
                dataObj.s5=String.valueOf(s5.getText());
                dataObj.s6=String.valueOf(s6.getText());
                dataObj.s7=String.valueOf(s7.getText());
                dataObj.s8=String.valueOf(s8.getText());
                dataObj.s9=String.valueOf(s9.getText());
                dataObj.s10=String.valueOf(s10.getText());


                dataObj.date = new Date().getTime();
                dataObj.birthday = String.valueOf(birthday.getText());
                if (dataObj.schoolyear.isEmpty()) {
                    schoolyear.setError("School year is required!");
                    schoolyear.requestFocus();
                    return;

                }
                if (dataObj.mop.isEmpty()) {
                    downpayment.setError("School year is required!");
                    downpayment.requestFocus();
                    return;

                }
                if (dataObj.yearAndsem.equals("--Select your Course--")) {

                        TextView errorText = (TextView)semester.getSelectedView();
                        errorText.setError("Invalid!");
                        errorText.setTextColor(Color.RED);
                        return;
                    }
                if (dataObj.section.equals("--Select--")) {

                    TextView errorText = (TextView)section.getSelectedView();
                    errorText.setError("Invalid!");
                    errorText.setTextColor(Color.RED);
                    return;
                }

                print.child(userID).child("pre-enlist").child(String.valueOf(invoiceNo + 1)).setValue(dataObj);


                AlertDialog dlg=new AlertDialog.Builder(Pre_Enrollment.this)
                        .setTitle("Your Form is ready to Print")
                        .setMessage("File Location: Filemanager>Android>data>com.example.ccc...llmentapplication")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create();
                dlg.show();


                printPDF();

            }
        });

    }

    /////////////////////////////////////* SUBJECT EACH COURSE*////////////////////////////////
///////////////BSCS//////////////////
    private void BSCS1y1sem() {
        DatabaseReference databasecs = FirebaseDatabase.getInstance().getReference("course_curriculum/bscs/first_year");

        databasecs.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //Introduction to Computing
                String sub1 = snapshot.child("first_sem/subject1/code").getValue().toString();
                String Des1 = snapshot.child("first_sem/subject1/descriptive_title").getValue().toString();
                String Unit = snapshot.child("first_sem/subject1/units").getValue().toString();
                SC1.setText(sub1);
                des1.setText(Des1);
                units1.setText(Unit);

                String sub2 = snapshot.child("first_sem/subject2/code").getValue().toString();
                String Des2 = snapshot.child("first_sem/subject2/descriptive_title").getValue().toString();
                String Unit2 = snapshot.child("first_sem/subject2/units").getValue().toString();
                SC2.setText(sub2);
                des2.setText(Des2);
                units2.setText(Unit2);

                String sub3 = snapshot.child("first_sem/subject3/code").getValue().toString();
                String Des3 = snapshot.child("first_sem/subject3/descriptive_title").getValue().toString();
                String Unit3 = snapshot.child("first_sem/subject3/units").getValue().toString();
                SC3.setText(sub3);
                des3.setText(Des3);
                units3.setText(Unit3);

                String sub4 = snapshot.child("first_sem/subject4/code").getValue().toString();
                String Des4 = snapshot.child("first_sem/subject4/descriptive_title").getValue().toString();
                String Unit4 = snapshot.child("first_sem/subject4/units").getValue().toString();
                SC4.setText(sub4);
                des4.setText(Des4);
                units4.setText(Unit4);

                String sub5 = snapshot.child("first_sem/subject5/code").getValue().toString();
                String Des5 = snapshot.child("first_sem/subject5/descriptive_title").getValue().toString();
                String Unit5 = snapshot.child("first_sem/subject5/units").getValue().toString();
                SC5.setText(sub5);
                des5.setText(Des5);
                units5.setText(Unit5);

                String sub6 = snapshot.child("first_sem/subject6/code").getValue().toString();
                String Des6 = snapshot.child("first_sem/subject6/descriptive_title").getValue().toString();
                String Unit6 = snapshot.child("first_sem/subject6/units").getValue().toString();
                SC6.setText(sub6);
                des6.setText(Des6);
                units6.setText(Unit6);

                String sub7 = snapshot.child("first_sem/subject7/code").getValue().toString();
                String Des7 = snapshot.child("first_sem/subject7/descriptive_title").getValue().toString();
                String Unit7 = snapshot.child("first_sem/subject7/units").getValue().toString();
                SC7.setText(sub7);
                des7.setText(Des7);
                units7.setText(Unit7);

                String sub8 = snapshot.child("first_sem/subject8/code").getValue().toString();
                String Des8 = snapshot.child("first_sem/subject8/descriptive_title").getValue().toString();
                String Unit8 = snapshot.child("first_sem/subject8/units").getValue().toString();
                SC8.setText(sub8);
                des8.setText(Des8);
                units8.setText(Unit8);

                String sub9 = snapshot.child("first_sem/subject9/code").getValue().toString();
                String Des9 = snapshot.child("first_sem/subject9/descriptive_title").getValue().toString();
                String Unit9 = snapshot.child("first_sem/subject9/units").getValue().toString();
                SC9.setText(sub9);
                des9.setText(Des9);
                units9.setText(Unit9);

                String sub10 = snapshot.child("first_sem/subject10/code").getValue().toString();
                String Des10 = snapshot.child("first_sem/subject10/descriptive_title").getValue().toString();
                String Unit10 = snapshot.child("first_sem/subject10/units").getValue().toString();
                SC10.setText(sub10);
                des10.setText(Des10);
                units10.setText(Unit10);


            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void BSCS1y2sem() {

        DatabaseReference databasecs = FirebaseDatabase.getInstance().getReference("course_curriculum");

        databasecs.child("bscs").child("first_year").child("second_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //Introduction to Computing
                String sub1 = snapshot.child("subject_1/code").getValue().toString();
                String Des1 = snapshot.child("subject_1/descriptive_title").getValue().toString();
                String Unit = snapshot.child("subject_1/units").getValue().toString();
                SC1.setText(sub1);
                des1.setText(Des1);
                units1.setText(Unit);

                String sub2 = snapshot.child("subject_2/code").getValue().toString();
                String Des2 = snapshot.child("subject_2/descriptive_title").getValue().toString();
                String Unit2 = snapshot.child("subject_2/units").getValue().toString();
                SC2.setText(sub2);
                des2.setText(Des2);
                units2.setText(Unit2);

                String sub3 = snapshot.child("subject_3/code").getValue().toString();
                String Des3 = snapshot.child("subject_3/descriptive_title").getValue().toString();
                String Unit3 = snapshot.child("subject_3/units").getValue().toString();
                SC3.setText(sub3);
                des3.setText(Des3);
                units3.setText(Unit3);

                String sub4 = snapshot.child("subject_4/code").getValue().toString();
                String Des4 = snapshot.child("subject_4/descriptive_title").getValue().toString();
                String Unit4 = snapshot.child("subject_4/units").getValue().toString();
                SC4.setText(sub4);
                des4.setText(Des4);
                units4.setText(Unit4);

                String sub5 = snapshot.child("subject_5/code").getValue().toString();
                String Des5 = snapshot.child("subject_5/descriptive_title").getValue().toString();
                String Unit5 = snapshot.child("subject_5/units").getValue().toString();
                SC5.setText(sub5);
                des5.setText(Des5);
                units5.setText(Unit5);

                String sub6 = snapshot.child("subject_6/code").getValue().toString();
                String Des6 = snapshot.child("subject_6/descriptive_title").getValue().toString();
                String Unit6 = snapshot.child("subject_6/units").getValue().toString();
                SC6.setText(sub6);
                des6.setText(Des6);
                units6.setText(Unit6);

                String sub7 = snapshot.child("subject_7/code").getValue().toString();
                String Des7 = snapshot.child("subject_7/descriptive_title").getValue().toString();
                String Unit7 = snapshot.child("subject_7/units").getValue().toString();
                SC7.setText(sub7);
                des7.setText(Des7);
                units7.setText(Unit7);

                String sub8 = snapshot.child("subject_8/code").getValue().toString();
                String Des8 = snapshot.child("subject_8/descriptive_title").getValue().toString();
                String Unit8 = snapshot.child("subject_8/units").getValue().toString();
                SC8.setText(sub8);
                des8.setText(Des8);
                units8.setText(Unit8);

                String sub9 = snapshot.child("subject_9/code").getValue().toString();
                String Des9 = snapshot.child("subject_9/descriptive_title").getValue().toString();
                String Unit9 = snapshot.child("subject_9/units").getValue().toString();
                SC9.setText(sub9);
                des9.setText(Des9);
                units9.setText(Unit9);

                String sub10 = snapshot.child("subject_10/code").getValue().toString();
                String Des10 = snapshot.child("subject_10/descriptive_title").getValue().toString();
                String Unit10 = snapshot.child("subject_10/units").getValue().toString();
                SC10.setText(sub10);
                des10.setText(Des10);
                units10.setText(Unit10);


            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void BSCS2y1sem() {
        DatabaseReference databasecs = FirebaseDatabase.getInstance().getReference("course_curriculum/bscs/second_year");

        databasecs.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //Introduction to Computing
                String sub1 = snapshot.child("first_sem/subject_1/code").getValue().toString();
                String Des1 = snapshot.child("first_sem/subject_1/descriptive_title").getValue().toString();
                String Unit = snapshot.child("first_sem/subject_1/units").getValue().toString();
                SC1.setText(sub1);
                des1.setText(Des1);
                units1.setText(Unit);

                String sub2 = snapshot.child("first_sem/subject_2/code").getValue().toString();
                String Des2 = snapshot.child("first_sem/subject_2/descriptive_title").getValue().toString();
                String Unit2 = snapshot.child("first_sem/subject_2/units").getValue().toString();
                SC2.setText(sub2);
                des2.setText(Des2);
                units2.setText(Unit2);

                String sub3 = snapshot.child("first_sem/subject_3/code").getValue().toString();
                String Des3 = snapshot.child("first_sem/subject_3/descriptive_title").getValue().toString();
                String Unit3 = snapshot.child("first_sem/subject_3/units").getValue().toString();
                SC3.setText(sub3);
                des3.setText(Des3);
                units3.setText(Unit3);

                String sub4 = snapshot.child("first_sem/subject_4/code").getValue().toString();
                String Des4 = snapshot.child("first_sem/subject_4/descriptive_title").getValue().toString();
                String Unit4 = snapshot.child("first_sem/subject_4/units").getValue().toString();
                SC4.setText(sub4);
                des4.setText(Des4);
                units4.setText(Unit4);

                String sub5 = snapshot.child("first_sem/subject_5/code").getValue().toString();
                String Des5 = snapshot.child("first_sem/subject_5/descriptive_title").getValue().toString();
                String Unit5 = snapshot.child("first_sem/subject_5/units").getValue().toString();
                SC5.setText(sub5);
                des5.setText(Des5);
                units5.setText(Unit5);

                String sub6 = snapshot.child("first_sem/subject_6/code").getValue().toString();
                String Des6 = snapshot.child("first_sem/subject_6/descriptive_title").getValue().toString();
                String Unit6 = snapshot.child("first_sem/subject_6/units").getValue().toString();
                SC6.setText(sub6);
                des6.setText(Des6);
                units6.setText(Unit6);

                String sub7 = snapshot.child("first_sem/subject_7/code").getValue().toString();
                String Des7 = snapshot.child("first_sem/subject_7/descriptive_title").getValue().toString();
                String Unit7 = snapshot.child("first_sem/subject_7/units").getValue().toString();
                SC7.setText(sub7);
                des7.setText(Des7);
                units7.setText(Unit7);

                String sub8 = snapshot.child("first_sem/subject_8/code").getValue().toString();
                String Des8 = snapshot.child("first_sem/subject_8/descriptive_title").getValue().toString();
                String Unit8 = snapshot.child("first_sem/subject_8/units").getValue().toString();
                SC8.setText(sub8);
                des8.setText(Des8);
                units8.setText(Unit8);

                String sub9 = snapshot.child("first_sem/subject_9/code").getValue().toString();
                String Des9 = snapshot.child("first_sem/subject_9/descriptive_title").getValue().toString();
                String Unit9 = snapshot.child("first_sem/subject_9/units").getValue().toString();
                SC9.setText(sub9);
                des9.setText(Des9);
                units9.setText(Unit9);

                String sub10 = snapshot.child("first_sem/subject_10/code").getValue().toString();
                String Des10 = snapshot.child("first_sem/subject_10/descriptive_title").getValue().toString();
                String Unit10 = snapshot.child("first_sem/subject_10/units").getValue().toString();
                SC10.setText(sub10);
                des10.setText(Des10);
                units10.setText(Unit10);


            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void BSCS2y2sem() {

        DatabaseReference databasecs = FirebaseDatabase.getInstance().getReference("course_curriculum");

        databasecs.child("bscs").child("second_year").child("second_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //Introduction to Computing
                String sub1 = snapshot.child("subject_1/code").getValue().toString();
                String Des1 = snapshot.child("subject_1/descriptive_title").getValue().toString();
                String Unit = snapshot.child("subject_1/units").getValue().toString();
                SC1.setText(sub1);
                des1.setText(Des1);
                units1.setText(Unit);

                String sub2 = snapshot.child("subject_2/code").getValue().toString();
                String Des2 = snapshot.child("subject_2/descriptive_title").getValue().toString();
                String Unit2 = snapshot.child("subject_2/units").getValue().toString();
                SC2.setText(sub2);
                des2.setText(Des2);
                units2.setText(Unit2);

                String sub3 = snapshot.child("subject_3/code").getValue().toString();
                String Des3 = snapshot.child("subject_3/descriptive_title").getValue().toString();
                String Unit3 = snapshot.child("subject_3/units").getValue().toString();
                SC3.setText(sub3);
                des3.setText(Des3);
                units3.setText(Unit3);

                String sub4 = snapshot.child("subject_4/code").getValue().toString();
                String Des4 = snapshot.child("subject_4/descriptive_title").getValue().toString();
                String Unit4 = snapshot.child("subject_4/units").getValue().toString();
                SC4.setText(sub4);
                des4.setText(Des4);
                units4.setText(Unit4);

                String sub5 = snapshot.child("subject_5/code").getValue().toString();
                String Des5 = snapshot.child("subject_5/descriptive_title").getValue().toString();
                String Unit5 = snapshot.child("subject_5/units").getValue().toString();
                SC5.setText(sub5);
                des5.setText(Des5);
                units5.setText(Unit5);

                String sub6 = snapshot.child("subject_6/code").getValue().toString();
                String Des6 = snapshot.child("subject_6/descriptive_title").getValue().toString();
                String Unit6 = snapshot.child("subject_6/units").getValue().toString();
                SC6.setText(sub6);
                des6.setText(Des6);
                units6.setText(Unit6);

                String sub7 = snapshot.child("subject_7/code").getValue().toString();
                String Des7 = snapshot.child("subject_7/descriptive_title").getValue().toString();
                String Unit7 = snapshot.child("subject_7/units").getValue().toString();
                SC7.setText(sub7);
                des7.setText(Des7);
                units7.setText(Unit7);

                String sub8 = snapshot.child("subject_8/code").getValue().toString();
                String Des8 = snapshot.child("subject_8/descriptive_title").getValue().toString();
                String Unit8 = snapshot.child("subject_8/units").getValue().toString();
                SC8.setText(sub8);
                des8.setText(Des8);
                units8.setText(Unit8);

                String sub9 = snapshot.child("subject_9/code").getValue().toString();
                String Des9 = snapshot.child("subject_9/descriptive_title").getValue().toString();
                String Unit9 = snapshot.child("subject_9/units").getValue().toString();
                SC9.setText(sub9);
                des9.setText(Des9);
                units9.setText(Unit9);

                String sub10 = snapshot.child("subject_10/code").getValue().toString();
                String Des10 = snapshot.child("subject_10/descriptive_title").getValue().toString();
                String Unit10 = snapshot.child("subject_10/units").getValue().toString();
                SC10.setText(sub10);
                des10.setText(Des10);
                units10.setText(Unit10);


            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void BSCS3y1sem() {

        DatabaseReference databasecs = FirebaseDatabase.getInstance().getReference("course_curriculum");

        databasecs.child("bscs").child("third_year").child("first_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //Introduction to Computing
                String sub1 = snapshot.child("subject_1/code").getValue().toString();
                String Des1 = snapshot.child("subject_1/descriptive_title").getValue().toString();
                String Unit = snapshot.child("subject_1/units").getValue().toString();
                SC1.setText(sub1);
                des1.setText(Des1);
                units1.setText(Unit);

                String sub2 = snapshot.child("subject_2/code").getValue().toString();
                String Des2 = snapshot.child("subject_2/descriptive_title").getValue().toString();
                String Unit2 = snapshot.child("subject_2/units").getValue().toString();
                SC2.setText(sub2);
                des2.setText(Des2);
                units2.setText(Unit2);

                String sub3 = snapshot.child("subject_3/code").getValue().toString();
                String Des3 = snapshot.child("subject_3/descriptive_title").getValue().toString();
                String Unit3 = snapshot.child("subject_3/units").getValue().toString();
                SC3.setText(sub3);
                des3.setText(Des3);
                units3.setText(Unit3);

                String sub4 = snapshot.child("subject_4/code").getValue().toString();
                String Des4 = snapshot.child("subject_4/descriptive_title").getValue().toString();
                String Unit4 = snapshot.child("subject_4/units").getValue().toString();
                SC4.setText(sub4);
                des4.setText(Des4);
                units4.setText(Unit4);

                String sub5 = snapshot.child("subject_5/code").getValue().toString();
                String Des5 = snapshot.child("subject_5/descriptive_title").getValue().toString();
                String Unit5 = snapshot.child("subject_5/units").getValue().toString();
                SC5.setText(sub5);
                des5.setText(Des5);
                units5.setText(Unit5);

                String sub6 = snapshot.child("subject_6/code").getValue().toString();
                String Des6 = snapshot.child("subject_6/descriptive_title").getValue().toString();
                String Unit6 = snapshot.child("subject_6/units").getValue().toString();
                SC6.setText(sub6);
                des6.setText(Des6);
                units6.setText(Unit6);

                String sub7 = snapshot.child("subject_7/code").getValue().toString();
                String Des7 = snapshot.child("subject_7/descriptive_title").getValue().toString();
                String Unit7 = snapshot.child("subject_7/units").getValue().toString();
                SC7.setText(sub7);
                des7.setText(Des7);
                units7.setText(Unit7);

                String sub8 = snapshot.child("subject_8/code").getValue().toString();
                String Des8 = snapshot.child("subject_8/descriptive_title").getValue().toString();
                String Unit8 = snapshot.child("subject_8/units").getValue().toString();
                SC8.setText(sub8);
                des8.setText(Des8);
                units8.setText(Unit8);

                String sub9 = snapshot.child("subject_9/code").getValue().toString();
                String Des9 = snapshot.child("subject_9/descriptive_title").getValue().toString();
                String Unit9 = snapshot.child("subject_9/units").getValue().toString();
                SC9.setText(sub9);
                des9.setText(Des9);
                units9.setText(Unit9);

                String sub10 = snapshot.child("subject_10/code").getValue().toString();
                String Des10 = snapshot.child("subject_10/descriptive_title").getValue().toString();
                String Unit10 = snapshot.child("subject_10/units").getValue().toString();
                SC10.setText(sub10);
                des10.setText(Des10);
                units10.setText(Unit10);


            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void BSCS3y2sem() {

        DatabaseReference databasecs = FirebaseDatabase.getInstance().getReference("course_curriculum");

        databasecs.child("bscs").child("third_year").child("second_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //Introduction to Computing
                String sub1 = snapshot.child("subject_1/code").getValue().toString();
                String Des1 = snapshot.child("subject_1/descriptive_title").getValue().toString();
                String Unit = snapshot.child("subject_1/units").getValue().toString();
                SC1.setText(sub1);
                des1.setText(Des1);
                units1.setText(Unit);

                String sub2 = snapshot.child("subject_2/code").getValue().toString();
                String Des2 = snapshot.child("subject_2/descriptive_title").getValue().toString();
                String Unit2 = snapshot.child("subject_2/units").getValue().toString();
                SC2.setText(sub2);
                des2.setText(Des2);
                units2.setText(Unit2);

                String sub3 = snapshot.child("subject_3/code").getValue().toString();
                String Des3 = snapshot.child("subject_3/descriptive_title").getValue().toString();
                String Unit3 = snapshot.child("subject_3/units").getValue().toString();
                SC3.setText(sub3);
                des3.setText(Des3);
                units3.setText(Unit3);

                String sub4 = snapshot.child("subject_4/code").getValue().toString();
                String Des4 = snapshot.child("subject_4/descriptive_title").getValue().toString();
                String Unit4 = snapshot.child("subject_4/units").getValue().toString();
                SC4.setText(sub4);
                des4.setText(Des4);
                units4.setText(Unit4);

                String sub5 = snapshot.child("subject_5/code").getValue().toString();
                String Des5 = snapshot.child("subject_5/descriptive_title").getValue().toString();
                String Unit5 = snapshot.child("subject_5/units").getValue().toString();
                SC5.setText(sub5);
                des5.setText(Des5);
                units5.setText(Unit5);

                String sub6 = snapshot.child("subject_6/code").getValue().toString();
                String Des6 = snapshot.child("subject_6/descriptive_title").getValue().toString();
                String Unit6 = snapshot.child("subject_6/units").getValue().toString();
                SC6.setText(sub6);
                des6.setText(Des6);
                units6.setText(Unit6);

                String sub7 = snapshot.child("subject_7/code").getValue().toString();
                String Des7 = snapshot.child("subject_7/descriptive_title").getValue().toString();
                String Unit7 = snapshot.child("subject_7/units").getValue().toString();
                SC7.setText(sub7);
                des7.setText(Des7);
                units7.setText(Unit7);

                String sub8 = snapshot.child("subject_8/code").getValue().toString();
                String Des8 = snapshot.child("subject_8/descriptive_title").getValue().toString();
                String Unit8 = snapshot.child("subject_8/units").getValue().toString();
                SC8.setText(sub8);
                des8.setText(Des8);
                units8.setText(Unit8);

                String sub9 = snapshot.child("subject_9/code").getValue().toString();
                String Des9 = snapshot.child("subject_9/descriptive_title").getValue().toString();
                String Unit9 = snapshot.child("subject_9/units").getValue().toString();
                SC9.setText(sub9);
                des9.setText(Des9);
                units9.setText(Unit9);

                String sub10 = snapshot.child("subject_10/code").getValue().toString();
                String Des10 = snapshot.child("subject_10/descriptive_title").getValue().toString();
                String Unit10 = snapshot.child("subject_10/units").getValue().toString();
                SC10.setText(sub10);
                des10.setText(Des10);
                units10.setText(Unit10);


            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void BSCS4y1sem() {

        DatabaseReference databasecs = FirebaseDatabase.getInstance().getReference("course_curriculum");

        databasecs.child("bscs").child("fourth_year").child("first_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //Introduction to Computing
                String sub1 = snapshot.child("subject_1/code").getValue().toString();
                String Des1 = snapshot.child("subject_1/descriptive_title").getValue().toString();
                String Unit = snapshot.child("subject_1/units").getValue().toString();
                SC1.setText(sub1);
                des1.setText(Des1);
                units1.setText(Unit);

                String sub2 = snapshot.child("subject_2/code").getValue().toString();
                String Des2 = snapshot.child("subject_2/descriptive_title").getValue().toString();
                String Unit2 = snapshot.child("subject_2/units").getValue().toString();
                SC2.setText(sub2);
                des2.setText(Des2);
                units2.setText(Unit2);

                String sub3 = snapshot.child("subject_3/code").getValue().toString();
                String Des3 = snapshot.child("subject_3/descriptive_title").getValue().toString();
                String Unit3 = snapshot.child("subject_3/units").getValue().toString();
                SC3.setText(sub3);
                des3.setText(Des3);
                units3.setText(Unit3);

                String sub4 = snapshot.child("subject_4/code").getValue().toString();
                String Des4 = snapshot.child("subject_4/descriptive_title").getValue().toString();
                String Unit4 = snapshot.child("subject_4/units").getValue().toString();
                SC4.setText(sub4);
                des4.setText(Des4);
                units4.setText(Unit4);

                String sub5 = snapshot.child("subject_5/code").getValue().toString();
                String Des5 = snapshot.child("subject_5/descriptive_title").getValue().toString();
                String Unit5 = snapshot.child("subject_5/units").getValue().toString();
                SC5.setText(sub5);
                des5.setText(Des5);
                units5.setText(Unit5);

                String sub6 = snapshot.child("subject_6/code").getValue().toString();
                String Des6 = snapshot.child("subject_6/descriptive_title").getValue().toString();
                String Unit6 = snapshot.child("subject_6/units").getValue().toString();
                SC6.setText(sub6);
                des6.setText(Des6);
                units6.setText(Unit6);

                String sub7 = snapshot.child("subject_7/code").getValue().toString();
                String Des7 = snapshot.child("subject_7/descriptive_title").getValue().toString();
                String Unit7 = snapshot.child("subject_7/units").getValue().toString();
                SC7.setText(sub7);
                des7.setText(Des7);
                units7.setText(Unit7);

                String sub8 = snapshot.child("subject_8/code").getValue().toString();
                String Des8 = snapshot.child("subject_8/descriptive_title").getValue().toString();
                String Unit8 = snapshot.child("subject_8/units").getValue().toString();
                SC8.setText(sub8);
                des8.setText(Des8);
                units8.setText(Unit8);

                String sub9 = snapshot.child("subject_9/code").getValue().toString();
                String Des9 = snapshot.child("subject_9/descriptive_title").getValue().toString();
                String Unit9 = snapshot.child("subject_9/units").getValue().toString();
                SC9.setText(sub9);
                des9.setText(Des9);
                units9.setText(Unit9);

                String sub10 = snapshot.child("subject_10/code").getValue().toString();
                String Des10 = snapshot.child("subject_10/descriptive_title").getValue().toString();
                String Unit10 = snapshot.child("subject_10/units").getValue().toString();
                SC10.setText(sub10);
                des10.setText(Des10);
                units10.setText(Unit10);


            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void BSCS4y2sem() {

        DatabaseReference databasecs = FirebaseDatabase.getInstance().getReference("course_curriculum");

        databasecs.child("bscs").child("fourth_year").child("second_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //Introduction to Computing
                String sub1 = snapshot.child("subject_1/code").getValue().toString();
                String Des1 = snapshot.child("subject_1/descriptive_title").getValue().toString();
                String Unit = snapshot.child("subject_1/units").getValue().toString();
                SC1.setText(sub1);
                des1.setText(Des1);
                units1.setText(Unit);

                String sub2 = snapshot.child("subject_2/code").getValue().toString();
                String Des2 = snapshot.child("subject_2/descriptive_title").getValue().toString();
                String Unit2 = snapshot.child("subject_2/units").getValue().toString();
                SC2.setText(sub2);
                des2.setText(Des2);
                units2.setText(Unit2);

                String sub3 = snapshot.child("subject_3/code").getValue().toString();
                String Des3 = snapshot.child("subject_3/descriptive_title").getValue().toString();
                String Unit3 = snapshot.child("subject_3/units").getValue().toString();
                SC3.setText(sub3);
                des3.setText(Des3);
                units3.setText(Unit3);

                String sub4 = snapshot.child("subject_4/code").getValue().toString();
                String Des4 = snapshot.child("subject_4/descriptive_title").getValue().toString();
                String Unit4 = snapshot.child("subject_4/units").getValue().toString();
                SC4.setText(sub4);
                des4.setText(Des4);
                units4.setText(Unit4);

                String sub5 = snapshot.child("subject_5/code").getValue().toString();
                String Des5 = snapshot.child("subject_5/descriptive_title").getValue().toString();
                String Unit5 = snapshot.child("subject_5/units").getValue().toString();
                SC5.setText(sub5);
                des5.setText(Des5);
                units5.setText(Unit5);

                String sub6 = snapshot.child("subject_6/code").getValue().toString();
                String Des6 = snapshot.child("subject_6/descriptive_title").getValue().toString();
                String Unit6 = snapshot.child("subject_6/units").getValue().toString();
                SC6.setText(sub6);
                des6.setText(Des6);
                units6.setText(Unit6);

                String sub7 = snapshot.child("subject_7/code").getValue().toString();
                String Des7 = snapshot.child("subject_7/descriptive_title").getValue().toString();
                String Unit7 = snapshot.child("subject_7/units").getValue().toString();
                SC7.setText(sub7);
                des7.setText(Des7);
                units7.setText(Unit7);

                String sub8 = snapshot.child("subject_8/code").getValue().toString();
                String Des8 = snapshot.child("subject_8/descriptive_title").getValue().toString();
                String Unit8 = snapshot.child("subject_8/units").getValue().toString();
                SC8.setText(sub8);
                des8.setText(Des8);
                units8.setText(Unit8);

                String sub9 = snapshot.child("subject_9/code").getValue().toString();
                String Des9 = snapshot.child("subject_9/descriptive_title").getValue().toString();
                String Unit9 = snapshot.child("subject_9/units").getValue().toString();
                SC9.setText(sub9);
                des9.setText(Des9);
                units9.setText(Unit9);

                String sub10 = snapshot.child("subject_10/code").getValue().toString();
                String Des10 = snapshot.child("subject_10/descriptive_title").getValue().toString();
                String Unit10 = snapshot.child("subject_10/units").getValue().toString();
                SC10.setText(sub10);
                des10.setText(Des10);
                units10.setText(Unit10);


            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    ///////////////BEED//////////////////
    private void BEED1y1sem() {
        DatabaseReference databasecs = FirebaseDatabase.getInstance().getReference("course_curriculum/beed/first_year");

        databasecs.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                String sub1 = snapshot.child("first_sem/subject1/code").getValue().toString();
                String Des1 = snapshot.child("first_sem/subject1/descriptive_title").getValue().toString();
                String Unit = snapshot.child("first_sem/subject1/units").getValue().toString();
                SC1.setText(sub1);
                des1.setText(Des1);
                units1.setText(Unit);

                String sub2 = snapshot.child("first_sem/subject2/code").getValue().toString();
                String Des2 = snapshot.child("first_sem/subject2/descriptive_title").getValue().toString();
                String Unit2 = snapshot.child("first_sem/subject2/units").getValue().toString();
                SC2.setText(sub2);
                des2.setText(Des2);
                units2.setText(Unit2);

                String sub3 = snapshot.child("first_sem/subject3/code").getValue().toString();
                String Des3 = snapshot.child("first_sem/subject3/descriptive_title").getValue().toString();
                String Unit3 = snapshot.child("first_sem/subject3/units").getValue().toString();
                SC3.setText(sub3);
                des3.setText(Des3);
                units3.setText(Unit3);

                String sub4 = snapshot.child("first_sem/subject4/code").getValue().toString();
                String Des4 = snapshot.child("first_sem/subject4/descriptive_title").getValue().toString();
                String Unit4 = snapshot.child("first_sem/subject4/units").getValue().toString();
                SC4.setText(sub4);
                des4.setText(Des4);
                units4.setText(Unit4);

                String sub5 = snapshot.child("first_sem/subject5/code").getValue().toString();
                String Des5 = snapshot.child("first_sem/subject5/descriptive_title").getValue().toString();
                String Unit5 = snapshot.child("first_sem/subject5/units").getValue().toString();
                SC5.setText(sub5);
                des5.setText(Des5);
                units5.setText(Unit5);

                String sub6 = snapshot.child("first_sem/subject6/code").getValue().toString();
                String Des6 = snapshot.child("first_sem/subject6/descriptive_title").getValue().toString();
                String Unit6 = snapshot.child("first_sem/subject6/units").getValue().toString();
                SC6.setText(sub6);
                des6.setText(Des6);
                units6.setText(Unit6);

                String sub7 = snapshot.child("first_sem/subject7/code").getValue().toString();
                String Des7 = snapshot.child("first_sem/subject7/descriptive_title").getValue().toString();
                String Unit7 = snapshot.child("first_sem/subject7/units").getValue().toString();
                SC7.setText(sub7);
                des7.setText(Des7);
                units7.setText(Unit7);

                String sub8 = snapshot.child("first_sem/subject8/code").getValue().toString();
                String Des8 = snapshot.child("first_sem/subject8/descriptive_title").getValue().toString();
                String Unit8 = snapshot.child("first_sem/subject8/units").getValue().toString();
                SC8.setText(sub8);
                des8.setText(Des8);
                units8.setText(Unit8);

                String sub9 = snapshot.child("first_sem/subject9/code").getValue().toString();
                String Des9 = snapshot.child("first_sem/subject9/descriptive_title").getValue().toString();
                String Unit9 = snapshot.child("first_sem/subject9/units").getValue().toString();
                SC9.setText(sub9);
                des9.setText(Des9);
                units9.setText(Unit9);

                String sub10 = snapshot.child("first_sem/subject10/code").getValue().toString();
                String Des10 = snapshot.child("first_sem/subject10/descriptive_title").getValue().toString();
                String Unit10 = snapshot.child("first_sem/subject10/units").getValue().toString();
                SC10.setText(sub10);
                des10.setText(Des10);
                units10.setText(Unit10);


            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void BEED1y2sem() {

        DatabaseReference databasecs = FirebaseDatabase.getInstance().getReference("course_curriculum");

        databasecs.child("beed").child("first_year").child("second_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //Introduction to Computing
                String sub1 = snapshot.child("subject1/code").getValue().toString();
                String Des1 = snapshot.child("subject1/descriptive_title").getValue().toString();
                String Unit = snapshot.child("subject1/units").getValue().toString();
                SC1.setText(sub1);
                des1.setText(Des1);
                units1.setText(Unit);

                String sub2 = snapshot.child("subject2/code").getValue().toString();
                String Des2 = snapshot.child("subject2/descriptive_title").getValue().toString();
                String Unit2 = snapshot.child("subject2/units").getValue().toString();
                SC2.setText(sub2);
                des2.setText(Des2);
                units2.setText(Unit2);

                String sub3 = snapshot.child("subject3/code").getValue().toString();
                String Des3 = snapshot.child("subject3/descriptive_title").getValue().toString();
                String Unit3 = snapshot.child("subject3/units").getValue().toString();
                SC3.setText(sub3);
                des3.setText(Des3);
                units3.setText(Unit3);

                String sub4 = snapshot.child("subject4/code").getValue().toString();
                String Des4 = snapshot.child("subject4/descriptive_title").getValue().toString();
                String Unit4 = snapshot.child("subject4/units").getValue().toString();
                SC4.setText(sub4);
                des4.setText(Des4);
                units4.setText(Unit4);

                String sub5 = snapshot.child("subject5/code").getValue().toString();
                String Des5 = snapshot.child("subject5/descriptive_title").getValue().toString();
                String Unit5 = snapshot.child("subject5/units").getValue().toString();
                SC5.setText(sub5);
                des5.setText(Des5);
                units5.setText(Unit5);

                String sub6 = snapshot.child("subject6/code").getValue().toString();
                String Des6 = snapshot.child("subject6/descriptive_title").getValue().toString();
                String Unit6 = snapshot.child("subject6/units").getValue().toString();
                SC6.setText(sub6);
                des6.setText(Des6);
                units6.setText(Unit6);

                String sub7 = snapshot.child("subject7/code").getValue().toString();
                String Des7 = snapshot.child("subject7/descriptive_title").getValue().toString();
                String Unit7 = snapshot.child("subject7/units").getValue().toString();
                SC7.setText(sub7);
                des7.setText(Des7);
                units7.setText(Unit7);

                String sub8 = snapshot.child("subject8/code").getValue().toString();
                String Des8 = snapshot.child("subject8/descriptive_title").getValue().toString();
                String Unit8 = snapshot.child("subject8/units").getValue().toString();
                SC8.setText(sub8);
                des8.setText(Des8);
                units8.setText(Unit8);

                String sub9 = snapshot.child("subject9/code").getValue().toString();
                String Des9 = snapshot.child("subject9/descriptive_title").getValue().toString();
                String Unit9 = snapshot.child("subject9/units").getValue().toString();
                SC9.setText(sub9);
                des9.setText(Des9);
                units9.setText(Unit9);

                String sub10 = snapshot.child("subject10/code").getValue().toString();
                String Des10 = snapshot.child("subject10/descriptive_title").getValue().toString();
                String Unit10 = snapshot.child("subject10/units").getValue().toString();
                SC10.setText(sub10);
                des10.setText(Des10);
                units10.setText(Unit10);


            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void BEED2y1sem() {
        DatabaseReference databasecs = FirebaseDatabase.getInstance().getReference("course_curriculum/beed/second_year");

        databasecs.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //Introduction to Computing
                String sub1 = snapshot.child("first_sem/subject1/code").getValue().toString();
                String Des1 = snapshot.child("first_sem/subject1/descriptive_title").getValue().toString();
                String Unit = snapshot.child("first_sem/subject1/units").getValue().toString();
                SC1.setText(sub1);
                des1.setText(Des1);
                units1.setText(Unit);

                String sub2 = snapshot.child("first_sem/subject2/code").getValue().toString();
                String Des2 = snapshot.child("first_sem/subject2/descriptive_title").getValue().toString();
                String Unit2 = snapshot.child("first_sem/subject2/units").getValue().toString();
                SC2.setText(sub2);
                des2.setText(Des2);
                units2.setText(Unit2);

                String sub3 = snapshot.child("first_sem/subject3/code").getValue().toString();
                String Des3 = snapshot.child("first_sem/subject3/descriptive_title").getValue().toString();
                String Unit3 = snapshot.child("first_sem/subject3/units").getValue().toString();
                SC3.setText(sub3);
                des3.setText(Des3);
                units3.setText(Unit3);

                String sub4 = snapshot.child("first_sem/subject4/code").getValue().toString();
                String Des4 = snapshot.child("first_sem/subject4/descriptive_title").getValue().toString();
                String Unit4 = snapshot.child("first_sem/subject4/units").getValue().toString();
                SC4.setText(sub4);
                des4.setText(Des4);
                units4.setText(Unit4);

                String sub5 = snapshot.child("first_sem/subject5/code").getValue().toString();
                String Des5 = snapshot.child("first_sem/subject5/descriptive_title").getValue().toString();
                String Unit5 = snapshot.child("first_sem/subject5/units").getValue().toString();
                SC5.setText(sub5);
                des5.setText(Des5);
                units5.setText(Unit5);

                String sub6 = snapshot.child("first_sem/subject6/code").getValue().toString();
                String Des6 = snapshot.child("first_sem/subject6/descriptive_title").getValue().toString();
                String Unit6 = snapshot.child("first_sem/subject6/units").getValue().toString();
                SC6.setText(sub6);
                des6.setText(Des6);
                units6.setText(Unit6);

                String sub7 = snapshot.child("first_sem/subject7/code").getValue().toString();
                String Des7 = snapshot.child("first_sem/subject7/descriptive_title").getValue().toString();
                String Unit7 = snapshot.child("first_sem/subject7/units").getValue().toString();
                SC7.setText(sub7);
                des7.setText(Des7);
                units7.setText(Unit7);

                String sub8 = snapshot.child("first_sem/subject8/code").getValue().toString();
                String Des8 = snapshot.child("first_sem/subject8/descriptive_title").getValue().toString();
                String Unit8 = snapshot.child("first_sem/subject8/units").getValue().toString();
                SC8.setText(sub8);
                des8.setText(Des8);
                units8.setText(Unit8);

                String sub9 = snapshot.child("first_sem/subject_9/code").getValue().toString();
                String Des9 = snapshot.child("first_sem/subject_9/descriptive_title").getValue().toString();
                String Unit9 = snapshot.child("first_sem/subject_9/units").getValue().toString();
                SC9.setText(sub9);
                des9.setText(Des9);
                units9.setText(Unit9);

                String sub10 = snapshot.child("first_sem/subject_10/code").getValue().toString();
                String Des10 = snapshot.child("first_sem/subject_10/descriptive_title").getValue().toString();
                String Unit10 = snapshot.child("first_sem/subject_10/units").getValue().toString();
                SC10.setText(sub10);
                des10.setText(Des10);
                units10.setText(Unit10);


            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void BEED2y2sem() {

        DatabaseReference databasecs = FirebaseDatabase.getInstance().getReference("course_curriculum");

        databasecs.child("beed").child("second_year").child("second_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject1/code").getValue().toString();
                String Des1 = snapshot.child("subject1/descriptive_title").getValue().toString();
                String Unit = snapshot.child("subject1/units").getValue().toString();
                SC1.setText(sub1);
                des1.setText(Des1);
                units1.setText(Unit);

                String sub2 = snapshot.child("subject2/code").getValue().toString();
                String Des2 = snapshot.child("subject2/descriptive_title").getValue().toString();
                String Unit2 = snapshot.child("subject2/units").getValue().toString();
                SC2.setText(sub2);
                des2.setText(Des2);
                units2.setText(Unit2);

                String sub3 = snapshot.child("subject3/code").getValue().toString();
                String Des3 = snapshot.child("subject3/descriptive_title").getValue().toString();
                String Unit3 = snapshot.child("subject3/units").getValue().toString();
                SC3.setText(sub3);
                des3.setText(Des3);
                units3.setText(Unit3);

                String sub4 = snapshot.child("subject4/code").getValue().toString();
                String Des4 = snapshot.child("subject4/descriptive_title").getValue().toString();
                String Unit4 = snapshot.child("subject4/units").getValue().toString();
                SC4.setText(sub4);
                des4.setText(Des4);
                units4.setText(Unit4);

                String sub5 = snapshot.child("subject5/code").getValue().toString();
                String Des5 = snapshot.child("subject5/descriptive_title").getValue().toString();
                String Unit5 = snapshot.child("subject5/units").getValue().toString();
                SC5.setText(sub5);
                des5.setText(Des5);
                units5.setText(Unit5);

                String sub6 = snapshot.child("subject6/code").getValue().toString();
                String Des6 = snapshot.child("subject6/descriptive_title").getValue().toString();
                String Unit6 = snapshot.child("subject6/units").getValue().toString();
                SC6.setText(sub6);
                des6.setText(Des6);
                units6.setText(Unit6);

                String sub7 = snapshot.child("subject7/code").getValue().toString();
                String Des7 = snapshot.child("subject7/descriptive_title").getValue().toString();
                String Unit7 = snapshot.child("subject7/units").getValue().toString();
                SC7.setText(sub7);
                des7.setText(Des7);
                units7.setText(Unit7);

                String sub8 = snapshot.child("subject8/code").getValue().toString();
                String Des8 = snapshot.child("subject8/descriptive_title").getValue().toString();
                String Unit8 = snapshot.child("subject8/units").getValue().toString();
                SC8.setText(sub8);
                des8.setText(Des8);
                units8.setText(Unit8);

                String sub9 = snapshot.child("subject9/code").getValue().toString();
                String Des9 = snapshot.child("subject9/descriptive_title").getValue().toString();
                String Unit9 = snapshot.child("subject9/units").getValue().toString();
                SC9.setText(sub9);
                des9.setText(Des9);
                units9.setText(Unit9);

                String sub10 = snapshot.child("subject10/code").getValue().toString();
                String Des10 = snapshot.child("subject10/descriptive_title").getValue().toString();
                String Unit10 = snapshot.child("subject10/units").getValue().toString();
                SC10.setText(sub10);
                des10.setText(Des10);
                units10.setText(Unit10);


            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void BEED3y1sem() {

        DatabaseReference databasecs = FirebaseDatabase.getInstance().getReference("course_curriculum");

        databasecs.child("beed").child("third_year").child("first_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //Introduction to Computing
                String sub1 = snapshot.child("subject1/code").getValue().toString();
                String Des1 = snapshot.child("subject1/descriptive_title").getValue().toString();
                String Unit = snapshot.child("subject1/units").getValue().toString();
                SC1.setText(sub1);
                des1.setText(Des1);
                units1.setText(Unit);

                String sub2 = snapshot.child("subject2/code").getValue().toString();
                String Des2 = snapshot.child("subject2/descriptive_title").getValue().toString();
                String Unit2 = snapshot.child("subject2/units").getValue().toString();
                SC2.setText(sub2);
                des2.setText(Des2);
                units2.setText(Unit2);

                String sub3 = snapshot.child("subject3/code").getValue().toString();
                String Des3 = snapshot.child("subject3/descriptive_title").getValue().toString();
                String Unit3 = snapshot.child("subject3/units").getValue().toString();
                SC3.setText(sub3);
                des3.setText(Des3);
                units3.setText(Unit3);

                String sub4 = snapshot.child("subject4/code").getValue().toString();
                String Des4 = snapshot.child("subject4/descriptive_title").getValue().toString();
                String Unit4 = snapshot.child("subject4/units").getValue().toString();
                SC4.setText(sub4);
                des4.setText(Des4);
                units4.setText(Unit4);

                String sub5 = snapshot.child("subject5/code").getValue().toString();
                String Des5 = snapshot.child("subject5/descriptive_title").getValue().toString();
                String Unit5 = snapshot.child("subject5/units").getValue().toString();
                SC5.setText(sub5);
                des5.setText(Des5);
                units5.setText(Unit5);

                String sub6 = snapshot.child("subject6/code").getValue().toString();
                String Des6 = snapshot.child("subject6/descriptive_title").getValue().toString();
                String Unit6 = snapshot.child("subject6/units").getValue().toString();
                SC6.setText(sub6);
                des6.setText(Des6);
                units6.setText(Unit6);

                String sub7 = snapshot.child("subject7/code").getValue().toString();
                String Des7 = snapshot.child("subject7/descriptive_title").getValue().toString();
                String Unit7 = snapshot.child("subject7/units").getValue().toString();
                SC7.setText(sub7);
                des7.setText(Des7);
                units7.setText(Unit7);

                String sub8 = snapshot.child("subject8/code").getValue().toString();
                String Des8 = snapshot.child("subject8/descriptive_title").getValue().toString();
                String Unit8 = snapshot.child("subject8/units").getValue().toString();
                SC8.setText(sub8);
                des8.setText(Des8);
                units8.setText(Unit8);

                String sub9 = snapshot.child("subject9/code").getValue().toString();
                String Des9 = snapshot.child("subject9/descriptive_title").getValue().toString();
                String Unit9 = snapshot.child("subject9/units").getValue().toString();
                SC9.setText(sub9);
                des9.setText(Des9);
                units9.setText(Unit9);

                String sub10 = snapshot.child("subject10/code").getValue().toString();
                String Des10 = snapshot.child("subject10/descriptive_title").getValue().toString();
                String Unit10 = snapshot.child("subject10/units").getValue().toString();
                SC10.setText(sub10);
                des10.setText(Des10);
                units10.setText(Unit10);


            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void BEED3y2sem() {

        DatabaseReference databasecs = FirebaseDatabase.getInstance().getReference("course_curriculum");

        databasecs.child("beed").child("third_year").child("second_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject1/code").getValue().toString();
                String Des1 = snapshot.child("subject1/descriptive_title").getValue().toString();
                String Unit = snapshot.child("subject1/units").getValue().toString();
                SC1.setText(sub1);
                des1.setText(Des1);
                units1.setText(Unit);

                String sub2 = snapshot.child("subject2/code").getValue().toString();
                String Des2 = snapshot.child("subject2/descriptive_title").getValue().toString();
                String Unit2 = snapshot.child("subject2/units").getValue().toString();
                SC2.setText(sub2);
                des2.setText(Des2);
                units2.setText(Unit2);

                String sub3 = snapshot.child("subject3/code").getValue().toString();
                String Des3 = snapshot.child("subject3/descriptive_title").getValue().toString();
                String Unit3 = snapshot.child("subject3/units").getValue().toString();
                SC3.setText(sub3);
                des3.setText(Des3);
                units3.setText(Unit3);

                String sub4 = snapshot.child("subject4/code").getValue().toString();
                String Des4 = snapshot.child("subject4/descriptive_title").getValue().toString();
                String Unit4 = snapshot.child("subject4/units").getValue().toString();
                SC4.setText(sub4);
                des4.setText(Des4);
                units4.setText(Unit4);

                String sub5 = snapshot.child("subject5/code").getValue().toString();
                String Des5 = snapshot.child("subject5/descriptive_title").getValue().toString();
                String Unit5 = snapshot.child("subject5/units").getValue().toString();
                SC5.setText(sub5);
                des5.setText(Des5);
                units5.setText(Unit5);

                String sub6 = snapshot.child("subject6/code").getValue().toString();
                String Des6 = snapshot.child("subject6/descriptive_title").getValue().toString();
                String Unit6 = snapshot.child("subject6/units").getValue().toString();
                SC6.setText(sub6);
                des6.setText(Des6);
                units6.setText(Unit6);

                String sub7 = snapshot.child("subject7/code").getValue().toString();
                String Des7 = snapshot.child("subject7/descriptive_title").getValue().toString();
                String Unit7 = snapshot.child("subject7/units").getValue().toString();
                SC7.setText(sub7);
                des7.setText(Des7);
                units7.setText(Unit7);

                String sub8 = snapshot.child("subject8/code").getValue().toString();
                String Des8 = snapshot.child("subject8/descriptive_title").getValue().toString();
                String Unit8 = snapshot.child("subject8/units").getValue().toString();
                SC8.setText(sub8);
                des8.setText(Des8);
                units8.setText(Unit8);

                String sub9 = snapshot.child("subject9/code").getValue().toString();
                String Des9 = snapshot.child("subject9/descriptive_title").getValue().toString();
                String Unit9 = snapshot.child("subject9/units").getValue().toString();
                SC9.setText(sub9);
                des9.setText(Des9);
                units9.setText(Unit9);

                String sub10 = snapshot.child("subject10/code").getValue().toString();
                String Des10 = snapshot.child("subject10/descriptive_title").getValue().toString();
                String Unit10 = snapshot.child("subject10/units").getValue().toString();
                SC10.setText(sub10);
                des10.setText(Des10);
                units10.setText(Unit10);


            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void BEED4y1sem() {

        DatabaseReference databasecs = FirebaseDatabase.getInstance().getReference("course_curriculum");

        databasecs.child("beed").child("fourth_year").child("first_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject1/code").getValue().toString();
                String Des1 = snapshot.child("subject1/descriptive_title").getValue().toString();
                String Unit = snapshot.child("subject1/units").getValue().toString();
                SC1.setText(sub1);
                des1.setText(Des1);
                units1.setText(Unit);

                String sub2 = snapshot.child("subject2/code").getValue().toString();
                String Des2 = snapshot.child("subject2/descriptive_title").getValue().toString();
                String Unit2 = snapshot.child("subject2/units").getValue().toString();
                SC2.setText(sub2);
                des2.setText(Des2);
                units2.setText(Unit2);

                String sub3 = snapshot.child("subject3/code").getValue().toString();
                String Des3 = snapshot.child("subject3/descriptive_title").getValue().toString();
                String Unit3 = snapshot.child("subject3/units").getValue().toString();
                SC3.setText(sub3);
                des3.setText(Des3);
                units3.setText(Unit3);

                String sub4 = snapshot.child("subject4/code").getValue().toString();
                String Des4 = snapshot.child("subject4/descriptive_title").getValue().toString();
                String Unit4 = snapshot.child("subject4/units").getValue().toString();
                SC4.setText(sub4);
                des4.setText(Des4);
                units4.setText(Unit4);

                String sub5 = snapshot.child("subject5/code").getValue().toString();
                String Des5 = snapshot.child("subject5/descriptive_title").getValue().toString();
                String Unit5 = snapshot.child("subject5/units").getValue().toString();
                SC5.setText(sub5);
                des5.setText(Des5);
                units5.setText(Unit5);

                String sub6 = snapshot.child("subject6/code").getValue().toString();
                String Des6 = snapshot.child("subject6/descriptive_title").getValue().toString();
                String Unit6 = snapshot.child("subject6/units").getValue().toString();
                SC6.setText(sub6);
                des6.setText(Des6);
                units6.setText(Unit6);

                String sub7 = snapshot.child("subject7/code").getValue().toString();
                String Des7 = snapshot.child("subject7/descriptive_title").getValue().toString();
                String Unit7 = snapshot.child("subject7/units").getValue().toString();
                SC7.setText(sub7);
                des7.setText(Des7);
                units7.setText(Unit7);

                String sub8 = snapshot.child("subject8/code").getValue().toString();
                String Des8 = snapshot.child("subject8/descriptive_title").getValue().toString();
                String Unit8 = snapshot.child("subject8/units").getValue().toString();
                SC8.setText(sub8);
                des8.setText(Des8);
                units8.setText(Unit8);

                String sub9 = snapshot.child("subject9/code").getValue().toString();
                String Des9 = snapshot.child("subject9/descriptive_title").getValue().toString();
                String Unit9 = snapshot.child("subject9/units").getValue().toString();
                SC9.setText(sub9);
                des9.setText(Des9);
                units9.setText(Unit9);

                String sub10 = snapshot.child("subject10/code").getValue().toString();
                String Des10 = snapshot.child("subject10/descriptive_title").getValue().toString();
                String Unit10 = snapshot.child("subject10/units").getValue().toString();
                SC10.setText(sub10);
                des10.setText(Des10);
                units10.setText(Unit10);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void BEED4y2sem() {

        DatabaseReference databasecs = FirebaseDatabase.getInstance().getReference("course_curriculum");

        databasecs.child("beed").child("fourth_year").child("second_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject1/code").getValue().toString();
                String Des1 = snapshot.child("subject1/descriptive_title").getValue().toString();
                String Unit = snapshot.child("subject1/units").getValue().toString();
                SC1.setText(sub1);
                des1.setText(Des1);
                units1.setText(Unit);

                String sub2 = snapshot.child("subject2/code").getValue().toString();
                String Des2 = snapshot.child("subject2/descriptive_title").getValue().toString();
                String Unit2 = snapshot.child("subject2/units").getValue().toString();
                SC2.setText(sub2);
                des2.setText(Des2);
                units2.setText(Unit2);

                String sub3 = snapshot.child("subject3/code").getValue().toString();
                String Des3 = snapshot.child("subject3/descriptive_title").getValue().toString();
                String Unit3 = snapshot.child("subject3/units").getValue().toString();
                SC3.setText(sub3);
                des3.setText(Des3);
                units3.setText(Unit3);

                String sub4 = snapshot.child("subject4/code").getValue().toString();
                String Des4 = snapshot.child("subject4/descriptive_title").getValue().toString();
                String Unit4 = snapshot.child("subject4/units").getValue().toString();
                SC4.setText(sub4);
                des4.setText(Des4);
                units4.setText(Unit4);

                String sub5 = snapshot.child("subject5/code").getValue().toString();
                String Des5 = snapshot.child("subject5/descriptive_title").getValue().toString();
                String Unit5 = snapshot.child("subject5/units").getValue().toString();
                SC5.setText(sub5);
                des5.setText(Des5);
                units5.setText(Unit5);

                String sub6 = snapshot.child("subject6/code").getValue().toString();
                String Des6 = snapshot.child("subject6/descriptive_title").getValue().toString();
                String Unit6 = snapshot.child("subject6/units").getValue().toString();
                SC6.setText(sub6);
                des6.setText(Des6);
                units6.setText(Unit6);

                String sub7 = snapshot.child("subject7/code").getValue().toString();
                String Des7 = snapshot.child("subject7/descriptive_title").getValue().toString();
                String Unit7 = snapshot.child("subject7/units").getValue().toString();
                SC7.setText(sub7);
                des7.setText(Des7);
                units7.setText(Unit7);

                String sub8 = snapshot.child("subject8/code").getValue().toString();
                String Des8 = snapshot.child("subject8/descriptive_title").getValue().toString();
                String Unit8 = snapshot.child("subject8/units").getValue().toString();
                SC8.setText(sub8);
                des8.setText(Des8);
                units8.setText(Unit8);

                String sub9 = snapshot.child("subject9/code").getValue().toString();
                String Des9 = snapshot.child("subject9/descriptive_title").getValue().toString();
                String Unit9 = snapshot.child("subject9/units").getValue().toString();
                SC9.setText(sub9);
                des9.setText(Des9);
                units9.setText(Unit9);

                String sub10 = snapshot.child("subject10/code").getValue().toString();
                String Des10 = snapshot.child("subject10/descriptive_title").getValue().toString();
                String Unit10 = snapshot.child("subject10/units").getValue().toString();
                SC10.setText(sub10);
                des10.setText(Des10);
                units10.setText(Unit10);

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void bsed_e1y2sem() {

        DatabaseReference databasecs = FirebaseDatabase.getInstance().getReference("course_curriculum");

        databasecs.child("bsed_e").child("first_year").child("second_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject1/code").getValue().toString();
                String Des1 = snapshot.child("subject1/descriptive_title").getValue().toString();
                String Unit = snapshot.child("subject1/units").getValue().toString();
                SC1.setText(sub1);
                des1.setText(Des1);
                units1.setText(Unit);

                String sub2 = snapshot.child("subject2/code").getValue().toString();
                String Des2 = snapshot.child("subject2/descriptive_title").getValue().toString();
                String Unit2 = snapshot.child("subject2/units").getValue().toString();
                SC2.setText(sub2);
                des2.setText(Des2);
                units2.setText(Unit2);

                String sub3 = snapshot.child("subject3/code").getValue().toString();
                String Des3 = snapshot.child("subject3/descriptive_title").getValue().toString();
                String Unit3 = snapshot.child("subject3/units").getValue().toString();
                SC3.setText(sub3);
                des3.setText(Des3);
                units3.setText(Unit3);

                String sub4 = snapshot.child("subject4/code").getValue().toString();
                String Des4 = snapshot.child("subject4/descriptive_title").getValue().toString();
                String Unit4 = snapshot.child("subject4/units").getValue().toString();
                SC4.setText(sub4);
                des4.setText(Des4);
                units4.setText(Unit4);

                String sub5 = snapshot.child("subject5/code").getValue().toString();
                String Des5 = snapshot.child("subject5/descriptive_title").getValue().toString();
                String Unit5 = snapshot.child("subject5/units").getValue().toString();
                SC5.setText(sub5);
                des5.setText(Des5);
                units5.setText(Unit5);

                String sub6 = snapshot.child("subject6/code").getValue().toString();
                String Des6 = snapshot.child("subject6/descriptive_title").getValue().toString();
                String Unit6 = snapshot.child("subject6/units").getValue().toString();
                SC6.setText(sub6);
                des6.setText(Des6);
                units6.setText(Unit6);

                String sub7 = snapshot.child("subject7/code").getValue().toString();
                String Des7 = snapshot.child("subject7/descriptive_title").getValue().toString();
                String Unit7 = snapshot.child("subject7/units").getValue().toString();
                SC7.setText(sub7);
                des7.setText(Des7);
                units7.setText(Unit7);

                String sub8 = snapshot.child("subject8/code").getValue().toString();
                String Des8 = snapshot.child("subject8/descriptive_title").getValue().toString();
                String Unit8 = snapshot.child("subject8/units").getValue().toString();
                SC8.setText(sub8);
                des8.setText(Des8);
                units8.setText(Unit8);

                String sub9 = snapshot.child("subject9/code").getValue().toString();
                String Des9 = snapshot.child("subject9/descriptive_title").getValue().toString();
                String Unit9 = snapshot.child("subject9/units").getValue().toString();
                SC9.setText(sub9);
                des9.setText(Des9);
                units9.setText(Unit9);

                String sub10 = snapshot.child("subject10/code").getValue().toString();
                String Des10 = snapshot.child("subject10/descriptive_title").getValue().toString();
                String Unit10 = snapshot.child("subject10/units").getValue().toString();
                SC10.setText(sub10);
                des10.setText(Des10);
                units10.setText(Unit10);


            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void bsed_e1y1sem() {

        DatabaseReference databasecs = FirebaseDatabase.getInstance().getReference("course_curriculum");

        databasecs.child("bsed_e").child("first_year").child("second_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject1/code").getValue().toString();
                String Des1 = snapshot.child("subject1/descriptive_title").getValue().toString();
                String Unit = snapshot.child("subject1/units").getValue().toString();
                SC1.setText(sub1);
                des1.setText(Des1);
                units1.setText(Unit);

                String sub2 = snapshot.child("subject2/code").getValue().toString();
                String Des2 = snapshot.child("subject2/descriptive_title").getValue().toString();
                String Unit2 = snapshot.child("subject2/units").getValue().toString();
                SC2.setText(sub2);
                des2.setText(Des2);
                units2.setText(Unit2);

                String sub3 = snapshot.child("subject3/code").getValue().toString();
                String Des3 = snapshot.child("subject3/descriptive_title").getValue().toString();
                String Unit3 = snapshot.child("subject3/units").getValue().toString();
                SC3.setText(sub3);
                des3.setText(Des3);
                units3.setText(Unit3);

                String sub4 = snapshot.child("subject4/code").getValue().toString();
                String Des4 = snapshot.child("subject4/descriptive_title").getValue().toString();
                String Unit4 = snapshot.child("subject4/units").getValue().toString();
                SC4.setText(sub4);
                des4.setText(Des4);
                units4.setText(Unit4);

                String sub5 = snapshot.child("subject5/code").getValue().toString();
                String Des5 = snapshot.child("subject5/descriptive_title").getValue().toString();
                String Unit5 = snapshot.child("subject5/units").getValue().toString();
                SC5.setText(sub5);
                des5.setText(Des5);
                units5.setText(Unit5);

                String sub6 = snapshot.child("subject6/code").getValue().toString();
                String Des6 = snapshot.child("subject6/descriptive_title").getValue().toString();
                String Unit6 = snapshot.child("subject6/units").getValue().toString();
                SC6.setText(sub6);
                des6.setText(Des6);
                units6.setText(Unit6);

                String sub7 = snapshot.child("subject7/code").getValue().toString();
                String Des7 = snapshot.child("subject7/descriptive_title").getValue().toString();
                String Unit7 = snapshot.child("subject7/units").getValue().toString();
                SC7.setText(sub7);
                des7.setText(Des7);
                units7.setText(Unit7);

                String sub8 = snapshot.child("subject8/code").getValue().toString();
                String Des8 = snapshot.child("subject8/descriptive_title").getValue().toString();
                String Unit8 = snapshot.child("subject8/units").getValue().toString();
                SC8.setText(sub8);
                des8.setText(Des8);
                units8.setText(Unit8);

                String sub9 = snapshot.child("subject9/code").getValue().toString();
                String Des9 = snapshot.child("subject9/descriptive_title").getValue().toString();
                String Unit9 = snapshot.child("subject9/units").getValue().toString();
                SC9.setText(sub9);
                des9.setText(Des9);
                units9.setText(Unit9);

                String sub10 = snapshot.child("subject10/code").getValue().toString();
                String Des10 = snapshot.child("subject10/descriptive_title").getValue().toString();
                String Unit10 = snapshot.child("subject10/units").getValue().toString();
                SC10.setText(sub10);
                des10.setText(Des10);
                units10.setText(Unit10);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void bsed_e2y1sem() {

        DatabaseReference databasecs = FirebaseDatabase.getInstance().getReference("course_curriculum");

        databasecs.child("bsed_e").child("second_year").child("first_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject1/code").getValue().toString();
                String Des1 = snapshot.child("subject1/descriptive_title").getValue().toString();
                String Unit = snapshot.child("subject1/units").getValue().toString();
                SC1.setText(sub1);
                des1.setText(Des1);
                units1.setText(Unit);

                String sub2 = snapshot.child("subject2/code").getValue().toString();
                String Des2 = snapshot.child("subject2/descriptive_title").getValue().toString();
                String Unit2 = snapshot.child("subject2/units").getValue().toString();
                SC2.setText(sub2);
                des2.setText(Des2);
                units2.setText(Unit2);

                String sub3 = snapshot.child("subject3/code").getValue().toString();
                String Des3 = snapshot.child("subject3/descriptive_title").getValue().toString();
                String Unit3 = snapshot.child("subject3/units").getValue().toString();
                SC3.setText(sub3);
                des3.setText(Des3);
                units3.setText(Unit3);

                String sub4 = snapshot.child("subject4/code").getValue().toString();
                String Des4 = snapshot.child("subject4/descriptive_title").getValue().toString();
                String Unit4 = snapshot.child("subject4/units").getValue().toString();
                SC4.setText(sub4);
                des4.setText(Des4);
                units4.setText(Unit4);

                String sub5 = snapshot.child("subject5/code").getValue().toString();
                String Des5 = snapshot.child("subject5/descriptive_title").getValue().toString();
                String Unit5 = snapshot.child("subject5/units").getValue().toString();
                SC5.setText(sub5);
                des5.setText(Des5);
                units5.setText(Unit5);

                String sub6 = snapshot.child("subject6/code").getValue().toString();
                String Des6 = snapshot.child("subject6/descriptive_title").getValue().toString();
                String Unit6 = snapshot.child("subject6/units").getValue().toString();
                SC6.setText(sub6);
                des6.setText(Des6);
                units6.setText(Unit6);

                String sub7 = snapshot.child("subject7/code").getValue().toString();
                String Des7 = snapshot.child("subject7/descriptive_title").getValue().toString();
                String Unit7 = snapshot.child("subject7/units").getValue().toString();
                SC7.setText(sub7);
                des7.setText(Des7);
                units7.setText(Unit7);

                String sub8 = snapshot.child("subject8/code").getValue().toString();
                String Des8 = snapshot.child("subject8/descriptive_title").getValue().toString();
                String Unit8 = snapshot.child("subject8/units").getValue().toString();
                SC8.setText(sub8);
                des8.setText(Des8);
                units8.setText(Unit8);

                String sub9 = snapshot.child("subject9/code").getValue().toString();
                String Des9 = snapshot.child("subject9/descriptive_title").getValue().toString();
                String Unit9 = snapshot.child("subject9/units").getValue().toString();
                SC9.setText(sub9);
                des9.setText(Des9);
                units9.setText(Unit9);

                String sub10 = snapshot.child("subject10/code").getValue().toString();
                String Des10 = snapshot.child("subject10/descriptive_title").getValue().toString();
                String Unit10 = snapshot.child("subject10/units").getValue().toString();
                SC10.setText(sub10);
                des10.setText(Des10);
                units10.setText(Unit10);


            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void bsed_e2y2sem() {

        DatabaseReference databasecs = FirebaseDatabase.getInstance().getReference("course_curriculum");

        databasecs.child("bsed_e").child("second_year").child("second_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject1/code").getValue().toString();
                String Des1 = snapshot.child("subject1/descriptive_title").getValue().toString();
                String Unit = snapshot.child("subject1/units").getValue().toString();
                SC1.setText(sub1);
                des1.setText(Des1);
                units1.setText(Unit);

                String sub2 = snapshot.child("subject2/code").getValue().toString();
                String Des2 = snapshot.child("subject2/descriptive_title").getValue().toString();
                String Unit2 = snapshot.child("subject2/units").getValue().toString();
                SC2.setText(sub2);
                des2.setText(Des2);
                units2.setText(Unit2);

                String sub3 = snapshot.child("subject3/code").getValue().toString();
                String Des3 = snapshot.child("subject3/descriptive_title").getValue().toString();
                String Unit3 = snapshot.child("subject3/units").getValue().toString();
                SC3.setText(sub3);
                des3.setText(Des3);
                units3.setText(Unit3);

                String sub4 = snapshot.child("subject4/code").getValue().toString();
                String Des4 = snapshot.child("subject4/descriptive_title").getValue().toString();
                String Unit4 = snapshot.child("subject4/units").getValue().toString();
                SC4.setText(sub4);
                des4.setText(Des4);
                units4.setText(Unit4);

                String sub5 = snapshot.child("subject5/code").getValue().toString();
                String Des5 = snapshot.child("subject5/descriptive_title").getValue().toString();
                String Unit5 = snapshot.child("subject5/units").getValue().toString();
                SC5.setText(sub5);
                des5.setText(Des5);
                units5.setText(Unit5);

                String sub6 = snapshot.child("subject6/code").getValue().toString();
                String Des6 = snapshot.child("subject6/descriptive_title").getValue().toString();
                String Unit6 = snapshot.child("subject6/units").getValue().toString();
                SC6.setText(sub6);
                des6.setText(Des6);
                units6.setText(Unit6);

                String sub7 = snapshot.child("subject7/code").getValue().toString();
                String Des7 = snapshot.child("subject7/descriptive_title").getValue().toString();
                String Unit7 = snapshot.child("subject7/units").getValue().toString();
                SC7.setText(sub7);
                des7.setText(Des7);
                units7.setText(Unit7);

                String sub8 = snapshot.child("subject8/code").getValue().toString();
                String Des8 = snapshot.child("subject8/descriptive_title").getValue().toString();
                String Unit8 = snapshot.child("subject8/units").getValue().toString();
                SC8.setText(sub8);
                des8.setText(Des8);
                units8.setText(Unit8);

                String sub9 = snapshot.child("subject9/code").getValue().toString();
                String Des9 = snapshot.child("subject9/descriptive_title").getValue().toString();
                String Unit9 = snapshot.child("subject9/units").getValue().toString();
                SC9.setText(sub9);
                des9.setText(Des9);
                units9.setText(Unit9);

                String sub10 = snapshot.child("subject10/code").getValue().toString();
                String Des10 = snapshot.child("subject10/descriptive_title").getValue().toString();
                String Unit10 = snapshot.child("subject10/units").getValue().toString();
                SC10.setText(sub10);
                des10.setText(Des10);
                units10.setText(Unit10);

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void bsed_e3y1sem() {

        DatabaseReference databasecs = FirebaseDatabase.getInstance().getReference("course_curriculum");

        databasecs.child("bsed_e").child("third_year").child("first_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject1/code").getValue().toString();
                String Des1 = snapshot.child("subject1/descriptive_title").getValue().toString();
                String Unit = snapshot.child("subject1/units").getValue().toString();
                SC1.setText(sub1);
                des1.setText(Des1);
                units1.setText(Unit);

                String sub2 = snapshot.child("subject2/code").getValue().toString();
                String Des2 = snapshot.child("subject2/descriptive_title").getValue().toString();
                String Unit2 = snapshot.child("subject2/units").getValue().toString();
                SC2.setText(sub2);
                des2.setText(Des2);
                units2.setText(Unit2);

                String sub3 = snapshot.child("subject3/code").getValue().toString();
                String Des3 = snapshot.child("subject3/descriptive_title").getValue().toString();
                String Unit3 = snapshot.child("subject3/units").getValue().toString();
                SC3.setText(sub3);
                des3.setText(Des3);
                units3.setText(Unit3);

                String sub4 = snapshot.child("subject4/code").getValue().toString();
                String Des4 = snapshot.child("subject4/descriptive_title").getValue().toString();
                String Unit4 = snapshot.child("subject4/units").getValue().toString();
                SC4.setText(sub4);
                des4.setText(Des4);
                units4.setText(Unit4);

                String sub5 = snapshot.child("subject5/code").getValue().toString();
                String Des5 = snapshot.child("subject5/descriptive_title").getValue().toString();
                String Unit5 = snapshot.child("subject5/units").getValue().toString();
                SC5.setText(sub5);
                des5.setText(Des5);
                units5.setText(Unit5);

                String sub6 = snapshot.child("subject6/code").getValue().toString();
                String Des6 = snapshot.child("subject6/descriptive_title").getValue().toString();
                String Unit6 = snapshot.child("subject6/units").getValue().toString();
                SC6.setText(sub6);
                des6.setText(Des6);
                units6.setText(Unit6);

                String sub7 = snapshot.child("subject7/code").getValue().toString();
                String Des7 = snapshot.child("subject7/descriptive_title").getValue().toString();
                String Unit7 = snapshot.child("subject7/units").getValue().toString();
                SC7.setText(sub7);
                des7.setText(Des7);
                units7.setText(Unit7);

                String sub8 = snapshot.child("subject8/code").getValue().toString();
                String Des8 = snapshot.child("subject8/descriptive_title").getValue().toString();
                String Unit8 = snapshot.child("subject8/units").getValue().toString();
                SC8.setText(sub8);
                des8.setText(Des8);
                units8.setText(Unit8);

                String sub9 = snapshot.child("subject9/code").getValue().toString();
                String Des9 = snapshot.child("subject9/descriptive_title").getValue().toString();
                String Unit9 = snapshot.child("subject9/units").getValue().toString();
                SC9.setText(sub9);
                des9.setText(Des9);
                units9.setText(Unit9);

                String sub10 = snapshot.child("subject10/code").getValue().toString();
                String Des10 = snapshot.child("subject10/descriptive_title").getValue().toString();
                String Unit10 = snapshot.child("subject10/units").getValue().toString();
                SC10.setText(sub10);
                des10.setText(Des10);
                units10.setText(Unit10);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void bsed_e3y2sem() {

        DatabaseReference databasecs = FirebaseDatabase.getInstance().getReference("course_curriculum");

        databasecs.child("bsed_e").child("third_year").child("second_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject1/code").getValue().toString();
                String Des1 = snapshot.child("subject1/descriptive_title").getValue().toString();
                String Unit = snapshot.child("subject1/units").getValue().toString();
                SC1.setText(sub1);
                des1.setText(Des1);
                units1.setText(Unit);

                String sub2 = snapshot.child("subject2/code").getValue().toString();
                String Des2 = snapshot.child("subject2/descriptive_title").getValue().toString();
                String Unit2 = snapshot.child("subject2/units").getValue().toString();
                SC2.setText(sub2);
                des2.setText(Des2);
                units2.setText(Unit2);

                String sub3 = snapshot.child("subject3/code").getValue().toString();
                String Des3 = snapshot.child("subject3/descriptive_title").getValue().toString();
                String Unit3 = snapshot.child("subject3/units").getValue().toString();
                SC3.setText(sub3);
                des3.setText(Des3);
                units3.setText(Unit3);

                String sub4 = snapshot.child("subject4/code").getValue().toString();
                String Des4 = snapshot.child("subject4/descriptive_title").getValue().toString();
                String Unit4 = snapshot.child("subject4/units").getValue().toString();
                SC4.setText(sub4);
                des4.setText(Des4);
                units4.setText(Unit4);

                String sub5 = snapshot.child("subject5/code").getValue().toString();
                String Des5 = snapshot.child("subject5/descriptive_title").getValue().toString();
                String Unit5 = snapshot.child("subject5/units").getValue().toString();
                SC5.setText(sub5);
                des5.setText(Des5);
                units5.setText(Unit5);

                String sub6 = snapshot.child("subject6/code").getValue().toString();
                String Des6 = snapshot.child("subject6/descriptive_title").getValue().toString();
                String Unit6 = snapshot.child("subject6/units").getValue().toString();
                SC6.setText(sub6);
                des6.setText(Des6);
                units6.setText(Unit6);

                String sub7 = snapshot.child("subject7/code").getValue().toString();
                String Des7 = snapshot.child("subject7/descriptive_title").getValue().toString();
                String Unit7 = snapshot.child("subject7/units").getValue().toString();
                SC7.setText(sub7);
                des7.setText(Des7);
                units7.setText(Unit7);

                String sub8 = snapshot.child("subject8/code").getValue().toString();
                String Des8 = snapshot.child("subject8/descriptive_title").getValue().toString();
                String Unit8 = snapshot.child("subject8/units").getValue().toString();
                SC8.setText(sub8);
                des8.setText(Des8);
                units8.setText(Unit8);

                String sub9 = snapshot.child("subject9/code").getValue().toString();
                String Des9 = snapshot.child("subject9/descriptive_title").getValue().toString();
                String Unit9 = snapshot.child("subject9/units").getValue().toString();
                SC9.setText(sub9);
                des9.setText(Des9);
                units9.setText(Unit9);

                String sub10 = snapshot.child("subject10/code").getValue().toString();
                String Des10 = snapshot.child("subject10/descriptive_title").getValue().toString();
                String Unit10 = snapshot.child("subject10/units").getValue().toString();
                SC10.setText(sub10);
                des10.setText(Des10);
                units10.setText(Unit10);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void bsed_e4y1sem() {

        DatabaseReference databasecs = FirebaseDatabase.getInstance().getReference("course_curriculum");

        databasecs.child("bsed_e").child("fourth_year").child("first_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject1/code").getValue().toString();
                String Des1 = snapshot.child("subject1/descriptive_title").getValue().toString();
                String Unit = snapshot.child("subject1/units").getValue().toString();
                SC1.setText(sub1);
                des1.setText(Des1);
                units1.setText(Unit);

                String sub2 = snapshot.child("subject2/code").getValue().toString();
                String Des2 = snapshot.child("subject2/descriptive_title").getValue().toString();
                String Unit2 = snapshot.child("subject2/units").getValue().toString();
                SC2.setText(sub2);
                des2.setText(Des2);
                units2.setText(Unit2);

                String sub3 = snapshot.child("subject3/code").getValue().toString();
                String Des3 = snapshot.child("subject3/descriptive_title").getValue().toString();
                String Unit3 = snapshot.child("subject3/units").getValue().toString();
                SC3.setText(sub3);
                des3.setText(Des3);
                units3.setText(Unit3);

                String sub4 = snapshot.child("subject4/code").getValue().toString();
                String Des4 = snapshot.child("subject4/descriptive_title").getValue().toString();
                String Unit4 = snapshot.child("subject4/units").getValue().toString();
                SC4.setText(sub4);
                des4.setText(Des4);
                units4.setText(Unit4);

                String sub5 = snapshot.child("subject5/code").getValue().toString();
                String Des5 = snapshot.child("subject5/descriptive_title").getValue().toString();
                String Unit5 = snapshot.child("subject5/units").getValue().toString();
                SC5.setText(sub5);
                des5.setText(Des5);
                units5.setText(Unit5);

                String sub6 = snapshot.child("subject6/code").getValue().toString();
                String Des6 = snapshot.child("subject6/descriptive_title").getValue().toString();
                String Unit6 = snapshot.child("subject6/units").getValue().toString();
                SC6.setText(sub6);
                des6.setText(Des6);
                units6.setText(Unit6);

                String sub7 = snapshot.child("subject7/code").getValue().toString();
                String Des7 = snapshot.child("subject7/descriptive_title").getValue().toString();
                String Unit7 = snapshot.child("subject7/units").getValue().toString();
                SC7.setText(sub7);
                des7.setText(Des7);
                units7.setText(Unit7);

                String sub8 = snapshot.child("subject8/code").getValue().toString();
                String Des8 = snapshot.child("subject8/descriptive_title").getValue().toString();
                String Unit8 = snapshot.child("subject8/units").getValue().toString();
                SC8.setText(sub8);
                des8.setText(Des8);
                units8.setText(Unit8);

                String sub9 = snapshot.child("subject9/code").getValue().toString();
                String Des9 = snapshot.child("subject9/descriptive_title").getValue().toString();
                String Unit9 = snapshot.child("subject9/units").getValue().toString();
                SC9.setText(sub9);
                des9.setText(Des9);
                units9.setText(Unit9);

                String sub10 = snapshot.child("subject10/code").getValue().toString();
                String Des10 = snapshot.child("subject10/descriptive_title").getValue().toString();
                String Unit10 = snapshot.child("subject10/units").getValue().toString();
                SC10.setText(sub10);
                des10.setText(Des10);
                units10.setText(Unit10);

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void bsed_e4y2sem() {

        DatabaseReference databasecs = FirebaseDatabase.getInstance().getReference("course_curriculum");

        databasecs.child("bsed_e").child("fourth_year").child("second_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject1/code").getValue().toString();
                String Des1 = snapshot.child("subject1/descriptive_title").getValue().toString();
                String Unit = snapshot.child("subject1/units").getValue().toString();
                SC1.setText(sub1);
                des1.setText(Des1);
                units1.setText(Unit);

                String sub2 = snapshot.child("subject2/code").getValue().toString();
                String Des2 = snapshot.child("subject2/descriptive_title").getValue().toString();
                String Unit2 = snapshot.child("subject2/units").getValue().toString();
                SC2.setText(sub2);
                des2.setText(Des2);
                units2.setText(Unit2);

                String sub3 = snapshot.child("subject3/code").getValue().toString();
                String Des3 = snapshot.child("subject3/descriptive_title").getValue().toString();
                String Unit3 = snapshot.child("subject3/units").getValue().toString();
                SC3.setText(sub3);
                des3.setText(Des3);
                units3.setText(Unit3);

                String sub4 = snapshot.child("subject4/code").getValue().toString();
                String Des4 = snapshot.child("subject4/descriptive_title").getValue().toString();
                String Unit4 = snapshot.child("subject4/units").getValue().toString();
                SC4.setText(sub4);
                des4.setText(Des4);
                units4.setText(Unit4);

                String sub5 = snapshot.child("subject5/code").getValue().toString();
                String Des5 = snapshot.child("subject5/descriptive_title").getValue().toString();
                String Unit5 = snapshot.child("subject5/units").getValue().toString();
                SC5.setText(sub5);
                des5.setText(Des5);
                units5.setText(Unit5);

                String sub6 = snapshot.child("subject6/code").getValue().toString();
                String Des6 = snapshot.child("subject6/descriptive_title").getValue().toString();
                String Unit6 = snapshot.child("subject6/units").getValue().toString();
                SC6.setText(sub6);
                des6.setText(Des6);
                units6.setText(Unit6);

                String sub7 = snapshot.child("subject7/code").getValue().toString();
                String Des7 = snapshot.child("subject7/descriptive_title").getValue().toString();
                String Unit7 = snapshot.child("subject7/units").getValue().toString();
                SC7.setText(sub7);
                des7.setText(Des7);
                units7.setText(Unit7);

                String sub8 = snapshot.child("subject8/code").getValue().toString();
                String Des8 = snapshot.child("subject8/descriptive_title").getValue().toString();
                String Unit8 = snapshot.child("subject8/units").getValue().toString();
                SC8.setText(sub8);
                des8.setText(Des8);
                units8.setText(Unit8);

                String sub9 = snapshot.child("subject9/code").getValue().toString();
                String Des9 = snapshot.child("subject9/descriptive_title").getValue().toString();
                String Unit9 = snapshot.child("subject9/units").getValue().toString();
                SC9.setText(sub9);
                des9.setText(Des9);
                units9.setText(Unit9);

                String sub10 = snapshot.child("subject10/code").getValue().toString();
                String Des10 = snapshot.child("subject10/descriptive_title").getValue().toString();
                String Unit10 = snapshot.child("subject10/units").getValue().toString();
                SC10.setText(sub10);
                des10.setText(Des10);
                units10.setText(Unit10);

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    //////////////////////////////////// PRINT PDF //////////////////////////////////////////////////////

    private void printPDF() {
        PdfDocument myPdfDocument = new PdfDocument();
        Paint paint = new Paint();
        PdfDocument.PageInfo myPageInfo1 = new PdfDocument.PageInfo.Builder(596, 842, 1).create();
        PdfDocument.Page myPage1 = myPdfDocument.startPage(myPageInfo1);
        Canvas canvas = myPage1.getCanvas();
        //
        File file = new File(this.getExternalFilesDir("/"), "Form.pdf" );
        canvas.drawBitmap(scaledbmp, (myPageInfo1.getPageWidth() / 2) - 20, 10, paint);


        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(14.0f);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        canvas.drawText("Cainta Catholic College", myPageInfo1.getPageWidth() / 2, 65, paint);
        paint.setTextSize(11.0f);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));



        canvas.drawText("Cainta,Rizal", myPageInfo1.getPageWidth() / 2, 80, paint);

        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        canvas.drawText("COLLEGE DEPARTMENT", myPageInfo1.getPageWidth() / 2, 100, paint);
        canvas.drawText("INFORMATION SHEET", myPageInfo1.getPageWidth() / 2, 111, paint);

        paint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText("OR#:", myPageInfo1.getPageWidth() - 80, 85, paint);
        canvas.drawText("SECTION : " + section.getSelectedItem(), myPageInfo1.getPageWidth() - 135, 191, paint);
        canvas.drawText("UNITS", myPageInfo1.getPageWidth() - 185, 260, paint);
        canvas.drawText("SCHEDULE", myPageInfo1.getPageWidth() - 65, 260, paint);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
        canvas.drawText("" + units1.getText(), myPageInfo1.getPageWidth() - 195, 288, paint);
        canvas.drawText("" + units2.getText(), myPageInfo1.getPageWidth() - 195, 316, paint);
        canvas.drawText("" + units3.getText(), myPageInfo1.getPageWidth() - 195, 344, paint);
        canvas.drawText("" + units4.getText(), myPageInfo1.getPageWidth() - 195, 372, paint);
        canvas.drawText("" + units5.getText(), myPageInfo1.getPageWidth() - 195, 400, paint);
        canvas.drawText("" + units6.getText(), myPageInfo1.getPageWidth() - 195, 428, paint);
        canvas.drawText("" + units7.getText(), myPageInfo1.getPageWidth() - 195, 456, paint);
        canvas.drawText("" + units8.getText(), myPageInfo1.getPageWidth() - 195, 484, paint);
        canvas.drawText("" + units9.getText(), myPageInfo1.getPageWidth() - 195, 512, paint);
        canvas.drawText("" + units10.getText(), myPageInfo1.getPageWidth() - 195, 540, paint);
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText("" + s1.getText(), myPageInfo1.getPageWidth() - 105, 288, paint);
        canvas.drawText("" + s2.getText(), myPageInfo1.getPageWidth() - 105, 316, paint);
        canvas.drawText("" + s3.getText(), myPageInfo1.getPageWidth() - 105, 344, paint);
        canvas.drawText("" + s4.getText(), myPageInfo1.getPageWidth() - 105, 372, paint);
        canvas.drawText("" + s5.getText(), myPageInfo1.getPageWidth() - 105, 400, paint);
        canvas.drawText("" + s6.getText(), myPageInfo1.getPageWidth() - 105, 428, paint);
        canvas.drawText("" + s7.getText(), myPageInfo1.getPageWidth() - 105, 456, paint);
        canvas.drawText("" + s8.getText(), myPageInfo1.getPageWidth() - 105, 484, paint);
        canvas.drawText("" + s9.getText(), myPageInfo1.getPageWidth() - 105, 512, paint);
        canvas.drawText("" + s10.getText(), myPageInfo1.getPageWidth() - 105, 540, paint);

        paint.setTextAlign(Paint.Align.LEFT);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText("NAME: " + name.getText(), 40, 140, paint);
        canvas.drawText("EMAIL: " + gmail.getText(), 40, 157, paint);
        canvas.drawText("COURSE: " + course.getText(), 40, 174, paint);
        canvas.drawText("YEAR & SEM: " + semester.getSelectedItem(), 40, 191, paint);
        //
        canvas.drawText("Downpayment " + downpayment.getText(), 40, 208, paint);
        canvas.drawText("CODE", 40, 260, paint);
        canvas.drawText("DESCRIPTION: ", 175, 260, paint);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
        canvas.drawText("" + SC1.getText(), 40, 288, paint);
        canvas.drawText("" + SC2.getText(), 40, 316, paint);
        canvas.drawText("" + SC3.getText(), 40, 344, paint);
        canvas.drawText("" + SC4.getText(), 40, 372, paint);
        canvas.drawText("" + SC5.getText(), 40, 400, paint);
        canvas.drawText("" + SC6.getText(), 40, 428, paint);
        canvas.drawText("" + SC7.getText(), 40, 456, paint);
        canvas.drawText("" + SC8.getText(), 40, 484, paint);
        canvas.drawText("" + SC9.getText(), 40, 512, paint);
        canvas.drawText("" + SC10.getText(), 40, 540, paint);

        canvas.drawText("" + des1.getText(), 140, 288, paint);
        canvas.drawText("" + des2.getText(), 140, 316, paint);
        canvas.drawText("" + des3.getText(), 140, 344, paint);
        canvas.drawText("" + des4.getText(), 140, 372, paint);
        canvas.drawText("" + des5.getText(), 140, 400, paint);
        canvas.drawText("" + des6.getText(), 140, 428, paint);
        canvas.drawText("" + des7.getText(), 140, 456, paint);
        canvas.drawText("" + des8.getText(), 140, 484, paint);
        canvas.drawText("" + des9.getText(), 140, 512, paint);
        canvas.drawText("" + des10.getText(), 140, 540, paint);
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        canvas.drawText("NOTE:", 40, 600, paint);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
        canvas.drawLine(80, 600, myPageInfo1.getPageWidth() - 35, 600, paint);
        canvas.drawLine(40, 630, myPageInfo1.getPageWidth() - 35, 630, paint);

        canvas.drawLine(40, 655, myPageInfo1.getPageWidth() - 35, 655, paint);

        canvas.drawLine(40, 705, 200, 705, paint);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        canvas.drawText("Adviser", 100, 720, paint);
        canvas.drawText("*This is not valid until it is signed by the adviser.",40,780,paint);
        paint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawLine(myPageInfo1.getPageWidth() - 210, 705, myPageInfo1.getPageWidth() - 50, 705, paint);
        canvas.drawText("Student Signature", myPageInfo1.getPageWidth() - 80, 720, paint);


        one = Integer.parseInt(units1.getText().toString());
        two = Integer.parseInt(units2.getText().toString());
        three = Integer.parseInt(units3.getText().toString());
        four = Integer.parseInt(units4.getText().toString());
        five = Integer.parseInt(units5.getText().toString());
        six = Integer.parseInt(units6.getText().toString());
        seven = Integer.parseInt(units7.getText().toString());
        eight = Integer.parseInt(units8.getText().toString());
        nine = Integer.parseInt(units9.getText().toString());
        ten = Integer.parseInt(units10.getText().toString());

        paint.setTextAlign(Paint.Align.RIGHT);
        total = one + two + three + four + five + six + seven + eight + nine + ten;
        canvas.drawText("" + total, myPageInfo1.getPageWidth() - 195, 562, paint);


        myPdfDocument.finishPage(myPage1);


        try {
            myPdfDocument.writeTo(new FileOutputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        myPdfDocument.close();


    }

    //////////////////////////////////// BSCS SCHEDULE SECTION //////////////////////////////////////////////////////

    ///////////////////////////////////1st year 1st sem/////////////////////////////////////////
    private void bscs1y1sSched_A() {

        DatabaseReference database = FirebaseDatabase.getInstance().getReference("course_curriculum");

        database.child("bscs").child("first_year").child("first_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //sectionA
                String sub1 = snapshot.child("subject01/a").getValue().toString();
                String sub2 = snapshot.child("subject02/a").getValue().toString();
                String sub3 = snapshot.child("subject03/a").getValue().toString();
                String sub4 = snapshot.child("subject04/a").getValue().toString();
                String sub5 = snapshot.child("subject05/a").getValue().toString();
                String sub6 = snapshot.child("subject06/a").getValue().toString();
                String sub7 = snapshot.child("subject07/a").getValue().toString();
                String sub8 = snapshot.child("subject08/a").getValue().toString();
                String sub9 = snapshot.child("subject09/a").getValue().toString();
                String sub10 = snapshot.child("subject10/a").getValue().toString();


                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
    private void bscs1y1sSched_B(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("course_curriculum");

        database.child("bscs").child("first_year").child("first_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //sectionA
                String sub1 = snapshot.child("subject01/b").getValue().toString();
                String sub2 = snapshot.child("subject02/b").getValue().toString();
                String sub3 = snapshot.child("subject03/b").getValue().toString();
                String sub4 = snapshot.child("subject04/b").getValue().toString();
                String sub5 = snapshot.child("subject05/b").getValue().toString();
                String sub6 = snapshot.child("subject06/b").getValue().toString();
                String sub7 = snapshot.child("subject07/b").getValue().toString();
                String sub8 = snapshot.child("subject08/b").getValue().toString();
                String sub9 = snapshot.child("subject09/b").getValue().toString();
                String sub10 = snapshot.child("subject10/b").getValue().toString();


                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
    private void bscs1y1sSched_C(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("course_curriculum");

        database.child("bscs").child("first_year").child("first_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/c").getValue().toString();
                String sub2 = snapshot.child("subject02/c").getValue().toString();
                String sub3 = snapshot.child("subject03/c").getValue().toString();
                String sub4 = snapshot.child("subject04/c").getValue().toString();
                String sub5 = snapshot.child("subject05/c").getValue().toString();
                String sub6 = snapshot.child("subject06/c").getValue().toString();
                String sub7 = snapshot.child("subject07/c").getValue().toString();
                String sub8 = snapshot.child("subject08/c").getValue().toString();
                String sub9 = snapshot.child("subject09/c").getValue().toString();
                String sub10 = snapshot.child("subject10/c").getValue().toString();


                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    ///////////////////////////////////1nd year 2nd sem/////////////////////////////////////////
    private void bscs1y2sSched_A() {

        DatabaseReference database = FirebaseDatabase.getInstance().getReference("course_curriculum");

        database.child("bscs").child("first_year").child("second_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //sectionA
                String sub1 = snapshot.child("subject01/a").getValue().toString();
                String sub2 = snapshot.child("subject02/a").getValue().toString();
                String sub3 = snapshot.child("subject03/a").getValue().toString();
                String sub4 = snapshot.child("subject04/a").getValue().toString();
                String sub5 = snapshot.child("subject05/a").getValue().toString();
                String sub6 = snapshot.child("subject06/a").getValue().toString();
                String sub7 = snapshot.child("subject07/a").getValue().toString();
                String sub8 = snapshot.child("subject08/a").getValue().toString();
                String sub9 = snapshot.child("subject09/a").getValue().toString();
                String sub10 = snapshot.child("subject10/a").getValue().toString();


                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
    private void bscs1y2sSched_B(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("course_curriculum");

        database.child("bscs").child("first_year").child("second_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //sectionA
                String sub1 = snapshot.child("subject01/b").getValue().toString();
                String sub2 = snapshot.child("subject02/b").getValue().toString();
                String sub3 = snapshot.child("subject03/b").getValue().toString();
                String sub4 = snapshot.child("subject04/b").getValue().toString();
                String sub5 = snapshot.child("subject05/b").getValue().toString();
                String sub6 = snapshot.child("subject06/b").getValue().toString();
                String sub7 = snapshot.child("subject07/b").getValue().toString();
                String sub8 = snapshot.child("subject08/b").getValue().toString();
                String sub9 = snapshot.child("subject09/b").getValue().toString();
                String sub10 = snapshot.child("subject10/b").getValue().toString();


                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
    private void bscs1y2sSched_C(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("course_curriculum");

        database.child("bscs").child("first_year").child("second_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/c").getValue().toString();
                String sub2 = snapshot.child("subject02/c").getValue().toString();
                String sub3 = snapshot.child("subject03/c").getValue().toString();
                String sub4 = snapshot.child("subject04/c").getValue().toString();
                String sub5 = snapshot.child("subject05/c").getValue().toString();
                String sub6 = snapshot.child("subject06/c").getValue().toString();
                String sub7 = snapshot.child("subject07/c").getValue().toString();
                String sub8 = snapshot.child("subject08/c").getValue().toString();
                String sub9 = snapshot.child("subject09/c").getValue().toString();
                String sub10 = snapshot.child("subject10/c").getValue().toString();

                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    ///////////////////////////////////2nd year 1st sem/////////////////////////////////////////
    private void bscs2y1sSched_A() {

        DatabaseReference database = FirebaseDatabase.getInstance().getReference("course_curriculum");

        database.child("bscs").child("second_year").child("first_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/a").getValue().toString();
                String sub2 = snapshot.child("subject02/a").getValue().toString();
                String sub3 = snapshot.child("subject03/a").getValue().toString();
                String sub4 = snapshot.child("subject04/a").getValue().toString();
                String sub5 = snapshot.child("subject05/a").getValue().toString();
                String sub6 = snapshot.child("subject06/a").getValue().toString();
                String sub7 = snapshot.child("subject07/a").getValue().toString();
                String sub8 = snapshot.child("subject08/a").getValue().toString();
                String sub9 = snapshot.child("subject09/a").getValue().toString();
                String sub10 = snapshot.child("subject10/a").getValue().toString();


                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
    private void bscs2y1sSched_B(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("course_curriculum");

        database.child("bscs").child("second_year").child("first_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/b").getValue().toString();
                String sub2 = snapshot.child("subject02/b").getValue().toString();
                String sub3 = snapshot.child("subject03/b").getValue().toString();
                String sub4 = snapshot.child("subject04/b").getValue().toString();
                String sub5 = snapshot.child("subject05/b").getValue().toString();
                String sub6 = snapshot.child("subject06/b").getValue().toString();
                String sub7 = snapshot.child("subject07/b").getValue().toString();
                String sub8 = snapshot.child("subject08/b").getValue().toString();
                String sub9 = snapshot.child("subject09/b").getValue().toString();
                String sub10 = snapshot.child("subject10/b").getValue().toString();

                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
    private void bscs2y1sSched_C(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("course_curriculum");

        database.child("bscs").child("second_year").child("first_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/c").getValue().toString();
                String sub2 = snapshot.child("subject02/c").getValue().toString();
                String sub3 = snapshot.child("subject03/c").getValue().toString();
                String sub4 = snapshot.child("subject04/c").getValue().toString();
                String sub5 = snapshot.child("subject05/c").getValue().toString();
                String sub6 = snapshot.child("subject06/c").getValue().toString();
                String sub7 = snapshot.child("subject07/c").getValue().toString();
                String sub8 = snapshot.child("subject08/c").getValue().toString();
                String sub9 = snapshot.child("subject09/c").getValue().toString();
                String sub10 = snapshot.child("subject10/c").getValue().toString();


                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    ///////////////////////////////////2nd year 2nd sem/////////////////////////////////////////
    private void bscs2y2sSched_A(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("course_curriculum");

        database.child("bscs").child("second_year").child("second_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/a").getValue().toString();
                String sub2 = snapshot.child("subject02/a").getValue().toString();
                String sub3 = snapshot.child("subject03/a").getValue().toString();
                String sub4 = snapshot.child("subject04/a").getValue().toString();
                String sub5 = snapshot.child("subject05/a").getValue().toString();
                String sub6 = snapshot.child("subject06/a").getValue().toString();
                String sub7 = snapshot.child("subject07/a").getValue().toString();
                String sub8 = snapshot.child("subject08/a").getValue().toString();
                String sub9 = snapshot.child("subject09/a").getValue().toString();
                String sub10 = snapshot.child("subject10/a").getValue().toString();


                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);


            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void bscs2y2sSched_B(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("course_curriculum");

        database.child("bscs").child("second_year").child("second_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/b").getValue().toString();
                String sub2 = snapshot.child("subject02/b").getValue().toString();
                String sub3 = snapshot.child("subject03/b").getValue().toString();
                String sub4 = snapshot.child("subject04/b").getValue().toString();
                String sub5 = snapshot.child("subject05/b").getValue().toString();
                String sub6 = snapshot.child("subject06/b").getValue().toString();
                String sub7 = snapshot.child("subject07/b").getValue().toString();
                String sub8 = snapshot.child("subject08/b").getValue().toString();
                String sub9 = snapshot.child("subject09/b").getValue().toString();
                String sub10 = snapshot.child("subject10/b").getValue().toString();

                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void bscs2y2sSched_C(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("course_curriculum");

        database.child("bscs").child("second_year").child("second_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/c").getValue().toString();
                String sub2 = snapshot.child("subject02/c").getValue().toString();
                String sub3 = snapshot.child("subject03/c").getValue().toString();
                String sub4 = snapshot.child("subject04/c").getValue().toString();
                String sub5 = snapshot.child("subject05/c").getValue().toString();
                String sub6 = snapshot.child("subject06/c").getValue().toString();
                String sub7 = snapshot.child("subject07/c").getValue().toString();
                String sub8 = snapshot.child("subject08/c").getValue().toString();
                String sub9 = snapshot.child("subject09/c").getValue().toString();
                String sub10 = snapshot.child("subject10/c").getValue().toString();


                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    ///////////////////////////////////3rd year 1st sem/////////////////////////////////////////
    private void bscs3y1sSched_A(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("course_curriculum");

        database.child("bscs").child("third_year").child("first_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/a").getValue().toString();
                String sub2 = snapshot.child("subject02/a").getValue().toString();
                String sub3 = snapshot.child("subject03/a").getValue().toString();
                String sub4 = snapshot.child("subject04/a").getValue().toString();
                String sub5 = snapshot.child("subject05/a").getValue().toString();
                String sub6 = snapshot.child("subject06/a").getValue().toString();
                String sub7 = snapshot.child("subject07/a").getValue().toString();
                String sub8 = snapshot.child("subject08/a").getValue().toString();
                String sub9 = snapshot.child("subject09/a").getValue().toString();
                String sub10 = snapshot.child("subject10/a").getValue().toString();


                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);


            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void bscs3y1sSched_B(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("course_curriculum");

        database.child("bscs").child("third_year").child("first_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String sub1 = snapshot.child("subject01/b").getValue().toString();
                String sub2 = snapshot.child("subject02/b").getValue().toString();
                String sub3 = snapshot.child("subject03/b").getValue().toString();
                String sub4 = snapshot.child("subject04/b").getValue().toString();
                String sub5 = snapshot.child("subject05/b").getValue().toString();
                String sub6 = snapshot.child("subject06/b").getValue().toString();
                String sub7 = snapshot.child("subject07/b").getValue().toString();
                String sub8 = snapshot.child("subject08/b").getValue().toString();
                String sub9 = snapshot.child("subject09/b").getValue().toString();
                String sub10 = snapshot.child("subject10/b").getValue().toString();

                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void bscs3y1sSched_C(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("course_curriculum");

        database.child("bscs").child("third_year").child("first_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/c").getValue().toString();
                String sub2 = snapshot.child("subject02/c").getValue().toString();
                String sub3 = snapshot.child("subject03/c").getValue().toString();
                String sub4 = snapshot.child("subject04/c").getValue().toString();
                String sub5 = snapshot.child("subject05/c").getValue().toString();
                String sub6 = snapshot.child("subject06/c").getValue().toString();
                String sub7 = snapshot.child("subject07/c").getValue().toString();
                String sub8 = snapshot.child("subject08/c").getValue().toString();
                String sub9 = snapshot.child("subject09/c").getValue().toString();
                String sub10 = snapshot.child("subject10/c").getValue().toString();


                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    ///////////////////////////////////3rd year 2nd sem/////////////////////////////////////////

    private void bscs3y2sSched_A(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("course_curriculum");

        database.child("bscs").child("third_year").child("second_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //sectionA
                String sub1 = snapshot.child("subject01/a").getValue().toString();
                String sub2 = snapshot.child("subject02/a").getValue().toString();
                String sub3 = snapshot.child("subject03/a").getValue().toString();
                String sub4 = snapshot.child("subject04/a").getValue().toString();
                String sub5 = snapshot.child("subject05/a").getValue().toString();
                String sub6 = snapshot.child("subject06/a").getValue().toString();
                String sub7 = snapshot.child("subject07/a").getValue().toString();
                String sub8 = snapshot.child("subject08/a").getValue().toString();
                String sub9 = snapshot.child("subject09/a").getValue().toString();
                String sub10 = snapshot.child("subject10/a").getValue().toString();


                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);


            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void bscs3y2sSched_B(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("course_curriculum");

        database.child("bscs").child("third_year").child("second_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/b").getValue().toString();
                String sub2 = snapshot.child("subject02/b").getValue().toString();
                String sub3 = snapshot.child("subject03/b").getValue().toString();
                String sub4 = snapshot.child("subject04/b").getValue().toString();
                String sub5 = snapshot.child("subject05/b").getValue().toString();
                String sub6 = snapshot.child("subject06/b").getValue().toString();
                String sub7 = snapshot.child("subject07/b").getValue().toString();
                String sub8 = snapshot.child("subject08/b").getValue().toString();
                String sub9 = snapshot.child("subject09/b").getValue().toString();
                String sub10 = snapshot.child("subject10/b").getValue().toString();

                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void bscs3y2sSched_C(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("course_curriculum");

        database.child("bscs").child("third_year").child("second_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/c").getValue().toString();
                String sub2 = snapshot.child("subject02/c").getValue().toString();
                String sub3 = snapshot.child("subject03/c").getValue().toString();
                String sub4 = snapshot.child("subject04/c").getValue().toString();
                String sub5 = snapshot.child("subject05/c").getValue().toString();
                String sub6 = snapshot.child("subject06/c").getValue().toString();
                String sub7 = snapshot.child("subject07/c").getValue().toString();
                String sub8 = snapshot.child("subject08/c").getValue().toString();
                String sub9 = snapshot.child("subject09/c").getValue().toString();
                String sub10 = snapshot.child("subject10/c").getValue().toString();


                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    ///////////////////////////////////4th year 1st sem/////////////////////////////////////////

    private void bscs4y1sSched_A(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("course_curriculum");

        database.child("bscs").child("fourth_year").child("first_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/a").getValue().toString();
                String sub2 = snapshot.child("subject02/a").getValue().toString();
                String sub3 = snapshot.child("subject03/a").getValue().toString();
                String sub4 = snapshot.child("subject04/a").getValue().toString();
                String sub5 = snapshot.child("subject05/a").getValue().toString();
                String sub6 = snapshot.child("subject06/a").getValue().toString();
                String sub7 = snapshot.child("subject07/a").getValue().toString();
                String sub8 = snapshot.child("subject08/a").getValue().toString();
                String sub9 = snapshot.child("subject09/a").getValue().toString();
                String sub10 = snapshot.child("subject10/a").getValue().toString();


                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);


            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void bscs4y1sSched_B(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("course_curriculum");

        database.child("bscs").child("fourth_year").child("first_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/b").getValue().toString();
                String sub2 = snapshot.child("subject02/b").getValue().toString();
                String sub3 = snapshot.child("subject03/b").getValue().toString();
                String sub4 = snapshot.child("subject04/b").getValue().toString();
                String sub5 = snapshot.child("subject05/b").getValue().toString();
                String sub6 = snapshot.child("subject06/b").getValue().toString();
                String sub7 = snapshot.child("subject07/b").getValue().toString();
                String sub8 = snapshot.child("subject08/b").getValue().toString();
                String sub9 = snapshot.child("subject09/b").getValue().toString();
                String sub10 = snapshot.child("subject10/b").getValue().toString();

                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void bscs4y1sSched_C(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("course_curriculum");

        database.child("bscs").child("fourth_year").child("first_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/c").getValue().toString();
                String sub2 = snapshot.child("subject02/c").getValue().toString();
                String sub3 = snapshot.child("subject03/c").getValue().toString();
                String sub4 = snapshot.child("subject04/c").getValue().toString();
                String sub5 = snapshot.child("subject05/c").getValue().toString();
                String sub6 = snapshot.child("subject06/c").getValue().toString();
                String sub7 = snapshot.child("subject07/c").getValue().toString();
                String sub8 = snapshot.child("subject08/c").getValue().toString();
                String sub9 = snapshot.child("subject09/c").getValue().toString();
                String sub10 = snapshot.child("subject10/c").getValue().toString();


                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    ///////////////////////////////////4th year 2nd sem/////////////////////////////////////////

    private void bscs4y2sSched_A(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("course_curriculum");

        database.child("bscs").child("fourth_year").child("second_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/a").getValue().toString();
                String sub2 = snapshot.child("subject02/a").getValue().toString();
                String sub3 = snapshot.child("subject03/a").getValue().toString();
                String sub4 = snapshot.child("subject04/a").getValue().toString();
                String sub5 = snapshot.child("subject05/a").getValue().toString();
                String sub6 = snapshot.child("subject06/a").getValue().toString();
                String sub7 = snapshot.child("subject07/a").getValue().toString();
                String sub8 = snapshot.child("subject08/a").getValue().toString();
                String sub9 = snapshot.child("subject09/a").getValue().toString();
                String sub10 = snapshot.child("subject10/a").getValue().toString();


                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);


            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void bscs4y2sSched_B(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("course_curriculum");

        database.child("bscs").child("fourth_year").child("second_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //sectionA
                String sub1 = snapshot.child("subject01/b").getValue().toString();
                String sub2 = snapshot.child("subject02/b").getValue().toString();
                String sub3 = snapshot.child("subject03/b").getValue().toString();
                String sub4 = snapshot.child("subject04/b").getValue().toString();
                String sub5 = snapshot.child("subject05/b").getValue().toString();
                String sub6 = snapshot.child("subject06/b").getValue().toString();
                String sub7 = snapshot.child("subject07/b").getValue().toString();
                String sub8 = snapshot.child("subject08/b").getValue().toString();
                String sub9 = snapshot.child("subject09/b").getValue().toString();
                String sub10 = snapshot.child("subject10/b").getValue().toString();


                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void bscs4y2sSched_C(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("course_curriculum");

        database.child("bscs").child("fourth_year").child("second_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/c").getValue().toString();
                String sub2 = snapshot.child("subject02/c").getValue().toString();
                String sub3 = snapshot.child("subject03/c").getValue().toString();
                String sub4 = snapshot.child("subject04/c").getValue().toString();
                String sub5 = snapshot.child("subject05/c").getValue().toString();
                String sub6 = snapshot.child("subject06/c").getValue().toString();
                String sub7 = snapshot.child("subject07/c").getValue().toString();
                String sub8 = snapshot.child("subject08/c").getValue().toString();
                String sub9 = snapshot.child("subject09/c").getValue().toString();
                String sub10 = snapshot.child("subject10/c").getValue().toString();


                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }



    //////////////////////////////////// BSOA SCHEDULE SECTION //////////////////////////////////////////////////////

    ///////////////////////////////////1st year 1st sem/////////////////////////////////////////
    private void beed1y1sSched_A() {

        DatabaseReference database = FirebaseDatabase.getInstance().getReference("course_curriculum");

        database.child("beed").child("first_year").child("first_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/a").getValue().toString();
                String sub2 = snapshot.child("subject02/a").getValue().toString();
                String sub3 = snapshot.child("subject03/a").getValue().toString();
                String sub4 = snapshot.child("subject04/a").getValue().toString();
                String sub5 = snapshot.child("subject05/a").getValue().toString();
                String sub6 = snapshot.child("subject06/a").getValue().toString();
                String sub7 = snapshot.child("subject07/a").getValue().toString();
                String sub8 = snapshot.child("subject08/a").getValue().toString();
                String sub9 = snapshot.child("subject09/a").getValue().toString();
                String sub10 = snapshot.child("subject10/a").getValue().toString();


                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);




            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
    private void beed1y1sSched_B(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("course_curriculum");

        database.child("beed").child("first_year").child("first_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/b").getValue().toString();
                String sub2 = snapshot.child("subject02/b").getValue().toString();
                String sub3 = snapshot.child("subject03/b").getValue().toString();
                String sub4 = snapshot.child("subject04/b").getValue().toString();
                String sub5 = snapshot.child("subject05/b").getValue().toString();
                String sub6 = snapshot.child("subject06/b").getValue().toString();
                String sub7 = snapshot.child("subject07/b").getValue().toString();
                String sub8 = snapshot.child("subject08/b").getValue().toString();
                String sub9 = snapshot.child("subject09/b").getValue().toString();
                String sub10 = snapshot.child("subject10/b").getValue().toString();


                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
    private void beed1y1sSched_C(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("schedule");

        database.child("beed").child("first_year").child("first_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/c").getValue().toString();
                String sub2 = snapshot.child("subject02/c").getValue().toString();
                String sub3 = snapshot.child("subject03/c").getValue().toString();
                String sub4 = snapshot.child("subject04/c").getValue().toString();
                String sub5 = snapshot.child("subject05/c").getValue().toString();
                String sub6 = snapshot.child("subject06/c").getValue().toString();
                String sub7 = snapshot.child("subject07/c").getValue().toString();
                String sub8 = snapshot.child("subject08/c").getValue().toString();
                String sub9 = snapshot.child("subject09/c").getValue().toString();
                String sub10 = snapshot.child("subject10/c").getValue().toString();


                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    ///////////////////////////////////1nd year 2nd sem/////////////////////////////////////////
    private void beed1y2sSched_A() {

        DatabaseReference database = FirebaseDatabase.getInstance().getReference("course_curriculum");

        database.child("beed").child("first_year").child("second_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/a").getValue().toString();
                String sub2 = snapshot.child("subject02/a").getValue().toString();
                String sub3 = snapshot.child("subject03/a").getValue().toString();
                String sub4 = snapshot.child("subject04/a").getValue().toString();
                String sub5 = snapshot.child("subject05/a").getValue().toString();
                String sub6 = snapshot.child("subject06/a").getValue().toString();
                String sub7 = snapshot.child("subject07/a").getValue().toString();
                String sub8 = snapshot.child("subject08/a").getValue().toString();
                String sub9 = snapshot.child("subject09/a").getValue().toString();
                String sub10 = snapshot.child("subject10/a").getValue().toString();


                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
    private void beed1y2sSched_B(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("course_curriculum");

        database.child("beed").child("first_year").child("second_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/b").getValue().toString();
                String sub2 = snapshot.child("subject02/b").getValue().toString();
                String sub3 = snapshot.child("subject03/b").getValue().toString();
                String sub4 = snapshot.child("subject04/b").getValue().toString();
                String sub5 = snapshot.child("subject05/b").getValue().toString();
                String sub6 = snapshot.child("subject06/b").getValue().toString();
                String sub7 = snapshot.child("subject07/b").getValue().toString();
                String sub8 = snapshot.child("subject08/b").getValue().toString();
                String sub9 = snapshot.child("subject09/b").getValue().toString();
                String sub10 = snapshot.child("subject10/b").getValue().toString();


                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
    private void beed1y2sSched_C(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("course_curriculum");

        database.child("beed").child("first_year").child("second_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/c").getValue().toString();
                String sub2 = snapshot.child("subject02/c").getValue().toString();
                String sub3 = snapshot.child("subject03/c").getValue().toString();
                String sub4 = snapshot.child("subject04/c").getValue().toString();
                String sub5 = snapshot.child("subject05/c").getValue().toString();
                String sub6 = snapshot.child("subject06/c").getValue().toString();
                String sub7 = snapshot.child("subject07/c").getValue().toString();
                String sub8 = snapshot.child("subject08/c").getValue().toString();
                String sub9 = snapshot.child("subject09/c").getValue().toString();
                String sub10 = snapshot.child("subject10/c").getValue().toString();


                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    ///////////////////////////////////2nd year 1st sem/////////////////////////////////////////
    private void beed2y1sSched_A() {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("course_curriculum");
        database.child("beed").child("second_year").child("first_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/a").getValue().toString();
                String sub2 = snapshot.child("subject02/a").getValue().toString();
                String sub3 = snapshot.child("subject03/a").getValue().toString();
                String sub4 = snapshot.child("subject04/a").getValue().toString();
                String sub5 = snapshot.child("subject05/a").getValue().toString();
                String sub6 = snapshot.child("subject06/a").getValue().toString();
                String sub7 = snapshot.child("subject07/a").getValue().toString();
                String sub8 = snapshot.child("subject08/a").getValue().toString();
                String sub9 = snapshot.child("subject09/a").getValue().toString();
                String sub10 = snapshot.child("subject10/a").getValue().toString();


                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
    private void beed2y1sSched_B(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("course_curriculum");
        database.child("beed").child("second_year").child("first_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String sub1 = snapshot.child("subject01/b").getValue().toString();
                String sub2 = snapshot.child("subject02/b").getValue().toString();
                String sub3 = snapshot.child("subject03/b").getValue().toString();
                String sub4 = snapshot.child("subject04/b").getValue().toString();
                String sub5 = snapshot.child("subject05/b").getValue().toString();
                String sub6 = snapshot.child("subject06/b").getValue().toString();
                String sub7 = snapshot.child("subject07/b").getValue().toString();
                String sub8 = snapshot.child("subject08/b").getValue().toString();
                String sub9 = snapshot.child("subject09/b").getValue().toString();
                String sub10 = snapshot.child("subject10/b").getValue().toString();

                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
    private void beed2y1sSched_C(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("course_curriculum");

        database.child("beed").child("second_year").child("first_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/c").getValue().toString();
                String sub2 = snapshot.child("subject02/c").getValue().toString();
                String sub3 = snapshot.child("subject03/c").getValue().toString();
                String sub4 = snapshot.child("subject04/c").getValue().toString();
                String sub5 = snapshot.child("subject05/c").getValue().toString();
                String sub6 = snapshot.child("subject06/c").getValue().toString();
                String sub7 = snapshot.child("subject07/c").getValue().toString();
                String sub8 = snapshot.child("subject08/c").getValue().toString();
                String sub9 = snapshot.child("subject09/c").getValue().toString();
                String sub10 = snapshot.child("subject10/c").getValue().toString();

                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    ///////////////////////////////////2nd year 2nd sem/////////////////////////////////////////
    private void beed2y2sSched_A(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("course_curriculum");
        database.child("beed").child("second_year").child("second_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/a").getValue().toString();
                String sub2 = snapshot.child("subject02/a").getValue().toString();
                String sub3 = snapshot.child("subject03/a").getValue().toString();
                String sub4 = snapshot.child("subject04/a").getValue().toString();
                String sub5 = snapshot.child("subject05/a").getValue().toString();
                String sub6 = snapshot.child("subject06/a").getValue().toString();
                String sub7 = snapshot.child("subject07/a").getValue().toString();
                String sub8 = snapshot.child("subject08/a").getValue().toString();
                String sub9 = snapshot.child("subject09/a").getValue().toString();
                String sub10 = snapshot.child("subject10/a").getValue().toString();


                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);


            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void beed2y2sSched_B(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("course_curriculum");
        database.child("beed").child("second_year").child("second_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/b").getValue().toString();
                String sub2 = snapshot.child("subject02/b").getValue().toString();
                String sub3 = snapshot.child("subject03/b").getValue().toString();
                String sub4 = snapshot.child("subject04/b").getValue().toString();
                String sub5 = snapshot.child("subject05/b").getValue().toString();
                String sub6 = snapshot.child("subject06/b").getValue().toString();
                String sub7 = snapshot.child("subject07/b").getValue().toString();
                String sub8 = snapshot.child("subject08/b").getValue().toString();
                String sub9 = snapshot.child("subject09/b").getValue().toString();
                String sub10 = snapshot.child("subject10/b").getValue().toString();
                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void beed2y2sSched_C(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("course_curriculum");
        database.child("beed").child("second_year").child("second_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String sub1 = snapshot.child("subject01/c").getValue().toString();
                String sub2 = snapshot.child("subject02/c").getValue().toString();
                String sub3 = snapshot.child("subject03/c").getValue().toString();
                String sub4 = snapshot.child("subject04/c").getValue().toString();
                String sub5 = snapshot.child("subject05/c").getValue().toString();
                String sub6 = snapshot.child("subject06/c").getValue().toString();
                String sub7 = snapshot.child("subject07/c").getValue().toString();
                String sub8 = snapshot.child("subject08/c").getValue().toString();
                String sub9 = snapshot.child("subject09/c").getValue().toString();
                String sub10 = snapshot.child("subject10/c").getValue().toString();


                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    ///////////////////////////////////3rd year 1st sem/////////////////////////////////////////
    private void beed3y1sSched_A(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("course_curriculum");

        database.child("beed").child("third_year").child("first_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/a").getValue().toString();
                String sub2 = snapshot.child("subject02/a").getValue().toString();
                String sub3 = snapshot.child("subject03/a").getValue().toString();
                String sub4 = snapshot.child("subject04/a").getValue().toString();
                String sub5 = snapshot.child("subject05/a").getValue().toString();
                String sub6 = snapshot.child("subject06/a").getValue().toString();
                String sub7 = snapshot.child("subject07/a").getValue().toString();
                String sub8 = snapshot.child("subject08/a").getValue().toString();
                String sub9 = snapshot.child("subject09/a").getValue().toString();
                String sub10 = snapshot.child("subject10/a").getValue().toString();


                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);


            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void beed3y1sSched_B(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("course_curriculum");
        database.child("beed").child("third_year").child("first_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/b").getValue().toString();
                String sub2 = snapshot.child("subject02/b").getValue().toString();
                String sub3 = snapshot.child("subject03/b").getValue().toString();
                String sub4 = snapshot.child("subject04/b").getValue().toString();
                String sub5 = snapshot.child("subject05/b").getValue().toString();
                String sub6 = snapshot.child("subject06/b").getValue().toString();
                String sub7 = snapshot.child("subject07/b").getValue().toString();
                String sub8 = snapshot.child("subject08/b").getValue().toString();
                String sub9 = snapshot.child("subject09/b").getValue().toString();
                String sub10 = snapshot.child("subject10/b").getValue().toString();


                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void beed3y1sSched_C(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("course_curriculum");

        database.child("beed").child("third_year").child("first_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/c").getValue().toString();
                String sub2 = snapshot.child("subject02/c").getValue().toString();
                String sub3 = snapshot.child("subject03/c").getValue().toString();
                String sub4 = snapshot.child("subject04/c").getValue().toString();
                String sub5 = snapshot.child("subject05/c").getValue().toString();
                String sub6 = snapshot.child("subject06/c").getValue().toString();
                String sub7 = snapshot.child("subject07/c").getValue().toString();
                String sub8 = snapshot.child("subject08/c").getValue().toString();
                String sub9 = snapshot.child("subject09/c").getValue().toString();
                String sub10 = snapshot.child("subject10/c").getValue().toString();


                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    ///////////////////////////////////3rd year 2nd sem/////////////////////////////////////////

    private void beed3y2sSched_A(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("course_curriculum");
        database.child("beed").child("third_year").child("second_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/a").getValue().toString();
                String sub2 = snapshot.child("subject02/a").getValue().toString();
                String sub3 = snapshot.child("subject03/a").getValue().toString();
                String sub4 = snapshot.child("subject04/a").getValue().toString();
                String sub5 = snapshot.child("subject05/a").getValue().toString();
                String sub6 = snapshot.child("subject06/a").getValue().toString();
                String sub7 = snapshot.child("subject07/a").getValue().toString();
                String sub8 = snapshot.child("subject08/a").getValue().toString();
                String sub9 = snapshot.child("subject09/a").getValue().toString();
                String sub10 = snapshot.child("subject10/a").getValue().toString();


                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);


            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void beed3y2sSched_B(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("course_curriculum");

        database.child("beed").child("third_year").child("second_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/b").getValue().toString();
                String sub2 = snapshot.child("subject02/b").getValue().toString();
                String sub3 = snapshot.child("subject03/b").getValue().toString();
                String sub4 = snapshot.child("subject04/b").getValue().toString();
                String sub5 = snapshot.child("subject05/b").getValue().toString();
                String sub6 = snapshot.child("subject06/b").getValue().toString();
                String sub7 = snapshot.child("subject07/b").getValue().toString();
                String sub8 = snapshot.child("subject08/b").getValue().toString();
                String sub9 = snapshot.child("subject09/b").getValue().toString();
                String sub10 = snapshot.child("subject10/b").getValue().toString();

                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void beed3y2sSched_C(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("course_curriculum");

        database.child("beed").child("third_year").child("second_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/c").getValue().toString();
                String sub2 = snapshot.child("subject02/c").getValue().toString();
                String sub3 = snapshot.child("subject03/c").getValue().toString();
                String sub4 = snapshot.child("subject04/c").getValue().toString();
                String sub5 = snapshot.child("subject05/c").getValue().toString();
                String sub6 = snapshot.child("subject06/c").getValue().toString();
                String sub7 = snapshot.child("subject07/c").getValue().toString();
                String sub8 = snapshot.child("subject08/c").getValue().toString();
                String sub9 = snapshot.child("subject09/c").getValue().toString();
                String sub10 = snapshot.child("subject10/c").getValue().toString();


                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    ///////////////////////////////////4th year 1st sem/////////////////////////////////////////

    private void beed4y1sSched_A(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("course_curriculum");

        database.child("beed").child("fourth_year").child("first_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/a").getValue().toString();
                String sub2 = snapshot.child("subject02/a").getValue().toString();
                String sub3 = snapshot.child("subject03/a").getValue().toString();
                String sub4 = snapshot.child("subject04/a").getValue().toString();
                String sub5 = snapshot.child("subject05/a").getValue().toString();
                String sub6 = snapshot.child("subject06/a").getValue().toString();
                String sub7 = snapshot.child("subject07/a").getValue().toString();
                String sub8 = snapshot.child("subject08/a").getValue().toString();
                String sub9 = snapshot.child("subject09/a").getValue().toString();
                String sub10 = snapshot.child("subject10/a").getValue().toString();


                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);


            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void beed4y1sSched_B(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("course_curriculum");

        database.child("beed").child("fourth_year").child("first_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/b").getValue().toString();
                String sub2 = snapshot.child("subject02/b").getValue().toString();
                String sub3 = snapshot.child("subject03/b").getValue().toString();
                String sub4 = snapshot.child("subject04/b").getValue().toString();
                String sub5 = snapshot.child("subject05/b").getValue().toString();
                String sub6 = snapshot.child("subject06/b").getValue().toString();
                String sub7 = snapshot.child("subject07/b").getValue().toString();
                String sub8 = snapshot.child("subject08/b").getValue().toString();
                String sub9 = snapshot.child("subject09/b").getValue().toString();
                String sub10 = snapshot.child("subject10/b").getValue().toString();


                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void beed4y1sSched_C(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("course_curriculum");

        database.child("beed").child("fourth_year").child("first_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/c").getValue().toString();
                String sub2 = snapshot.child("subject02/c").getValue().toString();
                String sub3 = snapshot.child("subject03/c").getValue().toString();
                String sub4 = snapshot.child("subject04/c").getValue().toString();
                String sub5 = snapshot.child("subject05/c").getValue().toString();
                String sub6 = snapshot.child("subject06/c").getValue().toString();
                String sub7 = snapshot.child("subject07/c").getValue().toString();
                String sub8 = snapshot.child("subject08/c").getValue().toString();
                String sub9 = snapshot.child("subject09/c").getValue().toString();
                String sub10 = snapshot.child("subject10/c").getValue().toString();


                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    ///////////////////////////////////4th year 2nd sem/////////////////////////////////////////

    private void beed4y2sSched_A(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("course_curriculum");

        database.child("beed").child("fourth_year").child("second_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/a").getValue().toString();
                String sub2 = snapshot.child("subject02/a").getValue().toString();
                String sub3 = snapshot.child("subject03/a").getValue().toString();
                String sub4 = snapshot.child("subject04/a").getValue().toString();
                String sub5 = snapshot.child("subject05/a").getValue().toString();
                String sub6 = snapshot.child("subject06/a").getValue().toString();
                String sub7 = snapshot.child("subject07/a").getValue().toString();
                String sub8 = snapshot.child("subject08/a").getValue().toString();
                String sub9 = snapshot.child("subject09/a").getValue().toString();
                String sub10 = snapshot.child("subject10/a").getValue().toString();


                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);


            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void beed4y2sSched_B(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("course_curriculum");
        database.child("beed").child("fourth_year").child("second_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/b").getValue().toString();
                String sub2 = snapshot.child("subject02/b").getValue().toString();
                String sub3 = snapshot.child("subject03/b").getValue().toString();
                String sub4 = snapshot.child("subject04/b").getValue().toString();
                String sub5 = snapshot.child("subject05/b").getValue().toString();
                String sub6 = snapshot.child("subject06/b").getValue().toString();
                String sub7 = snapshot.child("subject07/b").getValue().toString();
                String sub8 = snapshot.child("subject08/b").getValue().toString();
                String sub9 = snapshot.child("subject09/b").getValue().toString();
                String sub10 = snapshot.child("subject10/b").getValue().toString();

                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void beed4y2sSched_C(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("course_curriculum");
        database.child("beed").child("fourth_year").child("second_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/c").getValue().toString();
                String sub2 = snapshot.child("subject02/c").getValue().toString();
                String sub3 = snapshot.child("subject03/c").getValue().toString();
                String sub4 = snapshot.child("subject04/c").getValue().toString();
                String sub5 = snapshot.child("subject05/c").getValue().toString();
                String sub6 = snapshot.child("subject06/c").getValue().toString();
                String sub7 = snapshot.child("subject07/c").getValue().toString();
                String sub8 = snapshot.child("subject08/c").getValue().toString();
                String sub9 = snapshot.child("subject09/c").getValue().toString();
                String sub10 = snapshot.child("subject10/c").getValue().toString();

                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    //////////////////////////////////// BSOA SCHEDULE SECTION //////////////////////////////////////////////////////

    ///////////////////////////////////1st year 1st sem/////////////////////////////////////////
    private void bsoa1y1sSched_A() {

        DatabaseReference database = FirebaseDatabase.getInstance().getReference("course_curriculum");

        database.child("bsba").child("first_year").child("first_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/a").getValue().toString();
                String sub2 = snapshot.child("subject02/a").getValue().toString();
                String sub3 = snapshot.child("subject03/a").getValue().toString();
                String sub4 = snapshot.child("subject04/a").getValue().toString();
                String sub5 = snapshot.child("subject05/a").getValue().toString();
                String sub6 = snapshot.child("subject06/a").getValue().toString();
                String sub7 = snapshot.child("subject07/a").getValue().toString();
                String sub8 = snapshot.child("subject08/a").getValue().toString();
                String sub9 = snapshot.child("subject09/a").getValue().toString();
                String sub10 = snapshot.child("subject10/a").getValue().toString();


                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
    private void bsoa1y1sSched_B(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("course_curriculum");

        database.child("bsba").child("first_year").child("first_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/b").getValue().toString();
                String sub2 = snapshot.child("subject02/b").getValue().toString();
                String sub3 = snapshot.child("subject03/b").getValue().toString();
                String sub4 = snapshot.child("subject04/b").getValue().toString();
                String sub5 = snapshot.child("subject05/b").getValue().toString();
                String sub6 = snapshot.child("subject06/b").getValue().toString();
                String sub7 = snapshot.child("subject07/b").getValue().toString();
                String sub8 = snapshot.child("subject08/b").getValue().toString();
                String sub9 = snapshot.child("subject09/b").getValue().toString();
                String sub10 = snapshot.child("subject10/b").getValue().toString();

                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
    private void bsoa1y1sSched_C(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("course_curriculum");

        database.child("bsba").child("first_year").child("first_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/c").getValue().toString();
                String sub2 = snapshot.child("subject02/c").getValue().toString();
                String sub3 = snapshot.child("subject03/c").getValue().toString();
                String sub4 = snapshot.child("subject04/c").getValue().toString();
                String sub5 = snapshot.child("subject05/c").getValue().toString();
                String sub6 = snapshot.child("subject06/c").getValue().toString();
                String sub7 = snapshot.child("subject07/c").getValue().toString();
                String sub8 = snapshot.child("subject08/c").getValue().toString();
                String sub9 = snapshot.child("subject09/c").getValue().toString();
                String sub10 = snapshot.child("subject10/c").getValue().toString();

                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    ///////////////////////////////////1nd year 2nd sem/////////////////////////////////////////
    private void bsoa1y2sSched_A() {

        DatabaseReference database = FirebaseDatabase.getInstance().getReference("course_curriculum");

        database.child("bsoa").child("first_year").child("second_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/a").getValue().toString();
                String sub2 = snapshot.child("subject02/a").getValue().toString();
                String sub3 = snapshot.child("subject03/a").getValue().toString();
                String sub4 = snapshot.child("subject04/a").getValue().toString();
                String sub5 = snapshot.child("subject05/a").getValue().toString();
                String sub6 = snapshot.child("subject06/a").getValue().toString();
                String sub7 = snapshot.child("subject07/a").getValue().toString();
                String sub8 = snapshot.child("subject08/a").getValue().toString();
                String sub9 = snapshot.child("subject09/a").getValue().toString();
                String sub10 = snapshot.child("subject10/a").getValue().toString();


                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);




            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
    private void bsoa1y2sSched_B(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("course_curriculum");

        database.child("bsoa").child("first_year").child("second_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/b").getValue().toString();
                String sub2 = snapshot.child("subject02/b").getValue().toString();
                String sub3 = snapshot.child("subject03/b").getValue().toString();
                String sub4 = snapshot.child("subject04/b").getValue().toString();
                String sub5 = snapshot.child("subject05/b").getValue().toString();
                String sub6 = snapshot.child("subject06/b").getValue().toString();
                String sub7 = snapshot.child("subject07/b").getValue().toString();
                String sub8 = snapshot.child("subject08/b").getValue().toString();
                String sub9 = snapshot.child("subject09/b").getValue().toString();
                String sub10 = snapshot.child("subject10/b").getValue().toString();

                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
    private void bsoa1y2sSched_C(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("course_curriculum");

        database.child("bsba").child("first_year").child("second_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String sub1 = snapshot.child("subject01/c").getValue().toString();
                String sub2 = snapshot.child("subject02/c").getValue().toString();
                String sub3 = snapshot.child("subject03/c").getValue().toString();
                String sub4 = snapshot.child("subject04/c").getValue().toString();
                String sub5 = snapshot.child("subject05/c").getValue().toString();
                String sub6 = snapshot.child("subject06/c").getValue().toString();
                String sub7 = snapshot.child("subject07/c").getValue().toString();
                String sub8 = snapshot.child("subject08/c").getValue().toString();
                String sub9 = snapshot.child("subject09/c").getValue().toString();
                String sub10 = snapshot.child("subject10/c").getValue().toString();


                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    ///////////////////////////////////2nd year 1st sem/////////////////////////////////////////
    private void bsoa2y1sSched_A() {

        DatabaseReference database = FirebaseDatabase.getInstance().getReference("course_curriculum");

        database.child("bsoa").child("second_year").child("first_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/a").getValue().toString();
                String sub2 = snapshot.child("subject02/a").getValue().toString();
                String sub3 = snapshot.child("subject03/a").getValue().toString();
                String sub4 = snapshot.child("subject04/a").getValue().toString();
                String sub5 = snapshot.child("subject05/a").getValue().toString();
                String sub6 = snapshot.child("subject06/a").getValue().toString();
                String sub7 = snapshot.child("subject07/a").getValue().toString();
                String sub8 = snapshot.child("subject08/a").getValue().toString();
                String sub9 = snapshot.child("subject09/a").getValue().toString();
                String sub10 = snapshot.child("subject10/a").getValue().toString();


                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
    private void bsoa2y1sSched_B(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("course_curriculum");

        database.child("bsoa").child("second_year").child("first_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/b").getValue().toString();
                String sub2 = snapshot.child("subject02/b").getValue().toString();
                String sub3 = snapshot.child("subject03/b").getValue().toString();
                String sub4 = snapshot.child("subject04/b").getValue().toString();
                String sub5 = snapshot.child("subject05/b").getValue().toString();
                String sub6 = snapshot.child("subject06/b").getValue().toString();
                String sub7 = snapshot.child("subject07/b").getValue().toString();
                String sub8 = snapshot.child("subject08/b").getValue().toString();
                String sub9 = snapshot.child("subject09/b").getValue().toString();
                String sub10 = snapshot.child("subject10/b").getValue().toString();
                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
    private void bsoa2y1sSched_C(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("course_curriculum");

        database.child("bsoa").child("second_year").child("first_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String sub1 = snapshot.child("subject01/c").getValue().toString();
                String sub2 = snapshot.child("subject02/c").getValue().toString();
                String sub3 = snapshot.child("subject03/c").getValue().toString();
                String sub4 = snapshot.child("subject04/c").getValue().toString();
                String sub5 = snapshot.child("subject05/c").getValue().toString();
                String sub6 = snapshot.child("subject06/c").getValue().toString();
                String sub7 = snapshot.child("subject07/c").getValue().toString();
                String sub8 = snapshot.child("subject08/c").getValue().toString();
                String sub9 = snapshot.child("subject09/c").getValue().toString();
                String sub10 = snapshot.child("subject10/c").getValue().toString();


                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    ///////////////////////////////////2nd year 2nd sem/////////////////////////////////////////
    private void bsoa2y2sSched_A(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("course_curriculum");

        database.child("bsoa").child("second_year").child("second_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/a").getValue().toString();
                String sub2 = snapshot.child("subject02/a").getValue().toString();
                String sub3 = snapshot.child("subject03/a").getValue().toString();
                String sub4 = snapshot.child("subject04/a").getValue().toString();
                String sub5 = snapshot.child("subject05/a").getValue().toString();
                String sub6 = snapshot.child("subject06/a").getValue().toString();
                String sub7 = snapshot.child("subject07/a").getValue().toString();
                String sub8 = snapshot.child("subject08/a").getValue().toString();
                String sub9 = snapshot.child("subject09/a").getValue().toString();
                String sub10 = snapshot.child("subject10/a").getValue().toString();


                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);


            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void bsoa2y2sSched_B(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("course_curriculum");

        database.child("bsoa").child("second_year").child("second_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/b").getValue().toString();
                String sub2 = snapshot.child("subject02/b").getValue().toString();
                String sub3 = snapshot.child("subject03/b").getValue().toString();
                String sub4 = snapshot.child("subject04/b").getValue().toString();
                String sub5 = snapshot.child("subject05/b").getValue().toString();
                String sub6 = snapshot.child("subject06/b").getValue().toString();
                String sub7 = snapshot.child("subject07/b").getValue().toString();
                String sub8 = snapshot.child("subject08/b").getValue().toString();
                String sub9 = snapshot.child("subject09/b").getValue().toString();
                String sub10 = snapshot.child("subject10/b").getValue().toString();

                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void bsoa2y2sSched_C(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("course_curriculum");

        database.child("bsoa").child("second_year").child("second_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/c").getValue().toString();
                String sub2 = snapshot.child("subject02/c").getValue().toString();
                String sub3 = snapshot.child("subject03/c").getValue().toString();
                String sub4 = snapshot.child("subject04/c").getValue().toString();
                String sub5 = snapshot.child("subject05/c").getValue().toString();
                String sub6 = snapshot.child("subject06/c").getValue().toString();
                String sub7 = snapshot.child("subject07/c").getValue().toString();
                String sub8 = snapshot.child("subject08/c").getValue().toString();
                String sub9 = snapshot.child("subject09/c").getValue().toString();
                String sub10 = snapshot.child("subject10/c").getValue().toString();

                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    ///////////////////////////////////3rd year 1st sem/////////////////////////////////////////
    private void bsoa3y1sSched_A(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("course_curriculum");

        database.child("bsoa").child("third_year").child("first_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/a").getValue().toString();
                String sub2 = snapshot.child("subject02/a").getValue().toString();
                String sub3 = snapshot.child("subject03/a").getValue().toString();
                String sub4 = snapshot.child("subject04/a").getValue().toString();
                String sub5 = snapshot.child("subject05/a").getValue().toString();
                String sub6 = snapshot.child("subject06/a").getValue().toString();
                String sub7 = snapshot.child("subject07/a").getValue().toString();
                String sub8 = snapshot.child("subject08/a").getValue().toString();
                String sub9 = snapshot.child("subject09/a").getValue().toString();
                String sub10 = snapshot.child("subject10/a").getValue().toString();

                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void bsoa3y1sSched_B(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("course_curriculum");

        database.child("bsoa").child("third_year").child("first_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/b").getValue().toString();
                String sub2 = snapshot.child("subject02/b").getValue().toString();
                String sub3 = snapshot.child("subject03/b").getValue().toString();
                String sub4 = snapshot.child("subject04/b").getValue().toString();
                String sub5 = snapshot.child("subject05/b").getValue().toString();
                String sub6 = snapshot.child("subject06/b").getValue().toString();
                String sub7 = snapshot.child("subject07/b").getValue().toString();
                String sub8 = snapshot.child("subject08/b").getValue().toString();
                String sub9 = snapshot.child("subject09/b").getValue().toString();
                String sub10 = snapshot.child("subject10/b").getValue().toString();


                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void bsoa3y1sSched_C(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("course_curriculum");

        database.child("bsoa").child("third_year").child("first_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/c").getValue().toString();
                String sub2 = snapshot.child("subject02/c").getValue().toString();
                String sub3 = snapshot.child("subject03/c").getValue().toString();
                String sub4 = snapshot.child("subject04/c").getValue().toString();
                String sub5 = snapshot.child("subject05/c").getValue().toString();
                String sub6 = snapshot.child("subject06/c").getValue().toString();
                String sub7 = snapshot.child("subject07/c").getValue().toString();
                String sub8 = snapshot.child("subject08/c").getValue().toString();
                String sub9 = snapshot.child("subject09/c").getValue().toString();
                String sub10 = snapshot.child("subject10/c").getValue().toString();

                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    ///////////////////////////////////3rd year 2nd sem/////////////////////////////////////////

    private void bsoa3y2sSched_A(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("course_curriculum");

        database.child("bsoa").child("third_year").child("second_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/a").getValue().toString();
                String sub2 = snapshot.child("subject02/a").getValue().toString();
                String sub3 = snapshot.child("subject03/a").getValue().toString();
                String sub4 = snapshot.child("subject04/a").getValue().toString();
                String sub5 = snapshot.child("subject05/a").getValue().toString();
                String sub6 = snapshot.child("subject06/a").getValue().toString();
                String sub7 = snapshot.child("subject07/a").getValue().toString();
                String sub8 = snapshot.child("subject08/a").getValue().toString();
                String sub9 = snapshot.child("subject09/a").getValue().toString();
                String sub10 = snapshot.child("subject10/a").getValue().toString();

                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void bsoa3y2sSched_B(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("course_curriculum");

        database.child("bsoa").child("third_year").child("second_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/b").getValue().toString();
                String sub2 = snapshot.child("subject02/b").getValue().toString();
                String sub3 = snapshot.child("subject03/b").getValue().toString();
                String sub4 = snapshot.child("subject04/b").getValue().toString();
                String sub5 = snapshot.child("subject05/b").getValue().toString();
                String sub6 = snapshot.child("subject06/b").getValue().toString();
                String sub7 = snapshot.child("subject07/b").getValue().toString();
                String sub8 = snapshot.child("subject08/b").getValue().toString();
                String sub9 = snapshot.child("subject09/b").getValue().toString();
                String sub10 = snapshot.child("subject10/b").getValue().toString();


                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void bsoa3y2sSched_C(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("course_curriculum");

        database.child("bsoa").child("third_year").child("second_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/c").getValue().toString();
                String sub2 = snapshot.child("subject02/c").getValue().toString();
                String sub3 = snapshot.child("subject03/c").getValue().toString();
                String sub4 = snapshot.child("subject04/c").getValue().toString();
                String sub5 = snapshot.child("subject05/c").getValue().toString();
                String sub6 = snapshot.child("subject06/c").getValue().toString();
                String sub7 = snapshot.child("subject07/c").getValue().toString();
                String sub8 = snapshot.child("subject08/c").getValue().toString();
                String sub9 = snapshot.child("subject09/c").getValue().toString();
                String sub10 = snapshot.child("subject10/c").getValue().toString();

                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    ///////////////////////////////////4th year 1st sem/////////////////////////////////////////

    private void bsoa4y1sSched_A(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("course_curriculum");

        database.child("bsoa").child("fourth_year").child("first_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/a").getValue().toString();
                String sub2 = snapshot.child("subject02/a").getValue().toString();
                String sub3 = snapshot.child("subject03/a").getValue().toString();
                String sub4 = snapshot.child("subject04/a").getValue().toString();
                String sub5 = snapshot.child("subject05/a").getValue().toString();
                String sub6 = snapshot.child("subject06/a").getValue().toString();
                String sub7 = snapshot.child("subject07/a").getValue().toString();
                String sub8 = snapshot.child("subject08/a").getValue().toString();
                String sub9 = snapshot.child("subject09/a").getValue().toString();
                String sub10 = snapshot.child("subject10/a").getValue().toString();

                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void bsoa4y1sSched_B(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("course_curriculum");

        database.child("bsoa").child("fourth_year").child("first_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/b").getValue().toString();
                String sub2 = snapshot.child("subject02/b").getValue().toString();
                String sub3 = snapshot.child("subject03/b").getValue().toString();
                String sub4 = snapshot.child("subject04/b").getValue().toString();
                String sub5 = snapshot.child("subject05/b").getValue().toString();
                String sub6 = snapshot.child("subject06/b").getValue().toString();
                String sub7 = snapshot.child("subject07/b").getValue().toString();
                String sub8 = snapshot.child("subject08/b").getValue().toString();
                String sub9 = snapshot.child("subject09/b").getValue().toString();
                String sub10 = snapshot.child("subject10/b").getValue().toString();

                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void bsoa4y1sSched_C(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("course_curriculum");

        database.child("bsoa").child("fourth_year").child("first_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/c").getValue().toString();
                String sub2 = snapshot.child("subject02/c").getValue().toString();
                String sub3 = snapshot.child("subject03/c").getValue().toString();
                String sub4 = snapshot.child("subject04/c").getValue().toString();
                String sub5 = snapshot.child("subject05/c").getValue().toString();
                String sub6 = snapshot.child("subject06/c").getValue().toString();
                String sub7 = snapshot.child("subject07/c").getValue().toString();
                String sub8 = snapshot.child("subject08/c").getValue().toString();
                String sub9 = snapshot.child("subject09/c").getValue().toString();
                String sub10 = snapshot.child("subject10/c").getValue().toString();


                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    ///////////////////////////////////4th year 2nd sem/////////////////////////////////////////

    private void bsoa4y2sSched_A(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("course_curriculum");

        database.child("bsoa").child("fourth_year").child("second_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/a").getValue().toString();
                String sub2 = snapshot.child("subject02/a").getValue().toString();
                String sub3 = snapshot.child("subject03/a").getValue().toString();
                String sub4 = snapshot.child("subject04/a").getValue().toString();
                String sub5 = snapshot.child("subject05/a").getValue().toString();
                String sub6 = snapshot.child("subject06/a").getValue().toString();
                String sub7 = snapshot.child("subject07/a").getValue().toString();
                String sub8 = snapshot.child("subject08/a").getValue().toString();
                String sub9 = snapshot.child("subject09/a").getValue().toString();
                String sub10 = snapshot.child("subject10/a").getValue().toString();

                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void bsoa4y2sSched_B(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("course_curriculum");

        database.child("bsoa").child("fourth_year").child("second_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/b").getValue().toString();
                String sub2 = snapshot.child("subject02/b").getValue().toString();
                String sub3 = snapshot.child("subject03/b").getValue().toString();
                String sub4 = snapshot.child("subject04/b").getValue().toString();
                String sub5 = snapshot.child("subject05/b").getValue().toString();
                String sub6 = snapshot.child("subject06/b").getValue().toString();
                String sub7 = snapshot.child("subject07/b").getValue().toString();
                String sub8 = snapshot.child("subject08/b").getValue().toString();
                String sub9 = snapshot.child("subject09/b").getValue().toString();
                String sub10 = snapshot.child("subject10/b").getValue().toString();

                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void bsoa4y2sSched_C(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("course_curriculum");

        database.child("bsoa").child("fourth_year").child("second_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/c").getValue().toString();
                String sub2 = snapshot.child("subject02/c").getValue().toString();
                String sub3 = snapshot.child("subject03/c").getValue().toString();
                String sub4 = snapshot.child("subject04/c").getValue().toString();
                String sub5 = snapshot.child("subject05/c").getValue().toString();
                String sub6 = snapshot.child("subject06/c").getValue().toString();
                String sub7 = snapshot.child("subject07/c").getValue().toString();
                String sub8 = snapshot.child("subject08/c").getValue().toString();
                String sub9 = snapshot.child("subject09/c").getValue().toString();
                String sub10 = snapshot.child("subject10/c").getValue().toString();

                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    //////////////////////////////////// BSBA SCHEDULE SECTION //////////////////////////////////////////////////////

    ///////////////////////////////////1st year 1st sem/////////////////////////////////////////
    private void bsba1y1sSched_A() {

        DatabaseReference database = FirebaseDatabase.getInstance().getReference("schedule");

        database.child("bsba").child("first_year").child("first_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/a").getValue().toString();
                String sub2 = snapshot.child("subject02/a").getValue().toString();
                String sub3 = snapshot.child("subject03/a").getValue().toString();
                String sub4 = snapshot.child("subject04/a").getValue().toString();
                String sub5 = snapshot.child("subject05/a").getValue().toString();
                String sub6 = snapshot.child("subject06/a").getValue().toString();
                String sub7 = snapshot.child("subject07/a").getValue().toString();
                String sub8 = snapshot.child("subject08/a").getValue().toString();
                String sub9 = snapshot.child("subject09/a").getValue().toString();
                String sub10 = snapshot.child("subject10/a").getValue().toString();

                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
    private void bsba1y1sSched_B(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("schedule");

        database.child("bsba").child("first_year").child("first_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/b").getValue().toString();
                String sub2 = snapshot.child("subject02/b").getValue().toString();
                String sub3 = snapshot.child("subject03/b").getValue().toString();
                String sub4 = snapshot.child("subject04/b").getValue().toString();
                String sub5 = snapshot.child("subject05/b").getValue().toString();
                String sub6 = snapshot.child("subject06/b").getValue().toString();
                String sub7 = snapshot.child("subject07/b").getValue().toString();
                String sub8 = snapshot.child("subject08/b").getValue().toString();
                String sub9 = snapshot.child("subject09/b").getValue().toString();
                String sub10 = snapshot.child("subject10/b").getValue().toString();
                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
    private void bsba1y1sSched_C(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("schedule");

        database.child("bsba").child("first_year").child("first_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/c").getValue().toString();
                String sub2 = snapshot.child("subject02/c").getValue().toString();
                String sub3 = snapshot.child("subject03/c").getValue().toString();
                String sub4 = snapshot.child("subject04/c").getValue().toString();
                String sub5 = snapshot.child("subject05/c").getValue().toString();
                String sub6 = snapshot.child("subject06/c").getValue().toString();
                String sub7 = snapshot.child("subject07/c").getValue().toString();
                String sub8 = snapshot.child("subject08/c").getValue().toString();
                String sub9 = snapshot.child("subject09/c").getValue().toString();
                String sub10 = snapshot.child("subject10/c").getValue().toString();

                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    ///////////////////////////////////1nd year 2nd sem/////////////////////////////////////////
    private void bsba1y2sSched_A() {

        DatabaseReference database = FirebaseDatabase.getInstance().getReference("schedule");

        database.child("bsba").child("first_year").child("second_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/a").getValue().toString();
                String sub2 = snapshot.child("subject02/a").getValue().toString();
                String sub3 = snapshot.child("subject03/a").getValue().toString();
                String sub4 = snapshot.child("subject04/a").getValue().toString();
                String sub5 = snapshot.child("subject05/a").getValue().toString();
                String sub6 = snapshot.child("subject06/a").getValue().toString();
                String sub7 = snapshot.child("subject07/a").getValue().toString();
                String sub8 = snapshot.child("subject08/a").getValue().toString();
                String sub9 = snapshot.child("subject09/a").getValue().toString();
                String sub10 = snapshot.child("subject10/a").getValue().toString();

                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
    private void bsba1y2sSched_B(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("schedule");

        database.child("bsba").child("first_year").child("second_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/b").getValue().toString();
                String sub2 = snapshot.child("subject02/b").getValue().toString();
                String sub3 = snapshot.child("subject03/b").getValue().toString();
                String sub4 = snapshot.child("subject04/b").getValue().toString();
                String sub5 = snapshot.child("subject05/b").getValue().toString();
                String sub6 = snapshot.child("subject06/b").getValue().toString();
                String sub7 = snapshot.child("subject07/b").getValue().toString();
                String sub8 = snapshot.child("subject08/b").getValue().toString();
                String sub9 = snapshot.child("subject09/b").getValue().toString();
                String sub10 = snapshot.child("subject10/b").getValue().toString();

                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
    private void bsba1y2sSched_C(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("schedule");

        database.child("bsba").child("first_year").child("second_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/c").getValue().toString();
                String sub2 = snapshot.child("subject02/c").getValue().toString();
                String sub3 = snapshot.child("subject03/c").getValue().toString();
                String sub4 = snapshot.child("subject04/c").getValue().toString();
                String sub5 = snapshot.child("subject05/c").getValue().toString();
                String sub6 = snapshot.child("subject06/c").getValue().toString();
                String sub7 = snapshot.child("subject07/c").getValue().toString();
                String sub8 = snapshot.child("subject08/c").getValue().toString();
                String sub9 = snapshot.child("subject09/c").getValue().toString();
                String sub10 = snapshot.child("subject10/c").getValue().toString();


                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    ///////////////////////////////////2nd year 1st sem/////////////////////////////////////////
    private void bsba2y1sSched_A() {

        DatabaseReference database = FirebaseDatabase.getInstance().getReference("schedule");

        database.child("bsba").child("second_year").child("first_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/a").getValue().toString();
                String sub2 = snapshot.child("subject02/a").getValue().toString();
                String sub3 = snapshot.child("subject03/a").getValue().toString();
                String sub4 = snapshot.child("subject04/a").getValue().toString();
                String sub5 = snapshot.child("subject05/a").getValue().toString();
                String sub6 = snapshot.child("subject06/a").getValue().toString();
                String sub7 = snapshot.child("subject07/a").getValue().toString();
                String sub8 = snapshot.child("subject08/a").getValue().toString();
                String sub9 = snapshot.child("subject09/a").getValue().toString();
                String sub10 = snapshot.child("subject10/a").getValue().toString();


                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
    private void bsba2y1sSched_B(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("schedule");

        database.child("bsba").child("second_year").child("first_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/b").getValue().toString();
                String sub2 = snapshot.child("subject02/b").getValue().toString();
                String sub3 = snapshot.child("subject03/b").getValue().toString();
                String sub4 = snapshot.child("subject04/b").getValue().toString();
                String sub5 = snapshot.child("subject05/b").getValue().toString();
                String sub6 = snapshot.child("subject06/b").getValue().toString();
                String sub7 = snapshot.child("subject07/b").getValue().toString();
                String sub8 = snapshot.child("subject08/b").getValue().toString();
                String sub9 = snapshot.child("subject09/b").getValue().toString();
                String sub10 = snapshot.child("subject10/b").getValue().toString();


                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
    private void bsba2y1sSched_C(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("schedule");

        database.child("bsba").child("second_year").child("first_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/c").getValue().toString();
                String sub2 = snapshot.child("subject02/c").getValue().toString();
                String sub3 = snapshot.child("subject03/c").getValue().toString();
                String sub4 = snapshot.child("subject04/c").getValue().toString();
                String sub5 = snapshot.child("subject05/c").getValue().toString();
                String sub6 = snapshot.child("subject06/c").getValue().toString();
                String sub7 = snapshot.child("subject07/c").getValue().toString();
                String sub8 = snapshot.child("subject08/c").getValue().toString();
                String sub9 = snapshot.child("subject09/c").getValue().toString();
                String sub10 = snapshot.child("subject10/c").getValue().toString();


                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    ///////////////////////////////////2nd year 2nd sem/////////////////////////////////////////
    private void bsba2y2sSched_A(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("schedule");

        database.child("bsba").child("second_year").child("second_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/a").getValue().toString();
                String sub2 = snapshot.child("subject02/a").getValue().toString();
                String sub3 = snapshot.child("subject03/a").getValue().toString();
                String sub4 = snapshot.child("subject04/a").getValue().toString();
                String sub5 = snapshot.child("subject05/a").getValue().toString();
                String sub6 = snapshot.child("subject06/a").getValue().toString();
                String sub7 = snapshot.child("subject07/a").getValue().toString();
                String sub8 = snapshot.child("subject08/a").getValue().toString();
                String sub9 = snapshot.child("subject09/a").getValue().toString();
                String sub10 = snapshot.child("subject10/a").getValue().toString();


                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void bsba2y2sSched_B(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("schedule");

        database.child("bsba").child("second_year").child("second_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/b").getValue().toString();
                String sub2 = snapshot.child("subject02/b").getValue().toString();
                String sub3 = snapshot.child("subject03/b").getValue().toString();
                String sub4 = snapshot.child("subject04/b").getValue().toString();
                String sub5 = snapshot.child("subject05/b").getValue().toString();
                String sub6 = snapshot.child("subject06/b").getValue().toString();
                String sub7 = snapshot.child("subject07/b").getValue().toString();
                String sub8 = snapshot.child("subject08/b").getValue().toString();
                String sub9 = snapshot.child("subject09/b").getValue().toString();
                String sub10 = snapshot.child("subject10/b").getValue().toString();

                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void bsba2y2sSched_C(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("schedule");

        database.child("bsba").child("second_year").child("second_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/c").getValue().toString();
                String sub2 = snapshot.child("subject02/c").getValue().toString();
                String sub3 = snapshot.child("subject03/c").getValue().toString();
                String sub4 = snapshot.child("subject04/c").getValue().toString();
                String sub5 = snapshot.child("subject05/c").getValue().toString();
                String sub6 = snapshot.child("subject06/c").getValue().toString();
                String sub7 = snapshot.child("subject07/c").getValue().toString();
                String sub8 = snapshot.child("subject08/c").getValue().toString();
                String sub9 = snapshot.child("subject09/c").getValue().toString();
                String sub10 = snapshot.child("subject10/c").getValue().toString();


                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    ///////////////////////////////////3rd year 1st sem/////////////////////////////////////////
    private void bsba3y1sSched_A(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("schedule");

        database.child("bsba").child("third_year").child("first_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/a").getValue().toString();
                String sub2 = snapshot.child("subject02/a").getValue().toString();
                String sub3 = snapshot.child("subject03/a").getValue().toString();
                String sub4 = snapshot.child("subject04/a").getValue().toString();
                String sub5 = snapshot.child("subject05/a").getValue().toString();
                String sub6 = snapshot.child("subject06/a").getValue().toString();
                String sub7 = snapshot.child("subject07/a").getValue().toString();
                String sub8 = snapshot.child("subject08/a").getValue().toString();
                String sub9 = snapshot.child("subject09/a").getValue().toString();
                String sub10 = snapshot.child("subject10/a").getValue().toString();

                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void bsba3y1sSched_B(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("schedule");

        database.child("bsba").child("third_year").child("first_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/b").getValue().toString();
                String sub2 = snapshot.child("subject02/b").getValue().toString();
                String sub3 = snapshot.child("subject03/b").getValue().toString();
                String sub4 = snapshot.child("subject04/b").getValue().toString();
                String sub5 = snapshot.child("subject05/b").getValue().toString();
                String sub6 = snapshot.child("subject06/b").getValue().toString();
                String sub7 = snapshot.child("subject07/b").getValue().toString();
                String sub8 = snapshot.child("subject08/b").getValue().toString();
                String sub9 = snapshot.child("subject09/b").getValue().toString();
                String sub10 = snapshot.child("subject10/b").getValue().toString();
                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void bsba3y1sSched_C(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("schedule");

        database.child("bsba").child("third_year").child("first_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/c").getValue().toString();
                String sub2 = snapshot.child("subject02/c").getValue().toString();
                String sub3 = snapshot.child("subject03/c").getValue().toString();
                String sub4 = snapshot.child("subject04/c").getValue().toString();
                String sub5 = snapshot.child("subject05/c").getValue().toString();
                String sub6 = snapshot.child("subject06/c").getValue().toString();
                String sub7 = snapshot.child("subject07/c").getValue().toString();
                String sub8 = snapshot.child("subject08/c").getValue().toString();
                String sub9 = snapshot.child("subject09/c").getValue().toString();
                String sub10 = snapshot.child("subject10/c").getValue().toString();


                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    ///////////////////////////////////3rd year 2nd sem/////////////////////////////////////////

    private void bsba3y2sSched_A(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("schedule");

        database.child("bsba").child("third_year").child("second_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/a").getValue().toString();
                String sub2 = snapshot.child("subject02/a").getValue().toString();
                String sub3 = snapshot.child("subject03/a").getValue().toString();
                String sub4 = snapshot.child("subject04/a").getValue().toString();
                String sub5 = snapshot.child("subject05/a").getValue().toString();
                String sub6 = snapshot.child("subject06/a").getValue().toString();
                String sub7 = snapshot.child("subject07/a").getValue().toString();
                String sub8 = snapshot.child("subject08/a").getValue().toString();
                String sub9 = snapshot.child("subject09/a").getValue().toString();
                String sub10 = snapshot.child("subject10/a").getValue().toString();

                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void bsba3y2sSched_B(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("schedule");

        database.child("bsba").child("third_year").child("second_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/b").getValue().toString();
                String sub2 = snapshot.child("subject02/b").getValue().toString();
                String sub3 = snapshot.child("subject03/b").getValue().toString();
                String sub4 = snapshot.child("subject04/b").getValue().toString();
                String sub5 = snapshot.child("subject05/b").getValue().toString();
                String sub6 = snapshot.child("subject06/b").getValue().toString();
                String sub7 = snapshot.child("subject07/b").getValue().toString();
                String sub8 = snapshot.child("subject08/b").getValue().toString();
                String sub9 = snapshot.child("subject09/b").getValue().toString();
                String sub10 = snapshot.child("subject10/b").getValue().toString();


                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void bsba3y2sSched_C(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("schedule");

        database.child("bsba").child("third_year").child("second_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/c").getValue().toString();
                String sub2 = snapshot.child("subject02/c").getValue().toString();
                String sub3 = snapshot.child("subject03/c").getValue().toString();
                String sub4 = snapshot.child("subject04/c").getValue().toString();
                String sub5 = snapshot.child("subject05/c").getValue().toString();
                String sub6 = snapshot.child("subject06/c").getValue().toString();
                String sub7 = snapshot.child("subject07/c").getValue().toString();
                String sub8 = snapshot.child("subject08/c").getValue().toString();
                String sub9 = snapshot.child("subject09/c").getValue().toString();
                String sub10 = snapshot.child("subject10/c").getValue().toString();


                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    ///////////////////////////////////4th year 1st sem/////////////////////////////////////////

    private void bsba4y1sSched_A(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("schedule");

        database.child("bsba").child("fourth_year").child("first_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/a").getValue().toString();
                String sub2 = snapshot.child("subject02/a").getValue().toString();
                String sub3 = snapshot.child("subject03/a").getValue().toString();
                String sub4 = snapshot.child("subject04/a").getValue().toString();
                String sub5 = snapshot.child("subject05/a").getValue().toString();
                String sub6 = snapshot.child("subject06/a").getValue().toString();
                String sub7 = snapshot.child("subject07/a").getValue().toString();
                String sub8 = snapshot.child("subject08/a").getValue().toString();
                String sub9 = snapshot.child("subject09/a").getValue().toString();
                String sub10 = snapshot.child("subject10/a").getValue().toString();


                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void bsba4y1sSched_B(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("schedule");

        database.child("bsba").child("fourth_year").child("first_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/b").getValue().toString();
                String sub2 = snapshot.child("subject02/b").getValue().toString();
                String sub3 = snapshot.child("subject03/b").getValue().toString();
                String sub4 = snapshot.child("subject04/b").getValue().toString();
                String sub5 = snapshot.child("subject05/b").getValue().toString();
                String sub6 = snapshot.child("subject06/b").getValue().toString();
                String sub7 = snapshot.child("subject07/b").getValue().toString();
                String sub8 = snapshot.child("subject08/b").getValue().toString();
                String sub9 = snapshot.child("subject09/b").getValue().toString();
                String sub10 = snapshot.child("subject10/b").getValue().toString();


                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void bsba4y1sSched_C(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("schedule");

        database.child("bsba").child("fourth_year").child("first_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String sub1 = snapshot.child("subject01/c").getValue().toString();
                String sub2 = snapshot.child("subject02/c").getValue().toString();
                String sub3 = snapshot.child("subject03/c").getValue().toString();
                String sub4 = snapshot.child("subject04/c").getValue().toString();
                String sub5 = snapshot.child("subject05/c").getValue().toString();
                String sub6 = snapshot.child("subject06/c").getValue().toString();
                String sub7 = snapshot.child("subject07/c").getValue().toString();
                String sub8 = snapshot.child("subject08/c").getValue().toString();
                String sub9 = snapshot.child("subject09/c").getValue().toString();
                String sub10 = snapshot.child("subject10/c").getValue().toString();
                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    ///////////////////////////////////4th year 2nd sem/////////////////////////////////////////

    private void bsba4y2sSched_A(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("schedule");

        database.child("bsba").child("fourth_year").child("second_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/a").getValue().toString();
                String sub2 = snapshot.child("subject02/a").getValue().toString();
                String sub3 = snapshot.child("subject03/a").getValue().toString();
                String sub4 = snapshot.child("subject04/a").getValue().toString();
                String sub5 = snapshot.child("subject05/a").getValue().toString();
                String sub6 = snapshot.child("subject06/a").getValue().toString();
                String sub7 = snapshot.child("subject07/a").getValue().toString();
                String sub8 = snapshot.child("subject08/a").getValue().toString();
                String sub9 = snapshot.child("subject09/a").getValue().toString();
                String sub10 = snapshot.child("subject10/a").getValue().toString();


                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void bsba4y2sSched_B(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("schedule");

        database.child("bsba").child("fourth_year").child("second_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/b").getValue().toString();
                String sub2 = snapshot.child("subject02/b").getValue().toString();
                String sub3 = snapshot.child("subject03/b").getValue().toString();
                String sub4 = snapshot.child("subject04/b").getValue().toString();
                String sub5 = snapshot.child("subject05/b").getValue().toString();
                String sub6 = snapshot.child("subject06/b").getValue().toString();
                String sub7 = snapshot.child("subject07/b").getValue().toString();
                String sub8 = snapshot.child("subject08/b").getValue().toString();
                String sub9 = snapshot.child("subject09/b").getValue().toString();
                String sub10 = snapshot.child("subject10/b").getValue().toString();
                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void bsba4y2sSched_C(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("schedule");

        database.child("bsba").child("fourth_year").child("second_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/c").getValue().toString();
                String sub2 = snapshot.child("subject02/c").getValue().toString();
                String sub3 = snapshot.child("subject03/c").getValue().toString();
                String sub4 = snapshot.child("subject04/c").getValue().toString();
                String sub5 = snapshot.child("subject05/c").getValue().toString();
                String sub6 = snapshot.child("subject06/c").getValue().toString();
                String sub7 = snapshot.child("subject07/c").getValue().toString();
                String sub8 = snapshot.child("subject08/c").getValue().toString();
                String sub9 = snapshot.child("subject09/c").getValue().toString();
                String sub10 = snapshot.child("subject10/c").getValue().toString();


                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }



    //////////////////////////////////// ABREED SCHEDULE SECTION //////////////////////////////////////////////////////

    ///////////////////////////////////1st year 1st sem/////////////////////////////////////////
    private void abreed1y1sSched_A() {

        DatabaseReference database = FirebaseDatabase.getInstance().getReference("schedule");

        database.child("bsba").child("first_year").child("first_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/a").getValue().toString();
                String sub2 = snapshot.child("subject02/a").getValue().toString();
                String sub3 = snapshot.child("subject03/a").getValue().toString();
                String sub4 = snapshot.child("subject04/a").getValue().toString();
                String sub5 = snapshot.child("subject05/a").getValue().toString();
                String sub6 = snapshot.child("subject06/a").getValue().toString();
                String sub7 = snapshot.child("subject07/a").getValue().toString();
                String sub8 = snapshot.child("subject08/a").getValue().toString();
                String sub9 = snapshot.child("subject09/a").getValue().toString();
                String sub10 = snapshot.child("subject10/a").getValue().toString();

                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
    private void abreed1y1sSched_B(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("schedule");

        database.child("bsba").child("first_year").child("first_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/b").getValue().toString();
                String sub2 = snapshot.child("subject02/b").getValue().toString();
                String sub3 = snapshot.child("subject03/b").getValue().toString();
                String sub4 = snapshot.child("subject04/b").getValue().toString();
                String sub5 = snapshot.child("subject05/b").getValue().toString();
                String sub6 = snapshot.child("subject06/b").getValue().toString();
                String sub7 = snapshot.child("subject07/b").getValue().toString();
                String sub8 = snapshot.child("subject08/b").getValue().toString();
                String sub9 = snapshot.child("subject09/b").getValue().toString();
                String sub10 = snapshot.child("subject10/b").getValue().toString();

                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
    private void abreed1y1sSched_C(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("schedule");

        database.child("bsba").child("first_year").child("first_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/c").getValue().toString();
                String sub2 = snapshot.child("subject02/c").getValue().toString();
                String sub3 = snapshot.child("subject03/c").getValue().toString();
                String sub4 = snapshot.child("subject04/c").getValue().toString();
                String sub5 = snapshot.child("subject05/c").getValue().toString();
                String sub6 = snapshot.child("subject06/c").getValue().toString();
                String sub7 = snapshot.child("subject07/c").getValue().toString();
                String sub8 = snapshot.child("subject08/c").getValue().toString();
                String sub9 = snapshot.child("subject09/c").getValue().toString();
                String sub10 = snapshot.child("subject10/c").getValue().toString();
                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    ///////////////////////////////////1nd year 2nd sem/////////////////////////////////////////
    private void abreed1y2sSched_A() {

        DatabaseReference database = FirebaseDatabase.getInstance().getReference("schedule");

        database.child("bsba").child("first_year").child("second_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/a").getValue().toString();
                String sub2 = snapshot.child("subject02/a").getValue().toString();
                String sub3 = snapshot.child("subject03/a").getValue().toString();
                String sub4 = snapshot.child("subject04/a").getValue().toString();
                String sub5 = snapshot.child("subject05/a").getValue().toString();
                String sub6 = snapshot.child("subject06/a").getValue().toString();
                String sub7 = snapshot.child("subject07/a").getValue().toString();
                String sub8 = snapshot.child("subject08/a").getValue().toString();
                String sub9 = snapshot.child("subject09/a").getValue().toString();
                String sub10 = snapshot.child("subject10/a").getValue().toString();
                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
    private void abreed1y2sSched_B(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("schedule");

        database.child("bsba").child("first_year").child("second_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/b").getValue().toString();
                String sub2 = snapshot.child("subject02/b").getValue().toString();
                String sub3 = snapshot.child("subject03/b").getValue().toString();
                String sub4 = snapshot.child("subject04/b").getValue().toString();
                String sub5 = snapshot.child("subject05/b").getValue().toString();
                String sub6 = snapshot.child("subject06/b").getValue().toString();
                String sub7 = snapshot.child("subject07/b").getValue().toString();
                String sub8 = snapshot.child("subject08/b").getValue().toString();
                String sub9 = snapshot.child("subject09/b").getValue().toString();
                String sub10 = snapshot.child("subject10/b").getValue().toString();

                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
    private void abreed1y2sSched_C(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("schedule");

        database.child("bsba").child("first_year").child("second_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/c").getValue().toString();
                String sub2 = snapshot.child("subject02/c").getValue().toString();
                String sub3 = snapshot.child("subject03/c").getValue().toString();
                String sub4 = snapshot.child("subject04/c").getValue().toString();
                String sub5 = snapshot.child("subject05/c").getValue().toString();
                String sub6 = snapshot.child("subject06/c").getValue().toString();
                String sub7 = snapshot.child("subject07/c").getValue().toString();
                String sub8 = snapshot.child("subject08/c").getValue().toString();
                String sub9 = snapshot.child("subject09/c").getValue().toString();
                String sub10 = snapshot.child("subject10/c").getValue().toString();


                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    ///////////////////////////////////2nd year 1st sem/////////////////////////////////////////
    private void abreed2y1sSched_A() {

        DatabaseReference database = FirebaseDatabase.getInstance().getReference("schedule");

        database.child("bsba").child("second_year").child("first_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/a").getValue().toString();
                String sub2 = snapshot.child("subject02/a").getValue().toString();
                String sub3 = snapshot.child("subject03/a").getValue().toString();
                String sub4 = snapshot.child("subject04/a").getValue().toString();
                String sub5 = snapshot.child("subject05/a").getValue().toString();
                String sub6 = snapshot.child("subject06/a").getValue().toString();
                String sub7 = snapshot.child("subject07/a").getValue().toString();
                String sub8 = snapshot.child("subject08/a").getValue().toString();
                String sub9 = snapshot.child("subject09/a").getValue().toString();
                String sub10 = snapshot.child("subject10/a").getValue().toString();

                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
    private void abreed2y1sSched_B(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("schedule");

        database.child("bsba").child("second_year").child("first_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/b").getValue().toString();
                String sub2 = snapshot.child("subject02/b").getValue().toString();
                String sub3 = snapshot.child("subject03/b").getValue().toString();
                String sub4 = snapshot.child("subject04/b").getValue().toString();
                String sub5 = snapshot.child("subject05/b").getValue().toString();
                String sub6 = snapshot.child("subject06/b").getValue().toString();
                String sub7 = snapshot.child("subject07/b").getValue().toString();
                String sub8 = snapshot.child("subject08/b").getValue().toString();
                String sub9 = snapshot.child("subject09/b").getValue().toString();
                String sub10 = snapshot.child("subject10/b").getValue().toString();
                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
    private void abreed2y1sSched_C(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("schedule");

        database.child("bsba").child("second_year").child("first_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/c").getValue().toString();
                String sub2 = snapshot.child("subject02/c").getValue().toString();
                String sub3 = snapshot.child("subject03/c").getValue().toString();
                String sub4 = snapshot.child("subject04/c").getValue().toString();
                String sub5 = snapshot.child("subject05/c").getValue().toString();
                String sub6 = snapshot.child("subject06/c").getValue().toString();
                String sub7 = snapshot.child("subject07/c").getValue().toString();
                String sub8 = snapshot.child("subject08/c").getValue().toString();
                String sub9 = snapshot.child("subject09/c").getValue().toString();
                String sub10 = snapshot.child("subject10/c").getValue().toString();

                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    ///////////////////////////////////2nd year 2nd sem/////////////////////////////////////////
    private void abreed2y2sSched_A(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("schedule");

        database.child("bsba").child("second_year").child("second_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/a").getValue().toString();
                String sub2 = snapshot.child("subject02/a").getValue().toString();
                String sub3 = snapshot.child("subject03/a").getValue().toString();
                String sub4 = snapshot.child("subject04/a").getValue().toString();
                String sub5 = snapshot.child("subject05/a").getValue().toString();
                String sub6 = snapshot.child("subject06/a").getValue().toString();
                String sub7 = snapshot.child("subject07/a").getValue().toString();
                String sub8 = snapshot.child("subject08/a").getValue().toString();
                String sub9 = snapshot.child("subject09/a").getValue().toString();
                String sub10 = snapshot.child("subject10/a").getValue().toString();


                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void abreed2y2sSched_B(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("schedule");

        database.child("bsba").child("second_year").child("second_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/b").getValue().toString();
                String sub2 = snapshot.child("subject02/b").getValue().toString();
                String sub3 = snapshot.child("subject03/b").getValue().toString();
                String sub4 = snapshot.child("subject04/b").getValue().toString();
                String sub5 = snapshot.child("subject05/b").getValue().toString();
                String sub6 = snapshot.child("subject06/b").getValue().toString();
                String sub7 = snapshot.child("subject07/b").getValue().toString();
                String sub8 = snapshot.child("subject08/b").getValue().toString();
                String sub9 = snapshot.child("subject09/b").getValue().toString();
                String sub10 = snapshot.child("subject10/b").getValue().toString();

                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void abreed2y2sSched_C(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("schedule");

        database.child("bsba").child("second_year").child("second_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/c").getValue().toString();
                String sub2 = snapshot.child("subject02/c").getValue().toString();
                String sub3 = snapshot.child("subject03/c").getValue().toString();
                String sub4 = snapshot.child("subject04/c").getValue().toString();
                String sub5 = snapshot.child("subject05/c").getValue().toString();
                String sub6 = snapshot.child("subject06/c").getValue().toString();
                String sub7 = snapshot.child("subject07/c").getValue().toString();
                String sub8 = snapshot.child("subject08/c").getValue().toString();
                String sub9 = snapshot.child("subject09/c").getValue().toString();
                String sub10 = snapshot.child("subject10/c").getValue().toString();

                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    ///////////////////////////////////3rd year 1st sem/////////////////////////////////////////
    private void abreed3y1sSched_A(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("schedule");

        database.child("bsba").child("third_year").child("first_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/a").getValue().toString();
                String sub2 = snapshot.child("subject02/a").getValue().toString();
                String sub3 = snapshot.child("subject03/a").getValue().toString();
                String sub4 = snapshot.child("subject04/a").getValue().toString();
                String sub5 = snapshot.child("subject05/a").getValue().toString();
                String sub6 = snapshot.child("subject06/a").getValue().toString();
                String sub7 = snapshot.child("subject07/a").getValue().toString();
                String sub8 = snapshot.child("subject08/a").getValue().toString();
                String sub9 = snapshot.child("subject09/a").getValue().toString();
                String sub10 = snapshot.child("subject10/a").getValue().toString();

                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void abreed3y1sSched_B(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("schedule");

        database.child("bsba").child("third_year").child("first_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/b").getValue().toString();
                String sub2 = snapshot.child("subject02/b").getValue().toString();
                String sub3 = snapshot.child("subject03/b").getValue().toString();
                String sub4 = snapshot.child("subject04/b").getValue().toString();
                String sub5 = snapshot.child("subject05/b").getValue().toString();
                String sub6 = snapshot.child("subject06/b").getValue().toString();
                String sub7 = snapshot.child("subject07/b").getValue().toString();
                String sub8 = snapshot.child("subject08/b").getValue().toString();
                String sub9 = snapshot.child("subject09/b").getValue().toString();
                String sub10 = snapshot.child("subject10/b").getValue().toString();

                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void abreed3y1sSched_C(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("schedule");

        database.child("bsba").child("third_year").child("first_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/c").getValue().toString();
                String sub2 = snapshot.child("subject02/c").getValue().toString();
                String sub3 = snapshot.child("subject03/c").getValue().toString();
                String sub4 = snapshot.child("subject04/c").getValue().toString();
                String sub5 = snapshot.child("subject05/c").getValue().toString();
                String sub6 = snapshot.child("subject06/c").getValue().toString();
                String sub7 = snapshot.child("subject07/c").getValue().toString();
                String sub8 = snapshot.child("subject08/c").getValue().toString();
                String sub9 = snapshot.child("subject09/c").getValue().toString();
                String sub10 = snapshot.child("subject10/c").getValue().toString();

                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    ///////////////////////////////////3rd year 2nd sem/////////////////////////////////////////

    private void abreed3y2sSched_A(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("schedule");

        database.child("bsba").child("third_year").child("second_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/a").getValue().toString();
                String sub2 = snapshot.child("subject02/a").getValue().toString();
                String sub3 = snapshot.child("subject03/a").getValue().toString();
                String sub4 = snapshot.child("subject04/a").getValue().toString();
                String sub5 = snapshot.child("subject05/a").getValue().toString();
                String sub6 = snapshot.child("subject06/a").getValue().toString();
                String sub7 = snapshot.child("subject07/a").getValue().toString();
                String sub8 = snapshot.child("subject08/a").getValue().toString();
                String sub9 = snapshot.child("subject09/a").getValue().toString();
                String sub10 = snapshot.child("subject10/a").getValue().toString();
                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void abreed3y2sSched_B(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("schedule");

        database.child("bsba").child("third_year").child("second_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/b").getValue().toString();
                String sub2 = snapshot.child("subject02/b").getValue().toString();
                String sub3 = snapshot.child("subject03/b").getValue().toString();
                String sub4 = snapshot.child("subject04/b").getValue().toString();
                String sub5 = snapshot.child("subject05/b").getValue().toString();
                String sub6 = snapshot.child("subject06/b").getValue().toString();
                String sub7 = snapshot.child("subject07/b").getValue().toString();
                String sub8 = snapshot.child("subject08/b").getValue().toString();
                String sub9 = snapshot.child("subject09/b").getValue().toString();
                String sub10 = snapshot.child("subject10/b").getValue().toString();


                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void abreed3y2sSched_C(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("schedule");

        database.child("bsba").child("third_year").child("second_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //sectionA
                String sub1 = snapshot.child("subject01/c").getValue().toString();
                String sub2 = snapshot.child("subject02/c").getValue().toString();
                String sub3 = snapshot.child("subject03/c").getValue().toString();
                String sub4 = snapshot.child("subject04/c").getValue().toString();
                String sub5 = snapshot.child("subject05/c").getValue().toString();
                String sub6 = snapshot.child("subject06/c").getValue().toString();
                String sub7 = snapshot.child("subject07/c").getValue().toString();
                String sub8 = snapshot.child("subject08/c").getValue().toString();
                String sub9 = snapshot.child("subject09/c").getValue().toString();
                String sub10 = snapshot.child("subject10/c").getValue().toString();
                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    ///////////////////////////////////4th year 1st sem/////////////////////////////////////////

    private void abreed4y1sSched_A(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("schedule");

        database.child("bsba").child("fourth_year").child("first_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String sub1 = snapshot.child("subject01/a").getValue().toString();
                String sub2 = snapshot.child("subject02/a").getValue().toString();
                String sub3 = snapshot.child("subject03/a").getValue().toString();
                String sub4 = snapshot.child("subject04/a").getValue().toString();
                String sub5 = snapshot.child("subject05/a").getValue().toString();
                String sub6 = snapshot.child("subject06/a").getValue().toString();
                String sub7 = snapshot.child("subject07/a").getValue().toString();
                String sub8 = snapshot.child("subject08/a").getValue().toString();
                String sub9 = snapshot.child("subject09/a").getValue().toString();
                String sub10 = snapshot.child("subject10/a").getValue().toString();
                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void abreed4y1sSched_B(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("schedule");

        database.child("bsba").child("fourth_year").child("first_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/b").getValue().toString();
                String sub2 = snapshot.child("subject02/b").getValue().toString();
                String sub3 = snapshot.child("subject03/b").getValue().toString();
                String sub4 = snapshot.child("subject04/b").getValue().toString();
                String sub5 = snapshot.child("subject05/b").getValue().toString();
                String sub6 = snapshot.child("subject06/b").getValue().toString();
                String sub7 = snapshot.child("subject07/b").getValue().toString();
                String sub8 = snapshot.child("subject08/b").getValue().toString();
                String sub9 = snapshot.child("subject09/b").getValue().toString();
                String sub10 = snapshot.child("subject10/b").getValue().toString();


                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void abreed4y1sSched_C(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("schedule");

        database.child("bsba").child("fourth_year").child("first_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/c").getValue().toString();
                String sub2 = snapshot.child("subject02/c").getValue().toString();
                String sub3 = snapshot.child("subject03/c").getValue().toString();
                String sub4 = snapshot.child("subject04/c").getValue().toString();
                String sub5 = snapshot.child("subject05/c").getValue().toString();
                String sub6 = snapshot.child("subject06/c").getValue().toString();
                String sub7 = snapshot.child("subject07/c").getValue().toString();
                String sub8 = snapshot.child("subject08/c").getValue().toString();
                String sub9 = snapshot.child("subject09/c").getValue().toString();
                String sub10 = snapshot.child("subject10/c").getValue().toString();

                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    ///////////////////////////////////4th year 2nd sem/////////////////////////////////////////

    private void abreed4y2sSched_A(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("schedule");

        database.child("bsba").child("fourth_year").child("second_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String sub1 = snapshot.child("subject01/a").getValue().toString();
                String sub2 = snapshot.child("subject02/a").getValue().toString();
                String sub3 = snapshot.child("subject03/a").getValue().toString();
                String sub4 = snapshot.child("subject04/a").getValue().toString();
                String sub5 = snapshot.child("subject05/a").getValue().toString();
                String sub6 = snapshot.child("subject06/a").getValue().toString();
                String sub7 = snapshot.child("subject07/a").getValue().toString();
                String sub8 = snapshot.child("subject08/a").getValue().toString();
                String sub9 = snapshot.child("subject09/a").getValue().toString();
                String sub10 = snapshot.child("subject10/a").getValue().toString();


                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void abreed4y2sSched_B(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("schedule");

        database.child("bsba").child("fourth_year").child("second_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/b").getValue().toString();
                String sub2 = snapshot.child("subject02/b").getValue().toString();
                String sub3 = snapshot.child("subject03/b").getValue().toString();
                String sub4 = snapshot.child("subject04/b").getValue().toString();
                String sub5 = snapshot.child("subject05/b").getValue().toString();
                String sub6 = snapshot.child("subject06/b").getValue().toString();
                String sub7 = snapshot.child("subject07/b").getValue().toString();
                String sub8 = snapshot.child("subject08/b").getValue().toString();
                String sub9 = snapshot.child("subject09/b").getValue().toString();
                String sub10 = snapshot.child("subject10/b").getValue().toString();
                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void abreed4y2sSched_C(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("schedule");

        database.child("bsba").child("fourth_year").child("second_sem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String sub1 = snapshot.child("subject01/c").getValue().toString();
                String sub2 = snapshot.child("subject02/c").getValue().toString();
                String sub3 = snapshot.child("subject03/c").getValue().toString();
                String sub4 = snapshot.child("subject04/c").getValue().toString();
                String sub5 = snapshot.child("subject05/c").getValue().toString();
                String sub6 = snapshot.child("subject06/c").getValue().toString();
                String sub7 = snapshot.child("subject07/c").getValue().toString();
                String sub8 = snapshot.child("subject08/c").getValue().toString();
                String sub9 = snapshot.child("subject09/c").getValue().toString();
                String sub10 = snapshot.child("subject10/c").getValue().toString();


                s1.setText(sub1);
                s2.setText(sub2);
                s3.setText(sub3);
                s4.setText(sub4);
                s5.setText(sub5);
                s6.setText(sub6);
                s7.setText(sub7);
                s8.setText(sub8);
                s9.setText(sub9);
                s10.setText(sub10);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

}

