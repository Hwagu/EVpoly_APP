package com.example.evpoly_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Page_Parking extends AppCompatActivity {
    ImageView BTN_parkingTOhome;
    ImageView BTN_parkingTOhistory;
    ImageView BTN_parkingTOnotice;
    Spinner SB_parkingArea;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_parking);

        BTN_parkingTOhome = findViewById(R.id.BTN_parkingTOhome);
        BTN_parkingTOhistory = findViewById(R.id.BTN_parkingTOhistory);
        BTN_parkingTOnotice = findViewById(R.id.BTN_parkingTOnotice);
        textView = findViewById(R.id.textView);

        //페이지 이동
        BTN_parkingTOhome.setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        Intent intent1 = new Intent(getApplicationContext(), Page_Home.class);
                        startActivity(intent1);
                    }
                }
        );
        BTN_parkingTOhistory.setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        Intent intent1 = new Intent(getApplicationContext(), Page_History.class);
                        startActivity(intent1);
                    }
                }
        );
        BTN_parkingTOnotice.setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        Intent intent1 = new Intent(getApplicationContext(), Page_Notice.class);
                        startActivity(intent1);
                    }
                }
        );

        //스피너 어댑터

        SB_parkingArea = (Spinner)findViewById(R.id.SB_parkingArea);
        ArrayAdapter parkingArea = ArrayAdapter.createFromResource(this,
                R.array.parkingArea, android.R.layout.simple_spinner_item);
        SB_parkingArea.setAdapter(parkingArea);
        SB_parkingArea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                ((TextView) SB_parkingArea.getChildAt(0)).setTextColor(Color.BLACK);

                textView.setText(SB_parkingArea.getSelectedItem().toString());

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
            });
    }
    }

