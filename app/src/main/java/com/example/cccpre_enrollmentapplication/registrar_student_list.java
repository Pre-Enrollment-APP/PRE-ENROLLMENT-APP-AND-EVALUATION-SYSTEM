package com.example.cccpre_enrollmentapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Spinner;

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
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class registrar_student_list extends AppCompatActivity {

    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference myRef=database.getReference("record");
    Button saveAndPrintButton, printButton;
    EditText editTextName,editTextQty;
    Spinner spinner;
    Paint forLinePaint=new Paint();
    studentinfo dataObj=new studentinfo();
    String[] itemList;
    double[] itemPrice;
    long invoiceNo=0;
    ArrayAdapter<String> adapter;
    DecimalFormat decimalFormat= new DecimalFormat("#.##");
    SimpleDateFormat datePattternformat= new SimpleDateFormat("dd-MM-yyyy hh:mm a");
    //
    private FirebaseUser user;
    private String userID;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrar_student_list);

        callFindViewById();
        callOnclickListener();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                invoiceNo =snapshot.getChildrenCount();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }

    private void callOnclickListener(){
        saveAndPrintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  dataObj.invoiceNo=invoiceNo+1;
             //   dataObj.customerName=String.valueOf(editTextName.getText());
              //  dataObj.date=new Date().getTime();
              //  dataObj.fuelType=spinner.getSelectedItem().toString();
               // dataObj.fuelQty=Double.parseDouble(String.valueOf(editTextQty.getText()));
              //  dataObj.amount=Double.valueOf(decimalFormat.format(dataObj.getFuelQty()*itemPrice[spinner.getSelectedItemPosition()]));

                myRef.child(String.valueOf(invoiceNo+1)).setValue(dataObj);
                printPDF();
            }
        });
    }

    private void  printPDF(){
        PdfDocument myPdfDocument= new PdfDocument();
        Paint paint= new Paint();
        PdfDocument.PageInfo myPageInfo = new PdfDocument.PageInfo.Builder(216,280,1).create();
        PdfDocument.Page myPage =myPdfDocument.startPage(myPageInfo);
        Canvas canvas =myPage.getCanvas();

        paint.setTextSize(15.5f);
        paint.setColor(Color.rgb(0,50,250));

        canvas.drawText("Hare Krisha Fuel Station", 20,20, paint);
        paint.setColor(Color.rgb(0,50,250));

        canvas.drawText("Plot No.2, Shri Bharat Marg",20,40,paint);
        canvas.drawText(editTextQty.getText()+"ltr",120,135,paint);

        myPdfDocument.finishPage(myPage);
        File file =new File(this.getExternalFilesDir("/"),"hehehe.pdf");

        try {
            myPdfDocument.writeTo(new FileOutputStream(file));
        }catch (IOException e){
            e.printStackTrace();
        }

        /*DatabaseReference Ref=database.getReference("User");
        mAuth=FirebaseAuth.getInstance();
        user=FirebaseAuth.getInstance().getCurrentUser();
        userID=user.getUid();
        Ref.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/
    }
    private void callFindViewById(){
        saveAndPrintButton=findViewById(R.id.btnSaveAndPrint);
        printButton=findViewById(R.id.btnPrint);
        editTextName=findViewById(R.id.editTextName);
        editTextQty=findViewById(R.id.editTextQty);

        spinner=findViewById(R.id.spinner);
        itemList=new String[]{"Petrol","Diesel"};
        itemPrice=new double[]{72,56,36,56};
        adapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, itemList);
        spinner.setAdapter(adapter);




    }
}