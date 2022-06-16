package com.example.evpoly_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    private GridView carGrid2 = null;
    private GridViewAdapter adapter = null;
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
                R.array.parkingArea, android.R.layout.simple_spinner_dropdown_item);
        SB_parkingArea2.setAdapter(parkingArea);
        SB_parkingArea2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                ((TextView) SB_parkingArea2.getChildAt(0)).setTextColor(Color.BLACK);
                String Area1="";
                String Area2="";
                String Area3="";
                String Area4="";
                String Area5="";
                String Area6="";
                int car_photo = 0;
                if (position == 0) {
                    Area1 = "123가 1234";
                    Area2 = "123가 1234";
                    Area3 = "123가 1234";
                    Area4 = "123가 1234";
                    Area5 = "123가 1234";
                    Area6 = "123가 1234";
                    car_photo = R.drawable.car1;
                } else if (position == 1) {
                    Area1 = "234가 2345";
                    Area2 = "234가 2345";
                    Area3 = "234가 2345";
                    Area4 = "234가 2345";
                    Area5 = "234가 2345";
                    Area6 = "234가 2345";
                    car_photo = R.drawable.car2;
                } else if (position == 2) {
                    Area1 = "345가 3456";
                    Area2 = "345가 3456";
                    Area3 = "345가 3456";
                    Area4 = "345가 3456";
                    Area5 = "345가 3456";
                    Area6 = "345가 3456";
                    car_photo = R.drawable.car3;
                } else if (position == 3) {
                    Area1 = "456가 4567";
                    Area2 = "456가 4567";
                    Area3 = "456가 4567";
                    Area4 = "456가 4567";
                    Area5 = "456가 4567";
                    Area6 = "456가 4567";
                    car_photo = R.drawable.car4;
                }
                carGrid2 = (GridView) findViewById(R.id.carGrid2);
                adapter = new GridViewAdapter();

                //Adapter 안에 아이템의 정보 담기
                adapter.addItem(new carItem("B1층 1번", Area1,car_photo));
                adapter.addItem(new carItem("B1층 2번", Area2,car_photo));
                adapter.addItem(new carItem("B1층 3번", Area3,car_photo));
                adapter.addItem(new carItem("B2층 1번", Area4,car_photo));
                adapter.addItem(new carItem("B2층 2번", Area5,car_photo));
                adapter.addItem(new carItem("B2층 3번", Area6,car_photo));
                //리스트뷰에 Adapter 설정
                carGrid2.setAdapter(adapter);

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

        carGrid2 = (GridView) findViewById(R.id.carGrid2);
        adapter = new GridViewAdapter();
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
    Calendar minDate = Calendar.getInstance();
    public void mOnClick_DatePick(View view) {
        // DATE Picker가 처음 떴을 때, 오늘 날짜가 보이도록 설정.
        Calendar cal = Calendar.getInstance();
        new DatePickerDialog(this, mDateSetListener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE)).show();

    }

}

