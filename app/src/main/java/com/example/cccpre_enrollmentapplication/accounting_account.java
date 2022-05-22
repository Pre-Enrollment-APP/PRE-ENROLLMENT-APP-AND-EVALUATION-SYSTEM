package com.example.cccpre_enrollmentapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.content.Intent;
import android.view.View;

public class accounting_account extends AppCompatActivity {
    public ImageButton menu_page;
    public ImageButton bscs;
    public ImageButton ABREED;
    public ImageButton beed;
    public ImageButton bsoa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accounting_account);

        menu_page=findViewById(R.id.menuicon);
        menu_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(accounting_account.this,tabmenu.class);
                startActivity(intent);
            }
        });

        bscs=findViewById(R.id.stbscs);
        bscs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(accounting_account.this,mis_accounting.class);
                startActivity(intent);
            }
        });

        beed=findViewById(R.id.stbscs);
        beed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(accounting_account.this,mis_accounting.class);
                startActivity(intent);
            }
        });

        bsoa=findViewById(R.id.stbsoa);
        bsoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(accounting_account.this,mis_accounting.class);
                startActivity(intent);
            }
        });

        ABREED=findViewById(R.id.ABREED);
        ABREED.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(accounting_account.this,mis_accounting.class);
                startActivity(intent);
            }
        });


    }
}
