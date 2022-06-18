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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class Page_History extends AppCompatActivity {
    public String carnum1; public String carnum2; public String carnum3;
    public String carnum4; public String carnum5; public String carnum6;
    public String carnum7; public String carnum8; public String carnum9;
    public String carnum10; String carnum_default ="OOO가OOOO";

    public String carEV1;    public String carEV2;    public String carEV3;
    public String carEV4;    public String carEV5;    public String carEV6;
    public String carEV7;    public String carEV8;    public String carEV9;
    public String carEV10;   public String carEV = "-";

    public String carTime1;    public String carTime2;    public String carTime3;
    public String carTime4;    public String carTime5;    public String carTime6;
    public String carTime7;    public String carTime8;    public String carTime9;
    public String carTime10;   public String carTime = "-";
    public String A;

    ImageView BTN_historyTOhome;
    ImageView BTN_historyTOparking;
    ImageView BTN_historyTOnotice;
    ImageView BTN_calendar;
    TextView TEXT_date;
    Spinner SB_parkingArea2;
    TextView textView4;

    private GridView carGrid2 = null;
    private GridViewAdapter adapter = null;
    // TextView TEXT_date;
    private TimePickerDialog.OnTimeSetListener callbackMethod;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_history);
        textView4 = findViewById(R.id.textView4);
        BTN_historyTOhome = findViewById(R.id.BTN_historyTOhome);
        BTN_historyTOparking = findViewById(R.id.BTN_historyTOparking);
        BTN_historyTOnotice = findViewById(R.id.BTN_historyTOnotice);
        BTN_calendar = findViewById(R.id.BTN_calendar);

        TEXT_date = findViewById(R.id.TEXT_date);
        //페이지 이동
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

        DatePickerDialog.OnDateSetListener mDateSetListener =
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int yy, int mm, int dd) {
                        // Date Picker에서 선택한 날짜를 TextView에 설정
                        TextView TEXT_date = findViewById(R.id.TEXT_date);
                        TEXT_date.setText(String.format("%d-%d-%d", yy, mm +1 , dd));
                        A = String.format("%d-%d-%d", yy, mm +1 , dd);
                    }
                };

        //json 파싱
        String resultText = "[NULL]";
        try {
            resultText = new Task().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        JSONParse1(resultText);

        //스피너 어댑터
        SB_parkingArea2 = (Spinner) findViewById(R.id.SB_parkingArea2);
        ArrayAdapter parkingArea = ArrayAdapter.createFromResource(this,
                R.array.parkingArea, android.R.layout.simple_spinner_dropdown_item);
        SB_parkingArea2.setAdapter(parkingArea);
        SB_parkingArea2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                ((TextView) SB_parkingArea2.getChildAt(0)).setTextColor(Color.BLACK);
                int car_photo = 0;
                carGrid2 = (GridView) findViewById(R.id.carGrid2);
                adapter = new GridViewAdapter();

                if (position == 0) {
                    car_photo = R.drawable.car1;
                    //Adapter 안에 아이템의 정보 담기
                    int idx = carTime3.indexOf(" ");
                    String result = carTime3.substring(0,idx);
                    textView4.setText(result);

                    adapter.addItem(new carItem("1층 A구역", "급속충전", carnum1, carEV1, carTime1, car_photo));
                    adapter.addItem(new carItem("1층 B구역", "급속충전", carnum2, carEV2, carTime2, car_photo));
                    adapter.addItem(new carItem("1층 C구역", "급속충전", carnum3, carEV3, carTime3, car_photo));
                    adapter.addItem(new carItem("2층 A구역", "일반충전", carnum4, carEV4, carTime4, car_photo));
                    adapter.addItem(new carItem("2층 B구역", "일반충전", carnum5, carEV5, carTime5, car_photo));
                    adapter.addItem(new carItem("2층 C구역", "일반충전", carnum6, carEV6, carTime6, car_photo));
                    car_photo = R.drawable.car1;
                } else if (position == 1) {
                    car_photo = R.drawable.car2;
                    //Adapter 안에 아이템의 정보 담기
                    adapter.addItem(new carItem("1층 A구역", "급속충전", carnum7, carEV7, carTime7, car_photo));
                    adapter.addItem(new carItem("1층 B구역", "급속충전", carnum8, carEV8, carTime8, car_photo));
                    adapter.addItem(new carItem("2층 A구역", "일반충전", carnum9, carEV9, carTime9, car_photo));
                    adapter.addItem(new carItem("2층 B구역", "일반충전", carnum10, carEV10, carTime10, car_photo));

                } else if (position == 2) {
                    car_photo = R.drawable.car3;
                    adapter.addItem(new carItem("1층 A구역", "급속충전", carnum_default, carEV, carTime, car_photo));
                    adapter.addItem(new carItem("1층 B구역", "급속충전", carnum_default, carEV, carTime, car_photo));
                    adapter.addItem(new carItem("1층 C구역", "급속충전", carnum_default, carEV, carTime, car_photo));
                    adapter.addItem(new carItem("2층 A구역", "일반충전", carnum_default, carEV, carTime, car_photo));
                    adapter.addItem(new carItem("2층 B구역", "일반충전", carnum_default, carEV, carTime, car_photo));
                    adapter.addItem(new carItem("2층 C구역", "일반충전", carnum_default, carEV, carTime, car_photo));
                    car_photo = R.drawable.car3;
                }
                //리스트뷰에 Adapter 설정
                carGrid2.setAdapter(adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

//        //현재 날짜로 설정
//        long now = System.currentTimeMillis();
//        Date mDate = new Date(now);
//        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
//        String getTime = simpleDate.format(mDate);
//        //String getTime1 = simpleDate.format(A);
//        TEXT_date.setText(getTime);
    }



//    public void mOnClick_DatePick(View view) {
//        // DATE Picker가 처음 떴을 때, 오늘 날짜가 보이도록 설정.
//        Calendar cal = Calendar.getInstance();
//        new DatePickerDialog(this, mDateSetListener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE)).show();
//    }

    void JSONParse1 (String jsonStr) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            JSONArray jsonArray = new JSONArray(jsonStr);
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if (jsonObject.getInt("구역") == 30001) {
                    carnum1 = jsonObject.getString("차량번호");
                    carTime1 = jsonObject.getString("입차시간");
                    if (jsonObject.getString("전기차 여부").equals("N")) {
                        carEV1 = "X";
                    } else if (jsonObject.getString("전기차 여부").equals("Y")) {
                        carEV1 = "O";
                    }
                } else if (jsonObject.getInt("구역") == 30002) {
                    carnum2 = jsonObject.getString("차량번호");
                    carTime2 = jsonObject.getString("입차시간");
                    if (jsonObject.getString("전기차 여부").equals("N")) {
                        carEV2 = "X";
                    } else if (jsonObject.getString("전기차 여부").equals("Y")) {
                        carEV2 = "O";
                    }
                } else if (jsonObject.getInt("구역") == 30003) {
                    carnum3 = jsonObject.getString("차량번호");
                    carTime3 = jsonObject.getString("입차시간");
                    if (jsonObject.getString("전기차 여부").equals("N")) {
                        carEV3 = "X";
                    } else if (jsonObject.getString("전기차 여부").equals("Y")) {
                        carEV3 = "O";
                    }

//                    if(simpleDate.format(jsonObject.getInt("입차시간"))==A)
//                    {
//                        textView4.setText(simpleDate.format(jsonObject.getInt("입차시간")));
//                    }
                } else if (jsonObject.getInt("구역") == 30004) {
                    carnum4 = jsonObject.getString("차량번호");
                   // A = jsonObject.getString("입차시간");
                    //textView4.setText(jsonObject.getString("입차시간"));
                    if (jsonObject.getString("전기차 여부").equals("N")) {
                        carEV4 = "X";
                    } else if (jsonObject.getString("전기차 여부").equals("Y")) {
                        carEV4 = "O";
                    }
                } else if (jsonObject.getInt("구역") == 30005) {
                    carnum5 = jsonObject.getString("차량번호");
                    carTime5 = jsonObject.getString("입차시간");
                    if (jsonObject.getString("전기차 여부").equals("N")) {
                        carEV5 = "X";
                    } else if (jsonObject.getString("전기차 여부").equals("Y")) {
                        carEV5 = "O";
                    }
                } else if (jsonObject.getInt("구역") == 30006) {
                    carnum6 = jsonObject.getString("차량번호");
                    carTime6 = jsonObject.getString("입차시간");
                    if (jsonObject.getString("전기차 여부").equals("N")) {
                        carEV6 = "X";
                    } else if (jsonObject.getString("전기차 여부").equals("Y")) {
                        carEV6 = "O";
                    }
                } else if (jsonObject.getInt("구역") == 30007) {
                    carnum7 = jsonObject.getString("차량번호");
                    carTime7 = jsonObject.getString("입차시간");
                    if (jsonObject.getString("전기차 여부").equals("N")) {
                        carEV7 = "X";
                    } else if (jsonObject.getString("전기차 여부").equals("Y")) {
                        carEV7 = "O";
                    }
                } else if (jsonObject.getInt("구역") == 30008) {
                    carnum8 = jsonObject.getString("차량번호");
                    carTime8 = jsonObject.getString("입차시간");
                    if (jsonObject.getString("전기차 여부").equals("N")) {
                        carEV8 = "X";
                    } else if (jsonObject.getString("전기차 여부").equals("Y")) {
                        carEV8 = "O";
                    }
                } else if (jsonObject.getInt("구역") == 30009) {
                    carnum9 = jsonObject.getString("차량번호");
                    carTime9 = jsonObject.getString("입차시간");
                    if (jsonObject.getString("전기차 여부").equals("N")) {
                        carEV9 = "X";
                    } else if (jsonObject.getString("전기차 여부").equals("Y")) {
                        carEV9 = "O";
                    }
                } else if (jsonObject.getInt("구역") == 30010) {
                    carnum10 = jsonObject.getString("차량번호");
                    carTime10 = jsonObject.getString("입차시간");
                    if (jsonObject.getString("전기차 여부").equals("N")) {
                        carEV10 = "X";
                    } else if (jsonObject.getString("전기차 여부").equals("Y")) {
                        carEV10 = "O";
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}

