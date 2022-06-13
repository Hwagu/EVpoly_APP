package com.example.evpoly_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Page_Notice extends AppCompatActivity {
    ImageView BTN_noticeTOhome;
    ImageView BTN_noticeTOparking;
    ImageView BTN_noticeTOhistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_notice);

        BTN_noticeTOhome = findViewById(R.id.BTN_noticeTOhome);
        BTN_noticeTOparking = findViewById(R.id.BTN_noticeTOparking);
        BTN_noticeTOhistory = findViewById(R.id.BTN_noticeTOhistory);


        BTN_noticeTOhome.setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        Intent intent1 = new Intent(getApplicationContext(), Page_Home.class);
                        startActivity(intent1);
                    }
                }
        );
        BTN_noticeTOparking.setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        Intent intent1 = new Intent(getApplicationContext(), Page_Parking.class);
                        startActivity(intent1);
                    }
                }
        );
        BTN_noticeTOhistory.setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        Intent intent1 = new Intent(getApplicationContext(), Page_History.class);
                        startActivity(intent1);
                    }
                }
        );
    }

}