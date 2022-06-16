package com.example.evpoly_app;

public class carItem {
    String carNum;
    String carArea;
    int resId;

    public carItem(String carNum,String carArea, int resId) {
        this.carNum = carNum;
        this.carArea = carArea;
        this.resId = resId;

    }

    public String getcarNum() {
        return carNum;
    }
    public void setcarNum(String carNum) {
        this.carNum = carNum;
    }

    public String getcarArea() {
        return carArea;
    }
    public void setcarArea(String carArea) {
        this.carNum = carArea;
    }

    public int getResId() {
        return resId;
    }
    public void setResId(int resId) {
        this.resId = resId;
    }
}
