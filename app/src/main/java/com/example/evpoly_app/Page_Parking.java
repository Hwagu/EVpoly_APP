package com.example.evpoly_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
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
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Page_Parking extends AppCompatActivity {

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
    public Bitmap getBlob;
    public Drawable drawable;

    ImageView BTN_parkingTOhome;
    ImageView BTN_parkingTOhistory;
    ImageView BTN_parkingTOnotice;
    Spinner SB_parkingArea;

    private GridView carGrid1 = null;
    private GridViewAdapter adapter = null;


    @Override
    public  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_parking);
        BTN_parkingTOhome = findViewById(R.id.BTN_parkingTOhome);
        BTN_parkingTOhistory = findViewById(R.id.BTN_parkingTOhistory);
        BTN_parkingTOnotice = findViewById(R.id.BTN_parkingTOnotice);
        drawable= getResources().getDrawable(R.drawable.nocar);

        //json 파싱

        String resultText = "[NULL]";
        try {
            resultText = new Task().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        JSONParse(resultText);
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
                R.array.parkingArea, android.R.layout.simple_spinner_dropdown_item);
        SB_parkingArea.setAdapter(parkingArea);
        SB_parkingArea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                ((TextView) SB_parkingArea.getChildAt(0)).setTextColor(Color.BLACK);
                Drawable car_photo;
                Drawable no_car_photo;
                no_car_photo = getResources().getDrawable(R.drawable.nocar);
                carGrid1 = (GridView) findViewById(R.id.carGrid1);
                adapter = new GridViewAdapter();
                if (position == 0) {  //1층
                    car_photo = getResources().getDrawable(R.drawable.car1);

                    //Adapter 안에 아이템의 정보 담기
                    adapter.addItem(new carItem("1층 A구역","급속충전", carnum1,carEV1,carTime1,drawable));
                    adapter.addItem(new carItem("1층 B구역","급속충전", carnum2,carEV2,carTime2,no_car_photo));
                    adapter.addItem(new carItem("1층 C구역","급속충전", carnum3,carEV3,carTime3,car_photo));
                    adapter.addItem(new carItem("2층 A구역","일반충전", carnum4,carEV4,carTime4,car_photo));
                    adapter.addItem(new carItem("2층 B구역","일반충전", carnum5,carEV5,carTime5,car_photo));
                    adapter.addItem(new carItem("2층 C구역","일반충전", carnum6,carEV6,carTime6,car_photo));

                } else if (position == 1) {  //2층
                    car_photo = getResources().getDrawable(R.drawable.car2);

                    //Adapter 안에 아이템의 정보 담기
                    adapter.addItem(new carItem("1층 A구역","급속충전", carnum7,carEV7,carTime7,car_photo));
                    adapter.addItem(new carItem("1층 B구역","급속충전", carnum8,carEV8,carTime8,car_photo));
                    adapter.addItem(new carItem("2층 A구역","일반충전", carnum9,carEV9,carTime9,car_photo));
                    adapter.addItem(new carItem("2층 B구역","일반충전", carnum10,carEV10,carTime10,no_car_photo));


                }
//                else if (position == 2) {   //3층
//                    car_photo = getResources().getDrawable(R.drawable.car3);
//                    adapter.addItem(new carItem("1층 A구역","급속충전", carnum_default, carEV, carTime, car_photo));
//                    adapter.addItem(new carItem("1층 B구역","급속충전", carnum_default, carEV, carTime, car_photo));
//                    adapter.addItem(new carItem("1층 C구역","급속충전", carnum_default, carEV, carTime,car_photo));
//                    adapter.addItem(new carItem("2층 A구역","일반충전", carnum_default, carEV, carTime,car_photo));
//                    adapter.addItem(new carItem("2층 B구역","일반충전", carnum_default, carEV, carTime,car_photo));
//                    adapter.addItem(new carItem("2층 C구역","일반충전", carnum_default, carEV, carTime,car_photo));
//                }

                //리스트뷰에 Adapter 설정
                carGrid1.setAdapter(adapter);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
            });
    }
    void JSONParse(String jsonStr){
        StringBuilder stringBuilder = new StringBuilder();
        try {
            JSONArray jsonArray = new JSONArray(jsonStr);
            for(int i = 0; i < jsonArray.length(); i++){

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if(jsonObject.getInt("구역")==30001)
                {
                    carnum1 = jsonObject.getString("차량번호");
                    carTime1 = jsonObject.getString("입차시간");
                   // getBlob=new Bitmap[];
                    Bitmap getBlob;
                    getBlob=StringToBitMap(jsonObject.getString("차량사진"));
                    drawable = new BitmapDrawable(getBlob);
                    if(jsonObject.getString("전기차 여부").equals("N")){
                        carEV1 =  "X";
                    }else if(jsonObject.getString("전기차 여부").equals("Y")){
                        carEV1 =  "O";}
                }else if(jsonObject.getInt("구역")==30002)
                {
                    carnum2 = jsonObject.getString("차량번호");
                    carTime2 = jsonObject.getString("입차시간");
                    if(jsonObject.getString("전기차 여부").equals("N")){
                        carEV2 =  "X";
                    }else if(jsonObject.getString("전기차 여부").equals("Y")){
                        carEV2 =  "O";}
                }else if(jsonObject.getInt("구역")==30003)
                {
                    carnum3 = jsonObject.getString("차량번호");
                    carTime3 = jsonObject.getString("입차시간");
                    if(jsonObject.getString("전기차 여부").equals("N")){
                        carEV3 =  "X";
                    }else if(jsonObject.getString("전기차 여부").equals("Y")){
                        carEV3 =  "O";}
                }else if(jsonObject.getInt("구역")==30004)
                {
                    carnum4 = jsonObject.getString("차량번호");
                    carTime4 = jsonObject.getString("입차시간");
                    if(jsonObject.getString("전기차 여부").equals("N")){
                        carEV4 =  "X";
                    }else if(jsonObject.getString("전기차 여부").equals("Y")){
                        carEV4 =  "O";}
                }else if(jsonObject.getInt("구역")==30005)
                {
                    carnum5 = jsonObject.getString("차량번호");
                    carTime5 = jsonObject.getString("입차시간");
                    if(jsonObject.getString("전기차 여부").equals("N")){
                        carEV5 =  "X";
                    }else if(jsonObject.getString("전기차 여부").equals("Y")){
                        carEV5 =  "O";}
                }else if(jsonObject.getInt("구역")==30006)
                {
                    carnum6 = jsonObject.getString("차량번호");
                    carTime6 = jsonObject.getString("입차시간");
                    if(jsonObject.getString("전기차 여부").equals("N")){
                        carEV6 =  "X";
                    }else if(jsonObject.getString("전기차 여부").equals("Y")){
                        carEV6 =  "O";}
                }else if(jsonObject.getInt("구역")==30007)
                {
                    carnum7 = jsonObject.getString("차량번호");
                    carTime7 = jsonObject.getString("입차시간");
                    if(jsonObject.getString("전기차 여부").equals("N")){
                        carEV7 =  "X";
                    }else if(jsonObject.getString("전기차 여부").equals("Y")){
                        carEV7 =  "O";}
                }else if(jsonObject.getInt("구역")==30008)
                {
                    carnum8 = jsonObject.getString("차량번호");
                    carTime8 = jsonObject.getString("입차시간");
                    if(jsonObject.getString("전기차 여부").equals("N")){
                        carEV8 =  "X";
                    }else if(jsonObject.getString("전기차 여부").equals("Y")){
                        carEV8 =  "O";}
                }else if(jsonObject.getInt("구역")==30009)
                {
                    carnum9 = jsonObject.getString("차량번호");
                    carTime9 = jsonObject.getString("입차시간");
                    if(jsonObject.getString("전기차 여부").equals("N")){
                        carEV9 =  "X";
                    }else if(jsonObject.getString("전기차 여부").equals("Y")){
                        carEV9 =  "O";}
                }else if(jsonObject.getInt("구역")==30010)
                {
                    carnum10 = jsonObject.getString("차량번호");
                    carTime10 = jsonObject.getString("입차시간");
                    if(jsonObject.getString("전기차 여부").equals("N")){
                        carEV10 =  "X";
                    }else if(jsonObject.getString("전기차 여부").equals("Y")){
                        carEV10 =  "O";}
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private Bitmap StringToBitMap(String carPhoto) {
        try {
            byte[] encodeByte = Base64.decode(carPhoto, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
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

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.car_list_layout, viewGroup, false);

            TextView TEXT_carArea = (TextView) convertView.findViewById(R.id.TEXT_carArea);
            TextView TEXT_carNum = (TextView) convertView.findViewById(R.id.TEXT_carNum);
            TextView TEXT_carEV = (TextView) convertView.findViewById(R.id.TEXT_carEV);
            ImageView IMG_carPhoto = (ImageView) convertView.findViewById(R.id.IMG_carPhoto);

            TEXT_carArea.setText(carItem.getcarArea());
            TEXT_carNum.setText(carItem.getcarNum());
            TEXT_carEV.setText(carItem.getcarEV());
            IMG_carPhoto.setImageDrawable(carItem.getResId());
            //Log.d(TAG, "getView() - [ "+position+" ] "+bearItem.getName());

        } else {
            View view = new View(context);
            view = (View) convertView;
        }

        //각 아이템 선택 event
        convertView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Toast.makeText(context, "충전타입: " + carItem.getcarAreaType() + "\n입차시간: " + carItem.getcarTime(), Toast.LENGTH_LONG).show();
            }
        });

        return convertView;  //뷰 객체 반환
    }



    }

