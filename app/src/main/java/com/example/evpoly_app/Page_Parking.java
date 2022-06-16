package com.example.evpoly_app;

import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class Page_Parking extends AppCompatActivity {

    ImageView BTN_parkingTOhome;
    ImageView BTN_parkingTOhistory;
    ImageView BTN_parkingTOnotice;
    Spinner SB_parkingArea;

    public int A;
    public static String Area;
    private GridView carGrid1 = null;
    private GridViewAdapter adapter = null;
    String carnum = " 1234";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_parking);
        BTN_parkingTOhome = findViewById(R.id.BTN_parkingTOhome);
        BTN_parkingTOhistory = findViewById(R.id.BTN_parkingTOhistory);
        BTN_parkingTOnotice = findViewById(R.id.BTN_parkingTOnotice);

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
//        ArrayAdapter parkingArea = ArrayAdapter.createFromResource(this,
//                R.array.parkingArea);
        ArrayAdapter parkingArea = ArrayAdapter.createFromResource(this,
                R.array.parkingArea, android.R.layout.simple_spinner_dropdown_item);
        SB_parkingArea.setAdapter(parkingArea);
        SB_parkingArea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                ((TextView) SB_parkingArea.getChildAt(0)).setTextColor(Color.BLACK);
                //textView.setText(SB_parkingArea.getSelectedItem().toString());
                String Area1="";
                String Area2="";
                String Area3="";
                String Area4="";
                String Area5="";
                String Area6="";
                int car_photo=0;
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
                carGrid1 = (GridView) findViewById(R.id.carGrid1);
                adapter = new GridViewAdapter();

                //Adapter 안에 아이템의 정보 담기
                adapter.addItem(new carItem("B1층 1번", Area1,car_photo));
                adapter.addItem(new carItem("B1층 2번", Area2,car_photo));
                adapter.addItem(new carItem("B1층 3번", Area3,car_photo));
                adapter.addItem(new carItem("B2층 1번", Area4,car_photo));
                adapter.addItem(new carItem("B2층 2번", Area5,car_photo));
                adapter.addItem(new carItem("B2층 3번", Area6,car_photo));
                //리스트뷰에 Adapter 설정
                carGrid1.setAdapter(adapter);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
            });
    }
    }


/* 그리드뷰 어댑터 */
class GridViewAdapter extends BaseAdapter {
    ArrayList<carItem> items = new ArrayList<carItem>();

    @Override
    public int getCount() {
        return items.size();
    }

    public void addItem(carItem item) {
        items.add(item);
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        final Context context = viewGroup.getContext();
        final carItem carItem = items.get(position);

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.car_list_layout, viewGroup, false);

            TextView TEXT_carArea = (TextView) convertView.findViewById(R.id.TEXT_carArea);
            TextView TEXT_carNum = (TextView) convertView.findViewById(R.id.TEXT_carNum);
            ImageView IMG_carPhoto = (ImageView) convertView.findViewById(R.id.IMG_carPhoto);

            TEXT_carArea.setText(carItem.getcarNum());
            TEXT_carNum.setText(carItem.getcarArea());
            IMG_carPhoto.setImageResource(carItem.getResId());
            //Log.d(TAG, "getView() - [ "+position+" ] "+bearItem.getName());

        } else {
            View view = new View(context);
            view = (View) convertView;
        }

//        //각 아이템 선택 event
//        convertView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(context, bearItem.getNum()+" 번 - "+bearItem.getName()+" 입니당! ", Toast.LENGTH_SHORT).show();
//            }
//        });

        return convertView;  //뷰 객체 반환
    }
}

