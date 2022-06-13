package com.example.evpoly_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Page_History extends AppCompatActivity {
    ImageView BTN_historyTOhome;
    ImageView BTN_historyTOparking;
    ImageView BTN_historyTOnotice;
    ImageView BTN_calendar;
    TextView TEXT_date;
    Spinner SB_parkingArea2;
    // TextView TEXT_date;
    private TimePickerDialog.OnTimeSetListener callbackMethod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_history);

        BTN_historyTOhome = findViewById(R.id.BTN_historyTOhome);
        BTN_historyTOparking = findViewById(R.id.BTN_historyTOparking);
        BTN_historyTOnotice = findViewById(R.id.BTN_historyTOnotice);
        BTN_calendar = findViewById(R.id.BTN_calendar);
        TEXT_date = findViewById(R.id.TEXT_date);
        SB_parkingArea2 = (Spinner) findViewById(R.id.SB_parkingArea2);

        BTN_historyTOhome.setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent1 = new Intent(getApplicationContext(), Page_Home.class);
                        startActivity(intent1);
                    }
                }
        );
        BTN_historyTOparking.setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent1 = new Intent(getApplicationContext(), Page_Parking.class);
                        startActivity(intent1);
                    }
                }
        );
        BTN_historyTOnotice.setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent1 = new Intent(getApplicationContext(), Page_Notice.class);
                        startActivity(intent1);
                    }
                }
        );

        //스피너 어댑터
        ArrayAdapter parkingArea = ArrayAdapter.createFromResource(this,
                R.array.parkingArea, android.R.layout.simple_spinner_item);
        SB_parkingArea2.setAdapter(parkingArea);
        SB_parkingArea2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                ((TextView) SB_parkingArea2.getChildAt(0)).setTextColor(Color.BLACK);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        //현재 날짜로 설정
        long now = System.currentTimeMillis();
        Date mDate = new Date(now);
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
        String getTime = simpleDate.format(mDate);
        TEXT_date.setText(getTime);
    }


    DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int yy, int mm, int dd) {
                    // Date Picker에서 선택한 날짜를 TextView에 설정
                    TextView TEXT_date = findViewById(R.id.TEXT_date);
                    TEXT_date.setText(String.format("%d-%d-%d", yy, mm + 1, dd));
                }
            };

    public void mOnClick_DatePick(View view) {
        // DATE Picker가 처음 떴을 때, 오늘 날짜가 보이도록 설정.
        Calendar cal = Calendar.getInstance();
        new DatePickerDialog(this, mDateSetListener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE)).show();
    }

}