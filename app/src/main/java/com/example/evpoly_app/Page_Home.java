package com.example.evpoly_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Page_Home extends AppCompatActivity {

    ImageView BTN_homeTOparking;
    ImageView BTN_homeTOhistory;
    ImageView BTN_homeTOnotice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_home);

        BTN_homeTOparking = findViewById(R.id.BTN_homeTOparking);
        BTN_homeTOhistory = findViewById(R.id.BTN_homeTOhistory);
        BTN_homeTOnotice = findViewById(R.id.BTN_homeTOnotice);


        BTN_homeTOparking.setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        Intent intent1 = new Intent(getApplicationContext(), Page_Parking.class);
                        startActivity(intent1);
                    }
                }
        );
        BTN_homeTOhistory.setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        Intent intent1 = new Intent(getApplicationContext(), Page_History.class);
                        startActivity(intent1);
                    }
                }
        );
        BTN_homeTOnotice.setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        Intent intent1 = new Intent(getApplicationContext(), Page_Notice.class);
                        startActivity(intent1);
                    }
                }
        );

    }
}