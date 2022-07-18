package com.example.evpoly_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.daum.android.map.MapViewEventListener;
//import com.google.android.gms.maps.MapView;
import net.daum.mf.map.api.MapView;
import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;

public class Page_Home extends AppCompatActivity {

    ImageView BTN_homeTOparking;
    ImageView BTN_homeTOhistory;
    ImageView BTN_homeTOnotice;
    TextView  TEXT_todaycar;
    TextView  TEXT_weeklycar;
    TextView  TEXT_monthlycar;
    ViewGroup mapView;
    RelativeLayout mapViewContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_home);

        BTN_homeTOparking = findViewById(R.id.BTN_homeTOparking);
        BTN_homeTOhistory = findViewById(R.id.BTN_homeTOhistory);
        BTN_homeTOnotice = findViewById(R.id.BTN_homeTOnotice);
        BTN_homeTOnotice = findViewById(R.id.BTN_homeTOnotice);
        TEXT_todaycar = findViewById(R.id.TEXT_todaycar);
        TEXT_weeklycar = findViewById(R.id.TEXT_weeklycar);
        TEXT_monthlycar = findViewById(R.id.TEXT_monthlycar);

        //페이지 이동
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

        TEXT_todaycar.setText("8대 / 10대");
        TEXT_weeklycar.setText("78대");
        TEXT_monthlycar.setText("350대");


        //지도
        MapView mapView = new MapView(this);
        ViewGroup mapViewContainer = (ViewGroup) findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);
        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(35.877412, 128.735869), true); //지도중심점
        MapPOIItem marker = new MapPOIItem(); //마커
        marker.setItemName("Default Marker");
        marker.setTag(0);
        marker.setMapPoint(MapPoint.mapPointWithGeoCoord(35.877412, 128.735869));
        marker.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
        marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
        mapView.addPOIItem(marker);
    }
}