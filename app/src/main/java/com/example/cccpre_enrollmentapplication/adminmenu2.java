package com.example.cccpre_enrollmentapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;

public class adminmenu2 extends AppCompatActivity {
public ImageButton registrar;
    public ImageButton accounting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_menu2);

        registrar=findViewById(R.id.buttonregistrar);
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(adminmenu2.this,registrarpage.class);
                startActivity(intent);
            }
        });


        accounting=findViewById(R.id.buttonaccount);
        accounting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent hello=new Intent(adminmenu2.this,accounting_account.class);
                startActivity(hello);
            }
        });
    }
}