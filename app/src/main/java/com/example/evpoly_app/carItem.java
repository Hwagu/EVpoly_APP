package com.example.evpoly_app;

import android.graphics.drawable.Drawable;

public class carItem {
    String carNum;
    String carAreaType;
    String carArea;
    String carEV;
    String carTime;
    Drawable resId;

    public carItem(String carArea,String carAreaType, String carNum,String carEV,String carTime, Drawable resId) {
        this.carNum = carNum;
        this.carAreaType = carAreaType;
        this.carEV = carEV;
        this.carArea = carArea;
        this.carTime = carTime;
        this.resId = resId;

    }

    public String getcarNum() {
        return carNum;
    }
    public void setcarNum(String carNum) {
        this.carNum = carNum;
    }

    public String getcarAreaType() {
        return carAreaType;
    }
    public void setcarAreaType(String carAreaType) {
        this.carAreaType = carAreaType;
    }

    public String getcarArea() {
        return carArea;
    }
    public void setcarArea(String carArea) {this.carNum = carArea;}

    public String getcarEV() {
        return carEV;
    }
    public void setcarEV(String carEV) { this.carEV = carEV;}

    public String getcarTime() {
        return carTime;
    }
    public void setcarTime(String carTime) { this.carTime = carTime;}

    public Drawable getResId() {
        return resId;
    }
    public void setResId(Drawable resId) {
        this.resId = resId;
    }
}
